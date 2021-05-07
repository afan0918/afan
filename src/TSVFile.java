import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * bug原因，title沒有改動導致匯出錯誤
 * @author Afan Chen
 */
public class TSVFile {

    public String path = "C:\\Users";
    public String[] allTitle;
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
        for (String x : DataTitle) System.out.println(x);

        while ((line = reader.readLine()) != null) {
            String[] item = line.split("\t");
            ArrayList<Double> tmp = new ArrayList<>();
            sampleName.add(item[0]);
            labelMat.add(item[1].compareTo("H") == 0 ? 0 : 1);//h是0,a是1
            for (int i = 2; i < item.length; i++)
                tmp.add(item[i].compareTo("NA") == 0 ?
                        Double.NaN : Double.parseDouble(item[i]));
            data.add(tmp);
        }

        isr.close();
    }

    /**
     * 處裡NA值
     * 將data的型態轉成array方便之後取用
     */

    public void ArrayListDataToArray() {

        int n = data.size(), m = 0;
        boolean[] badData = new boolean[data.get(0).size()];

        for (int i = 0; i < data.get(0).size(); i++) {
            int sum = 0;
            for (ArrayList<Double> datum : data) {
                if (datum.get(i).isNaN()) sum++;
            }
            badData[i] = sum * 5 < n;
            if (badData[i]) ++m;
        }

        doubleData = new double[data.size()][m];
        IntLabelMat = new int[labelMat.size()];
        int c = 0;
        String[] title=new String[m+1];

        for (int i = 0; i < data.get(0).size(); ++i) {
            if (!badData[i]) continue;
            for (int j = 0; j < data.size(); j++) {
                doubleData[j][c] = data.get(j).get(i).isNaN() ?
                        1 : data.get(j).get(i);
            }
            c++;
        }

        c=0;

        for (int i = 2; i < DataTitle.length; ++i) {
            if (!badData[i-2]) continue;
            title[++c]=DataTitle[i];
        }

        allTitle=DataTitle;
        DataTitle=title;

        for (int i = 0; i < labelMat.size(); i++) {
            IntLabelMat[i] = labelMat.get(i);
        }
    }
}
