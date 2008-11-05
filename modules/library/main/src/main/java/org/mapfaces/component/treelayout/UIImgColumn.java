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
 * @author Kevin Delfour (Geomatys)
 */
public class UIImgColumn extends UIColumnBase {

    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLImgColumn";
    private static final String FAMILY = "org.mapfaces.treelayout.Column";

    private String icon;
    private String defaultImg = "/resource.jsf?r=/org/mapfaces/resources/img/calendar_select.png";
    private String alt = "No image";
    private String title = "";
    private String styleImg = "";

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHeaderIcon() {
        return icon;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHeaderIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = icon;
        values[2] = defaultImg;
        values[3] = alt;
        values[4] = title;
        values[5] = styleImg;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
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

    public void setImg(final String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(final String alt) {
        this.alt = alt;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHeaderTitle() {
        return title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHeaderTitle(final String title) {
        this.title = title;
    }

    public String getStyleImg() {
        return styleImg;
    }

    public void setStyleImg(final String styleImg) {
        this.styleImg = styleImg;
    }
}
