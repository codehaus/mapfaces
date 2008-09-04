package org.mapfaces.component.abstractTree;

import java.io.Serializable;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public abstract class UIAbstractTreeNodeInfo extends UIOutput implements Serializable {

    // =========== ATTRIBUTES ================================================== //
    private String header;
    private String hide;
    private boolean debug;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = getHeader();
        values[2] = getHide();
        values[3] = getDebug();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setHeader((String) values[1]);
        setHide((String) values[2]);
        setDebug((Boolean)values[3]);
    }

    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();

    
}