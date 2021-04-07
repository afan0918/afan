import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

public class ChartMethod {
    public static void DrawFScoreChart(XYChart chart,String[] DataTitle, ArrayList<double[]> Data) throws Exception {
        // Create Chart
        chart = new XYChartBuilder().width(339).height(287).xAxisTitle("Combination").yAxisTitle("FScore").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);

        // Series
        for(int i=0;i<DataTitle.length;i++) {
            chart.addSeries(DataTitle[i], Data.get(i));
        }
    }
}
