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

import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 * UITreeBase is a UICommand that represents a user interface component which,
 * when activated by the user, triggers an application specific "command" or "action".
 * Such a component is typically rendered as a push button, a menu item, or a hyperlink.
 *
 * @author Kevin Delfour (Geomatys)
 */
public abstract class UITreeBase extends UICommand implements AjaxRendererInterface,StateHolder {

    /* Fields */
    private TreeTableModel  tree;
    private String          style;
    private String          styleClass;
    private boolean         debug;
    private boolean         mootools = true;
    private boolean         minifyJS = true;

    /* Accessors */
    public TreeTableModel getTree() {
        return tree;
    }

    public void setTree(final TreeTableModel tree) {
        this.tree = tree;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(final String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(final String styleClass) {
        this.styleClass = styleClass;
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
        values[1] = isDebug();
        values[2] = getTree();
        values[3] = getStyle();
        values[4] = getStyleClass();
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
        setDebug((Boolean) values[1]);
        setTree((TreeTableModel) values[2]);
        setStyle((String) values[3]);
        setStyleClass((String) values[4]);
    }

    /**
     * <p>Delegate to the renderer</p>
     * @param context The FacesContext for the current request
     * @param component
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        final AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
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

    public boolean isMootools() {
        return mootools;
    }

    public void setMootools(final boolean mootools) {
        this.mootools = mootools;
    }

    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(final boolean minifyJS) {
        this.minifyJS = minifyJS;
    }
}
