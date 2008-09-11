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

package org.mapfaces.taglib.treelayout;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class CheckColumnTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression icon = null;
    private ValueExpression value = null;
    private ValueExpression width = null;
    private ValueExpression debug = null;

    private final String COLUMN_COMP_TYPE = "org.mapfaces.treelayout.treetable.treepanel.CheckColumn";
    private final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.treepanel.HTMLCheckColumn";

    public ValueExpression getHeader() {
        return header;
    }

    /**
     * 
     * @param header
     */
    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public ValueExpression getIcon() {
        return icon;
    }

    public void setIcon(ValueExpression icon) {
        this.icon = icon;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }
    
     /**
     * @return the debug
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("icon", getIcon());
        component.setValueExpression("value", getValue());
        component.setValueExpression("width", getWidth());
        component.setValueExpression("debug", getDebug());
    }

    @Override
    public void release() {
        super.release();
        setValue(null);
        setIcon(null);
        setHeader(null);
        setWidth(null);
        setDebug(null);
    }

    @Override
    public String getComponentType() {
        return COLUMN_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
}
