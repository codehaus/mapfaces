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
import org.mapfaces.component.UIButtonBar;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.util.Utils;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class ButtonBarRenderer extends WidgetBaseRenderer {

    
    private static final Logger LOGGER = Logger.getLogger(ButtonBarRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

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
        
        final UIButtonBar comp = (UIButtonBar) component;
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
            writer.writeAttribute("class", "mfButtonBar", "styleclass");
        }
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach NavigationHistory control
         */
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        writer.write(new StringBuilder("").append("if(!window.controlToAdd" + jsObject + "){window.controlToAdd" + jsObject + " = [];}\n").
                append("window.controlToAdd" + jsObject + ".push(\n\tfunction() {\n" +
                "").toString());

        if (comp.isHistory()) {

            writer.write(
                    "\t\tif (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavigationHistory) {\n" +
                    "\t\t\twindow.nav = new OpenLayers.Control.NavigationHistory();\n");
            writer.write("\t\t\t" + jsObject + ".addControl(window.nav);\n" +
                    "\t\t}\n");
        }
        if (comp.isZoomIn() || comp.isHistory() || comp.isZoomOut() || comp.isPan() || comp.isZoomMaxExtent()) {

            final String idDivbar;
            if (comp.isFloatingBar()) {
                idDivbar = comp.getId();
            } else {
                idDivbar = comp.getClientId(context);
            }
//            /**
//             *TBD
//             *Test for editing tools 
//             */
//            writer.write("\t\t\twindow.vlayer = new OpenLayers.Layer.Vector( 'Editable' );\n");
//           writer.write("\t\t\t" + jsObject + ".addLayer(window.vlayer);\n");
//          // writer.write("\t\t\t" + jsObject + ".addLayer(window.gml);\n");
//           //writer.write("\t\t\t" + jsObject + ".addControl(new OpenLayers.Control.EditingToolbar(vlayer));");
//            //TBD
           
            writer.write("\n" +
                    "\t\tif (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavToolbar) {\n" +
                    "\t\t\tvar " + jsObject + comp.getId() + " = new OpenLayers.Control.NavToolbar({\n\t\t\t\t'div':OpenLayers.Util.getElement('" + idDivbar + "')");

            if (comp.isZoomIn()) {
                writer.write(",\n\t\t\t\tzoomIn: true");
            }
            if (comp.isZoomOut()) {
                writer.write(",\n\t\t\t\tzoomOut: true");
            }
            if (comp.isPan()) {
                writer.write(",\n\t\t\t\tpan: true");
            }
            if (comp.isZoomMaxExtent()) {
                writer.write(",\n\t\t\t\tzoomMaxExtent: true");
            }
            if (comp.isHistory()) {
                writer.write(",\n\t\t\t\thistory: true");
            }
            if (comp.isGraticule()) {
                writer.write(",\n\t\t\t\tgraticule: true");
            }
            if (comp.isSave()) {
                writer.write(",\n\t\t\t\tsave: true");
            }
            if (comp.isPan() && comp.isPanEffect()) {
                writer.write(",\n\t\t\t\tpanEffect: true");
            }
            if (comp.isFeatureInfo()) {
                final String rerender = comp.getReRender();
                String idsToRefresh = Utils.buildRerenderStringFromString(formId, rerender);
                String clientIdAjaxRegion = FacesUtils.findClientIdComponentClass(context, context.getViewRoot(), HtmlAjaxRegion.class);

                writer.write(",\n\t\t\t\tgetFeatureInfo: true");
                if (idsToRefresh != null) {
                    writer.write(",\n\t\t\t\tgetFeatureInfoOptions: {\n\t\t\t\t\tidToRefresh:'" + idsToRefresh + "'");
                }

                if (comp.isCallAjaxRegion() && clientIdAjaxRegion != null) {
                    writer.write(",\n\t\t\t\t\tajaxRegionClientId:'"+clientIdAjaxRegion+"'");
                }
                writer.write("\n\t\t\t\t}");


            }
            if (comp.isMeasureDistance()) {
                writer.write(",\n\t\t\t\tmeasureDistance: true");
            }
            if (comp.isMeasureArea()) {
                writer.write(",\n\t\t\t\tmeasureArea: true");
            }

            if (comp.isSelectionZoomBox()) {

                final String northId = comp.getNorthIdSelectionBox();
                final String southId = comp.getSouthIdSelectionBox();
                final String eastId = comp.getEastIdSelectionBox();
                final String westId = comp.getWestIdSelectionBox();
                final String focusId = comp.getFocusIdSelectionBox();
                final String colorBox = comp.getColorSelectionBox();

                writer.write(",\n\t\t\t\tselectionZoomBox: true");
                writer.write(",\n\t\t\t\tselectionZoomBoxOptions: {north:'" + northId + "',south:'" + southId + "',east:'" + eastId + "',west:'" + westId + "',focusId:'" + focusId + "',color:'" + colorBox + "'}");
            }

            writer.write("\n\t\t\t});\n");
  
           
           
            writer.write("\n\t\t\t" + jsObject + ".addControl(" + jsObject + comp.getId() + ");\n");
            
            
            
            writer.write("\t\t}\n" +
                    "\t}\n);\n" +
                    "window.controlToAdd" + jsObject + "[window.controlToAdd" + jsObject + ".length-1]();\n");
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
