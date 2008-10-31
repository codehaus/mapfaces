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

public class UILocatorMap extends UIMapPane {

    public static final String FAMILIY = "org.mapfaces.LocatorMap";
    
    private String  targetContextCompId ;
    
    /** Creates a new instance of UILocatorMap */
    public UILocatorMap() {
        super();
        setRendererType("org.mapfaces.renderkit.html.LocatorMap"); // this component has a renderer
    }

    public String getFamily() {
        return FAMILIY;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = getTargetContextCompId();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTargetContextCompId((String) values[1]);
    }

    public String getTargetContextCompId() {
        return targetContextCompId;
    }

    public void setTargetContextCompId(String targetContextCompId) {
        this.targetContextCompId = targetContextCompId;
    }

}
