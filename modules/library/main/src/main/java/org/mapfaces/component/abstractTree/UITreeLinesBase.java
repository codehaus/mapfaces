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
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.interfaces.A4JInterface;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 *
 * @author kevindelfour
 */
public abstract class UITreeLinesBase extends UITreeBase implements AjaxInterface, Cloneable {

    /* Fields */
    private TreeNodeModel nodeinstance;    // Store id of the treelines 
    private int nodeId;
    private int row;
    private int depth;
    private Boolean hasChildren = false;
    private Boolean toRender = false;
    private String varId;

    /* Accessors */
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

    /**
     * <p>Perform any processing required to restore the state from the entries in the state Object.</p>
     * <p>If the class that implements this interface has references to instances that also implement StateHolder 
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>restoreState(javax.faces.context.FacesContext, java.lang.Object) method on all those instances as well.</p>
     * @param context The FacesContext for the current request 
     * @param state the state Object
     */
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

     /**
     * <p>Delegate to the renderer</p>
     * @param context The FacesContext for the current request 
     * @param component 
     */
    @Override
    public void handleAjaxRequest(FacesContext context) {
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    /**
     * UIAbstractTreeLines class implements the Cloneable interface to indicate to the Object.clone() method that it is legal 
     * for that method to make a field-for-field copy of instances of that class. 
     * @return a clone of this component
     */
    public UITreeLinesBase getInstance() {
        try {
            return (UITreeLinesBase) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UITreeLinesBase.class.getName()).log(Level.SEVERE, null, ex);
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
