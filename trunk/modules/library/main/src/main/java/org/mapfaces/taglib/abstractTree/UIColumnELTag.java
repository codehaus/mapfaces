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
package org.mapfaces.taglib.abstractTree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mapfaces.taglib.abstractTree.UITreeComponentELTag;

/**
 * <p>UIColumnELTag is the base class for all JSP tags that correspond to a Tree column Component instance in the view.</p>
 * @author kdelfour
 */
public abstract class UIColumnELTag extends UITreeComponentELTag {

    /* Fields */
    private ValueExpression header = null;
    private ValueExpression width = null;
    private ValueExpression icon = null;
    private ValueExpression styleHeader = null;

    /* Accessors */
    /**
     * Accessor for header
     * @return the header title
     */
    public ValueExpression getHeader() {
        return header;
    }

    /**
     * Mutator for header
     * @param header New value for header
     */
    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    /**
     * Accessor for width
     * @return width value
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * Mutator for width
     * @param width New value for width
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * Accessor for icon
     * @return the icon href
     */
    public ValueExpression getIcon() {
        return icon;
    }

    /**
     * Mutator for icon
     * @param icon New value for icon href
     */
    public void setIcon(ValueExpression icon) {
        this.icon = icon;
    }

    /**
     * Accessor for styelHeader
     * @return styleHeader value
     */
    public ValueExpression getStyleHeader() {
        return styleHeader;
    }

    /**
     * Mutator for styleHeader
     * @param styleHeader New value for styleHeader
     */
    public void setStyleHeader(ValueExpression styleHeader) {
        this.styleHeader = styleHeader;
    }

    /* Methods*/
    /**
     * @override setProperties in class TreeELTag 
     * @param component
     */
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

    /**
     * @override release in class TreeELTag 
     */
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

    /* Abstracts methods*/
    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the component type for the component that is or will be bound to this tag.
     */
    @Override
    public abstract String getComponentType();

    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the rendererType property that selects the Renderer to be used for encoding this component, 
     * or null to ask the component to render itself directly
     */
    @Override
    public abstract String getRendererType();
}
