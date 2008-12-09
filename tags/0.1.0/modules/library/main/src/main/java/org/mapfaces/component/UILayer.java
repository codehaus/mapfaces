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

import java.io.File;
import javax.faces.context.FacesContext;
import org.mapfaces.models.Layer;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UILayer extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.MapPane.Layer";

    private Layer layer;
    /*
     * Name of webapp root element
     */
    private String contextPath;
    /*
     * Directory where image generated by the portrayal servcie were saved
     */
    private File dir;
    /*
     * style of the layer div , this style is modified when we drag the map
     */
    private String style;

    public UILayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.MapPane.Layer");    // this component has a renderer
        if (isDebug()) {
            System.out.println("[UILayer] constructor-----------------");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    public void setContextPath(final String ctxPath) {
        contextPath = ctxPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(final Layer layer) {
        this.layer = layer;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(final File dir) {
        this.dir = dir;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = contextPath;
        values[2] = dir;
        values[3] = layer;
        values[4] = style;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        contextPath = (String) values[1];
        dir = (File) values[2];
        layer = (Layer) values[3];
        style = (String) values[4];
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
