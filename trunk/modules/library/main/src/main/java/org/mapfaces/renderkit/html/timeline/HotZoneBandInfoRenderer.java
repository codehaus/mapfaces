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
 * @author Mehdi Sidhoum.
 */
public class HotZoneBandInfoRenderer extends Renderer {

    private static final Logger LOGGER = Logger.getLogger(HotZoneBandInfoRenderer.class.getName());

    /** Creates a new instance of HotZoneBandInfoRenderer */
    public HotZoneBandInfoRenderer() {
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
        if (!(component.getParent() instanceof UITimeLine)) {
            return;
        }
        assertValid(context, component);

        //casting the component.
        final UIHotZoneBandInfo bandInfoComp = (UIHotZoneBandInfo) component;
        if (bandInfoComp.isHidden()) {
            return;
        }
        final UITimeLine parentTimeline = TimeLineUtils.getParentUITimeLine(context, bandInfoComp);
        bandInfoComp.setJsObject(bandInfoComp.getId().replace("-", "_"));
        final ExternalContext extContext = context.getExternalContext();
        final int index;
        final String key = component.getParent().getId() + "-indexhotZoneBand";
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

        final boolean timelineControlFlag = parentTimeline.isActiveControl();

        if (bandInfoComp.isSliderInput() && !timelineControlFlag && parentTimeline.isEnableBandsInput()) {

            final UISliderInput sliderInput = new UISliderInput();
            sliderInput.setId(component.getId() + "slider");
            sliderInput.setForid(String.valueOf(index));
            sliderInput.setHorizontal("true");

            final String sliderWidth = bandInfoComp.getSliderWidth();
            if (sliderWidth != null && !sliderWidth.isEmpty()) {
                sliderInput.setLength(sliderWidth);
            } else {
                sliderInput.setLength("250");
            }
            sliderInput.setMaxval("22");
            sliderInput.setTransient(true);

            if (FacesUtils.findComponentById(context, context.getViewRoot(), component.getId() + "slider") == null) {
                component.getChildren().add(sliderInput);
            }
        }
        writeChangeIntervalJS(context, bandInfoComp, writer);

        if (bandInfoComp.isInputInterval() && !timelineControlFlag) {
            writeSelectOneMenu(writer, context, bandInfoComp, index);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final UIHotZoneBandInfo comp = (UIHotZoneBandInfo) component;
        if (comp.isHidden()) {
            return;
        }
        final String idjs               = comp.getJsObject();
        final ResponseWriter writer     = context.getResponseWriter();
        final UITimeLine parentTimeline = TimeLineUtils.getParentUITimeLine(context, comp);

        if (parentTimeline.isDynamicBands()) {
            //writing js code for this component events list.
            writer.startElement("script", comp);
            writer.writeAttribute("type", "text/javascript", "text/javascript");
            writer.write("var " + idjs + "_eventSource = new Timeline.DefaultEventSource();\n");

            final List<Event> events;
            final Object value = comp.getAttributes().get("value");
            if (value != null) {
                if ( value instanceof java.lang.String ) {
                    final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                    events = (List<Event>) ve.getValue(context.getELContext());
                } else {
                    events = (List<Event>) value;
                }
            }else{
                events = new ArrayList<Event>();
            }

            final List<Event> specialEvents = TimeLineUtils.writeScriptEvents(context, parentTimeline, events, idjs);

            //add events from the attached layer of this component
            writer.endElement("script");
        }
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UIHotZoneBandInfo comp    = (UIHotZoneBandInfo) component;
        final UITimeLine parentTimeline = TimeLineUtils.getParentUITimeLine(context, comp);
        final Layer attachedLayer       = comp.getLayer();
        final Map requestMap            = context.getExternalContext().getRequestParameterMap();

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
                    ajaxlayerId = ajaxlayerId.substring(ajaxlayerId.indexOf(":") + 1);
                }

                //getting the hidden parameter in the ajax request
                String hidden = "";
                if (requestMap.containsKey("hidden")) {
                    hidden = (String) requestMap.get("hidden");
                }

                //if the layer id correspond to this component layer then proceed to refresh the bandInfo comp.
                String sh = "";
                if (attachedLayer != null) {
                    sh = attachedLayer.getCompId();
                    sh = sh.substring(sh.indexOf(":") + 1);
                }
                if (ajaxlayerId.equals(sh)) {
                    //do the rerender of the bandInfo only if hidden was set to False.
                    if (hidden.equals("false")) {
                        comp.setHidden(false);
                        final UIHotZoneBandInfo mainBand = (UIHotZoneBandInfo) FacesUtils.findComponentById(context, context.getViewRoot(), comp.getId() + "_mainband");
                        if (mainBand != null) {
                            mainBand.setWidth(40);
                        }

                        try {
                            final UILayer uiLayer = ((UILayer) FacesUtils.findComponentByClientId(context, context.getViewRoot(), (String) requestMap.get("org.mapfaces.ajax.AJAX_LAYER_ID")));
                            final Layer layer = uiLayer.getLayer();
                            final List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                            final Date centerDate = TimeLineUtils.getDefaultDateFromLayer(layer);
                            comp.setValue(layerEvents);
                            comp.setCenterDate(centerDate);
                        } catch (ParseException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (DatatypeConfigurationException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    } else {
                        comp.setHidden(true);
                    }
                }
            }

            //setting the correct width for the main bandInfo component.
            int visibleBandsCount = TimeLineUtils.getVisibleBandsList(context, parentTimeline).size();
            if (comp.getId().equals(parentTimeline.getId() + "_mainband")) {

                int mainBandWidth = 100;
                if (visibleBandsCount > 1) {
                    mainBandWidth = 40;
                }
                comp.setWidth(mainBandWidth);

            }
        }
        return;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    public void writeSelectOneMenu(final ResponseWriter writer, final FacesContext context,
            final UIHotZoneBandInfo bandInfo, final int index) throws IOException {
        final String idjs = bandInfo.getJsObject();
        writer.startElement("div", bandInfo);
        writer.writeAttribute("id", idjs + "-inputdate-div", null);
        writer.startElement("select", bandInfo);
        writer.writeAttribute("size", "1", null);
        writer.writeAttribute("onchange", idjs + "_changeIntervalUnit(" + index + ",this.value);", null);
        writer.writeAttribute("name", bandInfo.getClientId(context) + "selectone", "clientId");
        writer.writeAttribute("id", bandInfo.getClientId(context) + "selectone", "id");

        for (int i = 0; i < 11; i++) {
            writer.startElement("option", bandInfo);
            writer.writeAttribute("value", "Timeline.DateTime." + BandInfoRenderer.intervalNames[i], null);

            if (bandInfo.getIntervalUnit() != null && bandInfo.getIntervalUnit().equals(BandInfoRenderer.intervalNames[i])) {
                writer.writeAttribute("selected", Boolean.TRUE, null);
            }
            writer.writeText(BandInfoRenderer.intervalNames[i], null);
            writer.endElement("option");
        }
        writer.endElement("select");
        writer.endElement("div");
    }

    /**
     * for HotZones
     * @param context
     * @param bandInfo
     * @param writer
     * @throws java.io.IOException
     */
    public void writeChangeIntervalJS(final FacesContext context, final UIHotZoneBandInfo bandInfo,
            final ResponseWriter writer) throws IOException {
        final UITimeLine timelineComp = (UITimeLine) bandInfo.getParent();
        final String idjs             = timelineComp.getJsObject();
        final String idbandjs         = bandInfo.getJsObject();
        final Date centerDate         = bandInfo.getDate();
        final String DATE_FORMAT      = "yyyy-MM-dd'T'HH:mm:ss";
        final SimpleDateFormat sdf    = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        final String savedate         = idjs + "_tl.getBand(bandId).getCenterVisibleDate();\n";
        final String reloadDate       = idjs + "_tl.getBand(bandId).setCenterVisibleDate(savedate);\n";
        final String scrolltocenter   = idjs + "_tl.getBand(bandId).scrollToCenter(savedate);\n";

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
                    .append("var ms = Timeline.DateTime.gregorianUnitLengths[eval(val)];\n")
                    .append("var savedate = ").append(savedate)
                    .append(idjs).append("_bandInfos[bandId].ether._interval=ms;\n")
                    .append(idjs).append("_bandInfos[bandId].ether._params.interval=ms;\n")
                    .append(idjs).append("_bandInfos[bandId].etherPainter._params.unit=eval(val);\n")
                    .append("var size = ").append(idjs).append("_bandInfos[bandId].etherPainter._zones.length;\n")
                    .append(idjs).append("_bandInfos[bandId].etherPainter._zones[0].unit=eval(val);\n")
                    .append(idjs).append("_bandInfos[bandId].etherPainter._zones[size-1].unit=eval(val);\n")
                    //                    centerdateScript +
                    .append(reloadDate)
                    .append(idjs).append("_eventSource._fire(\"onAddMany\", []);\n")
                    .append(idjs).append("_tl.getBand(bandId).layout();\n")
                    //idjs + "_bandInfos[1].eventPainter.setLayout(" + idjs + "_bandInfos[0].eventPainter.getLayout());\n" +
                    .append("}").toString());
            writer.endElement("script");
        }
    }
}
