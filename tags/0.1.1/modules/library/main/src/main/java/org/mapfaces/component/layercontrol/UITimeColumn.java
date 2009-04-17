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

import org.mapfaces.component.treelayout.UIImgColumn;

/**
 * @author Olivier Terral (Geomatys)
 */
public class UITimeColumn extends UIImgColumn {

    private static final long serialVersionUID = -1878798978545632171L;
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.TimeColumn";
    private static final String FAMILY = "org.mapfaces.treelayout.Column";
    /**
     * This property is used only in LayerControlRenderer to define a basic id for this Column.
     */
    private static final String layerProperty = "Time";

    private String layerId;
    private String imgData = "/resource.jsf?r=/org/mapfaces/resources/img/calendar_select.png";

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(final String layerId) {
        this.layerId = layerId;
    }

    public String getLayerProperty() {
        return layerProperty;
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

    /**
     * {@inheritDoc }
     */
    @Override
    public String getImg() {
        return imgData;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setImg(final String imgData) {
        this.imgData = imgData;
    }
}
