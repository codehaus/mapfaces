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
import javax.faces.context.FacesContext;

import org.mapfaces.component.UIScaleBar;
import org.mapfaces.taglib.ScaleBarTag;
import org.mapfaces.component.UIMapPane;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ScaleBarRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIScaleBar comp = (UIScaleBar) component;
        final String clientId = comp.getClientId(context);

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");

        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+ScaleBarTag.COMP_TYPE.substring(ScaleBarTag.COMP_TYPE.lastIndexOf(".")+1,ScaleBarTag.COMP_TYPE.length()),"styleclass");

        if (getStyle() != null)
            writer.writeAttribute("style",getStyle(),"style");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement
        String jsObject = null ;
        comp_loop :
        for (UIComponent comps : comp.getParent().getChildren()){
            if(comps instanceof UIMapPane){
                jsObject = comps.getClientId(context);
                break comp_loop;
            }
        }
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        writer.write(new StringBuilder("").
        append("if (!window.controlToAdd" + jsObject + ") { \n").
        append("    window.controlToAdd" + jsObject + " = []; \n").
        append("} \n").
        append("window.controlToAdd" + jsObject + ".push(function() {\n").
        append("    if (window.OpenLayers && window.OpenLayers.Control && window.OpenLayers.Control.ScaleBar) { \n").
        append("        var scb = new OpenLayers.Control.ScaleBar({div: OpenLayers.Util.getElement('" + clientId + "')}); \n").
        append("        "+jsObject + ".addControl(scb); \n").
        append("    } \n").
        append("}) \n").
        append("window.controlToAdd" + jsObject + "[window.controlToAdd" + jsObject + ".length-1](); \n").toString());
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
