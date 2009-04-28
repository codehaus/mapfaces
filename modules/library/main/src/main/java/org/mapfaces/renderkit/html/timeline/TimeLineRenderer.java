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
import javax.xml.datatype.DatatypeConfigurationException;
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

import org.geotoolkit.temporal.object.DefaultInstant;
import org.geotoolkit.temporal.object.DefaultOrdinalPosition;
import org.geotoolkit.temporal.object.DefaultPeriod;
import org.geotoolkit.temporal.object.DefaultPeriodDuration;
import org.geotoolkit.temporal.object.DefaultPosition;
import org.geotoolkit.temporal.object.DefaultTemporalObject;
import org.geotoolkit.temporal.object.Utils;
import org.geotoolkit.temporal.reference.DefaultOrdinalEra;

import org.mapfaces.share.listener.ResourcePhaseListener;
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
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 */
public class TimeLineRenderer extends Renderer {

    private static final String DATE_FORMAT    = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
    private static final SimpleDateFormat SDF  = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
    
    /**
     * {@inheritDoc }
     */
    @Override
    @SuppressWarnings("TimeLineRenderer")
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //casting the component.
        final UITimeLine comp = (UITimeLine) component;
        final String clientId = component.getClientId(context);
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        
        comp.setJsObject(comp.getId().replace("-", "_") + "_Container");
        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.ajaxScript")) {
            extContext.getRequestMap().put("ajaxflag.ajaxScript", Boolean.TRUE);
            writeTimeLineScripts(context, comp);
        }

        //check if a timeline control panel component has been declared.
        final boolean timelineControlFlag = comp.isActiveControl();

        //this is the init height of the timeline if there is only one bandInfo component (the main band).
        int height = UITimeLine.TIMELINE_Default_Height;
        if (comp.getBandHeight() != 0) {
            height = comp.getBandHeight();
        }

        //Adding BandInfos sub components if the timeline is wrapped by an UIModelBase component.
        final UIModelBase parentContext = FacesUtils.getParentUIModelBase(context, component);
        if (parentContext != null && (parentContext instanceof UIContext) && comp.isDynamicBands()) {
            final AbstractModelBase modelbase = ((UIContext) parentContext).getModel();
            if (modelbase instanceof Context) {
                final List<Layer> layers = ((Context) modelbase).getLayers();
                final List<Event> events = new ArrayList<Event>();
                int i = 0;

                if (FacesUtils.getCountTemporalLayers(layers) != 0) {
                    //int proportinalwidth = Math.round(60 / FacesUtils.getCountTemporalLayers(layers));
                    for (final Layer layer : layers) {
                        if (layer.getDimensionList() != null && layer.getTime() != null) {
                            final UIHotZoneBandInfo bandinfo = new UIHotZoneBandInfo();
                            bandinfo.setLayer(layer);
                            bandinfo.setId(comp.getId() + "_band" + i);
                            //bandinfo.setWidth(proportinalwidth);
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
                                final List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                                events.addAll(layerEvents);
                            } catch (ParseException ex) {
                                Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DatatypeConfigurationException ex) {
                                Logger.getLogger(TimeLineRenderer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                final UIHotZoneBandInfo mainBandinfo = new UIHotZoneBandInfo();
                mainBandinfo.setId(comp.getId() + "_mainband");
                //mainBandinfo.setValue(events);

                mainBandinfo.setInputInterval(false);
                mainBandinfo.setIntervalPixels(50);
                mainBandinfo.setIntervalUnit("YEAR");
                mainBandinfo.setShowEventText(false);

                //write an SliderInput component for the mainBandInfo only if the property activeControl is set to True.
                if (comp.isActiveControl()) {
                    mainBandinfo.setSliderInput(true);
                    mainBandinfo.setSliderWidth("100");
                }

                mainBandinfo.setTrackHeight(0.3);
                mainBandinfo.setBackgroundColor(TimeLineUtils.colors[0]);
                mainBandinfo.setTheme(comp.getTheme());
                if (FacesUtils.findComponentById(context, context.getViewRoot(), comp.getId() + "_mainband") == null) {
                    comp.getChildren().add(mainBandinfo);
                }

                final List<UIHotZoneBandInfo> visibleBands = TimeLineUtils.getVisibleBandsList(context, comp);
                final int visibleBandsCount = visibleBands.size();
                if (visibleBandsCount > 1) {
                    if (comp.getBandHeight() != 0) {
                        height = comp.getBandHeight() * visibleBandsCount ;
                    } else {
                        height = UITimeLine.TIMELINE_Default_Height * visibleBandsCount;
                    }
                }
            }
        }

        //if a control panel is declared then a div is added to wrap the timeline and the panel control.
        if (comp.isActiveControl()) {
            writer.startElement("div", comp);
            writer.writeAttribute("id", clientId, "id");
            writer.writeAttribute("class", "timeline-main-div", "class");
            final String stylewrap = new StringBuilder("height:").append(height).append("px; max-height:").append(comp.getHeight()).append("px;overflow-x:hidden;overflow-y:auto;position:relative;").toString();
            writer.writeAttribute("style", stylewrap, "style");

            final UITimeLineControl timelineControl = new UITimeLineControl();
            timelineControl.setId(comp.getId() + "-control");
            timelineControl.setStyle(comp.getStyleControlPanel());
            timelineControl.setStyleClass(comp.getStyleClassControlPanel());
            timelineControl.setParent(component);
            timelineControl.encodeAll(context);
        }

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-wrap", "id");
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        final UITimeLine comp = (UITimeLine) component;
        final String idjs     = comp.getJsObject();

        //begin the javascript declarations
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");
        writer.write("var " + idjs + "_tl;\n");
        writer.write("var " + idjs + "_eventSource = new Timeline.DefaultEventSource();\n");

        //getting the value property, this value can be List<Event> for the TimeLine model or  Event depending of the ISO19108 implementation.
        List<Event> events = new ArrayList<Event>();

        final Object value = comp.getAttributes().get("value");

        if (value != null) {
            if ( value instanceof java.lang.String ) {
                final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                events = (List<Event>) ve.getValue(context.getELContext());
            } else {
                events = (List<Event>) value;
            }
        }
        events = buildAllTemporalEvents(events);

        final List<Event> specialEvents = writeScriptEvents(context, comp, events, idjs);

        //split the list erasZone to extract the events defined with Duration object to creates the HotzoneBandinfos components.
        final List<Event> durationEventsCopy     = new ArrayList<Event>();
        final List<HighlightDecorator> erasZones = new ArrayList<HighlightDecorator>();
        for (final Event event : specialEvents) {
            if (event instanceof HighlightDecorator) {
                erasZones.add((HighlightDecorator) event);
            } else {
                durationEventsCopy.add(event);
            }
        }
        //Creates a list of Zone objects from the durationEventsCopy list to display zones in the bandInfo component
        final List<Zone> zones                         = buildZonesFromEvents(durationEventsCopy);
        final List<UIHotZoneBandInfo> visiblebandsInfo = TimeLineUtils.getVisibleBandsList(context, comp);
        final int visibleBandsCount                    = visiblebandsInfo.size();

        //getting children to create BandInfos or HotZoneBandInfos only if the bandinfo component is rendered to True.
        if (comp.getChildCount() != 0) {

            writer.write("var " + idjs + "_bandInfos = [\n");

            String separator = "";
            int indexBand = 0;
            for (final UIComponent child : comp.getChildren()) {
                if (child.getClass().toString().contains("UIBandInfo")) {
                    final UIBandInfo bandInfo = (UIBandInfo) child;
                    writer.write(separator);
                    writeScriptBandsInfo(context, comp, bandInfo);
                    separator = ",\n";
                } else if (child.getClass().toString().contains("UIHotZoneBandInfo")) {
                    final UIHotZoneBandInfo bandInfo = (UIHotZoneBandInfo) child;
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
                for (int i = 0; i < visibleBandsCount; i++) {
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
            /*if (comp.getChildCount() > 1) {
            i--;
            // writer.write(idjs + "_bandInfos[" + i + "].eventPainter.setLayout(" + idjs + "_bandInfos[" + (i - 1) + "].eventPainter.getLayout());\n");
            }*/
            }
        }

        //write scripts necessary for eras.
        writeScriptsEras(context, comp, erasZones);

        //declare a new Timeline widget.
        writer.write(idjs + "_tl = Timeline.create(document.getElementById('" + FacesUtils.getFormId(context, component) + ":" + idjs + "'), " + idjs + "_bandInfos);\n");

        //set the background color for all visible bandsInfos components. Note this script must be after the declaration of the new timeline calls.
        int i = 0;
        for (i = 0; i < visibleBandsCount; i++) {
            if (comp.isDynamicBands() && i != visibleBandsCount - 1) {
                writer.write(idjs + "_bandInfos[" + i + "].etherPainter._backgroundLayer.style.backgroundColor = \"" + visiblebandsInfo.get(i).getBackgroundColor() + "\";\n");
            }
        }
        //setting the background color script for the mainBand component.
        writer.write(idjs + "_bandInfos[" + (i - 1) + "].etherPainter._backgroundLayer.style.backgroundColor = \"" + visiblebandsInfo.get(i - 1).getBackgroundColor() + "\";\n");


        writeResizeFunction(context, comp);

        //TO BE DELETED no reference to mapfaces components because the timeline should be works alone, without a context
        final UIModelBase parentContext = FacesUtils.getParentUIModelBase(context, component);
        if (parentContext != null && (parentContext instanceof UIContext)) {

            writer.write(new StringBuilder("Timeline.sendAjaxRequest=function(img,domEvt,evt){\n")
                    .append("        var parameters = {    'synchronized': 'true',\n")
                    .append("                             'org.mapfaces.ajax.AJAX_LAYER_ID': ").append("img.textContent.split(' ')[0],\n")
                    .append("                             'refresh': ").append("img.textContent.split(' ')[0],\n")
                    .append("                              'Time': img.textContent.split(' ')[1],\n")
                    .append("                              'org.mapfaces.ajax.AJAX_CONTAINER_ID':'Time',\n")
                    .append("                              'render': 'true' //render the layers, always set to true after the first page loads\n")
                    .append("                         };")
                    .append("parameters['").append(((UIContext) parentContext).getAjaxCompId()).append("'] =' ").append(((UIContext) parentContext).getAjaxCompId()).append("';")
                    .append("        A4J.AJAX.Submit( 'j_id_jsp_1260680181_0','").append(FacesUtils.getFormId(context, component)).append("',\n")
                    .append("                        null,\n")
                    .append("                        {   'control':this,\n")
                    .append("                            'single':true,\n")
                    .append("                            'parameters': parameters \n")
                    .append("                        } \n")
                    .append("                      );\n")
                    .append("    };\n")
                    .toString());
        }
        writer.endElement("script");
        writer.endElement("div"); //close the global div

        if (comp.isActiveControl()) {
            writer.endElement("div"); //close the wrap div
        }

        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UITimeLine comp = (UITimeLine) component;
        final Map requestMap  = context.getExternalContext().getRequestParameterMap();

        //if the dynamicbands property is False the entire timeline is set to one layer. Else each band have its own layer.
        if (FacesUtils.getParentUIModelBase(context, component) != null &&
                (FacesUtils.getParentUIModelBase(context, component) instanceof UIContext) &&
                !comp.isDynamicBands()) {
            if (requestMap.containsKey("org.mapfaces.ajax.AJAX_LAYER_ID") &&
                    requestMap.containsKey("org.mapfaces.ajax.AJAX_CONTAINER_ID") &&
                    ((String) requestMap.get("org.mapfaces.ajax.AJAX_CONTAINER_ID")).contains("Time")) {
                
                final UILayer uiLayer = ((UILayer) FacesUtils.findComponentByClientId(context, context.getViewRoot(), (String) requestMap.get("org.mapfaces.ajax.AJAX_LAYER_ID")));
                final Layer layer = uiLayer.getLayer();
                
                try {
                    final List<Event> layerEvents = TimeLineUtils.getEventsFromLayer(layer);
                    final Date centerDate = TimeLineUtils.getDefaultDateFromLayer(layer);

                    for (final UIComponent tmp : component.getChildren()) {
                        if (tmp instanceof UIHotZoneBandInfo) {
                            final UIHotZoneBandInfo bandinfo = (UIHotZoneBandInfo) tmp;
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

        final int visibleBandsCount = TimeLineUtils.getVisibleBandsList(context, comp).size();
        for (final UIComponent child : comp.getChildren()) {
            //setting the intervalUnit of children from their selectOneMenu component.
            if (child instanceof UIBandInfo) {
                final UIBandInfo bandInfo    = (UIBandInfo) child;
                final String clientId        = bandInfo.getClientId(context);
                final String submitted_value = ((String) requestMap.get(clientId + "selectone"));
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (component.getChildCount() == 0) {
            return;
        }

        final List<UIHotZoneBandInfo> visibleBands = TimeLineUtils.getVisibleBandsList(context, (UITimeLine) component);
        final int visibleBandsCount                = visibleBands.size();
        final int proportinalwidth                 = Math.round(100 / visibleBandsCount);
        final int rest                             = 100 - (proportinalwidth * visibleBandsCount);

        for (final UIComponent tmp : component.getChildren()) {
            if (tmp instanceof UIHotZoneBandInfo) {
//                if (rest != 0) {
//                    proportinalwidth++;
//                    rest--;
//                }
                ((UIHotZoneBandInfo) tmp).setWidth(proportinalwidth);
//                if (rest != 0) {
//                    proportinalwidth--;
//                }
            }
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    /**
     * This method write a function to resize the timeline component.
     */
    private void writeResizeFunction(final FacesContext context, final UITimeLine comp) throws IOException {
        final String idjs           = comp.getJsObject();
        final ResponseWriter writer = context.getResponseWriter();
        writer.write(new StringBuilder("var resizeTimerID = null;\n")
                .append("function onResize() {\n")
                .append("if (resizeTimerID == null) {\n")
                .append("resizeTimerID = window.setTimeout(function() {\n")
                .append("resizeTimerID = null;\n")
                .append("").append(idjs).append("_tl.layout();\n")
                .append("}, 500);\n")
                .append("}\n")
                .append("}\n")
                .toString());
    }

    private void writeScriptBandsInfo(final FacesContext context, final UITimeLine comp, 
            final UIBandInfo bandInfo) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        
        final StringBuilder content = new StringBuilder();
        
        //proceed to get the timeZone property.
        final Integer timeZone = bandInfo.getTimeZone();
        if (timeZone != null) {
            content.append("timeZone:    ").append(timeZone).append(",\n");
        }

        // proceed to get the date property to center this bandInfo component.
        final Date date = bandInfo.getDate();
        if (date != null) {
            content.append("date: \"").append(SDF.format(date)).append("\",\n");
        }

        // proceed to get the showEventText attribute.
        final boolean showEventText = (Boolean) bandInfo.getAttributes().get("showEventText");
        if (!showEventText) {
            content.append("showEventText:  ").append(showEventText).append(",\n");
        }

        // proceed to get the trackHeight attribute.
        final Double trackHeight = bandInfo.getTrackHeight();
        if (trackHeight != null) {
            content.append("trackHeight:    ").append(trackHeight).append(",\n");
        }

        // proceed to get the trackGap attribute.
        final Double trackGap = bandInfo.getTrackGap();
        if (trackGap != null) {
            content.append("trackGap:    ").append(trackGap).append(",\n");
        }

        // proceed to get the width attribute.
        final Integer width = bandInfo.getWidth();
        if (width != null) {
            content.append("width:    \"").append(width).append("%\",\n");
        }

        // proceed to get the intervalUnit attribute.
        final String intervalUnit = bandInfo.getIntervalUnit();
        if (intervalUnit != null) {
            content.append("intervalUnit:     Timeline.DateTime.").append(intervalUnit).append(",\n");
        } else {
            // default is YEAR.
            content.append("intervalUnit:     Timeline.DateTime.YEAR,\n");
        }

        // proceed to get the theme property.
        String theme = bandInfo.getTheme();
        if (theme == null || theme.equals("")) {
            if (comp.getTheme() != null && !comp.getTheme().equals("")) {
                theme = comp.getTheme();
            }
        }
        if (theme != null) {
            content.append("theme:    Timeline.").append(theme).append(".create(),\n");
        }

        // proceed to get the intervalPixels attribute.
        final Integer intervalPixels = bandInfo.getIntervalPixels();
        if (intervalPixels != null) {
            content.append("intervalPixels:    ").append(intervalPixels).append("\n");
        } else {
            //default is 100.
            content.append("intervalPixels:    100\n");
        }

        final String idjs = comp.getJsObject();
        writer.write("Timeline.createBandInfo({\n");
        writer.write("eventSource:    " + idjs + "_eventSource,\n");

        writer.write(content.toString());


        writer.write("})\n");
    }

    private void writeScriptHotZoneBandsInfo(final FacesContext context, final UITimeLine comp,
            final UIHotZoneBandInfo bandInfo, final int indexBand, final List<Zone> zones) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();

        final StringBuilder content = new StringBuilder();
        
        //proceed to get the timeZone property.
        final Integer timeZone = bandInfo.getTimeZone();
        if (timeZone != null) {
            content.append("timeZone:    ").append(timeZone).append(",\n");
        }

        // proceed to get the date property to center this bandInfo component.
        final Date date = bandInfo.getCenterDate();
        if (date != null) {
            content.append("date: \"").append(SDF.format(date)).append("\",\n");
        }

        // proceed to get the showEventText attribute.
        final boolean showEventText = (Boolean) bandInfo.getAttributes().get("showEventText");
        if (!showEventText) {
            content.append("showEventText:  ").append(showEventText).append(",\n");
        }

        // proceed to get the trackHeight attribute.
        final Double trackHeight = bandInfo.getTrackHeight();
        if (trackHeight != null) {
            content.append("trackHeight:    ").append(trackHeight).append(",\n");
        }

        // proceed to get the trackGap attribute.
        final Double trackGap = bandInfo.getTrackGap();
        if (trackGap != null) {
            content.append("trackGap:    ").append(trackGap).append(",\n");
        }

        // proceed to get the width attribute.
        final Integer width = bandInfo.getWidth();
        if (width != null) {
            content.append("width:    \"").append(width).append("%\",\n");
        }

        // proceed to get the intervalUnit attribute.
        final String intervalUnit = bandInfo.getIntervalUnit();
        if (intervalUnit != null) {
            content.append("intervalUnit:     Timeline.DateTime.").append(intervalUnit).append(",\n");
        } else {
            // default is YEAR.
            content.append("intervalUnit:     Timeline.DateTime.YEAR,\n");
        }

        // proceed to get the theme property.
        String theme = bandInfo.getTheme();
        if (theme == null || theme.equals("")) {
            if (comp.getTheme() != null && !comp.getTheme().equals("")) {
                theme = comp.getTheme();
            }
        }
        if (theme != null) {
            content.append("theme:    Timeline.").append(theme).append(".create(),\n");
        }

        // proceed to get the intervalPixels attribute.
        Integer intervalPixels = bandInfo.getIntervalPixels();
        if (intervalPixels != null) {
            content.append("intervalPixels:    ").append(intervalPixels).append("\n");
        } else {
            //default is 100.
            content.append("intervalPixels:    100\n");
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
                for (final Zone zone : zones) {
                    final Date begin          = zone.getBegin();
                    final Date end            = zone.getEnd();
                    final String zoneInterval = zone.getUnit();
                    final int magnify         = zone.getMagnify();
                    writer.write(new StringBuilder("{")
                            .append("start:    \"").append(SDF.format(begin)).append("\",\n")
                            .append("end:    \"").append(SDF.format(end)).append("\",\n")
                            .append("magnify:    ").append(magnify).append(",\n")
                            .append("unit:    Timeline.DateTime.").append(zoneInterval).append("}\n")
                            .toString());
                }
                writer.write("],\n");
            } else {
                writer.write("zones: [],\n");
            }
        } else {
            //write an empty zones array for all others bands
            writer.write("zones: [],\n");
        }

        writer.write(new StringBuilder("eventSource:    ").append(idjs).append("_eventSource,\n").toString());
        writer.write(content.toString());
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
    private List<Event> writeScriptEvents(final FacesContext context, final UITimeLine comp, 
            final List<Event> events, final String idjs) throws IOException {
        final List<Event> erasZones = new ArrayList<Event>();
        final ResponseWriter writer = context.getResponseWriter();

        if (events != null && (!events.isEmpty())) {
            for (final Event event : events) {

                if (!(event instanceof HighlightDecorator)) {
                    if (event.getDuration() == null) {
                        addEvent(context, event, comp, idjs);
                    } else {
                        erasZones.add(event);
                    }

                } else {
                    final HighlightDecorator eraZone = (HighlightDecorator) event;
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
    private void addEvent(final FacesContext context, final Event event, final UITimeLine comp, 
            final String idjs) throws IOException {
        final ResponseWriter writer      = context.getResponseWriter();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String pathUrl             = request.getRequestURL().toString();
        final URL url                    = new URL(pathUrl);
        final String domainUrl           = url.getProtocol() + "://" + url.getAuthority();
        final String fullContextPath     = domainUrl + request.getContextPath() + "/";

        if (event != null) {
            final Calendar cal          = Calendar.getInstance(Locale.ENGLISH);
            final String highIcon       = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/dark-red-circle.png", context);
            final String lowIcon        = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/green-circle.png", context);
            final String normalIcon     = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/blue-circle.png", context);
            final String jsFormater     = "Timeline.DateTime.parseGregorianDateTime";
            final StringBuilder content = new StringBuilder();
            
            if (event.getDateBegin() != null) {
                content.append(jsFormater).append("('").append(SDF.format(event.getDateBegin())).append("'),\n");
            }else{
                content.append(jsFormater).append("('").append(SDF.format(cal.getTime())).append("') ,\n");
            }
            
            if (event.getDateEnd() != null) {
                content.append(jsFormater).append("('").append(SDF.format(event.getDateEnd())).append("') , \n");
            }else if(event.isTopological()){
                // if isTopological() then the end date must not be null !! default is new Date().
                content.append(jsFormater).append("('").append(SDF.format(cal.getTime())).append("') , \n");
            }else{
                content.append("null ,\n");
            }

            content.append("null,\n");
            content.append("null,\n");
            
            if (event.isTopological()) {
                content.append("false,\n");
            }else{
                content.append("true,\n");
            }

            if (event.getTitle() != null) {
                content.append("\"").append(event.getTitle()).append("\" ,\n");
            }else{
                content.append("'' ,\n");
            }

            if (event.getDescription() != null) {
                content.append("\"").append(event.getDescription()).append("\" ,\n");
            }else{
                content.append("'' ,\n");
            }

            if (event.getImage() != null) {
                content.append("\"").append(event.getImage()).append("\" ,\n");
            }else{
                content.append("'' ,\n");
            }

            if (event.getLink() != null) {
                content.append("\"").append(event.getLink()).append("\" ,\n");
            }else{
                content.append("'' ,\n");
            }

            if (event.getIcon() != null && (!event.getIcon().equals(""))) {
                if (event.getIcon().startsWith("http")) {
                    content.append("\"").append(event.getIcon()).append("\" ,\n");
                } else {
                    content.append("\"").append(fullContextPath).append(event.getIcon()).append("\" ,\n");
                }
            }else if (event.getPriority() != null) {
                if (event.getPriority().equals(Priority.HIGH)) {
                    content.append("\"").append(domainUrl).append(highIcon).append("\" ,\n");
                } else if (event.getPriority().equals(Priority.LOW)) {
                    content.append("\"").append(domainUrl).append(lowIcon).append("\" ,\n");
                } else {
                    content.append("\"").append(domainUrl).append(normalIcon).append("\" ,\n");
                }
            }else{
                content.append("'' ,\n");
            }

            
            if (event.getColor() != null && (!event.getColor().equals(""))) {
                content.append("'").append(event.getColor()).append("' ,\n");
            }else if (event.getStatus() != null) {
                if (event.getStatus().equals(Status.IN_PROGRESS)) {
                    content.append("'green' ,\n");
                } else if (event.getStatus().equals(Status.NOT_STARTED)) {
                    content.append("'red' ,\n");
                } else {
                    content.append("'blue' ,\n");
                }
            }else{
                content.append("'' ,\n");
            }

            if (event.getTextColor() != null && (!event.getTextColor().equals(""))) {
                content.append("'").append(event.getTextColor()).append("' \n");
            }else{
                content.append("''\n");
            }

            writer.write(idjs + "_eventSource.add(new Timeline.DefaultEventSource.Event(\n");
            writer.write(content.toString());
            writer.write("));\n");
        }
    }

    private void writeScriptsEras(final FacesContext context, final UITimeLine comp, 
            final List<HighlightDecorator> eras) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final String idjs           = comp.getJsObject();
        
        if (eras != null && (!eras.isEmpty())) {
            writer.writeText("for (var i = 0; i < " + idjs + "_bandInfos.length; i++) {", null);
            writer.writeText("" + idjs + "_bandInfos[i].decorators = [", null);
            int i = 0;
            for (final HighlightDecorator era : eras) {
                i++;
                
                if (!(era.getDateBegin() == null || era.getDateEnd() == null)) {
                    final StringBuilder content = new StringBuilder("new Timeline.SpanHighlightDecorator({\n");
                    
                    content.append("   startDate:\"").append(SDF.format(era.getDateBegin())).append("\",\n");
                    content.append("   endDate:\"").append(SDF.format(era.getDateEnd())).append("\",\n");


                    if (era.getColor() != null) {
                        content.append("	color:\"").append(era.getColor()).append("\",\n");
                    }else{
                        content.append("	color:\"#ffd292\",\n");
                    }

                    content.append("  opacity: 50,\n");

                    if (era.getTitle() != null) {
                        content.append("	startLabel:\"").append(era.getTitle()).append("\",\n");
                    }

                    if (era.getTitle() != null) {
                        content.append("	endLabel:\"").append(era.getDescription()).append("\"\n");
                    }

                    final String theme = "    theme: theme";
                    //@TODO theme property must be defined in the timeline component.

                    content.append("     }) \n");
                    
                    writer.write(content.toString());
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
    public String getResourcePhaseListener(final String s, final FacesContext context) {
        return ResourcePhaseListener.getURL(context, s, null);
    }

    /**
     *
     * This method write if necessary timeline script and place it into the header. that use myfaces implementation.
     * @param context
     * @throws java.io.IOException
     */
    private void writeTimeLineScriptInHeader(final FacesContext context)
            throws IOException {
        //AddResourceFactory.getInstance(context).addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, ResourcePhaseListener.getURLForHeader(context, "/timeline/resources/api/timeline-api.js", null));
    }

    /**
     *
     * This method write  all scripts for timeline component in the body tag.
     * @param context
     * @throws java.io.IOException
     */
    private void writeTimeLineScripts(final FacesContext context, final UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        final UITimeLine comp = (UITimeLine) component;
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
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/compressed/timeline.min.js", null), null);
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
    public List<Event> buildAllTemporalEvents(final List<Event> events) {
        final List<Event> response = new ArrayList<Event>();

        if (events != null && (!events.isEmpty())) {
            for (final Event event : events) {
                final DefaultTemporalObject tm_object = event.getTemporalObject();
                if (tm_object != null) {
                    if (tm_object instanceof TemporalPrimitive) {
                        if (tm_object instanceof TemporalGeometricPrimitive) {
                            if (tm_object instanceof Instant) {
                                final DefaultInstant instant = (DefaultInstant) tm_object;
                                final DefaultPosition position = (DefaultPosition) instant.getPosition();
                                if (position != null) {
                                    if (position.anyOther() != null) {
                                        if (position.anyOther() instanceof OrdinalPosition) {
                                            final DefaultOrdinalPosition ordinalpos = (DefaultOrdinalPosition) position.anyOther();
                                            final DefaultOrdinalEra ordinalEra      = (DefaultOrdinalEra) ordinalpos.getOrdinalPosition();
                                            final HighlightDecorator eraDeco        = new HighlightDecorator(ordinalEra.getBeginning(),
                                                    ordinalEra.getEnd(),
                                                    ordinalEra.getName().toString(),
                                                    "",
                                                    event.getColor());
                                            final Event newEvent = new Event(ordinalEra.getBeginning(),
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
                                        final Event newEvent = new Event(instant.getPosition().getDate(),
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
                                final DefaultPeriod period          = (DefaultPeriod) tm_object;
                                final DefaultInstant instantBegin   = (DefaultInstant) period.getBeginning();
                                final DefaultInstant instantEnd     = (DefaultInstant) period.getEnding();
                                final DefaultPosition positionBegin = (DefaultPosition) instantBegin.getPosition();
                                final DefaultPosition positionEnd   = (DefaultPosition) instantEnd.getPosition();

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

    public void writeInputDateText(final ResponseWriter writer, final UITimeLine comp, final FacesContext context) throws IOException {
        final String idjs = comp.getJsObject();
        writer.startElement("input", comp);
        writer.writeAttribute("id", idjs + "_inputdate", "id");
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("title", "Enter a date in format YYYY-mm-ddTHH:MM:ss to center the timeline.", "title");
        writer.writeAttribute("onchange", idjs + "_centerToDate();", null);
        writer.writeAttribute("name", idjs + "_inputdate", null);
        writer.endElement("input");

        writer.write(new StringBuilder("<script>\n")
                .append("function ").append(idjs).append("_centerToDate(){\n")
                .append("var valdate = document.getElementById('").append(idjs).append("_inputdate').value;\n")
                .append("var dateInput = Timeline.DateTime.parseIso8601DateTime(valdate);\n")
                .append("if (dateInput instanceof Date) {\n")
                .append(idjs).append("_tl.getBand(0).scrollToCenter(dateInput);\n")
                .append("} return false;}\n")
                .append("</script>\n")
                .toString());
    }

    public List<Zone> buildZonesFromEvents(final List<Event> events) {
        final List<Zone> response = new ArrayList<Zone>();
        if (events != null && (!events.isEmpty())) {
            for (final Event event : events) {
                final Date begin = event.getDateBegin();
                final Date end = event.getDateEnd();

                final DefaultPeriodDuration duration;
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

    public String getIntervalFromDuration(final Duration duration) {
        final Map<Unit, String> map = new HashMap<Unit, String>();
        map.put(NonSI.YEAR, "YEAR");
        map.put(NonSI.MONTH, "MONTH");
        map.put(NonSI.WEEK, "WEEK");
        map.put(NonSI.DAY, "DAY");
        map.put(NonSI.HOUR, "HOUR");
        map.put(NonSI.MINUTE, "MINUTE");
        map.put(SI.SECOND, "SECOND");
        final Unit unit = Utils.getUnitFromDuration(duration);
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

    public void writeSliderZoom(final FacesContext context, final UITimeLine comp, 
            final ResponseWriter writer, final ExternalContext extContext) throws IOException {
        //Write the scripts for slider component once per page
        if (!extContext.getRequestMap().containsKey("ajaxflag.sliderScript")) {
            extContext.getRequestMap().put("ajaxflag.sliderScript", Boolean.TRUE);
            writeSilderScripts(writer, comp, context);
        }
        final String clientId = comp.getClientId(context);
        writer.startElement("div", comp);
        writer.writeAttribute("id", comp.getJsObject() + "-zoom", "id");
        writer.writeAttribute("style", "background:#D9E1EC;float:left; height: 300px; width: 34pt; z-index: 1000; position: absolute;", "style");
        writer.endElement("div");

        final String idjs = comp.getJsObject();
        writer.startElement("script", comp);
        writer.write(
                new StringBuilder("\nvar ").append(idjs).append("zoomSlider = document.getElementById(\"").append(idjs).append("-zoom\");\n")
                .append(idjs).append("zoomSlider.appendChild(JSSlider.getInstance(\"").append(idjs).append("zoom\", false, 10, 150, 45, undefined, undefined, \"").append(idjs).append("_valChangeHandlerzoomslider\", false).render());\n")
                .append("function ").append(idjs).append("_valChangeHandlerzoomslider(newStartPercent0To100, newEndPercent0To100) {\n")
                .append("    var slideVal = Math.round(10*newStartPercent0To100/100);\n")
                .append("    var intervalPixels;\n")
                .append("    intervalPixels = (slideVal*70);\n")
                .append("    if (intervalPixels == 0) {\n")
                .append("        intervalPixels = 100;\n")
                .append("    }\n")
                .append(idjs).append("_tl.getBand(0).getEther()._pixelsPerInterval= intervalPixels;\n")
                .append(idjs).append("_tl.paint();\n")
                .append("}\n")
                .toString());
        writer.endElement("script");
    }
}
