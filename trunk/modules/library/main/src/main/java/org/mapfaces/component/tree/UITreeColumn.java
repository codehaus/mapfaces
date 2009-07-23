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

import org.mapfaces.component.abstractTree.UITreeColumnBase;

/**
 * @author Kevin Delfour (Geomatys)
 */
public class UITreeColumn extends UITreeColumnBase {

    private static final String TREECOLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeColumn";
    private static final String TREECOLUMN_COMP_FAMILY = "org.mapfaces.treetable.treepanel.TreeColumn";

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return TREECOLUMN_COMP_FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return TREECOLUMN_RENDERER_TYPE;
    }
}
