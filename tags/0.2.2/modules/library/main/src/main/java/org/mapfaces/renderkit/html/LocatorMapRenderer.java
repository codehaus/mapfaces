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
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.UILocatorMap;
import org.mapfaces.models.Context;
import org.mapfaces.taglib.LocatorMapTag;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.component.UIMapPane;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class LocatorMapRenderer extends MapPaneRenderer {

    
    private static final Logger LOGGER = Logger.getLogger(LocatorMapRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if((String) component.getAttributes().get("styleClass")==null)
            setStyleClass("mf" + LocatorMapTag.COMP_TYPE.substring(LocatorMapTag.COMP_TYPE.lastIndexOf(".") + 1, LocatorMapTag.COMP_TYPE.length()));
        super.encodeBegin(context, component);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final UILocatorMap comp     = (UILocatorMap) component;
        final String clientId       = comp.getClientId(context);
        final ResponseWriter writer = context.getResponseWriter();
        final Context model;

        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }        

        writer.endElement("div");
        writer.endElement("div");

        if(comp.getTargetContextCompId() != null){
            writer.startElement("script", comp);
            writer.writeAttribute("type", "text/javascript", "text/javascript");

            //suppression des ":" pour nommer l'objet javascript correspondant correctement   String jsObject = component.getClientId(context);
            String jsObject = comp.getClientId(context);
            if (jsObject.contains(":")) jsObject = jsObject.replace(":", "");

            //suppression des ":" pour nommer l'objet javascript correspondant correctement
            //Find UIMapPane refers to this widget 
            String mapJsObject = null ;
            UIMapPane uIMapPane = FacesUtils.getUIMapPane(context, component);
            if (uIMapPane != null) {
                    mapJsObject = uIMapPane.getClientId(context);
            } else {
                LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
                component.setRendered(false);
                return;
            }
            if (mapJsObject.contains(":")) {
                //TODO don't commi this 
                mapJsObject = mapJsObject.split(":")[0]+"mappane";
            }else{
                mapJsObject = "mappane";
            }
            writer.write(new StringBuilder("    var ovmapOptions = {\n")
                    .append("                       id:'").append(jsObject).append("',\n")
                    .append("                       outsideViewport:true,")
                    .append("                       controls:[],\n")
                    .append("                       projection: new OpenLayers.Projection('").append(model.getSrs().toUpperCase()).append("'),\n")
                    .append("                       size: new OpenLayers.Size('300','150'),\n")
                    .append("                       maxExtent: new OpenLayers.Bounds(").append(comp.getMaxExtent()).append("),\n")
                    .append("                       currentExtent: new OpenLayers.Bounds(").append(model.getMinx().toString()).append(",").append(model.getMiny().toString()).append(",").append(model.getMaxx().toString()).append(",").append(model.getMaxy().toString()).append("),\n")
                    .append("                       maxResolution: 'auto',\n")
                    .append("                       theme:  null ,\n")
                    .append("                       fractionnalZoom:  true ,\n")
                    .append("                       layersName:  '").append(model.getLayersCompId().split(",")[0] ).append("' ,\n")
                    .append("                       mfAjaxCompId:'").append(comp.getAjaxCompId()).append("',\n")
                    .append("                       mfFormId:'").append(FacesUtils.getFormId(context, component)).append("',\n")
                    .append("                       mfRequestId:'updateBboxOrWindow'\n")
                    .append("                   };\n").toString());

            writer.write("var " + jsObject + " = new OpenLayers.Control.OverviewMap({div: OpenLayers.Util.getElement('" + clientId + "'),mapOptions:ovmapOptions});\n");
            writer.write(new StringBuilder(mapJsObject).append(".addControl(").append(jsObject).append(");\n")
                .append("    if(!window.maps){window.maps = {};}\n")
                .append("    window.maps.").append(jsObject).append(" = ").append(jsObject).append(".ovmap;\n").toString());
            writer.endElement("script");
        }
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
