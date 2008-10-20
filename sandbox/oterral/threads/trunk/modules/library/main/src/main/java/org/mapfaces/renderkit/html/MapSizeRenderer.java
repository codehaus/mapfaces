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

package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.UIMapSize;
import org.mapfaces.models.Context;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class MapSizeRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        UIMapSize comp = (UIMapSize) component;
        Context model = (Context) comp.getModel();

        getWriter().startElement("div", comp);
        getWriter().writeAttribute("id", getClientId(), "id");
        getWriter().writeAttribute("style", getStyle(), "style");

        if (getStyleClass() == null) {
            getWriter().writeAttribute("class", "mfMapSize", "styleClass");
        } else {
            getWriter().writeAttribute("class", getStyleClass(), "styleClass");
        /* Add <h:outputText> */
        }
        if (comp.getTitle() != null) {
            UIOutput outputText = new UIOutput();
            outputText.setId(comp.getId() + "Title");
            outputText.setValue(comp.getTitle());
            comp.getChildren().add(outputText);
        }

        /* Add <h:selectOneMenu> */
        if (comp.getItemsLabels() != null && comp.getItemsValues() != null) {
            comp.getChildren().add(createSelectOneMenu(context, comp, true, model.getLayersId()));
        } else {
            //TODO if no items specified
        }
        getWriter().flush();
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        super.encodeChildren(context, component);
        getWriter().endElement("div");
        getWriter().flush();
    }

    private UISelectOne createSelectOneMenu(FacesContext context,UIMapSize comp,boolean ajaxSupport,String idsToRefresh) {
        UISelectOne selectOneMenu= new UISelectOne();
        selectOneMenu.setId(comp.getId()+"_Select");
        String[] labelsArray = comp.getItemsLabels().split("/");
        String[] valuesArray = comp.getItemsValues().split("/");

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
            if (ajaxSupport) {
                selectOneMenu.getChildren().add(createAjaxSupport(context, comp, idsToRefresh));
            }
        }


        return selectOneMenu;
    }

    private HtmlAjaxSupport createAjaxSupport(FacesContext context, UIMapSize comp, String idsToRefresh) {

        /* Add <a4j:support> component */
        HtmlAjaxSupport ajaxComp = FacesUtils.createBasicAjaxSupport(context, comp, "onchange", null);

        /*2 ways to resize the map:
         *  -use OpenLayers javascript, but the resize change the resolution of the map but in term of speed it's the better solution
         *  -use the AjaxSupport to reRender the MaPane, but we loose the OpenLayers events of ButtonBar or CursorTrack
         *  -use the AjaxSupport to refresh only layers , but we don't change the OpenLayers.Map size
         */

        String mappaneJsObject =  FacesUtils.getParentUIModelBase(context, comp).getClientId(context).replace(":", "");
        
        ajaxComp.setOnsubmit("" +
                "var size = this.value.split(',');" +
                mappaneJsObject+".div.style.width=size[0]+'px';" +
                mappaneJsObject+".div.style.height=size[1]+'px';" +
                mappaneJsObject+".updateSize();" +
                "return false;");
        return ajaxComp;
    }

}
