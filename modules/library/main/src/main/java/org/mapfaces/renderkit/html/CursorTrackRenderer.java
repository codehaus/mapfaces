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
import org.mapfaces.component.UIMapPane;
import org.mapfaces.taglib.CursorTrackTag;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class CursorTrackRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        final UICursorTrack comp = (UICursorTrack) component;
        final String clientId    = comp.getClientId(context);

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");

        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+CursorTrackTag.COMP_TYPE.substring(CursorTrackTag.COMP_TYPE.lastIndexOf(".")+1,CursorTrackTag.COMP_TYPE.length()),"styleclass");

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
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach mouse control
         */
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        if (comp.isDebug()) {
            System.out.println("show PX " +comp.isShowPX());
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

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
