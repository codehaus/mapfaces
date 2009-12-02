/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 * 
 * @author Kevin Delfour
 */
public abstract class BaseExtendELTag extends BaseELTag {

    /* Fields */
    private ValueExpression onclick = null;
    private ValueExpression ondblclick = null;
    private ValueExpression onkeydown = null;
    private ValueExpression onkeypress = null;
    private ValueExpression onkeyup = null;
    private ValueExpression onmousedown = null;
    private ValueExpression onmousemove = null;
    private ValueExpression onmouseout = null;
    private ValueExpression onmouseover = null;
    private ValueExpression onmouseup = null;

    /* Abstracts methods*/
    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the component type for the component that is or will be bound to this tag.
     */
    @Override
    public abstract String getComponentType();

    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the rendererType property that selects the Renderer to be used for encoding this component, 
     * or null to ask the component to render itself directly
     */
    @Override
    public abstract String getRendererType();

    /* Methods*/
    /**
     * <p>Override properties and attributes of the specified component, 
     * if the corresponding properties of this tag handler instance were explicitly set.</p>
     * <p>This method must be called ONLY  if the specified UIComponent was in fact created during 
     * the execution of this tag handler instance, and this call will occur BEFORE the 
     * UIComponent is added to the view.</p>
     * @param component UIComponent whose properties are to be overridden
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("onclick", getOnclick());
        component.setValueExpression("ondblclick", getOndblclick());
        component.setValueExpression("onkeydown", getOnkeydown());
        component.setValueExpression("onkeypress", getOnkeypress());
        component.setValueExpression("onkeyup", getOnkeyup());
        component.setValueExpression("onmousedown", getOnmousedown());
        component.setValueExpression("onmousemove", getOnmousemove());
        component.setValueExpression("onmouseout", getOnmouseout());
        component.setValueExpression("onmouseover", getOnmouseover());
        component.setValueExpression("onmouseup", getOnmouseup());

    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
    @Override
    public void release() {
        super.release();
        setOnclick(null);
        setOndblclick(null);
        setOnkeydown(null);
        setOnkeypress(null);
        setOnkeyup(null);
        setOnmousedown(null);
        setOnmousemove(null);
        setOnmouseout(null);
        setOnmouseover(null);
        setOnmouseup(null);
    }

    /* Accessors */
    /**
     * @return the onclick
     */
    public ValueExpression getOnclick() {
        return onclick;
    }

    /**
     * @param onclick the onclick to set
     */
    public void setOnclick(ValueExpression onclick) {
        this.onclick = onclick;
    }

    /**
     * @return the ondblclick
     */
    public ValueExpression getOndblclick() {
        return ondblclick;
    }

    /**
     * @param ondblclick the ondblclick to set
     */
    public void setOndblclick(ValueExpression ondblclick) {
        this.ondblclick = ondblclick;
    }

    /**
     * @return the onkeydown
     */
    public ValueExpression getOnkeydown() {
        return onkeydown;
    }

    /**
     * @param onkeydown the onkeydown to set
     */
    public void setOnkeydown(ValueExpression onkeydown) {
        this.onkeydown = onkeydown;
    }

    /**
     * @return the onkeypress
     */
    public ValueExpression getOnkeypress() {
        return onkeypress;
    }

    /**
     * @param onkeypress the onkeypress to set
     */
    public void setOnkeypress(ValueExpression onkeypress) {
        this.onkeypress = onkeypress;
    }

    /**
     * @return the onkeyup
     */
    public ValueExpression getOnkeyup() {
        return onkeyup;
    }

    /**
     * @param onkeyup the onkeyup to set
     */
    public void setOnkeyup(ValueExpression onkeyup) {
        this.onkeyup = onkeyup;
    }

    /**
     * @return the onmousedown
     */
    public ValueExpression getOnmousedown() {
        return onmousedown;
    }

    /**
     * @param onmousedown the onmousedown to set
     */
    public void setOnmousedown(ValueExpression onmousedown) {
        this.onmousedown = onmousedown;
    }

    /**
     * @return the onmousemove
     */
    public ValueExpression getOnmousemove() {
        return onmousemove;
    }

    /**
     * @param onmousemove the onmousemove to set
     */
    public void setOnmousemove(ValueExpression onmousemove) {
        this.onmousemove = onmousemove;
    }

    /**
     * @return the onmouseout
     */
    public ValueExpression getOnmouseout() {
        return onmouseout;
    }

    /**
     * @param onmouseout the onmouseout to set
     */
    public void setOnmouseout(ValueExpression onmouseout) {
        this.onmouseout = onmouseout;
    }

    /**
     * @return the onmouseover
     */
    public ValueExpression getOnmouseover() {
        return onmouseover;
    }

    /**
     * @param onmouseover the onmouseover to set
     */
    public void setOnmouseover(ValueExpression onmouseover) {
        this.onmouseover = onmouseover;
    }

    /**
     * @return the onmouseup
     */
    public ValueExpression getOnmouseup() {
        return onmouseup;
    }

    /**
     * @param onmouseup the onmouseup to set
     */
    public void setOnmouseup(ValueExpression onmouseup) {
        this.onmouseup = onmouseup;
    }
}
