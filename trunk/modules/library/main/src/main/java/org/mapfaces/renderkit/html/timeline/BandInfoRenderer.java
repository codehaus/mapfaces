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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.timeline.UIBandInfo;
import org.mapfaces.component.timeline.UISliderInput;
import org.mapfaces.component.timeline.UITimeLine;

/**
 * @author Mehdi Sidhoum.
 */
public class BandInfoRenderer extends Renderer {

    // @TODO , these should be the localized names by properties files.
    static final String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    public BandInfoRenderer() {
        super();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
//        System.out.println(">>>> BandInfoRenderer encodeBegin");
        if (!component.isRendered()) {
            return;
        }
        if (!(component.getParent() instanceof UITimeLine)) {
            return;
        }
        assertValid(context, component);

        //casting the component.
        final UIBandInfo bandInfoComp = (UIBandInfo) component;

        bandInfoComp.setJsObject(bandInfoComp.getId().replace("-","_"));
        final ExternalContext extContext = context.getExternalContext();
        final int index;
        final String key = component.getParent().getId() + "-indexBand";

        if (!extContext.getRequestMap().containsKey(key)) {
            extContext.getRequestMap().put(key, 0);
            index = 0;
        } else {
            final int j = (Integer) extContext.getRequestMap().get(key);
            index = j + 1;
            extContext.getRequestMap().remove(key);
            extContext.getRequestMap().put(key, index);
        }

        //begin to render the component.
        final ResponseWriter writer = context.getResponseWriter();

        final UISliderInput sliderInput = new UISliderInput();
        sliderInput.setId(component.getId() + "slider");
        sliderInput.setForid(String.valueOf(index));
        sliderInput.setHorizontal("true");
        sliderInput.setLength("250");
        sliderInput.setMaxval("22");
        component.setTransient(true);

        component.getChildren().add(sliderInput);

        writeChangeIntervalJS(context, bandInfoComp, writer);
        if (bandInfoComp.isInputInterval()) {
            writeSelectOneMenu(writer, context, bandInfoComp, index);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
//        System.out.println("BandInfoRenderer encodeEnd");
        context.getResponseWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        return;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    public void writeSelectOneMenu(final ResponseWriter writer, final FacesContext context,
            final UIBandInfo bandInfo, final int index) throws IOException {
        final String idjs = bandInfo.getJsObject();
        writer.startElement("select", bandInfo);
        writer.writeAttribute("size", "1", null);
        writer.writeAttribute("onchange", idjs + "_changeIntervalUnit(" + index + ",this.value);", null);
        writer.writeAttribute("name", bandInfo.getClientId(context) + "selectone", "clientId");

        for (int i = 0; i < 11; i++) {
            writer.startElement("option", bandInfo);
            writer.writeAttribute("value", "Timeline.DateTime." + intervalNames[i], null);

            if (bandInfo.getIntervalUnit().equals(intervalNames[i])) {
                writer.writeAttribute("selected", Boolean.TRUE, null);
            }
            writer.writeText(intervalNames[i], null);
            writer.endElement("option");
        }
        writer.endElement("select");


    /*Map requestMap = context.getExternalContext().getRequestParameterMap();
    if (!requestMap.containsKey(bandInfo.getClientId(context) + "selectone")) {

    HtmlSelectOneMenu selectOne = new HtmlSelectOneMenu();
    selectOne.setId(bandInfo.getId() + "selectone");
    for (int i = 0; i < 11; i++) {
    UISelectItem selectitem = new UISelectItem();
    selectitem.setItemValue(intervalNames[i]);
    selectitem.setItemLabel(intervalNames[i]);
    selectOne.getChildren().add(selectitem);
    }

    HtmlAjaxSupport ajaxSupport = new HtmlAjaxSupport();
    ajaxSupport.setId("support" + bandInfo.getId());
    ajaxSupport.setEvent("onchange");
    ajaxSupport.setReRender(bandInfo.getParent().getId());
    selectOne.getChildren().add(ajaxSupport);
    bandInfo.getChildren().add(selectOne);

    }*/
    }

    /**
     * for bandInfo
     * @param context
     * @param bandInfo
     * @param writer
     * @throws java.io.IOException
     */
    public void writeChangeIntervalJS(final FacesContext context, final UIBandInfo bandInfo,
            final ResponseWriter writer) throws IOException {
        final UITimeLine timelineComp = (UITimeLine) bandInfo.getParent();
        final String idjs             = timelineComp.getJsObject();
        final String idbandjs         = bandInfo.getJsObject();
        final Date centerDate         = bandInfo.getDate();
        final String DATE_FORMAT      = "yyyy-MM-dd'T'HH:mm:ss";
        final SimpleDateFormat sdf    = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        final String centerdateScript;

        if (centerDate == null) {
            centerdateScript = idjs + "_tl.getBand(bandId).setCenterVisibleDate(new Date());\n";
        } else {
            centerdateScript = idjs + "_tl.getBand(bandId).setCenterVisibleDate(Timeline.DateTime.parseIso8601DateTime(\"" + sdf.format(centerDate) + "\"));\n";
        }

        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey(idbandjs + "ajaxflag.jsfunction")) {
            extContext.getRequestMap().put(idbandjs + "ajaxflag.jsfunction", Boolean.TRUE);
            writer.startElement("script", bandInfo);
            writer.writeAttribute("type", "text/javascript", null);
            writer.write(new StringBuilder("function ").append(idbandjs).append("_changeIntervalUnit(bandId,val){\n")
                    .append(idjs).append("_bandInfos[bandId].etherPainter._unit=eval(val);\n")
                    .append("var ms =1;\n")
                    .append("if (val == \"Timeline.DateTime.MILLENNIUM\") {\n")
                    .append("ms=31536000000000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.CENTURY\") {\n")
                    .append("ms=3153600000000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.DECADE\") {\n")
                    .append("ms=315360000000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.YEAR\") {\n")
                    .append("ms=31536000000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.MONTH\") {\n")
                    .append("ms=2678400000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.WEEK\") {\n")
                    .append("ms=604800000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.DAY\") {\n")
                    .append("ms=86400000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.HOUR\") {\n")
                    .append("ms=3600000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.MINUTE\") {\n")
                    .append("ms=60000;\n")
                    .append("}else\n")
                    .append("if (val == \"Timeline.DateTime.SECOND\") {\n")
                    .append("ms=1000;\n")
                    .append("}\n")
                    .append(idjs).append("_bandInfos[bandId].ether._interval=ms;\n")
                    .append(centerdateScript)
                    .append(idjs).append("_eventSource._fire(\"onAddMany\", []);\n")
                    .append(idjs).append("_bandInfos[bandId].etherPainter.paint();")
                    //"tl.layout();\n" +
                    //"tl._distributeWidths();\n" +
                    .append(idjs).append("_bandInfos[1].eventPainter.setLayout(").append(idjs).append("_bandInfos[0].eventPainter.getLayout());\n")
                    .append("}").toString());
            writer.endElement("script");
        }
    }
}