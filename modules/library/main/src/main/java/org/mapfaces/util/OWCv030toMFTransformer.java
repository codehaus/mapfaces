/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.backend.AbstractDimension;
import org.geotools.data.wms.backend.AbstractLayer;
import org.geotools.data.wms.backend.AbstractWMSCapabilities;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultDimension;
import org.mapfaces.models.DefaultLayer;
import org.mapfaces.models.DefaultServer;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.OWC_v030;
import org.mapfaces.models.Server;
import org.xml.sax.SAXException;

/**
 *
 * @author olivier
 */
public class OWCv030toMFTransformer {

    
    protected final ContextFactory contextFactory = new DefaultContextFactory();
    
    public Context visit(OWSContextType doc) throws UnsupportedEncodingException, JAXBException {
        Context ctx = contextFactory.createDefaultContext();
        ctx.setType(doc.getClass().toString());
        ctx.setVersion(doc.getVersion());        
        ctx.setId(doc.getId());
        ctx.setTitle(doc.getGeneral().getTitle());
        BoundingBoxType bbox = doc.getGeneral().getBoundingBox().getValue();
        ctx.setSrs(bbox.getCrs());
        System.out.println(bbox.toString());
        ctx.setBoundingBox(String.valueOf(bbox.getLowerCorner().get(0).doubleValue()),
                            String.valueOf(bbox.getLowerCorner().get(1).doubleValue()),
                            String.valueOf(bbox.getUpperCorner().get(0).doubleValue()),
                            String.valueOf(bbox.getUpperCorner().get(1).doubleValue()));
        ctx.setWindowSize(doc.getGeneral().getWindow().getWidth().toString(),doc.getGeneral().getWindow().getHeight().toString());
        List array= visitResourceList(doc.getResourceList().getLayer());
        ctx.setLayers((List<Layer>) array.get(0));
        ctx.setWmsServers((HashMap<String, Server>) array.get(1));
        return ctx;
    }
    
    public List visitResourceList(List<LayerType> layerList) throws UnsupportedEncodingException, JAXBException{
       List<Layer> layers = new ArrayList<Layer>();
       HashMap<String, Server> servers = new HashMap<String, Server>();
       HashMap<String, WebMapServer> webMapServers = new HashMap<String, WebMapServer>();
        int i = 0;
        for (LayerType layerType : layerList) {
            try {
                switch (layerType.getServer().get(0).getService()) {
                    case URN_OGC_SERVICE_TYPE_WMS:
                        if (webMapServers == null) {
                            webMapServers = new HashMap<String, WebMapServer>();
                        }
                        String wmsUrl = layerType.getServer().get(0).getOnlineResource().get(0).getHref();
                        if(!wmsUrl.contains("http://")){

                            //TODO
//                                if(wmsUrl.startsWith("/")){
//                                    wmsUrl =((ServletContext) FacesContext.getCurrentInstance().getExternalContext()).getContextPath()+wmsUrl;
//                                    System.out.println(wmsUrl);
//                                    System.out.println(((ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext())).getContextPath());
//                             
//                               
//                                }else{
//                                    wmsUrl =((ServletContext) FacesContext.getCurrentInstance().getExternalContext()).getContextPath()+wmsUrl;
//                                }
                        }
                        if (webMapServers.get(wmsUrl) == null) {
                            webMapServers.put(wmsUrl, new WebMapServer(new URL(wmsUrl), layerType.getServer().get(0).getVersion()));

                        }
                        Server wms = new DefaultServer();
                        wms.setHref(wmsUrl);
                        wms.setService(layerType.getServer().get(0).getService().value());
                        wms.setVersion(layerType.getServer().get(0).getVersion());
                        wms.setGTCapabilities(webMapServers.get(wmsUrl).getCapabilities());
                        if(servers.get(wmsUrl) != null)
                            servers.put(wmsUrl, wms);
                        
                        Layer layer = new DefaultLayer();
                        
                        /* Server */
                        layer.setServer(wms);

                        /* Type */
                        layer.setType(layerType.getServer().get(0).getService().value());

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
                        layer.setTitle(layerType.getTitle());

                        /*OutputFormat*/
                        for (String format : layerType.getOutputFormat()) {
                            if (format.isEmpty()) {
                                layer.setOutputFormat("image/gif");
                            } else {
                                layer.setOutputFormat(format);
                                break;
                            }
                        }

                        /*DimensionList*/
                        HashMap allDims = visitDimensionList(layerType, webMapServers);
                        if (allDims.size() > 0) {
                            layer.setDimensionList(allDims);
                        }

                        /*StyleList*/
                        HashMap<String, String> allStyles = visitStyleList(layerType.getStyleList());
                        if(allStyles.get("legendUrl") == null){
                            String url = wmsUrl;
                            if (!wmsUrl.contains("?")) {
                                url += "?";
                            }
                            url += "REQUEST=GetLegendGraphic&amp;VERSION=" +
                                    wms.getVersion() + "&amp;LAYER=" +
                                    layer.getName() + "&amp;WIDTH=50&amp;HEIGHT=30&amp;FORMAT=image/png";
                            if (allStyles.get("sldBody") != null && !allStyles.get("sldBody").equals("")) {
                                url += "&amp;SLD_BODY=" + allStyles.get("sldBody");
                            } else if (allStyles.get("sld") != null &&  !allStyles.get("sld").equals("")) {
                                url += "&amp;SLD=" + allStyles.get("sld");
                            } else if (allStyles.get("styles") != null ) {
                                url += "&amp;STYLE=" + allStyles.get("styles");
                            }
                            layer.setLegendUrl(url);
                        }else{
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
//                            if(layerType.getOpacity()!=null)
//                            wfsLayer.setOpacity(layerType.getOpacity().toString());
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
            } catch (SAXException ex) {
                Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        List<Object> result = new ArrayList(); 
        result.add(layers);
        result.add(servers);
        return result;
    }

    private Dimension visitDimension(DimensionType dimType) {
        Dimension dim = new DefaultDimension();
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

    private Dimension visitDimensionFromGetCaps(AbstractDimension dimType) {
        Dimension dim = new DefaultDimension();
        dim.setCurrent(true);
        dim.setDefault(dimType.getDefault());
        dim.setMultipleValues(true);
        dim.setName(dimType.getName());
        dim.setNearestValues(true);
        dim.setUnitSymbol(dimType.getUnitSymbol());
        dim.setUnits(dimType.getUnits());
        dim.setUserValue(dimType.getDefault());
        dim.setValue(dimType.getValue());
        return dim;
    }

    private HashMap visitDimensionList(LayerType layerType,HashMap<String, WebMapServer> webMapServers) {
        HashMap allDims = new HashMap<String, Dimension>();
        HashMap tmp;
        String wmsUrl = layerType.getServer().get(0).getOnlineResource().get(0).getHref();
        if (layerType.getDimensionList() == null) {
            //TODO find dimension into getcapabilities
            tmp = visitDimensionListFromGetCaps(layerType, webMapServers.get(wmsUrl).getJaxbCapabilities());
            if( tmp != null )
                allDims.putAll(tmp);
        }
        if (layerType.getDimensionList() != null && layerType.getDimensionList().getDimension().size() > 0) {
            for (DimensionType dim : layerType.getDimensionList().getDimension()) {
                if (dim.getUserValue() == null) {
                    if (dim.getDefault() == null) {
                        if (dim.getValue() == null) {
                            //TODO find dimension into getcapabilities
                           tmp = visitDimensionListFromGetCaps(layerType, webMapServers.get(wmsUrl).getJaxbCapabilities());
                            if( tmp != null )
                                allDims.putAll(tmp);
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

    private HashMap<String, Dimension> visitDimensionListFromGetCaps(LayerType layerType, AbstractWMSCapabilities jaxbCapabilities) {
         HashMap allDims = new HashMap<String, Dimension>();
         if (jaxbCapabilities != null) {
            AbstractLayer layer = jaxbCapabilities.getLayerFromName(layerType.getName());
            if (layer != null) {
                List<AbstractDimension> dims = layer.getAbstractDimension();
                if (dims.size() > 0) {
                    for (AbstractDimension tmp : dims) {
                        allDims.put(tmp.getName(),visitDimensionFromGetCaps(tmp));
                    }
                    return allDims;
                }
            }
        }
        return null;
    }

    private HashMap<String, String> visitStyleList(StyleListType styleListType) throws UnsupportedEncodingException, JAXBException {
         HashMap<String, String> allStyles = new HashMap<String, String>();

         if (styleListType != null) {
            for (StyleType style : styleListType.getStyle()) {
                if (style.isCurrent() != null && style.isCurrent()) {
                    if (style.getName() == null) {
                        if(style.getSLD() != null){
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
                                allStyles.put("styles","");
                            }
                        }else {
                            allStyles.put("styles","");  
                        }
                    } else {
                        allStyles.put("styles",style.getName());
                    }
                    //Create legendUrl if not specified in the context
                    if (style.getLegendURL() != null) {
                        if (allStyles.get("sldBody") != null && !allStyles.get("sldBody").equals("")) {
                            allStyles.put("legendUrl",style.getLegendURL().getOnlineResource().getHref() + "&amp;sld_body=" + allStyles.get("sldBody"));
                        } else {
                            allStyles.put("legendUrl",style.getLegendURL().getOnlineResource().getHref());
                        }
                    }
                    break;
                }
                allStyles.put("styles","");
            }
        } else {
            allStyles.put("styles","");
        }
        return allStyles;
    }

}
