/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
