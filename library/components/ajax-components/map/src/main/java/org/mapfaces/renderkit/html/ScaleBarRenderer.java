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
import org.mapfaces.share.utils.RendererUtils.HTML;

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
        final UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
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

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,clientId,HTML.id_ATTRIBUTE);

        if (getStyleClass() == null)
            writer.writeAttribute(HTML.class_ATTRIBUTE,"mf"+ScaleBarTag.COMP_TYPE.substring(ScaleBarTag.COMP_TYPE.lastIndexOf(".")+1,ScaleBarTag.COMP_TYPE.length()),"styleclass");
        else
            writer.writeAttribute(HTML.class_ATTRIBUTE,getStyleClass(),"styleclass");
        
        if (getStyle() != null)
            writer.writeAttribute(HTML.style_ATTRIBUTE,getStyle(),HTML.style_ATTRIBUTE);

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");

       
        if (mapJsVariable.contains(":")) {
            mapJsVariable = mapJsVariable.replace(":", "");
        }
        writer.write(new StringBuilder("").
        //Add reRender of ScaleBar on moveend event
        append(mapJsVariable+".moveend.push('" + clientId + "');").
        append("if (!window.controlToAdd" + mapJsVariable + ") { ").
        append("    window.controlToAdd" + mapJsVariable + " = []; ").
        append("} ").
        append("window.controlToAdd" + mapJsVariable + ".push(function() {").
        append("    if (window.OpenLayers && window.OpenLayers.Control && window.OpenLayers.Control.ScaleBar) { ").
        append("        var scb = new OpenLayers.Control.ScaleBar({div: OpenLayers.Util.getElement('" + clientId + "')}); ").
        append("        "+mapJsVariable + ".addControl(scb); ").
        append("    } ").
        append("});").
        append("window.controlToAdd" + mapJsVariable + "[window.controlToAdd" + mapJsVariable + ".length-1](); ").toString());
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
