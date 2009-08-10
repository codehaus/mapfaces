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

package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.component.treelayout.UISelectOneMenuColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

/**
 * @author Kevin Delfour
 */
public class SelectOneMenuColumnRenderer extends AbstractColumnRenderer {

    private static final String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        final UISelectOneMenuColumn comp = (UISelectOneMenuColumn) component;
        final ResponseWriter writer      = context.getResponseWriter();

        if(comp.getValue() != null && comp.getItemsLabels()!=null &&
            comp.getChildCount() == 0){
            final HtmlSelectOneMenu selectOneMenu = new HtmlSelectOneMenu();
            final String itemsLabels        = comp.getItemsLabels();
            final String[] labelsArray      = itemsLabels.split(comp.getSeparator());
            final String[] valuesArray      = itemsLabels.split(comp.getSeparator());

            selectOneMenu.setId(comp.getId() + "_Select");
            selectOneMenu.setValue(comp.getValue());

            if (labelsArray.length > 0 && valuesArray.length > 0) {
                if (labelsArray.length != valuesArray.length) {
                    //TODO if length are not equals, add missing values or labels
                }
                for (int i=0; i<labelsArray.length; i++) {
                    final UISelectItem selectItem = new UISelectItem();
                    selectItem.setItemLabel(labelsArray[i]);
                    selectItem.setItemValue(valuesArray[i]);
                    selectOneMenu.getChildren().add(selectItem);
                }
            }
            comp.getChildren().add(selectOneMenu);
        } else {
            ((UISelectOne) comp.getChildren().get(0)).setValue(comp.getValue());
        }
        writer.startElement("center", comp);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
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
    public void beforeEncodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
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

    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
