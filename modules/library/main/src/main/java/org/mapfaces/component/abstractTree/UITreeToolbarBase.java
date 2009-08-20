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
package org.mapfaces.component.abstractTree;

import javax.faces.context.FacesContext;

/**
 *
 * @author Kevin Delfour (Geomatys)
 */
public abstract class UITreeToolbarBase extends UITreeBase {

    private String styleClassTools;
    private String styleTools;

    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = getStyleTools();
        values[2] = getStyleClassTools();
        return values;
    }

    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setStyleTools((String) values[1]);
        setStyleClassTools((String)values[2]);
    }

    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();

    public String getStyleClassTools() {
        return styleClassTools;
    }

    public void setStyleClassTools(final String styleClassTools) {
        this.styleClassTools = styleClassTools;
    }

    public String getStyleTools() {
        return styleTools;
    }

    public void setStyleTools(final String styleTools) {
        this.styleTools = styleTools;
    }
}
