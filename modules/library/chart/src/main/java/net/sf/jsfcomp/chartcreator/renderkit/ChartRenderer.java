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

package net.sf.jsfcomp.chartcreator.renderkit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseListener;
import javax.faces.render.Renderer;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.CategoryLabelEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;

import net.sf.jsfcomp.chartcreator.component.UIChart;
import net.sf.jsfcomp.chartcreator.model.ChartData;
import net.sf.jsfcomp.chartcreator.utils.ChartConstants;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import net.sf.jsfcomp.chartcreator.utils.ChartUtils;
import org.mapfaces.share.listener.DetectBrowserListener;
import org.mapfaces.util.FacesUtils;

/**
 * @author Cagatay Civici (latest modification by $Author: cagatay_civici $)
 * @version $Revision: 745 $ $Date: 2007-05-08 10:16:19 +0300 (Tue, 08 May 2007) $
 * 
 * Renders the img tag and refers to an image that is generated at serverside
 */
public class ChartRenderer extends Renderer {

    boolean embed = false;
    String PIXEL = null;

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIChart chart = (UIChart) component;
        boolean isIE = FacesUtils.isIEBrowser(context);      
        
        setChartDataAtSession(context, chart);

        String clientId = chart.getClientId(context);
        String formId = ChartUtils.getFormId(context, component);
        String jsSuffix = clientId.replace(":","");
        String onloadFunc = new StringBuffer("function onLoad").append(jsSuffix).append("(chartId, formId, ajaxCompId) { if(window.setMfIds)window.setMfIds(chartId, formId, ajaxCompId);var elt = document.getElementById(chartId);if (elt) elt.style.display='block';while(document.getElementById(chartId+'clone') != null)  elt.parentNode.removeChild(document.getElementById(chartId+'clone'));};")
                .append("onLoad").append(jsSuffix).append("('").append(clientId).append("','").append(formId).append("','").append(clientId).append("_Ajax');").toString();
        //Set the chartURL
        String chartURL = null;
        if (ChartUtils.useServlet(context)) {
            //using a servlet to generate the chart
            chartURL = ChartConstants.CHART_REQUEST + ".chart.jsf?ts=" + System.currentTimeMillis() + "&chartId=" + clientId;
        } else {
            //using a phaseListener
            String viewId = context.getViewRoot().getViewId();
            String actionURL = context.getApplication().getViewHandler().getActionURL(context, viewId);
            chartURL = actionURL + "?ts=" + System.currentTimeMillis() + "&chartId=" + clientId;
        }

        if (chart.getOutput().equals("png") || chart.getOutput().equals("jpeg")) {
            writer.startElement("img", chart);
            writer.writeAttribute("id", clientId, null);
            writer.writeAttribute("width", String.valueOf(chart.getWidth()), null);
            writer.writeAttribute("border", "0", null);
            writer.writeAttribute("height", String.valueOf(chart.getHeight()), null);
            writer.writeAttribute("src", chartURL, null);
            ChartUtils.renderPassThruImgAttributes(writer, chart);
        } else if (chart.getOutput().equals("vml") || isIE) {
            writer.startElement("iframe", chart);
            writer.writeAttribute("id", clientId, null);
            writer.writeAttribute("width", String.valueOf(chart.getWidth()), null);
            writer.writeAttribute("height", String.valueOf(chart.getHeight()), null);
            writer.writeAttribute("src", chartURL, null);
            writer.writeAttribute("style", "display:none; margin: 0px; padding: 0px; border: none;", null);
            writer.writeAttribute("onload", onloadFunc, null);

        } else {
//            writer.startElement("script", chart);
//            writer.writeAttribute("type", "text/javascript", null);
//            writer.write ( 
//                "" +
//                "function onLoad"+jsSuffix+"(id) { " +
//                "     " +
//                "window.getMfChartId(id);" +
//                "     if (document.getElementById(id)) \n " +
//                "          document.getElementById(id).style.display='block'; \n " +
//                "     while(document.getElementById(id+'clone') != null) \n " +
//                "          document.getElementById(id).parentNode.removeChild(document.getElementById(id+'clone')); \n " +
//                "}" 
//                );
//            writer.endElement("script");
            //IE doesn't support SVG without the Adobe SVG Viewer (ASV)
            /*There is 2 different way to define an SVG document into browser 
            
            First one embed tag:
            -It's not a spec W3C but works on IE (with ASV) and others browsers 
            <embed  width="1000" height="500" src="/chartc/barCharts.jsf?ts=1232968811906&chartId=form1:_id0" type="image/svg+xml" onload="onloadChart(this);"/>
            
            Second object tag:
            -It's a part of W3C spec  but ASV forbid its use because of a security problem 
            -In Firefox you can see the SVG document with firebug extension (good for developers)
            
            <object type="image/svg+xml" data="data/test.svg" width="320" height="240">
            <param name="src" value="data/test.svg">
            alt : <a href="data/test.svg">test.svg</a>
            </object>
             */

            if (embed) {
                writer.startElement("embed", chart);
            } else {
                writer.startElement("object", chart);
            }
            writer.writeAttribute("onload", onloadFunc, null);
            /* 
            BUG Chrome,Safari :  when we set 'display:none;' these 2 browsers 
            load 2 times the SVG doc, once when the page loads and second times  
            when the display property is set to block. By default is set to none to 
            make the reRender smoother 
            
            BUG Firefox2, Opera : if display set to none the graph never displayed 
            because onLoad function never triggered but if set to block the graoph disply correctly
            
            To desactivate the smoother reRender  set the display property to 'block'
            and comment the line 462 of org/mapfaces/resources/opencharts/custom/OpenCharts/lib/MapExt.js file
            
            Without smoother reRender the graph display good on all browser , just A4J js error can happen on chrome.
             */
            writer.writeAttribute("style", "display:block;", null);
            writer.writeAttribute("id", clientId, null);
            writer.writeAttribute("width", String.valueOf(chart.getWidth()), null);
            writer.writeAttribute("border", "0", null);
            writer.writeAttribute("height", String.valueOf(chart.getHeight()), null);

            writer.writeAttribute("type", ChartUtils.resolveContentType(chart.getOutput()), null);
            writer.writeAttribute("pluginspage", "http://www.adobe.com/svg/viewer/install/main.html", null);

            if (embed) {
                writer.writeAttribute("src", chartURL, null);
                ChartUtils.renderPassThruImgAttributes(writer, chart);
            } else {
                writer.writeAttribute("data", chartURL, null);
                ChartUtils.renderPassThruImgAttributes(writer, chart);
//                writer.startElement("param", chart);
//                writer.writeAttribute("name", "src", null);
//                writer.writeAttribute("value", chartURL, null);
//                writer.endElement("param");
                writer.write("alt : ");
                writer.startElement("a", chart);
                writer.writeAttribute("href", chartURL, null);
                writer.write(chartURL);
                writer.endElement("a");
            }

        }

        /* Add a4j:support component */

        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(chart.getId() + "_Ajax");
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setImmediate(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setReRender(chart.getClientId(context));
        ajaxComp.setOnsubmit("");
        if (ChartUtils.findComponentById(context, component, ajaxComp.getId()) == null) {
            chart.getChildren().add(ajaxComp);
            chart.setAjaxCompId(ajaxComp.getClientId(context));
        }
    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIChart comp = (UIChart) component;
        boolean isIE = false;
        
        //getting the DetectBrowserListener if exists, otherwise call FacesUtils method.        
        PhaseListener resultListener = FacesUtils.getListenerFromLifeCycle(DetectBrowserListener.class);
        if (resultListener != null && resultListener instanceof DetectBrowserListener) {
            //cast and set the boolean isIE
            DetectBrowserListener detectBrowserListener = (DetectBrowserListener) resultListener;
            detectBrowserListener.proceedToDetect(context);
            isIE = detectBrowserListener.IsIE();   
        }else {
            isIE = FacesUtils.isIEBrowser(context);
        }        

        if (comp.getOutput().equals("png") || comp.getOutput().equals("jpeg")) {
            writer.endElement("img");
        } else if (comp.getOutput().equals("vml") || isIE) {
            writer.endElement("iframe");
        } else {
            if (embed) {
                writer.endElement("embed");
            } else {
                writer.endElement("object");
            }
        }
        if (comp.getGenerateMap() != null) {
            writeImageMap(context, comp);
        }
        //reInit the reDraw flag.
        comp.setReDraw(true);
    }

    public void decode(final FacesContext context, final UIComponent component) {
        super.decode(context, component);
        final UIChart comp = (UIChart) component;
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = comp.getClientId(context);
        Object obj = session.get(clientId);
        if (obj != null) {
            ChartData data = (ChartData) obj;
            if (context.getExternalContext().getRequestParameterMap() != null) {
                final Map params = context.getExternalContext().getRequestParameterMap();
                if (params.size() > 0) {
                    data.setRequestParameterMap(new HashMap(params));
                    if (params.get("chart.rerender") != null) {
                        comp.setReDraw(false);
                    }
                }
            }
        }
    }
    // creates and puts the chart data to session for this chart object
    private void setChartDataAtSession(FacesContext facesContext, UIChart comp) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = comp.getClientId(facesContext);
        boolean redraw = comp.isReDraw();        
        if (session.get(clientId) == null) {
            redraw = true;
        } 
        else {
//            System.out.println("comp.getDatasource() "+comp.getDatasource());
//            System.out.println("(ChartData) session.get(clientId)).getDatasource()) " + ((ChartData) session.get(clientId)).getDatasource());
            if (comp.getDatasource() != null && comp.getDatasource() != ((ChartData) session.get(clientId)).getDatasource()) {
                redraw = true;
            } else {
                redraw = false;
            }
        }
        if (redraw) {
            Logger.getLogger(ChartRenderer.class.getName()).log(Level.INFO, "rerendring the chart !");
            ChartData data = new ChartData(comp);
            JFreeChart chart = ChartUtils.createChartWithType(data);
            ChartUtils.setGeneralChartProperties(chart, data);
            data.setChart(chart);
            data.setInfo(null);
            session.put(clientId, data);
        }
    }

    private void renderImageMapSupport(FacesContext context, UIChart uichart, ChartRenderingInfo chartRenderingInfo) {
        ResponseWriter writer = context.getResponseWriter();

        try {
            Iterator entities = chartRenderingInfo.getEntityCollection().iterator();
            while (entities.hasNext()) {
                ChartEntity entity = (ChartEntity) entities.next();
                if (entity instanceof CategoryLabelEntity) {
                    CategoryLabelEntity categoryEntity = (CategoryLabelEntity) entity;
                    if (categoryEntity.getKey() != null) {
                        categoryEntity.setToolTipText(categoryEntity.getKey().toString());
                        categoryEntity.setURLText("?category=" + categoryEntity.getKey().toString());
                    }
                }
            }

            writer.write("<script>");
            writer.write("function chart" + uichart.getId() + "Click(data) {");

            if (uichart.getOngeneratedimagemapclick() != null) {
                writer.write("  " + uichart.getOngeneratedimagemapclick() + "(data);");
            }
            writer.write("}");
            writer.write("</script>");
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void writeImageMap(FacesContext context, UIChart uichart) {
        ResponseWriter writer = context.getResponseWriter();
        ExternalContext externalContext = context.getExternalContext();
        Map sessionMap = externalContext.getSessionMap();
        String clientId = uichart.getClientId(context);
        ChartData data = (ChartData) sessionMap.get(clientId);
        JFreeChart chart = ChartUtils.createChartWithType(data);
        ChartUtils.setGeneralChartProperties(chart, data);

        ChartRenderingInfo chartRenderingInfo = new ChartRenderingInfo();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if (data.getOutput().equalsIgnoreCase("png")) {
                ChartUtilities.writeChartAsPNG(out, chart, data.getWidth(), data.getHeight(), chartRenderingInfo);
            } else if (data.getOutput().equalsIgnoreCase("jpeg")) {
                ChartUtilities.writeChartAsJPEG(out, chart, data.getWidth(), data.getHeight(), chartRenderingInfo);
            }
            renderImageMapSupport(context, uichart, chartRenderingInfo);
            writer.write(ChartUtilities.getImageMap(uichart.getGenerateMap(), chartRenderingInfo, new StandardToolTipTagFragmentGenerator(), new URLTagFragmentGenerator(uichart.getId())));
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

class ToolTipTagFragmentGenerator implements org.jfree.chart.imagemap.ToolTipTagFragmentGenerator {

    public String generateToolTipFragment(String toolTipText) {
        return " title=\"" + toolTipText + "\" alt=\"" + toolTipText + "\"";
    }
}

class URLTagFragmentGenerator implements org.jfree.chart.imagemap.URLTagFragmentGenerator {

    private String chartId = null;

    public URLTagFragmentGenerator(String chartId) {
        this.chartId = chartId;
    }

    public String generateURLFragment(String urlText) {
        return " href=\"#\" onclick=\"chart" + chartId + "Click('" + urlText + "')\"";
    }
}