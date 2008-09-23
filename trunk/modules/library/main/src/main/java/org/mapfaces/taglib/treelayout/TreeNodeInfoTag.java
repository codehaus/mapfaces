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
public class TreeNodeInfoTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression hide = null;
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private final String TREENODEINFO_COMP_TYPE = "org.mapfaces.treelayout.TreeNodeInfo";
    private final String TREENODEINFO_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.treepanel.HTMLTreeNodeInfo";

    public ValueExpression getHeader() {
        return header;
    }

    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    public ValueExpression getHide() {
        return hide;
    }

    public void setHide(ValueExpression hide) {
        this.hide = hide;
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

    public ValueExpression getStyle() {
        return style;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public ValueExpression getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("hide", getHide());
        component.setValueExpression("debug", getDebug());
        component.setValueExpression("style", getStyle());
        component.setValueExpression("styleClass", getStyleClass());
    }

    @Override
    public void release() {
        super.release();
        setHeader(null);
        setHide(null);
        setDebug(null);
        setStyle(null);
        setStyleClass(null);
    }

    @Override
    public String getComponentType() {
        return TREENODEINFO_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREENODEINFO_RENDERER_TYPE;
    }
}
