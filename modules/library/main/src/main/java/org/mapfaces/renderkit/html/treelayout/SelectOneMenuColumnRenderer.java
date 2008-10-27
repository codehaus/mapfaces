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
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


import org.mapfaces.component.treelayout.UISelectOneMenuColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

public class SelectOneMenuColumnRenderer extends AbstractColumnRenderer {

    static String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        
        UISelectOneMenuColumn comp = (UISelectOneMenuColumn) component;
        ResponseWriter writer = context.getResponseWriter(); 
        if(comp.getValue() != null && comp.getItemsLabels()!=null){

            UISelectOne selectOneMenu = new UISelectOne();
            selectOneMenu.setId(comp.getId() + "_Select");
            selectOneMenu.setValue(comp.getValue());
            String itemsLabels = comp.getItemsLabels();
            String[] labelsArray = itemsLabels.split(comp.getSeparator());
            String[] valuesArray = itemsLabels.split(comp.getSeparator());

            if (labelsArray.length > 0 && valuesArray.length > 0) {
                if (labelsArray.length != valuesArray.length) {
                    //TODO if length are not equals, add missing values or labels                
                }
                for (int i = 0; i < labelsArray.length; i++) {
                    UISelectItem selectItem = new UISelectItem();
                    selectItem.setItemLabel(labelsArray[i]);
                    selectItem.setItemValue(valuesArray[i]);
                    selectOneMenu.getChildren().add(selectItem);
                }
            }
            comp.getChildren().add(selectOneMenu);
        }
        writer.startElement("center", comp);

    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
    }

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
    }

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }
    
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

   
}
