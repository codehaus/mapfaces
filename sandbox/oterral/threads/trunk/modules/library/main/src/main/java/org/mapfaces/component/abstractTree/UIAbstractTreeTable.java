/*
 *    Mapfaces - 
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.component.abstractTree;

import org.mapfaces.share.request.RequestMapUtils;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.StateHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.models.tree.TreeTableModel;

/**
 *
 * @author kdelfour
 */
public abstract class UIAbstractTreeTable extends UITreeBase implements StateHolder, Cloneable {

    private int nodeCount = 0;
    private int rowId = 0;
    private boolean RenderDefaultTree = true;
    
    // =========== ATTRIBUTES ================================================== //
    private int height;
    private int width;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
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
        TreeModelsUtils TreeModelsTools = new TreeModelsUtils();
        nodeCount = TreeModelsTools.getTreeNodeCount(tree);
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
    
    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = nodeCount;
        values[2] = rowId;
        values[3] = RenderDefaultTree;
        values[4] = width;
        values[5] = height;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        nodeCount = (Integer) values[1];
        rowId = (Integer) values[2];
        RenderDefaultTree = (Boolean) values[3];
        width = (Integer) values[4];
        height = (Integer) values[5];
    }

    public UIAbstractTreeTable getInstance() {
        try {
            return (UIAbstractTreeTable) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UIAbstractTreeNodeInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
