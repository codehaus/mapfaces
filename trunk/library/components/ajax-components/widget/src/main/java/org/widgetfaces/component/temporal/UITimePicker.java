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

/**
 * This class is a User Interface used to parameter the TimePicker component.
 * @author leopratlong (Geomatys)
 * @since 0.2
 */
public class UITimePicker extends UIInput {

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
     * Define if we have to load the MooTools library on the View page.
     */
    private boolean loadMootools = true;
    /**
     * Define if we have to load the No_Gray TimePicker library on the View page.
     */
    private boolean loadJs = true;
    /**
     * Define the css Style of the component.
     */
    private String style;
    /**
     * Define the CSS Class of the component.
     */
    private String styleClass;
    /**
     * Define the position of the Output Label
     */
    private boolean outputTop;
    /**
     * Flag that indicates if the css resources will be loaded or not.
     */
    private boolean loadCss = true;

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
        final Object values[] = new Object[8];
        values[0] = super.saveState(context);
        values[1] = this.getValue();
        values[2] = this.isLoadMootools();
        values[3] = this.isLoadJs();
        values[4] = this.getStyle();
        values[5] = this.getStyleClass();
        values[6] = this.isOutputTop();
        values[7] = this.isLoadCss();
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
        this.setLoadMootools((Boolean) values[2]);
        this.setLoadJs((Boolean) values[3]);
        this.setStyle((String) values[4]);
        this.setStyleClass((String) values[5]);
        this.setOutputTop((Boolean) values[6]);
        this.setLoadCss((Boolean) values[7]);
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
     * @return the loadMootools
     */
    public boolean isLoadMootools() {
        return loadMootools;
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(boolean loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @return the loadJs
     */
    public boolean isLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
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
     * @return the styleClass
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the outputTop
     */
    public boolean isOutputTop() {
        return outputTop;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setOutputTop(boolean outputTop) {
        this.outputTop = outputTop;
    }

    /**
     * @return the loadCss
     */
    public boolean isLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(boolean loadCss) {
        this.loadCss = loadCss;
    }
}
