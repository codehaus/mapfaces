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

import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 *
 * @author Kevin Delfour (Geomatys)
 */
public abstract class UIColumnBase extends UITreeBase implements AjaxInterface, Cloneable {

    private static final Logger LOGGER = Logger.getLogger(UIColumnBase.class.getName());

    /* Fields */
    private String headerTitle;
    private String headerIcon;
    private String width;
    private String styleHeader;

    /* Accessors */
    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(final String title) {
        this.headerTitle = title;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(final String width) {
        this.width = width;
    }

    public String getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(final String icon) {
        this.headerIcon = icon;
    }

    public String getStyleHeader() {
        return styleHeader;
    }

    public void setStyleHeader(final String styleHeader) {
        this.styleHeader = styleHeader;
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
        final Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = getHeaderTitle();
        values[2] = getWidth();
        values[3] = getHeaderIcon();
        values[4] = getStyleHeader();
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
        setHeaderTitle((String) values[1]);
        setWidth((String) values[2]);
        setHeaderIcon((String) values[3]);
        setStyleHeader((String) values[4]);
    }

    /**
     * <p>Delegate to the renderer</p>
     * @param context The FacesContext for the current request
     * @param component
     */
    @Override
    public void handleAjaxRequest(final FacesContext context) {
        final AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    /**
     * UIAbstractTreeColumn class implements the Cloneable interface to indicate to the Object.clone() method that it is legal
     * for that method to make a field-for-field copy of instances of that class.
     * @return a clone of this component
     */
    public UIColumnBase getInstance() {
        try {
            return (UIColumnBase) this.clone();
        } catch (CloneNotSupportedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
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
