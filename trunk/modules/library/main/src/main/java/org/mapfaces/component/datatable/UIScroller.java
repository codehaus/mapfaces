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
package org.mapfaces.component.datatable;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

/**
 * <ul>
 * <li><b>actionListener</b></li> - method reference to handle an action
 * event generated as a result of clicking on a link that points a particular
 * page in the result-set.
 * <li><b>navFacetOrientation</b></li> - When rendering a widget representing
 * "page navigation" where should the facet markup be rendered in relation to
 * the page navigation widget?  Values are "NORTH", "SOUTH", "EAST", "WEST".
 * Case insensitive. This can be value or a value binding reference expression.
 * <li><b>attachedTo</b></li> - The data grid component for which this
 * acts as a scroller. This can be value or a value binding reference expression.
 * </ul>
 * @author kdelfour
 */
public class UIScroller extends UICommand {

    public static final String FAMILY = "org.mapfaces.datatable.Scroller";
    /* Fields */
    private String actionListener = null;
    private String navFacetOrientation = null;
    private String attachedTo = null;

    /* Accessors */
    /**
     * @param actionListener the actionListener to set
     */
    public void setActionListener(String actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * @return the navFacetOrientation
     */
    public String getNavFacetOrientation() {
        return navFacetOrientation;
    }

    /**
     * @param navFacetOrientation the navFacetOrientation to set
     */
    public void setNavFacetOrientation(String navFacetOrientation) {
        this.navFacetOrientation = navFacetOrientation;
    }

    /**
     * @return the attachedTo
     */
    public String getAttachedTo() {
        return attachedTo;
    }

    /**
     * @param attachedTo the attachedTo to set
     */
    public void setAttachedTo(String attachedTo) {
        this.attachedTo = attachedTo;
    }

    /* Methods */
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = getActionListeners();
        values[2] = getNavFacetOrientation();
        values[3] = getAttachedTo();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setActionListener((String) values[1]);
        setNavFacetOrientation((String) values[2]);
        setAttachedTo((String) values[3]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }
}

