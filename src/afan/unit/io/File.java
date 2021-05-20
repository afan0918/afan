package afan.unit.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class File {
    private String Path ="C:\\Users\\username\\Documents";
    private String[] AllTitle=null;//全部的資料特徵
    private String[] DataTitle=null;//被篩選完把na率太高的特徵篩除之後的資料特徵
    private ArrayList<Integer> LabelMat =new ArrayList<>();
    private ArrayList<ArrayList<Double>> Data =new ArrayList<>();
    private double[][] DoubleData =null;
    private int[] IntLabelMat=null;
    private ArrayList<String> SampleName=new ArrayList<>();

    /**
     * 選擇檔案路徑
     */
    public void ChooseFile() {}

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

    /**
     * 將data的型態轉成array方便之後取用
     */
    public void ArrayListDataToArray(double maxNA){

        int n = getData().size(), m = 0;
        boolean[] badData = new boolean[getData().get(0).size()];

        for (int i = 0; i < getData().get(0).size(); i++) {
            int sum = 0;
            for (ArrayList<Double> datum : getData()) {
                if (datum.get(i).isNaN()) sum++;
            }
            badData[i] = sum < n*maxNA/100;//把NA率高於標準的數據進行篩除
            if (badData[i]) ++m;
        }

        double[][] doubleData=new double[getData().size()][getData().get(0).size()];
        int[] intLabelMat=new int[getLabelMat().size()];
        int c = 0;
        String[] title=new String[m+1];

        for (int i = 0; i < getData().get(0).size(); ++i) {
            if (!badData[i]) continue;
            for (int j = 0; j < getData().size(); j++) {
                doubleData[j][c] = getData().get(j).get(i).isNaN() ?
                        1 : getData().get(j).get(i);
            }
            c++;
        }

        c=0;

        for (int i = 2; i < getDataTitle().length; ++i) {
            if (!badData[i-2]) continue;
            title[++c]=getDataTitle()[i];
        }

        setAllTitle(getDataTitle());
        setDataTitle(title);

        for(int i=0;i<getLabelMat().size();i++){
            intLabelMat[i]=getLabelMat().get(i);
//            System.out.println(IntLabelMat[i]);
        }

        setDoubleData(doubleData);
        setIntLabelMat(intLabelMat);
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        this.Path = path;
    }

    public String[] getAllTitle() {
        return AllTitle;
    }

    public void setAllTitle(String[] allTitle) {
        AllTitle = allTitle;
    }

    public String[] getDataTitle() {
        return DataTitle;
    }

    public void setDataTitle(String[] dataTitle) {
        DataTitle = dataTitle;
    }

    public ArrayList<Integer> getLabelMat() {
        return LabelMat;
    }

    public void setLabelMat(ArrayList<Integer> labelMat) {
        this.LabelMat = labelMat;
    }

    public void addLabelMat(int labelMat) {
        this.LabelMat.add(labelMat);
    }

    public ArrayList<ArrayList<Double>> getData() {
        return Data;
    }

    public void setData(ArrayList<ArrayList<Double>> data) {
        this.Data = data;
    }

    public void addData(ArrayList<Double> data){
        this.Data.add(data);
    }

    public double[][] getDoubleData() {
        return DoubleData;
    }

    public void setDoubleData(double[][] doubleData) {
        this.DoubleData = doubleData;
    }

    public int[] getIntLabelMat() {
        return IntLabelMat;
    }

    public void setIntLabelMat(int[] intLabelMat) {
        IntLabelMat = intLabelMat;
    }

    public File getFile(){
        return this;
    }

    public ArrayList<String> getSampleName() {
        return SampleName;
    }

    public void setSampleName(ArrayList<String> sampleName) {
        SampleName = sampleName;
    }

    public void addSampleName(String sampleName) {
        SampleName.add(sampleName);
    }
}
