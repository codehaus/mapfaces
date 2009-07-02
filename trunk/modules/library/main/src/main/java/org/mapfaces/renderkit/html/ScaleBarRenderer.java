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

import org.mapfaces.component.UIScaleBar;
import org.mapfaces.taglib.ScaleBarTag;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ScaleBarRenderer extends WidgetBaseRenderer {
    
    private static final Logger LOGGER = Logger.getLogger(ScaleBarRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        
        //Find UIMapPane refers to this widget 
        String mapJsVariable = null ;
        UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                mapJsVariable = uIMapPane.getClientId(context);
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            return;
        }
        
        super.encodeBegin(context, component);
        final UIScaleBar comp = (UIScaleBar) component;
        final String clientId = comp.getClientId(context);

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");

        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+ScaleBarTag.COMP_TYPE.substring(ScaleBarTag.COMP_TYPE.lastIndexOf(".")+1,ScaleBarTag.COMP_TYPE.length()),"styleclass");
        else
            writer.writeAttribute("class",getStyleClass(),"styleclass");
        
        if (getStyle() != null)
            writer.writeAttribute("style",getStyle(),"style");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

       
        if (mapJsVariable.contains(":")) {
            mapJsVariable = mapJsVariable.replace(":", "");
        }
        writer.write(new StringBuilder("").
        //Add reRender of ScaleBar on moveend event
        append(mapJsVariable+".moveend.push('" + clientId + "');").
        append("if (!window.controlToAdd" + mapJsVariable + ") { \n").
        append("    window.controlToAdd" + mapJsVariable + " = []; \n").
        append("} \n").
        append("window.controlToAdd" + mapJsVariable + ".push(function() {\n").
        append("    if (window.OpenLayers && window.OpenLayers.Control && window.OpenLayers.Control.ScaleBar) { \n").
        append("        var scb = new OpenLayers.Control.ScaleBar({div: OpenLayers.Util.getElement('" + clientId + "')}); \n").
        append("        "+mapJsVariable + ".addControl(scb); \n").
        append("    } \n").
        append("}) \n").
        append("window.controlToAdd" + mapJsVariable + "[window.controlToAdd" + mapJsVariable + ".length-1](); \n").toString());
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
