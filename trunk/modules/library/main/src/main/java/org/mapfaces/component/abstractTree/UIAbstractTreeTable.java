package org.mapfaces.component.abstractTree;

import org.mapfaces.share.requestmap.RequestMapUtils;
import java.io.Serializable;
import java.util.Map;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.util.treetable.TreeTableUtils;

/**
 *
 * @author kdelfour
 */
public abstract class UIAbstractTreeTable extends UIComponentBase implements Serializable {

    private TreeTableModel tree;
    private int nodeCount = 0;
    private int rowId = 0;
    private boolean RenderDefaultTree = true;

    // =========== ATTRIBUTES ================================================== //
    private boolean debug;
    private int height;
    private int width;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean width) {
        this.debug = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // =========== FONCTIONS ======================================== //
    /**
     * 
     * @return
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * 
     * @param tree
     */
    public void setNodeCount(TreeTableModel tree) {
        TreeTableUtils tabletools = new TreeTableUtils();
        nodeCount = tabletools.getTreeNodeCount(tree);
    }

    /**
     * 
     * @return
     */
    public int getRowId() {
        return rowId;
    }

    /**
     * 
     * @param aRowId
     */
    public void setRowId(int aRowId) {
        rowId = aRowId;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        requestMap.put("org.treetable.rowId", aRowId);
    }

    /**
     * 
     * @return
     */
    public String getVarName() {
        return (String) RequestMapUtils.getbyKey("org.treetable.varName");
    }

    /**
     * 
     * @param aVarName
     */
    public void setVarName(String aVarName) {
        RequestMapUtils.put("org.treetable.varName", aVarName);
    }

    /**
     * 
     * @return
     */
    public boolean isRenderDefaultTree() {
        return RenderDefaultTree;
    }

    /**
     * 
     * @param aRenderDefaultTree
     */
    public void setRenderDefaultTree(boolean aRenderDefaultTree) {
        RenderDefaultTree = aRenderDefaultTree;
    }

    /**
     * 
     * @return
     */
    public TreeTableModel getTree() {
        return tree;
    }

    /**
     * 
     * @param tree
     */
    public void setTree(TreeTableModel tree) {
        this.tree = tree;
    }

    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[8];
        values[0] = super.saveState(context);
        values[1] = tree;
        values[2] = nodeCount;
        values[3] = rowId;
        values[4] = RenderDefaultTree;
        values[5] = getWidth();
        values[6] = getHeight();
        values[7] = getDebug();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        tree = (TreeTableModel) values[1];
        nodeCount = (Integer) values[2];
        rowId = (Integer) values[3];
        RenderDefaultTree = (Boolean) values[4];
        width = (Integer) values[5];
        height = (Integer) values[6];
        debug = (Boolean) values[7];
    }
}
