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

package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UITreePanelBase;

/**
 *
 * @author kdelfour
 */
public class UITreePanel extends UITreePanelBase {

    private final String TREEPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.HTMLTreePanel";
    private final String TREEPANEL_COMP_FAMILY = "javax.faces.Output";

    @Override
    public String getFamily() {
        return TREEPANEL_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREEPANEL_RENDERER_TYPE;
    }
}
