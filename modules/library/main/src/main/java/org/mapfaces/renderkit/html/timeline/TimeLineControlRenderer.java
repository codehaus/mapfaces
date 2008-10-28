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
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UISliderInput;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.timeline.UITimeLineControl;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.timeline.TimeLineUtils;

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
        String clientId = component.getClientId(context);
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }

        writer.startElement("div", comp); //main div
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
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        UITimeLineControl comp = (UITimeLineControl) component;
        UITimeLine timelineComp = null;
        if (comp.getParent() != null && comp.getParent() instanceof UITimeLine) {
            timelineComp = (UITimeLine) comp.getParent();
        }
        
        ResponseWriter writer = context.getResponseWriter();
        
        if (timelineComp != null) {
            writer.write("<div id=\"table-div\"  class=\"mf-table\">");
            writer.write(   "<div id=\"header-div\" class=\"mf-table-thead\">");
            writer.write(       "<div id=\"header-line-div\" class=\"mf-table-header\">");
            writer.write(           "<div id=\"head-cell1\" class=\"mf-table-headercell mf-table-column1\">Layer Title</div>");
            writer.write(           "<div id=\"head-cell2\" class=\"mf-table-headercell mf-table-column2\">Interval slider</div>");
            writer.write(       "</div>");
            writer.write(   "</div>");
            writer.write("  <div id= \"body-table-div\">");
            int i = 0;

            for (UIHotZoneBandInfo bandinfo : FacesUtils.getBandInfoTimelineChildren(context, timelineComp)) {
                bandinfo.setJsObject(bandinfo.getId().replace("-", "_"));
                String labelLayer = "";
                if (bandinfo.getLayer() != null) {
                    labelLayer = bandinfo.getLayer().getName();
                }
                if (!bandinfo.getId().contains("_mainband")) {
                    writer.startElement("div", comp);
                    writer.writeAttribute("id", comp.getId() + "body-line-div" + i, "align");
                    writer.writeAttribute("class", "mf-subtable-line", "class");
                    
                    writer.startElement("div", comp);
                    writer.writeAttribute("align", "center", "align");
                    writer.writeAttribute("class", "mf-subtable-cell"+" mf-table-column1", "class");
                    writer.startElement("label", comp);
                    writer.writeAttribute("id", comp.getId() + "_label" + i, "align");
                    writer.write(labelLayer);
                    writer.endElement("label");
                    writer.endElement("div");

                    writer.startElement("div", comp);
                    writer.writeAttribute("align", "center", "align");
                    writer.writeAttribute("class", "mf-subtable-cell"+" mf-table-column2", "class");
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
                    writer.endElement("div");

                    writer.endElement("div");
                } else {
                    writer.startElement("div", comp);
                    writer.writeAttribute("id", comp.getId() + "body-mainline-div", "align");
                    writer.writeAttribute("class", "mf-subtable-main", "class");
                    
                    writer.startElement("div", comp);
                    writer.writeAttribute("align", "center", "align");
                    writer.writeAttribute("class", "mf-subtable-main-cell", "class");
                    if (bandinfo.isInputInterval()) {
                        TimeLineUtils.writeSelectOneMenu(writer, context, bandinfo, i);
                    }
                    writer.endElement("div");

                    writer.startElement("div", comp);
                    writer.writeAttribute("align", "center", "align");
                    writer.writeAttribute("class", "mf-subtable-main-cell", "class");
                    if (timelineComp.isInputDate()) {
                        TimeLineUtils.writeInputDateText(writer, timelineComp, context);
                    }
                    writer.endElement("div");

                    writer.endElement("div");
                }
                i++;
            }
             writer.write("</div>"); //close body-table-div
             writer.write("</div>"); //close table-div
        }
        writer.endElement("div"); //close the main div
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
    
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (component.getChildCount() == 0) {
            return;
        }
        List<UIComponent> children = component.getChildren();
        for (UIComponent tmp : children) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
