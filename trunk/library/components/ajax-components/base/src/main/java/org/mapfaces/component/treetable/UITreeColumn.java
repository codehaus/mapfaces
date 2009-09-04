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

package org.mapfaces.component.treetable;

import javax.faces.component.UIColumn;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public class UITreeColumn extends UIColumn {

    private static final String FAMILY = "org.mapfaces.component.tree.TreeColumn";
    private static final String RENDERER_TYPE = null;
    private boolean viewControls = false;
    private String width;

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = isViewControls();
        values[2] = getWidth();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setViewControls((Boolean) values[1]);
        setWidth((String) values[2]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @return the viewControls
     */
    public boolean isViewControls() {
        return viewControls;
    }

    /**
     * @param viewControls the viewControls to set
     */
    public void setViewControls(boolean viewControls) {
        this.viewControls = viewControls;
    }

    /**
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }
}
