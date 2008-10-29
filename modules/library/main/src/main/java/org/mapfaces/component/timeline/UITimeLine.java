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

import java.util.List;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import org.mapfaces.models.timeline.Event;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class UITimeLine extends UICommand {

    public static final String FAMILIY = "org.mapfaces.component.TimeLine";
    /**
     * List of the events for the timeline.
     */
    private List<Event> events;
    /**
     * Name of the js object when the timeline is defined
     */
    private String jsObject;
    /**
     * Flag that indicates if the slider zoom subcomponent will be displayed or not.
     */
    private boolean sliderZoom;
    /**
     * Flag that indicates if the input date subcomponent will be rendered or not.
     */
    private boolean inputDate;
    /**
     * Name of the theme for the bands sub components.
     */
    private String theme ="ClassicTheme";
    /**
     * Flag that indicates if the timeline must be synchronized or not between bandinfos components. Default is true.
     */
    private boolean synchronizeBands = true;
    /**
     * Flag that indicates if the timeline should load the band components dynamically from a context or not.
     */
    private boolean dynamicBands;
    /**
     * Flag that indicates if the timeline should load the control panel.
     */
    private boolean activeControl;
    /**
     * The style of the control panel which is a subcomponent.
     */
    private String styleControlPanel;
    /**
     * The style class of the control panel which is a subcomponent.
     */
    private String styleClassControlPanel;
    /**
     * Flag that indicates if we want use the compressed scripts or the uncompressed.
     */
    private boolean minifyJS = true;
    /**
     * This flag indicates if the timeline should display the input components and sliders of the bands components.
     */
    private boolean enableBandsInput = false;
    /**
     * This is the height of the timeline eg. 170;
     */
    private int height;
    
    public static int TIMELINE_Default_Height = 80;
    
    public String getFamily() {
        return FAMILIY;
    }

    /** Creates a new instance of UITimeLine */
    public UITimeLine() {
        super();
        setRendererType("org.mapfaces.renderkit.HTMLTimeLine"); // this component has a renderer
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getJsObject() {
        return jsObject;
    }

    public void setJsObject(String jsObject) {
        this.jsObject = jsObject;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public boolean isSliderZoom() {
        return sliderZoom;
    }

    public void setSliderZoom(boolean sliderZoom) {
        this.sliderZoom = sliderZoom;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[16];
        values[0] = super.saveState(context);
        values[1] = events;
        values[2] = jsObject;
        values[3] = sliderZoom;
        values[4] = inputDate;
        values[5] = theme;
        values[6] = synchronizeBands;
        values[7] = dynamicBands;
        values[8] = activeControl;
        values[9] = styleControlPanel;
        values[10] = styleClassControlPanel;
        values[11] = minifyJS;
        values[12] = enableBandsInput;
        values[13] = height;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        events = (List<Event>) values[1];
        jsObject = (String) values[2];
        sliderZoom = (Boolean) values[3];
        inputDate = (Boolean) values[4];
        theme = (String) values[5];
        synchronizeBands = (Boolean) values[6];
        dynamicBands = (Boolean) values[7];
        activeControl = (Boolean) values[8];
        styleControlPanel = (String) values[9];
        styleClassControlPanel = (String) values[10];
        minifyJS = (Boolean) values[11];
        enableBandsInput = (Boolean) values[12];
        height = (Integer) values[13];
    }

    public boolean isInputDate() {
        return inputDate;
    }

    public void setInputDate(boolean inputDate) {
        this.inputDate = inputDate;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isSynchronizeBands() {
        return synchronizeBands;
    }

    public void setSynchronizeBands(boolean synchronizeBands) {
        this.synchronizeBands = synchronizeBands;
    }

    public boolean isDynamicBands() {
        return dynamicBands;
    }

    public void setDynamicBands(boolean dynamicBands) {
        this.dynamicBands = dynamicBands;
    }

    public boolean isActiveControl() {
        return activeControl;
    }

    public void setActiveControl(boolean activeControl) {
        this.activeControl = activeControl;
    }

    public String getStyleControlPanel() {
        return styleControlPanel;
    }

    public void setStyleControlPanel(String styleControlPanel) {
        this.styleControlPanel = styleControlPanel;
    }

    public String getStyleClassControlPanel() {
        return styleClassControlPanel;
    }

    public void setStyleClassControlPanel(String styleClassControlPanel) {
        this.styleClassControlPanel = styleClassControlPanel;
    }

    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(boolean minifyJS) {
        this.minifyJS = minifyJS;
    }

    public boolean isEnableBandsInput() {
        return enableBandsInput;
    }

    public void setEnableBandsInput(boolean enableBandsInput) {
        this.enableBandsInput = enableBandsInput;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
