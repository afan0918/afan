import fr.ign.cogit.roc4j.core.ReceiverOperatingCharacteristics;
import fr.ign.cogit.roc4j.graphics.OperatingPoint;
import fr.ign.cogit.roc4j.optimization.Context;
import fr.ign.cogit.roc4j.optimization.IsoCostLine;
import fr.ign.cogit.roc4j.utils.Tools;
import smile.classification.LogisticRegression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Afan Chen
 */
public class LogisticRegressionMethod {

    private final ArrayList<double[]> Weight = new ArrayList<>();
    private final ArrayList<Double> FScore = new ArrayList<>();
    private final ArrayList<Double> Sensitivity = new ArrayList<>();
    private final ArrayList<Double> Specificity = new ArrayList<>();
    private final ArrayList<int[]> Combinations = new ArrayList<>();

    private final ArrayList<Double> AUCs = new ArrayList<>();

    /**
     * 把所有的組合都以Logistic Regression嘗試一遍，跑出結果並儲存
     *
     * @param Data      data to training.
     * @param label     label of data.
     * @param MaxIter   最大迭代次數
     *                  maximum number of iterations
     * @param Lambda    λ (盡量把值控制在趨近零)
     *                  Tune the overall impact of the regularization term
     *                  by multiplying its value.
     *                  Encourages weight values toward 0 (but not exactly 0)
     *                  Encourages the mean of the weights toward 0, with a normal (bell-shaped or Gaussian) distribution.
     * @param Tolerance margin of tolerance
     *                  To stop searching for a minimum (or maximum)
     *                  once some tolerance is achieved, i.e. once you're close enough.
     */

    public void RunAllPermutationsLogisticRegression(double[][] Data, int[] label, int MaxIter, double Lambda, double Tolerance) {
        for (int i = 2; i <= Data[0].length; i++) {
            List<int[]> combination = MathMethod.Combination(Data[0].length, i);
            double[][] data = new double[Data.length][i];
            for (int[] ints : combination) {
                for (int k = 0; k < ints.length; k++) {
                    for (int kk = 0; kk < Data.length; kk++) {
                        data[kk][k] = Data[kk][ints[k]];
                    }
                }
                lr(data, label, MaxIter, Lambda, Tolerance);
                Combinations.add(ints);
            }
        }
    }

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

    public void lr(double[][] data, int[] label, int MaxIter, double Lambda, double Tolerance) {

        int[] predict = new int[data.length];
        double[] weight;

        LogisticRegression.Binomial binomial = LogisticRegression.Binomial.binomial(data, label, Lambda, Tolerance, MaxIter);

        for (int i = 0; i < data.length; i++)
            predict[i] = binomial.predict(data[i]);


        int tp = 0, fp = 0, fn = 0, tn = 0;
        for (int i = 0; i < predict.length; i++) {
            if (label[i] == 1) {
                if (predict[i] == 1) tp++;
                else if (predict[i] == 0) fn++;
            } else if (label[i] == 0) {
                if (predict[i] == 1) fp++;
                else if (predict[i] == 0) tn++;
            }
        }

        double recall = (double) tp / (tp + fn), precision = (double) tp / (tp + fp);
        double specificity = (double) tn / (tn + fp);
        double fscore = 2 * recall * precision / (recall + precision);

        weight = binomial.coefficients();
        Sensitivity.add(recall);
        Specificity.add(specificity);
        FScore.add(fscore);
        Weight.add(weight);

        /**
         * 幫學長多求一個AUC值專區
         */

        double[] tmp = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            tmp[i] = MathMethod.dot(data[i], weight)+weight[weight.length-1];
        }

        double max_val = -1000000000;
        double min_val = 1000000000;

        for (double v : tmp) {
            max_val = Math.max(max_val, v);
            min_val = Math.min(min_val, v);
        }

        double len = max_val - min_val;
        double offset = min_val / len;

        System.out.println(len);
        System.out.println(offset);

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] /= len;
            tmp[i] -= offset;
        }

        for (double v : tmp) {
            System.out.println(v);
        }

        ReceiverOperatingCharacteristics roc = new ReceiverOperatingCharacteristics(label, tmp);
        AUCs.add(roc.computeAUC());

//        實驗室學長要機器學習後的cutoffpoint的歷史遺留，但我覺得有點沒有必要
//        機器學習就是先找出一條公式來分類阿，到底在幹嘛啦，求偏移量逆
//        Context context = new Context(1, 1, 0.5);
//        IsoCostLine line = new IsoCostLine(context);
//        line.setIntercept(0.5);
//        OperatingPoint point = line.optimize(roc);
//        double threshold = Tools.round(point.getThreshold(), (int) (Math.log(tmp.length)+3));
    }

    public ArrayList<double[]> getWeight() {
        return Weight;
    }

    public ArrayList<Double> getFScore() {
        return FScore;
    }

    public ArrayList<Double> getSensitivity() {
        return Sensitivity;
    }

    public ArrayList<Double> getSpecificity() {
        return Specificity;
    }

    public ArrayList<int[]> getCombinations() {
        return Combinations;
    }

    public ArrayList<Double> getAUCs() {
        return AUCs;
    }
}
