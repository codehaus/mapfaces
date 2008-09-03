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

    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = getHeader();
        values[2] = getHide();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setHeader((String) values[1]);
        setHide((String) values[2]);
    }

    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}