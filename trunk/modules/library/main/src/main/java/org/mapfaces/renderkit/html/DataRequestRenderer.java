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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.ajax4jsf.ajax.html.HtmlAjaxRegion;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.geotoolkit.display.exception.PortrayalException;
import org.geotoolkit.display2d.service.DefaultPortrayalService;
import org.geotoolkit.feature.collection.FeatureCollection;
import org.geotoolkit.feature.FeatureCollectionUtilities;
import org.geotoolkit.feature.simple.DefaultSimpleFeature;
import org.geotoolkit.feature.simple.SimpleFeatureTypeBuilder;
import org.geotoolkit.filter.identity.DefaultFeatureId;
import org.geotoolkit.map.FeatureMapLayer;
import org.geotoolkit.map.MapBuilder;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.geotoolkit.style.MutableStyle;
import org.mapfaces.component.UIDataRequest;
import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.WmsLayer;
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

    private static final Logger LOGGER = Logger.getLogger(DataRequestRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIDataRequest comp = (UIDataRequest) component;
        final String clientId = comp.getClientId(context);
        final ResponseWriter responseWriter = context.getResponseWriter();
        responseWriter.startElement("div", comp);
        responseWriter.writeAttribute("id", clientId, "id");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final UIDataRequest comp = (UIDataRequest) component;
        final String clientId = comp.getClientId(context);
        ResponseWriter responseWriter = context.getResponseWriter();
        if (responseWriter == null) {
            responseWriter = FacesUtils.getResponseWriter2(context);
        }

        final String rerender = comp.getReRender();
        if (rerender != null && !rerender.equals("")) {


            HtmlAjaxSupport a4jSupport = null;
            final String a4jsupportFacetKey = "a4jsupport";
            if (comp.getFacets().containsKey(a4jsupportFacetKey)) {
                a4jSupport = (HtmlAjaxSupport) comp.getFacets().get(a4jsupportFacetKey);
            } else {
                a4jSupport = FacesUtils.createBasicAjaxSupport(context, comp, "", rerender);
                comp.getFacets().put(a4jsupportFacetKey, a4jSupport);
            }
            String formId = FacesUtils.getFormId(context, component);
            if (formId == null && clientId.contains(":")) {
                formId = clientId.substring(0, clientId.indexOf(':'));
            }
            if (comp.isInvokeActions()) {

                String targetregion = formId;
                final String clientIdAjaxRegion = FacesUtils.findClientIdComponentClass(context, context.getViewRoot(), HtmlAjaxRegion.class);
                if (clientIdAjaxRegion != null) {
                    targetregion = clientIdAjaxRegion;
                }
                responseWriter.write("<script type='text/javascript'>" +
                        "A4J.AJAX.Submit('" + targetregion + "','" + formId + "',null,{'parameters':{'" + formId + ":" + a4jSupport.getId() + "':'" + formId + ":" + a4jSupport.getId() + "'} ,'actionUrl':window.location.href} );" +
                        "</script>");
            }
        }

        responseWriter.endElement("div");
        responseWriter.flush();
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

        final UIPopup popup = (UIPopup) FacesUtils.findComponentById(context, context.getViewRoot(), comp.getTargetPopupId());
        final int popupWidth;
        final int popupHeight;

        if (popup != null) {
            popupWidth = popup.getWidth();
            popupHeight = popup.getHeight();
        } else {
            popupWidth = 300;
            popupHeight = 200;
        }

        final Map params = context.getExternalContext().getRequestParameterMap();
        if (params != null) {
            final Context model = (Context) comp.getModel();
            String x = "170";
            String y = "160";

            //@TODO these keys should be in static utility class but where?
            final String mfActionKey = "org.mapfaces.ajax.ACTION";
            final String mfGfiXKey = "org.mapfaces.ajax.ACTION_GETFEATUREINFO_X";
            final String mfGfiYKey = "org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y";
            final String mfGfiLatKey = "org.mapfaces.ajax.ACTION_GETFEATUREINFO_LAT";
            final String mfGfiLonKey = "org.mapfaces.ajax.ACTION_GETFEATUREINFO_LON";

            if (params.get(mfActionKey) != null && ((String) params.get(mfActionKey)).equals("getFeatureInfo") //                    && params.get("refresh") != null && params.get("refresh").equals(comp.getClientId(context)))
                    ) {
                if (params.get(mfGfiYKey) != null) {
                    y = (String) params.get(mfGfiYKey);

                    if (popup != null) {
                        final int realTop = (new Integer(y)) - popup.getHeight();
                        popup.setTop("top:" + realTop + "px;");
                    }
                }
                if (params.get(mfGfiXKey) != null) {
                    x = (String) params.get(mfGfiXKey);
                    if (popup != null) {
                        final int realLeft = (new Integer(x)) - (popup.getWidth() / 2);
                        popup.setLeft("left:" + realLeft + "px;");
                    }
                }

                //setting to the component the real latitude and logitude by calculating from pixels.
                //setting the values lat and lon expressions if not null
                if (params.get(mfGfiLatKey) != null) {
                    final double lat = Double.parseDouble((String) params.get(mfGfiLatKey));
                    final ValueExpression veLat = comp.getValueExpression("outputLatitude");
                    if (veLat != null) {
                        veLat.setValue(context.getELContext(), lat);
                        comp.setValueExpression("outputLatitude", veLat);
                    }
                    comp.setOutputLatitude(lat);
                }
                if (params.get(mfGfiLonKey) != null) {
                    final double lon = Double.parseDouble((String) params.get(mfGfiLonKey));
                    final ValueExpression veLon = comp.getValueExpression("outputLongitude");
                    if (veLon != null) {
                        veLon.setValue(context.getELContext(), lon);
                        comp.setValueExpression("outputLongitude", veLon);
                    }
                    comp.setOutputLongitude(lon);
                }

                //QUERY_LAYERS attribute for each server
                final HashMap<String, List<WmsLayer>> wmsServers = new HashMap<String, List<WmsLayer>>();
                final List<Feature> featureInfoList = new ArrayList<Feature>();
                final List<String> featureInfoValues = new ArrayList<String>();
                //List of request url to send. Result of this request will be display in UIPopup
                final List<String> requestUrlList = new ArrayList<String>();
                final int countFeature = comp.getFeatureCount();
                final String outputFormat = (comp.getOutputFormat() != null && !comp.getOutputFormat().equals("")) ? comp.getOutputFormat() : "text/html";
                final String featureCount = (comp.getFeatureCount() != 0) ? String.valueOf(comp.getFeatureCount()) : "1000";

                final List<Layer> queryableAndVisibleLayers = model.getQueryableAndVisibleLayers();
                //System.out.println("nombre de lauers visible et queryable : " + queryableAndVisibleLayers.size());
                for (Layer queryLayer : queryableAndVisibleLayers) {
                    if (queryLayer != null && queryLayer.getType() != null) {
                        switch (queryLayer.getType()) {
                            case DEFAULT:
                                break;
                            case WMS:

                                boolean skipLayer = false;
                                //If user has specified a list of wms layer to request
                                if (comp.getLayersNames() != null && !((List) comp.getLayersNames()).contains(queryLayer.getName())) {
                                    skipLayer = true;
                                }

                                if (!comp.isFeatureLayerOnly() && !skipLayer) {
                                    //Range Layer by Server
                                    final WmsLayer wmsLayer = (WmsLayer) queryLayer;
                                    if (wmsServers.containsKey(wmsLayer.getServer().getHref())) {
                                        wmsServers.get(wmsLayer.getServer().getHref()).add(wmsLayer);
                                    } else {
                                        final List<WmsLayer> list = new ArrayList<WmsLayer>();
                                        list.add(wmsLayer);
                                        wmsServers.put(wmsLayer.getServer().getHref(), list);
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
                                final FeatureLayer temp = (FeatureLayer) queryLayer;
                                final Map mapFeaturesLayer = new HashMap<String, Feature>();
                                
                                final String featureInfoX = (String) params.get(mfGfiXKey);
                                final String featureInfoY = (String) params.get(mfGfiYKey);

                                MapContext mapContext;
                                MutableStyle mutableStyle = null;
                                //building a FeatureCollection for this layer.
                                final FeatureCollection<SimpleFeatureType, SimpleFeature> features = FeatureCollectionUtilities.createCollection();
                                final SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

                                try {
                                    mutableStyle = FacesUtils.createStyle(temp.getImage(), temp.getSize(), temp.getRotation(), 1);
                                } catch (MalformedURLException ex) {
                                    LOGGER.log(Level.SEVERE, null, ex);
                                }

                                DefaultGeographicCRS layerCrs = DefaultGeographicCRS.WGS84;
                                if (temp.getFeatures() != null && temp.getFeatures().size() != 0) {

                                    final Feature f = temp.getFeatures().get(0);
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

                                    final List<Object> objects = new ArrayList<Object>();
                                    for (String key : f.getAttributes().keySet()) {
                                        objects.add(f.getAttributes().get(key));
                                    }

                                    final SimpleFeature sf = new DefaultSimpleFeature(objects, sft, new DefaultFeatureId(f.getId()));
                                    features.add(sf);
                                }

                                final FeatureMapLayer mapLayer = MapBuilder.createFeatureLayer(features, mutableStyle);
                                mapLayer.setSelectable(true);
                                mapContext = MapBuilder.createContext(layerCrs);
                                mapContext.layers().add(mapLayer);
                                final Rectangle rect = new Rectangle(Integer.parseInt(featureInfoX), Integer.parseInt(featureInfoY), 1, 1);
                                final FeatureVisitor featureVisitor = new FeatureVisitor();
                                try {
                                    DefaultPortrayalService.visit(mapContext, model.getEnvelope(), model.getDimension(), true, null, rect, featureVisitor);
                                } catch (PortrayalException ex) {
                                    LOGGER.log(Level.SEVERE, null, ex);
                                }

                                //Adding the resulting feature into the final list of features for dataResult ValueExpression value.
                                for (org.opengis.feature.Feature f : featureVisitor.getFeatureList()) {
                                    if (f instanceof org.opengis.feature.simple.SimpleFeature) {
                                        final org.opengis.feature.simple.SimpleFeature ff = (org.opengis.feature.simple.SimpleFeature) f;

                                        final Feature resultFeature = (Feature) mapFeaturesLayer.get(ff.getID());
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
                //Probably use only for getfeatureinfo
                if (wmsServers.size() > 0) {
                    //building the getfeatureInfo request for each wms server
                    final Iterator<String> it = wmsServers.keySet().iterator();
                    while (it.hasNext()) {
                        final String href = it.next();
                        final StringBuilder featureInfoRequest = new StringBuilder();
                        featureInfoRequest.append(href);
                        featureInfoRequest.append((featureInfoRequest.toString().contains("?") ? "&" : "?")).
                                append("BBOX=").append(model.getBoundingBox()).
                                append("&STYLES=").
                                append("&FORMAT=").append("image/gif").
                                append("&INFO_FORMAT=").append(outputFormat). //info_format is specified on the tag of the component default to text/plain, other cass hasn't be tested
                                append("&VERSION=1.1.1").
                                append("&SRS=").append(model.getSrs().toUpperCase(Locale.getDefault())).
                                append("&REQUEST=GetFeatureInfo").
                                append("&WIDTH=").append(model.getWindowWidth()).
                                append("&HEIGHT=").append(model.getWindowHeight()).
                                append("&X=").append(x).
                                append("&Y=").append(y).
                                append("&SERVICE=WMS").
                                append("&FEATURE_COUNT=").append(featureCount);
                        final List<WmsLayer> list = wmsServers.get(href);
                        final boolean requestHasElevationParam = false;
                        final boolean requestHasTimeParam = false;
                        String layers = "";
                        for (WmsLayer wmsLayer : list) {

                            //All Multidimensionnal Layers of a Context have the same Elevation and Time value
                            String elevationValue = "";
                            if (wmsLayer.getElevation() != null) {
                                elevationValue = wmsLayer.getElevation().getUserValue();
                                if (elevationValue != null && !elevationValue.equals("") && !requestHasElevationParam) {
                                    featureInfoRequest.append("&ELEVATION=").append(elevationValue);
                                }
                            }
                            String timeValue = "";
                            if (wmsLayer.getTime() != null) {
                                timeValue = wmsLayer.getTime().getUserValue();
                                if (timeValue != null && !timeValue.equals("") && !requestHasTimeParam) {
                                    featureInfoRequest.append("&TIME=").append(timeValue);
                                }
                            }
                            layers += wmsLayer.getName() + ",";
                        }
                        String escapedString = null;
                        escapedString = layers.substring(0, layers.length() - 1);
                        featureInfoRequest.append("&LAYERS=").append(escapedString).append("&QUERY_LAYERS=").append(escapedString);
                        requestUrlList.add(featureInfoRequest.toString());

                    }
                }

                final Map<String, String> wmsFeatureInfoValues = new HashMap<String,String>();
                final List<Thread> runList = new ArrayList<Thread>();
                
                //If the popup isn't in Iframe mode or if popup is null, load results of all getfeatureInfo requests in featureInfoValues List
                if (popup == null || !popup.isIframe()) {
                    for (String request : requestUrlList) {
                       if (debug)
                           Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.INFO, "GetfeatureInfo url requested : "+request);
                        final FeatureInfoThread fiThread = new FeatureInfoThread(wmsFeatureInfoValues, request);
                        fiThread.start();
                        runList.add(fiThread);

                    }
                }

                //Joining the threads for wms requests.
                for (Thread th : runList) {
                    try {
                        th.join(20000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //setting the featureInfoValues list
                for (String urlKey : wmsFeatureInfoValues.keySet()) {
                    featureInfoValues.add(wmsFeatureInfoValues.get(urlKey));
                }

                //setting the value expression for dataResult if not null
                final String veDataResultKey = "dataResult";
                final ValueExpression ve = comp.getValueExpression(veDataResultKey);
                if (ve != null) {
                    ve.setValue(context.getELContext(), featureInfoList);
                    comp.setValueExpression(veDataResultKey, ve);
                }
                comp.setDataResult(featureInfoList);

                //setting the value expression for featureInfoValues if not null
                final ValueExpression veVal = comp.getValueExpression("featureInfoValues");
                if (veVal != null) {
                    veVal.setValue(context.getELContext(), featureInfoValues);
                    comp.setValueExpression("featureInfoValues", veVal);
                }
                comp.setFeatureInfoValues(featureInfoValues);

                //setting the value expression for requestUrlList if not null.
                final ValueExpression veLog = comp.getValueExpression("requestUrlList");
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
                if (comp.getActionExpression() != null) {
                    comp.getActionExpression().invoke(context.getELContext(), null);
                }
                for (ActionListener al : comp.getActionListeners()) {
                    al.processAction(new ActionEvent(component));
                }
                component.queueEvent(new ActionEvent(comp));
                comp.setInvokeActions(true);


                final int innerWidth = popupWidth - 73;
                final int innerHeight = popupHeight - 75;

                if (popup != null && !comp.isFeatureLayerOnly()) {

                    final StringBuilder innerHtml = new StringBuilder("<div style='width:").append(innerWidth).
                            append("px;height:").append(innerHeight).
                            append("px;overflow" + (popup.isIframe() ? "hidden" : "auto") + "font-size:11px;font-family:Arial;'>");

                    if (popup.isIframe()) {
                        for (String request : requestUrlList) {
                            innerHtml.append("<iframe style='width:").append(innerWidth).
                                    append("px;height:").
                                    append(innerHeight).
                                    append("px;font-size:0.7em;font-family:verdana;border:none;overflow:auto;z-index:150;' id='popup' name='popup' src='").
                                    append(request).append("'></iframe><br/>");
                        }
                    } else {
                        for (String result : featureInfoValues) {
                            if (result != null) {
                                innerHtml.append("<pre>");
                                innerHtml.append(result);
                                innerHtml.append("</pre>");
                            } else {
                                innerHtml.append("<div>No data found</div>");
                            }
                        }
                    }
                    innerHtml.append("</div>");
                    popup.setInnerHTML(innerHtml.toString());
                    popup.setHidden(false);

                    //setting the value expression for dataResult if not null
                    if (ve != null && (ve.getValue(context.getELContext()) instanceof String)) {
                        ve.setValue(context.getELContext(), innerHtml);
                        comp.setValueExpression(veDataResultKey, ve);
                    }
                    comp.setDataResult(innerHtml);

                }

            } else if (params.get(mfActionKey) != null && ((String) params.get(mfActionKey)).equals("getCoverage")) {

                final Layer queryLayer = model.getVisibleLayers().get(model.getVisibleLayers().size() - 1);

                if (queryLayer instanceof WmsLayer && popup != null && popup.isIframe()) {
                    final StringBuilder innerHTML = new StringBuilder();
                    final String serverHref = ((WmsLayer) queryLayer).getServer().getHref();

                    String[] windowPixel = null;
                    if ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL") != null) {
                        windowPixel = ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL")).split(",");
                    }

                    innerHTML.append("<iframe style='display:none' id='popup' name='popup' src='").
                            append(serverHref.substring(0, serverHref.lastIndexOf('/'))).
                            append("/wcs?BBOX=").
                            append((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_AOI"));

                    if (queryLayer.getElevation() != null) {
                        final String elevation = queryLayer.getElevation().getUserValue() + "," + queryLayer.getElevation().getUserValue();
                        innerHTML.append(',').append(elevation);
                    }
                    innerHTML.append("&STYLES=").append("&FORMAT=").
                            append(
                            (params.containsKey("org.mapfaces.ajax.ACTION_GETCOVERAGE_FORMAT") ?
                                (String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_FORMAT") : "matrix")).
                            append("&VERSION=1.0.0").
                            append("&CRS=").append(model.getSrs()).
                            append("&REQUEST=GetCoverage'").
                            append("&COVERAGE=").append(queryLayer.getName()).
                            append("&WIDTH=").append(windowPixel[0]).
                            append("&HEIGHT=").append(windowPixel[1]);

                    if (queryLayer.getTime() != null) {
                        innerHTML.append("&TIME=" + queryLayer.getTime().getUserValue());
                    }

                    innerHTML.append("'></iframe>");
                    // innerHTML = "<iframe style='display:none' id='popup' name='popup' src='http://demo.geomatys.fr/constellation/WS/wcs?bbox=-14978036.532703482,-5209751.837462081,-10369409.907256257,-1402625.4947013294,5.0,5.0&styles=&format=matrix&version=1.0.0&crs=EPSG:3395&request=GetCoverage&coverage=AO_Coriolis_(queryLayer)&width=1259&height=176&time=2007-06-20T12:00:00Z'></iframe>";
                    popup.setInnerHTML(innerHTML.toString());
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
    }


    private static class FeatureInfoThread extends Thread {

        private final Map<String, String> map;
        private final String url;

        public FeatureInfoThread(Map<String, String> map, String url) {
            this.map = map;
            this.url = url;
        }

        @Override
        public void run() {
            final long d1 = System.currentTimeMillis();
            try {
                final String response = (String) FacesUtils.sendRequest(url, null, null, null);
                if (response != null) {
                    map.put(url, response);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            final long d2 = System.currentTimeMillis();
            final long diff = d2 - d1;
            Logger.getLogger(DataRequestRenderer.class.getName()).log(Level.INFO, "Finished getfeatureInfo for layer(s) "+FacesUtils.getParameterValue("LAYERS", url)+"  in "+diff+" ms.");

        }
    }
}
