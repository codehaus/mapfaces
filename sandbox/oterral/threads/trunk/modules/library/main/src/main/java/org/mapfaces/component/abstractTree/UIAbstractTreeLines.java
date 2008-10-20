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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.ajax4jsf.framework.ajax.AjaxListener;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 *
 * @author kevindelfour
 */
public abstract class UIAbstractTreeLines extends UITreeBase implements AjaxInterface, Cloneable {

    private TreeNodeModel nodeinstance;    // Store id of the treelines 
    private int nodeId;
    private int row;
    private int depth;
    private Boolean hasChildren = false;
    private Boolean toRender = false;
    private String varId;
    // =========== METHODS ======================================== //
    public TreeNodeModel getNodeInstance() {
        return nodeinstance;
    }

    public void setNodeInstance(TreeNodeModel nodeinstance) {
        this.nodeinstance = nodeinstance;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Boolean hasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean haveTreelinesChildren) {
        this.hasChildren = haveTreelinesChildren;
    }

    public Boolean isToRender() {
        return toRender;
    }

    public void setToRender(Boolean toRender) {
        if (toRender) {
            setRendered(true);
        }
        this.toRender = toRender;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    @Override
    public void setRendered(boolean arg0) {
        super.setRendered(arg0);
    }
    // =========== FONCTIONS ======================================== //
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[8];
        values[0] = super.saveState(context);
        values[1] = getNodeInstance();
        values[2] = getNodeId();
        values[3] = getRow();
        values[4] = getDepth();
        values[5] = hasChildren();
        values[6] = isToRender();
        values[7] = isRendered();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setNodeInstance((TreeNodeModel) values[1]);
        setNodeId((Integer) values[2]);
        setRow((Integer) values[3]);
        setDepth((Integer) values[4]);
        setHasChildren((Boolean) values[5]);
        setToRender((Boolean) values[6]);
        setRendered((Boolean) values[7]);
    }

    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    public UIAbstractTreeLines getInstance() {
        try {
            return (UIAbstractTreeLines) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UIAbstractTreeLines.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
