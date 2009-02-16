/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jsfcomp.chartcreator.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.chart.extend.IdentifiedChartFactory;
import org.mapfaces.chart.extend.IdentifiedSVGGraphics2D;
import net.sf.jsfcomp.chartcreator.model.ChartAxisData;
import net.sf.jsfcomp.chartcreator.model.ChartData;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.Zoomable;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleInsets;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.mapfaces.share.listener.ResourcePhaseListener;

/**
 * @author Cagatay Civici (latest modification by $Author: cagatay_civici $)
 * @version $Revision: 751 $ $Date: 2007-05-08 11:18:24 +0300 (Tue, 08 May 2007) $
 */
public class ChartUtils {

    private static String passthruImgAttributes[] = {
        "alt",
        "styleClass",
        "onclick",
        "ondblclick",
        "onmousedown",
        "onmouseup",
        "onmouseover",
        "onmousemove",
        "onmouseout",
        "onkeypress",
        "onkeydown",
        "onkeyup",
        "usemap",
    };

    public static void renderPassThruImgAttributes(ResponseWriter writer, UIComponent component) throws IOException {
        for (int i = 0; i < passthruImgAttributes.length; i++) {
            Object value = component.getAttributes().get(passthruImgAttributes[i]);
            if (value != null) {
                writer.writeAttribute(passthruImgAttributes[i], value, null);
            }
        }
        //title attribute overlaps with the chart title so renamed to imgTitle to define img tag's title  
        if (component.getAttributes().get("imgTitle") != null) {
            writer.writeAttribute("title", component.getAttributes().get("imgTitle"), null);
        }
    }

    public static PlotOrientation getPlotOrientation(String orientation) {
        if (orientation.equalsIgnoreCase("horizontal")) {
            return PlotOrientation.HORIZONTAL;
        } else if (orientation.equalsIgnoreCase("vertical")) {
            return PlotOrientation.VERTICAL;
        } else {
            throw new RuntimeException("Unsupported plot orientation:\n" + orientation);
        }
    }

    public static Color getColor(String color) {
        if (color.startsWith("#")) {
            return new Color(Integer.parseInt(color.substring(1), 16));
        } else {
            // Colors by name
            if (color.equalsIgnoreCase("black")) {
                return Color.black;
            }
            if (color.equalsIgnoreCase("gray")) {
                return Color.gray;
            }
            if (color.equalsIgnoreCase("yellow")) {
                return Color.yellow;
            }
            if (color.equalsIgnoreCase("green")) {
                return Color.green;
            }
            if (color.equalsIgnoreCase("blue")) {
                return Color.blue;
            }
            if (color.equalsIgnoreCase("red")) {
                return Color.red;
            }
            if (color.equalsIgnoreCase("orange")) {
                return Color.orange;
            }
            if (color.equalsIgnoreCase("cyan")) {
                return Color.cyan;
            }
            if (color.equalsIgnoreCase("magenta")) {
                return Color.magenta;
            }
            if (color.equalsIgnoreCase("darkgray")) {
                return Color.darkGray;
            }
            if (color.equalsIgnoreCase("lightgray")) {
                return Color.lightGray;
            }
            if (color.equalsIgnoreCase("pink")) {
                return Color.pink;
            }
            if (color.equalsIgnoreCase("white")) {
                return Color.white;
            }
            throw new RuntimeException("Unsupported chart color:\n" + color);
        }
    }

    public static String resolveContentType(String output) {
        if (output.equalsIgnoreCase("png")) {
            return "img/png";
        } else if (output.equalsIgnoreCase("jpeg")) {
            return "img/jpeg";
        } else if (output.equalsIgnoreCase("svg")) {
            return "image/svg+xml";
        } else {
            throw new RuntimeException("Unsupported output format:\n" + output);
        }
    }
    //Creates the chart with the given chart data
    public static JFreeChart createChartWithType(ChartData chartData) {
        JFreeChart chart = null;
        Object datasource = chartData.getDatasource();
        if ((datasource instanceof List) && ((List) datasource).size() > 0) {  //List<XYDataset>
            Dataset dataset = (Dataset) ((List) datasource).get(0);
            if (dataset instanceof PieDataset) {
                chart = createChartWithPieDataSet(chartData);
            } else if (dataset instanceof CategoryDataset) {
                chart = createChartWithCategoryDataSet(chartData);
            } else if (dataset instanceof XYDataset) {
                chart = createChartWithXYDataSet(chartData);
            } else {
                throw new RuntimeException("Unsupported chart type");
            }
        } else {
            if (datasource instanceof PieDataset) {
                chart = createChartWithPieDataSet(chartData);
            } else if (datasource instanceof CategoryDataset) {
                chart = createChartWithCategoryDataSet(chartData);
            } else if (datasource instanceof XYDataset) {
                chart = createChartWithXYDataSet(chartData);
            } else {
                throw new RuntimeException("Unsupported chart type");
            }
        }

        if (chartData.getLegendFontSize() > 0) {
            chart.getLegend().setItemFont(LegendTitle.DEFAULT_ITEM_FONT.deriveFont(chartData.getLegendFontSize()));
        }
        if (!chartData.isLegendBorder()) {
            chart.getLegend().setBorder(0, 0, 0, 0);
        }
        return chart;
    }

    public static void setGeneralChartProperties(JFreeChart chart, ChartData chartData) {
        Color color = ChartUtils.getColor(chartData.getBackground2());
        if (color == null) {
            chart.setBackgroundPaint(new Color(0f, 0f, 0f, 0f));
            chart.getPlot().setBackgroundAlpha(0f);
        } else {
            chart.setBackgroundPaint(ChartUtils.getColor(chartData.getBackground2()));
        }

        chart.getPlot().setBackgroundPaint(ChartUtils.getColor(chartData.getForeground2()));
        chart.setTitle(chartData.getTitle());
        //AntiAlias makes some grid lines disappear
        //chart.setAntiAlias(chartData.isAntialias());

        //Alpha transparency (100% means opaque)
        if (chartData.getAlpha() < 100) {
            chart.getPlot().setForegroundAlpha((float) chartData.getAlpha() / 100);
        }
    }

    public static JFreeChart createChartWithCategoryDataSet(ChartData chartData) {
        JFreeChart chart = null;
        PlotOrientation plotOrientation = ChartUtils.getPlotOrientation(chartData.getOrientation());

        CategoryDataset dataset = (CategoryDataset) chartData.getDatasource();
        String type = chartData.getType();
        String xAxis = chartData.getXlabel();
        String yAxis = chartData.getYlabel();
        boolean is3d = chartData.isChart3d();
        boolean legend = chartData.isLegend();

        if (type.equalsIgnoreCase("bar")) {
            if (is3d == true) {
                chart = ChartFactory.createBarChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            } else {
                chart = IdentifiedChartFactory.createBarChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            }
            setBarOutline(chart, chartData);
        } else if (type.equalsIgnoreCase("stackedbar")) {
            if (is3d == true) {
                chart = ChartFactory.createStackedBarChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            } else {
                chart = ChartFactory.createStackedBarChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            }
            setBarOutline(chart, chartData);
        } else if (type.equalsIgnoreCase("line")) {
            if (is3d == true) {
                chart = ChartFactory.createLineChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            } else {
                chart = ChartFactory.createLineChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
            }
        } else if (type.equalsIgnoreCase("area")) {
            chart = ChartFactory.createAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("stackedarea")) {
            chart = ChartFactory.createStackedAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("waterfall")) {
            chart = ChartFactory.createWaterfallChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("gantt")) {
            chart = ChartFactory.createGanttChart("", xAxis, yAxis, (IntervalCategoryDataset) dataset, legend, true, false);
        }

        CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot();
        plot.setDomainGridlinesVisible(chartData.isDomainGridLines());
        plot.setRangeGridlinesVisible(chartData.isRangeGridLines());
        if (chartData.getGenerateMap() != null) {
            plot.getRenderer().setBaseItemURLGenerator(
                    new StandardCategoryURLGenerator(""));
        }
        int seriesCount = plot.getDataset().getColumnCount();
        if (chartData.getLineStokeWidth() > 0) {
            for (int index = 0; index <= seriesCount; index++) {
                plot.getRenderer().setSeriesStroke(index, new BasicStroke(chartData.getLineStokeWidth()));
            }
        }

        setCategorySeriesColors(chart, chartData);

        setCategoryExtensions(chart, chartData);

        return chart;
    }

    public static JFreeChart createChartWithPieDataSet(ChartData chartData) {
        PieDataset dataset = (PieDataset) chartData.getDatasource();
        String type = chartData.getType();
        boolean legend = chartData.isLegend();
        JFreeChart chart = null;
        if (type.equalsIgnoreCase("pie")) {
            if (chartData.isChart3d()) {
                chart = ChartFactory.createPieChart3D("", dataset, legend, true, false);
                PiePlot3D plot = (PiePlot3D) chart.getPlot();
                plot.setDepthFactor((float) chartData.getDepth() / 100);
            } else {
                // chart = ChartFactory.createPieChart("", dataset, legend, true, false);
                chart = IdentifiedChartFactory.createPieChart("", dataset, true, false, Locale.FRENCH);
            }
        } else if (type.equalsIgnoreCase("ring")) {
            chart = ChartFactory.createRingChart("", dataset, legend, true, false);
        }
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle((float) chartData.getStartAngle());
        if (chartData.getGenerateMap() != null) {
            plot.setURLGenerator(
                    new StandardPieURLGenerator(""));
        }
        setPieSectionColors(chart, chartData);

        return chart;
    }

    public static JFreeChart createChartWithXYDataSet(ChartData chartData) {
        List<XYDataset> datasets = null;
        XYDataset dataset = null;
        boolean isList = false;
        if (chartData.getDatasource() instanceof List) {
            datasets = (List<XYDataset>) chartData.getDatasource();
            dataset = datasets.get(0);
        } else {
            dataset = (XYDataset) chartData.getDatasource();
        }
        String type = chartData.getType();
        String xAxis = chartData.getXlabel();
        String yAxis = chartData.getYlabel();
        boolean legend = chartData.isLegend();

        JFreeChart chart = null;
        PlotOrientation plotOrientation = ChartUtils.getPlotOrientation(chartData.getOrientation());

        if (type.equalsIgnoreCase("timeseries")) {
            chart = IdentifiedChartFactory.createTimeSeriesChart("", xAxis, yAxis, dataset, legend, true, false);
        } else if (type.equalsIgnoreCase("xyline")) {
            chart = ChartFactory.createXYLineChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("polar")) {
            chart = ChartFactory.createPolarChart("", dataset, legend, true, false);
        } else if (type.equalsIgnoreCase("scatter")) {
            chart = ChartFactory.createScatterPlot("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("xyarea")) {
            chart = ChartFactory.createXYAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("xysteparea")) {
            chart = ChartFactory.createXYStepAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("xystep")) {
            chart = ChartFactory.createXYStepChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("bubble")) {
            chart = ChartFactory.createBubbleChart("", xAxis, yAxis, (XYZDataset) dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("candlestick")) {
            chart = ChartFactory.createCandlestickChart("", xAxis, yAxis, (OHLCDataset) dataset, legend);
        } else if (type.equalsIgnoreCase("boxandwhisker")) {
            chart = ChartFactory.createBoxAndWhiskerChart("", xAxis, yAxis, (BoxAndWhiskerXYDataset) dataset, legend);
        } else if (type.equalsIgnoreCase("highlow")) {
            chart = ChartFactory.createHighLowChart("", xAxis, yAxis, (OHLCDataset) dataset, legend);
        } else if (type.equalsIgnoreCase("histogram")) {
            chart = ChartFactory.createHistogram("", xAxis, yAxis, (IntervalXYDataset) dataset, plotOrientation, legend, true, false);
        } else if (type.equalsIgnoreCase("wind")) {
            chart = ChartFactory.createWindPlot("", xAxis, yAxis, (WindDataset) dataset, legend, true, false);
        }

        //If there is multiple datasets
        if (datasets != null) {
            ChartUtils.addXYDataset(chart, datasets);
        }

        if (chart.getPlot() instanceof XYPlot) {
            chart.getXYPlot().setDomainGridlinesVisible(chartData.isDomainGridLines());
            chart.getXYPlot().setRangeGridlinesVisible(chartData.isRangeGridLines());

            if (chartData.getGenerateMap() != null) {
                chart.getXYPlot().getRenderer().setURLGenerator(new StandardXYURLGenerator(""));
            }
        }

        //setXYSeriesColors(chart, chartData);
        setXYDatasetColors(chart, chartData);
        setXYExtensions(chart, chartData);

        return chart;
    }

    /**
     * Series coloring
     * Plot has no getRenderer so two methods for each plot type(categoryplot and xyplot)
     */
    public static void setCategorySeriesColors(JFreeChart chart, ChartData chartData) {
        if (chart.getPlot() instanceof CategoryPlot) {
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            if (chartData.getColors() != null) {
                String[] colors = chartData.getColors().split(",");
                for (int i = 0; i < colors.length; i++) {
                    plot.getRenderer().setSeriesPaint(i, ChartUtils.getColor(colors[i].trim()));
                }
            }
        }
    }

    public static void setXYSeriesColors(JFreeChart chart, ChartData chartData) {
        if (chart.getPlot() instanceof XYPlot && chartData.getColors() != null) {
            XYPlot plot = (XYPlot) chart.getPlot();
            String[] colors = chartData.getColors().split(",");
            for (int i = 0; i < colors.length; i++) {
                plot.getRenderer().setSeriesPaint(i, ChartUtils.getColor(colors[i].trim()));
            }
        }
    }

    private static void setXYDatasetColors(JFreeChart chart, ChartData chartData) {
        if (chart.getPlot() instanceof XYPlot && chartData.getColors() != null) {
            XYPlot plot = (XYPlot) chart.getPlot();
            String[] colors = chartData.getColors().split(",");
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.black);
            plot.setRangeGridlinePaint(Color.black);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

            //Define the renderer of the XYPlot with a line and a shape (point, rectangle, triangle, square...)
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
            //Set if points are filled or not
            //default to false because it creates 2 <path> tag (SVG output) for one point
            renderer.setBaseShapesFilled(false);
//            //Series 0 shape will be drawn with an Ellipse
//            renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6), false);
            
            int total = 0;
            for (int l = 0; l < plot.getDatasetCount(); l++) {
                for (int m = 0; m < plot.getDataset(l).getSeriesCount(); m++) {
                    total += plot.getDataset(l).getItemCount(m);
                }
            }
            if (total < 50) {
                //Set if shape  are visible or not
                renderer.setBaseShapesVisible(true);
            } else {
                renderer.setBaseShapesVisible(false);
            }
            /**/
            int i = 0;
            int cpt = 0;
            while (i < plot.getDatasetCount()) {
                try {
                    Color color = ChartUtils.getColor(colors[cpt].trim());
                    NumberAxis axis = new NumberAxis("Range Axis " + (i + 1));
                    axis.setAutoRangeIncludesZero(false);
                    axis.setLabelPaint(color);
                    axis.setTickLabelPaint(color);
                    plot.setRangeAxis(i, axis);
                    if ((i % 2) == 0) {
                        plot.setRangeAxisLocation(i, AxisLocation.BOTTOM_OR_LEFT);
                    } else {
                        plot.setRangeAxisLocation(i, AxisLocation.TOP_OR_RIGHT);
                    }
                    plot.mapDatasetToRangeAxis(i, new Integer(i));
                    int j = 0;
                    while (j < plot.getDataset(i).getSeriesCount()) {
                        renderer.setDrawSeriesLineAsPath(false);
                        plot.setRenderer(i, (XYItemRenderer) renderer.clone());//                
                        plot.getRenderer(i).setSeriesPaint(j, ChartUtils.getColor(colors[cpt].trim()));
                        j++;
                        cpt++;
                    }
                    } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ChartUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
        }
    }

    public static void setPieSectionColors(JFreeChart chart, ChartData chartData) {
        if (chartData.getColors() != null) {
            String[] colors = chartData.getColors().split(",");
            for (int i = 0; i < colors.length; i++) {
                //((PiePlot)chart.getPlot()).setSectionPaint(new Integer(i), ChartUtils.getColor(colors[i].trim())); does not work
                ((PiePlot) chart.getPlot()).setSectionPaint(i, ChartUtils.getColor(colors[i].trim()));
            }
        }
    }

    /**
     * Sets the outline of the bars
     */
    public static void setBarOutline(JFreeChart chart, ChartData chartData) {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
        barrenderer.setDrawBarOutline(chartData.isOutline());
    }

    /**
     * XY charts extensions
     */
    public static void setXYExtensions(JFreeChart chart, ChartData chartData) {
        int axisIndex = 1;
        for (Iterator it = chartData.getExtensions().iterator(); it.hasNext();) {
            Object extension = it.next();
            if (extension instanceof ChartAxisData) {
                ChartAxisData chartAxisData = (ChartAxisData) extension;
                ChartAxisUtils.createXYSeriesAxis(chart, chartAxisData, axisIndex);
                axisIndex++;
            }
        }
    }

    /**
     * Category charts extensions
     */
    public static void setCategoryExtensions(JFreeChart chart, ChartData chartData) {
        int axisIndex = 1;
        for (Iterator it = chartData.getExtensions().iterator(); it.hasNext();) {
            Object extension = it.next();
            if (extension instanceof ChartAxisData) {
                ChartAxisData chartAxisData = (ChartAxisData) extension;
                ChartAxisUtils.createCategorySeriesAxis(chart, chartAxisData, axisIndex);
                axisIndex++;
            }
        }
    }

    public static boolean useServlet(FacesContext facesContext) {
        String value = facesContext.getExternalContext().getInitParameter(ChartConstants.GENERATOR_PARAM);
        if (value != null) {
            return Boolean.valueOf(value).booleanValue();
        } else {
            return true;
        }
    }

    public static void writeChartAsSVG(OutputStream stream, ChartData chartData, ChartRenderingInfo info) throws SVGGraphics2DIOException, UnsupportedEncodingException {
        if (chartData.getType().equals("timeseries")) {
            writeChartAsSVG(stream, chartData.getChart(), chartData.getWidth(), chartData.getHeight(), info, true);
        } else {
            writeChartAsSVG(stream, chartData.getChart(), chartData.getWidth(), chartData.getHeight(), info, false);
        }
    }

    public static void writeChartAsSVG(OutputStream stream, JFreeChart chart, int width, int height) throws SVGGraphics2DIOException, UnsupportedEncodingException {
        ChartRenderingInfo chartRenderingInfo = new ChartRenderingInfo();
        writeChartAsSVG(stream, chart, width, height, chartRenderingInfo, false);
    }

    public static void writeChartAsSVG(OutputStream stream, JFreeChart chart, int width, int height, ChartRenderingInfo chartRenderingInfo, boolean isDynamic) throws SVGGraphics2DIOException, UnsupportedEncodingException {

        //create the SVG from chart
        final DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        final String svgNS = "http://www.w3.org/2000/svg";
        final Document document = domImpl.createDocument(svgNS, "svg", null);
        final SVGGraphics2D svgGenerator = new IdentifiedSVGGraphics2D(document);
        svgGenerator.setSVGCanvasSize(new Dimension(width, height));
        chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, width, height), chartRenderingInfo);

        Element root = document.getDocumentElement();
        root.appendChild(svgGenerator.getRoot().getChildNodes().item(2));

        //Add JavaScript 
        if (isDynamic) {
            boolean effects = true;
            boolean navigation = true;
            boolean increaseDragSpeed = false;
            if (navigation) {
                addNavigation(document, root, width, height);
                if (increaseDragSpeed) {
                    optimizeSvgRendering(document);
                //                replaceLinesByPolyline(svgGenerator, document, root, width, height);
                //                replacePathByCircle(svgGenerator, document, root, width, height);
                }
            }
            if (effects) {
                addEffects(document);
            }
            if (effects || navigation) {
                addInit(document);
            }
        }
        svgGenerator.stream(root, new OutputStreamWriter(stream, "ISO-8859-1"));
    }

    private static Node createCircleFromPath(Document document, Node item) {
        String[] coordinates = item.getAttributes().getNamedItem("d").getNodeValue().split("C")[0].substring(1).split(" ");
        int rayon = 4;
        Node circle = createCircle(document, "",
                String.valueOf(Double.valueOf(coordinates[0]) - (3)),
                coordinates[1],
                String.valueOf(rayon), "", "", "");

        NamedNodeMap circleAttributes = circle.getAttributes();
        NamedNodeMap attributes = item.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (!attributes.item(i).getNodeName().equals("d")) {
                circleAttributes.setNamedItem(attributes.item(i).cloneNode(true));
            }
        }
        return circle;


    }

    private static Node createPolyline(Document document, String id, String points, String stroke, String style) {
        Element elt = document.createElement("polyline");
        elt.getAttributes().setNamedItem(createAttribute(document, "id", id));
        elt.getAttributes().setNamedItem(createAttribute(document, "points", points));
        elt.getAttributes().setNamedItem(createAttribute(document, "fill", "none"));
        elt.getAttributes().setNamedItem(createAttribute(document, "stroke", stroke));
        elt.getAttributes().setNamedItem(createAttribute(document, "style", style));
        return elt;
    }

    private static Node createPolylineFromLines(Document document, String points, NamedNodeMap attributes) {
        Node polyline = createPolyline(document, "",
                points, "", "");

        NamedNodeMap eltAttributes = polyline.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.item(i).getNodeName().equals("serie") || attributes.item(i).getNodeName().equals("stroke")) {
                eltAttributes.setNamedItem(attributes.item(i).cloneNode(true));
            }
        }
        return polyline;
    }

    private static void optimizeSvgRendering(Document document) {
        Node canvas = document.getElementById("canvas");
        if (canvas != null) {
            NodeList childs = canvas.getChildNodes();
            for (int i = 1; i < childs.getLength(); i++) {
                if (childs.item(i).getNodeName().equals("line") && childs.item(i).getAttributes().getNamedItem("serie") != null) {
                    StringWriter points = new StringWriter();
                    NamedNodeMap lineAttributes = null;
                    while (childs.item(i).getNodeName().equals("line")) {
                        points.append(childs.item(i).getAttributes().getNamedItem("x1").getNodeValue()).append(",").append(childs.item(i).getAttributes().getNamedItem("y1").getNodeValue()).append(" ");
                        if ((childs.item(i + 1) != null) && !childs.item(i + 1).getNodeName().equals("line")) {
                            lineAttributes = childs.item(i).getAttributes();
                            points.append(childs.item(i).getAttributes().getNamedItem("x2").getNodeValue()).append(",").append(childs.item(i).getAttributes().getNamedItem("y2").getNodeValue()).append(" ");
                        }
                        childs.item(i).getParentNode().removeChild(childs.item(i));
                    }
                    i--;
                    childs.item(i).getParentNode().insertBefore(createPolylineFromLines(document, points.toString(), lineAttributes), childs.item(i));
                } else if (childs.item(i).getNodeName().equals("path") && childs.item(i).getAttributes().getNamedItem("serie") != null) {
                    //remove useless child
//                    if ((childs.item(i).getAttributes().getNamedItem("fill") != null) && (childs.item(i).getAttributes().getNamedItem("fill").getNodeValue().equals("none"))) {
//                        canvas.removeChild(childs.item(i));
//                        i--;
//                    } else {
                    //replace the others by a circle
                    canvas.replaceChild(createCircleFromPath(document, childs.item(i)), childs.item(i));
//                    }
                }
            }
        }

    }

    private static void replaceLinesByPolyline(SVGGraphics2D svgGenerator,
            Document document, Element root, int width, int height) {

        Node canvas = document.getElementById("canvas");
        if (canvas != null) {
            NodeList childs = canvas.getChildNodes();
            List<Node> polyline = new ArrayList();
            for (int i = 1; i < childs.getLength(); i++) {
                if (childs.item(i).getNodeName().equals("line") && childs.item(i).getAttributes().getNamedItem("serie") != null) {
                    StringWriter points = new StringWriter();
                    NamedNodeMap lineAttributes = null;
                    while (childs.item(i).getNodeName().equals("line")) {
                        points.append(childs.item(i).getAttributes().getNamedItem("x1").getNodeValue()).append(",").append(childs.item(i).getAttributes().getNamedItem("y1").getNodeValue()).append(" ");
                        if (!childs.item(i + 1).getNodeName().equals("line")) {
                            lineAttributes = childs.item(i).getAttributes();
                            points.append(childs.item(i).getAttributes().getNamedItem("x2").getNodeValue()).append(",").append(childs.item(i).getAttributes().getNamedItem("y2").getNodeValue()).append(" ");
                        }
                        childs.item(i).getParentNode().removeChild(childs.item(i));
                    }
                    childs.item(i).getParentNode().insertBefore(createPolylineFromLines(document, points.toString(), lineAttributes), childs.item(i));
                }
            }
        }
    }

    private static void replacePathByCircle(SVGGraphics2D svgGenerator,
            Document document, Element root, int width, int height) {

        Node canvas = document.getElementById("canvas");
        if (canvas != null) {
            NodeList childs = canvas.getChildNodes();
            for (int i = 1; i < childs.getLength(); i++) {
                if (childs.item(i).getNodeName().equals("path") && childs.item(i).getAttributes().getNamedItem("serie") != null) {
                    //remove useless child
                    if ((childs.item(i).getAttributes().getNamedItem("fill") != null) && (childs.item(i).getAttributes().getNamedItem("fill").getNodeValue().equals("none"))) {
                        canvas.removeChild(childs.item(i));
                    } else {
                        //replace the others by a circle
                        canvas.replaceChild(createCircleFromPath(document, childs.item(i)), childs.item(i));
                    }
                }
            }
        }

    }

    private static void addNavigation(Document document, Element root, int width, int height) {
        //root.setAttribute("onload", "loadOpenCharts('canvas');");
        String[] jsFiles = {
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/SingleFile.js",
            "/org/mapfaces/resources/opencharts/custom/OpenLayers.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Util.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/Class.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/Bounds.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/Element.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/LonLat.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/Pixel.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/BaseTypes/Size.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Events.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/Click.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/Hover.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/Drag.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/Box.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/MouseWheel.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Handler/Keyboard.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Control.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Control/DragPan.js",
            "/org/mapfaces/resources/opencharts/lib/OpenLayers/Control/ZoomBox.js",
            "/org/mapfaces/resources/opencharts/custom/OpenLayers/Map.js",
            "/org/mapfaces/resources/opencharts/custom/OpenLayers/Handler/MouseWheel.js",
            "/org/mapfaces/resources/opencharts/custom/OpenLayers/Control/Navigation.js"
        };
        addScript(document, jsFiles);

        //Add a draggable <rect> element with the same width and height as his parent 
        NodeList gNodes = root.getElementsByTagName("g");
        boolean canvasCreated = false;
        for (int i = 0; i < gNodes.getLength(); i++) {
            if (i + 1 == gNodes.getLength()) {
                //There is no data to display in the graph so the canvas rectangle doesn't exist 
                //we must create one to have a background-color to seeing grid lines.

                Element canvas = document.createElement("g");
                //Set default attributes to the canvas
                canvas.getAttributes().setNamedItem(createAttribute(document, "id", "canvas"));
                canvas.getAttributes().setNamedItem(createAttribute(document, "width", String.valueOf(width)));
                canvas.getAttributes().setNamedItem(createAttribute(document, "height", String.valueOf(height)));
                canvas.getAttributes().setNamedItem(createAttribute(document, "x", String.valueOf(0)));
                canvas.getAttributes().setNamedItem(createAttribute(document, "y", String.valueOf(0)));
                canvas.getAttributes().setNamedItem(createAttribute(document, "cursor", "move"));
                canvas.getAttributes().setNamedItem(createAttribute(document, "fill", "black"));
                canvas.getAttributes().setNamedItem(createAttribute(document, "stroke", "black"));

                gNodes.item(i).getParentNode().insertBefore(canvas, gNodes.item(i));
                canvasCreated = true;
            }
            if (canvasCreated || (gNodes.item(i).hasChildNodes() && (gNodes.item(i).getFirstChild().getAttributes().getNamedItem("serie") != null))) {

                //Set default attributes to the canvas
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "id", "canvas"));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "width", String.valueOf(width)));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "height", String.valueOf(height)));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "x", String.valueOf(0)));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "y", String.valueOf(0)));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "cursor", "move"));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "fill", "black"));
                gNodes.item(i).getAttributes().setNamedItem(createAttribute(document, "stroke", "black"));

                //Add id to the container of grid lines
                gNodes.item(i - 1).getAttributes().setNamedItem(createAttribute(document, "id", "canvasGrid"));

                //Insert the container of grid lines into the canvas node as first child
                gNodes.item(i).insertBefore(gNodes.item(i - 1), gNodes.item(i).getFirstChild());
                i--;

                //Find the Rect element who defines the size of canvas container
                Node rectangleContainer = findCanvasContainer(document);
                if (rectangleContainer != null) {
                    //Set fill and id attribute to the canvas container
                    rectangleContainer.getAttributes().setNamedItem(createAttribute(document, "id", "canvasContainer"));
                    rectangleContainer.getAttributes().setNamedItem(createAttribute(document, "fill", "white"));
                    gNodes.item(i).insertBefore(rectangleContainer, gNodes.item(i).getFirstChild());

                    //Coordinates of the top-left corner of canvas container in pixel
                    Double containerOriginX = Double.valueOf(rectangleContainer.getAttributes().getNamedItem("x").getNodeValue());
                    Double containerOriginY = Double.valueOf(rectangleContainer.getAttributes().getNamedItem("y").getNodeValue());

                    //Width and height of canvas container
                    Double containerWidth = Double.valueOf(rectangleContainer.getAttributes().getNamedItem("width").getNodeValue());
                    Double containerHeight = Double.valueOf(rectangleContainer.getAttributes().getNamedItem("height").getNodeValue());

                    //Coordinates of the bottom-right corner of canvas container in pixel
                    Double containerMaxX = containerWidth + containerOriginX;
                    Double containerMaxY = containerHeight + containerOriginY;

                    //Coordinates of the top-left corner of canvas buffer in pixel
                    Double bufferOriginX = containerOriginX - containerWidth;
                    Double bufferOriginY = containerOriginY - containerHeight;

                    //Coordinates of the bottom-right corner of canvas buffer in pixel
                    Double bufferMaxX = containerWidth * 2 + containerOriginX;
                    Double bufferMaxY = containerHeight * 2 + containerOriginY;


                    //<rect x="-500" y="-500" clip-path="url(#clipPath1)" fill="red" width="1974.207" id="canvasRect" height="1652.4062" stroke="none"/>
                    //gNodes.item(i).insertBefore(createRect(document, "bufferCanvas", -500,-500,2000,2000, "silver", "cursor:move;"), gNodes.item(i).getFirstChild());

                    //Add a canvas buffer node as firstChild of canvas
                    gNodes.item(i).insertBefore(createPath(document, "canvasBuffer", "M" + bufferOriginX + " " + bufferOriginY + " L" + bufferMaxX + " " + bufferOriginY + " L" + bufferMaxX + " " + bufferMaxY + " L" + bufferOriginX + " " + bufferMaxY + " L" + bufferOriginX + " " + bufferOriginY + " Z M" + containerOriginX + " " + containerOriginY + " L" + containerOriginX + " " + containerMaxY + " L" + containerMaxX + " " + containerMaxY + " L" + containerMaxX + " " + containerOriginY + " Z", "silver", "cursor:move;"), gNodes.item(i).getFirstChild());

                    //Add the canvas node as second child of svg element (after defs node) , before  the original Rect graph container
                    gNodes.item(i).getParentNode().insertBefore(gNodes.item(i), gNodes.item(i).getParentNode().getFirstChild().getNextSibling());

                    //Replace the original Rect chart container by a Path graph container with a hole to see the canvas;
                    Node path = createPath(document, "chartContainer", "M0 0 L" + width + " 0 L" + width + " " + height + " L0 " + height + " L0 0 Z M" + containerOriginX + " " + containerOriginY + " L" + containerOriginX + " " + containerMaxY + " L" + containerMaxX + " " + containerMaxY + " L" + containerMaxX + " " + containerOriginY + " Z", "", "");
                    //d.setNodeValue("M0 0 L    600       0 L    600           400        L0     400        L0 0 Z M    104.5566        33.6406     L    104.5566        222.7656           L    495.4434                222.7656           L    495.4434                33.6406     Z");
                    // M0 0 L    1200      0 L    1200          800        L0     800        L0 0 Z M    121.2363        33.6406  L      121.2363        974.207            L    652.4062                974.207            L    652.4062                33.6406 Z
                    gNodes.item(i).getParentNode().getFirstChild().getNextSibling().getNextSibling().replaceChild(
                            path, gNodes.item(i).getParentNode().getFirstChild().getNextSibling().getNextSibling().getFirstChild());

                    createBufferGridLines(document, bufferOriginX, bufferOriginY, bufferMaxX, bufferMaxY);

                }
                return;
            }
        }
    }

    private static void addScript(Document document, String[] jsFiles) {
        Element script = document.createElement("script");
        for (int i = 0; i < jsFiles.length; i++) {
            Element clone = (Element) script.cloneNode(false);
            clone.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", ResourcePhaseListener.getURL(FacesContext.getCurrentInstance(), jsFiles[i], null));
            document.getDocumentElement().appendChild(clone);
        }
    }

    private static void addXYDataset(JFreeChart chart, List<XYDataset> datasets) {

        if (datasets.size() > 1) {
            XYPlot plot = chart.getXYPlot();
            Iterator iterator = datasets.listIterator();
            int i = 1;
            //datasets[0] already draw on the chart so we pass it
            iterator.next();
            while (iterator.hasNext()) {
                XYDataset f = (XYDataset) iterator.next();
                plot.setDataset(i, f);
                i++;
            }
        }
    }

    private static Element addTextTag(Document document) {
        Element text = document.createElement("text");
        text.setAttribute("id", "text");
        text.setAttribute("style", "display:none;");
        text.appendChild(document.createTextNode("X:Y:"));
        return text;
    }

    private static void addEffects(Document document) {
        String[] file = new String[1];
        file[0] = "/org/mapfaces/resources/js/effects.js";
        addScript(document, file);
    }

    private static void addInit(Document document) {
        String[] file = new String[1];
        file[0] = "/org/mapfaces/resources/js/init.js";
        addScript(document, file);
    }

    private static Attr createAttribute(Document document, String nodeName, String nodeValue) {
        Attr attr = document.createAttribute(nodeName);
        attr.setNodeValue(nodeValue);
        return attr;
    }

    private static Element createCircle(Document document, String id,
            String cx, String cy, String r, String fill, String stroke,
            String style) {

        Element elt = document.createElement("circle");
        elt.getAttributes().setNamedItem(createAttribute(document, "id", id));
        elt.getAttributes().setNamedItem(createAttribute(document, "cx", cx));
        elt.getAttributes().setNamedItem(createAttribute(document, "cy", cy));
        elt.getAttributes().setNamedItem(createAttribute(document, "r", r));
        elt.getAttributes().setNamedItem(createAttribute(document, "fill", fill));
        elt.getAttributes().setNamedItem(createAttribute(document, "stroke", stroke));
        elt.getAttributes().setNamedItem(createAttribute(document, "style", style));

        return elt;
    }

    private static Element createPath(Document document, String id, String d,
            String fill, String style) {

        Element elt = document.createElement("path");
        elt.getAttributes().setNamedItem(createAttribute(document, "id", id));
        elt.getAttributes().setNamedItem(createAttribute(document, "d", d));
        elt.getAttributes().setNamedItem(createAttribute(document, "fill", fill));
        elt.getAttributes().setNamedItem(createAttribute(document, "style", style));

        return elt;
    }

    private static Element createRect(Document document, String id, String x,
            String y, String width, String height, String fill, String style) {

        //Set default attributes tu the canvas
        Element elt = document.createElement("rect");
        elt.getAttributes().setNamedItem(createAttribute(document, "id", id));
        elt.getAttributes().setNamedItem(createAttribute(document, "width", width));
        elt.getAttributes().setNamedItem(createAttribute(document, "height", height));
        elt.getAttributes().setNamedItem(createAttribute(document, "x", x));
        elt.getAttributes().setNamedItem(createAttribute(document, "y", y));
        elt.getAttributes().setNamedItem(createAttribute(document, "style", style));
        elt.getAttributes().setNamedItem(createAttribute(document, "fill", fill));

        return elt;
    }

    private static Element createLine(Document document, String id, String x1,
            String x2, String y1, String y2, String stroke, String style) {

        //Set default attributes tu the canvas
        Element elt = document.createElement("line");
        elt.getAttributes().setNamedItem(createAttribute(document, "id", id));
        elt.getAttributes().setNamedItem(createAttribute(document, "x1", x1));
        elt.getAttributes().setNamedItem(createAttribute(document, "x2", x2));
        elt.getAttributes().setNamedItem(createAttribute(document, "y1", y1));
        elt.getAttributes().setNamedItem(createAttribute(document, "y2", y2));
        elt.getAttributes().setNamedItem(createAttribute(document, "style", style));
        elt.getAttributes().setNamedItem(createAttribute(document, "stroke", stroke));

        return elt;
    }

    private static void createBufferGridLines(Document document, Double bufferOriginX, Double bufferOriginY, Double bufferMaxX, Double bufferMaxY) {
        //Enlarge the existing grid lines to the canvas buffer size
        //To calculation minx, miny, maxx, maxy we are agreed that lines are correctly arranged before adding buffer grid lines
        // in the first time the vertical lines are defined in a range order
        // in a second time the horizontal lines are defined in an inverse range order
        // Hera a TimeSeries chart grid lines example : 
                    /*<g id="canvasGrid" stroke-linecap="butt" fill="white" text-rendering="optimizeLegibility" font-family="sans-serif" stroke-linejoin="bevel" stroke-dasharray="2,2"  stroke="white" stroke-width="0.5" stroke-miterlimit="0">
        <line fill="none" x1="532.674" x2="532.674" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="616.1007" x2="616.1007" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="699.5653" x2="699.5653" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="781.1406" x2="781.1406" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="863.6606" x2="863.6606" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="947.0874" x2="947.0874" y1="-618.7656000000001" y2="1338.453"/>
        <line fill="none" x1="1030.5519" x2="1030.5519" y1="-618.7656000000001" y2="1338.453"/>                            
        ...... other vertival lines
        <line fill="none" x1="-852.9707" x2="2069.6503" y1="685.5462" y2="685.5462"/>
        <line fill="none" x1="-852.9707" x2="2069.6503" y1="667.0812" y2="667.0812"/>
        <line fill="none" x1="-852.9707" x2="2069.6503" y1="648.6161" y2="648.6161"/>
        <line fill="none" x1="-852.9707" x2="2069.6503" y1="630.1511" y2="630.1511"/>
        <line fill="none" x1="-852.9707" x2="2069.6503" y1="611.6861" y2="611.6861"/>
        ...... other horizontal lines
        </g>*/
        Double minX = null;
        Double maxX = null;
        Double minY = null;
        Double maxY = null;
        Double intervalX = null;
        Double intervalY = null;
        Node canvasGrid = document.getElementById("canvasGrid");
        if (canvasGrid.hasChildNodes()) {
            NodeList gridLines = canvasGrid.getChildNodes();
            for (int j = 0; j < gridLines.getLength(); j++) {
                Node line = gridLines.item(j);
                if (line.getNodeName().equals("line")) {
                    NamedNodeMap lineAttr = line.getAttributes();
                    lineAttr.removeNamedItem("clip-path");
                    if (lineAttr.getNamedItem("x1").getNodeValue().equals(lineAttr.getNamedItem("x2").getNodeValue())) {

                        //Calcul min x value for an  vertical lines
                        if (minX == null) {
                            minX = Double.valueOf(lineAttr.getNamedItem("x1").getNodeValue());
                        }
                        //Calcul interval between 2 vertical lines
                        if (intervalX == null && gridLines.item(j + 1) != null) {
                            intervalX = Double.valueOf(gridLines.item(j + 1).getAttributes().getNamedItem("x1").getNodeValue()) - Double.valueOf(lineAttr.getNamedItem("x1").getNodeValue());                        //Calcul max x value for an  vertical lines
                        }
                        //Calcul max x value for an  vertical lines
                        if (maxX == null && gridLines.item(j + 1) != null && gridLines.item(j + 1).getAttributes().getNamedItem("y1").getNodeValue().equals(gridLines.item(j + 1).getAttributes().getNamedItem("y2").getNodeValue())) {
                            maxX = Double.valueOf(lineAttr.getNamedItem("x1").getNodeValue());

                        }
                        //Enlarge x attributes to buffer height
                        lineAttr.setNamedItem(createAttribute(document, "y1", String.valueOf(bufferOriginY)));
                        lineAttr.setNamedItem(createAttribute(document, "y2", String.valueOf(bufferMaxY)));

                    } else if (lineAttr.getNamedItem("y1").getNodeValue().equals(lineAttr.getNamedItem("y2").getNodeValue())) {

                        //Calcul min y value for an  horizontal lines
                        if (maxY == null) {
                            maxY = Double.valueOf(lineAttr.getNamedItem("y1").getNodeValue());                        //Calcul interval between 2 horizontal lines
                        }
                        if (intervalY == null && gridLines.item(j + 1) != null) {
                            intervalY = Double.valueOf(lineAttr.getNamedItem("y1").getNodeValue()) - Double.valueOf(gridLines.item(j + 1).getAttributes().getNamedItem("y1").getNodeValue());                        //Calcul max y value for an  horizontal lines
                        }
                        if (minY == null && gridLines.item(j + 1) == null) {
                            minY = Double.valueOf(lineAttr.getNamedItem("y1").getNodeValue());                        //Enlarge x attributes to buffer width
                        }
                        lineAttr.setNamedItem(createAttribute(document, "x1", String.valueOf(bufferOriginX)));
                        lineAttr.setNamedItem(createAttribute(document, "x2", String.valueOf(bufferMaxX)));
                    }
                }
            }

            if (minX != null && intervalX != null && maxX != null && minY != null && intervalY != null && maxY != null && bufferOriginX != null && bufferOriginY != null && bufferMaxX != null && bufferMaxY != null) {
                //Add vertical grid lines to the canvas buffer
                //WARNING :  when dataset is empty minx = maxx and intervalx = 0.0;
                if (maxX > minX && intervalX > 0) {
                    while ((minX - intervalX) >= bufferOriginX) {
                        minX = minX - intervalX;
                        canvasGrid.appendChild(createLine(document, "", String.valueOf(minX),
                                String.valueOf(minX), String.valueOf(bufferOriginY),
                                String.valueOf(bufferMaxY), "", ""));
                    }
                    while ((maxX + intervalX) <= bufferMaxX) {
                        maxX = maxX + intervalX;
                        canvasGrid.appendChild(createLine(document, "", String.valueOf(maxX),
                                String.valueOf(maxX), String.valueOf(bufferOriginY),
                                String.valueOf(bufferMaxY), "", ""));
                    }
                }

                //Add horizontal grid lines to the canvas buffer                
                if (maxY > minY && intervalY > 0) {
                    while ((minY - intervalY) >= bufferOriginY) {
                        minY = minY - intervalY;
                        canvasGrid.appendChild(createLine(document, "", String.valueOf(bufferOriginX),
                                String.valueOf(bufferMaxX), String.valueOf(minY),
                                String.valueOf(minY), "", ""));
                    }
                    while ((maxY + intervalY) <= bufferMaxY) {
                        maxY = maxY + intervalY;
                        canvasGrid.appendChild(createLine(document, "", String.valueOf(bufferOriginX),
                                String.valueOf(bufferMaxX), String.valueOf(maxY), String.valueOf(maxY), "", ""));
                    }
                }
            }
        }

    }

    private static Node findCanvasContainer(Document document) {
        NodeList rectNodes = document.getElementsByTagNameNS(ChartConstants.SVG_NS, "rect");

        Node canvasContainer = null;
        if (rectNodes.getLength() > 0 && rectNodes.item(rectNodes.getLength() - 1) != null && rectNodes.item(rectNodes.getLength() - 1).getParentNode().getChildNodes().getLength() == 1) {
            canvasContainer = rectNodes.item(rectNodes.getLength() - 1);
        }

        for (int i = 0; i < rectNodes.getLength() - 1; i++) {
            if ((rectNodes != null) && (rectNodes.item(i) != null) && (canvasContainer != null) && (rectNodes.item(i).getAttributes() != null) && (canvasContainer.getAttributes() != null) && (canvasContainer.getAttributes().getNamedItem("width").getNodeValue().equals(rectNodes.item(i).getAttributes().getNamedItem("width").getNodeValue())) && (canvasContainer.getAttributes().getNamedItem("height").getNodeValue().equals(rectNodes.item(i).getAttributes().getNamedItem("height").getNodeValue())) && (canvasContainer.getAttributes().getNamedItem("x").getNodeValue().equals(rectNodes.item(i).getAttributes().getNamedItem("x").getNodeValue())) && (canvasContainer.getAttributes().getNamedItem("y").getNodeValue().equals(rectNodes.item(i).getAttributes().getNamedItem("y").getNodeValue()))) {
                rectNodes.item(i).getParentNode().removeChild(rectNodes.item(i));
            }
        }
        return canvasContainer;
    }

//    private static void removeUselessChild(Node gNode) {
//
//        if (gNode.hasChildNodes()) {
//            NodeList nodes = gNode.getChildNodes();
//            for (int i = 0; i < nodes.getLength(); i++) {
//                //When we creates a Circle , batik trasnform it to 2 <PATH>, so I remove one path to prevent smooth dragging
//                if ((nodes.item(i).getNodeName().equals("path")) && (nodes.item(i).getAttributes().getNamedItem("fill") != null) && (nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue().equals("none"))) {
//                    gNode.removeChild(nodes.item(i));
//                }
//            }
//        }
//    }
    /**
     * Zooms in on an anchor point (specified in screen coordinate space).
     *
     * @param x  the x value (in screen coordinates).
     * @param y  the y value (in screen coordinates).
     */
    public static void zoomInBoth(double x, double y, JFreeChart chart, double zoomInFactor, boolean zoomAroundAnchor, ChartRenderingInfo info) {
        Plot plot = chart.getPlot();
        if (plot == null) {
            return;
        }
        // here we tweak the notify flag on the plot so that only
        // one notification happens even though we update multiple
        // axes...
//        boolean savedNotify = plot.isNotify();
//        plot.setNotify(false);
        zoomInDomain(x, y, chart, zoomInFactor, zoomAroundAnchor, info);
        zoomInRange(x, y, chart, zoomInFactor, zoomAroundAnchor, info);
//        plot.setNotify(savedNotify);
    }

    /**
     * Decreases the length of the domain axis, centered about the given
     * coordinate on the screen.  The length of the domain axis is reduced
     * by the value of {@link #getZoomInFactor()}.
     *
     * @param x  the x coordinate (in screen coordinates).
     * @param y  the y-coordinate (in screen coordinates).
     */
    public static void zoomInDomain(double x, double y, JFreeChart chart, double zoomInFactor, boolean zoomAroundAnchor, ChartRenderingInfo info) {
        Plot plot = chart.getPlot();
        if (plot instanceof Zoomable) {
            // here we tweak the notify flag on the plot so that only
            // one notification happens even though we update multiple
            // axes...
//            boolean savedNotify = plot.isNotify();
//            plot.setNotify(false);
            Zoomable z = (Zoomable) plot;
            z.zoomDomainAxes(zoomInFactor, info.getPlotInfo(),
                    translateScreenToJava2D(new Point((int) x, (int) y)),
                    zoomAroundAnchor);
//            plot.setNotify(savedNotify);
        }
    }

    /**
     * Decreases the length of the range axis, centered about the given
     * coordinate on the screen.  The length of the range axis is reduced by
     * the value of {@link #getZoomInFactor()}.
     *
     * @param x  the x-coordinate (in screen coordinates).
     * @param y  the y coordinate (in screen coordinates).
     */
    public static void zoomInRange(double x, double y, JFreeChart chart, double zoomInFactor, boolean zoomAroundAnchor, ChartRenderingInfo info) {
        Plot plot = chart.getPlot();
        if (plot instanceof Zoomable) {
            // here we tweak the notify flag on the plot so that only
            // one notification happens even though we update multiple
            // axes...
//            boolean savedNotify = plot.isNotify();
//            plot.setNotify(false);
            Zoomable z = (Zoomable) plot;
            z.zoomRangeAxes(zoomInFactor, info.getPlotInfo(),
                    translateScreenToJava2D(new Point((int) x, (int) y)),
                    zoomAroundAnchor);
//            plot.setNotify(savedNotify);
        }
    }

//    /**
//     * Zooms out on an anchor point (specified in screen coordinate space).
//     *
//     * @param x  the x value (in screen coordinates).
//     * @param y  the y value (in screen coordinates).
//     */
//    public void zoomOutBoth(double x, double y) {
//        Plot plot = this.chart.getPlot();
//        if (plot == null) {
//            return;
//        }
//        // here we tweak the notify flag on the plot so that only
//        // one notification happens even though we update multiple
//        // axes...
//        boolean savedNotify = plot.isNotify();
//        plot.setNotify(false);
//        zoomOutDomain(x, y);
//        zoomOutRange(x, y);
//        plot.setNotify(savedNotify);
//    }
//
//    /**
//     * Increases the length of the domain axis, centered about the given
//     * coordinate on the screen.  The length of the domain axis is increased
//     * by the value of {@link #getZoomOutFactor()}.
//     *
//     * @param x  the x coordinate (in screen coordinates).
//     * @param y  the y-coordinate (in screen coordinates).
//     */
//    public void zoomOutDomain(double x, double y) {
//        Plot plot = this.chart.getPlot();
//        if (plot instanceof Zoomable) {
//            // here we tweak the notify flag on the plot so that only
//            // one notification happens even though we update multiple
//            // axes...
//            boolean savedNotify = plot.isNotify();
//            plot.setNotify(false);
//            Zoomable z = (Zoomable) plot;
//            z.zoomDomainAxes(this.zoomOutFactor, this.info.getPlotInfo(),
//                    translateScreenToJava2D(new Point((int) x, (int) y)),
//                    this.zoomAroundAnchor);
//            plot.setNotify(savedNotify);
//        }
//    }
//
//    /**
//     * Increases the length the range axis, centered about the given
//     * coordinate on the screen.  The length of the range axis is increased
//     * by the value of {@link #getZoomOutFactor()}.
//     *
//     * @param x  the x coordinate (in screen coordinates).
//     * @param y  the y-coordinate (in screen coordinates).
//     */
//    public void zoomOutRange(double x, double y) {
//        Plot plot = this.chart.getPlot();
//        if (plot instanceof Zoomable) {
//            // here we tweak the notify flag on the plot so that only
//            // one notification happens even though we update multiple
//            // axes...
//            boolean savedNotify = plot.isNotify();
//            plot.setNotify(false);
//            Zoomable z = (Zoomable) plot;
//            z.zoomRangeAxes(this.zoomOutFactor, this.info.getPlotInfo(),
//                    translateScreenToJava2D(new Point((int) x, (int) y)),
//                    this.zoomAroundAnchor);
//            plot.setNotify(savedNotify);
//        }
//    }
    /**
     * Translates a panel (component) location to a Java2D point.
     *
     * @param screenPoint  the screen location (<code>null</code> not
     *                     permitted).
     *
     * @return The Java2D coordinates.
     */
    public static Point2D translateScreenToJava2D(Point screenPoint) {
        //Insets insets = getInsets();
        double x = (screenPoint.getX());
        double y = (screenPoint.getY());
        return new Point2D.Double(x, y);
    }

    /**
     * Zooms in on a selected region.
     *
     * @param selection  the selected region.
     */
    public static void zoom(Rectangle2D selection, JFreeChart chart, ChartRenderingInfo info) {

        // get the origin of the zoom selection in the Java2D space used for
        // drawing the chart (that is, before any scaling to fit the panel)
        Point2D selectOrigin = translateScreenToJava2D(new Point(
                (int) Math.ceil(selection.getX()),
                (int) Math.ceil(selection.getY())));
        PlotRenderingInfo plotInfo = info.getPlotInfo();
        Rectangle2D scaledDataArea = getScreenDataArea(
                (int) selection.getCenterX(), (int) selection.getCenterY(), info);
        if ((selection.getHeight() > 0) && (selection.getWidth() > 0)) {

            double hLower = (selection.getMinX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
            double hUpper = (selection.getMaxX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
            double vLower = (scaledDataArea.getMaxY() - selection.getMaxY()) / scaledDataArea.getHeight();
            double vUpper = (scaledDataArea.getMaxY() - selection.getMinY()) / scaledDataArea.getHeight();

            Plot p = chart.getPlot();
            if (p instanceof Zoomable) {
                // here we tweak the notify flag on the plot so that only
                // one notification happens even though we update multiple
                // axes...
//                boolean savedNotify = p.isNotify();
//                p.setNotify(false);
                Zoomable z = (Zoomable) p;
                if (z.getOrientation() == PlotOrientation.HORIZONTAL) {
                    z.zoomDomainAxes(vLower, vUpper, plotInfo, selectOrigin);
                    z.zoomRangeAxes(hLower, hUpper, plotInfo, selectOrigin);
                } else {
                    z.zoomDomainAxes(hLower, hUpper, plotInfo, selectOrigin);
                    z.zoomRangeAxes(vLower, vUpper, plotInfo, selectOrigin);
                }
//                p.setNotify(savedNotify);
            }

        }

    }

    /**
     * Returns the data area for the chart (the area inside the axes) with the
     * current scaling applied (that is, the area as it appears on screen).
     *
     * @return The scaled data area.
     */
    public static Rectangle2D getScreenDataArea(ChartRenderingInfo info) {
        Rectangle2D dataArea = info.getPlotInfo().getDataArea();
//        Insets insets = getInsets();
//        double x = dataArea.getX() * this.scaleX + insets.left;
//        double y = dataArea.getY() * this.scaleY + insets.top;
//        double w = dataArea.getWidth() * this.scaleX;
//        double h = dataArea.getHeight() * this.scaleY;
        double x = dataArea.getX();
        double y = dataArea.getY();
        double w = dataArea.getWidth();
        double h = dataArea.getHeight();
        return new Rectangle2D.Double(x, y, w, h);
    }

    /**
     * Applies any scaling that is in effect for the chart drawing to the
     * given rectangle.
     *
     * @param rect  the rectangle (<code>null</code> not permitted).
     *
     * @return A new scaled rectangle.
     */
    public static Rectangle2D scale(Rectangle2D rect) {
//        Insets insets = getInsets();
//        double x = rect.getX() * getScaleX() + insets.left;
//        double y = rect.getY() * getScaleY() + insets.top;
//        double w = rect.getWidth() * getScaleX();
//        double h = rect.getHeight() * getScaleY();
        double x = rect.getX();
        double y = rect.getY();
        double w = rect.getWidth();
        double h = rect.getHeight();
        return new Rectangle2D.Double(x, y, w, h);
    }

    /**
     * Returns the data area (the area inside the axes) for the plot or subplot,
     * with the current scaling applied.
     *
     * @param x  the x-coordinate (for subplot selection).
     * @param y  the y-coordinate (for subplot selection).
     *
     * @return The scaled data area.
     */
    public static Rectangle2D getScreenDataArea(int x, int y, ChartRenderingInfo info) {
        PlotRenderingInfo plotInfo = null;
        Rectangle2D result;
        if (plotInfo.getSubplotCount() == 0) {
            result = getScreenDataArea(info);
        } else {
            // get the origin of the zoom selection in the Java2D space used for
            // drawing the chart (that is, before any scaling to fit the panel)
            Point2D selectOrigin = translateScreenToJava2D(new Point(x, y));
            int subplotIndex = plotInfo.getSubplotIndex(selectOrigin);
            if (subplotIndex == -1) {
                return null;
            }
            result = scale(plotInfo.getSubplotInfo(subplotIndex).getDataArea());
        }
        return result;
    }

    public static String getFormId(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIForm)) {
            if (parent != null) {
                parent = parent.getParent();
            } else {
                throw new IllegalStateException("You must specify a form for the mapfaces components.");
            }
        }
        return parent.getClientId(context);
    }

    /**
     * Returns a component referenced by his id.
     * @param context
     * @param root
     * @param id
     * @return component referenced by id or null if not found
     */
    public static UIComponent findComponentById(final FacesContext context,
            final UIComponent root, final String id) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            final UIComponent child = (UIComponent) root.getChildren().get(i);
            component = findComponentById(context, child, id);
        }
        if (root.getId() != null) {
            if (component == null && root.getId().equals(id)) {
                component = root;
            }
        }
        return component;
    }
}