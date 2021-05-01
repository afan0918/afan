import fr.ign.cogit.roc4j.core.ReceiverOperatingCharacteristics;
import fr.ign.cogit.roc4j.core.RocCurvesCollection;
import fr.ign.cogit.roc4j.graphics.RocSpace;
import fr.ign.cogit.roc4j.graphics.RocSpaceStyle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Afan Chen
 */
public class ROCcurve {

    private final RocCurvesCollection ROC = new RocCurvesCollection(true);
    RocSpace space = Main.gui.jPanel5;
    RocSpace space2 = Main.gui.jPanel4;

    public ArrayList<Double> CutOffPoints;
    public ArrayList<Double> fScores;
    public ArrayList<Double> auc = new ArrayList<>();
    public ArrayList<Color> colors = new ArrayList<>();
    public double[][] data;
    public String[] DataTitle;
    public int[] more;

    public void CutOffPoint(int[] label,double[][] Data,double minAUC) {
        CutOffPoints = new ArrayList<>();
        fScores = new ArrayList<>();
        int[] feature = new int[Data.length];
        double[] dtmp=new double[Data.length];
        double tmp;
        int itmp;

        double[] proba = new double[Data.length];

        for (int j = 0; j < proba.length; j++) {
            proba[j] = (double) j / (proba.length - 1);
        }

        for (int i = 0; i < Data[0].length; i++) {

            for (int j = 0; j < Data.length; j++) {
                feature[j] = label[j];
                dtmp[j]=Data[j][i];
            }

            for (int j = 0; j < feature.length; j++) {
                for (int k = 1; k < feature.length-j; k++) {
                    if (dtmp[k] < dtmp[k-1]) {
                        tmp = dtmp[k];
                        dtmp[k] = dtmp[k-1];
                        dtmp[k-1] = tmp;
                        itmp = feature[k];
                        feature[k] = feature[k - 1];
                        feature[k - 1] = itmp;
                    }
                }
            }

            Curve curve = new Curve(feature,1);
            double[] FPRAndTPR;//FPR與TPR
            double maxVal = 0;
            int CutOffPoint = 0;

            for (int j = 0; j < label.length; j++) {
                FPRAndTPR = curve.rocPoint(j);
                tmp = FPRAndTPR[0] - FPRAndTPR[1];
                //System.out.println(FPRAndTPR[1]);
                if (Math.abs(tmp) > maxVal) {
                    CutOffPoint = j;
                    maxVal = Math.abs(tmp);
                }
            }

            CutOffPoints.add(CutOffPoint!=0?(dtmp[CutOffPoint]+ dtmp[CutOffPoint - 1]) / 2:dtmp[0]);

            if (curve.rocArea() > 0.5)
                auc.add(curve.rocArea());
            else
                auc.add(1 - curve.rocArea());

            System.out.println(curve.rocArea());

            int[] TP_FP_FN_TN=curve.confusionMatrix(CutOffPoint);
            double recall = (double) TP_FP_FN_TN[0] / (TP_FP_FN_TN[0] + TP_FP_FN_TN[2]), precision = (double) TP_FP_FN_TN[0] / (TP_FP_FN_TN[0] + TP_FP_FN_TN[1]);
            double fscore = 2 * recall * precision / (recall + precision);
            fScores.add(fscore);

            //先把圖畫好
            if(curve.rocArea()>=minAUC||1 - curve.rocArea()>=minAUC) {

                if(1 - curve.rocArea()<=0.5) MathMethod.ComplementReverse(feature);
                ReceiverOperatingCharacteristics roc = new ReceiverOperatingCharacteristics(feature, proba, 1000);

                ROC.add(roc);

                int r = (int) (Math.random() * 240);
                int g = (int) (Math.random() * 240);
                int b = (int) (Math.random() * 240);

                Color color = new Color(r, g, b);
                colors.add(color);

                roc.setColor(color);//隨機給定顏色值
                //roc.setThickness(1.f);//把線條變細
            }
        }

        more = new int[Data[0].length];
        for (int i = 0; i < more.length; i++) {
            if (auc.get(i) >= minAUC) more[i] = 1;
            else more[i] = 0;
        }
    }

    public void FilterData(double[][] Data) {
        int num = Arrays.stream(more).sum();
        int count = 0;
        data = new double[Data.length][num];
        DataTitle=new String[num];
        for (int i = 0; i < more.length; i++) {
            while (i < more.length && more[i] == 0) i++;
            if (i >= more.length) break;
            for (int j = 0; j < Data.length; j++) {
                if (Data[j][i] >= CutOffPoints.get(i)) data[j][count] = 1;
                else data[j][count] = 0;
            }
            DataTitle[count]=DataBase.csvFile.DataTitle[i+1];
            count++;
        }
    }

    /**
     * 將各項數據的標題和AUC
     * 以跟畫ROC曲線相同的顏色
     * 寫入圖中來當圖例
     * 缺點:資料太多會爆，我先只顯示前六筆
     *
     * @param DataTitle This is the title of data.
     */

    public void writeAUC(String[] DataTitle) {
        for (int i = 1; i < DataTitle.length && i < 6; i++) {
            space.writeText(DataTitle[i], 420, 490 + 20 * i, 12, colors.get(i - 1));
        }
    }

    /**
     * 設定標題與一些圖形要素
     * @return 畫好的panel
     */

    public void drawROC() {
        space.setStyle(RocSpaceStyle.STYLE_PLAIN);
        space.addRocCurve(ROC);
        space2.setStyle(RocSpaceStyle.STYLE_PLAIN);
        space2.addRocCurve(ROC);
    }

//    /**
//     * 依照cut-off point
//     * 將數據分為兩類並存好
//     *
//     * @param Data The training data.
//     */
//
//    public void CutPoint(double[][] Data,double minAUC) {//只能畫一條線
//        int count=0;//計數器，統計auc值大於minAUC的個數
//        int c=0;
//        more = new int[Data.length];
//
//        for(int i=0;i<Data[0].length;i++){
//            if(auc.get(i)>=minAUC){
//                count++;
//                more[i]=1;
//            }else{
//                more[i]=0;
//            }
//        }
//
//        /**
//         * 記得要寫好資料分類
//         */
//
//        data = new double[Data.length][count];
//        for (int j = 0; j < Data[0].length; j++) {
//            if(more[j]==1) continue;
//            for (int i = 0; i < Data.length; i++) {
//                if(Data[i][j] < CutOffPoints.get(j)) {
//                    data[i][c] = 0;
//                }else{
//                    data[i][c] = 1;
//                }
//            }
//            c++;
//        }
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[0].length; j++) {
//                System.out.println(data[i][j]);
//            }
//        }
//    }
    
    /**
     * 將會製成的ROC圖表存檔至指定位置
     *
     * @param Path 存檔檔案路徑
     */

    public void SavepngFile(String Path) {
        space2.save(Path, RocSpace.FORMAT_PNG);
    }
}