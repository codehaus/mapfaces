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
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
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
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.FeatureVisitor;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @author Olivier Terral (Geomatys).
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
        
        if (comp.isInvokeActions()) {
            //invoke methodBinding on action and actionListener if not null.
            if (comp.getActionExpression() != null) {
                   comp.getActionExpression().invoke(context.getELContext(), null);
            }
            for (ActionListener al : comp.getActionListeners()) {
                   al.processAction(new ActionEvent(component));
            }
        }

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final UIDataRequest comp = (UIDataRequest) component;
        ResponseWriter responseWriter = context.getResponseWriter();
        if (responseWriter == null) {
            responseWriter = FacesUtils.getResponseWriter2(context);
        }
        responseWriter.endElement("div");
        responseWriter.flush();
        //init the invocation on action methods.
        comp.setInvokeActions(false);
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
                
                //setting to the component the real latitude and logitude by calculating from pixels.
                //setting the values lat and lon expressions if not null
                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_LAT") != null) {
                    final double lat = Double.parseDouble((String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_LAT"));
                    final ValueExpression veLat = comp.getValueExpression("outputLatitude");
                    if (veLat != null) {
                        veLat.setValue(context.getELContext(), lat);
                        comp.setValueExpression("outputLatitude", veLat);
                    }
                    comp.setOutputLatitude(lat);
                }
                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_LON") != null) {
                    final double lon = Double.parseDouble( (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_LON"));
                    final ValueExpression veLon = comp.getValueExpression("outputLongitude");
                    if (veLon != null) {
                        veLon.setValue(context.getELContext(), lon);
                        comp.setValueExpression("outputLongitude", veLon);
                    }
                    comp.setOutputLongitude(lon);
                }
                
                final List<WmsLayer> layersWMS = new ArrayList<WmsLayer>();
                String layersNameString = "";
                int nbWmsLayers = Utils.getWMSLayerscount(model.getVisibleLayers());
                int loop = 0;
                List<Feature> featureInfoList = new ArrayList<Feature>();
                List<String> featureInfoValues = new ArrayList<String>();
                List<String> requestUrlList = (comp.getRequestUrlList() != null) ? (List) comp.getRequestUrlList() : new ArrayList<String>();
                
                boolean FeatureLayerExist = false;
                int countFeature = comp.getFeatureCount();
                String outputFormat = (comp.getOutputFormat() != null && !comp.getOutputFormat().equals("")) ? comp.getOutputFormat() : "text/html";
                String featureCount = (comp.getFeatureCount() != 0) ? String.valueOf(comp.getFeatureCount()) : "";
                
                for (Layer queryLayer : model.getVisibleLayers()) {
                    if (queryLayer != null && queryLayer.getType() != null) {
                        switch (queryLayer.getType()) {
                            case DEFAULT:
                                break;
                            case WMS:
                                boolean skipLayer = false;
                                if (comp.getLayersNames() != null && ! ((List)comp.getLayersNames()).contains(queryLayer.getName())) {
                                    skipLayer = true;
                                }
                                if (!layersWMS.contains(queryLayer) && ! skipLayer && ! comp.isFeatureLayerOnly()) {
                                    loop++;
                                    layersWMS.add((WmsLayer) queryLayer);
                                    layersNameString += queryLayer.getName();
                                    if (loop != nbWmsLayers) {
                                        layersNameString += ",";
                                    }
                                    WmsLayer wmsLayer = (WmsLayer) queryLayer;
                                                                        
                                    String elevationValue = "";
                                    if (wmsLayer.getElevation() != null) {
                                        elevationValue = wmsLayer.getElevation().getUserValue();
                                    }
                                    String timeValue = "";
                                    if (wmsLayer.getTime() != null) {
                                        timeValue = wmsLayer.getTime().getUserValue();
                                    }                                    
                                    
                                    //building the getfeatureInfo request
                                    StringBuilder featureInfoRequest = new StringBuilder("");
                                    featureInfoRequest.append(wmsLayer.getServer().getHref()).
                                    append("?BBOX=").append(model.getBoundingBox()).
                                    append("&STYLES=").
                                    append("&FORMAT=").append(wmsLayer.getOutputFormat()).
                                    append("&INFO_FORMAT=").append("text/plain"). //force the info_format to text/plain to store in the list featureInfoValues
                                    append("&VERSION=").append(wmsLayer.getServer().getVersion()).
                                    append("&SRS=").append(model.getSrs().toUpperCase()).
                                    append("&REQUEST=GetFeatureInfo").
                                    append("&LAYERS=").append(wmsLayer.getName()).
                                    append("&QUERY_LAYERS=").append(wmsLayer.getName()).
                                    append("&WIDTH=").append(model.getWindowWidth()).
                                    append("&HEIGHT=").append(model.getWindowHeight()).
                                    append("&X=").append(X).
                                    append("&Y=").append(Y).
                                    append("&SERVICE=WMS").
                                    append("&FEATURE_COUNT=").append(featureCount);
                                    
                                    if (elevationValue != null && ! elevationValue.equals("")) {
                                        featureInfoRequest.append("&ELEVATION=").append(elevationValue);
                                    }
                                    if (timeValue != null && ! timeValue.equals("")) {
                                        featureInfoRequest.append("&TIME=").append(timeValue);
                                    }
                                    
                                    String urlRequestInfo = featureInfoRequest.toString();
                                    if (! requestUrlList.contains(urlRequestInfo)) {
                                        requestUrlList.add(urlRequestInfo);
                                    }
                                    
                                    try {
                                        String response = (String) FacesUtils.sendRequest(urlRequestInfo, null, null, null);
                                        if (response != null) {
                                            final String responseClean = response.replace("\n", " ");
                                            if ( ! featureInfoValues.contains(wmsLayer.getName()+" : "+ responseClean)) {
                                                featureInfoValues.add(wmsLayer.getName()+" : "+ responseClean);
                                            }
                                        }
                                    } catch (MalformedURLException ex) {
                                        Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                }
                                
                                break;
                            case WFS:
                                break;
                            case WCS:
                                break;
                            case SLD:
                                break;
                            case FES:
                                break;
                            case GML:
                                break;
                            case KML:
                                break;
                            case MAPCONTEXT:
                                break;
                            case FEATURE:
                                FeatureLayer temp = (FeatureLayer) queryLayer;
                                Map mapFeaturesLayer = new HashMap<String, Feature>();

                                FeatureLayerExist = true;
                                final String featureInfo_X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                                final String featureInfo_Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");
                                
                                MapContext mapContext;
                                MutableStyle mutableStyle = null;
                                //building a FeatureCollection for this layer.
                                FeatureCollection<SimpleFeatureType, SimpleFeature> features = FeatureCollections.newCollection();
                                SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

                                try {
                                    mutableStyle = FacesUtils.createStyle(temp.getImage(), temp.getSize(), temp.getRotation(), 1);
                                } catch (MalformedURLException ex) {
                                    Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                DefaultGeographicCRS layerCrs = DefaultGeographicCRS.WGS84;
                                if (temp.getFeatures() != null && temp.getFeatures().size() != 0) {

                                    Feature f = temp.getFeatures().get(0);
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
                                for (Feature f : temp.getFeatures()) {
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

                                final FeatureMapLayer mapLayer = MapBuilder.createFeatureLayer(features, mutableStyle);
                                mapLayer.setSelectable(true);
                                mapContext = MapBuilder.createContext(layerCrs);
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
                                        if (resultFeature != null && !featureInfoList.contains(resultFeature) && (countFeature == 0 || featureInfoList.size() < countFeature)) {
                                            featureInfoList.add(resultFeature);
                                        }
                                    }
                                }
                                mapFeaturesLayer.clear();
                                break;
                            default:
                                break;
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
                
                //setting the value expression for featureInfoValues if not null
                ValueExpression veVal = comp.getValueExpression("featureInfoValues");
                if (veVal != null) {
                    veVal.setValue(context.getELContext(), featureInfoValues);
                    comp.setValueExpression("featureInfoValues", veVal);
                }
                comp.setFeatureInfoValues(featureInfoValues);
                
                //setting the value expression for requestUrlList if not null.
                ValueExpression veLog = comp.getValueExpression("requestUrlList");
                if (veLog != null) {
                    veLog.setValue(context.getELContext(), requestUrlList);
                    comp.setValueExpression("requestUrlList", veLog);
                }
                comp.setRequestUrlList(requestUrlList);
                
                //allow the visibility True of the popup for the featureInfoValues list and featureInfoList
                if (popup != null) {
                    if (featureInfoValues.size() != 0 || featureInfoList.size() != 0) {
                        popup.setHidden(false);
                    } else {
                        popup.setHidden(true);
                    }
                }

                //invoke methodBinding on action and actionListener if not null.
                comp.setInvokeActions(true);
                

                final int innerWidth = popupWidth - 73;
                final int innerHeight = popupHeight - 75;

                if (popup != null && popup.isIframe() && !comp.isFeatureLayerOnly()) {

                    StringBuilder innerHtml = new StringBuilder("<div style='width:").append(innerWidth).append("px;height:").append(innerHeight).append("px;overflow-x:auto;overflow-y:auto;'>");

                    //@TODO factorization of servers wms, one request by server and the QUERY_LAYERS parameter must contains all layers name : layersNameString.
                    for (WmsLayer queryLayer : layersWMS) {
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
                                append("&FEATURE_COUNT=").append(featureCount).
                                append("'></iframe><br/>");
                    }
                    innerHtml.append("</div>");
                    popup.setInnerHTML(innerHtml.toString());
                    popup.setHidden(false);

                    //setting the value expression for dataResult if not null
                    if (ve != null && (ve.getValue(context.getELContext()) instanceof String)) {
                        ve.setValue(context.getELContext(), innerHtml);
                        comp.setValueExpression("dataResult", ve);
                    }
                    comp.setDataResult(innerHtml);

                }

            } else if (params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("getCoverage")) {

                final Layer queryLayer = model.getVisibleLayers().get(model.getVisibleLayers().size() - 1);

                if (queryLayer instanceof WmsLayer) {
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
                    String innerHTML = "<iframe style='display:none' id='popup' name='popup' src='" + ((WmsLayer) queryLayer).getServer().getHref().substring(0, ((WmsLayer) queryLayer).getServer().getHref().lastIndexOf("/")) + "/wcs" +
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
                    // innerHTML = "<iframe style='display:none' id='popup' name='popup' src='http://demo.geomatys.fr/constellation/WS/wcs?bbox=-14978036.532703482,-5209751.837462081,-10369409.907256257,-1402625.4947013294,5.0,5.0&styles=&format=matrix&version=1.0.0&crs=EPSG:3395&request=GetCoverage&coverage=AO_Coriolis_(queryLayer)&width=1259&height=176&time=2007-06-20T12:00:00Z'></iframe>";
                    if (popup != null && popup.isIframe()) {
                        popup.setInnerHTML(innerHTML);
                    }
                }

            } else {
                //init the popup innerHtml in others decode process
                if (popup != null) {
                    popup.setInnerHTML(null);
                    popup.setHidden(true);
                }
            }
            comp.setModel((AbstractModelBase) model);

        }
        return;
    }
}
