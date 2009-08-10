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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * @author Mehdi Sidhoum.
 */
public class TimeLineTag extends UIComponentELTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.TimeLine";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.HTMLTimeLine";
    
    /**
     * The Data in JSON format or List<TimeLineEvent> its represented by java.lang.Object .
     */
    private ValueExpression value = null;
    
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    /**
     * flag that indicates if this timeline component will contains a slider for zoom.
     */
    private ValueExpression sliderZoom = null;
    /**
     * flag that indicates if this timeline component will contains an input date.
     */
    private ValueExpression inputDate = null;
    /**
     * This is a name of a theme for the bandinfos subcomponents.
     */
    private ValueExpression theme = null;
    /**
     * Flag that indicates if the timeline must be synchronized or not between bandinfos components.
     */
    private ValueExpression synchronizeBands = null;
    /**
     * Flag that indicates if the timeline should load the band components dynamically from a context or not.
     */
    private ValueExpression dynamicBands = null;
    /**
     * Flag that indicates if the timeline should load the control panel.
     */
    private ValueExpression activeControl = null;
    /**
     * The style of the control panel which is a subcomponent.
     */
    private ValueExpression styleControlPanel = null;
    /**
     * The style class of the control panel which is a subcomponent.
     */
    private ValueExpression styleClassControlPanel = null;
    /**
     * This is a flag that indicates if we must load compressed scripts or uncompressed.
     */
    private ValueExpression minifyJS = null;
    /**
     * This is a flag that indicates if the timeline allow to its children if they must load the input slider and inputs components.
     */
    private ValueExpression enableBandsInput = null;
    /**
     * This is the height of the timeline eg. 170;
     */
    private ValueExpression height = null;
    /**
     * This is the height of every bandInfo component.
     */
    private ValueExpression bandHeight = null;
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("value",value);
        component.setValueExpression("styleClass",styleClass);
        component.setValueExpression("style",style);
        component.setValueExpression("sliderZoom",sliderZoom);
        component.setValueExpression("inputDate",inputDate);
        component.setValueExpression("theme",theme);
        component.setValueExpression("synchronizeBands",synchronizeBands);
        component.setValueExpression("dynamicBands",dynamicBands);
        component.setValueExpression("activeControl",activeControl);
        component.setValueExpression("styleControlPanel",styleControlPanel);
        component.setValueExpression("styleClassControlPanel",styleClassControlPanel);
        component.setValueExpression("minifyJS",minifyJS);
        component.setValueExpression("enableBandsInput",enableBandsInput);
        component.setValueExpression("height",height);
        component.setValueExpression("bandHeight",bandHeight);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        
        value = null;
        styleClass = null;
        style = null;
        sliderZoom = null;
        inputDate = null;
        theme = null;
        synchronizeBands = null;
        dynamicBands = null;
        activeControl = null;
        styleControlPanel = null;
        styleClassControlPanel = null;
        minifyJS = null;
        enableBandsInput = null;
        height = null;
        bandHeight = null;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public ValueExpression getSliderZoom() {
        return sliderZoom;
    }

    public void setSliderZoom(ValueExpression sliderZoom) {
        this.sliderZoom = sliderZoom;
    }

    public ValueExpression getInputDate() {
        return inputDate;
    }

    public void setInputDate(ValueExpression inputDate) {
        this.inputDate = inputDate;
    }

    public ValueExpression getTheme() {
        return theme;
    }

    public void setTheme(ValueExpression theme) {
        this.theme = theme;
    }

    public ValueExpression getSynchronizeBands() {
        return synchronizeBands;
    }

    public void setSynchronizeBands(ValueExpression synchronizeBands) {
        this.synchronizeBands = synchronizeBands;
    }

    public ValueExpression getDynamicBands() {
        return dynamicBands;
    }

    public void setDynamicBands(ValueExpression dynamicBands) {
        this.dynamicBands = dynamicBands;
    }

    public ValueExpression getActiveControl() {
        return activeControl;
    }

    public void setActiveControl(ValueExpression activeControl) {
        this.activeControl = activeControl;
    }

    public ValueExpression getStyleControlPanel() {
        return styleControlPanel;
    }

    public void setStyleControlPanel(ValueExpression styleControlPanel) {
        this.styleControlPanel = styleControlPanel;
    }

    public ValueExpression getStyleClassControlPanel() {
        return styleClassControlPanel;
    }

    public void setStyleClassControlPanel(ValueExpression styleClassControlPanel) {
        this.styleClassControlPanel = styleClassControlPanel;
    }

    public ValueExpression getMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(ValueExpression minifyJS) {
        this.minifyJS = minifyJS;
    }

    public ValueExpression getEnableBandsInput() {
        return enableBandsInput;
    }

    public void setEnableBandsInput(ValueExpression enableBandsInput) {
        this.enableBandsInput = enableBandsInput;
    }

    public ValueExpression getHeight() {
        return height;
    }

    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    public ValueExpression getBandHeight() {
        return bandHeight;
    }

    public void setBandHeight(ValueExpression bandHeight) {
        this.bandHeight = bandHeight;
    }
}
