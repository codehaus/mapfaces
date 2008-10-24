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
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import javax.faces.context.FacesContext;
import org.mapfaces.share.interfaces.A4JInterface;
import org.mapfaces.share.interfaces.A4JRendererInterface;

/**
 *
 * @author kevindelfour
 */
public abstract class UITreeColumnBase extends UIColumnBase implements AjaxInterface,A4JInterface, Cloneable {

    /* Fields */
    private boolean alreadyRender = false;

    /* Accessors */
    public boolean isAlreadyRender() {
        return alreadyRender;
    }

    public void setAlreadyRender(boolean alreadyRender) {
        this.alreadyRender = alreadyRender;
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
        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = isAlreadyRender();
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
        alreadyRender = (Boolean) values[1];
    }

    /**
     * <p>Delegate to the renderer</p>
     * @param context The FacesContext for the current request 
     * @param component 
     */
    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }
    
    @Override
    public void A4JPostRequest(FacesContext context) {
        A4JRendererInterface renderer = (A4JRendererInterface) this.getRenderer(context);
        renderer.A4JPostRequest(context, this);
    }

    /**
     * UIAbstractTreeColumn class implements the Cloneable interface to indicate to the Object.clone() method that it is legal 
     * for that method to make a field-for-field copy of instances of that class. 
     * @return a clone of this component
     */
    @Override
    public UITreeColumnBase getInstance() {
        try {
            return (UITreeColumnBase) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UITreeColumnBase.class.getName()).log(Level.SEVERE, null, ex);
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
