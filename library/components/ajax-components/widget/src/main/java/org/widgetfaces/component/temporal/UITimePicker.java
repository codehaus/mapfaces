/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.widgetfaces.component.temporal;

import java.util.Date;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UITemporal;

/**
 * This class is an User Interface used to parameter the TimePicker component.
 * @author leo pratlong (Geomatys)
 * @since 0.2
 */
public class UITimePicker extends UITemporal {

    /**
     * Define the family of the component.
     */
    public static final String FAMILY = "org.mapfaces.Timepicker";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTimepicker";
    /**
     * Define the date value of the time picker component.
     */
    private Date value;
    /**
     * Define the css Style of the component.
     */
    private String style;
    /**
     * Define the target input.
     */
    private String targetInput;

    /**
     * Default constructor.
     * Create a new instance of UITimepicker
     */
    public UITimePicker() {
        super();
        setRendererType(RENDERER_TYPE);
    }

    /**
     * Save the state of the component (members and values).
     * @param context FacesContext of the component.
     * @return Object Array of object containing the values of the component;
     */
    @Override
    public Object saveState(FacesContext context) {
        final Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = this.getValue();
        values[2] = this.getStyle();
        values[3] = this.getTargetInput();
        return values;
    }

    /**
     * Restore the state of the component (members and values).
     * @param context FacesContext of the component
     * @param state Values of the component to restore.
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        this.setValue((Date) values[1]);
        this.setStyle((String) values[2]);
        this.setTargetInput((String) values[3]);
    }

    /**
     * @return the FAMILY
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Date value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public Date getValue() {
        return value;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the outputTop
     */
    public String getTargetInput() {
        return targetInput;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setTargetInput(String targetInput) {
        this.targetInput = targetInput;
    }
}
