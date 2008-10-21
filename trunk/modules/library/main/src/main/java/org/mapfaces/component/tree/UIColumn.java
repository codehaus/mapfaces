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

import org.mapfaces.component.abstractTree.UIColumnBase;

/**
 *
 * @author kevindelfour
 */
public class UIColumn extends UIColumnBase {

    private final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLColumn";
    private final String COLUMN_COMP_FAMILY = "org.mapfaces.treetable.treepanel.Column";

    @Override
    public String getFamily() {
        return COLUMN_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
    }
