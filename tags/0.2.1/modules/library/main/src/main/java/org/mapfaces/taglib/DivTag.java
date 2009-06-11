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

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class DivTag extends UIComponentELTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Div";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Div";
    
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    /**
     * Option to see debug messages.
     */
    private ValueExpression debug = null;
    
    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("style", style);
        component.setValueExpression("debug", debug);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        styleClass = null;
        style = null;
        debug = null;
    }
    
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

    public ValueExpression getDebug() {
        return debug;
    }

    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

}
