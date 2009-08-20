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

/**
 * @author Olivier Terral (Geomatys).
 */
public class PopupTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Popup";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Popup";
    
    private ValueExpression iframe = null;

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
        component.setValueExpression("iframe", iframe);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        iframe = null;
    }

    public ValueExpression getIframe() {
        return iframe;
    }

    public void setIframe(ValueExpression iframe) {
        this.iframe = iframe;
    }

}
