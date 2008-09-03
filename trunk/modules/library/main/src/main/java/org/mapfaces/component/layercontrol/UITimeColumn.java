/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.component.layercontrol;
import org.mapfaces.component.treelayout.UIImgColumn;
import org.mapfaces.component.treelayout.UISelectOneMenuColumn;



    
    
public class UITimeColumn extends UIImgColumn {
    
    private static final long serialVersionUID = -1878798978545632171L;
    
    
    private final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.TimeColumn";
    
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    
    private String layerId;
    private final String layerProperty = "Time";
    
    private String imgData="resource.jsf?r=/org/mapfaces/resources/img/calendar.png";
    
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
