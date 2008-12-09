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

package org.mapfaces.taglib.tabbedpane;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * @author Kevin Delfour
 */
public class TabItemTag extends UIComponentELTag {

    private static final String TABITEM_COMP_TYPE = "org.mapfaces.tabbedpane.tabpanel.TabItem";
    private static final String TABITEM_RENDERER_TYPE = "org.mapfaces.renderkit.tabpanel.HTMLTabItem";
    private ValueExpression title = null;

  
    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return TABITEM_COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return TABITEM_RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", title);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        super.release();
        setTitle(null);
    }
}

