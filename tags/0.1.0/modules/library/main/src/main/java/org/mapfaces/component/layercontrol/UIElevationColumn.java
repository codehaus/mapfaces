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

import org.mapfaces.component.treelayout.UISelectOneMenuColumn;

/**
 * @author Olivier Terral (Geomatys)
 */
public class UIElevationColumn extends UISelectOneMenuColumn {

    private static final long serialVersionUID = -1878798978545632171L;
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.ElevationColumn";
    private static final String FAMILY = "org.mapfaces.treelayout.Column";
    private static final String LAYER_PROPERTY = "Elevation";

    /**
     * Add extra parameter like this
     */
    private String layerId;
    private String title = " Elevation ";
    private String itemsLabels = "5.0,10.0";
    private String itemsValues = "5.0,10.0";

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(final String layerId) {
        this.layerId = layerId;
    }

    public String getLayerProperty() {
        return LAYER_PROPERTY;
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

    /**
     * {@inheritDoc }
     */
    @Override
    public String getItemsLabels() {
        return itemsLabels;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setItemsLabels(final String itemsLabels) {
        this.itemsLabels = itemsLabels;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getItemsValues() {
        return itemsValues;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setItemsValues(final String itemsValues) {
        this.itemsValues = itemsValues;
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

}
