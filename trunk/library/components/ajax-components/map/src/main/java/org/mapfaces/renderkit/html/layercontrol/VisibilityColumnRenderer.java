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
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;

import javax.faces.context.ResponseWriter;
import org.ajax4jsf.ajax.html.HtmlActionParameter;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.layercontrol.UIVisibilityColumn;
import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.WmsGetMapEntry;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.CheckColumnRenderer;
import org.mapfaces.util.FacesMapUtils;

/**
 * @author Olivier Terral (Geomatys).
 */
public class VisibilityColumnRenderer extends CheckColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component)
            throws IOException {
        final UIVisibilityColumn comp = (UIVisibilityColumn) component;
        final TreeNodeModel currentNode = ((UITreeLines) (component.getParent())).getNodeInstance();
        final HashMap<String, String> paramsMap = new HashMap();

        if (currentNode.isLeaf()) {
            super.encodeBegin(context, component);
            final HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) component.getChildren().get(0);
            final TreeItem currentTreeItem = (TreeItem) currentNode.getUserObject();
            final TreeItem parentTreeItem = (TreeItem) ((TreeNodeModel) currentNode.getParent()).getUserObject();

            if (currentTreeItem.getUserObject() instanceof WmsGetMapEntry) {

                final String compId = parentTreeItem.getCompId();
                final WmsGetMapEntry currentEntry = (WmsGetMapEntry) currentTreeItem.getUserObject();
                currentEntry.setCompId(compId);
                currentTreeItem.setUserObject(currentEntry);
                paramsMap.put("WmsGetMapEntry_SLD_identifier", currentEntry.getIdentifier());

            } else if (currentTreeItem.getUserObject() instanceof Layer) {
                final Layer layer = (Layer) currentTreeItem.getUserObject();

                if (layer.isDisable()) {
                    checkbox.setRendered(false);
                } else {
                    //checkbox.setDisabled(!layer.isDisplayable());
                    checkbox.setValue(!layer.isHidden());
                    checkbox.setSelected(!layer.isHidden());

                    if (comp.isBypassUpdates()) {
                        final UIForm formContainer = FacesMapUtils.findForm(component);
                        String checkboxId = formContainer.getId() + ":check_" + component.getId();
                        HtmlAjaxSupport a4jSupport = FacesMapUtils.createCompleteAjaxSupport(context, checkbox.getId(), "onchange", null, null, "window." + FacesMapUtils.getJsVariableFromClientId(layer.getCompId()) + ".setVisibility(document.getElementById('" + checkboxId + "').checked);", true, false);
                        HtmlActionParameter param = new HtmlActionParameter();
                        //added a new param that contains the visibility checkbox value
                        param.setNoEscape(true);
                        param.setName(checkboxId);
                        param.setValue("eval('function getCheckboxValue(){if(document.getElementById(\\'" + checkboxId + "\\').checked) {return \\'on\\'}else{return \\'\\'}};getCheckboxValue();')");
                        a4jSupport.getChildren().add(param);
                        checkbox.getChildren().add(a4jSupport);
                    } else {
                        checkbox.setOnclick(FacesMapUtils.getJsVariableFromClientId(layer.getCompId()) + ".setVisibility(this.checked);");
                    }
                }

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
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(final FacesContext context, final UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
}
