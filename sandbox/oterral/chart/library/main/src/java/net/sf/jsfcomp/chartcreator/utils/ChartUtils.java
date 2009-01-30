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
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import java.util.List;
import java.util.Locale;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
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
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
            throw new RuntimeException("Unsupported plot orientation:\n"+ orientation);
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
            throw new RuntimeException("Unsupported chart color:\n"+ color);
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
            throw new RuntimeException("Unsupported output format:\n"+ output);
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
        chart.setBackgroundPaint(ChartUtils.getColor(chartData.getBackground()));
        chart.getPlot().setBackgroundPaint(ChartUtils.getColor(chartData.getForeground()));
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
                chart = ChartFactory.createBarChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
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
                chart = IdentifiedChartFactory.createPieChart("", dataset,  true, false, Locale.FRENCH);
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
        if(chartData.getDatasource() instanceof List)            
            datasets = (List<XYDataset>) chartData.getDatasource();   
        else
            dataset = (XYDataset) chartData.getDatasource();
        String type = chartData.getType();
        String xAxis = chartData.getXlabel();
        String yAxis = chartData.getYlabel();
        boolean legend = chartData.isLegend();

        JFreeChart chart = null;
        PlotOrientation plotOrientation = ChartUtils.getPlotOrientation(chartData.getOrientation());

        if (type.equalsIgnoreCase("timeseries")) {
            if (dataset == null && datasets != null && (datasets.size() > 0)) {
                chart = ChartFactory.createTimeSeriesChart("", xAxis, yAxis, datasets.get(0), legend, true, false);
                ChartUtils.addXYDataset(chart, datasets);
            } else chart = ChartFactory.createTimeSeriesChart("", xAxis, yAxis, dataset, legend, true, false);
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
        Element doc = document.getDocumentElement();
        /*if (dynamic) {
            doc.appendChild(addScriptTag(document));
        }*/
        final SVGGraphics2D svgGenerator = new IdentifiedSVGGraphics2D(document);
        svgGenerator.getRoot(doc);
        svgGenerator.getRoot().appendChild(addScriptTag(svgGenerator.getDOMFactory()));
        svgGenerator.setSVGCanvasSize(new Dimension(width, height));
        chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, width, height), chartRenderingInfo);
        doc.appendChild(svgGenerator.getRoot().getChildNodes().item(2));
         if (dynamic) {
            doc.appendChild(addTextTag(document));
            doc.appendChild(addScriptTag(document));
        }
        
        svgGenerator.stream(doc, new OutputStreamWriter(stream, "UTF-8"));
//        Document svgDom = svgGenerator.getDOMFactory();
//        System.out.println(svgDom.getDocumentElement().getChildNodes().getLength());
//        svgDom.getDocumentElement().appendChild(addScriptTag(svgGenerator.getDOMFactory()));
//        System.out.println(svgDom.getDocumentElement().getChildNodes().getLength());
        

    }

    private static void addXYDataset(JFreeChart chart, List<XYDataset> datasets) {
        XYPlot plot = chart.getXYPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        
        XYItemRenderer renderer = (XYItemRenderer) plot.getRenderer();
        renderer.setBasePaint(Color.black);
       
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
        plot.setRenderer(1, new StandardXYItemRenderer());
        plot.getRenderer(1).setSeriesPaint(0, Color.red);
        
        // AXIS 3
        NumberAxis axis3 = new NumberAxis("Range Axis 3");
        axis3.setLabelPaint(Color.blue);
        axis3.setTickLabelPaint(Color.blue);
        plot.setRangeAxis(2, axis3);

        XYDataset dataset3 = datasets.get(2);
        plot.setDataset(2, dataset3);
        plot.mapDatasetToRangeAxis(2, new Integer(2));        
        plot.setRenderer(2, new StandardXYItemRenderer());
        plot.getRenderer(2).setSeriesPaint(0, Color.blue);

        // AXIS 4        
        NumberAxis axis4 = new NumberAxis("Range Axis 4");
        axis4.setLabelPaint(Color.green);
        axis4.setTickLabelPaint(Color.green);
        plot.setRangeAxis(3, axis4);
        
        XYDataset dataset4 = datasets.get(3);
        plot.setDataset(3, dataset4);
        plot.mapDatasetToRangeAxis(3, new Integer(3));
        
        plot.setRenderer(3, new StandardXYItemRenderer());
        plot.getRenderer(3).setSeriesPaint(0, Color.green);     
    }
    
    private static Element addTextTag(Document document) {
        Element text = document.createElement("text");
        text.setAttribute("id", "text");
        text.appendChild(document.createTextNode("X:Y:"));
        return text;
    }
    private static Element addScriptTag(Document document) {
        Element script = document.createElement("script");
        script.setAttribute("type", "text/ecmascript");
        script.appendChild(document.createCDATASection("\n"+
                "var svgDocument = null;\n"+
             "svgDocument = window.document;\n"+
             "//alert(svgDocument.documentElement.getElementsByTagName('rect').length);\n"+
"\n"+
             "// definit une nouvelle propriete window reference top\n"+
             "top.addEvents = addEvents;\n"+

"             // definition de la methode\n"+
"             function addEvents () {\n"+
                "   var list = svgDocument.documentElement.getElementsByTagName('polygon')\n"+
"                   for(var i=0;i<list.length;i++){"+
"                            var rectangle = list[i];   \n"+
"                            rectangle.addEventListener('mouseover',highlight,false);\n"+
"                            rectangle.addEventListener('mouseout',unhighlight,false);\n"+
"                            rectangle.addEventListener('mousemove',moveText,false);\n"+
"                            "+
"                   }"+
"             }\n"+
"             function moveText(evt) {" +
                " var text = svgDocument.getElementById('text');\n"+
"                 if (text == null){alert('text is null');   \n" +
                "     var text = svgDocument.createElement('text');   \n" +
                "     evt.target.parentNode.appendChild(text); \n" +
                "     text.setAttribute('id','text'); \n" +
                "     text.setAttribute('width','200'); \n" +
                "     text.setAttribute('height','100'); \n" +
                "     text.setAttribute('fill','red'); \n" +
                "     text.appendChild(document.createTextNode('X: Y: stronzo')); \n" +
                " }"+
"                 text.setAttribute('style','display:block;');\n"+
"                 text.setAttribute('x',evt.clientX+10);\n"+
"                 text.setAttribute('y',evt.clientY-10);\n" +
"                 if(text.firstChild.data)\n"+
"                      text.firstChild.data = 'X: '+evt.clientX+',Y: '+evt.clientY;\n"+
"                 else if(text.firstChild.nodeValue)\n"+
"                      text.firstChild.nodeValue= 'X: '+evt.clientX+',Y: '+evt.clientY;\n"+
"             }"+
"             function displayText(evt) {\n"+
"                  var text = svgDocument.getElementById('text');   \n"+
"                  if(text.getAttribute('style').indexOf('none;')!=-1){\n"+
"                        text.setAttribute('style','display:block;');\n"+
"                  } else{  text.setAttribute('style','display:none;');}\n"+
"             }\n"+
"             // methode ajoutee si element rectangle\n"+
"             function highlight(evt) {\n"+
"                 //evt.target.setAttribute('default-fill',evt.target.getAttribute('fill'));  \n"+
"                 //evt.target.setAttribute('fill','yellow');\n" +
                " if (evt.target.getAttribute('stroke') == 'none') {\n" +
"                     evt.target.setAttribute('default-stroke',evt.target.getAttribute('stroke'));  \n"+
"                     evt.target.setAttribute('stroke','red');\n" +
                " }"+
"                 evt.target.setAttribute('default-stroke-width',evt.target.getAttribute('stroke-width'));  \n"+
"                 evt.target.setAttribute('stroke-width','4');\n"+
"             }\n"+
"             function unhighlight(evt) {\n"+
"                 //evt.target.setAttribute('fill',evt.target.getAttribute('default-fill'));  \n"+
"                 if (evt.target.getAttribute('default-stroke'))\n" +
                "     evt.target.setAttribute('stroke',evt.target.getAttribute('default-stroke'));  \n"+
"                 evt.target.setAttribute('stroke-width',evt.target.getAttribute('default-stroke-width'));  \n"+
"             }\n"+
                
"            addEvents();\n"+
"        "));
        return script;
    }
}