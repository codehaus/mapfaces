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

import javax.faces.component.UIComponent;

import org.mapfaces.taglib.abstractTree.UITreeTableELTag;

/**
 * <p>TreeTableTag is the base class for all JSP tags that correspond to a Tree table Component instance in the treelayout.</p>
 * @author Kevin Delfour
 */
public class TreeTableTag extends UITreeTableELTag {

    /* Fields */
    private static final String TREETABLE_COMP_TYPE = "org.mapfaces.treelayout.TreeTable";
    private static final String TREETABLE_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeTable";

    /* Methods*/
    /**
     * @see getComponentType in class UITreeTableELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return TREETABLE_COMP_TYPE;
    }

    /**
     * @see getComponentType in class UITreeTableELTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return TREETABLE_RENDERER_TYPE;
    }

    /**
     * @override setProperties in class UITreeTableELTag
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
    }
}

