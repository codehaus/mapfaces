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

package org.mapfaces.renderkit.html.layercontrol;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.layercontrol.UITimeColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.ImgColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * 
 * @author Olivier Terral.
 */
public class TimeColumnRenderer extends ImgColumnRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        UITimeColumn comp = (UITimeColumn) component;
        comp.setTitle("Display or hide temporal information in the timeline.");
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeBegin(context, component);
            component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupport(context,
                    (UIComponent) component.getChildren().get(0),
                    "onclick",
                    getVarId(context, (UIAbstractColumn) component),
                    FacesUtils.getFormId(context, component) + ":timeline"));
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeEnd(context, component);
        }
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    public String getTimes(FacesContext context, UITimeColumn comp) {
        TreeNodeModel tnm = ((UITreeLines) (comp.getParent())).getNodeInstance();
        if (tnm.isLeaf() && ((Layer) (tnm.getUserObject())).getDimensionList() != null) {
            if (((Layer) (tnm.getUserObject())).getTime() != null) {
                return ((Layer) (tnm.getUserObject())).getTime().getValue();
            }
        }
        return null;
    }
}
