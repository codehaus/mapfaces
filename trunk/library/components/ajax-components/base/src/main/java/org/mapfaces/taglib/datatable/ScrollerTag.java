/*
 *    Mapfaces - http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
package org.mapfaces.taglib.datatable;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Kevin Delfour (IRD)
 */
public class ScrollerTag extends UIComponentELTag {

    public static final String COMP_TYPE = "org.mapfaces.Datatable.Scroller";
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.datatable.HTMLScroller";
    /* Fields */
    private ValueExpression actionListener = null;
    private ValueExpression navFacetOrientation = null;
    private ValueExpression attachedTo = null;

    /* Accessors */
    /**
     * @return the actionListener
     */
    public ValueExpression getActionListener() {
        return actionListener;
    }

    /**
     * @param actionListener the actionListener to set
     */
    public void setActionListener(ValueExpression actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * @return the navFacetOrientation
     */
    public ValueExpression getNavFacetOrientation() {
        return navFacetOrientation;
    }

    /**
     * @param navFacetOrientation the navFacetOrientation to set
     */
    public void setNavFacetOrientation(ValueExpression navFacetOrientation) {
        this.navFacetOrientation = navFacetOrientation;
    }

    /**
     * @return the attachedTo
     */
    public ValueExpression getAttachedTo() {
        return attachedTo;
    }

    /**
     * @param attachedTo the attachedTo to set
     */
    public void setAttachedTo(ValueExpression attachedTo) {
        this.attachedTo = attachedTo;
    }

    /* Methods*/
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void setProperties(final UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("actionListener",actionListener);
        component.setValueExpression("navFacetOrientation",navFacetOrientation);
        component.setValueExpression("attachedTo",attachedTo);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        super.release();
        setActionListener(null);
        setNavFacetOrientation(null);
        setAttachedTo(null);
    }
}
