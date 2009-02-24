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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.mapfaces.component.UILayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;


/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class LayerRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.LayerRenderer");
    
   @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
       super.encodeBegin(context, component);       
   }
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final UILayer comp = (UILayer) component;
        if (this.debug)
            LOGGER.log(Level.INFO, "[DEBUG] ENCODE END");
    }
    
    @Override
    public void decode(final FacesContext context, final UIComponent component) {

        if (context.getExternalContext().getRequestParameterMap() == null) {
            return;
        }
        final UILayer comp = (UILayer) component;
        if (this.debug)
            LOGGER.log(Level.INFO, "[DEBUG] DECODE");
        
        final Context model = (Context) comp.getModel();
        final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        final Layer layer = comp.getLayer();
        final String formId = FacesUtils.getFormId(context, comp);

//        final StringBuilder sb = new StringBuilder("Layer decode :");
//        sb.append(" Model Id = ").append(model.getId());
//        sb.append(" Layer Id = ").append(layer.getId());
//        sb.append(" Window Height = ").append(model.getWindowHeight());
//        sb.append(" Window Width = ").append(model.getWindowWidth());

        
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] \t\tparams.get('refresh') =  " +params.get("refresh"));
            LOGGER.log(Level.INFO, "[DEBUG] \t\tcomp.getClientId(context) =  " +comp.getClientId(context));
            //LOGGER.log(Level.INFO, "[DEBUG] \t\tparams.get('refresh'').equals(comp.getClientId(context)) " +params.get("refresh").equals(comp.getClientId(context)));
        }
        if (params.get("refresh") != null && params.get("refresh").equals(comp.getClientId(context))) {
            final String  bbox= params.get("bbox");
//            sb.append("\n BBox = "+ bbox);
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] \t\tparams.get('bbox'') = "+ bbox);
                LOGGER.log(Level.INFO, "[DEBUG] \t\tmodel.getbbox = " +model.getMinx() + "," + model.getMiny().toString() + "," + model.getMaxx() + "," + model.getMaxy());
            }
            if (bbox != null && !bbox.equals(model.getMinx() + "," + model.getMiny().toString() + "," + model.getMaxx() + "," + model.getMaxy())) {
                model.setMinx(bbox.split(",")[0]);
                model.setMiny(bbox.split(",")[1]);
                model.setMaxx(bbox.split(",")[2]);
                model.setMaxy(bbox.split(",")[3]);
            }
            String win = params.get("window");
            if (win == null) {
                win = params.get(formId + ":window");
            }
            if (win != null) {
                final String[] window = win.split(",");
                model.setWindowWidth(window[0]);
                model.setWindowHeight(window[1]);
            }
        }

        String value = params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE");
        final String layerId = params.get("org.mapfaces.ajax.AJAX_LAYER_ID");
        if (value == null && params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID") != null) {
            value = params.get(params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
        //layerId = params.get("refresh");
        }

        if (layerId != null) {
            final String layerProperty = params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID");
            if (layerId.equals(comp.getClientId(context))) {

                //Modify Context property
                if (layerProperty.contains("hidden")) {
                    final boolean test = !(value != null && value.equals("on"));
//                    tmp.setLayerHidden(layer.getId(), test);
                    layer.setHidden(test);
                    if (isDebug()) {
                         LOGGER.log(Level.INFO, "[DEBUG] \t\tThe property hidden of the layer " + layer.getId() + " has been modified :" + model.isLayerHidden(layer.getId()));
                    }
                } else if (layerProperty.contains("Opacity")) {
                    model.setLayerOpacity(layer.getId(), value);
                    if (isDebug()) {
                         LOGGER.log(Level.INFO, "[DEBUG] \t\tThe property opacity of the layer " + layer.getId() + " has been modified :" + model.getLayerOpacity(layer.getId()));
                    }
                } else if (layerProperty.contains("Time")) {
                    if (value == null) {
                        value = "";
                    }
                    model.setLayerAttrDimension(layer.getId(), "time", "userValue", value);
                    if (isDebug()) {
                        LOGGER.log(Level.INFO, "[DEBUG] \t\tThe property time of the layer " + layer.getId() + " has been modified :" + model.getLayerAttrDimension(layer.getId(), "time", "userValue"));
                    }
                } else if (layerProperty.contains("Elevation")) {
                    if (value == null) {
                        value = "";
                    }
                    model.setLayerAttrDimension(layer.getId(), "elevation", "userValue", value);
                    if (isDebug()) {
                        LOGGER.log(Level.INFO, "[DEBUG] \t\tThe property elevation of the layer " + layer.getId() + " has been modified :" + model.getLayerAttrDimension(layer.getId(), "elevation", "userValue"));
                    }
                } else if (layerProperty.contains("DimRange")) {
                    model.setLayerAttrDimension(layer.getId(), "dim_range", "userValue", value);
                    if (isDebug()) {
                        LOGGER.log(Level.INFO, "[DEBUG] \t\tThe property dim_range of the layer " + layer.getId() + " has been modified :" + model.getLayerAttrDimension(layer.getId(), "dim_range", "userValue"));
                    }
                }

            }
        }

        //Modify Component property
        if (params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE") != null) {
            comp.setStyle(params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE"));
        }
        
        comp.setModel((AbstractModelBase) model);
        comp.setLayer(model.getLayerFromId(layer.getId()));

        return;
    }
    
   
    /**
     * {@inheritDoc }
     */ 
    public void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
    
}
