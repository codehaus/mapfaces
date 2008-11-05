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
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIWidget extends UIWidgetBase{

    public static final String FAMILIY = "org.mapfaces.Widget";

    public UIWidget() {
        super();
        if(isDebug())
            System.out.println("[UIWidget] constructor----------------------");
        setRendererType("org.mapfaces.renderkit.html.Widget"); // this component has a renderer
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
    public void restoreState(final FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
    }

}
