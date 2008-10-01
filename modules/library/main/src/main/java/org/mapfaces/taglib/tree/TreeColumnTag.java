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
package org.mapfaces.taglib.tree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mapfaces.taglib.abstractTree.UITreeColumnELTag;

/**
 * <p>TreeColumnTag is the base class for all JSP tags that correspond to a TreeColumn Component instance in the view.</p>
 * @author kdelfour
 */
public class TreeColumnTag extends UITreeColumnELTag {

    /* Fields */
    private ValueExpression value = null;
    private static final String TREECOLUMN_COMP_TYPE = "org.mapfaces.treetable.treepanel.TreeColumn";
    private static final String TREECOLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeColumn";

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

    /* Methods*/
    /**
     * @override setProperties in class UITreeColumnELTag 
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("value", getValue());
    }

    /**
     * @override release in class UITreeColumnELTag 
     */
    @Override
    public void release() {
        super.release();
        setValue(null);
    }

    /**
     * @see getComponentType in class UITreeColumnELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return TREECOLUMN_COMP_TYPE;
    }

    /**
     * @see getRendererType in class UITreeColumnELTag
     * @return Renderer type
     */
    @Override
    public String getRendererType() {
        return TREECOLUMN_RENDERER_TYPE;
    }
}
