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
 *
 * @author kdelfour
 */
public class UITabPanel extends UIComponentBase implements Serializable {

    private static final long serialVersionUID = 4054363322134169900L;
    private final String TABPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTabPanel";
    private final String TABPANEL_COMP_FAMILY = "javax.faces.Output";
    // =========== ATTRIBUTES ================================================== //
    private String width;
    private String height;
    private String title;
    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getFamily() {
        return TABPANEL_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TABPANEL_RENDERER_TYPE;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = getWidth();
        values[2] = getHeight();
        values[3] = getTitle();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setWidth((String) values[1]);
        setHeight((String) values[2]);
        setTitle((String) values[3]);
    }
}
