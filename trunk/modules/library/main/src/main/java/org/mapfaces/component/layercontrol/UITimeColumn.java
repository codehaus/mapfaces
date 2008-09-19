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

public class UITimeColumn extends UIImgColumn {

    private static final long serialVersionUID = -1878798978545632171L;
    private final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.TimeColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    private String layerId;
    private final String layerProperty = "Time";
    private String imgData = "resource.jsf?r=/org/mapfaces/resources/img/calendar_select.png";

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getLayerProperty() {
        return layerProperty;
    }

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    public String getImg() {
        return imgData;
    }

    public void setImg(String imgData) {
        this.imgData = imgData;
    }
}
