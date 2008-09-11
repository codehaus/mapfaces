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
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.renderkit.html.treelayout.SelectOneMenuColumnRenderer;

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
//        System.out.println(((UIElevationColumn) component).getItemsLabels());
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        System.out.println("ya des elevations" + getElevations(context, (UIElevationColumn) component));
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getElevations(context, (UIElevationColumn) component) != null) {
            super.encodeBegin(context, component);
            component.getChildren().get(0).getChildren().add(createAjaxSupport(context, (UIComponent) component.getChildren().get(0), getVarId(context, (UIAbstractColumn) component)));
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getElevations(context, (UIElevationColumn) component) != null) {
            super.encodeEnd(context, component);
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
//    @Override
//    public UISelectOne createSelectOneMenu(FacesContext context,UISelectOneMenuColumn comp,boolean ajaxSupport,String idsToRefresh) {
//        UISelectOne selectOneMenu= new UISelectOne();
//        selectOneMenu.setId(comp.getId()+"_Select");
//        String[] labelsArray = comp.getItemsLabels().split(",");
//        String[] valuesArray = comp.getItemsValues().split(",");
//        String defaultValueSelected="1";
//        if(getVarId(context, (UIElevationColumn) comp)!=null){
//            defaultValueSelected = getCurrentElevation(context,(UIElevationColumn) comp);
//        }
//        System.out.println(defaultValueSelected);
//       // System.out.println((String)comp.getValue());
//        if(labelsArray.length>0 && valuesArray.length>0){
//            if(labelsArray.length != valuesArray.length){
//                //TODO if length are not equals, add missing values or labels  
//                throw new IllegalArgumentException("The array of labels has a different size than the array of values.");
//            }
//            for(int i = 0; i < labelsArray.length; i++){
//                UISelectItem selectItem = new UISelectItem();
//                selectItem.setItemLabel(labelsArray[i]);
//                selectItem.setItemValue(valuesArray[i]);
//                selectOneMenu.getChildren().add(selectItem);
//            System.out.println(labelsArray[i]);
//            }
//            selectOneMenu.getChildren().add(createAjaxSupport(context,(UIComponent) selectOneMenu,getVarId(context, (UIElevationColumn) comp)));                
//            
//        }                
//        
//        
//        return selectOneMenu;
//    }
    private HtmlAjaxSupport createAjaxSupport(FacesContext context, UIComponent comp, String layerId) {

        /* Add <a4j:support> component */
        HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(comp.getId() + "_Ajax");
        ajaxComp.setEvent("onchange");
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setReRender(layerId);
        /*ajaxComp.getChildren().add(createFParam("synchronized", "true"));*/
        /*ajaxComp.getChildren().add(createFParam("refresh", layerId));*/
        ajaxComp.getChildren().add(createFParam("org.mapfaces.ajax.AJAX_LAYER_ID", layerId));
        ajaxComp.getChildren().add(createFParam("org.mapfaces.ajax.AJAX_CONTAINER_ID", comp.getClientId(context)));
        return ajaxComp;
    }

    private UIParameter createFParam(String name, String value) {

        /* Add <f:param> component */
        UIParameter fParam = new UIParameter();
        fParam.setName(name);
        fParam.setValue(value);
        return fParam;
    }
}
