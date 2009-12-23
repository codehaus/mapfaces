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
import org.mapfaces.component.treetable.UITreeColumn;

/**
 *
 * @author leo pratlong (geomatys)
 */
public class UIComponentSelector extends UIWidgetBase {

    private static final String COMP_FAMILY   = "org.mapfaces.component.ComponentSelector";

    private String type;
    private boolean hasParent;
    private boolean mandatory;

    public UIComponentSelector() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ComponentSelector");
    }

    @Override
    public String getFamily() {
        return COMP_FAMILY;
    }

    @Override
    public Object saveState(FacesContext context) {
        final Object[] values = new Object[4];
        values[0] = super.saveState(context);
        values[1] = getType();
        values[2] = isHasParent();
        values[3] = isMandatory();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        final Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        setType((String) values[1]);
        setHasParent((Boolean) values[2]);
        setMandatory((Boolean) values[3]);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the getParent
     */
    public boolean isHasParent() {
        return hasParent;
    }

    /**
     * @param getParent the getParent to set
     */
    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
