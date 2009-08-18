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
public class UIScale extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.Scale";

    /** Creates a new instance of UIScale */
    public UIScale() {
        super();
        setRendererType("org.mapfaces.renderkit.html.Scale"); // this component has a renderer
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
        final Object values[] = new Object[1];
        values[0] = super.saveState(context);
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
    }

}
