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
import javax.faces.context.ResponseWriter;


import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.renderkit.html.treelayout.CheckColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * 
 * @author Olivier Terral.
 */
public class VisibilityColumnRenderer extends CheckColumnRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeBegin(context, component);

            component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupport(context,
                    (UIComponent) component.getChildren().get(0),
                    "onclick",
                    getVarId(context, (UIColumnBase) component),
                    null));
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeEnd(context, component);
        }
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {

        /*
         * Prepare informations for making any Ajax request (TO BE FACTORIZE)
         */

        ResponseWriter writer = context.getResponseWriter();
        String varId = getVarId(context, (UIColumnBase) component);
        writer.endElement("center");
        writer.startElement("script", component);
        writer.write("document.getElementById('" + component.getChildren().get(0).getClientId(context) + "').addEvent('change', function(event){" + addBeforeRequestScript(varId));
        writer.write("});");
        writer.endElement("script");
    }

    private String addBeforeRequestScript(String varId) {
        return "event.target.checked?document.getElementById('" + varId + "').style.display='block':document.getElementById('" + varId + "').style.display='none';";
    }
}
