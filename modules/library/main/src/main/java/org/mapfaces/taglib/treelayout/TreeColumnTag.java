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
public class TreeColumnTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression value = null;
    private ValueExpression width = null;
    private ValueExpression debug = null;
    private final String TREECOLUMN_COMP_TYPE = "org.mapfaces.treelayout.treetable.treepanel.TreeColumn";
    private final String TREECOLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.treepanel.HTMLTreeColumn";

    public ValueExpression getHeader() {
        return header;
    }

    public void setHeader(ValueExpression aHeader) {
        header = aHeader;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression Width) {
        width = Width;
    }

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
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
        component.setValueExpression("value", getValue());
        component.setValueExpression("width", width);
        component.setValueExpression("debug", getDebug());
    }

    @Override
    public void release() {
        super.release();
        setHeader(null);
        setValue(null);
        setWidth(null);
        setDebug(null);
    }

    @Override
    public String getComponentType() {
        return TREECOLUMN_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREECOLUMN_RENDERER_TYPE;
    }
}
