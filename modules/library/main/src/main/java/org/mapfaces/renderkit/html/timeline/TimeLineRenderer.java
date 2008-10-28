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

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import org.mapfaces.share.listener.ResourcePhaseListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.servlet.http.HttpServletRequest;
import org.geotools.temporal.object.DefaultInstant;
import org.geotools.temporal.object.DefaultOrdinalPosition;
import org.geotools.temporal.object.DefaultPeriod;
import org.geotools.temporal.object.DefaultPeriodDuration;
import org.geotools.temporal.object.DefaultPosition;
import org.geotools.temporal.object.DefaultTemporalObject;
import org.geotools.temporal.object.Utils;
import org.geotools.temporal.reference.DefaultOrdinalEra;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.component.timeline.UIBandInfo;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.timeline.UITimeLineControl;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.models.timeline.HighlightDecorator;
import org.mapfaces.models.timeline.Priority;
import org.mapfaces.models.timeline.Status;
import org.mapfaces.models.timeline.Zone;
import org.mapfaces.util.timeline.TimeLineUtils;
import org.opengis.temporal.Duration;
import org.opengis.temporal.Instant;
import org.opengis.temporal.OrdinalPosition;
import org.opengis.temporal.Period;
import org.opengis.temporal.PeriodDuration;
import org.opengis.temporal.TemporalGeometricPrimitive;
import org.opengis.temporal.TemporalPrimitive;

/**
 *
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 */
public class TimeLineRenderer extends Renderer {

    @Override
    @SuppressWarnings("TimeLineRenderer")
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //casting the component.
        UITimeLine comp = (UITimeLine) component;
        String clientId = component.getClientId(context);
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        comp.setJsObject(comp.getId().replace("-", "_") + "_Container");
        //Write the scripts once per page
        ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.ajaxScript")) {
            extContext.getRequestMap().put("ajaxflag.ajaxScript", Boolean.TRUE);
            writeTimeLineScripts(context, comp);
        }

        //check if a timeline control panel component has been declared.
        boolean timelineControlFlag = false;
        if (comp.isActiveControl()) {
            timelineControlFlag = true;
        }

        int height = 60;

        //Adding BandInfos sub components if the timeline is wrapped by an UIModelBase component.
        UIModelBase parentContext = FacesUtils.getParentUIModelBase(context, component);
        if (parentContext != null && (parentContext instanceof UIContext) && comp.isDynamicBands()) {
            AbstractModelBase modelbase = ((UIContext) parentContext).getModel();
            if (modelbase instanceof Context) {
                List<Layer> layers = ((Context) modelbase).getLayers();
                List<Event> events = new ArrayList<Event>();
                int i = 0;

                if (FacesUtils.getCountTemporalLayers(layers) != 0) {
                    int proportinalwidth = Math.round(60 / FacesUtils.getCountTemporalLayers(layers));
                    for (Layer layer : layers) {
                        if (layer.getDimensionList() != null && layer.getTime() != null) {
                            UIHotZoneBandInfo bandinfo = new UIHotZoneBandInfo();
                            bandinfo.setLayer(layer);
                            bandinfo.setId(comp.getId() + "_band" + i);
                            bandinfo.setWidth(proportinalwidth);
                            bandinfo.setSliderInput(true);
                            bandinfo.setSliderWidth("100");
                            bandinfo.setInputInterval(false);
                            bandinfo.setShowEventText(true);
                            bandinfo.setIntervalPixels(80);
                            bandinfo.setIntervalUnit("MONTH");
                            bandinfo.setTrackHeight(1.0);
                            bandinfo.setTheme(comp.getTheme());
                            bandinfo.setBackgroundColor(TimeLineUtils.colors[i + 1]);
                            bandinfo.setHidden(true); //hide the bandinfo component at the first time.
                            if (FacesUtils.findComponentById(context, context.getViewRoot(), comp.getId() + "_band" + i) == null) {
                                comp.getChildren().add(bandinfo);
                            }
                            i++;
                            try {

                                List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                                events.addAll(layerEvents);
                            } catch (ParseException ex) {
                                Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DatatypeConfigurationException ex) {
                                Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                UIHotZoneBandInfo mainBandinfo = new UIHotZoneBandInfo();
                mainBandinfo.setId(comp.getId() + "_mainband");
                mainBandinfo.setValue(events);

                int visibleBandsCount = TimeLineUtils.getVisibleBandsList(context, comp).size();
                int width = 100;
                if (visibleBandsCount > 1) {
                    height = comp.getHeight();
                    width = 40;
                }
                mainBandinfo.setWidth(width);
                mainBandinfo.setSliderInput(false);
                mainBandinfo.setIntervalPixels(50);
                mainBandinfo.setIntervalUnit("YEAR");
                mainBandinfo.setShowEventText(false);
                mainBandinfo.setInputInterval(true);
                mainBandinfo.setTrackHeight(0.5);
                mainBandinfo.setBackgroundColor(TimeLineUtils.colors[0]);
                mainBandinfo.setTheme(comp.getTheme());
                if (FacesUtils.findComponentById(context, context.getViewRoot(), comp.getId() + "_mainband") == null) {
                    comp.getChildren().add(mainBandinfo);
                }
            }
        }

        //if a control panel is declared then a div is added to wrap the timeline and the panel control.
        if (comp.isActiveControl()) {
            writer.startElement("div", comp);
            writer.writeAttribute("id", clientId + "-wrap", "id");
            writer.writeAttribute("class", "timeline-wrap", "class");

            UITimeLineControl timelineControl = new UITimeLineControl();
            timelineControl.setId(comp.getId() + "-control");
            timelineControl.setStyle(comp.getStyleControlPanel());
            timelineControl.setStyleClass(comp.getStyleClassControlPanel());
            timelineControl.setTransient(true);
            timelineControl.setParent(component);
            timelineControl.encodeAll(context);
        }

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null) {
            writer.writeAttribute("class", styleclass, "styleclass");
        }
        String style = "height: " + height + "px; border: 1px solid #aaa;";
        style += (String) comp.getAttributes().get("style");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }

        if (comp.isSliderZoom()) {
            writeSliderZoom(context, comp, writer, extContext);
        }

        style = style.concat(" width:100%;");

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "_Container", "id");
        writer.writeAttribute("style", style + " border:none; margin:0pt auto; left:0px;top:0px;position:relative;", "style");
        writer.endElement("div"); //close the tm-widget

        if (comp.isInputDate() && !timelineControlFlag && comp.isEnableBandsInput()) {
            writeInputDateText(writer, comp, context);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        UITimeLine comp = (UITimeLine) component;
        String idjs = comp.getJsObject();

        //begin the javascript declarations
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");
        writer.write("var " + idjs + "_tl;\n");
        writer.write("var " + idjs + "_eventSource = new Timeline.DefaultEventSource();\n");

        //getting the value property, this value can be List<Event> for the TimeLine model or  Event depending of the ISO19108 implementation.
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
        events = buildAllTemporalEvents(events);

        List<Event> specialEvents = writeScriptEvents(context, comp, events, idjs);

        //split the list erasZone to extract the events defined with Duration object to creates the HotzoneBandinfos components.
        List<Event> durationEventsCopy = new ArrayList<Event>();
        List<HighlightDecorator> erasZones = new ArrayList<HighlightDecorator>();
        for (Event event : specialEvents) {
            if (event instanceof HighlightDecorator) {
                erasZones.add((HighlightDecorator) event);
            } else {
                durationEventsCopy.add(event);
            }
        }
        //Creates a list of Zone objects from the durationEventsCopy list to display zones in the bandInfo component
        List<Zone> zones = buildZonesFromEvents(durationEventsCopy);


        List<UIHotZoneBandInfo> visiblebandsInfo = TimeLineUtils.getVisibleBandsList(context, comp);
        int visibleBandsCount = visiblebandsInfo.size();

        //getting children to create BandInfos or HotZoneBandInfos only if the bandinfo component is rendered to True.
        if (comp.getChildCount() != 0) {

            writer.write("var " + idjs + "_bandInfos = [\n");

            List<UIComponent> children = comp.getChildren();
            String separator = "";
            int indexBand = 0;
            for (UIComponent child : children) {
                if (child.getClass().toString().contains("UIBandInfo")) {
                    UIBandInfo bandInfo = (UIBandInfo) child;
                    writer.write(separator);
                    writeScriptBandsInfo(context, comp, bandInfo);
                    separator = ",\n";
                } else if (child.getClass().toString().contains("UIHotZoneBandInfo")) {
                    UIHotZoneBandInfo bandInfo = (UIHotZoneBandInfo) child;
                    if (!bandInfo.isHidden()) {
                        writer.write(separator);
                        writeScriptHotZoneBandsInfo(context, comp, bandInfo, indexBand, zones);
                        separator = ",\n";
                    }
                }
                indexBand++;
            }
            writer.write("];\n");

            //if the timeline have synchronizeBands flag fixed to true then the bands subcomponent will be synchronized. Only the not hidden bands.

            if (comp.isSynchronizeBands()) {

                //if not dynamic bands then all bands will be sync with the first band with index i-1 from 0
                int i = 0;
                for (i = 0; i < visibleBandsCount; i++) {
                    if (i > 0 && !comp.isDynamicBands()) {
                        //if not dynamic bands then all bands will be sync with the first band with index 0
                        writer.write("" + idjs + "_bandInfos[" + i + "].syncWith = " + (i - 1) + ";\n");
                        writer.write("" + idjs + "_bandInfos[" + i + "].highlight = true;\n");
                    }

                    if (comp.isDynamicBands() && i != visibleBandsCount - 1 && !(visiblebandsInfo.get(i)).isHidden()) {
                        //if there is a dynamicBands then all bands will be sync with the main band component.
                        writer.write(idjs + "_bandInfos[" + i + "].syncWith = " + (visibleBandsCount - 1) + ";\n");
                    }
                }
                if (comp.getChildCount() > 1) {
                    i--;
                // writer.write(idjs + "_bandInfos[" + i + "].eventPainter.setLayout(" + idjs + "_bandInfos[" + (i - 1) + "].eventPainter.getLayout());\n");
                }
            }
        }

        //write scripts necessary for eras.
        writeScriptsEras(context, comp, erasZones);

        //declare a new Timeline widget.
        writer.write(idjs + "_tl = Timeline.create(document.getElementById('" + FacesUtils.getFormId(context, component) + ":" + idjs + "'), " + idjs + "_bandInfos);\n");

        //set the background color for all visible bandsInfos components. Note this script must be after the declaration of the new timeline calls.
        int i = 0;
        for (i = 0; i < visibleBandsCount; i++) {
            if (comp.isDynamicBands() && i != visibleBandsCount - 1 ) {
                writer.write(idjs + "_bandInfos[" + i + "].etherPainter._backgroundLayer.style.backgroundColor = \"" + visiblebandsInfo.get(i).getBackgroundColor() + "\";\n");
            }
        }
        //setting the background color script for the mainBand component.
        writer.write(idjs + "_bandInfos[" + (i - 1) + "].etherPainter._backgroundLayer.style.backgroundColor = \"" + visiblebandsInfo.get(i - 1).getBackgroundColor() + "\";\n");


        writeResizeFunction(context, comp);

        //TO BE DELETED no reference to mapfaces components because the timeline should be works alone, without a context
        UIModelBase parentContext = FacesUtils.getParentUIModelBase(context, component);
        if (parentContext != null && (parentContext instanceof UIContext)) {

            writer.write("Timeline.sendAjaxRequest=function(img,domEvt,evt){\n" +
                    "        var parameters = {    'synchronized': 'true',\n" +
                    "                             'org.mapfaces.ajax.AJAX_LAYER_ID': '" + FacesUtils.getFormId(context, component) + ":'+img.textContent.split(' ')[0],\n" +
                    "                             'refresh': img.textContent.split(' ')[0],\n" +
                    "                              'Time': img.textContent.split(' ')[1],\n" +
                    "                              'org.mapfaces.ajax.AJAX_CONTAINER_ID':'Time',\n" +
                    "                              'render': 'true' //render the layers, always set to true after the first page loads\n" +
                    "                         };" +
                    "parameters['" + ((UIContext) parentContext).getAjaxCompId() + "'] =' " + ((UIContext) parentContext).getAjaxCompId() + "';" +
                    "        A4J.AJAX.Submit( 'j_id_jsp_1260680181_0','" + FacesUtils.getFormId(context, component) + "',\n" +
                    "                        null,\n" +
                    "                        {   'control':this,\n" +
                    "                            'single':true,\n" +
                    "                            'parameters': parameters \n" +
                    "                        } \n" +
                    "                      );\n" +
                    "    };\n");
        }
        writer.endElement("script");
        writer.endElement("div"); //close the global div

        if (comp.isActiveControl()) {
            writer.endElement("div"); //close the wrap div
        }

        writer.flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        UITimeLine comp = (UITimeLine) component;
        Map requestMap = context.getExternalContext().getRequestParameterMap();

        //if the dynamicbands property is False the entire timeline is set to one layer. Else each band have its own layer.
        if (FacesUtils.getParentUIModelBase(context, component) != null &&
                (FacesUtils.getParentUIModelBase(context, component) instanceof UIContext) &&
                !comp.isDynamicBands()) {
            if (requestMap.containsKey("org.mapfaces.ajax.AJAX_LAYER_ID") &&
                    requestMap.containsKey("org.mapfaces.ajax.AJAX_CONTAINER_ID") &&
                    ((String) requestMap.get("org.mapfaces.ajax.AJAX_CONTAINER_ID")).contains("Time")) {
                try {
                    UILayer uiLayer = ((UILayer) FacesUtils.findComponentByClientId(context, context.getViewRoot(), (String) requestMap.get("org.mapfaces.ajax.AJAX_LAYER_ID")));
                    Layer layer = uiLayer.getLayer();
                    List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                    Date centerDate = TimeLineUtils.getDefaultDateFromLayer(layer);

                    List<UIComponent> children = component.getChildren();
                    for (UIComponent tmp : children) {
                        if (tmp instanceof UIHotZoneBandInfo) {
                            UIHotZoneBandInfo bandinfo = (UIHotZoneBandInfo) tmp;
                            bandinfo.setDate(centerDate);
                            bandinfo.setCenterDate(centerDate);
                            bandinfo.setLayer(layer);
                        }
                    }
                    comp.setValue(layerEvents);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DatatypeConfigurationException ex) {
                    Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        int visibleBandsCount = TimeLineUtils.getVisibleBandsList(context, comp).size();
        for (UIComponent child : comp.getChildren()) {
            //setting the intervalUnit of children from their selectOneMenu component.
            if (child instanceof UIBandInfo) {
                UIBandInfo bandInfo = (UIBandInfo) child;
                String clientId = bandInfo.getClientId(context);
                String submitted_value = ((String) requestMap.get(clientId + "selectone"));
                bandInfo.setIntervalUnit(submitted_value);
            } else {
                //setting the correct width for all dynamic bandinfo components.
                if (child instanceof UIHotZoneBandInfo) {
                    if (visibleBandsCount > 1 && !((UIHotZoneBandInfo) child).isHidden() && !((UIHotZoneBandInfo) child).getId().equals(comp.getId() + "_mainband")) {
                        int proportinalwidth = Math.round(60 / (visibleBandsCount - 1));
                        ((UIHotZoneBandInfo) child).setWidth(proportinalwidth);
                    }
                }
            }
        }
        component.queueEvent(new ActionEvent(comp));
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

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /**
     * This method write a function to resize the timeline component.
     * @param context
     * @param comp
     */
    private void writeResizeFunction(FacesContext context, UITimeLine comp) throws IOException {
        String idjs = comp.getJsObject();
        ResponseWriter writer = context.getResponseWriter();
        writer.write("var resizeTimerID = null;\n" +
                "function onResize() {\n" +
                "if (resizeTimerID == null) {\n" +
                "resizeTimerID = window.setTimeout(function() {\n" +
                "resizeTimerID = null;\n" +
                "" + idjs + "_tl.layout();\n" +
                "}, 500);\n" +
                "}\n" +
                "}\n");
    }

    private void writeScriptBandsInfo(FacesContext context, UITimeLine comp, UIBandInfo bandInfo) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        //proceed to get the timeZone property.
        String timeZoneString = "";
        Integer timeZone = bandInfo.getTimeZone();
        if (timeZone != null) {
            timeZoneString = "timeZone:    " + timeZone + ",\n";
        }

        // proceed to get the date property to center this bandInfo component.
        String dateString = "";
        Date date = bandInfo.getDate();
        if (date != null) {
            dateString = "date: \"" + sdf.format(date) + "\",\n";
        }

        // proceed to get the showEventText attribute.
        String showEventTextString = "";
        boolean showEventText = (Boolean) bandInfo.getAttributes().get("showEventText");
        if (!showEventText) {
            showEventTextString = "showEventText:  " + showEventText + ",\n";
        }

        // proceed to get the trackHeight attribute.
        String trackHeightString = "";
        Double trackHeight = bandInfo.getTrackHeight();
        if (trackHeight != null) {
            trackHeightString = "trackHeight:    " + trackHeight + ",\n";
        }

        // proceed to get the trackGap attribute.
        String trackGapString = "";
        Double trackGap = bandInfo.getTrackGap();
        if (trackGap != null) {
            trackGapString = "trackGap:    " + trackGap + ",\n";
        }

        // proceed to get the width attribute.
        String widthString = "";
        Integer width = bandInfo.getWidth();
        if (width != null) {
            widthString = "width:    \"" + width + "%\",\n";
        }

        // proceed to get the intervalUnit attribute.
        String intervalUnitString = "";
        String intervalUnit = bandInfo.getIntervalUnit();
        if (intervalUnit != null) {
            intervalUnitString = "intervalUnit:     Timeline.DateTime." + intervalUnit + ",\n";
        } else {
            // default is YEAR.
            intervalUnitString = "intervalUnit:     Timeline.DateTime.YEAR,\n";
        }

        // proceed to get the theme property.
        String themeString = "";
        String theme = bandInfo.getTheme();
        if (theme == null || theme.equals("")) {
            if (comp.getTheme() != null && !comp.getTheme().equals("")) {
                theme = comp.getTheme();
            }
        }
        if (theme != null) {
            themeString = "theme:    Timeline." + theme + ".create(),\n";
        }

        // proceed to get the intervalPixels attribute.
        String intervalPixelsString = "";
        Integer intervalPixels = bandInfo.getIntervalPixels();
        if (intervalPixels != null) {
            intervalPixelsString = "intervalPixels:    " + intervalPixels + "\n";
        } else {
            //default is 100.
            intervalPixelsString = "intervalPixels:    100\n";
        }

        String idjs = comp.getJsObject();
        writer.write("Timeline.createBandInfo({\n");
        writer.write("eventSource:    " + idjs + "_eventSource,\n");

        writer.write(
                timeZoneString +
                dateString +
                showEventTextString +
                trackHeightString +
                trackGapString +
                widthString +
                intervalUnitString +
                themeString +
                intervalPixelsString);


        writer.write("})\n");
    }

    private void writeScriptHotZoneBandsInfo(FacesContext context, UITimeLine comp,
            UIHotZoneBandInfo bandInfo, int indexBand, List<Zone> zones) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        //proceed to get the timeZone property.
        String timeZoneString = "";
        Integer timeZone = bandInfo.getTimeZone();
        if (timeZone != null) {
            timeZoneString = "timeZone:    " + timeZone + ",\n";
        }

        // proceed to get the date property to center this bandInfo component.
        String dateString = "";
        Date date = bandInfo.getCenterDate();
        if (date != null) {

            dateString = "date: \"" + sdf.format(date) + "\",\n";
        }

        // proceed to get the showEventText attribute.
        String showEventTextString = "";
        boolean showEventText = (Boolean) bandInfo.getAttributes().get("showEventText");
        if (!showEventText) {
            showEventTextString = "showEventText:  " + showEventText + ",\n";
        }

        // proceed to get the trackHeight attribute.
        String trackHeightString = "";
        Double trackHeight = bandInfo.getTrackHeight();
        if (trackHeight != null) {
            trackHeightString = "trackHeight:    " + trackHeight + ",\n";
        }

        // proceed to get the trackGap attribute.
        String trackGapString = "";
        Double trackGap = bandInfo.getTrackGap();
        if (trackGap != null) {
            trackGapString = "trackGap:    " + trackGap + ",\n";
        }

        // proceed to get the width attribute.
        String widthString = "";
        Integer width = bandInfo.getWidth();
        if (width != null) {
            widthString = "width:    \"" + width + "%\",\n";
        }

        // proceed to get the intervalUnit attribute.
        String intervalUnitString = "";
        String intervalUnit = bandInfo.getIntervalUnit();
        if (intervalUnit != null) {
            intervalUnitString = "intervalUnit:     Timeline.DateTime." + intervalUnit + ",\n";
        } else {
            // default is YEAR.
            intervalUnitString = "intervalUnit:     Timeline.DateTime.YEAR,\n";
        }

        // proceed to get the theme property.
        String themeString = "";
        String theme = bandInfo.getTheme();
        if (theme == null || theme.equals("")) {
            if (comp.getTheme() != null && !comp.getTheme().equals("")) {
                theme = comp.getTheme();
            }
        }
        if (theme != null) {
            themeString = "theme:    Timeline." + theme + ".create(),\n";
        }

        // proceed to get the intervalPixels attribute.
        String intervalPixelsString = "";
        Integer intervalPixels = bandInfo.getIntervalPixels();
        if (intervalPixels != null) {
            intervalPixelsString = "intervalPixels:    " + intervalPixels + "\n";
        } else {
            //default is 100.
            intervalPixelsString = "intervalPixels:    100\n";
        }

        //If the dynamicband property is set to True then we have one eventSource js object per bandInfo, 
        //else there is only one for the global timeline component.
        String idjs = comp.getJsObject();
        if (comp.isDynamicBands()) {
            idjs = bandInfo.getJsObject();
        }

        writer.write("Timeline.createHotZoneBandInfo({\n");

        // write the hotZone only on the first HotZoneBandInfo to preseve the performances.
        if (indexBand == 0) {
            if (zones != null && (!zones.isEmpty())) {
                writer.write("zones: [\n");
                for (Zone zone : zones) {
                    Date begin = zone.getBegin();
                    Date end = zone.getEnd();
                    String zoneInterval = zone.getUnit();
                    int magnify = zone.getMagnify();
                    writer.write("{" +
                            "start:    \"" + sdf.format(begin) + "\",\n" +
                            "end:    \"" + sdf.format(end) + "\",\n" +
                            "magnify:    " + magnify + ",\n" +
                            "unit:    Timeline.DateTime." + zoneInterval + "}\n");
                }
                writer.write("],\n");
            } else {
                writer.write("zones: [],\n");
            }
        } else {
            //write an empty zones array for all others bands
            writer.write("zones: [],\n");
        }

        writer.write("eventSource:    " + idjs + "_eventSource,\n");

        writer.write(
                timeZoneString +
                dateString +
                showEventTextString +
                trackHeightString +
                trackGapString +
                widthString +
                intervalUnitString +
                themeString +
                intervalPixelsString);

        writer.write("})\n");
    }

    /**
     * 
     * This method split the events by identifying the event type to provide the necessary scripts for eventsource object.
     * the returned list contains Eras events and events which are defined with a Duration object.
     * @param context
     * @param comp
     * @param events
     */
    private List<Event> writeScriptEvents(FacesContext context, UITimeLine comp, List<Event> events, String idjs) throws IOException {
        List<Event> erasZones = new ArrayList<Event>();
        ResponseWriter writer = context.getResponseWriter();

        if (events != null && (!events.isEmpty())) {
            for (Event event : events) {

                if (!(event instanceof HighlightDecorator)) {
                    if (event.getDuration() == null) {
                        addEvent(context, event, comp, idjs);
                    } else {
                        erasZones.add(event);
                    }

                } else {
                    HighlightDecorator eraZone = (HighlightDecorator) event;
                    if (!erasZones.contains(eraZone)) {
                        erasZones.add(eraZone);
                    }
                }
            }
            writer.write(idjs + "_eventSource._fire(\"onAddMany\", []);\n");
        }
        return erasZones;
    }

    /**
     * This method add a new Event in the Timeline eventSource object.
     * @param context
     * @param event
     * @throws java.io.IOException
     */
    private void addEvent(FacesContext context, Event event, UITimeLine comp, String idjs) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String pathUrl = request.getRequestURL().toString();
        URL url = new URL(pathUrl);
        String domainUrl = url.getProtocol() + "://" + url.getAuthority();
        String fullContextPath = domainUrl + request.getContextPath() + "/";

        if (event != null) {

            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
            SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            String highIcon = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/dark-red-circle.png", context);
            String lowIcon = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/green-circle.png", context);
            String normalIcon = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/blue-circle.png", context);

            String jsFormater = "Timeline.DateTime.parseGregorianDateTime";
            String start = jsFormater + "('" + sdf.format(cal.getTime()) + "') ,\n";
            if (event.getDateBegin() != null) {
                start = jsFormater + "('" + sdf.format(event.getDateBegin()) + "'),\n";
            }

            String end = "null ,\n";
            if (event.getDateEnd() != null) {
                end = jsFormater + "('" + sdf.format(event.getDateEnd()) + "') , \n";
            }

            String instant = "true,\n";
            if (event.isTopological()) {
                instant = "false,\n";

                // if isTopological() then the end date must not be null !! default is new Date().
                if (end.equals("null")) {
                    end = jsFormater + "('" + sdf.format(cal.getTime()) + "') , \n";
                }
            }

            String title = "'' ,\n";
            if (event.getTitle() != null) {
                title = "\"" + event.getTitle() + "\" ,\n";
            }

            String description = "'' ,\n";
            if (event.getDescription() != null) {
                description = "\"" + event.getDescription() + "\" ,\n";
            }

            String image = "'' ,\n";
            if (event.getImage() != null) {
                image = "\"" + event.getImage() + "\" ,\n";
            }

            String link = "'' ,\n";
            if (event.getLink() != null) {
                link = "\"" + event.getLink() + "\" ,\n";
            }

            String icon = "'' ,\n";
            if (event.getPriority() != null) {
                if (event.getPriority().equals(Priority.HIGH)) {
                    icon = "\"" + domainUrl + highIcon + "\" ,\n";
                } else if (event.getPriority().equals(Priority.LOW)) {
                    icon = "\"" + domainUrl + lowIcon + "\" ,\n";
                } else {
                    icon = "\"" + domainUrl + normalIcon + "\" ,\n";
                }
            }
            if (event.getIcon() != null && (!event.getIcon().equals(""))) {
                if (event.getIcon().startsWith("http")) {
                    icon = "\"" + event.getIcon() + "\" ,\n";
                } else {
                    icon = "\"" + fullContextPath + event.getIcon() + "\" ,\n";
                }
            }

            String color = "'' ,\n";
            if (event.getStatus() != null) {
                if (event.getStatus().equals(Status.IN_PROGRESS)) {
                    color = "'green' ,\n";
                } else if (event.getStatus().equals(Status.NOT_STARTED)) {
                    color = "'red' ,\n";
                } else {
                    color = "'blue' ,\n";
                }
            }
            if (event.getColor() != null && (!event.getColor().equals(""))) {
                color = "'" + event.getColor() + "' ,\n";
            }

            String textColor = "''\n";
            if (event.getTextColor() != null && (!event.getTextColor().equals(""))) {
                textColor = "'" + event.getTextColor() + "' \n";
            }

            writer.write(idjs + "_eventSource.add(new Timeline.DefaultEventSource.Event(\n");
            writer.write(start +
                    end +
                    "null,\n" +
                    "null,\n" +
                    instant +
                    title +
                    description +
                    image +
                    link +
                    icon +
                    color +
                    textColor);
            writer.write("));\n");
        }
    }

    private void writeScriptsEras(FacesContext context, UITimeLine comp, List<HighlightDecorator> eras) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        String idjs = comp.getJsObject();
        if (eras != null && (!eras.isEmpty())) {
            writer.writeText("for (var i = 0; i < " + idjs + "_bandInfos.length; i++) {", null);
            writer.writeText("" + idjs + "_bandInfos[i].decorators = [", null);
            int i = 0;
            for (HighlightDecorator era : eras) {
                i++;
                String startDate = "";
                if (era.getDateBegin() != null) {
                    startDate = "   startDate:\"" + sdf.format(era.getDateBegin()) + "\",\n";
                }

                String endDate = "";
                if (era.getDateEnd() != null) {
                    endDate = "   endDate:\"" + sdf.format(era.getDateEnd()) + "\",\n";
                }

                String color = "	color:\"#ffd292\",\n";
                if (era.getColor() != null) {
                    color = "	color:\"" + era.getColor() + "\",\n";
                }

                String opacity = "  opacity: 50,\n";

                String startLabel = "";
                if (era.getTitle() != null) {
                    startLabel = "	startLabel:\"" + era.getTitle() + "\",\n";
                }

                String endLabel = "";
                if (era.getTitle() != null) {
                    endLabel = "	endLabel:\"" + era.getDescription() + "\"\n";
                }

                String theme = "    theme: theme";
                //@TODO theme property must be defined in the timeline component.

                if (!(startDate.equals("") || endDate.equals(""))) {
                    writer.write("new Timeline.SpanHighlightDecorator({\n" +
                            startDate + endDate + color + opacity + startLabel + endLabel +
                            "     }) \n");
                }
                if (i < eras.size()) {
                    writer.write(",\n");
                }
            }
            writer.writeText(" ]; \n", null);
            writer.writeText(" } \n", null);
        }
    }

    /**
     * 
     * This method get the resource url from phase listener to load resources from jar files.
     * @param s the url of a file.
     * @param context current faces context.
     * @return return the complete URL.
     */
    public String getResourcePhaseListener(String s, FacesContext context) {
        return ResourcePhaseListener.getURL(context, s, null);
    }

    /**
     * 
     * This method write if necessary timeline script and place it into the header. that use myfaces implementation.
     * @param context
     * @throws java.io.IOException
     */
    private void writeTimeLineScriptInHeader(FacesContext context)
            throws IOException {
        //AddResourceFactory.getInstance(context).addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, ResourcePhaseListener.getURLForHeader(context, "/timeline/resources/api/timeline-api.js", null));
    }

    /**
     * 
     * This method write  all scripts for timeline component in the body tag.
     * @param context
     * @throws java.io.IOException
     */
    private void writeTimeLineScripts(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UITimeLine comp = (UITimeLine) component;
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }

        writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/bundle.css", null) + "\"/>");
        if (comp.isMinifyJS()) {

            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.write("var TIMELINE_SINGLE_FILE = true;");
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/minify/zip.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");

        } else {
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/timeline-api.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/bundle.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/scripts/l10n/en/timeline.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/scripts/l10n/en/labellers.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/scripts/l10n/fr/timeline.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/scripts/l10n/fr/labellers.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/api/styles/theme.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
        }
    }

    /**
     * Returns a List of Events that contains all events especialy events which are multi-events (which have a scale property).
     * @param eventObjects.
     * @return The corresponding list of Event.
     */
    public List<Event> buildAllTemporalEvents(List<Event> events) {
        List<Event> response = new ArrayList<Event>();

        if (events != null && (!events.isEmpty())) {
            for (Event event : events) {
                DefaultTemporalObject tm_object = event.getTemporalObject();
                if (tm_object != null) {
                    if (tm_object instanceof TemporalPrimitive) {
                        if (tm_object instanceof TemporalGeometricPrimitive) {
                            if (tm_object instanceof Instant) {
                                DefaultInstant instant = (DefaultInstant) tm_object;
                                DefaultPosition position = (DefaultPosition) instant.getPosition();
                                if (position != null) {
                                    if (position.anyOther() != null) {
                                        if (position.anyOther() instanceof OrdinalPosition) {
                                            DefaultOrdinalPosition ordinalpos = (DefaultOrdinalPosition) position.anyOther();
                                            DefaultOrdinalEra ordinalEra = (DefaultOrdinalEra) ordinalpos.getOrdinalPosition();
                                            HighlightDecorator eraDeco = new HighlightDecorator(ordinalEra.getBeginning(),
                                                    ordinalEra.getEnd(),
                                                    ordinalEra.getName().toString(),
                                                    "",
                                                    event.getColor());
                                            Event newEvent = new Event(ordinalEra.getBeginning(),
                                                    ordinalEra.getEnd(),
                                                    null,
                                                    event.isTopological(),
                                                    event.getTitle(),
                                                    event.getDescription(),
                                                    event.getImage(),
                                                    event.getLink(),
                                                    event.getIcon(),
                                                    event.getPriority(),
                                                    event.getColor(),
                                                    event.getStatus(),
                                                    event.getTextColor(),
                                                    event.getOwner());
                                            response.add(eraDeco);
                                            response.add(newEvent);
                                            continue;
                                        }
                                    }
                                    if (position.getDate() != null) {
                                        Event newEvent = new Event(instant.getPosition().getDate(),
                                                null,
                                                null,
                                                event.isTopological(),
                                                event.getTitle(),
                                                event.getDescription(),
                                                event.getImage(),
                                                event.getLink(),
                                                event.getIcon(),
                                                event.getPriority(),
                                                event.getColor(),
                                                event.getStatus(),
                                                event.getTextColor(),
                                                event.getOwner());
                                        response.add(newEvent);
                                        continue;
                                    }
                                }
                                continue;
                            }
                            if (tm_object instanceof Period) {
                                DefaultPeriod period = (DefaultPeriod) tm_object;
                                DefaultInstant instantBegin = (DefaultInstant) period.getBeginning();
                                DefaultInstant instantEnd = (DefaultInstant) period.getEnding();
                                DefaultPosition positionBegin = (DefaultPosition) instantBegin.getPosition();
                                DefaultPosition positionEnd = (DefaultPosition) instantEnd.getPosition();

                                if (positionBegin.getDate() != null && positionEnd.getDate() != null) {
                                    Event newEvent = null;
                                    if (event.getDuration() == null) {
                                        //add an event which is a period and without a duration object.
                                        newEvent = new Event(positionBegin.getDate(),
                                                positionEnd.getDate(),
                                                null,
                                                event.isTopological(),
                                                event.getTitle(),
                                                event.getDescription(),
                                                event.getImage(),
                                                event.getLink(),
                                                event.getIcon(),
                                                event.getPriority(),
                                                event.getColor(),
                                                event.getStatus(),
                                                event.getTextColor(),
                                                event.getOwner());
                                        response.add(newEvent);
                                    } else {
                                        //add an event with duration object.
                                        newEvent = new Event(positionBegin.getDate(),
                                                positionEnd.getDate(),
                                                event.getDuration(),
                                                event.isTopological(),
                                                event.getTitle(),
                                                event.getDescription(),
                                                event.getImage(),
                                                event.getLink(),
                                                event.getIcon(),
                                                event.getPriority(),
                                                event.getColor(),
                                                event.getStatus(),
                                                event.getTextColor(),
                                                event.getOwner());
                                        response.add(newEvent);
                                    }

                                    continue;
                                }
                            }
                        }
                    }
                } else {
                    response.add(event);
                }
            }
            return response;
        }
        return null;
    }

    public void writeInputDateText(ResponseWriter writer, UITimeLine comp, FacesContext context) throws IOException {
        String idjs = comp.getJsObject();
        writer.startElement("input", comp);
        writer.writeAttribute("id", idjs + "_inputdate", "id");
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("onchange", idjs + "_centerToDate();", null);
        writer.writeAttribute("name", idjs + "_inputdate", null);
        writer.endElement("input");

        writer.write("<script>\n" +
                "function " + idjs + "_centerToDate(){\n" +
                "var valdate = document.getElementById('" + idjs + "_inputdate').value;\n" +
                "var dateInput = Timeline.DateTime.parseIso8601DateTime(valdate);\n" +
                "if (dateInput instanceof Date) {\n" +
                idjs + "_tl.getBand(0).scrollToCenter(dateInput);\n" +
                "} return false;}\n" +
                "</script>\n");
    }

    public List<Zone> buildZonesFromEvents(List<Event> events) {
        List<Zone> response = new ArrayList<Zone>();
        if (events != null && (!events.isEmpty())) {
            for (Event event : events) {
                Date begin = event.getDateBegin();
                Date end = event.getDateEnd();

                DefaultPeriodDuration duration;
                String unit = "";
                if (event.getDuration() instanceof PeriodDuration) {
                    duration = (DefaultPeriodDuration) event.getDuration();
                    unit = getIntervalFromDuration(duration);
                }

                Zone zone = new Zone(begin, end, unit, 50);
                response.add(zone);
            }
        }
        return response;
    }

    public String getIntervalFromDuration(Duration duration) {
        final Map<Unit, String> map = new HashMap<Unit, String>();
        map.put(NonSI.YEAR, "YEAR");
        map.put(NonSI.MONTH, "MONTH");
        map.put(NonSI.WEEK, "WEEK");
        map.put(NonSI.DAY, "DAY");
        map.put(NonSI.HOUR, "HOUR");
        map.put(NonSI.MINUTE, "MINUTE");
        map.put(SI.SECOND, "SECOND");
        Unit unit = Utils.getUnitFromDuration(duration);
        return map.get(unit);
    }

    public void writeSilderScripts(ResponseWriter writer, UITimeLine comp, FacesContext context) throws IOException {
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        if (!comp.isMinifyJS()) {
            writer.startElement("script", comp);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/slider/js/JSSlider.js", null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
        }
    }

    public void writeSliderZoom(FacesContext context, UITimeLine comp, ResponseWriter writer, ExternalContext extContext) throws IOException {
        //Write the scripts for slider component once per page
        if (!extContext.getRequestMap().containsKey("ajaxflag.sliderScript")) {
            extContext.getRequestMap().put("ajaxflag.sliderScript", Boolean.TRUE);
            writeSilderScripts(writer, comp, context);
        }
        String clientId = comp.getClientId(context);
        writer.startElement("div", comp);
        writer.writeAttribute("id", comp.getJsObject() + "-zoom", "id");
        writer.writeAttribute("style", "background:#D9E1EC;float:left; height: 300px; width: 34pt; z-index: 1000; position: absolute;", "style");
        writer.endElement("div");

        String idjs = comp.getJsObject();
        writer.startElement("script", comp);
        writer.write("\nvar " + idjs + "zoomSlider = document.getElementById(\"" + idjs + "-zoom\");\n" +
                idjs + "zoomSlider.appendChild(JSSlider.getInstance(\"" + idjs + "zoom\", false, 10, 150, 45, undefined, undefined, \"" + idjs + "_valChangeHandlerzoomslider\", false).render());\n" +
                "function " + idjs + "_valChangeHandlerzoomslider(newStartPercent0To100, newEndPercent0To100) {\n" +
                "    var slideVal = Math.round(10*newStartPercent0To100/100);\n" +
                "    var intervalPixels;\n" +
                "    intervalPixels = (slideVal*70);\n" +
                "    if (intervalPixels == 0) {\n" +
                "        intervalPixels = 100;\n" +
                "    }\n" +
                idjs + "_tl.getBand(0).getEther()._pixelsPerInterval= intervalPixels;\n" +
                idjs + "_tl.paint();\n" +
                "}\n");
        writer.endElement("script");
    }
}
