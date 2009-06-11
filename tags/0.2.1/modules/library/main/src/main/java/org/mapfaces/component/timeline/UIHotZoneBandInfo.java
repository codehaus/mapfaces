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

package org.mapfaces.component.timeline;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.models.timeline.Zone;

/**
 * @author Mehdi Sidhoum.
 */
public class UIHotZoneBandInfo extends UICommand implements StateHolder, Serializable {

    public static final String FAMILIY = "org.mapfaces.component.Timeline.HotZoneBandInfo";

    private List<Zone>  zones;
    private List<Event> events;
    private Event       eventSource;
    private Layer       layer;
    private Date        date;
    private Date        centerDate;
    private String      widthPercentage;
    private String      intervalUnit;
    private Integer     intervalPixels;
    private Integer     width;
    private String      theme;
    private Integer     timeZone;
    private Double      trackHeight;
    private Double      trackGap;
    private String      backgroundColor;
    private String      sliderWidth;
    private boolean     showEventText;
    private boolean     inputInterval;
    private boolean     sliderInput;
    private boolean     hidden;

    /**
     * Name of the js object wher the timeline is defined
     */
    private String jsObject;


    public String getJsObject() {
        return jsObject;
    }
    public void setJsObject(final String jsObject) {
        this.jsObject = jsObject;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /** Creates a new instance of UIHotZoneBandInfo */
    public UIHotZoneBandInfo() {
        super();
        setRendererType("org.mapfaces.renderkit.Timeline.HTMLHotZoneBandInfo"); // this component has a renderer
    }

    public Event getEventSource() {
        if (eventSource == null) {
            final ValueExpression ve = getValueExpression("eventSource");
            if (ve != null) {
                eventSource = (Event) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return eventSource;
    }

    public void setEventSource(final Event eventSource) {
        this.eventSource = eventSource;
    }

    public Date getDate() {
        if (date == null) {
            final ValueExpression ve = getValueExpression("date");
            if (ve != null) {
                date = (Date) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getWidthPercentage() {
        if (widthPercentage == null) {
            final ValueExpression ve = getValueExpression("widthPercentage");
            if (ve != null) {
                widthPercentage = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return widthPercentage;
    }

    public void setWidthPercentage(final String widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public String getIntervalUnit() {
        if (intervalUnit == null) {
            final ValueExpression ve = getValueExpression("intervalUnit");
            if (ve != null) {
                intervalUnit = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return intervalUnit;
    }

    /**
     * This fix the interval unit in the HotZoneBandInfo component, param can take one of this enumeration :
     * {MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR, DECADE, CENTURY, MILLENNIUM }. default is YEAR
     * @param intervalUnit
     */
    public void setIntervalUnit(final String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Integer getIntervalPixels() {
        if (intervalPixels == null) {
            final ValueExpression ve = getValueExpression("intervalPixels");
            if (ve != null) {
                intervalPixels = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return intervalPixels;
    }

    public void setIntervalPixels(final Integer intervalPixels) {
        this.intervalPixels = intervalPixels;
    }

    public Integer getWidth() {
        if (width == null) {
            final ValueExpression ve = getValueExpression("width");
            if (ve != null) {
                width = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return width;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public String getTheme() {
        if (theme == null) {
            final ValueExpression ve = getValueExpression("theme");
            if (ve != null) {
                theme = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return theme;
    }

    public void setTheme(final String theme) {
        this.theme = theme;
    }

    public Integer getTimeZone() {
        if (timeZone == null) {
            final ValueExpression ve = getValueExpression("timeZone");
            if (ve != null) {
                timeZone = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return timeZone;
    }

    public void setTimeZone(final Integer timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isShowEventText() {
        return showEventText;
    }

    public void setShowEventText(final boolean showEventText) {
        this.showEventText = showEventText;
    }

    public Double getTrackHeight() {
        if (trackHeight == null) {
            final ValueExpression ve = getValueExpression("trackHeight");
            if (ve != null) {
                trackHeight = (Double) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return trackHeight;
    }

    public void setTrackHeight(final Double trackHeight) {
        this.trackHeight = trackHeight;
    }

    public Double getTrackGap() {
        if (trackGap == null) {
            final ValueExpression ve = getValueExpression("trackGap");
            if (ve != null) {
                trackGap = (Double) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return trackGap;
    }

    public void setTrackGap(final Double trackGap) {
        this.trackGap = trackGap;
    }

    public boolean isInputInterval() {
        return inputInterval;
    }

    public void setInputInterval(final boolean inputInterval) {
        this.inputInterval = inputInterval;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(final List<Zone> zones) {
        this.zones = zones;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[22];
        values[0] = super.saveState(context);
        values[1] = date;
        values[2] = eventSource;
        values[3] = intervalPixels;
        values[4] = intervalUnit;
        values[5] = showEventText;
        values[6] = theme;
        values[7] = timeZone;
        values[8] = trackGap;
        values[9] = trackHeight;
        values[10] = width;
        values[11] = widthPercentage;
        values[12] = inputInterval;
        values[13] = zones;
        values[14] = sliderInput;
        values[15] = layer;
        values[16] = events;
        values[17] = centerDate;
        values[18] = sliderWidth;
        values[19] = hidden;
        values[20] = backgroundColor;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        date = (Date) values[1];
        eventSource = (Event) values[2];
        intervalPixels = (Integer) values[3];
        intervalUnit = (String) values[4];
        showEventText = (Boolean) values[5];
        theme = (String) values[6];
        timeZone = (Integer) values[7];
        trackGap = (Double) values[8];
        trackHeight = (Double) values[9];
        width = (Integer) values[10];
        widthPercentage = (String) values[11];
        inputInterval = (Boolean) values[12];
        zones = (List<Zone>) values[13];
        sliderInput = (Boolean) values[14];
        layer = (Layer) values[15];
        events = (List<Event>) values[16];
        centerDate = (Date) values[17];
        sliderWidth = (String) values[18];
        hidden = (Boolean) values[19];
        backgroundColor = (String) values[20];
    }

    public boolean isSliderInput() {
        return sliderInput;
    }

    public void setSliderInput(final boolean sliderInput) {
        this.sliderInput = sliderInput;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(final Layer layer) {
        this.layer = layer;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }

    public Date getCenterDate() {
        return centerDate;
    }

    public void setCenterDate(final Date centerDate) {
        this.centerDate = centerDate;
    }

    public String getSliderWidth() {
        return sliderWidth;
    }

    public void setSliderWidth(final String sliderWidth) {
        this.sliderWidth = sliderWidth;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
