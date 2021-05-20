package afan.unit.io;

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
public class TSVFile extends File{

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
            setPath(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * 將已正規化的資料分區塊存入DataTitle、labelMat、data當中
     */
    public void GetFileData(String Path) throws IOException {//將資料讀入，並轉成想要的資料格式

        InputStreamReader isr;//待處理資料的檔案路徑

        isr = new InputStreamReader(new FileInputStream(Path));

        BufferedReader reader = new BufferedReader(isr);
        String line;

        if ((line = reader.readLine()) == null) return;

        setDataTitle(line.split("\t"));
        //for (String x : getDataTitle()) System.out.println(x);

        while ((line = reader.readLine()) != null) {
            String[] item = line.split("\t");
            ArrayList<Double> tmp = new ArrayList<>();
            addSampleName(item[0]);
            addLabelMat(item[1].compareTo("0") == 0 ? 0 : 1);
            for (int i = 2; i < item.length; i++)
                tmp.add(item[i].compareTo("NA") == 0 ?
                        Double.NaN : Double.parseDouble(item[i]));
            addData(tmp);
        }

        isr.close();
    }

    public static void main(String[] args) {
        var tsv=new TSVFile();
        //選擇檔案
        tsv.ChooseFile();

        //讀入資料
        try {
            tsv.GetFileData(tsv.getPath());
        } catch (IOException e) {
            System.out.println("ㄨㄚˊ，失敗了");
            e.printStackTrace();
        }

        //轉array
        tsv.ArrayListDataToArray(20);
    }
}
