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

import javax.faces.component.UIComponent;

public class LayerControlTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.LayerControl";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.LayerControl";

    /**
     * Add extra parameter like this
     * 
     */
    //private ValueExpression value = null;
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    public void release() {
        super.release();
    //value = null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
    //component.setValueExpression("value",value);
    }
    /**
     *Add setters for extra parameters here 
     */
}
