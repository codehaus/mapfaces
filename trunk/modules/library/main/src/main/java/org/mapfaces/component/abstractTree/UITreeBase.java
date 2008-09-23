package org.mapfaces.component.abstractTree;

import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 *
 * @author kdelfour
 */
public abstract class UITreeBase extends UICommand implements AjaxRendererInterface,StateHolder {

    private TreeTableModel tree;
    private boolean debug;
    private String style;
    private String styleClass;

    public TreeTableModel getTree() {
        return tree;
    }

    public void setTree(TreeTableModel tree) {
        this.tree = tree;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = isDebug();
        values[2] = getTree();
        values[3] = getStyle();
        values[4] = getStyleClass();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setDebug((Boolean) values[1]);
        setTree((TreeTableModel) values[2]);
        setStyle((String) values[3]);
         setStyleClass((String) values[4]);
    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
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
