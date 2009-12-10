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

import javax.faces.component.UIComponent;

import org.mapfaces.taglib.abstractTree.UITreeToolbarELTag;

/**
 * @author Kevin Delfour
 */
public class TreeToolbarTag extends UITreeToolbarELTag {

    /* Fields */
    private static final String TOOLBAR_COMP_TYPE = "org.mapfaces.treetable.TreeToolbar";
    private static final String TOOLBAR_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.HTMLTreeToolbar";

    /* Methods*/
    /**
     * @override setProperties in class UIColumnELTag
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * @override release in class UIColumnELTag
     */
    @Override
    public void release() {
        super.release();
    }

    /**
     * @see getComponentType in class UIColumnELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return TOOLBAR_COMP_TYPE;
    }

    /**
     * @see getRendererType in class UIColumnELTag
     * @return renderer type
     */
    @Override
    public String getRendererType() {
        return TOOLBAR_RENDERER_TYPE;
    }
}
