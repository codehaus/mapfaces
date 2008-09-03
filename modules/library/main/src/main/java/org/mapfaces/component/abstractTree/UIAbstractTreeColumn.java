package org.mapfaces.component.abstractTree;

import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import java.io.Serializable;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public abstract class UIAbstractTreeColumn extends UIOutput implements AjaxInterface, Serializable {

    private boolean alreadyRender = false;
    
    // =========== ATTRIBUTES ================================================== //
    private String header;
    private String width;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    // =========== FONCTIONS ======================================== //
    public boolean isAlreadyRender() {
        return alreadyRender;
    }

    public void setAlreadyRender(boolean alreadyRender) {
        this.alreadyRender = alreadyRender;
    }

    //Override methods
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = alreadyRender;
        values[2] = getHeader();
        values[3] = getWidth();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        alreadyRender = (Boolean) values[1];
        header = ((String) values[2]);
        width = ((String) values[3]);
    }

    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
