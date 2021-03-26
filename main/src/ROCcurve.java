import fr.ign.cogit.roc4j.core.ReceiverOperatingCharacteristics;
import fr.ign.cogit.roc4j.core.RocCurvesCollection;
import fr.ign.cogit.roc4j.graphics.RocSpace;
import fr.ign.cogit.roc4j.graphics.RocSpaceStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ROCcurve {

    private final RocCurvesCollection ROC = new RocCurvesCollection(true);
    RocSpace space = new RocSpace();

    public ArrayList<Double> CutOffPoints = new ArrayList<>();
    public ArrayList<Double> auc = new ArrayList<>();
    public ArrayList<Color> colors = new ArrayList<>();
    public double[][] data;

    /**
     * 做一些ROC圖表的初步計算
     * 將傳入的單項數據的Cut-off point
     * 以及AUC(Area under the ROC Curve)
     * 存入class
     * 圖形存入space當中以利後續畫圖
     *
     * @param expected  Label.
     * @param probaData Data.
     */

    public void ROC(int[] expected, double[] probaData) {

        /**
         * 計算CUT-OFF點
         */

        double tmp;
        int itmp;
        for (int j = 0; j < probaData.length; j++) {
            for (int k = 1; k < probaData.length-j; k++) {
                if (probaData[k] < probaData[k - 1]) {
                    tmp = probaData[k];
                    probaData[k] = probaData[k - 1];
                    probaData[k - 1] = tmp;
                    itmp = expected[k];
                    expected[k] = expected[k - 1];
                    expected[k - 1] = itmp;
                }
            }
        }

        Curve curve = new Curve(expected, 1);
        double[] FPRAndTPR;//FPR與TPR
        double maxVal = 0;
        int CutOffPoint = 0;

        for (int i = 0; i < expected.length; i++) {
            FPRAndTPR = curve.rocPoint(i);
            tmp = FPRAndTPR[0]-FPRAndTPR[1];
            if (Math.abs(tmp) > maxVal) {
                CutOffPoint = i;
                maxVal = Math.abs(tmp);
            }
        }

        auc.add(curve.prArea());//用求梯形的方式積出auc

        /**
         *ROC圖表繪製
         */

        double[] proba = new double[probaData.length];

        for (int j = 0; j < proba.length; j++) {
            proba[j] = (double) j / (proba.length - 1);
        }

        ReceiverOperatingCharacteristics roc = new ReceiverOperatingCharacteristics(expected, proba, 1000);

        /**
         * 算AUC的部分
         */
//        AreaUnderCurve auc = new AreaUnderCurve(roc);
//        boolean flag=false;
//        if(Tools.round(100 * auc.getAreaValue(), 2)<50){
//            flag=true;
//            for (int j = 0; j < proba.length; j++)
//                proba[j] = (double) (proba.length - 1-j) / (proba.length - 1);
//            roc = new ReceiverOperatingCharacteristics(expected, proba, 1000);
//            auc = new AreaUnderCurve(roc);
//        }
//
//        String output = DataTitle + " , AUC = ";
//        output += Tools.round(100 * auc.getAreaValue(), 2) + " %";
//        System.out.println(output);
        ROC.add(roc);

        int r = (int) (Math.random() * 200);
        int g = (int) (Math.random() * 200);
        int b = (int) (Math.random() * 200);

        Color color = new Color(r, g, b);
        colors.add(color);

        roc.setColor(color);//隨機給定顏色值
        //roc.setThickness(1.f);//把線條變細

        /**
         * 取漸近的部分
         */
//        Context context = new Context(ftr, tpr, 0, 0, 0.5, 0.5);
//        IsoCostLine line = new IsoCostLine(context);
//        line.setIntercept(0.5);
//        OperatingPoint point = line.optimize(ROC.get(ROC.size() - 1));
//        double threshold = Tools.round(point.getThreshold(), (int)Math.log(proba.length)+3);

//        if(flag) {//有被翻轉
//            //System.out.println((threshold * (probaData.length - 1)));
//            if (threshold * (probaData.length - 1)-(int)(threshold * (probaData.length - 1))<0.5) {
//                //System.out.println((probaData[(probaData.length - 1) - (int) (threshold * (probaData.length - 1) + 1.5)] + probaData[(probaData.length - 1) - (int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//                CutPoints.add((probaData[(probaData.length - 1) - (int) (threshold * (probaData.length - 1) + 1.5)] + probaData[(probaData.length - 1) - (int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//            }else{
//                //System.out.println((probaData[(probaData.length) - (int) (threshold * (probaData.length - 1) - 0.5)] + probaData[(probaData.length) - (int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//                CutPoints.add((probaData[(probaData.length) - (int) (threshold * (probaData.length - 1) - 0.5)] + probaData[(probaData.length) - (int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//            }
//        }else{
//            //System.out.println((probaData[(int) (threshold * (probaData.length - 1) + 1.5)] + probaData[(int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//            CutPoints.add((probaData[(int) (threshold * (probaData.length - 1) + 1.5)] + probaData[(int) (threshold * (probaData.length - 1) + 0.5)]) / 2);
//        }
//        Flag.add(flag);
        System.out.println(CutOffPoint);
        System.out.println((probaData[CutOffPoint] + probaData[CutOffPoint - 1]) / 2);
        CutOffPoints.add((probaData[CutOffPoint] + probaData[CutOffPoint - 1]) / 2);
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
            space.writeText(DataTitle[i], 420, 490 + 20 * i, 16, colors.get(i - 1));
        }
    }

    /**
     * 設定標題與一些圖形要素
     *
     * @param title 圖片最上面的大標題
     */

    public void drawROC(String title) {

        space.setStyle(RocSpaceStyle.STYLE_PLAIN);
        space.addRocCurve(ROC);

        space.setTitle(title);
        space.setXLabel("Customized FPR axis");
        space.setYLabel("Customized TPR axis");

        JFrame fen = new JFrame();
        fen.setSize(700, 700);
        fen.setContentPane(space);
        fen.setLocationRelativeTo(null);
        fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fen.setVisible(true);
    }

    /**
     * 依照cut-off point
     * 將數據分為兩類並存好
     *
     * @param Data The training data.
     */

    public void CutPoint(double[][] Data) {//只能畫一條線
        data = new double[Data.length][Data[0].length];
        for (int i = 0; i < Data.length; i++) {
            for (int j = 0; j < Data[0].length; j++) {
                if(Data[i][j] < CutOffPoints.get(j)) {
                    data[i][j] = 0;
                }else{
                    data[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < Data.length; i++) {
            for (int j = 0; j < Data[0].length; j++) {
                System.out.println(data[i][j]);
            }
        }
    }

    /**
     * 將會製成的ROC圖表存檔至指定位置
     *
     * @param Path 存檔檔案路徑
     */

    public void SavepngFile(String Path) {
        Path += "\\ROCcurve.png";
        space.save(Path, RocSpace.FORMAT_PNG);
    }
}