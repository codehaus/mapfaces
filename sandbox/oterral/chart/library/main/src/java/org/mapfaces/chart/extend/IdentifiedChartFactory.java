/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.chart.extend;

import java.util.Locale;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author sorel
 */
public class IdentifiedChartFactory {




    /**
     * Creates a pie chart with default settings.
     * <P>
     * The chart object returned by this method uses a {@link PiePlot} instance
     * as the plot.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param locale  the locale (<code>null</code> not permitted).
     *
     * @return A pie chart.
     *
     * @since 1.0.7
     */
    public static JFreeChart createPieChart(String title, PieDataset dataset,
            boolean legend, boolean tooltips, Locale locale) {

        PiePlot plot = new IdentifiedPiePlot(dataset);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
        plot.setInsets(new RectangleInsets(0.0, 5.0, 5.0, 5.0));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
        }
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,plot, legend);
        
        return chart;

    }



    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <P>
     * A convenient dataset to use with this chart is a
     * {@link org.jfree.data.time.TimeSeriesCollection}.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code>
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A time series chart.
     */
    public static JFreeChart createTimeSeriesChart(String title,
                                                   String timeAxisLabel,
                                                   String valueAxisLabel,
                                                   XYDataset dataset,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {

        ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setLowerMargin(0.02);  // reduce the default margins
        timeAxis.setUpperMargin(0.02);
        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        XYPlot plot = new IdentifiedXYPlot(dataset, timeAxis, valueAxis, null);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator
                = StandardXYToolTipGenerator.getTimeSeriesInstance();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,false);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
        renderer.setURLGenerator(urlGenerator);
        plot.setRenderer(renderer);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,plot, legend);

        return chart;
    }


    /**
     * Creates a bar chart.  The chart object returned by this method uses a
     * {@link CategoryPlot} instance as the plot, with a {@link CategoryAxis}
     * for the domain axis, a {@link NumberAxis} as the range axis, and a
     * {@link BarRenderer} as the renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param categoryAxisLabel  the label for the category axis
     *                           (<code>null</code> permitted).
     * @param valueAxisLabel  the label for the value axis
     *                        (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     (<code>null</code> not permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A bar chart.
     */
    public static JFreeChart createBarChart(String title,
                                            String categoryAxisLabel,
                                            String valueAxisLabel,
                                            CategoryDataset dataset,
                                            PlotOrientation orientation,
                                            boolean legend,
                                            boolean tooltips,
                                            boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        BarRenderer renderer = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setBaseNegativeItemLabelPosition(position2);
         }
        else if (orientation == PlotOrientation.VERTICAL) {
            ItemLabelPosition position1 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setBasePositiveItemLabelPosition(position1);
            ItemLabelPosition position2 = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setBaseNegativeItemLabelPosition(position2);
        }
        if (tooltips) {
            renderer.setBaseToolTipGenerator(
                    new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator());
        }

        CategoryPlot plot = new IdentifiedCategoryPlot(dataset, categoryAxis, valueAxis,
                renderer);
        plot.setOrientation(orientation);
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        
        return chart;

    }

}
