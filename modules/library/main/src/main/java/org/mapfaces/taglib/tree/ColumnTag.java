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
package org.mapfaces.taglib.tree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class ColumnTag extends UIComponentELTag {

    public static String getCOLUMN_COMP_TYPE() {
        return COLUMN_COMP_TYPE;
    }
    private ValueExpression header = null;
    private ValueExpression width = null;
    private ValueExpression icon = null;
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private ValueExpression styleHeader = null;
    private static final String COLUMN_COMP_TYPE = "org.mapfaces.treetable.treepanel.Column";
    private static final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLColumn";

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

    public ValueExpression getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * @return the icon
     */
    public ValueExpression getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(ValueExpression icon) {
        this.icon = icon;
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

    public ValueExpression getStyleHeader() {
        return styleHeader;
    }

    public void setStyleHeader(ValueExpression styleHeader) {
        this.styleHeader = styleHeader;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("width", getWidth());
        component.setValueExpression("icon", getIcon());
        component.setValueExpression("debug", getDebug());
        component.setValueExpression("style", getStyle());
        component.setValueExpression("styleClass", getStyleClass());
        component.setValueExpression("styleHeader", getStyleHeader());
    }

    @Override
    public void release() {
        super.release();
        setHeader(null);
        setWidth(null);
        setIcon(null);
        setDebug(null);
        setStyle(null);
        setStyleClass(null);
        setStyleHeader(null);
    }

    @Override
    public String getComponentType() {
        return getCOLUMN_COMP_TYPE();
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
}
