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

import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * <p>UITreeTableELTag is the base class for all JSP tags that correspond to a Tree Component instance in the view.</p>
 * <p> Attributes are :<ul>
 * <li>value</li>
 * <li>var</li>
 * <li>collapsible</li>
 * <li>width</li>
 * <li>height</li>
 * </ul>
 * @author kdelfour
 */
public abstract class UITreeTableELTag extends UITreeComponentELTag {

    /* Fields */
    private ValueExpression value = null;
    private ValueExpression var = null;
    private ValueExpression width = null;
    private ValueExpression height = null;
    private ValueExpression mootools = null;
    private ValueExpression minifyJS = null;
    

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

    /**
     * Accessor for value
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * Mutator for value
     * @param value New value for value
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    /**
     * Accessor for Var
     * @return the treetable's var name
     */
    public ValueExpression getVar() {
        return var;
    }

    /**
     * Mutator for var
     * @param var the treetable's var name
     */
    public void setVar(ValueExpression var) {
        if (var != null) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Map requestMap = ec.getRequestMap();
            requestMap.put("org.treetable.varName", var);
        }
        this.var = var;
    }

    /**
     * Accessor for width
     * @return width value
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * Mutator for width
     * @param width New value for width
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * Accessor for height
     * @return height value
     */
    public ValueExpression getHeight() {
        return height;
    }

    /**
     * Mutator for height
     * @param height New value for height
     */
    public void setHeight(ValueExpression height) {
        this.height = height;
    }
    /**
     * Accessor for mootools .
     * @return mootools value
     */
    public ValueExpression getMootools() {
        return mootools;
    }
    
    /**
     * Mutator for mootools
     * @param mootools New value for mootools
     */
    public void setMootools(ValueExpression mootools) {
        this.mootools = mootools;
    }
    
    /**
     * Accessor for minifyJS
     * @return minifyJS value
     */
    public ValueExpression getMinifyJS() {
        return minifyJS;
    }
    
    /**
     * Mutator for minifyJS
     * @param minifyJS New value for minifyJS
     */
    public void setMinifyJS(ValueExpression minifyJS) {
        this.minifyJS = minifyJS;
    }
    
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
        component.setValueExpression("value", value);
        component.setValueExpression("var", var);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
        component.setValueExpression("mootools", mootools);
        component.setValueExpression("minifyJS", minifyJS);
        
    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
    @Override
    public void release() {
        super.release();
        setValue(null);
        setVar(null);
        setWidth(null);
        setHeight(null);
        setMootools(null);
        setMinifyJS(null);
    }
    
    
}

