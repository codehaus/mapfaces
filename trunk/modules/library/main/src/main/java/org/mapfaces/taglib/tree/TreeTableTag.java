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

import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.opengis.coverage.ValueSegment;

/**
 *
 * @author kdelfour
 */
public class TreeTableTag extends UIComponentELTag {

    private ValueExpression value = null;
    private ValueExpression var = null;
    private ValueExpression width = null;
    private ValueExpression height = null;
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private static final String TREETABLE_COMP_TYPE = "org.mapfaces.TreeTable";
    private static final String TREETABLE_RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTreeTable";

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public ValueExpression getVar() {
        return var;
    }

    public void setVar(ValueExpression var) {
        if (var != null) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Map requestMap = ec.getRequestMap();
            requestMap.put("org.treetable.varName", var);
        }
        this.var = var;
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
    public String getComponentType() {
        return TREETABLE_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREETABLE_RENDERER_TYPE;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("value", value);
        component.setValueExpression("var", var);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
        component.setValueExpression("debug", debug);
        component.setValueExpression("style", getStyle());
        component.setValueExpression("styleClass", getStyleClass());
    }

    @Override
    public void release() {
        super.release();
        setId(null);
        setValue(null);
        setVar(null);
        setWidth(null);
        setHeight(null);
        setDebug(null);
        setStyle(null);
        setStyleClass(null);
    }
}

