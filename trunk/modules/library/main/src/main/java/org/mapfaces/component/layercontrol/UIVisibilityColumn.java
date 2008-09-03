package org.mapfaces.component.layercontrol;

import java.io.Serializable;
import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.share.interfaces.AjaxInterface;

        
public class UIVisibilityColumn extends UICheckColumn  implements AjaxInterface,Serializable {
    
    private static final long serialVersionUID = 6110685871235636989L;

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.html.layercontrol.VisibilityColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    
    private String layerId;
    
    
    public UIVisibilityColumn(){
        super();
    }
    @Override
    public String getFamily() {
        return FAMILY;
    }


    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
    
    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }
   
    public String getLayerId(){
        return layerId;
    }
}
