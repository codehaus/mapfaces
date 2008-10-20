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
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum
 */
public class SliderInputRenderer extends Renderer {

    public SliderInputRenderer() {
        super();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        
        UISliderInput comp = (UISliderInput) component;

        //Write the scripts once per page
        ExternalContext extContext = context.getExternalContext();
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
        String comp_id = comp.getId().replace("-", "_");
        String valchangehandler = "valChangeHandler" + comp_id;
        String destinationDiv = "dest" + comp_id;

        // prepend base client Id to forid
        //forid = getBaseClientId(context) + forid;


        ResponseWriter writer = context.getResponseWriter();

        // Render client Javascript for slider
        writer.write("<div id=\"" + destinationDiv + "\"><!-- slider goes here --></div>");
        String idlabel = comp_id.concat("-label");

        String intervalUnit = "";
        int strtResult = 0;
        UICommand bandInfo = null;
        if (comp.getParent() instanceof UIBandInfo) {
            bandInfo = (UIBandInfo) comp.getParent();
            intervalUnit = ((UIBandInfo) bandInfo).getIntervalUnit();

        } else if (comp.getParent() instanceof UIHotZoneBandInfo) {
            bandInfo = (UIHotZoneBandInfo) comp.getParent();
            intervalUnit = ((UIHotZoneBandInfo) bandInfo).getIntervalUnit();
        }

        int startValue = comp.getIndexFromUnit(intervalUnit);
        strtResult = (startValue * 2 * 100) / 22;

        writer.startElement("label", comp);
        writer.writeAttribute("id", idlabel, "id");
        writer.write(intervalUnit);
        writer.endElement("label");


        String idjs = bandInfo.getId().replace("-", "_");
        writer.write("<script>var " + destinationDiv + " = document.getElementById(\"" + destinationDiv + "\"); " +
                destinationDiv + ".appendChild(JSSlider.getInstance(\"" +
                comp_id + "\", " + comp.getHorizontal() + ", " +
                "10, " + comp.getLength() + ", " + strtResult + ", undefined, undefined, \"" + valchangehandler + "\", false).render());\n" +
                " function " + valchangehandler + "(newStartPercent0To100, newEndPercent0To100) " +
                "{ var slideVal = Math.round(" +
                comp.getMaxval() + "*newStartPercent0To100/100); \n");

        writer.write("var interval;\n" +
                "if (slideVal <= 2){\n" +
                "interval = 'MILLISECOND';}\n" +
                "if (slideVal <= 4 && slideVal > 2){\n" +
                "interval = 'SECOND';}\n" +
                "if (slideVal <=6 && slideVal >4){\n" +
                "interval = 'MINUTE';}\n" +
                "if (slideVal <=8 && slideVal >6){\n" +
                "interval = 'HOUR';}\n" +
                "if (slideVal <=10 && slideVal >8){\n" +
                "interval = 'DAY';}\n" +
                "if (slideVal <=12 && slideVal >10){\n" +
                "interval = 'WEEK';}\n" +
                "if (slideVal <=14 && slideVal >12){\n" +
                "interval = 'MONTH';}\n" +
                "if (slideVal <=16 && slideVal >14){\n" +
                "interval = 'YEAR';}\n" +
                "if (slideVal <=18 && slideVal >16){\n" +
                "interval = 'DECADE';}\n" +
                "if (slideVal <=20 && slideVal >18){\n" +
                "interval = 'CENTURY';}\n" +
                "if (slideVal <=22 && slideVal >20){\n" +
                "interval = 'MILLENNIUM';}\n" +
                "document.getElementById(\"" +
                //getBaseClientId(context) + 
                idlabel + "\").innerHTML = interval; \n" +
                idjs + "_changeIntervalUnit(" + comp.getForid() + ",'Timeline.DateTime.'+interval);" + "}</script>");
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
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
    
    public void writeSilderScripts(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        writer.startElement("script", component);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/slider/js/JSSlider.js", null), null);
        writer.writeAttribute("type", "text/javascript", null);
        writer.endElement("script");
    }
}
