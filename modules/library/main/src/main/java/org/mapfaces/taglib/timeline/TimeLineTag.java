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
 *
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
        component.setValueExpression("value",value);
        component.setValueExpression("styleClass",styleClass);
        component.setValueExpression("style",style);
        component.setValueExpression("sliderZoom",sliderZoom);
        component.setValueExpression("inputDate",inputDate);
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        
        value = null;
        styleClass = null;
        style = null;
        sliderZoom = null;
        inputDate = null;
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
}
