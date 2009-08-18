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
import org.mapfaces.share.utils.RendererUtils.HTML;
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

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,clientId,HTML.id_ATTRIBUTE);
           
        if (getStyleClass() == null)
            writer.writeAttribute(HTML.class_ATTRIBUTE,"mf"+CursorTrackTag.COMP_TYPE.substring(CursorTrackTag.COMP_TYPE.lastIndexOf(".")+1,CursorTrackTag.COMP_TYPE.length()),"styleclass");
        else
            writer.writeAttribute(HTML.class_ATTRIBUTE,getStyleClass(),"styleclass");
        if (getStyle() != null)
            writer.writeAttribute(HTML.style_ATTRIBUTE,getStyle(),HTML.style_ATTRIBUTE);

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "text/javascript", "text/javascript");

       
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
        append("if (!window.controlToAdd" + jsObject + ") { ").
        append("    window.controlToAdd" + jsObject + " = []; ").
        append("} ").
        append("window.controlToAdd" + jsObject + ".push(function() {").
        append("    if (window.OpenLayers && window.OpenLayers.Control && window.OpenLayers.Control.MousePosition) { " ).
        append("        var mp = new OpenLayers.Control.MousePosition({'div':OpenLayers.Util.getElement('" + clientId + "')").toString());
        
        if (comp.isShowPX()) {
            writer.write(",PX: true");
        }
        if (comp.isShowXY()) {
            writer.write(",XY: true");
        }
        if (comp.isShowLatLon()) {
            writer.write(",LatLon: true");
        }
        if (comp.isShowDMS()) {
            writer.write(",DMS: true");
        }
        if (comp.isShowDM()) {
            writer.write(",DM: true");
        }
        
        writer.write(new StringBuilder("").
        append("        }); ").
        append("        window."+jsObject + ".addControl(mp); ").
        append("    } ").
        append("}); ").
        append("window.controlToAdd" + jsObject + "[window.controlToAdd" + jsObject + ".length-1](); ").toString());
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.endElement(HTML.DIV_ELEM);
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
