import smile.classification.Classifier;
import smile.classification.LogisticRegression;
import smile.classification.SVM;

public class main {

    public static CSVFile csv = new CSVFile();

    public static void main(String[] args) {

        csv=new CSVFile();
        csv.ChooseFile("C:\\user\\");//得到檔案位置

        csv.GetCSVFileData();//得到檔案中的資料並把標題存到DataTitle，數據存到data

        csv.ArrayListDataToDouble();

        for(int i=0;i<csv.doubleData.length;i++) {
            System.out.println(csv.data.get(i));
        }

        for(int i=0;i<csv.IntLabelMat.length;i++) {
            System.out.println(csv.IntLabelMat[i]);
        }

        int[] predict=new int[csv.doubleData.length];

        /**
         *Logstic Regression測試
         */

        System.out.println("LR");

        LogisticRegression.Binomial binomial=LogisticRegression.binomial(csv.doubleData,csv.IntLabelMat,0.1,1e-5,10000);

        double[] weight=binomial.coefficients();

        for (double v : weight) {
            System.out.println(v);
        }

        for(int i=0;i<csv.doubleData.length;i++)
            predict[i] = binomial.predict(csv.doubleData[i]);

        for(int i=0;i<csv.doubleData.length;i++)
            System.out.println(predict[i]);

        /**
         * 線性SVM測試
         */

        System.out.println("SVM");

        int[] SVMLabel = csv.IntLabelMat;

        for(int i=0;i<SVMLabel.length;i++){
            if(SVMLabel[i]==0) SVMLabel[i]--;
        }

        Classifier<double[]> svm = SVM.fit(csv.doubleData,SVMLabel,1,1e-8);

        for(int i=0;i<csv.doubleData.length;i++)
            predict[i] = svm.predict(csv.doubleData[i]);

        for(int i=0;i<csv.doubleData.length;i++)
            if(predict[i]==-1)
                predict[i]++;

        for(int i=0;i<csv.doubleData.length;i++)
            System.out.println(predict[i]);
    }
}