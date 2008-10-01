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
package org.mapfaces.taglib.abstractTree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * <p>UITreeComponentELTag is the base class for all JSP tags that correspond to a Tree Component instance in the view.</p>
 * <p> Attributes are :<ul>
 * <li>debug</li>
 * <li>style</li>
 * <li>styleClass</li>
 * </ul>
 * @author kdelfour
 */
public abstract class UITreeComponentELTag extends UIComponentELTag {

    /* Fields */
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;

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
        component.setValueExpression("debug", getDebug());
        component.setValueExpression("style", getStyle());
        component.setValueExpression("styleClass", getStyleClass());
    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
    @Override
    public void release() {
        super.release();
        setDebug(null);
        setStyle(null);
        setStyleClass(null);
    }

    /* Accessors */
    /**
     * Accessor for debug.
     * @return true if debug is activated, false if debug is not activated
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * Mutator for debug.
     * @param debug New value for debug.
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    /**
     * Accessor for style.
     * @return the style value
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     *  Mutator for style.
     * @param style New value for style
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    /**
     * Accessor for styleClass
     * @return the styleClass value
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     *  Mutator for styleClass.
     * @param styleClass New value for styleClass
     */
    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }
}
