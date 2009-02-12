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
package net.sf.jsfcomp.chartcreator;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
//import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsfcomp.chartcreator.model.ChartData;
import net.sf.jsfcomp.chartcreator.utils.ChartUtils;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 * @author Cagatay Civici (latest modification by $Author: cagatay_civici $)
 * @version $Revision: 745 $ $Date: 2007-05-08 10:16:19 +0300 (Tue, 08 May 2007) $
 * 
 * PhaseListener generating the chart image
 */
public class ChartListener implements PhaseListener {

    public void afterPhase(PhaseEvent phaseEvent) {
        String chartId = (String) phaseEvent.getFacesContext().getExternalContext().getRequestParameterMap().get("chartId");
        if (chartId != null) {
            handleChartRequest(phaseEvent, chartId);
        }
    }

    private void handleChartRequest(PhaseEvent phaseEvent, String id) {
        FacesContext facesContext = phaseEvent.getFacesContext();
        ExternalContext externalContext = facesContext.getExternalContext();

        Map sessionMap = externalContext.getSessionMap();

        ChartData chartData = (ChartData) sessionMap.get(id);
        ChartRenderingInfo info = new ChartRenderingInfo();
        //JFreeChart chart = ChartUtils.createChartWithType(chartData);
        JFreeChart chart = chartData.getChart();
       
        if (chartData.getRequestParameterMap()!= null) {
            String zoomin = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.ZOOMIN");
            String zoomout = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.ZOOMOUT");
            String pixel = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.PIXEL");
            String box = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.BOX");
            String pan = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.PAN");
            String offset = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.OFFSET");
            String translate = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.TRANSLATE");
            String container = (String) chartData.getRequestParameterMap().get("org.mapfaces.chart.CONTAINER_SIZE");
            String[] tab;
            if (pixel!=null) {
                tab = pixel.split(","); 
                if (zoomin != null && zoomin.equals("true")) {
                    chartData.zoomInBoth(Double.valueOf(tab[0]),
                            Double.valueOf(tab[1]));
                } else if (zoomout != null && zoomout.equals("true")) {
                    chartData.zoomOutBoth(Double.valueOf(tab[0]),
                            Double.valueOf(tab[1]));
                } 
            } else if (box!=null) {                
                tab = box.split(","); 
                chartData.zoom(new Rectangle2D.Double(Double.valueOf(tab[0])
                        ,Double.valueOf(tab[1])
                        ,Double.valueOf(tab[2])-Double.valueOf(tab[0])
                        ,Double.valueOf(tab[3])-Double.valueOf(tab[1])));

            } else if (pan!=null && pan.equals("true") && translate!=null) {
                    double offsetx = Double.valueOf(offset.split(",")[0]);
                    double offsety = Double.valueOf(offset.split(",")[1]);
                    double width = Double.valueOf(container.split(",")[0]);
                    double height = Double.valueOf(container.split(",")[1]);
                    chartData.pan((offsetx+(width/2)-Double.valueOf(translate.split(",")[0]))
                            ,(offsety+(height/2)-Double.valueOf(translate.split(",")[1]))
                            );
            }
            chartData.setRequestParameterMap(null);
        }
       
        try {
            if (externalContext.getResponse() instanceof HttpServletResponse) {
                writeChartWithServletResponse((HttpServletResponse) externalContext.getResponse(), chartData);
            //writeChartWithServletResponse((HttpServletResponse)externalContext.getResponse(),chart, chartData, info);
            //else if(externalContext.getResponse() instanceof RenderResponse)
            //	writeChartWithPortletResponse((RenderResponse)externalContext.getResponse(), chart, chartData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionMap.put(id, chartData);
            //emptySession(sessionMap, id);
            facesContext.responseComplete();
        }
    }

    public void beforePhase(PhaseEvent phaseEvent) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private void writeChartWithServletResponse(HttpServletResponse response, JFreeChart chart, ChartData chartData, ChartRenderingInfo info) throws IOException {
        OutputStream stream = response.getOutputStream();
        response.setContentType(ChartUtils.resolveContentType(chartData.getOutput()));
        writeChart(stream, chart, chartData, info);
    }

    private void writeChartWithServletResponse(HttpServletResponse response, ChartData chartData) throws IOException {
        OutputStream stream = response.getOutputStream();
        response.setContentType(ChartUtils.resolveContentType(chartData.getOutput()));
        writeChart(stream, chartData);
    }
//	private void writeChartWithPortletResponse(RenderResponse response, JFreeChart chart, ChartData chartData) throws IOException{
//		OutputStream stream = response.getPortletOutputStream();
//		response.setContentType(ChartUtils.resolveContentType(chartData.getOutput()));
//		writeChart(stream, chart, chartData);
//	}
    private void writeChart(OutputStream stream, JFreeChart chart, ChartData chartData, ChartRenderingInfo info) throws IOException {
        if (chartData.getOutput().equalsIgnoreCase("png")) {
            ChartUtilities.writeChartAsPNG(stream, chart, chartData.getWidth(), chartData.getHeight());
        } else if (chartData.getOutput().equalsIgnoreCase("jpeg")) {
            ChartUtilities.writeChartAsJPEG(stream, chart, chartData.getWidth(), chartData.getHeight());
        } else if (chartData.getOutput().equalsIgnoreCase("svg")) {
            ChartUtils.writeChartAsSVG(stream, chartData, info);
        }
        stream.flush();
        stream.close();
    }

    private void writeChart(OutputStream stream, ChartData chartData) throws IOException {
        ChartRenderingInfo info = chartData.getInfo();
        if (info == null) {
            info = new ChartRenderingInfo();
        }
        if (chartData.getOutput().equalsIgnoreCase("png")) {
            ChartUtilities.writeChartAsPNG(stream, chartData.getChart(), chartData.getWidth(), chartData.getHeight());
        } else if (chartData.getOutput().equalsIgnoreCase("jpeg")) {
            ChartUtilities.writeChartAsJPEG(stream, chartData.getChart(), chartData.getWidth(), chartData.getHeight());
        } else if (chartData.getOutput().equalsIgnoreCase("svg")) {
            ChartUtils.writeChartAsSVG(stream, chartData, info);
        }
        chartData.setInfo(info);
        stream.flush();
        stream.close();
    }

    private void emptySession(Map sessionMap, String id) {
        sessionMap.remove(id);
    }
}