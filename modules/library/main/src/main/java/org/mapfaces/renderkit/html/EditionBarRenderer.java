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
import org.ajax4jsf.framework.renderer.RendererUtils.HTML;
import org.mapfaces.component.UIEditionBar;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class EditionBarRenderer extends WidgetBaseRenderer {

    
    private static final Logger LOGGER = Logger.getLogger(EditionBarRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        
        
        //Find UIMapPane refers to this widget 
        String mapJsVariable = null ;
        final UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                mapJsVariable = FacesUtils.getJsVariableFromClientId(uIMapPane.getClientId(context));
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            return;
        }
        
        final UIEditionBar comp = (UIEditionBar) component;
        final String clientId = comp.getClientId(context);

        String formId = FacesUtils.getFormId(context, component);
        if (formId == null && clientId.contains(":")) {
            formId = clientId.substring(0, clientId.indexOf(':'));
        }

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);

        final String style = (String) comp.getAttributes().get(HTML.style_ATTRIBUTE);
        if (style != null) {
            writer.writeAttribute(HTML.style_ATTRIBUTE, style, HTML.style_ATTRIBUTE);
        } else {
            writer.writeAttribute(HTML.style_ATTRIBUTE, "position:absolute;z-index:150;", HTML.style_ATTRIBUTE);
        }
        final String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null) {
            writer.writeAttribute(HTML.class_ATTRIBUTE, styleclass, "styleclass");
        } else {
            writer.writeAttribute(HTML.class_ATTRIBUTE, "mfEditionBar", "styleclass");
        }
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");

        
        writer.write(new StringBuilder("").append("if(!window.controlToAdd" + mapJsVariable + "){window.controlToAdd" + mapJsVariable + " = [];}").
                append("window.controlToAdd" + mapJsVariable + ".push(function() {" +
                "").toString());

        if (comp.isDrawPoint() || comp.isDrawLine() || comp.isDrawPolygon() || comp.isModify() || comp.isSelect()) {

            final String idDivbar;
            if (comp.isFloatingBar()) {
                idDivbar = comp.getId();
            } else {
                idDivbar = comp.getClientId(context);
            }
            
            //Name of the js variable in client side
            final String controlJsVariable = FacesUtils.getJsVariableFromClientId(clientId);
//            /**
//             *TBD
//             *Test for editing tools 
//             */
            writer.write("window.editingLayer = new OpenLayers.Layer.Vector( 'Editing' );");
            writer.write("" + mapJsVariable + ".addLayer(window.editingLayer);");
//          // writer.write("" + jsObject + ".addLayer(window.gml);");
//           //writer.write("" + jsObject + ".addControl(new OpenLayers.Control.EditingToolbar(vlayer));");
//            //TBD
           
            writer.write("" +
                    "if (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.EditingToolbar) {" +
                    "var " + controlJsVariable + " = new OpenLayers.Control.EditingToolbar(editingLayer, {'div':OpenLayers.Util.getElement('" + idDivbar + "')");


            if (comp.isDrawPoint()) {
                writer.write(",drawPoint: true");
            }
            if (comp.isDrawLine()) {
                writer.write(",drawLine: true");
            }
            if (comp.isDrawPolygon()) {
                writer.write(",drawPolygon: true");
            }
            if (comp.isDrawRegularPolygon()) {
                writer.write(",drawRegularPolygon: true");
                writer.write(",regularPolygonSides: " + comp.getRegularPolygonSides());
            }
            if (comp.isSelect()) {
                writer.write(",select: true");
            }
            if (comp.isModify()) {
                writer.write(",modify: true");
            }
            if (comp.isDeleteFeature()) {
                writer.write(",deleteFeature: true");
            }
            if (comp.isDrag()) {
                writer.write(",drag: true");
            }
            if (comp.isResize()) {
                writer.write(",resize: true");
            }
            if (comp.isRotate()) {
                writer.write(",rotate: true");
            }
                    

            writer.write("});");

            if (comp.isSnapping()) {
                writer.write("" + controlJsVariable + "_Snapping = new OpenLayers.Control.Snapping({layer: window.editingLayer});");
                writer.write("" + mapJsVariable + ".addControl(" + controlJsVariable + "_Snapping);");
                writer.write("" + controlJsVariable + "_Snapping.activate();");
            }
//            if (comp.isSplit()) {
//This control is not enough robust
//                writer.write("" + controlJsVariable + "_Split = new OpenLayers.Control.Split({layer: window.editingLayer, source: window.editingLayer,tolerance: 0.0001});");
//                writer.write("" + mapJsVariable + ".addControl(" + controlJsVariable + "_Split);");
//                writer.write("" + controlJsVariable + "_Split.activate();");
//            }

            writer.write("" + mapJsVariable + ".addControl(" + controlJsVariable + ");");
            writer.write("}" +
                    "});" +
                    "window.controlToAdd" + mapJsVariable + "[window.controlToAdd" + mapJsVariable + ".length-1]();");
            }
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
