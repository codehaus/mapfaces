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
import org.mapfaces.component.UICursorTrack;
import org.mapfaces.taglib.CursorTrackTag;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class CursorTrackRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        UICursorTrack comp = (UICursorTrack) component;

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");

        if (styleClass == null) {
            writer.writeAttribute("class", "mf" + CursorTrackTag.COMP_TYPE.substring(CursorTrackTag.COMP_TYPE.lastIndexOf(".") + 1, CursorTrackTag.COMP_TYPE.length()), "styleclass");
        }
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = comp.getParent().getClientId(context);
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        writer.write("var mp = new OpenLayers.Control.MousePosition({'div':OpenLayers.Util.getElement('" + clientId + "')");

        if (comp.isShowPX()) {
            writer.write(",\nPX: true");
        }
        if (comp.isShowXY()) {
            writer.write(",\nXY: true");
        }
        if (comp.isShowLatLon()) {
            writer.write(",\nLatLon: true");
        }
        if (comp.isShowDMS()) {
            writer.write(",\nDMS: true");
        }
        if (comp.isShowDM()) {
            writer.write(",\nDM: true");
        }
        writer.write("\n});\n");
        writer.write(jsObject + ".addControl(mp);\n");
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();

    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
