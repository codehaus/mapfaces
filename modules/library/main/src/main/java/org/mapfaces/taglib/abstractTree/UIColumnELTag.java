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

/**
 * <p>UIColumnELTag is the base class for all JSP tags that correspond to a Tree column Component instance in the view.</p>
 * 
 * @author Kevin Delfour
 */
public abstract class UIColumnELTag extends UITreeComponentELTag {

    /* Fields */
    private ValueExpression headerTitle = null;
    private ValueExpression headerIcon = null;
    private ValueExpression width = null;
    private ValueExpression styleHeader = null;

    /* Accessors */
    /**
     * Accessor for title
     * @return the title value
     */
    public ValueExpression getHeaderTitle() {
        return headerTitle;
    }

    /**
     * Mutator for title
     * @param title New value for title
     */
    public void setHeaderTitle(ValueExpression title) {
        this.headerTitle = title;
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
    public ValueExpression getHeaderIcon() {
        return headerIcon;
    }

    /**
     * Mutator for icon
     * @param icon New value for icon href
     */
    public void setHeaderIcon(ValueExpression icon) {
        this.headerIcon = icon;
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
        component.setValueExpression("headerTitle", getHeaderTitle());
        component.setValueExpression("width", getWidth());
        component.setValueExpression("headerIcon", getHeaderIcon());
        component.setValueExpression("styleHeader", getStyleHeader());
    }

    /**
     * @override release in class TreeELTag 
     */
    @Override
    public void release() {
        super.release();
        setHeaderTitle(null);
        setWidth(null);
        setHeaderIcon(null);
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
