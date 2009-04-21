/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.taglib.datatable;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class ScrollerTag extends UIComponentELTag {

    public static final String COMP_TYPE = "org.mapfaces.Datatable.Scroller";
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.datatable.HTMLScroller";
    /* Fields */
    private ValueExpression actionListener = null;
    private ValueExpression navFacetOrientation = null;
    private ValueExpression attachedTo = null;

    /* Accessors */
    /**
     * @return the actionListener
     */
    public ValueExpression getActionListener() {
        return actionListener;
    }

    /**
     * @param actionListener the actionListener to set
     */
    public void setActionListener(ValueExpression actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * @return the navFacetOrientation
     */
    public ValueExpression getNavFacetOrientation() {
        return navFacetOrientation;
    }

    /**
     * @param navFacetOrientation the navFacetOrientation to set
     */
    public void setNavFacetOrientation(ValueExpression navFacetOrientation) {
        this.navFacetOrientation = navFacetOrientation;
    }

    /**
     * @return the attachedTo
     */
    public ValueExpression getAttachedTo() {
        return attachedTo;
    }

    /**
     * @param attachedTo the attachedTo to set
     */
    public void setAttachedTo(ValueExpression attachedTo) {
        this.attachedTo = attachedTo;
    }

    /* Methods*/
    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setProperties(final UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("actionListener",actionListener);
        component.setValueExpression("navFacetOrientation",navFacetOrientation);
        component.setValueExpression("attachedTo",attachedTo);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        super.release();
        setActionListener(null);
        setNavFacetOrientation(null);
        setAttachedTo(null);
    }
}
