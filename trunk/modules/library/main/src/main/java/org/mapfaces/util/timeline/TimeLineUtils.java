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

package org.mapfaces.util.timeline;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TimeZone;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.timeline.UITimeLineControl;
import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.models.timeline.HighlightDecorator;
import org.mapfaces.models.timeline.Priority;
import org.mapfaces.models.timeline.Status;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.PeriodUtilities;

/**
 *
 * @author Mehdi Sidhoum
 */
public class TimeLineUtils {
    
    static String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    public static List<Event> getEventsFromLayer(Layer layer) throws ParseException, DatatypeConfigurationException {
        List<Event> result = new ArrayList<Event>();
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        sdf.setTimeZone(TimeZone.getDefault());
        SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription(layer.getTimes(), sdf);
        Date dateBegin = dates.first();
        Date dateEnd = dates.last();
        for (Iterator it = dates.iterator(); it.hasNext();) {
            Date crrt = (Date) it.next();
            Event e = new Event(crrt,
                    null,
                    null,
                    false,
                    layer.getId() + " " + sdf.format(crrt),//layer.getName()+" "+layer.getId()+" "+sdf.format(crrt),
                    layer.getTitle(),//This is the duration of the jsf implementation for the component TimeLine : "+sdf.format(crrt),
                    sdf.format(crrt),
                    sdf.format(crrt),
                    "",
                    Priority.NORMAL,
                    "",
                    Status.IN_PROGRESS,
                    "",
                    layer);
            result.add(e);
        }
        return result;
        
    }
    
    /**
     * This method returns a default date from a mapfaces Layer model.
     * @param layer
     * @return
     * @throws java.text.ParseException
     */
    public static Date getDefaultDateFromLayer (Layer layer) throws ParseException, DatatypeConfigurationException {
        if (layer == null) {
            return new Date();
        }
        return PeriodUtilities.getFirstDateFromPeriodDescription(layer.getTimes());
    }
    
    /**
     * Returns the UITimeline of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UITimeLine getParentUITimeLine(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UITimeLine)) {
            parent = parent.getParent();
        }
        return (UITimeLine)  parent;
    }
    
    /**
     * 
     * This method split the events by identifying the event type to provide the necessary scripts for eventsource object.
     * the returned list contains Eras events and events which are defined with a Duration object.
     * @param context
     * @param comp
     * @param events
     */
    public static List<Event> writeScriptEvents(FacesContext context, UITimeLine comp, List<Event> events, String idjs) throws IOException {
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
    public static void addEvent(FacesContext context, Event event, UITimeLine comp, String idjs) throws IOException {
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
    
    /**
     * 
     * This method get the resource url from phase listener to load resources from jar files.
     * @param s the url of a file.
     * @param context current faces context.
     * @return return the complete URL.
     */
    public static String getResourcePhaseListener(String s, FacesContext context) {
        return ResourcePhaseListener.getURL(context, s, null);
    }
    
    /**
     * This method write a selectOneMenu component in a page.
     * @param writer
     * @param context
     * @param bandInfo
     * @param index
     * @throws java.io.IOException
     */
    public static void writeSelectOneMenu(ResponseWriter writer, FacesContext context, UIHotZoneBandInfo bandInfo, int index) throws IOException {
        String idjs = bandInfo.getJsObject();
        writer.startElement("div", bandInfo);
        writer.writeAttribute("id", idjs+"-inputdate-div", null);
        writer.startElement("select", bandInfo);
        writer.writeAttribute("size", "1", null);
        writer.writeAttribute("onchange", idjs + "_changeIntervalUnit(" + index + ",this.value);", null);
        writer.writeAttribute("name", bandInfo.getClientId(context) + "selectone", "clientId");
        writer.writeAttribute("id", bandInfo.getClientId(context) + "selectone", "id");

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
        writer.endElement("div");
    }
    
    /**
     * This method write an inputDate component.
     * @param writer
     * @param comp
     * @param context
     * @throws java.io.IOException
     */
    public static void writeInputDateText(ResponseWriter writer, UITimeLine comp, FacesContext context) throws IOException {
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
    
    /**
     * Returns the UITimeLineControl parent of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UITimeLineControl getParentTimeLineControl(FacesContext context, UIComponent comp) {
        UIComponent parent = comp;
        while (!(parent instanceof UITimeLineControl)) {
            parent = parent.getParent();
        }
        return (UITimeLineControl) parent;
    }
    
    /**
     * This method returns a list of no hidden bandsInfo component from a timeline component.
     * @param context
     * @param timeline
     * @return
     */
    public static List<UIHotZoneBandInfo> getNotHiddenBandsList(FacesContext context, UITimeLine timeline) {
        List<UIHotZoneBandInfo> results = new ArrayList<UIHotZoneBandInfo>();
        if (timeline != null) {
        for(UIComponent child : timeline.getChildren()) {
            if (child instanceof UIHotZoneBandInfo && ! ((UIHotZoneBandInfo) child).isHidden()) {
                results.add((UIHotZoneBandInfo) child);
            }
        }
        }
        return results;
    }
}
