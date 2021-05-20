package afan.GUI;

import afan.unit.database.DataBase;
import afan.unit.io.CSVFile;
import afan.unit.machineLearning.LogisticRegressionMethod;
import afan.unit.machineLearning.SVMMethod;
import afan.unit.math.MathMethod;
import afan.unit.plot.LineChart;
import afan.unit.plot.ROCCurve;
import org.knowm.xchart.XYChart;

import java.io.IOException;
import java.util.ArrayList;

import static afan.unit.constValue.importSetting.*;
import static afan.unit.database.DataBase.logisticRegressionMethod;
import static afan.unit.database.DataBase.svmMethod;

/**
 * 顯示csv讀入介面
 * @author afan
 */
public class ImportCSVFile extends ImportFile {

    private final CSVFile csv = new CSVFile();

    /**
     * Creates new form ImportFile
     * @param gui
     */
    public ImportCSVFile(Main gui) {
        super(gui);
    }

    protected void jButton_FilePickerActionPerformed(java.awt.event.ActionEvent evt) {
        csv.ChooseFile();
        FilePath.setText(csv.getPath());
    }

    protected void jButton_ImportActionPerformed(java.awt.event.ActionEvent evt) {
        //讀入資料
        try {
            csv.GetFileData(csv.getPath());
            //轉array
            csv.ArrayListDataToArray(Double.parseDouble(jTextField_MaxNA.getText()));
            DataBase.minAUC = Double.parseDouble(jTextField_MinAUC.getText());
            //把資料存起來
            DataBase.file = csv.getFile();

            String str = jComboBox1.getModel().getSelectedItem().toString();
            if (str.contains(algorithm_str_RunALL)) {
                DataBase.algorithm = algorithm_RunALL;
            } else if (str.contains(algorithm_str_LR)) {
                DataBase.algorithm = algorithm_LR;
            } else if (str.contains(algorithm_str_SVM)) {
                DataBase.algorithm = algorithm_SVM;
            }

            DataBase.ROC = new ROCCurve(mainGUI.getROCSpace());
            DataBase.ROC.CutOffPoint(DataBase.file.getIntLabelMat(), DataBase.file.getDoubleData(), DataBase.minAUC);
            DataBase.ROC.FilterData(DataBase.file.getDoubleData());

            mainGUI.updateROC();

            mainGUI.setJTextArea_FileDataText("File path : " + DataBase.file.getPath() + "\n" +
                    "Sample number : "+DataBase.file.getSampleName().size() + "\n" +
                    "Feature number : " + (DataBase.file.getAllTitle().length - 2) + "\n" +
                    "Filtered by NA%\n"+
                    "Feature number : " + DataBase.file.getDoubleData()[0].length + "\n" +
                    "Screened by AUC\n"+
                    "Feature number : " + DataBase.ROC.data[0].length + "\n" +
                    "Max AUC = " + MathMethod.getMaxValue(DataBase.ROC.fScores)
            );

            //跑機器學習算法
            if(jCheckBox_RunAllPermutations.isSelected()) {
                if (DataBase.algorithm == algorithm_RunALL) {
                    logisticRegressionMethod = new LogisticRegressionMethod();
                    logisticRegressionMethod.RunAllPermutationsLogisticRegression(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 100, 0.1, 1e-5);
                    svmMethod = new SVMMethod();
                    svmMethod.RunAllPermutationsSVM(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 0.1, 1e-5);
                } else if (DataBase.algorithm == algorithm_LR) {
                    logisticRegressionMethod = new LogisticRegressionMethod();
                    logisticRegressionMethod.RunAllPermutationsLogisticRegression(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 100, 0.1, 1e-5);
                } else if (DataBase.algorithm == algorithm_SVM) {
                    svmMethod = new SVMMethod();
                    svmMethod.RunAllPermutationsSVM(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 0.1, 1e-5);
                }
            }else{
                if (DataBase.algorithm == algorithm_RunALL) {
                    logisticRegressionMethod = new LogisticRegressionMethod();
                    logisticRegressionMethod.lr(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 100, 0.1, 1e-5);
                    svmMethod = new SVMMethod();
                    svmMethod.svm(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 0.1, 1e-5);
                } else if (DataBase.algorithm == algorithm_LR) {
                    logisticRegressionMethod = new LogisticRegressionMethod();
                    logisticRegressionMethod.lr(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 100, 0.1, 1e-5);
                } else if (DataBase.algorithm == algorithm_SVM) {
                    svmMethod = new SVMMethod();
                    svmMethod.svm(DataBase.ROC.data, DataBase.file.getIntLabelMat(), 0.1, 1e-5);
                }
            }

            ViewFScoresLineChart();
            mainGUI.repaint(200);

            /**
             * 如果讀好資料就關掉jframe，沒有就跳掉不關
             */
            ImportCSVFile f = this;
            f.dispose();
        } catch (IOException e) {
            System.out.println("ㄨㄚˊ，失敗了");
            FilePath.setText("Please choose file follow the format.");
            e.printStackTrace();
        }
    }

    private void ViewFScoresLineChart() {
        ArrayList<double[]> List=new ArrayList<>();
        ArrayList<String> Title=new ArrayList<>();
        if(logisticRegressionMethod!=null){
            ArrayList<Double> FScores = logisticRegressionMethod.getFScore();
            ArrayList<int[]> Combinations = logisticRegressionMethod.getCombinations();

            Title.add("LR");
            AddFScoreChartData(List, FScores, Combinations);
            List.get(List.size()-1)[0]= MathMethod.getMaxValue(DataBase.ROC.fScores);
        }
        if(svmMethod!=null){
            ArrayList<Double> FScores = svmMethod.getFScore();
            ArrayList<int[]> Combinations = svmMethod.getCombinations();

            Title.add("SVM");

            AddFScoreChartData(List, FScores, Combinations);
            List.get(List.size()-1)[0]=MathMethod.getMaxValue(DataBase.ROC.fScores);
        }
        try {
            XYChart xyChart=LineChart.DrawFScoreChart(Title, List);
//            JButton button=new JButton("Save the plot.");
//            XYChart finalXyChart = xyChart;
//            button.addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    //得到存檔路徑
//                    JFileChooser fileChooser = new JFileChooser();
//                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                    int result = fileChooser.showSaveDialog(null);
//                    String path = "";
//                    if (result == JFileChooser.APPROVE_OPTION) {
//                        path = fileChooser.getSelectedFile().getAbsolutePath();
//                    }
//                    try {
//                        BitmapEncoder.saveBitmapWithDPI(finalXyChart, path+"/FScoresLineChart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
            //刪除原先所有內容，加入新圖並重繪
            mainGUI.updateFScore(xyChart);

//            Main.updateROCUI();
//            Main.updateFScoreUI();
//
//            Main.gui.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddFScoreChartData(ArrayList<double[]> List, ArrayList<Double> FScores, ArrayList<int[]> combinations) {
        if (combinations.size()!=0) {
            List.add(MathMethod.GetBestFScore(FScores, combinations, DataBase.ROC.data[0].length));
        }else{
            double[] list=new double[FScores.size()+1];
            for(int i=0;i<FScores.size();i++){
                list[i+1]=FScores.get(i);
            }
            List.add(list);
        }
    }
}