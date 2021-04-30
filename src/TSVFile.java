import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Afan Chen
 */
public class TSVFile {

    public String path = "C:\\Users";
    public String[] DataTitle;
    private final ArrayList<String> sampleName = new ArrayList<>();
    private final ArrayList<Integer> labelMat = new ArrayList<>();
    private final ArrayList<ArrayList<Double>> data = new ArrayList<>();
    public double[][] doubleData;
    public int[] IntLabelMat;

    /**
     * 選擇檔案路徑
     */

    public void ChooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".tsv file", "tsv");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    /**
     * 將已正規化的資料分區塊存入DataTitle、labelMat、data當中
     */

    public void GetTSVFileData(String Path) throws IOException {//將資料讀入，並轉成想要的資料格式

        InputStreamReader isr;//待處理資料的檔案路徑

        isr = new InputStreamReader(new FileInputStream(Path));

        BufferedReader reader = new BufferedReader(isr);
        String line;

        if ((line = reader.readLine()) == null) return;

        DataTitle = line.split("\t");
        for(String x:DataTitle) System.out.println(x);

        while ((line = reader.readLine()) != null) {
            String[] item = line.split("\t");
            ArrayList<Double> tmp = new ArrayList<>();
            sampleName.add(item[0]);
            labelMat.add(item[1].compareTo("H")==0?1:0);//H是1，A是0
            for (int i = 2; i < item.length; i++)
                tmp.add(item[i].compareTo("NA")==0?1:Double.parseDouble(item[i]));
            data.add(tmp);
        }

        isr.close();
    }

    /**
     * 將data的型態轉成array方便之後取用
     */

    public void ArrayListDataToArray() {

        doubleData = new double[data.size()][data.get(0).size()];
        IntLabelMat = new int[labelMat.size()];

        for (int i = 0; i < doubleData.length; i++) {
            for (int j = 0; j < doubleData[0].length; j++) {
                doubleData[i][j] = data.get(i).get(j);
//                System.out.println(doubleData[i][j]);
            }
        }

        for (int i = 0; i < labelMat.size(); i++) {
            IntLabelMat[i] = labelMat.get(i);
//            System.out.println(IntLabelMat[i]);
        }
    }
}
