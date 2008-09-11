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
 * 
 * @author Olivier Terral.
 */
public class MapSizeTag extends WidgetBaseTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.MapSize";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.MapSize";
    
    private ValueExpression title = null;
    
    private ValueExpression itemsValues = null;
    
    private ValueExpression itemsLabels = null;
    
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
        title = null;
        itemsLabels = null;
        itemsValues = null;        
    }
    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);        
        component.setValueExpression("title",title);
        component.setValueExpression("itemsLabels",itemsLabels);
        component.setValueExpression("itemsValues",itemsValues);
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    public void setItemsValues(ValueExpression itemsValues) {
        this.itemsValues = itemsValues;
    }

    public void setItemsLabels(ValueExpression itemsLabels) {
        this.itemsLabels = itemsLabels;
    }
    
}
