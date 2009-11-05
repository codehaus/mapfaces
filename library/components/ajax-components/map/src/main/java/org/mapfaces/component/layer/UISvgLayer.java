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

package org.mapfaces.component.layer;

import org.mapfaces.component.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author leopratlong (Geomatys)
 */
public class UISvgLayer extends UILayer {

    public static final String FAMILIY = "org.mapfaces.SvgLayer";
    private boolean cliToServOnly;

    /** Creates a new instance of UIEditionBar */
    public UISvgLayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.SvgLayer"); // this component has a renderer
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
        values[1] = cliToServOnly;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setCliToServOnly((Boolean) values[1]);
    }

    /**
     * @return the cliToServOnly
     */
    public boolean isCliToServOnly() {
        return cliToServOnly;
    }

    /**
     * @param cliToServOnly the cliToServOnly to set
     */
    public void setCliToServOnly(boolean cliToServOnly) {
        this.cliToServOnly = cliToServOnly;
    }
}
