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

package org.mapfaces.component;

import javax.faces.context.FacesContext;

/**
 * @author OLivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UILocatorMap extends UIMapPane {

    public static final String FAMILIY = "org.mapfaces.LocatorMap";

    private String  targetContextCompId ;

    /** Creates a new instance of UILocatorMap */
    public UILocatorMap() {
        super();
        setRendererType("org.mapfaces.renderkit.html.LocatorMap"); // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = getTargetContextCompId();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTargetContextCompId((String) values[1]);
    }

    public String getTargetContextCompId() {
        return targetContextCompId;
    }

    public void setTargetContextCompId(final String targetContextCompId) {
        this.targetContextCompId = targetContextCompId;
    }

}
