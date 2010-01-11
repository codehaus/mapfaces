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

package org.mapfaces.taglib.treetable;

import com.sun.faces.taglib.html_basic.ColumnTag;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mapfaces.component.treetable.UITreeColumn;

/**
 *
 * @author kevindelfour
 */
public class TreeColumnTag extends ColumnTag {

    private static final String COMP_TYPE = "org.mapfaces.component.tree.TreeColumn";
    private static final String RENDER_TYPE = null;
    private ValueExpression viewControls;
    private ValueExpression width;

    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        if (!(component instanceof UITreeColumn)) {
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: org.mapfaces.components.treetable.UITreeData.  Perhaps you're missing a tag?");
        }

        final UITreeColumn column = (UITreeColumn) component;

        if (getViewControls() != null) {
            column.setValueExpression("viewControls", getViewControls());
        }
        if (getWidth() != null) {
            column.setValueExpression("width", getWidth());
        }

    }

    @Override
    public void release() {
        super.release();
        setViewControls(null);
        setWidth(null);
    }

    /**
     * @return the viewControls
     */
    public ValueExpression getViewControls() {
        return viewControls;
    }

    /**
     * @param viewControls the viewControls to set
     */
    public void setViewControls(ValueExpression viewControls) {
        this.viewControls = viewControls;
    }

    /**
     * @return the width
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }
}
