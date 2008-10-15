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

package org.mapfaces.taglib.timeline;

import java.util.Date;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class HotZoneBandInfoTag extends UIComponentELTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Timeline.HotZoneBandInfo";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.Timeline.HTMLHotZoneBandInfo";
    /**
     * The Date attribute represented by java.util.Date .
     */
    private ValueExpression date = null;
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    /**
     * The width of this BandInfo in percentage.
     */
    private ValueExpression width = null;
    /**
     * intervalUnit can take one of DateTime values : {HOUR, DAY, WEEK, MONTH, YEAR}.
     */
    private ValueExpression intervalUnit = null;
    /**
     * intervalPixels for this bandInfo.
     */
    private ValueExpression intervalPixels = null;
    /**
     * timeZone for this bandInfo.
     */
    private ValueExpression timeZone = null;
    /**
     * showEventText flag that indicates if the event text wil be rendered or not.
     */
    private ValueExpression showEventText = null;
    /**
     * trackHeight.
     */
    private ValueExpression trackHeight = null;
    /**
     * trackGap.
     */
    private ValueExpression trackGap = null;
    /**
     * theme.
     */
    private ValueExpression theme = null;
    /**
     * flag that indicates if this bandInfo component will contains an input component to choice intervals.
     */
    private ValueExpression inputInterval = null;
    /**
     * flag that indicates if this bandInfo component will contains a slider Input component to choice intervals.
     */
    private ValueExpression sliderInput = null;
    

    public String getComponentType() {
        return COMP_TYPE;
    }

    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        UIHotZoneBandInfo bandInfo = (UIHotZoneBandInfo) component;

        if (date != null) {
            if (!date.isLiteralText()) {
                component.setValueExpression("date", date);
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                ExpressionFactory ef = context.getApplication().getExpressionFactory();
                ValueExpression vex = ef.createValueExpression(context.getELContext(), date.getExpressionString(), java.util.Date.class);
                bandInfo.setDate((Date) vex.getValue(context.getELContext()));
            }
        }

        component.setValueExpression("intervalPixels", intervalPixels);
        component.setValueExpression("intervalUnit", intervalUnit);
        component.setValueExpression("showEventText", showEventText);
        component.setValueExpression("style", style);
        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("timeZone", timeZone);
        component.setValueExpression("trackGap", trackGap);
        component.setValueExpression("trackHeight", trackHeight);
        component.setValueExpression("width", width);
        component.setValueExpression("theme", theme);
        component.setValueExpression("inputInterval", inputInterval);
        component.setValueExpression("sliderInput", sliderInput);

        if (inputInterval != null) {
            bandInfo.setInputInterval((Boolean) inputInterval.getValue(FacesContext.getCurrentInstance().getELContext()));
        }

    }

    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        setDate(null);
        setStyleClass(null);
        setStyle(null);
        setIntervalPixels(null);
        setIntervalUnit(null);
        setShowEventText(null);
        setTrackGap(null);
        setTrackHeight(null);
        setWidth(null);
        setTimeZone(null);
        setTheme(null);
        setInputInterval(null);
        setSliderInput(null);
    }

    public void setDate(ValueExpression date) {
        if (date != null) {
            this.date = date;
        }
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    public void setIntervalUnit(ValueExpression intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public void setIntervalPixels(ValueExpression intervalPixels) {
        this.intervalPixels = intervalPixels;
    }

    public void setTimeZone(ValueExpression timeZone) {
        this.timeZone = timeZone;
    }

    public void setShowEventText(ValueExpression showEventText) {
        this.showEventText = showEventText;
    }

    public void setTrackHeight(ValueExpression trackHeight) {
        this.trackHeight = trackHeight;
    }

    public void setTrackGap(ValueExpression trackGap) {
        this.trackGap = trackGap;
    }

    public ValueExpression getTheme() {
        return theme;
    }

    public void setTheme(ValueExpression theme) {
        this.theme = theme;
    }

    public ValueExpression getInputInterval() {
        return inputInterval;
    }

    public void setInputInterval(ValueExpression inputInterval) {
        this.inputInterval = inputInterval;
    }

    public ValueExpression getSliderInput() {
        return sliderInput;
    }

    public void setSliderInput(ValueExpression sliderInput) {
        this.sliderInput = sliderInput;
    }
}
