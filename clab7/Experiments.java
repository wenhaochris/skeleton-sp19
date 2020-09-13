import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> t1 = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        xValues.add(0);
        y2Values.add(0.0);
        yValues.add(0.0);
        for(int i = 1; i <= 5000; i++){
            ExperimentHelper.getInt(t1);
            xValues.add(i);
            yValues.add(t1.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));

        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);
        chart.addSeries("Optimal depth", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> t2 = new BST<>();
        int temp = 0;
        int count = 0;
        while (count < 5000){
            count++;
            ExperimentHelper.getInt(t2);
        }

        double step_depth = t2.averageDepth();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(step_depth);
        for (int i = 1; i < 100000; i++){
            t2.deleteTakingSuccessor(t2.getRandomKey());
            ExperimentHelper.getInt(t2);
            xValues.add(i);
            yValues.add(t2.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> t3 = new BST<>();
        int temp = 0;
        int count = 0;
        while (count < 5000){
            count++;
            ExperimentHelper.getInt(t3);
        }

        double step_depth = t3.averageDepth();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(step_depth);
        for (int i = 1; i < 300000; i++){
           t3.deleteTakingRandom(t3.getRandomKey());
            ExperimentHelper.getInt(t3);
            xValues.add(i);
            yValues.add(t3.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();

    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();
    }
}
