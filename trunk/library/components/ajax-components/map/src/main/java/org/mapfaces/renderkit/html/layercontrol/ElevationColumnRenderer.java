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
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.SelectOneMenuColumnRenderer;
import org.mapfaces.util.FacesMapUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class ElevationColumnRenderer extends SelectOneMenuColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        ((UIElevationColumn) component).setSeparator(",");
        super.beforeEncodeBegin(context, component);
        ((UIElevationColumn) component).setItemsLabels(getElevations(context,
                (UIElevationColumn) component));
        ((UIElevationColumn) component).setItemsValues(getElevations(context,
                (UIElevationColumn) component));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {

        final TreeNodeModel currentNode =
                ((UITreeLines) (component.getParent())).getNodeInstance();
        if (currentNode.isLeaf() &&
                getElevations(context, (UIElevationColumn) component) != null) {
            super.encodeBegin(context, component);
            final HtmlSelectOneMenu child =
                    (HtmlSelectOneMenu) component.getChildren().get(0);
            final TreeItem currentTreeItem = 
                    (TreeItem) currentNode.getUserObject();

            if (currentTreeItem.getUserObject() instanceof Layer) {
                final Layer layer = (Layer) currentTreeItem.getUserObject();
                if (layer.isDisable())
                    child.setRendered(false);
                else
                    child.setOnchange(FacesMapUtils.getJsVariableFromClientId(layer.getCompId()) + ".setElevation(this.value);");

            }

        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    private String getCurrentElevation(FacesContext context, UIElevationColumn comp) {
        if (((UITreeLines) (comp.getParent())).getNodeInstance().isLeaf()) {
            return ((TreeItem) (((UITreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getElevation().getUserValue();
        }
        return "";
    }

    public String getElevations(FacesContext context, UIElevationColumn comp) {
        UITreeLines treeLine = null;
        TreeNodeModel tnm = null;
        TreeItem ti = null;
        Layer layer = null;
        if (comp != null && comp.getParent() instanceof UITreeLines) {
            treeLine = (UITreeLines) comp.getParent();
            tnm = treeLine.getNodeInstance();
            ti = (TreeItem) tnm.getUserObject();
            if (ti.getUserObject() instanceof Layer) {
                layer = (Layer) ti.getUserObject();
            }
        }

        if (treeLine != null && tnm != null && tnm.isLeaf() && layer != null && layer.getDimensionList() != null) {
            if (layer.getElevation() != null) {
                return layer.getElevation().getValue();
            }
        }
        return null;
    }
}
