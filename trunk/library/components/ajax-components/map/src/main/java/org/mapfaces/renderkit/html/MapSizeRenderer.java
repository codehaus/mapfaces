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
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.2
 */
public class MapSizeRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        final UIMapSize comp = (UIMapSize) component;
        final Context model  = (Context) comp.getModel();

        getWriter().startElement(HTML.DIV_ELEM, comp);
        getWriter().writeAttribute(HTML.id_ATTRIBUTE, comp.getClientId(context), HTML.id_ATTRIBUTE);
        getWriter().writeAttribute(HTML.style_ATTRIBUTE, getStyle(), HTML.style_ATTRIBUTE);

        if (getStyleClass() == null) {
            getWriter().writeAttribute(HTML.class_ATTRIBUTE, "mfMapSize", "styleClass");
        } else {
            getWriter().writeAttribute(HTML.class_ATTRIBUTE, getStyleClass(), "styleClass");
        /* Add <h:outputText> */
        }
        if (comp.getTitle() != null) {
            if(comp.getChildCount() == 0){
                final UIOutput outputText = new UIOutput();
                outputText.setId(comp.getId() + "_Title");
                outputText.setValue(comp.getTitle());
                comp.getChildren().add(outputText);
            }else if(!(comp.getChildren().get(0) instanceof UIOutput)) {
                ((UIOutput) (comp.getChildren().get(0))).setValue(comp.getTitle());
            }
        }

        /* Add <h:selectOneMenu> */
        if (comp.getItemsLabels() != null && comp.getItemsValues() != null) {
            if(comp.getChildCount() == 1){
                comp.getChildren().add(createSelectOneMenu(context, comp, true));
            }
        }// else {
            //TODO if no items specified
        //}
        getWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeChildren(context, component);
        getWriter().endElement(HTML.DIV_ELEM);
        getWriter().flush();
    }

    private UISelectOne createSelectOneMenu(final FacesContext context, final UIMapSize comp,
            final boolean ajaxSupport) {
        final UISelectOne selectOneMenu= new UISelectOne();
        selectOneMenu.setId(comp.getId()+"_Select");
        final String[] labelsArray = comp.getItemsLabels().split("/");
        final String[] valuesArray = comp.getItemsValues().split("/");

        if (labelsArray.length > 0 && valuesArray.length > 0) {
            //TODO if length are not equals, add missing values or labels
            //if (labelsArray.length != valuesArray.length) {
            //}
            for (int i = 0; i < labelsArray.length; i++) {
                final UISelectItem selectItem = new UISelectItem();
                selectItem.setItemLabel(labelsArray[i]);
                selectItem.setItemValue(valuesArray[i]);
                selectOneMenu.getChildren().add(selectItem);
            }
            if (ajaxSupport) {
                selectOneMenu.getFacets().put("a4jsupport", createAjaxSupport(context, comp));
            }
        }


        return selectOneMenu;
    }

    private HtmlAjaxSupport createAjaxSupport(final FacesContext context, final UIMapSize comp) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = FacesUtils.createBasicAjaxSupport(context, comp, "onchange", null);

        /*2 ways to resize the map:
         *  -use OpenLayers javascript, but the resize change the resolution of the map but in term of speed it's the better solution
         *  -use the AjaxSupport to reRender the MaPane, but we loose the OpenLayers events of ButtonBar or CursorTrack
         *  -use the AjaxSupport to refresh only layers , but we don't change the OpenLayers.Map size
         */

        final String mappaneJsObject =  FacesMapUtils.getParentUIModelBase(context, comp).getClientId(context).replace(":", "");

        ajaxComp.setOnsubmit(new StringBuilder()
                .append("var size = this.value.split(',');")
                .append(mappaneJsObject).append(".div.style.width=size[0]+'px';")
                .append(mappaneJsObject).append(".div.style.height=size[1]+'px';")
                .append(mappaneJsObject).append(".updateSize();")
                .append("return false;")
                .toString());
        return ajaxComp;
    }

}
