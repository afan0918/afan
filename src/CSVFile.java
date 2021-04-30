import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Afan Chen
 */
public class CSVFile {

    public String path="C:\\Users";
    public String[] DataTitle;
    private ArrayList<Integer> labelMat=new ArrayList<>();
    private ArrayList<ArrayList<Double>> data=new ArrayList<>();
    public double[][] doubleData;
    public int[] IntLabelMat;

    /**
     * 選擇檔案路徑
     * @param Path 一開始的選擇視窗位置
     */

    public void ChooseFile(String Path) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv file", "csv");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    /**
     * 將已正規化的資料分區塊存入DataTitle、labelMat、data當中
     */

    public void GetCSVFileData(String Path) throws IOException {//將資料讀入，並轉成想要的資料格式

        InputStreamReader isr = null;//待處理資料的檔案路徑

        isr = new InputStreamReader(new FileInputStream(Path));

        assert isr != null;
        BufferedReader reader = new BufferedReader(isr);
        String line = null;

        if ((line = reader.readLine()) == null) return;

        assert line != null;
        DataTitle= line.split(",");

        while(true){
            if ((line=reader.readLine())==null) break;

            String[] item = line.split(",");
            ArrayList<Double> tmp=new ArrayList<>();
            labelMat.add(Integer.valueOf(item[0]));
            for(int i=1;i< item.length;i++)
                tmp.add(Double.valueOf(item[i]));
            data.add(tmp);
        }

        isr.close();
    }

    /**
     * 將data的型態轉成array方便之後取用
     */

    public void ArrayListDataToArray(){

        doubleData=new double[data.size()][data.get(0).size()];
        IntLabelMat=new int[labelMat.size()];

        for(int i=0;i<doubleData.length;i++){
            for(int j=0;j<doubleData[0].length;j++){
                doubleData[i][j]=data.get(i).get(j);
//                System.out.println(doubleData[i][j]);
            }
        }

        for(int i=0;i<labelMat.size();i++){
            IntLabelMat[i]=labelMat.get(i);
//            System.out.println(IntLabelMat[i]);
        }
    }
}
