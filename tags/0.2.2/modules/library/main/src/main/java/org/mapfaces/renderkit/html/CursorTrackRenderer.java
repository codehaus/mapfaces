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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UICursorTrack;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.taglib.CursorTrackTag;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class CursorTrackRenderer extends WidgetBaseRenderer {

        
    private static final Logger LOGGER = Logger.getLogger(CursorTrackRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        
        //Find UIMapPane refers to this widget 
        String jsObject = null ;
        UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                jsObject = uIMapPane.getClientId(context);
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            return;
        }
        
        final UICursorTrack comp = (UICursorTrack) component;
        final String clientId    = comp.getClientId(context);

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");
           
        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+CursorTrackTag.COMP_TYPE.substring(CursorTrackTag.COMP_TYPE.lastIndexOf(".")+1,CursorTrackTag.COMP_TYPE.length()),"styleclass");
        else
            writer.writeAttribute("class",getStyleClass(),"styleclass");
        if (getStyle() != null)
            writer.writeAttribute("style",getStyle(),"style");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

       
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach mouse control
         */
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        if (comp.isDebug()) {
            System.out.println("show PX " +comp.isShowPX());
        }
        writer.write(new StringBuilder("").
        append("if (!window.controlToAdd" + jsObject + ") { \n").
        append("    window.controlToAdd" + jsObject + " = []; \n").
        append("} \n").
        append("window.controlToAdd" + jsObject + ".push(function() {\n").
        append("    if (window.OpenLayers && window.OpenLayers.Control && window.OpenLayers.Control.MousePosition) { \n" ).
        append("        var mp = new OpenLayers.Control.MousePosition({'div':OpenLayers.Util.getElement('" + clientId + "')").toString());
        
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
        
        writer.write(new StringBuilder("").
        append("        \n}); \n").
        append("        window."+jsObject + ".addControl(mp); \n").
        append("    } \n").
        append("}); \n").
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
