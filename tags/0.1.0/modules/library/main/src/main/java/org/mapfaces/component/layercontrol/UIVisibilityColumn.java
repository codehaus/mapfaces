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

package org.mapfaces.component.layercontrol;

import java.io.Serializable;

import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.share.interfaces.AjaxInterface;

/**
 * @author Olivier Terral (Geomatys)
 */
public class UIVisibilityColumn extends UICheckColumn  implements AjaxInterface,Serializable {

    private static final long serialVersionUID = 6110685871235636989L;
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.VisibilityColumn";
    private static final String FAMILY = "org.mapfaces.treelayout.Column";
    private static final String LAYER_PROPERTY = "Visible";

    private String layerId;

    public UIVisibilityColumn(){
        super();
    }

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

    public void setLayerId(final String layerId) {
        this.layerId = layerId;
    }

    public String getLayerId(){
        return layerId;
    }

    public String getLayerProperty() {
        return LAYER_PROPERTY;
    }
}
