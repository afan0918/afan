import fr.ign.cogit.roc4j.core.ReceiverOperatingCharacteristics;
import smile.classification.Classifier;
import smile.classification.SVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Afan Chen
 */
public class SVMMethod {

    private final ArrayList<double[]> Weight = new ArrayList<>();
    private final ArrayList<Double> bias = new ArrayList<>();
    private final ArrayList<Double> FScore = new ArrayList<>();
    private final ArrayList<Double> Sensitivity = new ArrayList<>();
    private final ArrayList<Double> Specificity = new ArrayList<>();
    private final ArrayList<int[]> Combinations = new ArrayList<>();

//    private final ArrayList<Double> AUCs = new ArrayList<>();

    void RunAllPermutationsSVM(double[][] Data, int[] label, double C, double tol) {
        for (int i = 2; i <= Data[0].length; i++) {
            List<int[]> combination = MathMethod.Combination(Data[0].length, i);
            double[][] data = new double[Data.length][i];
            for (int[] ints : combination) {
                for (int k = 0; k < ints.length; k++) {
                    for (int kk = 0; kk < Data.length; kk++) {
                        data[kk][k] = Data[kk][ints[k]];
                    }
                }
                svm(data, label, C, tol);
                Combinations.add(ints);
            }
        }
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

    void svm(double[][] data, int[] label, double C, double tol) {
        int[] predict = new int[data.length];
        double[] weight;
        double b;

        /**
         * 把有無得病的表示權重改成1,-1
         */

        for (int i = 0; i < label.length; i++) {
            if (label[i] == 0) label[i] = -1;
        }

        Classifier<double[]> svm = SVM.fit(data, label, C, tol);

        for (int i = 0; i < data.length; i++)
            predict[i] = svm.predict(data[i]);

        /**
         * 算完之後就把-1的權重改回零，正規化資訊
         */

        for (int i = 0; i < data.length; i++) {
            if (predict[i] == -1) {
                predict[i]++;
            }
        }

        for (int i = 0; i < label.length; i++) {
            if (label[i] == -1) label[i] = 0;
        }

        weight = svm.w();
        b = svm.b();

        int tp = 0, fp = 0, fn = 0, tn = 0;
        for (int i = 0; i < predict.length; i++) {
            if (label[i] == 1) {
                if (predict[i] == 1) tp++;
                else if (predict[i] == 0) fn++;
            } else if (label[i] == 0) {
                if (predict[i] == 1) fp++;
                else tn++;
            }
        }

        double recall = (double) tp / (tp + fn), precision = (double) tp / (tp + fp);
        double specificity = (double) tn / (tn + fp);
        double fscore = 2 * recall * precision / (recall + precision);

        Sensitivity.add(recall);
        Specificity.add(specificity);
        FScore.add(fscore);
        Weight.add(weight);
        bias.add(b);

        /**
         * 幫學長多求一個AUC值專區
         */

//        double[] tmp = new double[data.length];
//
//        for (int i = 0; i < data.length; i++) {
//            tmp[i] = MathMethod.dot(data[i], weight) + b;
//        }
//
//        double max_val = -1000000000;
//        double min_val = 1000000000;
//
//        for (double v : tmp) {
//            max_val = Math.max(max_val, v);
//            min_val = Math.min(min_val, v);
//        }
//
//        double len = max_val - min_val;
//        double offset = min_val / len;
//
//        System.out.println(len);
//        System.out.println(offset);
//
//        for (int i = 0; i < tmp.length; i++) {
//            tmp[i] /= len;
//            tmp[i] -= offset;
//        }
//
//        for (double v : tmp) {
//            System.out.println(v);
//        }

    }

    public ArrayList<double[]> getWeight() {
        return Weight;
    }

    public ArrayList<Double> getBias() {
        return bias;
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

//    public ArrayList<Double> getAUCs() {
//        return AUCs;
//    }
}