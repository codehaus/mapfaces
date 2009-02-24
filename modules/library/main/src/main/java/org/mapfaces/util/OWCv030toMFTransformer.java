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

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.opengis.owc.v030.DimensionType;
import net.opengis.owc.v030.LayerType;
import net.opengis.owc.v030.OWSContextType;
import net.opengis.owc.v030.StyleListType;
import net.opengis.owc.v030.StyleType;

import org.apache.commons.lang.StringUtils;

import org.constellation.ows.v100.BoundingBoxType;

import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.backend.AbstractDimension;
import org.geotools.data.wms.backend.AbstractLayer;
import org.geotools.data.wms.backend.AbstractWMSCapabilities;

import org.geotools.geometry.GeneralEnvelope;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.mapfaces.models.Context;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;
import org.mapfaces.models.layer.WmsLayer;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;


/**
 * This class builds all needed geotools and mapfaces object from the map context xml file.
 * 
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class OWCv030toMFTransformer {

    private static final ContextFactory contextFactory = new DefaultContextFactory();
    private static final HashMap<String, WebMapServer> webMapServers = new HashMap<String, WebMapServer>();
    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.util.OWCv030toMFTransformer");

    public static Context visit(OWSContextType doc) throws UnsupportedEncodingException, JAXBException {
        
        Context ctx = contextFactory.createDefaultContext();
        ctx.setType(doc.getClass().toString());
        ctx.setVersion(doc.getVersion());
        ctx.setId(doc.getId());
        ctx.setTitle(doc.getGeneral().getTitle());
        BoundingBoxType bbox = doc.getGeneral().getBoundingBox().getValue();

        LOGGER.log(Level.INFO, "["+OWCv030toMFTransformer.class.getName()+"]  BoundingBoxType : CRS="+bbox.getCrs()+"   Lower corner="+bbox.getLowerCorner()+"   upper corner="+bbox.getUpperCorner()+"  dimension="+bbox.getDimensions());
        
        ctx.setSrs(bbox.getCrs());
        final CoordinateReferenceSystem crs;        //Crs with axis order : x,y or y,x        
        final CoordinateReferenceSystem displayCrs; //Crs with axis order : x,y
        final GeneralEnvelope env;        
        Envelope displayEnv = null;
        try {
            crs = CRS.decode(bbox.getCrs());
            displayCrs = CRS.decode(bbox.getCrs(), true);
            env = new GeneralEnvelope(crs);
            env.setRange(0, bbox.getLowerCorner().get(0), bbox.getUpperCorner().get(0));
            env.setRange(1, bbox.getLowerCorner().get(1), bbox.getUpperCorner().get(1));
            displayEnv = CRS.transform(env, displayCrs);
        } catch (TransformException ex) {
            LOGGER.log(Level.SEVERE, "Transform  envelop failed : " + bbox.getCrs(), ex);
            return null;
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, "Invalide SRS definition : " + bbox.getCrs(), ex);
            return null;
        }

        ctx.setBoundingBox(
                String.valueOf(displayEnv.getLowerCorner().getCoordinate()[0]),
                String.valueOf(displayEnv.getLowerCorner().getCoordinate()[1]),
                String.valueOf(displayEnv.getUpperCorner().getCoordinate()[0]),
                String.valueOf(displayEnv.getUpperCorner().getCoordinate()[1]));
        ctx.setWindowSize(doc.getGeneral().getWindow().getWidth().toString(), doc.getGeneral().getWindow().getHeight().toString());
        List array = visitResourceList(doc.getResourceList().getLayer());
        ctx.setLayers((List<Layer>) array.get(0));
        ctx.setWmsServers((HashMap<String, Server>) array.get(1));
        return ctx;
    }

    public static List visitResourceList(List<LayerType> layerList) throws UnsupportedEncodingException, JAXBException {
        List<Layer> layers = new ArrayList<Layer>();
        HashMap<String, Server> servers = new HashMap<String, Server>();
        
        int i = 0;
        for (final LayerType layerType : layerList) {
            try {
                switch (layerType.getServer().get(0).getService()) {
                    case URN_OGC_SERVICE_TYPE_WMS:
                        final String wmsUrl = layerType.getServer().get(0).getOnlineResource().get(0).getHref();
                        if (!wmsUrl.contains("http://")) {                            //TODO
//                                if(wmsUrl.startsWith("/")){
//                                    wmsUrl =((ServletContext) FacesContext.getCurrentInstance().getExternalContext()).getContextPath()+wmsUrl;

//
//                                }else{
//                                    wmsUrl =((ServletContext) FacesContext.getCurrentInstance().getExternalContext()).getContextPath()+wmsUrl;
//                                }
                        }
                        if (webMapServers.get(wmsUrl) == null) {
                            Thread thr = new Thread() {

                                public void run() {
                                    try {
                                        WebMapServer wmserver = new WebMapServer(new URL(wmsUrl), layerType.getServer().get(0).getVersion());
                                        webMapServers.put(wmsUrl, wmserver);
                                    } catch (IOException ex) {
                                        LOGGER.log(Level.SEVERE, null, ex);
                                    } catch (ServiceException ex) {
                                        LOGGER.log(Level.SEVERE, null, ex);
                                    }
                                }
                            };

                            thr.start();
                            long start = System.currentTimeMillis();
                            long end = 0L;
                            while (webMapServers.get(wmsUrl) == null && end < 10000) {
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    LOGGER.log(Level.SEVERE, null, ex);
                                }
                                end = System.currentTimeMillis() - start;
                                if (webMapServers.get(wmsUrl) != null) {
                                    LOGGER.log(Level.INFO,"[OWCv030toMFTransformer] webMapServer object created with geotools in : " + end+" ms");
                                }
                            }
                        }
                        Server wms = contextFactory.createDefaultServer();
                        wms.setHref(wmsUrl);
                        wms.setService(layerType.getServer().get(0).getService().value());
                        wms.setVersion(layerType.getServer().get(0).getVersion());

                        WMSCapabilities wmscapabilities = null;

                        if (webMapServers.get(wmsUrl) != null) {
                            wmscapabilities = webMapServers.get(wmsUrl).getCapabilities();
                        } else {
                           LOGGER.log(Level.SEVERE, "[OWCv030toMFTransformer] WebMapServer from this service " + wmsUrl + " is null");
                        }
                        if (wmscapabilities == null) {
                           LOGGER.log(Level.SEVERE, "[OWCv030toMFTransformer] WmsCapabilities from this service " + wmsUrl + " is null");                            
                        }
                        wms.setGTCapabilities(wmscapabilities);
                        if (servers.get(wmsUrl) != null) {
                            servers.put(wmsUrl, wms);
                        }
                        WmsLayer layer = (WmsLayer) contextFactory.createDefaultWmsLayer();

                        /* Server */
                        layer.setServer(wms);

                        /* Type */
                        switch (layerType.getServer().get(0).getService()) {
                            case URN_OGC_SERVICE_TYPE_WMS:
                                layer.setType(org.mapfaces.models.LayerType.WMS);
                                break;
                            case URN_OGC_SERVICE_TYPE_WFS:
                                layer.setType(org.mapfaces.models.LayerType.WFS);
                                break;
                            case URN_OGC_SERVICE_TYPE_GML:
                                layer.setType(org.mapfaces.models.LayerType.GML);
                                break;
                            case URN_OGC_SERVICE_TYPE_KML:
                                layer.setType(org.mapfaces.models.LayerType.KML);
                                break;
                            case URN_OGC_SERVICE_TYPE_WCS:
                                layer.setType(org.mapfaces.models.LayerType.WCS);
                                break;
                            case URN_OGC_SERVICE_TYPE_SLD:
                                layer.setType(org.mapfaces.models.LayerType.SLD);
                                break;
                            case URN_OGC_SERVICE_TYPE_FES:
                                layer.setType(org.mapfaces.models.LayerType.FES);
                                break;
                            default:
                                break;
                        }
                        /* Id */
                        if (layerType.getId() == null) {
                            layerType.setId("MapFaces_Layer_WMS_" + i);
                        }
                        layer.setId(layerType.getId());

                        /* Name */
                        layer.setName(layerType.getName());

                        /* Hidden */
                        layer.setHidden(layerType.isHidden());

                        /* Group */
                        if (layerType.getGroup() != null) {
                            layer.setGroup(layerType.getGroup());
                        /* Opacity */
                        }
                        if (layerType.getOpacity() != null) {
                            layer.setOpacity(layerType.getOpacity().toString());
                        /* Title */
                        }
                        if (layerType.getTitle() != null) {
                            layer.setTitle(layerType.getTitle());
                        } else {
                            layer.setTitle(layerType.getName());

                        /*OutputFormat*/
                        }
                        for (String format : layerType.getOutputFormat()) {
                            if (format.isEmpty()) {
                                layer.setOutputFormat("image/gif");
                            } else {
                                layer.setOutputFormat(format);
                                break;
                            }
                        }

                        /*DimensionList*/
                        HashMap allDims = visitDimensionList(layerType/*, webMapServers*/);
                        if (allDims.size() > 0) {
                            layer.setDimensionList(allDims);
                        }

                        /*StyleList*/
                        HashMap<String, String> allStyles = visitStyleList(layerType.getStyleList());
                        if (allStyles.get("legendUrl") == null) {
                            String url = wmsUrl;
                            if (!wmsUrl.contains("?")) {
                                url += "?";
                            }
                            url += "REQUEST=GetLegendGraphic&amp;VERSION=" +
                                    wms.getVersion() + "&amp;LAYER=" +
                                    layer.getName() + "&amp;WIDTH=50&amp;HEIGHT=30&amp;FORMAT=image/png";
                            if (allStyles.get("sldBody") != null && !allStyles.get("sldBody").equals("")) {
                                url += "&amp;SLD_BODY=" + allStyles.get("sldBody");
                            } else if (allStyles.get("sld") != null && !allStyles.get("sld").equals("")) {
                                url += "&amp;SLD=" + allStyles.get("sld");
                            } else if (allStyles.get("styles") != null) {
                                url += "&amp;STYLE=" + allStyles.get("styles");
                            }
                            layer.setLegendUrl(url);
                        } else {
                            layer.setLegendUrl(allStyles.get("legendUrl"));
                        }
                        layer.setStyles(allStyles.get("styles"));
                        layer.setSld(allStyles.get("sld"));
                        layer.setSldBody(allStyles.get("sldBody"));

                        if (layer == null) {
                            break;
                        }
                        layers.add(layer);
                        break;

                    case URN_OGC_SERVICE_TYPE_WFS:
//                            DataStore data;
//                            String wfsUrl = layerType.getServer().get(0).getOnlineResource().get(0).getHref();
//                            String version = layerType.getServer().get(0).getVersion();
//                            if (wfsUrl.contains("?")) {
//                                wfsUrl += "SERVICE=WFS&REQUEST=GetCapabilities&VERSION=" + version;
//                            } else {
//                                wfsUrl += "?SERVICE=WFS&REQUEST=GetCapabilities&VERSION=" + version;
//                            }
//                            if (wfsDataStores == null) {
//                                wfsDataStores = new HashMap<String, DataStore>();
//                            }
//                            if (wfsDataStores.get(wfsUrl) == null) {
//                                Map connectionParameters = new HashMap();
//                                connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", wfsUrl);
//                                if (layerType.getMaxFeatures() != null) {
//                                    connectionParameters.put("WFSDataStoreFactory:MAXFEATURES", layerType.getMaxFeatures());
//                                // Step 2 - connection
//                                }
//                                data = DataStoreFinder.getDataStore(connectionParameters);
//                                wfsDataStores.put(wfsUrl, data);
//                            } else {
//                                data = wfsDataStores.get(wfsUrl);
//                            }
//
//                            // Step 3 - discouvery
//                            String typeName = layerType.getName();
//                            SimpleFeatureType schema = data.getSchema(typeName);
//
//                            /* Style style = getWfsLayerStyle(layerType,schema);
//                            // Step 4 - target
//                            DefaultMapLayer wfsLayer = new DefaultMapLayer(data.getFeatureSource(typeName),style);
//                            if(layerType.getId()==null)
//                            layerType.setId(Utils.generateUniqueId("MapFaces_Layer_WFS_"));
//                            wfsLayer.setId(layerType.getId());
//                            if(layerType.getLayerOpacity()!=null)
//                            wfsLayer.setLayerOpacity(layerType.getLayerOpacity().toString());
//                            if (wfsLayer == null) {
//                            break;
//                            }
//                            mapLayers[i] = wfsLayer;  */
                        break;
                    case URN_OGC_SERVICE_TYPE_GML:

                        //create the parser with the gml 2.0 configuration
                      /* GML DataStore was not very efficient
                        Configuration configuration = new org.geotools.gml2.GMLConfiguration();
                        Parser parser = new org.geotools.xml.Parser( configuration );
                        //the xml instance document above
                        InputStream gml =new URL(layerType.getServer().get(0).getOnlineResource().get(0).getHref()).openStream();
                        //parse
                        FeatureCollection fc = (FeatureCollection) parser.parse( gml );
                        // Step 3 - discouvery
                        String gmlName = layerType.getName();
                        SimpleFeatureType gmlSchema = (SimpleFeatureType) fc.getSchema();
                        Style gmlStyle = getWfsLayerStyle(layerType,gmlSchema);
                        // Step 4 - target
                        DefaultMapLayer gmlLayer = new DefaultMapLayer(fc, gmlStyle);
                        // wfsLayer.setSEStyle((org.opengis.style.Style) style);
                        if (gmlLayer == null) {
                        break;
                        }
                        mapLayers[i] = gmlLayer;  */
                        break;
                    case URN_OGC_SERVICE_TYPE_KML:
                        break;
                    case URN_OGC_SERVICE_TYPE_WCS:
                        break;
                    case URN_OGC_SERVICE_TYPE_SLD:
                        break;
                    case URN_OGC_SERVICE_TYPE_FES:
                        break;
                    default:
                        break;
                }
                i++;
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        List<Object> result = new ArrayList();
        result.add(layers);
        result.add(servers);
        return result;
    }

    private static Dimension visitDimension(DimensionType dimType) {
        Dimension dim = contextFactory.createDefaultDimension();
        dim.setCurrent(dimType.isCurrent());
        dim.setDefault(dimType.getDefault());
        dim.setMultipleValues(dimType.isMultipleValues());
        dim.setName(dimType.getName());
        dim.setNearestValues(dimType.isNearestValue());
        dim.setUnitSymbol(dimType.getUnitSymbol());
        dim.setUnits(dimType.getUnits());
        dim.setUserValue(dimType.getUserValue());
        dim.setValue(dimType.getValue());
        return dim;
    }

    /**
     * This method returns a dimension mapfaces model from an AbstractDimension.
     * @param dimType the AbstractDimension object
     * @param postgisflag this is a flag for postgis layers, if true the time parameter should be a null string in the initialization.
     * @return
     */
    private static Dimension visitDimensionFromGetCaps(AbstractDimension dimType, boolean postgisflag) {
        Dimension dim = contextFactory.createDefaultDimension();
        dim.setCurrent(true);
        dim.setDefault(dimType.getDefault());
        dim.setMultipleValues(true);
        dim.setName(dimType.getName());
        dim.setNearestValues(true);
        dim.setUnitSymbol(dimType.getUnitSymbol());
        dim.setUnits(dimType.getUnits());

        if (postgisflag) {
            dim.setUserValue("");
        } else {
            dim.setUserValue(dimType.getDefault());
        }
        dim.setValue(dimType.getValue());
        return dim;
    }

    private static HashMap visitDimensionList(LayerType layerType/*, HashMap<String, WebMapServer> webMapServers*/) {
        HashMap allDims = new HashMap<String, Dimension>();
        HashMap tmp = null;
        String wmsUrl = layerType.getServer().get(0).getOnlineResource().get(0).getHref();
        if (layerType.getDimensionList() == null) {
            //TODO find dimension into getcapabilities
            if (webMapServers.get(wmsUrl) != null) {
                tmp = visitDimensionListFromGetCaps(layerType, webMapServers.get(wmsUrl).getJaxbCapabilities());
            }
            if (tmp != null) {
                allDims.putAll(tmp);
            }
        }
        if (layerType.getDimensionList() != null && layerType.getDimensionList().getDimension().size() > 0) {
            for (DimensionType dim : layerType.getDimensionList().getDimension()) {
                if (dim.getUserValue() == null) {
                    if (dim.getDefault() == null) {
                        if (dim.getValue() == null) {
                            //TODO find dimension into getcapabilities
                            tmp = visitDimensionListFromGetCaps(layerType, webMapServers.get(wmsUrl).getJaxbCapabilities());
                            if (tmp != null) {
                                allDims.putAll(tmp);
                            }
                        } else {
                            /* select the first value and add it as default and userValue value*/
                            String val = (dim.getValue().split("/")[0]).split(",")[0];
                            dim.setDefault(val);
                            dim.setUserValue(val);
                            allDims.put(dim.getName(), visitDimension(dim));
                        }
                    } else {
                        /* select default value and add it as  userValue value*/
                        dim.setUserValue(dim.getDefault());
                        allDims.put(dim.getName(), visitDimension(dim));
                    }
                } else {
                    allDims.put(dim.getName(), visitDimension(dim));
                }
            }
        }
        return allDims;
    }

    private static HashMap<String, Dimension> visitDimensionListFromGetCaps(LayerType layerType, AbstractWMSCapabilities jaxbCapabilities) {
        HashMap allDims = new HashMap<String, Dimension>();
        if (jaxbCapabilities != null) {
            AbstractLayer layer = jaxbCapabilities.getLayerFromName(layerType.getName());
            if (layer != null) {

                //From the getCapabilities we can see if the layer is a postgis type by the keyword "Vector datas".
                boolean postgisflag = false;
                if (layer != null && layer.getKeywordList() != null && FacesUtils.matchesKeywordfromList(layer.getKeywordList().getKeyword(), "Vector datas")) {
                    postgisflag = true;
                    LOGGER.log(Level.INFO,"[" + layerType.getName() + "] postgis layer detected ! ");
                }

                List<AbstractDimension> dims = layer.getAbstractDimension();
                if (dims.size() > 0) {
                    for (AbstractDimension tmp : dims) {
                        allDims.put(tmp.getName(), visitDimensionFromGetCaps(tmp, postgisflag));
                    }
                    return allDims;
                }
            }
        }
        return null;
    }

    private static HashMap<String, String> visitStyleList(StyleListType styleListType) throws UnsupportedEncodingException, JAXBException {
        HashMap<String, String> allStyles = new HashMap<String, String>();

        if (styleListType != null) {
            for (StyleType style : styleListType.getStyle()) {
                if (style.isCurrent() != null && style.isCurrent()) {
                    if (style.getName() == null) {
                        if (style.getSLD() != null) {
                            if (style.getSLD().getOnlineResource() != null) {
                                allStyles.put("sld", URLEncoder.encode(StringUtils.defaultString(style.getSLD().getOnlineResource().getHref()), "UTF-8"));
                            } else if (style.getSLD().getStyledLayerDescriptor() != null) {
                                JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");
                                Marshaller marshaller = Jcontext.createMarshaller();
                                StringWriter test = new StringWriter();
                                marshaller.marshal(style.getSLD().getStyledLayerDescriptor(), test);
                                allStyles.put("sldBody", URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<", "><"), "UTF-8"));
                            } else if (style.getSLD().getFeatureTypeStyle() != null) {
                                //TODO transformFeatureTypeStyleToString
                                JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");
                                Marshaller marshaller = Jcontext.createMarshaller();
                                StringWriter test = new StringWriter();
                                marshaller.marshal(style.getSLD().getFeatureTypeStyle(), test);
                                allStyles.put("sldBody", URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<", "><"), "UTF-8"));
                            } else {
                                allStyles.put("styles", "");
                            }
                        } else {
                            allStyles.put("styles", "");
                        }
                    } else {
                        allStyles.put("styles", style.getName());
                    }
                    //Create legendUrl if not specified in the context
                    if (style.getLegendURL() != null) {
                        if (allStyles.get("sldBody") != null && !allStyles.get("sldBody").equals("")) {
                            allStyles.put("legendUrl", style.getLegendURL().getOnlineResource().getHref() + "&amp;sld_body=" + allStyles.get("sldBody"));
                        } else {
                            allStyles.put("legendUrl", style.getLegendURL().getOnlineResource().getHref());
                        }
                    }
                    break;
                }
                allStyles.put("styles", "");
            }
        } else {
            allStyles.put("styles", "");
        }
        return allStyles;
    }
    
//    public static transformGTMapContextToMFLayer(MapContext){
//        
//    }
            
}
