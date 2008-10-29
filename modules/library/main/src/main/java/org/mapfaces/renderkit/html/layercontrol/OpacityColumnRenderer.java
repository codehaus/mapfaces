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
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.renderkit.html.treelayout.SelectOneMenuColumnRenderer;
import org.mapfaces.util.FacesUtils;


public class OpacityColumnRenderer extends SelectOneMenuColumnRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeBegin(context, component);
            if(component.getChildCount()>0){
                    component.getChildren().get(0).getChildren().add(FacesUtils.createTreeAjaxSupport(context,
                    (UIComponent) component.getChildren().get(0),
                    "onchange",
                    getVarId(context, (UIColumnBase) component),
                    null));
            }
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
//        ResponseWriter writer = context.getResponseWriter();
//        writer.startElement("script", component);
//        writer.write("document.getElementById('" + component.getChildren().get(0).getClientId(context) + "').onchange = function(this){" + addBeforeRequestScript(getVarId(context, (UIColumnBase) component)) + "};");
//        writer.endElement("script");
    }

    public String addBeforeRequestScript(String layerId) {
        return  "";
//                "document.getElementById('" + layerId + "').style.opacity=this.value;" +
//                "document.getElementById('" + layerId + "').style.filter=alpha(opacity=(this.value*100));" +
//                "return false;"  ;
    }
}
