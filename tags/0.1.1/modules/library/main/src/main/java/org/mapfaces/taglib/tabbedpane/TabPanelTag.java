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
public class TabPanelTag extends UIComponentELTag {

    private static final String TABPANEL_COMP_TYPE = "org.mapfaces.tabbedpane.TabPanel";
    private static final String TABPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTabPanel";
    private ValueExpression title = null;
    private ValueExpression width = null;
    private ValueExpression height = null;

  
    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    public ValueExpression getHeight() {
        return height;
    }

    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return TABPANEL_COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return TABPANEL_RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setProperties(final UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", title);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        super.release();
        setTitle(null);
        setWidth(null);
        setHeight(null);
    }
}

