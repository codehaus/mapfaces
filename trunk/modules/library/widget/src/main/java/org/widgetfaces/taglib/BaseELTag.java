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
package org.widgetfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * <p>BaseELTag is the base class for all JSP tags that correspond to a widget Component instance in the view.</p>
 * <p> Attributes are :<ul>
 * <li><b>debug</b></li>
 * <li><b>style</b></li>
 * <li><b>styleClass</b></li>
 * </ul>
 * @author Kevin Delfour
 */
public abstract class BaseELTag extends UIComponentELTag {

    /* Fields */
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private ValueExpression loadMootools = null;
    private ValueExpression loadCss = null;
    private ValueExpression loadJs = null;

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
        component.setValueExpression("loadMootools", getLoadMootools());
        component.setValueExpression("loadCss", getLoadCss());
        component.setValueExpression("loadJs", getLoadJs());
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
        setLoadMootools(null);
        setLoadJs(null);
        setLoadCss(null);
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

    /**
     * @return the loadMootools
     */
    public ValueExpression getLoadMootools() {
        return loadMootools;
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(ValueExpression loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @return the loadCss
     */
    public ValueExpression getLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(ValueExpression loadCss) {
        this.loadCss = loadCss;
    }

    /**
     * @return the loadJs
     */
    public ValueExpression getLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(ValueExpression loadJs) {
        this.loadJs = loadJs;
    }
}
