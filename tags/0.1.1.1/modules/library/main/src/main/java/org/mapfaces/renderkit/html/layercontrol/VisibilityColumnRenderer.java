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

import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.WmsGetMapEntry;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.CheckColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 */
public class VisibilityColumnRenderer extends CheckColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        TreeNodeModel currentNode = ((UITreeLines) (component.getParent())).getNodeInstance();
        TreeItem currentTreeItem = (TreeItem) currentNode.getUserObject();

        //if currentNode is a leaf or the treeitem userobject is an instance of Layer
//        if (currentNode.isLeaf() || currentTreeItem.getUserObject() instanceof Layer) {
            if (currentNode.isLeaf()) {
            super.encodeBegin(context, component);

            //getting the treeNode parent of the current nodeInstance
            TreeNodeModel parentNodeInstance = (TreeNodeModel) currentNode.getParent();
            TreeItem parentTreeItem = (TreeItem) parentNodeInstance.getUserObject();

            if (currentTreeItem.getUserObject() instanceof WmsGetMapEntry) {
                String compId = parentTreeItem.getCompId();

                //setting the compId of the current WmsGetMapEntry of the current treeItem.
                WmsGetMapEntry currentEntry = (WmsGetMapEntry) currentTreeItem.getUserObject();
                currentEntry.setCompId(compId);
                currentTreeItem.setUserObject(currentEntry);

                
                final HashMap<String, String> paramsMap = new HashMap();
                paramsMap.put("WmsGetMapEntry_SLD_identifier", currentEntry.getIdentifier());
                
                //Adding to this component child (HtmlSelectBooleanCheckbox) an a4j support component
                component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupportWithParameters(context,
                        (UIComponent) component.getChildren().get(0),
                        "onclick",
                        getVarId(context, (UIColumnBase) component),
                        null,
                        paramsMap,"",""));
            } else {
                //Adding to this component child (HtmlSelectBooleanCheckbox) an a4j support component
                component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupport(context,
                        (UIComponent) component.getChildren().get(0),
                        "onclick",
                        getVarId(context, (UIColumnBase) component),
                        null));
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeEnd(context, component);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {

        /*
         * Prepare informations for making any Ajax request (TO BE FACTORIZE)
         *///        ResponseWriter writer = context.getResponseWriter();
//        String varId = getVarId(context, (UIColumnBase) component);
//        writer.startElement("script", component);
//        writer.write("document.getElementById('" + component.getChildren().get(0).getClientId(context) + "').onchange =  function(this){" + addBeforeRequestScript(varId));
//        writer.write("};");
//        writer.endElement("script");
    }

    private String addBeforeRequestScript(String varId) {
        return "";//this.checked?document.getElementById('" + varId + "').style.display='block':document.getElementById('" + varId + "').style.display='none';";
    }
}
