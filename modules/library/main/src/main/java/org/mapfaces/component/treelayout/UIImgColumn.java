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

package org.mapfaces.component.treelayout;

import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UIColumnBase;

/**
 * @author Olivier Terral.
 */
public class UIImgColumn extends UIColumnBase {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLImgColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";    
    // =========== ATTRIBUTES ================================================== //
    private String icon;
    private String defaultImg = "/resource.jsf?r=/org/mapfaces/resources/img/calendar_select.png";
    private String alt = "No image";
    private String title = "";
    private String styleImg = "";
    // =========== ATTRIBUTES ACCESSORS ======================================== //
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    public String getHeaderIcon() {
        return icon;
    }

    public void setHeaderIcon(String icon) {
        this.icon = icon;
    }
    // =========== FONCTIONS ======================================== //
    //Override methods
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = icon;
        values[2] = defaultImg;
        values[3] = alt;
        values[4] = title;
        values[5] = styleImg;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        icon = (String) values[1];
        defaultImg = (String) values[2];
        alt = (String) values[3];
        title = (String) values[4];
        styleImg = (String) values[5];
        
    }

    public String getImg() {
        return defaultImg;
    }

    public void setImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHeaderTitle() {
        return title;
    }

    public void setHeaderTitle(String title) {
        this.title = title;
    }

    public String getStyleImg() {
        return styleImg;
    }

    public void setStyleImg(String styleImg) {
        this.styleImg = styleImg;
    }
}
