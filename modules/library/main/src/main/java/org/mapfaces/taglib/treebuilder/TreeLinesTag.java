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
package org.mapfaces.taglib.treebuilder;

import javax.faces.component.UIComponent;
import org.mapfaces.taglib.abstractTree.UITreeLinesELTag;

/**
 * <p>TreeLinesTag is the base class for all JSP tags that correspond to a Tree Lines Component instance in the view.</p>
 * @author kevindelfour
 */
public class TreeLinesTag extends UITreeLinesELTag {

    /* Fields */
    private static final String COLUMN_COMP_TYPE = "org.mapfaces.treebuilder.treepanel.TreeLines";
    private static final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treebuilder.treepanel.HTMLTreeLines";

    /* Methods*/
    /**
     * @see getComponentType in class UITreeLinesELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return COLUMN_COMP_TYPE;
    }

    /**
     * @see getComponentType in class UITreeLinesELTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }

    /**
     * @override setProperties in class UITreeLinesELTag 
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * @override release in class UITreeLinesELTag 
     */
    @Override
    public void release() {
        super.release();
    }
}

