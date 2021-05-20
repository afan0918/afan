package afan.unit.plot;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

public class LineChart {
    public static XYChart DrawFScoreChart(ArrayList<String> DataTitle, ArrayList<double[]> Data) throws Exception {
        // Create Chart
        XYChart chart = new XYChartBuilder().height(400).width(630).xAxisTitle("Combination").yAxisTitle("FScore").build();
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        // Series
        for(int i=0;i<DataTitle.size();i++) {
            chart.addSeries(DataTitle.get(i), Data.get(i));
        }
        return chart;
    }
}
