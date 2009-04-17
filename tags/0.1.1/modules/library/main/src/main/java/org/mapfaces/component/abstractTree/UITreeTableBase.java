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

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.StateHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.request.RequestMapUtils;

/**
 *
 * @author Kevin Delfour (Geomatys)
 */
public abstract class UITreeTableBase extends UITreeBase implements StateHolder, Cloneable {

    /* Fields */
    private int     nodeCount = 0;
    private int     rowId = 0;
    private int     height;
    private int     width;
    private boolean RenderDefaultTree = true;

    /* Accessors */
    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(final TreeTableModel tree) {
        final TreeModelsUtils TreeModelsTools = new TreeModelsUtils();
        nodeCount = TreeModelsTools.getTreeNodeCount(tree);
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(final int aRowId) {
        rowId = aRowId;
        final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        final Map requestMap = ec.getRequestMap();
        requestMap.put("org.treetable.rowId", aRowId);
    }

    public String getVarName() {
        return (String) RequestMapUtils.getbyKey("org.treetable.varName");
    }

    public void setVarName(final String aVarName) {
        RequestMapUtils.put("org.treetable.varName", aVarName);
    }

    public boolean isRenderDefaultTree() {
        return RenderDefaultTree;
    }

    public void setRenderDefaultTree(final boolean aRenderDefaultTree) {
        RenderDefaultTree = aRenderDefaultTree;
    }

    /* Methods */
    /**
     * <p>Gets the state of the instance as a Serializable Object.</p>
     * <p>If the class that implements this interface has references to instances that implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>saveState(javax.faces.context.FacesContext) method on all those instances as well.</p>
     * <p>This method must not save the state of children and facets. That is done via the StateManager</p>
     * @param context The FacesContext for the current request
     * @return a Serializable Object
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = nodeCount;
        values[2] = rowId;
        values[3] = RenderDefaultTree;
        values[4] = width;
        values[5] = height;
        return values;
    }

    /**
     * <p>Perform any processing required to restore the state from the entries in the state Object.</p>
     * <p>If the class that implements this interface has references to instances that also implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>restoreState(javax.faces.context.FacesContext, java.lang.Object) method on all those instances as well.</p>
     * @param context The FacesContext for the current request
     * @param state the state Object
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        nodeCount = (Integer) values[1];
        rowId = (Integer) values[2];
        RenderDefaultTree = (Boolean) values[3];
        width = (Integer) values[4];
        height = (Integer) values[5];
    }

    /**
     * UIAbstractTreeTable class implements the Cloneable interface to indicate to the Object.clone() method that it is legal
     * for that method to make a field-for-field copy of instances of that class.
     * @return a clone of this component
     */
    public UITreeTableBase getInstance() {
        try {
            return (UITreeTableBase) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UITreeNodeInfoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /* Abstracts methods*/
    /**
     * <p>Return the identifier of the component family to which this component belongs.</p>
     * <p>This identifier, in conjunction with the value of the rendererType property, may be used to select the
     * appropriate Renderer for this component instance.</p>
     * @return the identifier of the component family as a String
     */
    @Override
    public abstract String getFamily();

    /**
     * @return the Renderer type for this UIComponent  (if any)
     */
    @Override
    public abstract String getRendererType();
}
