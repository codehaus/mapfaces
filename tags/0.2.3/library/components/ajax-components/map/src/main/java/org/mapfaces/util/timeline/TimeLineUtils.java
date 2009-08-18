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
 * @author Mehdi Sidhoum
 */
public class TimeLineUtils {

    public static final String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    /**
     * this array is used for the bandInfos background colors.
     */
    public static final String colors[] = {"#FFF5A1", "#dffbff", "#afcfff", "#ffd1b0", "#deffd8", "#fde5ff","#cfffe6",
    "#FFF5A1", "#dffbff", "#afcfff", "#ffd1b0", "#deffd8", "#fde5ff","#cfffe6",
    "#FFF5A1", "#dffbff", "#afcfff", "#ffd1b0", "#deffd8", "#fde5ff","#cfffe6"};

    public static List<Event> getEventsFromLayer(final Layer layer)
            throws ParseException, DatatypeConfigurationException {
        final List<Event> result   = new ArrayList<Event>();
        final String DATE_FORMAT   = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        final SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        sdf.setTimeZone(TimeZone.getDefault());

        final SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription(layer.getTimes(), sdf);
        final Date dateBegin = dates.first();
        final Date dateEnd = dates.last();

        for (final Iterator it = dates.iterator(); it.hasNext();) {
            final Date crrt = (Date) it.next();
            final Event e = new Event(crrt,
                    null,
                    null,
                    false,
                    layer.getCompId()+ " " + sdf.format(crrt),
                    layer.getTitle(),
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
     * @return Date
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
     * @return UITimeLine
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
    public static List<Event> writeScriptEvents(final FacesContext context, final UITimeLine comp,
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
            writer.write(idjs + "_eventSource._fire(\"onAddMany\", []);");
        }
        return erasZones;
    }

    /**
     * This method add a new Event in the Timeline eventSource object.
     * @param context
     * @param event
     * @throws java.io.IOException
     */
    public static void addEvent(final FacesContext context, final Event event, final UITimeLine comp,
            final String idjs) throws IOException {
        final ResponseWriter writer      = context.getResponseWriter();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String pathUrl             = request.getRequestURL().toString();
        final URL url                    = new URL(pathUrl);
        final String domainUrl           = url.getProtocol() + "://" + url.getAuthority();
        final String fullContextPath     = domainUrl + request.getContextPath() + "/";

        if (event != null) {

            final Calendar cal          = Calendar.getInstance(Locale.ENGLISH);
            final String DATE_FORMAT    = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
            final SimpleDateFormat sdf  = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            final String highIcon       = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/dark-red-circle.png", context);
            final String lowIcon        = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/green-circle.png", context);
            final String normalIcon     = getResourcePhaseListener("/org/mapfaces/resources/timeline/api/images/blue-circle.png", context);
            final String jsFormater     = "Timeline.DateTime.parseGregorianDateTime";

            final String start;
            if (event.getDateBegin() != null) {
                start = jsFormater + "('" + sdf.format(event.getDateBegin()) + "'),";
            }else{
                start = jsFormater + "('" + sdf.format(cal.getTime()) + "') ,";
            }

            String end = "null ,";
            if (event.getDateEnd() != null) {
                end = jsFormater + "('" + sdf.format(event.getDateEnd()) + "') , ";
            }

            String instant = "true,";
            if (event.isTopological()) {
                instant = "false,";

                // if isTopological() then the end date must not be null !! default is new Date().
                if (end.equals("null")) {
                    end = jsFormater + "('" + sdf.format(cal.getTime()) + "') , ";
                }
            }

            String title = "'' ,";
            if (event.getTitle() != null) {
                title = "\"" + event.getTitle() + "\" ,";
            }

            String description = "'' ,";
            if (event.getDescription() != null) {
                description = "\"" + event.getDescription() + "\" ,";
            }

            String image = "'' ,";
            if (event.getImage() != null) {
                image = "\"" + event.getImage() + "\" ,";
            }

            String link = "'' ,";
            if (event.getLink() != null) {
                link = "\"" + event.getLink() + "\" ,";
            }

            String icon = "'' ,";
            if (event.getPriority() != null) {
                if (event.getPriority().equals(Priority.HIGH)) {
                    icon = "\"" + domainUrl + highIcon + "\" ,";
                } else if (event.getPriority().equals(Priority.LOW)) {
                    icon = "\"" + domainUrl + lowIcon + "\" ,";
                } else {
                    icon = "\"" + domainUrl + normalIcon + "\" ,";
                }
            }
            if (event.getIcon() != null && (!event.getIcon().equals(""))) {
                if (event.getIcon().startsWith("http")) {
                    icon = "\"" + event.getIcon() + "\" ,";
                } else {
                    icon = "\"" + fullContextPath + event.getIcon() + "\" ,";
                }
            }

            String color = "'' ,";
            if (event.getStatus() != null) {
                if (event.getStatus().equals(Status.IN_PROGRESS)) {
                    color = "'green' ,";
                } else if (event.getStatus().equals(Status.NOT_STARTED)) {
                    color = "'red' ,";
                } else {
                    color = "'blue' ,";
                }
            }
            if (event.getColor() != null && (!event.getColor().equals(""))) {
                color = "'" + event.getColor() + "' ,";
            }

            String textColor = "''";
            if (event.getTextColor() != null && (!event.getTextColor().equals(""))) {
                textColor = "'" + event.getTextColor() + "' ";
            }

            writer.write(idjs + "_eventSource.add(new Timeline.DefaultEventSource.Event(");
            writer.write(start +
                    end +
                    "null," +
                    "null," +
                    instant +
                    title +
                    description +
                    image +
                    link +
                    icon +
                    color +
                    textColor);
            writer.write("));");
        }
    }

    /**
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
    public static void writeSelectOneMenu(final ResponseWriter writer, final FacesContext context,
            final UIHotZoneBandInfo bandInfo, final int index) throws IOException {
        final String idjs = bandInfo.getJsObject();
        writer.startElement("div", bandInfo);
        writer.writeAttribute("id", idjs+"-inputdate-div", null);
        writer.startElement("select", bandInfo);
        writer.writeAttribute("size", "1", null);
        writer.writeAttribute("onchange", idjs + "_changeIntervalUnit(" + index + ",this.value);", null);
        writer.writeAttribute("name", bandInfo.getClientId(context) + "selectone", "clientId");
        writer.writeAttribute("title", "Change the interval unit", "title");
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
    public static void writeInputDateText(final ResponseWriter writer, final UITimeLine comp,
            final FacesContext context, final String style ) throws IOException {
        final String idjs = comp.getJsObject();
        writer.startElement("input", comp);
        writer.writeAttribute("id", idjs + "_inputdate", "id");
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("title", "Enter a date in format YYYY-mm-ddTHH:MM:ss to center the timeline.", "title");
        writer.writeAttribute("onchange", idjs + "_centerToDate();", null);
        writer.writeAttribute("name", idjs + "_inputdate", null);
        writer.writeAttribute("style", style, null);
        writer.endElement("input");

        writer.write(new StringBuilder("<script>")
                .append("function ").append(idjs).append("_centerToDate(){")
                .append("var valdate = document.getElementById('").append(idjs).append("_inputdate').value;")
                .append("var dateInput = Timeline.DateTime.parseIso8601DateTime(valdate);")
                .append("if (dateInput instanceof Date) {")
                .append(idjs).append("_tl.getBand(0).scrollToCenter(dateInput);")
                .append("} return false;}")
                .append("</script>")
                .toString());
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
    public static List<UIHotZoneBandInfo> getVisibleBandsList(final FacesContext context, final UITimeLine timeline) {
        final List<UIHotZoneBandInfo> results = new ArrayList<UIHotZoneBandInfo>();
        if (timeline != null) {
            for(final UIComponent child : timeline.getChildren()) {
                if (child instanceof UIHotZoneBandInfo && ! ((UIHotZoneBandInfo) child).isHidden()) {
                    results.add((UIHotZoneBandInfo) child);
                }
            }
        }
        return results;
    }
}
