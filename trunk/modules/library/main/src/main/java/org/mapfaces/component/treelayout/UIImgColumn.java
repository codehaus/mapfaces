package org.mapfaces.component.treelayout;

import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import java.io.Serializable;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UIAbstractColumn;


public class UIImgColumn extends UIAbstractColumn {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLImgColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";

    // =========== ATTRIBUTES ================================================== //
    private String icon;
    private String defaultImg = "/resource.jsf?r=/org/mapfaces/resources/img/calendar_select.png";
    private String alt = "No image";
    
    // =========== ATTRIBUTES ACCESSORS ======================================== //
    @Override
    public String getFamily() {
        return FAMILY;
    }


    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

  
    
    // =========== FONCTIONS ======================================== //
    //Override methods
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
    }


    public String getImg() {
        return defaultImg;
    }

    public void setImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
