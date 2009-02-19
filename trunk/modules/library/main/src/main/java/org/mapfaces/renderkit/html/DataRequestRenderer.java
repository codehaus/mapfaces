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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.UIDataRequest;
import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.FacesUtils;

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
            System.out.println("%%%%%%%%%%%%%%%   popup child of DataRequest popupWidth = " + popupWidth + "   popupHeight = " + popupHeight);
        }


        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Context model = (Context) comp.getModel();

            final List<Layer> layersWMS = new ArrayList<Layer>();
            String layersNameString = "";
            int nbWmsLayers = Utils.getWMSLayerscount(model.getVisibleLayers());
            int loop = 0;
            for (Layer queryLayer : model.getVisibleLayers()) {
                if (queryLayer.getType().equals("mapfaces_type")) {
                    //@TODO do something to generate an Object List of Result from the attached features
                    System.out.println(">>>>>>>>>>>   MFLayer detected for GetFeatureInfo, skiping theme because it is a special layer for Mapfaces.");
                    continue;
                }else {
                    loop++;
                    if (! layersWMS.contains(queryLayer)) {
                        layersWMS.add(queryLayer);
                        layersNameString += queryLayer.getName();
                        if (loop != nbWmsLayers) {
                            layersNameString += ",";
                        }
                    }
                }
            }


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

                final int innerWidth = popupWidth - 73;
                final int innerHeight = popupHeight - 75;

                if (popup != null && popup.isIframe()) {
                    
                    StringBuilder innerHtml = new StringBuilder("<div style='width:").append(innerWidth).append("px;height:").append(innerHeight).append("px;overflow-x:auto;overflow-y:auto;'>");
                    //@TODO factorization of serves wms, one request by server and QUERY_LAYERS must contains all layers name
                    for (Layer queryLayer : layersWMS) {
                        innerHtml.append("<iframe style='width:").append(innerWidth).append("px;height:").append(innerHeight)
                            .append("px;font-size:0.7em;font-family:verdana;border:none;overflow:hidden;z-index:150;' id='popup' name='popup' src='")
                            .append(queryLayer.getServer().getHref())
                            .append("?BBOX=").append(model.getBoundingBox())
                            .append("&STYLES=")
                            .append("&FORMAT=").append(queryLayer.getOutputFormat())
                            .append("&INFO_FORMAT=text/html")
                            .append("&VERSION=").append(queryLayer.getServer().getVersion())
                            .append("&SRS=").append(model.getSrs().toUpperCase())
                            .append("&REQUEST=GetFeatureInfo")
                            .append("&LAYERS=").append(queryLayer.getName())
                            .append("&QUERY_LAYERS=").append(queryLayer.getName())
                            .append("&WIDTH=").append(model.getWindowWidth())
                            .append("&HEIGHT=").append(model.getWindowHeight())
                            .append("&X=").append(X)
                            .append("&Y=").append(Y)
                            .append("'></iframe>");
                    }
                    innerHtml.append("</div>");
                    popup.setInnerHTML(innerHtml.toString());
                    
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
}
