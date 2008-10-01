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
import org.mapfaces.taglib.abstractTree.UITreePanelELTag;

/**
 * <p>TreePanelTag is the base class for all JSP tags that correspond to a Tree panel Component instance in the treelayout.</p>
 * @author kdelfour
 */
public class TreePanelTag extends UITreePanelELTag {

    /* Fields */
    private final String TREEPANEL_COMP_TYPE = "org.mapfaces.treelayout.treetable.TreePanel";
    private final String TREEPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.HTMLTreePanel";

    /* Methods*/
    /**
     * @see getComponentType in class UITreePanelELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return TREEPANEL_COMP_TYPE;
    }

    /**
     * @see getComponentType in class UITreePanelELTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return TREEPANEL_RENDERER_TYPE;
    }

    /**
     * @override setProperties in class UITreePanelELTag 
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * @override release in class UITreePanelELTag 
     */
    @Override
    public void release() {
        super.release();
    }
}
