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
package org.mapfaces.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.xml.bind.JAXBException;

import net.opengis.owc.v030.DimensionListType;
import net.opengis.owc.v030.DimensionType;
import net.opengis.owc.v030.GeneralType;
import net.opengis.owc.v030.LayerType;
import net.opengis.owc.v030.OWSContextType;
import net.opengis.owc.v030.ResourceListType;
import net.opengis.owc.v030.ServerType;
import net.opengis.owc.v030.StyleListType;
import net.opengis.owc.v030.StyleType;
import net.opengis.owc.v030.URLType;
import net.opengis.owc.v030.OnlineResourceType;
import net.opengis.owc.v030.SLDType;
import net.opengis.owc.v030.ServiceType;

import org.constellation.ows.v100.BoundingBoxType;

import org.mapfaces.models.Context;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;
import org.mapfaces.models.layer.WmsLayer;

public class MFtoOWCv030Transformer {

    private final net.opengis.owc.v030.ObjectFactory factory_owc_030 = new net.opengis.owc.v030.ObjectFactory();
    private final org.constellation.ows.v100.ObjectFactory factory_ows_100 = new org.constellation.ows.v100.ObjectFactory();

    public OWSContextType visit(Context ctx) throws UnsupportedEncodingException, JAXBException {
        OWSContextType doc = factory_owc_030.createOWSContextType();
        doc.setId(ctx.getId());
        doc.setVersion(ctx.getVersion());
        doc.setGeneral(visitGeneral(ctx));
        doc.setResourceList(visitResourceList(ctx.getLayers()));
//        List array = visitResourceList(doc.getResourceList().getLayer());
//        ctx.setLayers((List<Layer>) array.get(0));
        return doc;
    }

    private GeneralType visitGeneral(Context ctx) {
        GeneralType general = factory_owc_030.createGeneralType();
        general.setTitle(ctx.getTitle());

        /*BBOX*/
        BoundingBoxType bbox = factory_ows_100.createBoundingBoxType();
        bbox.setCrs(ctx.getSrs());
        List lowerCorner = new ArrayList<Double>();
        lowerCorner.add(Double.valueOf(ctx.getMinx()));
        lowerCorner.add(Double.valueOf(ctx.getMiny()));
        List upperCorner = new ArrayList<Double>();
        upperCorner.add(Double.valueOf(ctx.getMaxx()));
        upperCorner.add(Double.valueOf(ctx.getMaxy()));
        bbox.setLowerCorner(lowerCorner);
        bbox.setUpperCorner(upperCorner);
        general.setBoundingBox(factory_ows_100.createBoundingBox(bbox));

        /*WINDOW*/
        general.setWindow(factory_owc_030.createWindowType());
        general.getWindow().setWidth(new BigInteger(ctx.getWindowWidth()));
        general.getWindow().setHeight(new BigInteger(ctx.getWindowHeight()));

        /*OTHERS*/

//TODO implements All other fonctionnality of OWC
//        if(ctx.getAbstract() != null)
//            general.setAbstract(ctx.getAbstract());
//        if(ctx.getDescriptionURL() != null){
//            URLType url = factory_owc_030.createURLType();
//            url.setFormat(ctx.getDescriptionURL().getFormat());
//            url.setHeight(new BigInteger(ctx.getDescriptionURL().getWidth()));
//            url.setWidth(new BigInteger(ctx.getDescriptionURL().getHeight()));
//            OnlineResourceType onLineResource = factory_owc_030.createOnlineResourceType();
//            onLineResource.setHref(ctx.getDescriptionURL().getHref());
//            url.setOnlineResource(onLineResource);
//            general.setDescriptionURL(url);
//        }
//        if(ctx.getLogoURL() != null){
//             URLType logoUrl = factory_owc_030.createURLType();
//             logoUrl.setFormat(ctx.getDescriptionURL().getFormat());
//             logoUrl.setHeight(new BigInteger(ctx.getDescriptionURL().getWidth()));
//             logoUrl.setWidth(new BigInteger(ctx.getDescriptionURL().getHeight()));
//             OnlineResourceType logoHref = factory_owc_030.createOnlineResourceType();
//             logoHref.setHref(ctx.getLogoURL().getHref());
//             logoUrl.setOnlineResource(logoHref);
//             general.setLogoURL(logoUrl);
//        }
//        if(ctx.getMaxScaleDenominator()!=null)
//            general.setMaxScaleDenominator(new Double(ctx.getMaxScaleDenominator()));
//        if(ctx.getMinScaleDenominator()!=null)
//            general.setMinScaleDenominator(new Double(ctx.getMinScaleDenominator()));

//         general.setExtension(value);
//         general.setKeywords(value);
//         general.setServiceProvider(value);

        return general;
    }

    private ResourceListType visitResourceList(List<Layer> layers) throws UnsupportedEncodingException {
        ResourceListType list = factory_owc_030.createResourceListType();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            LayerType layerType = factory_owc_030.createLayerType();
            //layerType.getAvailableCRS().add(e);
            //layerType.setDataURL(value);
            //layerType.setDepth(value);
            if (layer.getDimensionList() != null) {
                layerType.setDimensionList(visitDimensionList(layer.getDimensionList()));
            //layerType.setExtension(value);
            }
            if (layer.getGroup() != null) {
                layerType.setGroup(layer.getGroup());
            }
            layerType.setHidden(layer.isHidden());
            layerType.setId(layer.getId());
            //layerType.setInlineGeometry(value);
            if (layer.getMaxFeatures() != null) {
                layerType.setMaxFeatures(new BigInteger(layer.getMaxFeatures()));
            }
            if (layer.getMaxScaleDenominator() != null) {
                layerType.setMaxScaleDenominator(new Double(layer.getMaxScaleDenominator()));
//            if(layer.getMetadataURL() != null){
//                URLType url = factory_owc_030.createURLType();
//                url.setFormat(layer.getMetadataURL().getFormat());
//                url.setHeight(new BigInteger(layer.getMetadataURL().getWidth()));
//                url.setWidth(new BigInteger(layer.getMetadataURL().getHeight()));
//                OnlineResourceType onLineResource = factory_owc_030.createOnlineResourceType();
//                onLineResource.setHref(layer.getMetadataURL().getHref());
//                url.setOnlineResource(onLineResource);
//                layerType.setMetadataURL(url);
//            }
            }
            if (layer.getMinScaleDenominator() != null) {
                layerType.setMinScaleDenominator(new Double(layer.getMinScaleDenominator()));
            }
            layerType.setName(layer.getName());
            layerType.setOpacity(new BigDecimal(layer.getOpacity()));
//            if(layer.getParameterList() != null)
//                layerType.setParameterList(layer.getParameterList());
            layerType.setQueryable(new Boolean(layer.isQueryable()));
            if (layer.getResponseCRS() != null) {
                layerType.setResponseCRS(layer.getResponseCRS());
            }
            if (layer.getResX() != null) {
                layerType.setResx(layer.getResX());
            }
            if (layer.getResY() != null) {
                layerType.setResy(layer.getResY());
            }
            if (layer.getResZ() != null) {
                layerType.setResz(layer.getResZ());
            }
            if (layer.getOutputFormat() != null) {
                layerType.getOutputFormat().add(layer.getOutputFormat());
            }
            if (layerType.getServer() != null) {
                switch (layerType.getServer().get(0).getService()) {
                    case URN_OGC_SERVICE_TYPE_WMS:
                        WmsLayer wms = (WmsLayer) layer;
                        if (wms.getServer() != null) {
                            layerType.getServer().add(visitServer(wms.getServer()));
                        }
                        if (wms.getStyles() != null || wms.getSld() != null || wms.getSldBody() != null) {
                            layerType.setStyleList(visitStyles(wms));
                        }
                        break;
                    case URN_OGC_SERVICE_TYPE_FES:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");
                    case URN_OGC_SERVICE_TYPE_GML:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");
                    case URN_OGC_SERVICE_TYPE_KML:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");
                    case URN_OGC_SERVICE_TYPE_SLD:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");
                    case URN_OGC_SERVICE_TYPE_WFS:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");
                    case URN_OGC_SERVICE_TYPE_WCS:
                        throw new UnsupportedEncodingException("This type of layer is unsupported, only URN_OGC_SERVICE_TYPE_WMS is supported");

                }

            }

            list.getLayer().add(layerType);
        }
        return list;

    }

    private DimensionListType visitDimensionList(HashMap<String, Dimension> dimensionList) {
        DimensionListType dimList = factory_owc_030.createDimensionListType();
        for (Entry dimEntry : dimensionList.entrySet()) {
            Dimension dim = (Dimension) dimEntry.getValue();
            DimensionType dimType = factory_owc_030.createDimensionType();
            dimType.setCurrent(dim.isCurrent());
            dimType.setDefault(dim.getDefault());
            dimType.setMultipleValues(dim.isMultipleValues());
            dimType.setName(dim.getName());
            dimType.setNearestValue(dim.isNearestValues());
            dimType.setUnitSymbol(dim.getUnitSymbol());
            dimType.setUnits(dim.getUnits());
            dimType.setUserValue(dim.getUserValue());
            dimType.setValue(dim.getValue());
            dimList.getDimension().add(dimType);
        }
        return dimList;
    }

    private ServerType visitServer(Server server) {
        ServerType serverType = factory_owc_030.createServerType();
        serverType.setService(ServiceType.fromValue(server.getService()));
//        if(server.getTitle() != null)
//            serverType.setTitle(server.getTitle());
        serverType.setVersion(server.getVersion());
        serverType.setDefault(true);
        OnlineResourceType onLineResource = factory_owc_030.createOnlineResourceType();
        onLineResource.setHref(server.getHref());
        serverType.getOnlineResource().add(onLineResource);
        return serverType;
    }

    private StyleType visitSld(WmsLayer layer) {
        StyleType styleType = factory_owc_030.createStyleType();
//        styleType.getAbstract(layer.getName());
        styleType.setCurrent(true);
        styleType.setLegendURL(visitLegendUrl(layer.getLegendUrl()));
        SLDType sldType = factory_owc_030.createSLDType();
        OnlineResourceType onLineResource = factory_owc_030.createOnlineResourceType();
        onLineResource.setHref(layer.getSld());
        sldType.setOnlineResource(onLineResource);
        styleType.setSLD(sldType);
        return styleType;
    }

    private StyleType visitSldBody(WmsLayer layer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private URLType visitLegendUrl(String legendUrl) {
        URLType url = factory_owc_030.createURLType();
        OnlineResourceType onLineResource = factory_owc_030.createOnlineResourceType();
        onLineResource.setHref(legendUrl);
        url.setOnlineResource(onLineResource);
        return url;
    }

    private StyleType visitStyleName(WmsLayer layer) {
        StyleType styleType = factory_owc_030.createStyleType();
//        styleType.getAbstract(layer.getName());
        styleType.setCurrent(true);
        styleType.setLegendURL(visitLegendUrl(layer.getLegendUrl()));
        styleType.setName(layer.getStyles());
        styleType.setTitle(layer.getStyles());
        return styleType;
    }

    private StyleListType visitStyles(WmsLayer layer) {
        StyleListType styleList = factory_owc_030.createStyleListType();
        if (layer.getStyles() != null) {
            styleList.getStyle().add(visitStyleName(layer));
        } else if (layer.getSld() != null) {
            styleList.getStyle().add(visitSld(layer));
        } else if (layer.getSldBody() != null) {
            styleList.getStyle().add(visitSldBody(layer));
        }
        return styleList;
    }
}
