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

import com.vividsolutions.jts.geom.Geometry;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.geotools.display.exception.PortrayalException;
import org.geotools.display.service.DefaultPortrayalService;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.map.FeatureMapLayer;
import org.geotools.map.MapBuilder;
import org.geotools.map.MapContext;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.style.MutableStyle;
import org.mapfaces.component.UIDataRequest;
import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.FeatureVisitor;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author Mehdi Sidhoum (Geomatys).
 */
public class DataRequestRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIDataRequest comp = (UIDataRequest) component;
        final String clientId = comp.getClientId(context);

        ResponseWriter responseWriter = context.getResponseWriter();

        responseWriter.startElement("div", comp);

        responseWriter.writeAttribute("id", clientId, "id");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        if (responseWriter == null) {
            responseWriter = FacesUtils.getResponseWriter2(context);
        }
        responseWriter.endElement("div");
        responseWriter.flush();
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (component.getChildCount() == 0) {
            return;
        }
        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {

        final UIDataRequest comp = (UIDataRequest) component;

        UIPopup popup = (UIPopup) FacesUtils.findComponentById(context, context.getViewRoot(), comp.getTargetPopupId());
        int popupWidth = 300;
        int popupHeight = 200;
        if (popup != null) {
            popupWidth = popup.getWidth();
            popupHeight = popup.getHeight();
        }


        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Context model = (Context) comp.getModel();

            final Map params = context.getExternalContext().getRequestParameterMap();

            String X = "170";
            String Y = "160";
            if (params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("getFeatureInfo")) {
                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y") != null) {
                    Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");

                    if (popup != null) {
                        final int realTop = (new Integer(Y)) - popup.getHeight();
                        ;
                        popup.setTop("top:" + realTop + "px;");
                    }
                }
                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X") != null) {
                    X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                    if (popup != null) {
                        final int realLeft = (new Integer(X)) - (popup.getWidth() / 2);
                        popup.setLeft("left:" + realLeft + "px;");
                    }
                }

                final List<Layer> layersWMS = new ArrayList<Layer>();
                String layersNameString = "";
                int nbWmsLayers = Utils.getWMSLayerscount(model.getVisibleLayers());
                int loop = 0;
                List<Feature> featureInfoList = new ArrayList<Feature>();
                boolean MFlayerExist = false;

                for (Layer queryLayer : model.getVisibleLayers()) {
                    if (queryLayer.getType().equals("mapfaces")) {

                        Map mapFeaturesLayer = new HashMap<String, Feature>();


                        //@TODO do something to generate an Object List of Result from the attached features
                        MFlayerExist = true;
                        final String featureInfo_X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                        final String featureInfo_Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");
                        System.out.println("========= boundingbox = " + model.getBoundingBox());
                        System.out.println("========= X = " + featureInfo_X);
                        System.out.println("========= Y = " + featureInfo_Y);
                        System.out.println("========= MFlayer = " + queryLayer.getId() + "    features size = " + queryLayer.getFeatures().size());

//                        fillFeatureListIntersect(featureInfoList, 
//                                                 queryLayer.getFeatures(),
//                                                 model.getBoundingBox(),
//                                                 featureInfo_X,
//                                                 featureInfo_Y);

                        MapContext mapContext;

                        MutableStyle mutableStyle = null;

                        //building a FeatureCollection for this layer.
                        FeatureCollection<SimpleFeatureType, SimpleFeature> features = FeatureCollections.newCollection();
                        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

                        try {
                            mutableStyle = MFLayerRenderer.createStyle(queryLayer.getImage(), queryLayer.getSize(), queryLayer.getRotation(), 1);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        DefaultGeographicCRS layerCrs = DefaultGeographicCRS.WGS84;
                        if (queryLayer.getFeatures() != null && queryLayer.getFeatures().size() != 0) {

                            Feature f = queryLayer.getFeatures().get(0);
                            builder.setName(f.getName());
                            layerCrs = f.getCrs();
                            builder.setCRS(layerCrs);
                            for (String key : f.getAttributes().keySet()) {
                                if (key.equals("geometry")) {
                                    builder.add(key, Geometry.class);
                                } else {
                                    builder.add(key, f.getAttributes().get(key).getClass());
                                }
                            }
                        }

                        SimpleFeatureType sft = builder.buildFeatureType();
                        for (Feature f : queryLayer.getFeatures()) {
                            if (!mapFeaturesLayer.containsKey(f.getId())) {
                                mapFeaturesLayer.put(f.getId(), f);
                            }

                            List<Object> objects = new ArrayList<Object>();
                            for (String key : f.getAttributes().keySet()) {
                                objects.add(f.getAttributes().get(key));
                            }

                            SimpleFeature sf = new SimpleFeatureImpl(objects, sft, new FeatureIdImpl(f.getId()));
                            features.add(sf);
                        }

                        final FeatureMapLayer mapLayer = MapBuilder.getInstance().createFeatureLayer(features, mutableStyle);
                        mapLayer.setSelectable(true);
                        mapContext = MapBuilder.getInstance().createContext(layerCrs);
                        mapContext.layers().add(mapLayer);
                        Rectangle rect = new Rectangle(Integer.parseInt(featureInfo_X), Integer.parseInt(featureInfo_Y), 1, 1);
                        FeatureVisitor featureVisitor = new FeatureVisitor();
                        try {
                            DefaultPortrayalService.visit(mapContext, model.getEnvelope(), model.getDimension(), true, null, rect, featureVisitor);
                        } catch (PortrayalException ex) {
                            Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //Adding the resulting feature into the final list of features for dataResult ValueExpression value.
                        for (org.opengis.feature.Feature f : featureVisitor.getFeatureList()) {
                            if (f instanceof org.opengis.feature.simple.SimpleFeature) {
                                org.opengis.feature.simple.SimpleFeature ff = (org.opengis.feature.simple.SimpleFeature) f;

                                Feature resultFeature = (Feature) mapFeaturesLayer.get(ff.getID());
                                if (resultFeature != null && !featureInfoList.contains(resultFeature)) {
                                    featureInfoList.add(resultFeature);
                                }
                            }
                        }
                        mapFeaturesLayer.clear();

                    } else {
                        loop++;
                        if (!layersWMS.contains(queryLayer)) {
                            layersWMS.add(queryLayer);
                            layersNameString += queryLayer.getName();
                            if (loop != nbWmsLayers) {
                                layersNameString += ",";
                            }
                        }
                    }
                }

                //setting the value expression for dataResult if not null
                ValueExpression ve = comp.getValueExpression("dataResult");
                if (ve != null) {
                    ve.setValue(context.getELContext(), featureInfoList);
                    comp.setValueExpression("dataResult", ve);
                }
                comp.setDataResult(featureInfoList);
                if (featureInfoList.size() == 0 ){
                    popup.setRendered(false);
                }else {
                    popup.setRendered(true);
                }

                final int innerWidth = popupWidth - 73;
                final int innerHeight = popupHeight - 75;
                String outputFormat = (comp.getOutputFormat() != null && !comp.getOutputFormat().equals("")) ? comp.getOutputFormat() : "text/html";

                if (popup != null && popup.isIframe()) {

                    StringBuilder innerHtml = new StringBuilder("<div style='width:").append(innerWidth).append("px;height:").append(innerHeight).append("px;overflow-x:auto;overflow-y:auto;'>");
                    //@TODO factorization of servers wms, one request by server and QUERY_LAYERS must contains all layers name
                    for (Layer queryLayer : layersWMS) {
                        innerHtml.append("<iframe style='width:").append(innerWidth).append("px;height:").append(innerHeight).append("px;font-size:0.7em;font-family:verdana;border:none;overflow:hidden;z-index:150;' id='popup' name='popup' src='").
                                append(queryLayer.getServer().getHref()).
                                append("?BBOX=").append(model.getBoundingBox()).
                                append("&STYLES=").
                                append("&FORMAT=").append(queryLayer.getOutputFormat()).
                                append("&INFO_FORMAT=").append(outputFormat).
                                append("&VERSION=").append(queryLayer.getServer().getVersion()).
                                append("&SRS=").append(model.getSrs().toUpperCase()).
                                append("&REQUEST=GetFeatureInfo").
                                append("&LAYERS=").append(queryLayer.getName()).
                                append("&QUERY_LAYERS=").append(queryLayer.getName()).
                                append("&WIDTH=").append(model.getWindowWidth()).
                                append("&HEIGHT=").append(model.getWindowHeight()).
                                append("&X=").append(X).
                                append("&Y=").append(Y).
                                append("'></iframe><br/>");
                    }
                    innerHtml.append("</div>");
                    popup.setInnerHTML(innerHtml.toString());

                    //setting the value expression for dataResult if not null
                    if (ve != null && (ve.getValue(context.getELContext()) instanceof String)) {
                        ve.setValue(context.getELContext(), innerHtml);
                        comp.setValueExpression("dataResult", ve);
                    }
                    comp.setDataResult(innerHtml);

                }

            } else if (params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("getCoverage")) {

                final Layer queryLayer = model.getVisibleLayers().get(model.getVisibleLayers().size() - 1);

                String elevation = null;
                if (queryLayer.getElevation() != null) {
                    elevation = queryLayer.getElevation().getUserValue() + "," + queryLayer.getElevation().getUserValue();
                }
                String time = null;
                if (queryLayer.getTime() != null) {
                    time = queryLayer.getTime().getUserValue();
                }
                String[] windowPixel = null;
                if ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL") != null) {
                    windowPixel = ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL")).split(",");
                }
                String innerHTML = "<iframe style='display:none' id='popup' name='popup' src='" + queryLayer.getServer().getHref().substring(0, queryLayer.getServer().getHref().lastIndexOf("/")) + "/wcs" +
                        "?BBOX=" + (String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_AOI");
                if (elevation != null) {
                    innerHTML += "," + elevation;
                }
                innerHTML += "&STYLES=" + "&FORMAT=" + (String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_FORMAT") +
                        "&VERSION=" + "1.0.0" + "&CRS=" + model.getSrs() +
                        "&REQUEST=GetCoverage'" + "&COVERAGE=" + queryLayer.getName() +
                        "&WIDTH=" + windowPixel[0] + "&HEIGHT=" + windowPixel[1];
                if (time != null) {
                    innerHTML += "&TIME=" + time;
                }
                innerHTML += "'></iframe>";
                // innerHTML = "<iframe style='display:none' id='popup' name='popup' src='http://demo.geomatys.fr/constellation/WS/wcs?bbox=-14978036.532703482,-5209751.837462081,-10369409.907256257,-1402625.4947013294,5.0,5.0&styles=&format=matrix&version=1.0.0&crs=EPSG:3395&request=GetCoverage&coverage=AO_Coriolis_(Temp)&width=1259&height=176&time=2007-06-20T12:00:00Z'></iframe>";
                if (popup != null && popup.isIframe()) {
                    popup.setInnerHTML(innerHTML);
                }

            } else {
                //init the popup innerHtml in others decode process
                if (popup != null && popup.isIframe()) {
                    popup.setInnerHTML(null);
                }
            }
            comp.setModel((AbstractModelBase) model);

        }
        return;
    }

    public void fillFeatureListIntersect(List<Feature> listTofill, List<Feature> FeaturelistLayer, String boundingBox, String x, String y) {
        for (Feature f : FeaturelistLayer) {
//            System.out.println("===== "+f.getGeometry().intersects(arg0));
        }
    }
}
