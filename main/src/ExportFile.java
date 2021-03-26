import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ExportFile {

    private String path="C:\\Users\\Documents";

    /**
     * 選擇儲存檔案路徑
     * @param Path 初始選擇視窗位置
     */

    public String ChoosePath(String Path) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }

    /**
     * 將訓練出的邏輯回歸模型進行存檔動作
     * @param Path 存檔目的地路徑
     */

    public void SaveLRModel(String Path, ArrayList<Double> CutOffPoints, double LRFScore,double[] LRWeight){
        BufferedWriter fw = null;
        try {
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Path, true), StandardCharsets.UTF_8)); // 指定編碼格式，以免中文字符異常

            for(String x:GUI.csv.DataTitle){
                fw.append(x).append(",");
            }

            fw.newLine();
            fw.newLine();

            fw.append("ROC cut-off point,");

            for(int i=0;i<CutOffPoints.size();i++){
                fw.append(String.valueOf(CutOffPoints.get(i))).append(",");
            }

            fw.newLine();
            fw.newLine();

            fw.append("Best fscore,").append(String.valueOf(LRFScore));

            fw.newLine();
            fw.newLine();

            fw.append("The best wight is,");
            for(int i=0;i<LRWeight.length-1;i++){
                fw.append(String.valueOf(LRWeight[i])).append(",");
            }
            fw.newLine();

            fw.flush(); // 全部寫入緩存中的內容
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 將訓練出的SVM模型進行存檔動作
     * @param Path 存檔目的地路徑
     */

    public void SaveSVMModel(String Path, ArrayList<Double> CutOffPoints, double SVMFScore,double[] SVMWeight,double b){
        BufferedWriter fw = null;
        try {
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Path, true), StandardCharsets.UTF_8)); // 指定編碼格式，以免中文字符異常

            for(String x:GUI.csv.DataTitle){
                fw.append(x).append(",");
            }

            fw.newLine();
            fw.newLine();

            fw.append("ROC cut-off point,");

            for(int i=0;i<CutOffPoints.size();i++){
                fw.append(String.valueOf(CutOffPoints.get(i))).append(",");
            }

            fw.newLine();
            fw.newLine();

            fw.append("Best fscore,").append(String.valueOf(SVMFScore));

            fw.newLine();
            fw.newLine();

            fw.append("The best wight is,");
            for(int i=0;i<SVMWeight.length;i++){
                fw.append(String.valueOf(SVMWeight[i])).append(",");
            }
            fw.newLine();

            fw.flush(); // 全部寫入緩存中的內容
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void SavepngFile(String Path,ROCcurve ROC){
        ROC.SavepngFile(Path);
    }
}
