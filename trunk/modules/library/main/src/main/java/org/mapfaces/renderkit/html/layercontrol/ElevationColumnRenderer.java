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
import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.renderkit.html.treelayout.SelectOneMenuColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 */
public class ElevationColumnRenderer extends SelectOneMenuColumnRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        ((UIElevationColumn) component).setSeparator(",");
        super.beforeEncodeBegin(context, component);
        ((UIElevationColumn) component).setItemsLabels(getElevations(context, (UIElevationColumn) component));
        ((UIElevationColumn) component).setItemsValues(getElevations(context, (UIElevationColumn) component));

    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getElevations(context, (UIElevationColumn) component) != null) {
            component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupport(context, (UIComponent) component.getChildren().get(0), "onchange", getVarId(context, (UIColumnBase) component), null));
        }
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    private String getCurrentElevation(FacesContext context, UIElevationColumn comp) {
        if (((UITreeLines) (comp.getParent())).getNodeInstance().isLeaf()) {
            return ((Layer) (((UITreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getElevation().getUserValue();
        }
        return "";
    }

    public String getElevations(FacesContext context, UIElevationColumn comp) {
        if (((UITreeLines) (comp.getParent())).getNodeInstance().isLeaf() && ((Layer) (((UITreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getDimensionList() != null) {
            if (((Layer) (((UITreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getElevation() != null) {
                return ((Layer) (((UITreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getElevation().getValue();
            }
        }
        return null;
    }
}
