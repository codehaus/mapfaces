/*
 *    Mapfaces - 
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.renderkit.html.timeline;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UISliderInput;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.timeline.UITimeLineControl;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum
 */
public class TimeLineControlRenderer extends Renderer {

    public TimeLineControlRenderer() {
        super();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        UITimeLineControl comp = (UITimeLineControl) component;

        String timelineId = comp.getTarget();
        if (timelineId == null) {
            throw new NullPointerException("[TimeLineControlRenderer] The attribute 'target' cannot be null for the TimelineControl component !");
        }
        UITimeLine timelineComp = (UITimeLine) FacesUtils.findComponentById(context, context.getViewRoot(), timelineId);
        if (timelineComp == null) {
            throw new NullPointerException("[TimeLineControlRenderer] No timeline component founded in the jsf page !");
        }
        String clientId = component.getClientId(context);
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null) {
            writer.writeAttribute("class", styleclass, "styleclass");
        }
        String style = "height: 300px; width:auto; border: 1px solid #aaa;";
        style += (String) comp.getAttributes().get("style");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }


        writer.write("<table border=\"1\" class=\"mf-table\">");
        writer.write("<thead class=\"mf-table-thead\">");
        writer.write("<tr class=\"mf-table-header\">");
        writer.write("<th class=\"mf-table-headercell\">Layer Title</th>");
        writer.write("<th class=\"mf-table-headercell\">Interval slider</th>");
        writer.write("</thead>");
        writer.write("<tbody>");

        int i = 0;

        for (UIHotZoneBandInfo bandinfo : FacesUtils.getBandInfoTimelineChildren(context, timelineComp)) {

            String labelLayer = "";
            if (bandinfo.getLayer() != null) {
                labelLayer = bandinfo.getLayer().getName();
            }
            
            writer.startElement("tr", comp);
            writer.startElement("td", comp);
            writer.writeAttribute("align", "center", "align");
            writer.writeAttribute("class", "mf-subtable-cell", "class");
            writer.startElement("label", comp);
            writer.writeAttribute("id", comp.getId() + "_label" + i, "align");
            writer.write(labelLayer);
            writer.endElement("label");
            writer.endElement("td");


            writer.startElement("td", comp);
            writer.writeAttribute("align", "center", "align");

            if (bandinfo.isSliderInput()) {
                UISliderInput sliderInput = new UISliderInput();
                sliderInput.setId(bandinfo.getId() + "slider");
                sliderInput.setForid(String.valueOf(i));
                sliderInput.setHorizontal("true");
                String sliderWidth = bandinfo.getSliderWidth();
                if (sliderWidth != null && !sliderWidth.equals("")) {
                    sliderInput.setLength(sliderWidth);
                } else {
                    sliderInput.setLength("250");
                }
                sliderInput.setMaxval("22");
                sliderInput.setTransient(true);
                sliderInput.setParent(bandinfo);
                sliderInput.encodeAll(context);
            }
            writer.endElement("td");
            writer.endElement("tr");
            i++;
        }



    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.write("</tbody>");
        writer.write("</table>");
        writer.endElement("div");
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}
