/*
 * UIBandInfo.java
 *
 * Created on 10 avril 2008, 17:30
 */
package org.mapfaces.component.timeline;

import java.util.Date;
import javax.el.ValueExpression;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import org.mapfaces.models.timeline.Event;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class UIBandInfo extends UICommand {

    private Event eventSource;
    private Date date;
    private String widthPercentage;
    private String intervalUnit;
    private Integer intervalPixels;
    private Integer width;
    private String theme;
    private Integer timeZone;
    private boolean showEventText;
    private Double trackHeight;
    private Double trackGap;
    public static final String FAMILIY = "org.mapfaces.component.Timeline.BandInfo";
    private boolean inputInterval;


    /**
     * Name of the js object
     * 
     */
    private String jsObject;
    
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /** Creates a new instance of UIBandInfo */
    public UIBandInfo() {
        super();
        setRendererType("org.mapfaces.renderkit.Timeline.HTMLUIBandInfo"); // this component has a renderer
    }

    public Event getEventSource() {
        if (eventSource == null) {
            ValueExpression ve = getValueExpression("eventSource");
            if (ve != null) {
                eventSource = (Event) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return eventSource;
    }

    
    public String getJsObject() {
        return jsObject;
    }
    public void setJsObject(String jsObject) {
        this.jsObject = jsObject;
    }

    public void setEventSource(Event eventSource) {
        this.eventSource = eventSource;
    }

    public Date getDate() {
        if (date == null) {
            ValueExpression ve = getValueExpression("date");
            if (ve != null) {
                date = (Date) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWidthPercentage() {
        if (widthPercentage == null) {
            ValueExpression ve = getValueExpression("widthPercentage");
            if (ve != null) {
                widthPercentage = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return widthPercentage;
    }

    public void setWidthPercentage(String widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public String getIntervalUnit() {
        if (intervalUnit == null) {
            ValueExpression ve = getValueExpression("intervalUnit");
            if (ve != null) {
                intervalUnit = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return intervalUnit;
    }

    /**
     * This fix the interval unit in the BandInfo component, param can take one of this enumeration : 
     * {MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR, DECADE, CENTURY, MILLENNIUM }. default is YEAR 
     * @param intervalUnit
     */
    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Integer getIntervalPixels() {
        if (intervalPixels == null) {
            ValueExpression ve = getValueExpression("intervalPixels");
            if (ve != null) {
                intervalPixels = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return intervalPixels;
    }

    public void setIntervalPixels(Integer intervalPixels) {
        this.intervalPixels = intervalPixels;
    }

    public Integer getWidth() {
        if (width == null) {
            ValueExpression ve = getValueExpression("width");
            if (ve != null) {
                width = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getTheme() {
        if (theme == null) {
            ValueExpression ve = getValueExpression("theme");
            if (ve != null) {
                theme = (String) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getTimeZone() {
        if (timeZone == null) {
            ValueExpression ve = getValueExpression("timeZone");
            if (ve != null) {
                timeZone = (Integer) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isShowEventText() {
        return showEventText;
    }

    public void setShowEventText(boolean showEventText) {
        this.showEventText = showEventText;
    }

    public Double getTrackHeight() {
        if (trackHeight == null) {
            ValueExpression ve = getValueExpression("trackHeight");
            if (ve != null) {
                trackHeight = (Double) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return trackHeight;
    }

    public void setTrackHeight(Double trackHeight) {
        this.trackHeight = trackHeight;
    }

    public Double getTrackGap() {
        if (trackGap == null) {
            ValueExpression ve = getValueExpression("trackGap");
            if (ve != null) {
                trackGap = (Double) (ve.getValue(getFacesContext().getELContext()));
            }
        }
        return trackGap;
    }

    public void setTrackGap(Double trackGap) {
        this.trackGap = trackGap;
    }

    public boolean isInputInterval() {
        return inputInterval;
    }

    public void setInputInterval(boolean inputInterval) {
        this.inputInterval = inputInterval;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[14];
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
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
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
    }
}
