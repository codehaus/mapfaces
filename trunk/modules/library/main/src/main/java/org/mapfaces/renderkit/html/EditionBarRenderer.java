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
import org.ajax4jsf.ajax.html.HtmlAjaxRegion;
import org.mapfaces.component.UIEditionBar;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.util.Utils;
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
        UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                mapJsVariable = uIMapPane.getClientId(context);
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            return;
        }
        
        final UIEditionBar comp = (UIEditionBar) component;
        final String clientId = comp.getClientId(context);

        String formId = FacesUtils.getFormId(context, component);
        if (formId == null && clientId.contains(":")) {
            formId = clientId.substring(0, clientId.indexOf(":"));
        }

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");

        final String style = (String) comp.getAttributes().get("style");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        } else {
            writer.writeAttribute("style", "position:absolute;z-index:150;", "style");
        }
        final String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null) {
            writer.writeAttribute("class", styleclass, "styleclass");
        } else {
            writer.writeAttribute("class", "mfEditionBar", "styleclass");
        }
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach NavigationHistory control
         */
        if (mapJsVariable.contains(":")) {
            mapJsVariable = mapJsVariable.replace(":", "");
        }
        writer.write(new StringBuilder("").append("if(!window.controlToAdd" + mapJsVariable + "){window.controlToAdd" + mapJsVariable + " = [];}\n").
                append("window.controlToAdd" + mapJsVariable + ".push(\n\tfunction() {\n" +
                "").toString());

        if (comp.isDrawPoint()) {

            writer.write(
                    "\t\tif (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavigationHistory) {\n" +
                    "\t\t\twindow.nav = new OpenLayers.Control.NavigationHistory();\n");
            writer.write("\t\t\t" + mapJsVariable + ".addControl(window.nav);\n" +
                    "\t\t}\n");
        }
        if (comp.isDrawPoint() || comp.isDrawLine() || comp.isDrawPolygon() || comp.isModify() || comp.isSelect()) {

            final String idDivbar;
            if (comp.isFloatingBar()) {
                idDivbar = comp.getId();
            } else {
                idDivbar = comp.getClientId(context);
            }
            
            //Name of the js variable in client side
            String controlJsVariable = mapJsVariable + comp.getId();
//            /**
//             *TBD
//             *Test for editing tools 
//             */
            writer.write("\t\t\twindow.editingLayer = new OpenLayers.Layer.Vector( 'Editing' );\n");
            writer.write("\t\t\t" + mapJsVariable + ".addLayer(window.editingLayer);\n");
//          // writer.write("\t\t\t" + jsObject + ".addLayer(window.gml);\n");
//           //writer.write("\t\t\t" + jsObject + ".addControl(new OpenLayers.Control.EditingToolbar(vlayer));");
//            //TBD
           
            writer.write("\n" +
                    "\t\tif (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.EditingToolbar) {\n" +
                    "\t\t\tvar " + controlJsVariable + " = new OpenLayers.Control.EditingToolbar(editingLayer, {\n\t\t\t\t'div':OpenLayers.Util.getElement('" + idDivbar + "')");


            if (comp.isDrawPoint()) {
                writer.write(",\n\t\t\t\tdrawPoint: true");
            }
            if (comp.isDrawLine()) {
                writer.write(",\n\t\t\t\tdrawLine: true");
            }
            if (comp.isDrawPolygon()) {
                writer.write(",\n\t\t\t\tdrawPolygon: true");
            }
            if (comp.isDrawRegularPolygon()) {
                writer.write(",\n\t\t\t\tdrawRegularPolygon: true");
                writer.write(",\n\t\t\t\tregularPolygonSides: " + comp.getRegularPolygonSides());
            }
            if (comp.isSelect()) {
                writer.write(",\n\t\t\t\tselect: true");
            }
            if (comp.isModify()) {
                writer.write(",\n\t\t\t\tmodify: true");
            }
            if (comp.isDeleteFeature()) {
                writer.write(",\n\t\t\t\tdeleteFeature: true");
            }
            if (comp.isDrag()) {
                writer.write(",\n\t\t\t\tdrag: true");
            }
            if (comp.isResize()) {
                writer.write(",\n\t\t\t\tresize: true");
            }
            if (comp.isRotate()) {
                writer.write(",\n\t\t\t\trotate: true");
            }
                    

            writer.write("\n\t\t\t});\n");

            if (comp.isSnapping()) {
                writer.write("\n\t\t\t" + controlJsVariable + "_Snapping = new OpenLayers.Control.Snapping({layer: window.editingLayer});\n");
                writer.write("\n\t\t\t" + mapJsVariable + ".addControl(" + controlJsVariable + "_Snapping);\n");
                writer.write("\n\t\t\t" + controlJsVariable + "_Snapping.activate();\n");
            }
            if (comp.isSplit()) {
                writer.write("\n\t\t\t" + controlJsVariable + "_Split = new OpenLayers.Control.Split({layer: window.editingLayer, source: window.editingLayer,tolerance: 0.0001});\n");
                writer.write("\n\t\t\t" + mapJsVariable + ".addControl(" + controlJsVariable + "_Snapping);\n");
                writer.write("\n\t\t\t" + controlJsVariable + "_Split.activate();\n");
            }

            writer.write("\n\t\t\t" + mapJsVariable + ".addControl(" + controlJsVariable + ");\n");
            writer.write("\t\t}\n" +
                    "\t}\n);\n" +
                    "window.controlToAdd" + mapJsVariable + "[window.controlToAdd" + mapJsVariable + ".length-1]();\n");
            }
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
