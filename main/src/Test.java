import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
public class Test {
    public static void main(String [] args){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800,800));
        frame.setLocation(10,10);

        frame.setLayout(new FlowLayout());
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        //把Jbutton替换为你的饼图应该就可以了。
        //JButton  b1=new JButton("JPanel1");
        //饼图1
        JFreeChart chart = createChart(createDataset());
        // JButton  b2=new JButton("JPanel2");
        ChartPanel localChartPanel = new ChartPanel(chart, false);
        //饼图2
        JFreeChart chart2 = createChart(createDataset());
        ChartPanel localChartPanel2 = new ChartPanel(chart2, false);
        p1.add(localChartPanel);
        //p1.add(b1);
        p2.add(localChartPanel2);
        frame.add(p1, FlowLayout.LEFT);
        frame.add(p2, FlowLayout.CENTER);

        //在设置frame为显示状态
        frame.setVisible(true);
    }
    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }
    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 1",  // chart title
                dataset,             // data
                true,               // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;

    }

}