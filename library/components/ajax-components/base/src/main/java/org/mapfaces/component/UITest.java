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
 *
 * @author leo pratlong (geomatys)
 */
public class UITest extends UIWidgetBase {

    private static final String COMP_FAMILY   = "org.mapfaces.component.Test";

    private String value;
    private String name;

    public UITest() {
        super();
        setRendererType("org.mapfaces.renderkit.html.Test");
        
    }

    @Override
    public String getFamily() {
        return COMP_FAMILY;
    }

    @Override
    public Object saveState(FacesContext context) {
        final Object[] values = new Object[3];
        values[0] = super.saveState(context);
        values[1] = this.value;
        values[2] = this.name;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        final Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        value = (String) values[1];
        name = (String) values[2];
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
