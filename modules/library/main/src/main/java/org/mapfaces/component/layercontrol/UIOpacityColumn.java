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

import org.mapfaces.component.treelayout.UIColumn;
import org.mapfaces.component.treelayout.UISelectOneMenuColumn;



    
    
public class UIOpacityColumn extends UISelectOneMenuColumn {
    
    private static final long serialVersionUID = -1878798978545632171L;
    
    
    private final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.OpacityColumn";
    
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    
    private String layerId;
    private final String layerProperty = "Opacity";
    
    
     /**
     * Add extra parameter like this
     * 
     */
    
    private String title = " Opacity ";
    private String itemsLabels = "0/0.1/0.2/0.3/0.4/0.5/0.6/0.7/0.8/0.9/1";
    private String itemsValues = "0/0.1/0.2/0.3/0.4/0.5/0.6/0.7/0.8/0.9/1";
    
    
    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getLayerProperty() {
        return layerProperty;
    }

    

    public String getHeaderTitle() {
        return title;
    }

    public void setHeaderTitle(String title) {
        this.title = title;
    }

    public String getItemsLabels() {
        return itemsLabels;
    }

    public void setItemsLabels(String itemsLabels) {
        this.itemsLabels = itemsLabels;
    }

    public String getItemsValues() {
        return itemsValues;
    }

    public void setItemsValues(String itemsValues) {
        this.itemsValues = itemsValues;
    }
    
    @Override
    public String getFamily() {
        return FAMILY;
    }


    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
}
