import fr.ign.cogit.roc4j.core.ReceiverOperatingCharacteristics;
import fr.ign.cogit.roc4j.core.RocCurvesCollection;
import fr.ign.cogit.roc4j.graphics.RocSpace;
import fr.ign.cogit.roc4j.graphics.RocSpaceStyle;
import smile.classification.Classifier;
import smile.classification.LogisticRegression;
import smile.classification.SVM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI extends NetBeansGUI{

    public static CSVFile csv;
    public static ROCcurve ROC;

    /**
     * 當檔案選擇按鈕按下時，執行。
     */

    protected void jButton1ActionPerformed(ActionEvent evt) {

        csv=new CSVFile();
        csv.ChooseFile("C:\\Users\\Document");//得到檔案位置
        jTextField1.setText(csv.path);

        csv.GetCSVFileData();//取得csv中資料
        csv.ArrayListDataToDouble();

        ROC=new ROCcurve();
        double[] colData = new double[csv.data.size()];
        int[] labelMat = new int[csv.labelMat.size()];

        for (int i = 0; i < csv.data.get(0).size(); i++) {
            for (int j = 0; j < labelMat.length; j++)
                labelMat[j] = csv.labelMat.get(j);
            for (int j = 0; j < colData.length; j++)
                colData[j] = csv.data.get(j).get(i);

            ROC.ROC(labelMat, colData);
        }

        ROC.writeAUC(csv.DataTitle);
        ROC.CutPoint(csv.doubleData);
    }

    /**
     * 確認哪些CheckBox已被勾選，並將資料回傳
     */

    private ArrayList<Boolean> is_home_choose(){

        ArrayList<Boolean> result=new ArrayList<>();

        result.add(jCheckBox6.isSelected());
        result.add(jCheckBox1.isSelected());
        result.add(jCheckBox2.isSelected());
        result.add(jCheckBox3.isSelected());

        return result;
    }

    /**
     * home主頁當Run按鈕被按下時，執行
     * 就只是
     */

    protected void jButton2ActionPerformed(ActionEvent evt) {
        ArrayList<Boolean> Choose=is_home_choose();
        if(Choose.get(0)) {
            ROC.drawROC("");//把原始數據的roc圖畫出來
        }
        if(Choose.get(1)){
            lr();
        }
        if(Choose.get(3)){
            svm();
        }
    }

    /**
     * Logistic Regression頁面內容
     */

    /**
     * 確認哪些CheckBox已被勾選，並將資料回傳
     */

    private ArrayList<Boolean> is_LR_choose(){
        ArrayList<Boolean> result=new ArrayList<>();

        result.add(jCheckBox4.isSelected());
        result.add(jCheckBox5.isSelected());

        return result;
    }

    /**
     * Logistic Regression頁面的run按鈕被點擊
     */
    public double LRFScore;//我爛先直接拉出來方便資料匯出用，之後再做UNIT TEST
    public double[] LRWeight;
    protected void jButton3ActionPerformed(ActionEvent evt) {
        lr();
    }

    private void lr(){
        ArrayList<Boolean> Choose=is_LR_choose();
        int[] predict=new int[ROC.data.length];
        double[] weight;

        /**
         * lambda-> λ (盡量把值控制在趨近零)
         * Tune the overall impact of the regularization term
         * by multiplying its value.
         * Encourages weight values toward 0 (but not exactly 0)
         * Encourages the mean of the weights toward 0, with a normal (bell-shaped or Gaussian) distribution.
         * tol->margin of tolerance
         * To stop searching for a minimum (or maximum)
         * once some tolerance is achieved, i.e. once you're close enough.
         * 越大，表示在容忍範圍內的誤差/分錯的資料，不會被Loss function懲罰
         * 反之，越接近0，每一個誤差/分錯的資料都會被懲罰。
         * MaxIter->最大迭代次數
         * maximum number of iterations
         */

        LogisticRegression.Binomial binomial = LogisticRegression.binomial(ROC.data,csv.IntLabelMat,Double.parseDouble(jTextField7.getText()),Double.parseDouble(jTextField8.getText()),Integer.parseInt(jTextField2.getText()));

        for(int i=0;i<csv.doubleData.length;i++)
            predict[i] = binomial.predict(ROC.data[i]);

        for(int i=0;i<csv.doubleData.length;i++)
            System.out.println(predict[i]);

        Curve curve=new Curve(predict,1);

        int tp=0,fp=0,fn=0;
        for(int i=0;i<predict.length;i++){
            if(csv.IntLabelMat[i]==1){
                if(predict[i]==1) tp++;
                else if(predict[i]==0) fn++;
            }else if(csv.IntLabelMat[i]==0){
                if(predict[i]==1) fp++;
            }
        }

        double recall=(double) tp/(tp+fn), precision=(double) tp/(tp+fp);
        double FScore=2*recall*precision/(recall+precision);

        weight=binomial.coefficients();
        LRFScore=FScore;
        LRWeight=weight;

        if(Choose.get(0)){//ROC curve選項是否遭勾選

            /**
             * 畫出來很醜，我之後改一下讓他數據有差別不要是0和1
             */
            double[] tmp=new double[predict.length];//將預測結果轉換一下型別
            for(int i=0;i<predict.length;i++){
                tmp[i]=predict[i];
            }

            RocCurvesCollection ROC = new RocCurvesCollection(true);
            ReceiverOperatingCharacteristics roc = new ReceiverOperatingCharacteristics(csv.IntLabelMat, tmp, 1000);
            ROC.add(roc);

            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);

            Color color = new Color(r, g, b);

            roc.setColor(color);//隨機給定顏色值

            RocSpace space = new RocSpace();

            space.setStyle(RocSpaceStyle.STYLE_PLAIN);
            space.addRocCurve(ROC);

            space.setTitle("Logistic Regression");
            space.setXLabel("FPR axis");
            space.setYLabel("TPR axis");

            JFrame fen = new JFrame();
            fen.setSize(700, 700);
            fen.setContentPane(space);
            fen.setLocationRelativeTo(null);
            fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fen.setVisible(true);
        }

        if(Choose.get(1)){//The best model選項是否遭勾選
            jTextArea1.setText("The best model is ");//初始化
            for (int i=0;i<weight.length-2;i++) {
                jTextArea1.append(weight[i]+" "+csv.DataTitle[i+1]+" + ");
            }
            jTextArea1.append(weight[weight.length-2]+" "+csv.DataTitle[weight.length-1]);//最後一項
            jTextArea1.append("\n"+"F-Score = "+FScore);
        }
    }

    private ArrayList<Boolean> is_SVM_choose(){

        ArrayList<Boolean> result=new ArrayList<>();

        result.add(jCheckBox9.isSelected());

        return result;
    }

    private double SVMFScore;
    private double[] SVMWeight;
    private double SVMb;
    protected void jButton4ActionPerformed(ActionEvent evt) {
        svm();
    }

    private void svm(){
        ArrayList<Boolean> Choose=is_SVM_choose();
        int[] predict=new int[ROC.data.length];
        double[] weight;
        double b;

        int[] SVMLabel = csv.IntLabelMat;

        /**
         * 把有無得病的表示權重改成1,-1
         */

        for(int i=0;i<SVMLabel.length;i++){
            if(SVMLabel[i]==0) SVMLabel[i]--;
        }

        /**
         * 跑得其實不是SVM，而是能夠在大量數據中快速運算且精確度相似的LASVM
         * c->cost，可以當成容錯項，值越大榮挫越少
         * 須注意c值太大會 OverFitting
         * c越小蕙有越多support vectors
         * tol->margin of tolerance
         * To stop searching for a minimum (or maximum)
         * once some tolerance is achieved, i.e. once you're close enough.
         * 越大，表示在容忍範圍內的誤差/分錯的資料，不會被懲罰
         * 反之，越接近0，每一個誤差/分錯的資料都會被懲罰。
         */

        Classifier<double[]> svm = SVM.fit(ROC.data,SVMLabel,Double.parseDouble(jTextField4.getText()),Double.parseDouble(jTextField5.getText()));

        for(int i=0;i<ROC.data.length;i++)
            predict[i] = svm.predict(ROC.data[i]);

        /**
         * 算完之後就把-1的權重改回零，正規化資訊
         */

        for(int i=0;i<ROC.data.length;i++) {
            if (predict[i] == -1) {
                predict[i]++;
            }
        }

        for(int i=0;i<SVMLabel.length;i++){
            if(SVMLabel[i]==-1) SVMLabel[i]++;
        }

        weight=svm.w();
        b=svm.b();

        int tp=0,fp=0,fn=0;
        for(int i=0;i<predict.length;i++){
            if(csv.IntLabelMat[i]==1){
                if(predict[i]==1) tp++;
                else if(predict[i]==0) fn++;
            }else if(csv.IntLabelMat[i]==0){
                if(predict[i]==1) fp++;
            }
        }

        double recall=(double) tp/(tp+fn), precision=(double) tp/(tp+fp);
        double FScore=2*recall*precision/(recall+precision);

        if(Choose.get(0)){//The best model選項是否遭勾選

            //for (double v : weight) System.out.println(v);

            jTextArea2.setText("The best model is ");//初始化
            for (int i=0;i<weight.length-1;i++) {
                jTextArea2.append(weight[i]+" "+csv.DataTitle[i+1]+" + ");
            }
            jTextArea2.append(weight[weight.length-1]+" "+csv.DataTitle[weight.length]);//最後一項
            jTextArea2.append("\n"+"F-Score = "+FScore);
        }

        SVMFScore=FScore;
        SVMWeight=weight;
        SVMb=b;
    }

    ExportFile exportFile;

    protected void jButton5ActionPerformed(ActionEvent evt) {
        exportFile=new ExportFile();
        jTextField6.setText(exportFile.ChoosePath(jTextField6.getText()));
    }

    private ArrayList<Boolean> is_export_choose(){

        ArrayList<Boolean> result=new ArrayList<>();

        result.add(jCheckBox7.isSelected());
        result.add(jCheckBox10.isSelected());
        result.add(jCheckBox8.isSelected());

        return result;

    }

    /**
     * 使用者點選匯出檔案按鈕時，執行
     */

    protected void jButton6ActionPerformed(ActionEvent evt) {
        ArrayList<Boolean> Choose=is_export_choose();
        String path=jTextField6.getText();

        if(Choose.get(0)){
            exportFile.SaveLRModel(path+"\\LogisticRegression.csv",ROC.CutOffPoints,LRFScore,LRWeight);
        }
        if(Choose.get(0)){
            exportFile.SaveSVMModel(path+"\\SupportVectorMachine.csv",ROC.CutOffPoints,SVMFScore,SVMWeight,SVMb);
        }
        if(Choose.get(2)){//roc存檔
            exportFile.SavepngFile(path,ROC);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        EventQueue.invokeLater(() -> new GUI().setVisible(true));
    }
}
