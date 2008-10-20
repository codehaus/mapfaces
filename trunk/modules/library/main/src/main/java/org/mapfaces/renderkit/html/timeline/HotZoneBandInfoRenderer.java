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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.xml.datatype.DatatypeConfigurationException;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UISliderInput;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.timeline.TimeLineUtils;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class HotZoneBandInfoRenderer extends Renderer {
    // @TODO , these should be the localized names by properties files.
    static String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    /** Creates a new instance of HotZoneBandInfoRenderer */
    public HotZoneBandInfoRenderer() {
        super();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        if (!(component.getParent() instanceof UITimeLine)) {
            return;
        }
        assertValid(context, component);

        //casting the component.
        UIHotZoneBandInfo bandInfoComp = (UIHotZoneBandInfo) component;
        bandInfoComp.setJsObject(bandInfoComp.getId().replace("-", "_"));
        ExternalContext extContext = context.getExternalContext();
        int index = 0;
        String key = component.getParent().getId() + "-indexhotZoneBand";
        if (!extContext.getRequestMap().containsKey(key)) {
            extContext.getRequestMap().put(key, 0);
            index = 0;
        } else {
            int j = (Integer) extContext.getRequestMap().get(key);
            index = j + 1;
            extContext.getRequestMap().remove(key);
            extContext.getRequestMap().put(key, index);
        }

        //begin to render the component.
        ResponseWriter writer = context.getResponseWriter();

        if (bandInfoComp.isSliderInput()) {
            UISliderInput sliderInput = new UISliderInput();
            sliderInput.setId(component.getId() + "slider");
            sliderInput.setForid(String.valueOf(index));
            sliderInput.setHorizontal("true");
            sliderInput.setLength("200");
            sliderInput.setMaxval("22");
            sliderInput.setTransient(true);

            if (FacesUtils.findComponentById(context, context.getViewRoot(), component.getId() + "slider") == null) {
                component.getChildren().add(sliderInput);
            }
        }
        writeChangeIntervalJS(context, bandInfoComp, writer);
        if (bandInfoComp.isInputInterval()) {
            writeSelectOneMenu(writer, context, bandInfoComp, index);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        UIHotZoneBandInfo comp = (UIHotZoneBandInfo) component;
        String idjs = comp.getJsObject();
        ResponseWriter writer = context.getResponseWriter();
        UITimeLine parentTimeline = TimeLineUtils.getParentUITimeLine(context, comp);

        if (parentTimeline.isDynamicBands()) {
            //writing js code for this component events list.
            writer.startElement("script", comp);
            writer.writeAttribute("type", "text/javascript", "text/javascript");
            writer.write("var " + idjs + "_eventSource = new Timeline.DefaultEventSource();\n");

            List<Event> events = new ArrayList<Event>();
            Object value = comp.getAttributes().get("value");
            if (value != null) {
                if (value.getClass().toString().contains("java.lang.String")) {
                    ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                    events = (List<Event>) ve.getValue(context.getELContext());
                } else {
                    events = (List<Event>) value;
                }
            }

            List<Event> specialEvents = TimeLineUtils.writeScriptEvents(context, parentTimeline, events, idjs);

            //add events from the attached layer of this component
            writer.endElement("script");
        }
        writer.flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        UIHotZoneBandInfo comp = (UIHotZoneBandInfo) component;
        UITimeLine parentTimeline = TimeLineUtils.getParentUITimeLine(context, comp);
        Layer attachedLayer = comp.getLayer();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        
        //if the dynamicbands property is True then each bandInfo component have its own layer.
        if (FacesUtils.getParentUIModelBase(context, component) != null &&
                (FacesUtils.getParentUIModelBase(context, component) instanceof UIContext) &&
                parentTimeline.isDynamicBands()) {
            if (requestMap.containsKey("org.mapfaces.ajax.AJAX_LAYER_ID") &&
                    requestMap.containsKey("org.mapfaces.ajax.AJAX_CONTAINER_ID") &&
                    ((String) requestMap.get("org.mapfaces.ajax.AJAX_CONTAINER_ID")).contains("Time")) {
                
                //getting the layer id from a requestMap to identify if it is the layer attached to this component.
                String ajaxlayerId = (String) requestMap.get("org.mapfaces.ajax.AJAX_LAYER_ID");
                if (ajaxlayerId != null) {
                    ajaxlayerId = ajaxlayerId.substring(ajaxlayerId.indexOf(":")+1);
                }
                
                //if the layer id correspond to this component layer then proceed to refresh the bandInfo comp.
                if (attachedLayer != null && ajaxlayerId.equals(attachedLayer.getId()) && comp.isRendered()) {
                    try {
                        UILayer uiLayer = ((UILayer) FacesUtils.findComponentByClientId(context, context.getViewRoot(), (String) requestMap.get("org.mapfaces.ajax.AJAX_LAYER_ID")));
                        Layer layer = uiLayer.getLayer();
                        List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                        Date centerDate = TimeLineUtils.getDefaultDateFromLayer(layer);
                        comp.setValue(layerEvents);
                        comp.setCenterDate(centerDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    public void writeSelectOneMenu(ResponseWriter writer, FacesContext context, UIHotZoneBandInfo bandInfo, int index) throws IOException {
        String idjs = bandInfo.getJsObject();
        writer.startElement("select", bandInfo);
        writer.writeAttribute("size", "1", null);
        writer.writeAttribute("onchange", idjs + "_changeIntervalUnit(" + index + ",this.value);", null);
        writer.writeAttribute("name", bandInfo.getClientId(context) + "selectone", "clientId");

        for (int i = 0; i < 11; i++) {
            writer.startElement("option", bandInfo);
            writer.writeAttribute("value", "Timeline.DateTime." + intervalNames[i], null);

            if (bandInfo.getIntervalUnit() != null && bandInfo.getIntervalUnit().equals(intervalNames[i])) {
                writer.writeAttribute("selected", Boolean.TRUE, null);
            }
            writer.writeText(intervalNames[i], null);
            writer.endElement("option");
        }
        writer.endElement("select");
    }

    /**
     * for HotZones
     * @param context
     * @param bandInfo
     * @param writer
     * @throws java.io.IOException
     */
    public void writeChangeIntervalJS(FacesContext context, UIHotZoneBandInfo bandInfo, ResponseWriter writer) throws IOException {
        UITimeLine timelineComp = (UITimeLine) bandInfo.getParent();
        String idjs = timelineComp.getJsObject();
        String idbandjs = bandInfo.getJsObject();
        Date centerDate = bandInfo.getDate();
        final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        
        String savedate = idjs + "_tl.getBand(bandId).getCenterVisibleDate();\n";
        String reloadDate = idjs + "_tl.getBand(bandId).setCenterVisibleDate(savedate);\n";
        String scrolltocenter = idjs + "_tl.getBand(bandId).scrollToCenter(savedate);\n";
        
        String centerdateScript="";
        if (centerDate == null) {
            centerdateScript = idjs + "_tl.getBand(bandId).setCenterVisibleDate(new Date());\n";
        } else {
            centerdateScript = idjs + "_tl.getBand(bandId).setCenterVisibleDate(Timeline.DateTime.parseIso8601DateTime(\"" + sdf.format(centerDate) + "\"));\n";
        }

        ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey(idbandjs + "ajaxflag.jsfunction")) {
            extContext.getRequestMap().put(idbandjs + "ajaxflag.jsfunction", Boolean.TRUE);
            writer.startElement("script", bandInfo);
            writer.writeAttribute("type", "text/javascript", null);
            writer.write("function " + idbandjs + "_changeIntervalUnit(bandId,val){\n" +
                    "var ms = Timeline.DateTime.gregorianUnitLengths[eval(val)];\n" +
                    
                    "var savedate = "+savedate +
                    
                    idjs + "_bandInfos[bandId].ether._interval=ms;\n" +
                    idjs + "_bandInfos[bandId].ether._params.interval=ms;\n" +
                    idjs + "_bandInfos[bandId].etherPainter._params.unit=eval(val);\n" +
                    "var size = " + idjs + "_bandInfos[bandId].etherPainter._zones.length;\n" +
                    idjs + "_bandInfos[bandId].etherPainter._zones[0].unit=eval(val);\n" +
                    idjs + "_bandInfos[bandId].etherPainter._zones[size-1].unit=eval(val);\n" +
//                    centerdateScript +
                    idjs + "_eventSource._fire(\"onAddMany\", []);\n" +
                    idjs + "_tl.getBand(bandId).layout();\n" +
                    idjs + "_bandInfos[1].eventPainter.setLayout(" + idjs + "_bandInfos[0].eventPainter.getLayout());\n" +
                    reloadDate +
                    "}");
            writer.endElement("script");
        }
    }
}
