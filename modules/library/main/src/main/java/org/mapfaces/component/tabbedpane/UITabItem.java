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

package org.mapfaces.component.tabbedpane;

import java.io.Serializable;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 * @author Kevin Delfour.
 */
public class UITabItem extends UIComponentBase implements Serializable {

    private static final long serialVersionUID = 4054363322134169900L;
    private static final String TABITEM_RENDERER_TYPE = "org.mapfaces.renderkit.tabpanel.HTMLTabItem";
    private static final String TABITEM_COMP_FAMILY = "javax.faces.Output";

    private String title;
    private boolean active;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return TABITEM_COMP_FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return TABITEM_RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = getTitle();
        values[2] = isActive();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTitle((String) values[1]);
        setActive((Boolean) values[2]);
    }
}
