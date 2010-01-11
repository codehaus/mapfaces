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
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class ExtPanelTag extends UIComponentELTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.ExtPanel";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.ExtPanel";
    
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    /**
     * Option to see debug messages.
     */
    private ValueExpression debug = null;
    
    private ValueExpression title = null;
    
    private ValueExpression headerStyle = null;
    
    private ValueExpression headerStyleClass = null;
    
    private ValueExpression bodyStyle = null;
    
    private ValueExpression bodyStyleClass = null;
    
    private ValueExpression width = null;
    
    private ValueExpression height = null;
    
    
    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("style", style);
        component.setValueExpression("debug", debug);
        component.setValueExpression("title", title);
        component.setValueExpression("headerStyle", headerStyle);
        component.setValueExpression("headerStyleClass", headerStyleClass);
        component.setValueExpression("bodyStyle", bodyStyle);
        component.setValueExpression("bodyStyleClass", bodyStyleClass);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        styleClass = null;
        style = null;
        debug = null;
        title = null;
        headerStyle = null;
        headerStyleClass = null;
        bodyStyle = null;
        bodyStyleClass = null;
        width = null;
        height = null;
    }
    
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

    public ValueExpression getDebug() {
        return debug;
    }

    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    public ValueExpression getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(ValueExpression headerStyle) {
        this.headerStyle = headerStyle;
    }

    public ValueExpression getHeaderStyleClass() {
        return headerStyleClass;
    }

    public void setHeaderStyleClass(ValueExpression headerStyleClass) {
        this.headerStyleClass = headerStyleClass;
    }

    public ValueExpression getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(ValueExpression bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public ValueExpression getBodyStyleClass() {
        return bodyStyleClass;
    }

    public void setBodyStyleClass(ValueExpression bodyStyleClass) {
        this.bodyStyleClass = bodyStyleClass;
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

}
