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

import org.mapfaces.util.RendererUtils.HTML;
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
        final UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
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
            writer.writeAttribute(HTML.class_ATTRIBUTE, "mfButtonBar", "styleclass");
        }
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");

        
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach NavigationHistory control
         */
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        writer.write(new StringBuilder("").append("if(!window.controlToAdd" + jsObject + "){window.controlToAdd" + jsObject + " = [];}").
                append("window.controlToAdd" + jsObject + ".push(function() {" +
                "").toString());

        if (comp.isHistory()) {

            writer.write(
                    "if (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavigationHistory) {" +
                    "window.nav = new OpenLayers.Control.NavigationHistory();");
            writer.write("" + jsObject + ".addControl(window.nav);" +
                    "}");
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
//            writer.write("window.vlayer = new OpenLayers.Layer.Vector( 'Editable' );");
//           writer.write("" + jsObject + ".addLayer(window.vlayer);");
//          // writer.write("" + jsObject + ".addLayer(window.gml);");
//           //writer.write("" + jsObject + ".addControl(new OpenLayers.Control.EditingToolbar(vlayer));");
//            //TBD
           
            writer.write("" +
                    "if (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavToolbar) {" +
                    "var " + jsObject + comp.getId() + " = new OpenLayers.Control.NavToolbar({'div':OpenLayers.Util.getElement('" + idDivbar + "')");

            if (comp.isZoomIn()) {
                writer.write(",zoomIn: true");
            }
            if (comp.isZoomOut()) {
                writer.write(",zoomOut: true");
            }
            if (comp.isPan()) {
                writer.write(",pan: true");
            }
            if (comp.isZoomMaxExtent()) {
                writer.write(",zoomMaxExtent: true");
            }
            if (comp.isHistory()) {
                writer.write(",history: true");
            }
            if (comp.isGraticule()) {
                writer.write(",graticule: true");
            }
            if (comp.isSave()) {
                writer.write(",save: true");
            }
            if (comp.isPan() && comp.isPanEffect()) {
                writer.write(",panEffect: true");
            }
            if (comp.isFeatureInfo()) {
                final String rerender = comp.getReRender();
                final String idsToRefresh = Utils.buildRerenderStringFromString(formId, rerender);
                final String clientIdAjaxRegion = FacesUtils.findClientIdComponentClass(context, context.getViewRoot(), HtmlAjaxRegion.class);

                writer.write(",getFeatureInfo: true");
                if (idsToRefresh != null) {
                    writer.write(",getFeatureInfoOptions: {idToRefresh:'" + idsToRefresh + "'");
                }

                if (comp.isCallAjaxRegion() && clientIdAjaxRegion != null) {
                    writer.write(",ajaxRegionClientId:'"+clientIdAjaxRegion+"'");
                }
                writer.write("}");


            }
            if (comp.isMeasureDistance()) {
                writer.write(",measureDistance: true");
            }
            if (comp.isMeasureArea()) {
                writer.write(",measureArea: true");
            }

            if (comp.isSelectionZoomBox()) {

                final String northId = comp.getNorthIdSelectionBox();
                final String southId = comp.getSouthIdSelectionBox();
                final String eastId = comp.getEastIdSelectionBox();
                final String westId = comp.getWestIdSelectionBox();
                final String focusId = comp.getFocusIdSelectionBox();
                final String colorBox = comp.getColorSelectionBox();

                writer.write(",selectionZoomBox: true");
                writer.write(",selectionZoomBoxOptions: {north:'" + northId + "',south:'" + southId + "',east:'" + eastId + "',west:'" + westId + "',focusId:'" + focusId + "',color:'" + colorBox + "'}");
            }

            writer.write("});");
  
           
           
            writer.write("" + jsObject + ".addControl(" + jsObject + comp.getId() + ");");
            
            
            
            writer.write("}" +
                    "});" +
                    "window.controlToAdd" + jsObject + "[window.controlToAdd" + jsObject + ".length-1]();");
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
