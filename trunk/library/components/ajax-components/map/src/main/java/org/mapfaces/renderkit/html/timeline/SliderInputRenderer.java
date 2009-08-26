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
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.timeline.UIBandInfo;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UISliderInput;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesMapUtils;

/**
 * @author Mehdi Sidhoum
 */
public class SliderInputRenderer extends Renderer {

    public SliderInputRenderer() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        final UISliderInput comp = (UISliderInput) component;

        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.sliderScript")) {
            extContext.getRequestMap().put("ajaxflag.sliderScript", Boolean.TRUE);
            writeSilderScripts(context, comp);
        }

        if (comp.getAttributes().get("maxval") != null) {
            comp.setMaxval((String) comp.getAttributes().get("maxval"));
        }
        if (comp.getAttributes().get("for") != null) {
            comp.setForid((String) comp.getAttributes().get("for"));
        }
        if (comp.getAttributes().get("horizontal") != null) {
            comp.setHorizontal((String) comp.getAttributes().get("horizontal"));
        }
        if (comp.getAttributes().get("length") != null) {
            comp.setLength((String) comp.getAttributes().get("length"));
        }
        final String comp_id = comp.getId().replace("-", "_");
        final String valchangehandler = "valChangeHandler" + comp_id;
        final String destinationDiv = "dest" + comp_id;

        // prepend base client Id to forid
        //forid = getBaseClientId(context) + forid;


        final ResponseWriter writer = context.getResponseWriter();

        // Render client Javascript for slider

        writer.startElement("div", comp);
        writer.writeAttribute("id", comp.getId()+"-wrap", "id");
        String style = "";
        if (comp.getStyle() != null) {
            style = comp.getStyle();
        }

        writer.writeAttribute("style", style, "style");

        writer.write("<div id=\"" + destinationDiv + "\"><!-- slider goes here --></div>");
        final String idlabel = comp_id.concat("-label");

        String intervalUnit = "";
        UICommand bandInfo = null;
        if (comp.getParent() instanceof UIBandInfo) {
            bandInfo = (UIBandInfo) comp.getParent();
            intervalUnit = ((UIBandInfo) bandInfo).getIntervalUnit();

        } else if (comp.getParent() instanceof UIHotZoneBandInfo) {
            bandInfo = (UIHotZoneBandInfo) comp.getParent();
            intervalUnit = ((UIHotZoneBandInfo) bandInfo).getIntervalUnit();
        }

        final int startValue = comp.getIndexFromUnit(intervalUnit);
        final int strtResult = (startValue * 2 * 100) / 22;

        writer.startElement("label", comp);
        writer.writeAttribute("id", idlabel, "id");
        writer.writeAttribute("style", "margin:0pt auto;", "style");
        writer.write(intervalUnit);
        writer.endElement("label");

        writer.endElement("div"); //close the wrap div

        String idjs = "";
        if (bandInfo != null) {
            idjs = bandInfo.getId().replace("-", "_");
        }
        writer.write(new StringBuilder("<script>var ").append(destinationDiv).append(" = document.getElementById(\"").append(destinationDiv).append("\"); ")
                .append(destinationDiv).append(".appendChild(JSSlider.getInstance(\"")
                .append(comp_id).append("\", ").append(comp.getHorizontal()).append(", ")
                .append("10, ").append(comp.getLength()).append(", ").append(strtResult).append(", undefined, undefined, \"").append(valchangehandler).append("\", false).render());\n")
                .append(" function ").append(valchangehandler).append("(newStartPercent0To100, newEndPercent0To100) ")
                .append("{ var slideVal = Math.round(")
                .append(comp.getMaxval()).append("*newStartPercent0To100/100); \n").toString());

        writer.write(new StringBuilder("var interval;\n")
                .append("if (slideVal <= 2){\n")
                .append("interval = 'MILLISECOND';}\n")
                .append("if (slideVal <= 4 && slideVal > 2){\n")
                .append("interval = 'SECOND';}\n")
                .append("if (slideVal <=6 && slideVal >4){\n")
                .append("interval = 'MINUTE';}\n")
                .append("if (slideVal <=8 && slideVal >6){\n")
                .append("interval = 'HOUR';}\n")
                .append("if (slideVal <=10 && slideVal >8){\n")
                .append("interval = 'DAY';}\n")
                .append("if (slideVal <=12 && slideVal >10){\n")
                .append("interval = 'WEEK';}\n")
                .append("if (slideVal <=14 && slideVal >12){\n")
                .append("interval = 'MONTH';}\n")
                .append("if (slideVal <=16 && slideVal >14){\n")
                .append("interval = 'YEAR';}\n")
                .append("if (slideVal <=18 && slideVal >16){\n")
                .append("interval = 'DECADE';}\n")
                .append("if (slideVal <=20 && slideVal >18){\n")
                .append("interval = 'CENTURY';}\n")
                .append("if (slideVal <=22 && slideVal >20){\n")
                .append("interval = 'MILLENNIUM';}\n")
                .append("document.getElementById(\"")
                //getBaseClientId(context) +
                .append(idlabel).append("\").innerHTML = interval; \n")
                .append(idjs).append("_changeIntervalUnit(").append(comp.getForid()).append(",'Timeline.DateTime.'+interval);" + "}</script>").toString());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    public void writeSilderScripts(final FacesContext context, final UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesMapUtils.getResponseWriter2(context);
        }
        writer.startElement("script", component);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/slider/js/JSSlider.js", null), null);
        writer.writeAttribute("type", "text/javascript", null);
        writer.endElement("script");
    }
}
