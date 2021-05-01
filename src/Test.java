import smile.base.rbf.RBF;
import smile.classification.FLD;
import smile.classification.LDA;
import smile.classification.QDA;
import smile.classification.RBFNetwork;
import smile.data.CategoricalEncoder;
import smile.data.DataFrame;
import smile.data.formula.Formula;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        CSVFile csv = new CSVFile();
        csv.ChooseFile("C:\\Users");
        try {
            csv.GetCSVFileData(csv.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csv.ArrayListDataToArray();

        //LDA lda = new LDA.fit(csv.doubleData,csv.IntLabelMat,null, 1E-4);
        FLD fisher = FLD.fit(csv.doubleData,csv.IntLabelMat,-1,1E-4);
        int[] predict=new int[csv.doubleData.length];
        for(int i=0;i<csv.doubleData.length;i++){
            predict[i]=fisher.predict(csv.doubleData[i]);
            System.out.println(predict[i]);
        }

        System.out.println("RBF");
        RBFNetwork<double[]> rbf = RBFNetwork.fit(csv.doubleData,csv.IntLabelMat, RBF.fit(csv.doubleData, 2));
        for(int i=0;i<csv.doubleData.length;i++){
            predict[i]=rbf.predict(csv.doubleData[i]);
            System.out.println(predict[i]);
        }
    }
}
