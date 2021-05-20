package afan.unit.database;

import afan.unit.io.File;
import afan.unit.machineLearning.LogisticRegressionMethod;
import afan.unit.machineLearning.SVMMethod;
import afan.unit.plot.ROCCurve;

/**
 * @author Afan Chen
 * Store some static data
 */
public class DataBase {
    public static double minAUC = 0.5;
    public static File file = null;
    public static int algorithm = 1;
    public static ROCCurve ROC = null;
    public static LogisticRegressionMethod logisticRegressionMethod = null;
    public static SVMMethod svmMethod = null;
}

