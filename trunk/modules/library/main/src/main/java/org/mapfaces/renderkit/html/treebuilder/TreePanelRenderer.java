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
package org.mapfaces.renderkit.html.treebuilder;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreePanelRenderer;
import org.mapfaces.util.treebuilder.TreeBuilderUtils;

/**
 *
 * @author kdelfour
 */
public class TreePanelRenderer extends AbstractTreePanelRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list, boolean LoadingOption) {
            try {
            TreeBuilderUtils tools = new TreeBuilderUtils();
            tools.createTreeLines(component, node, list, LoadingOption);
        } catch (IOException ex) {
            Logger.getLogger(TreePanelRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
