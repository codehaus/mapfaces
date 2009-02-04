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
import java.awt.Insets;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
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
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
        // HTML colors (#FFFFFF format)
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
        if (datasource instanceof PieDataset) {
            chart = createChartWithPieDataSet(chartData);
        } else if (datasource instanceof CategoryDataset) {
            chart = createChartWithCategoryDataSet(chartData);
        } else if (datasource instanceof XYDataset) {
            chart = createChartWithXYDataSet(chartData);
        } else if (datasource instanceof List) {  //List<XYDataset>
            chart = createChartWithXYDataSet(chartData);
        } else {
            throw new RuntimeException("Unsupported chart type");
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
        chart.setBackgroundPaint(ChartUtils.getColor(chartData.getBackground2()));
        chart.getPlot().setBackgroundPaint(ChartUtils.getColor(chartData.getForeground2()));
        chart.setTitle(chartData.getTitle());
        chart.setAntiAlias(chartData.isAntialias());

        // Alpha transparency (100% means opaque)
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
        if (chartData.getDatasource() instanceof List) {
            datasets = (List<XYDataset>) chartData.getDatasource();
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
            if (dataset == null && datasets != null && (datasets.size() > 0)) {
                chart = IdentifiedChartFactory.createTimeSeriesChart("", xAxis, yAxis, datasets.get(0), legend, true, false);
                ChartUtils.addXYDataset(chart, datasets);
            } else {
                chart = ChartFactory.createTimeSeriesChart("", xAxis, yAxis, dataset, legend, true, false);
            }
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

        if (chart.getPlot() instanceof XYPlot) {
            chart.getXYPlot().setDomainGridlinesVisible(chartData.isDomainGridLines());
            chart.getXYPlot().setRangeGridlinesVisible(chartData.isRangeGridLines());

            if (chartData.getGenerateMap() != null) {
                chart.getXYPlot().getRenderer().setURLGenerator(new StandardXYURLGenerator(""));
            }
        }

        setXYSeriesColors(chart, chartData);

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

    public static void writeChartAsSVG(OutputStream stream, JFreeChart chart, int width, int height) throws SVGGraphics2DIOException, UnsupportedEncodingException {
        ChartRenderingInfo chartRenderingInfo = new ChartRenderingInfo();
        writeChartAsSVG(stream, chart, width, height, chartRenderingInfo);
    }

    public static void writeChartAsSVG(OutputStream stream, JFreeChart chart, int width, int height, ChartRenderingInfo chartRenderingInfo) throws SVGGraphics2DIOException, UnsupportedEncodingException {

        //create the SVG -------------------------------------------------------
        final DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        final String svgNS = "http://www.w3.org/2000/svg";
        final Document document = domImpl.createDocument(svgNS, "svg", null);
        boolean dynamic = true;
        Element root = document.getDocumentElement();
        /*if (dynamic) {
        doc.appendChild(addScriptTag(document));
        }*/
        final SVGGraphics2D svgGenerator = new IdentifiedSVGGraphics2D(document);
        svgGenerator.getRoot(root);
        svgGenerator.setSVGCanvasSize(new Dimension(width, height));
        chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, width, height), chartRenderingInfo);
        //root.setAttribute("onload", "loadOpenCharts('canvas');");
        root.appendChild(addOpenChartScript(document));
        root.appendChild(addInitScript(document));
        String[] jsFiles = {"js/openlayers/lib/OpenLayers/Util.js",
            "js/openlayers/lib/OpenLayers/BaseTypes.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/Class.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/Bounds.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/Element.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/LonLat.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/Pixel.js",
            "js/openlayers/lib/OpenLayers/BaseTypes/Size.js",
            "js/openlayers/lib/OpenLayers/Ajax.js",
            "js/openlayers/lib/OpenLayers/Request.js",
            "js/openlayers/lib/OpenLayers/Request/XMLHttpRequest.js",
            "js/openlayers/lib/OpenLayers/Events.js",
            "js/openlayers/lib/OpenLayers/Handler.js",
            "js/openlayers/lib/OpenLayers/Handler/Click.js",
            "js/openlayers/lib/OpenLayers/Handler/Hover.js",
            "js/openlayers/lib/OpenLayers/Handler/Point.js",
            "js/openlayers/lib/OpenLayers/Handler/Path.js",
            "js/openlayers/lib/OpenLayers/Handler/Polygon.js",
            "js/openlayers/lib/OpenLayers/Handler/Feature.js",
            "js/openlayers/lib/OpenLayers/Handler/Drag.js",
            "js/openlayers/lib/OpenLayers/Handler/RegularPolygon.js",
            "js/openlayers/lib/OpenLayers/Handler/Box.js",
            "js/openlayers/lib/OpenLayers/Handler/MouseWheel.js",
            "js/openlayers/lib/OpenLayers/Handler/Keyboard.js",
            "js/openlayers/lib/OpenLayers/Control.js",
            "js/openlayers/lib/OpenLayers/Control/Attribution.js",
            "js/openlayers/lib/OpenLayers/Control/DragPan.js",
            "js/openlayers/lib/OpenLayers/Control/ZoomBox.js",
            "js/openlayers/lib/OpenLayers/Control/Navigation.js",
            "js/openlayers/lib/OpenLayers/Control/MouseDefaults.js",
            "js/openlayers/lib/OpenLayers/Control/KeyboardDefaults.js",
            "js/openlayers/lib/OpenLayers/Control/ArgParser.js",
            "js/openlayers/lib/OpenLayers/Control/NavigationHistory.js",
            "js/openlayers/custom/OpenLayers/Map.js",
            "js/openlayers/custom/OpenLayers/Handler/MouseWheel.js",
            "js/openlayers/custom/OpenLayers/Control/Navigation.js",
            "js/openlayers/custom/OpenLayers/Control/MousePosition.js",
            "js/openlayers/custom/OpenLayers/Control/MouseWheelDefaults.js"
        };
        addScript(document, jsFiles);
        root.appendChild(svgGenerator.getRoot().getChildNodes().item(2));
        //Add a draggable <rect> element with the same width and height as his parent 
        NodeList gNodes = root.getElementsByTagName("g");

        for (int i = 0; i < gNodes.getLength(); i++) {
//            System.out.println(gNodes.getLength());
//            System.out.println(gNodes.item(i).hasChildNodes());
            if (gNodes.item(i).hasChildNodes() && (gNodes.item(i).getFirstChild().getAttributes().getNamedItem("serie") != null)) {

                //Set default attributes tu the canvas
                Attr id = document.createAttribute("id");
                id.setNodeValue("canvas");
                Attr widthAttr = document.createAttribute("width");
                widthAttr.setNodeValue(String.valueOf(width));
                Attr heightAttr = document.createAttribute("height");
                heightAttr.setNodeValue(String.valueOf(height));
                Attr xAttr = document.createAttribute("x");
                xAttr.setNodeValue(String.valueOf(0));
                Attr yAttr = document.createAttribute("y");
                yAttr.setNodeValue(String.valueOf(0));
                Attr style = document.createAttribute("style");
                style.setNodeValue("cursor:move;");
//                Attr dragEnable = document.createAttribute( "drag:enable");
//                dragEnable.setNodeValue("true");   
                gNodes.item(i).getAttributes().setNamedItem(id);
                gNodes.item(i).getAttributes().setNamedItem(widthAttr);
                gNodes.item(i).getAttributes().setNamedItem(heightAttr);
                gNodes.item(i).getAttributes().setNamedItem(xAttr);
                gNodes.item(i).getAttributes().setNamedItem(yAttr);
                gNodes.item(i).getAttributes().setNamedItem(style);
                //removeUselessChild(gNodes.item(i));
                //Add rectangle and set as first child
                /*Element rect = document.createElement("rect");
                rect.setAttribute("width", String.valueOf(width));
                rect.setAttribute("height", String.valueOf(height));
                rect.setAttribute("fill", "blue");
                rect.setAttribute("opacity", "0.1");*/

                gNodes.item(i).insertBefore(gNodes.item(i - 1), gNodes.item(i).getFirstChild());
                i--;
                Node rectangleContainer = findGraphContainer(document);
                if (rectangleContainer != null) {
                    Node oldContainerParent = rectangleContainer.getParentNode();
                    Node fill = document.createAttribute("fill");
                    fill.setNodeValue("silver");
                    Attr idRect = document.createAttribute("id");
                    idRect.setNodeValue("canvasRect");
                    rectangleContainer.getAttributes().setNamedItem(fill);
                    rectangleContainer.getAttributes().setNamedItem(idRect);
                    gNodes.item(i).insertBefore(rectangleContainer, gNodes.item(i).getFirstChild());
                    gNodes.item(i).getParentNode().insertBefore(gNodes.item(i), gNodes.item(i).getParentNode().getFirstChild().getNextSibling());
                    /*<clippath clipPathUnits="userSpaceOnUse" id="clipPath1">
                    <path d="M0 0 L600 0 L600 400 L0 400 L0 0 Z"/>
                    </clippath>*/
                    
                    /*Attr idClip = document.createAttribute("id");
                    idClip.setNodeValue("clipPath0");
                    Attr units = document.createAttribute("clipPathUnits");
                    units.setNodeValue("userSpaceOnUse");
                    Node clipPath = document.createElement("clippath");
                    clipPath.getAttributes().setNamedItem(idClip);
                    clipPath.getAttributes().setNamedItem(units);
                    */
                    Node path = document.createElement("path");
                    Attr d = document.createAttribute("d");
                    d.setNodeValue("M0 0 L600 0 L600 400 L0 400 L0 0 Z M104.5566 33.6406 L104.5566 222.7656 L495.4434 222.7656 L495.4434 33.6406 Z");
                    path.getAttributes().setNamedItem(d);
                    //clipPath.appendChild(path);          
                    
                    //gNodes.item(i).getParentNode().getFirstChild().appendChild(clipPath);
                    
                    //Attr clipPathAttr = document.createAttribute("clip-path");
                    //clipPathAttr.setNodeValue("url(#clipPath0)");
                    //gNodes.item(i).getParentNode().getFirstChild().getNextSibling().getNextSibling().getFirstChild().getAttributes().setNamedItem(clipPathAttr);
                    gNodes.item(i).getParentNode().getFirstChild().getNextSibling().getNextSibling().replaceChild(
                            path
                            , gNodes.item(i).getParentNode().getFirstChild().getNextSibling().getNextSibling().getFirstChild());
                   
                }
                //gNodes.item(i).getParentNode().insertBefore(gNodes.item(i),);
                //Remove useless child
                //System.out.println (gNodes.item(i).getAttributes().getNamedItem("id"));
                //Copy grid node into the canvas node
                break;
            }
        }
        if (dynamic) {
            root.appendChild(addTextTag(document));
            root.appendChild(addEffects(document));
            // doc.appendChild(addNavigation(document));            
            root.appendChild(addInitScript(document));
        }

        svgGenerator.stream(root, new OutputStreamWriter(stream, "UTF-8"));
//        Document svgDom = svgGenerator.getDOMFactory();
//        System.out.println(svgDom.getDocumentElement().getChildNodes().getLength());
//        svgDom.getDocumentElement().appendChild(addScriptTag(svgGenerator.getDOMFactory()));
//        System.out.println(svgDom.getDocumentElement().getChildNodes().getLength());


    }

    private static Node addOpenChartScript(Document document) {
        Element script = document.createElement("script");
        script.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "js/openlayers/custom/OpenLayers.js");
        return script;
    }

    private static Node addInitScript(Document document) {
        Element script = document.createElement("script");
        script.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "js/init.js");
        return script;
    }

    private static void addScript(Document document, String[] jsFiles) {
        Element script = document.createElement("script");
        for (int i = 0; i < jsFiles.length; i++) {
            Element clone = (Element) script.cloneNode(false);
            clone.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", jsFiles[i]);
            document.getDocumentElement().appendChild(clone);
        }
    }

    private static void addXYDataset(JFreeChart chart, List<XYDataset> datasets) {
        try {
            XYPlot plot = chart.getXYPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);

            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6), false);

            // AXIS 2
            NumberAxis axis2 = new NumberAxis("Range Axis 2");
            axis2.setAutoRangeIncludesZero(false);
            axis2.setLabelPaint(Color.red);
            axis2.setTickLabelPaint(Color.red);
            plot.setRangeAxis(1, axis2);
            plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);

            XYDataset dataset2 = datasets.get(1);
            plot.setDataset(1, dataset2);
            plot.mapDatasetToRangeAxis(1, new Integer(1));
            plot.setRenderer(1, (XYItemRenderer) renderer.clone());
            plot.getRenderer(1).setSeriesPaint(0, Color.red);

            // AXIS 3
            NumberAxis axis3 = new NumberAxis("Range Axis 3");
            axis3.setLabelPaint(Color.blue);
            axis3.setTickLabelPaint(Color.blue);
            plot.setRangeAxis(2, axis3);

            XYDataset dataset3 = datasets.get(2);
            plot.setDataset(2, dataset3);
            plot.mapDatasetToRangeAxis(2, new Integer(2));
            plot.setRenderer(2, (XYItemRenderer) renderer.clone());
            plot.getRenderer(2).setSeriesPaint(0, Color.blue);

            // AXIS 4
            NumberAxis axis4 = new NumberAxis("Range Axis 4");
            axis4.setLabelPaint(Color.green);
            axis4.setTickLabelPaint(Color.green);
            plot.setRangeAxis(3, axis4);

            XYDataset dataset4 = datasets.get(3);
            plot.setDataset(3, dataset4);
            plot.mapDatasetToRangeAxis(3, new Integer(3));

            plot.setRenderer(3, (XYItemRenderer) renderer.clone());
            plot.getRenderer(3).setSeriesPaint(0, Color.green);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ChartUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Element addTextTag(Document document) {
        Element text = document.createElement("text");
        text.setAttribute("id", "text");
        text.appendChild(document.createTextNode("X:Y:"));
        return text;
    }

    private static Element addEffects(Document document) {
        Element script = document.createElement("script");
        script.appendChild(document.createCDATASection("\n" +
                "var svgDocument = null;\n" +
                "svgDocument = window.document;\n" +
                "//alert(svgDocument.getElementsByTagName('rect').length);\n" +
                "\n" +
                "// definit une nouvelle propriete window reference top\n" +
                "top.addEvents = addEvents;\n" +
                "             // definition de la methode\n" +
                "             function addEvents () {\n" +
                "   var list = svgDocument.getElementsByTagName('path');\n" +
                "                   for(var i=0;i<list.length;i++){" +
                "       if(i == list.length-1 && list[i].parentNode.nodeName == 'g')" +
                "           list[i].parentNode.setAttribute('id','canvas');" +
                "                        var rectangle = list[i];   \n" +
                "                        rectangle.addEventListener('mouseover',highlight,false);\n" +
                "                        rectangle.addEventListener('mouseout',unhighlight,false);\n" +
                "                        //rectangle.addEventListener('mousemove',moveText,false);\n" +
                "                            " +
                "                   }" +
                "             }\n" +
                "             function moveText(evt) {" +
                " var text = svgDocument.getElementById('text');\n" +
                "                 if (text == null){alert('text is null');   \n" +
                "     var text = svgDocument.createElement('text');   \n" +
                "     evt.target.parentNode.appendChild(text); \n" +
                "     text.setAttribute('id','text'); \n" +
                "     text.setAttribute('width','200'); \n" +
                "     text.setAttribute('height','100'); \n" +
                "     text.setAttribute('fill','red'); \n" +
                "     text.appendChild(document.createTextNode('X: Y: stronzo')); \n" +
                " }" +
                "                 text.setAttribute('style','display:block;');\n" +
                "                 text.setAttribute('x',evt.clientX+10);\n" +
                "                 text.setAttribute('y',evt.clientY-10);\n" +
                "                 if(text.firstChild.data)\n" +
                "                      text.firstChild.data = 'X: '+evt.target.getAttribute('xValue')+',Y: '+parseInt(evt.target.getAttribute('yValue'));\n" +
                "                 else if(text.firstChild.nodeValue)\n" +
                "                      text.firstChild.nodeValue= 'X: '+evt.target.getAttribute('xValue')+',Y: '+parseInt(evt.target.getAttribute('yValue'));\n" +
                "             }" +
                "             function displayText(evt) {\n" +
                "                  var text = svgDocument.getElementById('text');   \n" +
                "                  if(text.getAttribute('style').indexOf('none;')!=-1){\n" +
                "                        text.setAttribute('style','display:block;');\n" +
                "                  } else{  text.setAttribute('style','display:none;');}\n" +
                "             }\n" +
                "             // methode ajoutee si element rectangle\n" +
                "             function highlight(evt) {\n" +
                "                 //evt.target.setAttribute('default-fill',evt.target.getAttribute('fill'));  \n" +
                "                 //evt.target.setAttribute('fill','yellow');\n" +
                "svgDocument.getElementById('text').style.display='block';moveText(evt);\n" +
                " if (evt.target.getAttribute('stroke') == 'none') {\n" +
                "                     evt.target.setAttribute('default-stroke',evt.target.getAttribute('stroke'));  \n" +
                "                     evt.target.setAttribute('stroke','yellow');\n" +
                " }" +
                "                 evt.target.setAttribute('default-stroke-width',evt.target.getAttribute('stroke-width'));  \n" +
                "                 evt.target.setAttribute('stroke-width','4');\n" +
                "             }\n" +
                "             function unhighlight(evt) {\n" +
                "                 //evt.target.setAttribute('fill',evt.target.getAttribute('default-fill'));  \n" +
                "svgDocument.getElementById('text').style.display='none';\n" +
                "                 if (evt.target.getAttribute('default-stroke'))\n" +
                "     evt.target.setAttribute('stroke',evt.target.getAttribute('default-stroke'));  \n" +
                "                 evt.target.setAttribute('stroke-width',evt.target.getAttribute('default-stroke-width'));  \n" +
                "             }\n" +
                "            loadOpenCharts('canvas');\n" +
                "            addEvents();" +
                "\n" +
                "        "));
        return script;
    }

    private static Node findGraphContainer(Document document) {
        NodeList rectNodes = document.getElementsByTagNameNS("http://www.w3.org/2000/svg", "rect");
//        System.out.println(rectNodes.toString());
//        System.out.println(rectNodes.getLength());
//        System.out.println(rectNodes.item(0).toString());
        for (int i = 0; i < rectNodes.getLength(); i++) {
            if ((rectNodes != null) && (rectNodes.item(i) != null) && (rectNodes.item(i).getNextSibling() != null) && (rectNodes.item(i).getNextSibling().getNextSibling() != null) && (rectNodes.item(i).getAttributes() != null) && (rectNodes.item(i).getAttributes().getNamedItem("width") != null) && (rectNodes.item(i).getAttributes().getNamedItem("height") != null) && (rectNodes.item(i).getAttributes().getNamedItem("x") != null) && (rectNodes.item(i).getAttributes().getNamedItem("y") != null) && (rectNodes.item(i).getNextSibling().getNodeName().equals("line")) && (rectNodes.item(i).getNextSibling().getNextSibling().getNodeName().equals("text"))) {
                return rectNodes.item(i);
            }
        }
        return null;
    }

    private static void removeUselessChild(Node gNode) {

        if (gNode.hasChildNodes()) {
            NodeList nodes = gNode.getChildNodes();
//             System.out.println(nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++) {
                //When we creates a Circle , batik trasnform it to 2 <PATH>, so I remove one path to prevent smooth dragging
//                  System.out.println (gNode.getAttributes().getNamedItem("id"));
                if ((nodes.item(i).getNodeName().equals("path")) && (nodes.item(i).getAttributes().getNamedItem("fill") != null) && (nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue().equals("none"))) {
                    gNode.removeChild(nodes.item(i));
                }
            }
        }
    }

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
}