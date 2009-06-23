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
package org.mapfaces.component.datatable;

import javax.faces.component.UIColumn;
import javax.faces.context.FacesContext;

/**
 * @author Kevin Delfour (IRD)
 */
public class UIColumns extends UIColumn {

    private static final String FAMILY = "org.mapfaces.datatable.Columns";
    /* Fields */
    private String axis;

    /* Accessors */
    /**
     * @return the axis
     */
    public String getAxis() {
        return axis;
    }

    /**
     * @param axis the axis to set
     */
    public void setAxis(String axis) {
        this.axis = axis;
    }
    /* Methods */
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = getAxis();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setAxis((String) values[1]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }
    
}
