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
package org.mapfaces.component.treebuilder;

import org.mapfaces.component.abstractTree.UITreeToolbarBase;

/**
 * @author Kevin Delfour (Geomatys)
 */
public class UITreeToolbar extends UITreeToolbarBase{

    private static final String TOOLBAR_COMP_FAMILY ="org.mapfaces.treebuilder.TreeToolbar";
    private static final String TOOLBAR_RENDERER_TYPE = "org.mapfaces.renderkit.treebuilder.HTMLTreeToolbar";

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return TOOLBAR_COMP_FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return TOOLBAR_RENDERER_TYPE;
    }

}
