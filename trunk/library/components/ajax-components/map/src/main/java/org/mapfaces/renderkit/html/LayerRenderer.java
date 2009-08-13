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

import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.mapfaces.component.UILayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.util.MapUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class LayerRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger(LayerRenderer.class.getName());

    @Override
    public void decode(final FacesContext context, final UIComponent component) {

        if (context.getExternalContext().getRequestParameterMap() == null) {
            return;
        }
        final UILayer comp = (UILayer) component;
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] DECODE");
        }

        final Context model = (Context) comp.getModel();
        final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        final Layer layer = comp.getLayer();

        
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] \t\tparams.get('refresh') =  " + params.get("refresh"));
            LOGGER.log(Level.INFO, "[DEBUG] \t\tcomp.getClientId(context) =  " + comp.getClientId(context));
        }

        final String bbox = params.get("bbox");

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] \t\tparams.get('bbox'') = " + bbox);
            LOGGER.log(Level.INFO, "[DEBUG] \t\tmodel.getbbox = " + model.getMinx() + "," + model.getMiny() + "," + model.getMaxx() + "," + model.getMaxy());
        }

        String win = params.get("window");

        if (win != null && ! win.equals("100,100")) {
            final String[] window = win.split(",");
            model.setWindowWidth(window[0]);
            model.setWindowHeight(window[1]);
        }

        if (bbox != null && !bbox.equals(model.getMinx() + "," + model.getMiny() + "," + model.getMaxx() + "," + model.getMaxy()) && ! win.equals("100,100")) {
            model.setMinx(bbox.split(",")[0]);
            model.setMiny(bbox.split(",")[1]);
            model.setMaxx(bbox.split(",")[2]);
            model.setMaxy(bbox.split(",")[3]);
        }
   

        final String layerId = params.get("org.mapfaces.ajax.AJAX_LAYER_ID");

        if (layerId != null) {

            if (layerId.equals(comp.getClientId(context))) {

                String value = params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE");

                final String layerProperty = params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID");
                if (layerProperty != null && value != null) {
                    final String opacityKey = "opacity";
                    final String hiddenKey = "hidden";
                    final String timeKey = "time";
                    final String elevationKey = "elevation";
                    final String dimrangeKey = "dimrange";
                    final String uservalueKey = "uservalue";
                    //Modify Context property
                    if (layerProperty.toLowerCase(Locale.getDefault()).contains(hiddenKey)) {
                        final boolean hidden = value.equals("true");
                        //                    tmp.setLayerHidden(layer.getId(), test);
                        if (layer instanceof DefaultWmsGetMapLayer) {
                            final String sldIdentifier = params.get("WmsGetMapEntry_SLD_identifier");
                            layer.setHidden(hidden, sldIdentifier);
                        } else {
                            layer.setHidden(hidden);
                        }

                        layer.setDisplayable(layer.isDisplayable(MapUtils.getScale(model)));
                        if (isDebug()) {
                            LOGGER.log(Level.INFO,
                                    this.debugChangePropertyMessage(hiddenKey,
                                        layer.getId(),
                                        String.valueOf(model.isLayerHidden(layer.getId()))));
                        }

                    } else if (layerProperty.toLowerCase().contains(opacityKey)) {
                        model.setLayerOpacity(layer.getId(), value);
                        if (isDebug()) {
                            LOGGER.log(Level.INFO, 
                                    this.debugChangePropertyMessage(opacityKey,
                                        layer.getId(),
                                        String.valueOf(model.getLayerOpacity(layer.getId()))));
                        }

                    } else if (layerProperty.toLowerCase().contains(timeKey)) {
                        model.setLayerAttrDimension(layer.getId(), timeKey, uservalueKey, value);

                        if (isDebug()) {
                            LOGGER.log(Level.INFO,
                                    this.debugChangePropertyMessage(timeKey,
                                        layer.getId(),
                                        String.valueOf(model.getLayerAttrDimension(layer.getId(), timeKey, uservalueKey))));
                        }

                    } else if (layerProperty.toLowerCase().contains(elevationKey)) {
                        model.setLayerAttrDimension(layer.getId(), elevationKey, uservalueKey, value);

                        if (isDebug()) {
                            LOGGER.log(Level.INFO,
                                    this.debugChangePropertyMessage(elevationKey,
                                        layer.getId(),
                                        String.valueOf(model.getLayerAttrDimension(layer.getId(), elevationKey, uservalueKey))));
                         }

                    } else if (layerProperty.toLowerCase().contains(dimrangeKey)) {
                        model.setLayerAttrDimension(layer.getId(), "dim_range", uservalueKey, value);
                        
                        if (isDebug()) {
                            LOGGER.log(Level.INFO,
                                    this.debugChangePropertyMessage(dimrangeKey,
                                        layer.getId(),
                                        String.valueOf(model.getLayerAttrDimension(layer.getId(), "dim_range", uservalueKey))));
                         }                     

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

    }
    private String debugChangePropertyMessage( String property, String id, String value) {
        return "[DEBUG] \t\tThe property " + property + " of the component " + id + " has been modified : " + value;
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
