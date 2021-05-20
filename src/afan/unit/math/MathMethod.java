package afan.unit.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Afan Chen
 */
public class MathMethod {

    public static void main(String[] args) {
        List<int[]> a=Combination(5,2);
        for(int i=0;i<a.size();i++){
            for(int j=0;j<a.get(i).length;j++){
                System.out.println(a.get(i)[j]);
            }
        }
    }

    public static List<int[]> Combination(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }

    public static void reverse(double[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            double temp = data[left];
            data[left]  = data[right];
            data[right] = temp;
        }
    }

    public static void ComplementReverse(int[] feature) {
        for (int i=0;i<feature.length;i++) {
            if(feature[i]==1) feature[i]=0;
            else if(feature[i]==0) feature[i]=1;
        }
    }

    public static double[] GetBestFScore(ArrayList<Double> fScores, ArrayList<int[]> combinations, int size) {
        double[] list=new double[size];
        int pos=0;
        for(int i=2;i<=size;i++){
            double max = 0;
            while(pos<combinations.size()&&i==combinations.get(pos).length) {
                if (max<fScores.get(pos)) max=fScores.get(pos);
                pos++;
            }
            list[i-1]=max;
        }
        return list;
    }

    public static double getMaxValue(ArrayList<Double> list) {
        double max=0;
        for(int i=0;i<list.size();i++){
            max=Math.max(max,list.get(i));
        }
        return max;
    }
}
