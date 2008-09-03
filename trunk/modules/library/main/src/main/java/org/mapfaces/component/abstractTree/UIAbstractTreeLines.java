package org.mapfaces.component.abstractTree;

import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import java.io.Serializable;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import org.mapfaces.models.tree.TreeNodeModel;

/**
 *
 * @author kevindelfour
 */
public abstract class UIAbstractTreeLines extends UIComponentBase implements AjaxInterface, Serializable {

    private TreeNodeModel nodeinstance;    // Store id of the treelines 
    private int store;
    private int row;
    private Boolean haveTreelinesChildren = false;
    private Boolean toRender = false;
    
    private String varId;

    // =========== METHODS ======================================== //
    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }
    
    public TreeNodeModel getNodeInstance() {
        return nodeinstance;
    }

    public void setNodeInstance(TreeNodeModel nodeinstance) {
        this.nodeinstance = nodeinstance;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Boolean getHaveTreelinesChildren() {
        return haveTreelinesChildren;
    }

    public void setHaveTreelinesChildren(Boolean haveTreelinesChildren) {
        this.haveTreelinesChildren = haveTreelinesChildren;
    }

    public Boolean getToRender() {
        return toRender;
    }

    public void setToRender(Boolean toRender) {
        if (toRender) {
            setRendered(true);
        }
        this.toRender = toRender;
    }

    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = nodeinstance;
        values[2] = store;
        values[3] = row;
        values[4] = haveTreelinesChildren;
        values[5] = varId;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        nodeinstance = (TreeNodeModel) values[1];
        store = (Integer) values[2];
        row = (Integer) values[3];
        haveTreelinesChildren = (Boolean) values[4];
        varId = (String) values[5];
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
