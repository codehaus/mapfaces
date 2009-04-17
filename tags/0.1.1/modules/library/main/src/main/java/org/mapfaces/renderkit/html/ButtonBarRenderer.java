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
import org.ajax4jsf.ajax.html.HtmlAjaxRegion;
import org.mapfaces.component.UIButtonBar;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class ButtonBarRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);
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

        //suppression des ":" pour nommer l'objet javascript correspondant correctement.
        String jsObject = "";
        comp_loop:
        for (UIComponent comps : comp.getParent().getChildren()) {
            if (comps instanceof UIMapPane) {
                jsObject = comps.getClientId(context);
                break comp_loop;
            }
        }
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach NavigationHistory control
         */
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        writer.write(new StringBuilder("").append("if(!window.controlToAdd" + jsObject + "){window.controlToAdd" + jsObject + " = [];}\n").
                append("window.controlToAdd" + jsObject + ".push(function() {" +
                "").toString());

        if (comp.isHistory()) {

            writer.write(
                    "if (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavigationHistory) {\n" +
                    "window.nav = new OpenLayers.Control.NavigationHistory();\n");
            writer.write(jsObject + ".addControl(window.nav);\n" +
                    "}\n");
        }
        if (comp.isZoomIn() || comp.isHistory() || comp.isZoomOut() || comp.isPan() || comp.isZoomMaxExtent()) {

            final String idDivbar;
            if (comp.isFloatingBar()) {
                idDivbar = comp.getId();
            } else {
                idDivbar = comp.getClientId(context);
            }
            writer.write("\n" +
                    "if (window.OpenLayers &&  window.OpenLayers.Control && window.OpenLayers.Control.NavToolbar) {\n" +
                    "var " + jsObject + comp.getId() + " = new OpenLayers.Control.NavToolbar({\n'div':OpenLayers.Util.getElement('" + idDivbar + "')");

            if (comp.isZoomIn()) {
                writer.write(",\nzoomIn: true");
            }
            if (comp.isZoomOut()) {
                writer.write(",\nzoomOut: true");
            }
            if (comp.isPan()) {
                writer.write(",\npan: true");
            }
            if (comp.isZoomMaxExtent()) {
                writer.write(",\nzoomMaxExtent: true");
            }
            if (comp.isHistory()) {
                writer.write(",\nhistory: true");
            }
            if (comp.isGraticule()) {
                writer.write(",\ngraticule: true");
            }
            if (comp.isSave()) {
                writer.write(",\nsave: true");
            }
            if (comp.isPan() && comp.isPanEffect()) {
                writer.write(",\npanEffect: true");
            }
            if (comp.isFeatureInfo()) {
                final String rerender = comp.getReRender();
                String idsToRefresh = Utils.buildRerenderStringFromString(formId, rerender);
                String clientIdAjaxRegion = FacesUtils.findClientIdComponentClass(context, context.getViewRoot(), HtmlAjaxRegion.class);

                writer.write(",\ngetFeatureInfo: true");
                if (idsToRefresh != null) {
                    writer.write(",\ngetFeatureInfoOptions: {idToRefresh:'" + idsToRefresh + "'");
                }

                if (comp.isCallAjaxRegion() && clientIdAjaxRegion != null) {
                    writer.write(",ajaxRegionClientId:'"+clientIdAjaxRegion+"'");
                }
                writer.write("}");


            }
            if (comp.isMeasureDistance()) {
                writer.write(",\nmeasureDistance: true");
            }
            if (comp.isMeasureArea()) {
                writer.write(",\nmeasureArea: true");
            }

            if (comp.isSelectionZoomBox()) {

                final String northId = comp.getNorthIdSelectionBox();
                final String southId = comp.getSouthIdSelectionBox();
                final String eastId = comp.getEastIdSelectionBox();
                final String westId = comp.getWestIdSelectionBox();
                final String focusId = comp.getFocusIdSelectionBox();
                final String colorBox = comp.getColorSelectionBox();

                writer.write(",\nselectionZoomBox: true");
                writer.write(",\nselectionZoomBoxOptions: {north:'" + northId + "',south:'" + southId + "',east:'" + eastId + "',west:'" + westId + "',focusId:'" + focusId + "',color:'" + colorBox + "'}");
            }

            writer.write("\n});\n");

            writer.write(jsObject + ".addControl(" + jsObject + comp.getId() + ");\n" +
                    "}\n" +
                    "});\n" +
                    "window.controlToAdd" + jsObject + "[window.controlToAdd" + jsObject + ".length-1]();");
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
