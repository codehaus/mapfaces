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
 * @author Mehdi Sidhoum
 */
public class TimeLineControlTag extends UIComponentELTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.TimeLine.TimeLineControl";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.TimeLine.HTMLTimeLineControl";
        
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    
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
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("styleClass",getStyleClass());
        component.setValueExpression("style",getStyle());
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        
        setStyleClass(null);
        setStyle(null);
    }

    public ValueExpression getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public ValueExpression getStyle() {
        return style;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }
}
