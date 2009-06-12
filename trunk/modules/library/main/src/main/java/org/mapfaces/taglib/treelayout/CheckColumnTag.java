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
package org.mapfaces.taglib.treelayout;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
  *<p>CheckColumnTag is an example of ColumnTag extension to make a specified column with checkbox in a treelayout</p>
 * @author Kevin Delfour
 */
public class CheckColumnTag extends ColumnTag {

    private static final String COLUMN_COMP_TYPE = "org.mapfaces.treelayout.CheckColumn";
    private static final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLCheckColumn";

    /* Fields */
    private ValueExpression value = null;

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
     * @override setProperties in class ColumnTag
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("value", getValue());
}

    /**
     * @override release in class ColumnTag
     */
    @Override
    public void release() {
        super.release();
        setValue(null);
    }

    /**
     * @override getComponentType in class ColumnTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return COLUMN_COMP_TYPE;
    }

    /**
     * @override getRendererType in class ColumnTag
     * @return renderer type
     */
    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
}
