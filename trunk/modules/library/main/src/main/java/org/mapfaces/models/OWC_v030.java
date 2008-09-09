/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import net.opengis.owc.v030.LayerType;
import net.opengis.owc.v030.OWSContextType;
import org.geotools.map.MapLayer;
import org.geotools.map.WMSMapLayer;
import org.geotools.referencing.CRS;
import com.vividsolutions.jts.geom.Envelope;
import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import net.opengis.owc.v030.DimensionListType;
import net.opengis.owc.v030.DimensionType;
import net.opengis.owc.v030.StyleType;
import org.geotools.data.wms.backend.AbstractDimension;
import org.geotools.data.wms.backend.AbstractLayer;
import org.geotools.data.wms.backend.AbstractWMSCapabilities;
import org.apache.commons.lang.StringUtils;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.wms.WebMapServer;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.feature.simple.SimpleFeatureType;

import org.xml.sax.SAXException;

public class OWC_v030 extends AbstractOWC {
    
    private transient OWSContextType doc;
    
    
    /*Context server used*/
    private HashMap<String,WebMapServer> wmsServers;
    
    
     /*Context server used*/
    private HashMap<String,DataStore> wfsDataStores;
    

    public OWC_v030(Object  JAXBValue) {
        this.doc =(OWSContextType) JAXBValue;
    }
    
    @Override
    public String getId() {
        return getDoc().getId();
    }
    
    @Override
    public String getVersion() {
        return getDoc().getVersion();
    }
    @Override
    public String getTitle() {
        return getDoc().getGeneral().getTitle();
    }

    @Override
    public void setTitle(String title) {
        getDoc().getGeneral().setTitle(title);
    }
    @Override
    public List<String> getKeywordList() {
        return getDoc().getGeneral().getKeywords().getKeyword();
    }

    @Override
    public String getAbstracts() {
        return getDoc().getGeneral().getAbstract();
    }

    @Override
    public String getLogoUrl() {
        return getDoc().getGeneral().getLogoURL().getOnlineResource().getHref();
    }
    
    @Override
    public String getDescriptionUrl() {
        return getDoc().getGeneral().getDescriptionURL().getOnlineResource().getHref();
    }
    
    
    @Override
    public BigInteger getWindowWidth() {
        return getDoc().getGeneral().getWindow().getWidth();
    }

    @Override
    public void setWindowWidth(BigInteger windowWidth) {
        getDoc().getGeneral().getWindow().setWidth(windowWidth);
    }

    @Override
    public BigInteger getWindowHeight() {
        return getDoc().getGeneral().getWindow().getHeight();
    }

    @Override
    public void setWindowHeight(BigInteger windowHeight) {
        getDoc().getGeneral().getWindow().setHeight(windowHeight);
    }

    @Override
    public Double getMinx() {
        return getDoc().getGeneral().getBoundingBox().getValue().getLowerCorner().get(0);
    }

    @Override
    public void setMinx(Double minx) {
        List<Double> lowerCorner  = new ArrayList<Double>(2);
        lowerCorner.add(minx);
        lowerCorner.add(getMiny());
        getDoc().getGeneral().getBoundingBox().getValue().setLowerCorner(lowerCorner);
    }

    @Override
    public Double getMiny() {
        return  getDoc().getGeneral().getBoundingBox().getValue().getLowerCorner().get(1);
    }

    @Override
    public void setMiny(Double miny) {
        List<Double> lowerCorner  = new ArrayList<Double>(2);
        lowerCorner.add(getMinx());        
        lowerCorner.add(miny);
        getDoc().getGeneral().getBoundingBox().getValue().setLowerCorner(lowerCorner);
    }

    @Override
    public Double getMaxx() {
        return getDoc().getGeneral().getBoundingBox().getValue().getUpperCorner().get(0);
    }

    @Override
    public void setMaxx(Double maxx) {
        List<Double> upperCorner  = new ArrayList<Double>(2);
        upperCorner.add(maxx);        
        upperCorner.add(getMaxy());
        getDoc().getGeneral().getBoundingBox().getValue().setUpperCorner(upperCorner);
    }

    @Override
    public Double getMaxy() {
        return getDoc().getGeneral().getBoundingBox().getValue().getUpperCorner().get(1);
    }

    @Override
    public void setMaxy(Double maxy) {
        List<Double> upperCorner  = new ArrayList<Double>(2);
        upperCorner.add(getMaxx());        
        upperCorner.add(maxy);
        getDoc().getGeneral().getBoundingBox().getValue().setUpperCorner(upperCorner);
    }

    @Override
    public String getSrs() {
        return getDoc().getGeneral().getBoundingBox().getValue().getCrs();
    }

    @Override
    public void setSrs(String srs) {
        getDoc().getGeneral().getBoundingBox().getValue().setCrs(srs);
    }
    
    

    /*private Style getStyleFromSLD(InputStream xmlin) { 
        
        StyleFactory factory = CommonFactoryFinder.getStyleFactory(GeoTools.getDefaultHints());
        SLDParser sldparser = new SLDParser(factory, xmlin);
        Style[] sldstyles = sldparser.readXML();      
        return sldstyles[0];
        return null;
        
    }

    private Style getStyleFromSLD(URL surl) throws IOException {   
        
         StyleFactory factory = CommonFactoryFinder.getStyleFactory(GeoTools.getDefaultHints());
         SLDParser sldparser = new SLDParser(factory, surl);
         Style[] sldstyles = sldparser.readXML();
         return sldstyles[0];
            
    }


    private Style getWfsLayerDefaultStyle(LayerType layer, SimpleFeatureType schema) {
        String typeName=layer.getName();
        StyleFactory sf = CommonFactoryFinder.getStyleFactory( GeoTools.getDefaultHints() );
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );        
        Symbolizer symbolizer; 
        if(schema.getDefaultGeometry().getType().getBinding().toString().equals("class com.vividsolutions.jts.geom.MultiPoint")){
            //Point
            symbolizer =(PointSymbolizer) sf.getDefaultPointSymbolizer();
        }else if(schema.getDefaultGeometry().getType().toString().equals("class com.vividsolutions.jts.geom.MultiLineString")){
            //Line
            symbolizer =(LineSymbolizer) sf.getDefaultLineSymbolizer();	
        }else{
            //Polygon
            symbolizer =(PolygonSymbolizer) sf.getDefaultPolygonSymbolizer();	
        }    
        Rule rule = sf.createRule();
        rule.setFilter(  Filter.INCLUDE );
        rule.setSymbolizers( new Symbolizer[]{  symbolizer });
        FeatureTypeStyle type = sf.createFeatureTypeStyle();
        type.setFeatureTypeName(typeName);
        type.addRule( rule );
        Style style = sf.createStyle();
        style.addFeatureTypeStyle(type);
                
        return style;
    }*/

      
    /*Feature Style*/
  /*  private Style getWfsLayerStyle(LayerType layer,SimpleFeatureType schema) throws MalformedURLException, JAXBException, IOException{
        Style wfsStyle = null;
        
        if(layer.getStyleList()!=null){
            for (StyleType style : layer.getStyleList().getStyle()) {

                if (style.isCurrent()!=null && style.isCurrent()) {

                    if(style.getName()==null){

                        //create the parser with the sld configuration
                        Configuration configuration = new SLDConfiguration();
                        Parser parser = new Parser(configuration);

                        if(style.getSLD().getOnlineResource()!=null){

                            //TODO transformUrlSLDToStyleObject
                            System.out.println(style.getSLD().getOnlineResource().getHref());
                            wfsStyle= getStyleFromSLD(new URL(style.getSLD().getOnlineResource().getHref()));
                            break;

                        }else if(style.getSLD().getStyledLayerDescriptor()!=null){ 

                            //TODO transformInlineSLDToStyleObject
                            JAXBContext Jcontext = JAXBContext.newInstance("org.constellation.sld.v100");
                            Marshaller marshaller = Jcontext.createMarshaller();
                            StringWriter xmlstr = new StringWriter();
                            marshaller.marshal(style.getSLD().getStyledLayerDescriptor(), xmlstr);
                            wfsStyle= getStyleFromSLD(new ByteArrayInputStream(xmlstr.getBuffer().toString().getBytes()));
                            break;

                         }else if(style.getSLD().getFeatureTypeStyle()!=null){
                            //TODO transformInlineFeatureTypeStyleToStyleObject
                            break;
                         }
                    }                                            
                    else{ 
                        //TODO request getStyle sur le serveur
                        break;
                    }
                }
            }
        }
        if(layer.getFilter()!=null){
            //TODO addFilterToStyleObject  :see ogc spec for filter  http://schemas.opengis.net/filter/1.0.0/filter.xsd
        }
        if(wfsStyle == null)
            wfsStyle= getWfsLayerDefaultStyle(layer,schema); 
        return wfsStyle;
    }*/
    
   
    

    @Override
    public HashMap<String, WebMapServer> getWmsServers() {
        return wmsServers;
    }

    @Override
    public void setWmsServers(HashMap<String, WebMapServer> wmsServers) {
        this.wmsServers = wmsServers;
    }

    @Override
    public HashMap<String, DataStore> getWfsDataStores() {
        return wfsDataStores;
    }

    @Override
    public void setWfsDataStores(HashMap<String, DataStore> wfsDataStores) {
         this.wfsDataStores = wfsDataStores;
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setVersion(String version) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return getDoc().getGeneral().getTitle();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String save() { 
        try {
            JAXBContext.newInstance("net.opengis.owc.v030").createMarshaller().marshal(getDoc(),  new File("owc.xml"));
            return "/tmp/owc.xml";
        } catch (JAXBException ex) {
            Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;            
    }
    
    /*******************************Layers functions*************************/
    
    @Override
    public Layer[] getLayers() {
        try {
            Double minx = getMinx();
            Double miny = getMiny();
            Double maxx = getMaxx();
            Double maxy = getMaxy();
            String srs = getSrs();
            ReferencedEnvelope refEnv = new ReferencedEnvelope(new Envelope(minx, maxx, miny, maxy), CRS.decode(srs));
            List<LayerType> layerList = getDoc().getResourceList().getLayer();
            Layer[] layers = new Layer[layerList.size()];
            int i = 0;
            for (LayerType layerType : layerList) {
                try {
                    switch (layerType.getServer().get(0).getService()) {
                        case URN_OGC_SERVICE_TYPE_WMS:
                            if(wmsServers==null){
                                wmsServers=new HashMap<String,WebMapServer>();                                         
                            }                             
                            String wmsUrl=layerType.getServer().get(0).getOnlineResource().get(0).getHref();
                            if(wmsServers.get(wmsUrl)==null){    
                                wmsServers.put(wmsUrl, new WebMapServer(new URL(wmsUrl),layerType.getServer().get(0).getVersion()));
                                
                            }             
                            
                            
                            Layer layer = new DefaultLayer();
                            
                            layer.setMapLayer(new WMSMapLayer(wmsServers.get(wmsUrl),refEnv));
                            
                             /* Type */                        
                            layer.setType(layerType.getServer().get(0).getService().value());
                            System.out.println("tyyyyyyyype"+layerType.getServer().get(0).getService().value());
                             /* Id */
                            if(layerType.getId()==null)
                                layerType.setId("MapFaces_Layer_WMS_"+i);                           
                            layer.setId(layerType.getId());
                                                        
                            /* Name */
                            layer.setName(layerType.getName());
                            
                            /* Hidden */
                            layer.setHidden(layerType.isHidden());
                            
                            /* Opacity */
                            if(layerType.getOpacity()!=null)
                                layer.setOpacity(layerType.getOpacity().toString());
                            
                            /* Title */    
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
                            HashMap allDims = parseDimensionList(layerType);
                            if(allDims.size()>0){
                                layer.setDimensionList(allDims);
                            }
                             
                            /*StyleList*/
                            if(layerType.getStyleList()!=null){
                                for (StyleType style : layerType.getStyleList().getStyle()) {
                                        if (style.isCurrent()!=null && style.isCurrent()) {  
                                            if(style.getName()==null){
                                                if(style.getSLD().getOnlineResource()!=null){
                                                    ((WMSMapLayer)layer.getMapLayer()).setSld(URLEncoder.encode(StringUtils.defaultString(style.getSLD().getOnlineResource().getHref()), "UTF-8"));                                                
                                                }else if(style.getSLD().getStyledLayerDescriptor()!=null){                                                   
                                                     JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");                                               
                                                    Marshaller marshaller = Jcontext.createMarshaller();
                                                    StringWriter test = new StringWriter();
                                                    marshaller.marshal(style.getSLD().getStyledLayerDescriptor(),test);
                                                    ((WMSMapLayer)layer.getMapLayer()).setSld_body(URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<","><"), "UTF-8")); 
                                                }else if(style.getSLD().getFeatureTypeStyle()!=null){
                                                    //TODO transformFeatureTypeStyleToString
                                                    JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");                                               
                                                    Marshaller marshaller = Jcontext.createMarshaller();
                                                    StringWriter test = new StringWriter();
                                                    marshaller.marshal(style.getSLD().getFeatureTypeStyle(),test);
                                                    ((WMSMapLayer)layer.getMapLayer()).setSld(URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<","><"), "UTF-8")); 
                                                }else{
                                                    ((WMSMapLayer)layer.getMapLayer()).setStyles(""); 
                                                }
                                            }else{
                                                 ((WMSMapLayer)layer.getMapLayer()).setStyles(style.getName());
                                            }   
                                            break;
                                        }
                                         ((WMSMapLayer)layer.getMapLayer()).setStyles("");                    
                                        System.out.println("            Styles de la couche : "+ ((WMSMapLayer)layer.getMapLayer()).getStyles());
                                }
                            }else{
                                  ((WMSMapLayer)layer.getMapLayer()).setStyles("");   
                            }
                            if (layer == null) {
                                break;
                            }
                            layers[i] = layer;                        
                            break;
                        
                        case URN_OGC_SERVICE_TYPE_WFS:                           
                            DataStore data;
                            String wfsUrl=layerType.getServer().get(0).getOnlineResource().get(0).getHref();
                            String version =  layerType.getServer().get(0).getVersion();
                            if(wfsUrl.contains("?")){
                                wfsUrl+="SERVICE=WFS&REQUEST=GetCapabilities&VERSION="+version;
                            }else{
                                wfsUrl+="?SERVICE=WFS&REQUEST=GetCapabilities&VERSION="+version;
                            }
                            if(wfsDataStores==null){
                                wfsDataStores=new HashMap<String,DataStore>(); 
                            }
                            if( wfsDataStores.get(wfsUrl)==null){    
                                Map connectionParameters = new HashMap();
                                connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", wfsUrl );
                                if(layerType.getMaxFeatures()!=null)
                                    connectionParameters.put("WFSDataStoreFactory:MAXFEATURES",layerType.getMaxFeatures());
                                // Step 2 - connection
                                data = DataStoreFinder.getDataStore( connectionParameters );
                                wfsDataStores.put(wfsUrl,data);
                            }else{
                                 data = wfsDataStores.get(wfsUrl);
                            }                                 
                           
                            // Step 3 - discouvery
                            String typeName = layerType.getName();
                            SimpleFeatureType schema = data.getSchema( typeName );
                            
                           /* Style style = getWfsLayerStyle(layerType,schema);
                            // Step 4 - target    
                            DefaultMapLayer wfsLayer = new DefaultMapLayer(data.getFeatureSource(typeName),style);  
                            
                            if(layerType.getId()==null)
                                layerType.setId(Utils.generateUniqueId("MapFaces_Layer_WFS_"));                            
                            wfsLayer.setId(layerType.getId());
                            
                            if(layerType.getOpacity()!=null)
                                wfsLayer.setOpacity(layerType.getOpacity().toString());
                             
                            if (wfsLayer == null) {
                                 break;
                            }
                            mapLayers[i] = wfsLayer;  */                     
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
                } catch (JAXBException ex) {
                    Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            return layers;
        } catch (NoSuchAuthorityCodeException ex) {
            Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
//    public MapLayer[] getMapLayers() {
//        try {
//            Double minx = getMinx();
//            Double miny = getMiny();
//            Double maxx = getMaxx();
//            Double maxy = getMaxy();
//            String srs = getSrs();
//            ReferencedEnvelope refEnv = new ReferencedEnvelope(new Envelope(minx, maxx, miny, maxy), CRS.decode(srs));
//            List<LayerType> layerList = getDoc().getResourceList().getLayer();
//            MapLayer[] mapLayers = new MapLayer[layerList.size()];
//            int i = 0;
//            for (LayerType layerType : layerList) {
//                try {
//                    switch (layerType.getServer().get(0).getService()) {
//                        case URN_OGC_SERVICE_TYPE_WMS:
//                            if(wmsServers==null){
//                                wmsServers=new HashMap<String,WebMapServer>(); 
//                            }                             
//                            String wmsUrl=layerType.getServer().get(0).getOnlineResource().get(0).getHref();
//                            if(wmsServers.get(wmsUrl)==null){    
//                                wmsServers.put(wmsUrl, new WebMapServer(new URL(wmsUrl),layerType.getServer().get(0).getVersion()));
//                                
//                            }             
//                            WMSMapLayer wmsLayer = new WMSMapLayer(wmsServers.get(wmsUrl),refEnv);
//                            
//                             /* Id */
//                            if(layerType.getId()==null)
//                                layerType.setId("MapFaces_Layer_WMS_"+i);                           
//                            wmsLayer.setId(layerType.getId());
//                            /* Name */
//                            wmsLayer.setName(layerType.getName());
//                            
//                            /* Hidden */
//                            wmsLayer.setVisible(!layerType.isHidden());
//                            
//                            /* Opacity */
//                            if(layerType.getOpacity()!=null)
//                                wmsLayer.setOpacity(layerType.getOpacity().toString());
//                            
//                            /* Title */    
//                            wmsLayer.setTitle(layerType.getTitle());
//                            
//                            /* Version */
//                            wmsLayer.setVersion(layerType.getServer().get(0).getVersion());
//                            
//                             /*OutputFormat*/
//                            for (String format : layerType.getOutputFormat()) {                                
//                                if (format.isEmpty()) {
//                                    wmsLayer.setOutputFormat("image/gif");
//                                } else {
//                                    wmsLayer.setOutputFormat(format);
//                                    break;
//                                }
//                            }
//                            
//                            /*DimensionList*/                            
//                            HashMap allDims = parseDimensionList(layerType);
//                            if(allDims.size()>0){
//                                wmsLayer.setDimensions(allDims);
//                            }
//                             
//                            /*StyleList*/
//                            if(layerType.getStyleList()!=null){
//                                for (StyleType style : layerType.getStyleList().getStyle()) {
//                                        if (style.isCurrent()!=null && style.isCurrent()) {  
//                                            if(style.getName()==null){
//                                                if(style.getSLD().getOnlineResource()!=null){
//                                                    wmsLayer.setSld(URLEncoder.encode(StringUtils.defaultString(style.getSLD().getOnlineResource().getHref()), "UTF-8"));                                                
//                                                }else if(style.getSLD().getStyledLayerDescriptor()!=null){                                                   
//                                                     JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");                                               
//                                                    Marshaller marshaller = Jcontext.createMarshaller();
//                                                    StringWriter test = new StringWriter();
//                                                    marshaller.marshal(style.getSLD().getStyledLayerDescriptor(),test);
//                                                    wmsLayer.setSld_body(URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<","><"), "UTF-8")); 
//                                                }else if(style.getSLD().getFeatureTypeStyle()!=null){
//                                                    //TODO transformFeatureTypeStyleToString
//                                                    JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030");                                               
//                                                    Marshaller marshaller = Jcontext.createMarshaller();
//                                                    StringWriter test = new StringWriter();
//                                                    marshaller.marshal(style.getSLD().getFeatureTypeStyle(),test);
//                                                    wmsLayer.setSld(URLEncoder.encode(StringUtils.defaultString(test.toString()).replaceAll(">+\\s+<","><"), "UTF-8")); 
//                                                }else{
//                                                     wmsLayer.setStyles(""); 
//                                                }
//                                            }else{
//                                                wmsLayer.setStyles(style.getName());
//                                            }   
//                                            break;
//                                        }
//                                        wmsLayer.setStyles("");                    
//                                        System.out.println("            Styles de la couche : "+wmsLayer.getStyles());
//                                }
//                            }else{
//                                 wmsLayer.setStyles("");   
//                            }
//                            if (wmsLayer == null) {
//                                break;
//                            }
//                            mapLayers[i] = wmsLayer;                        
//                            break;
//                        
//                        case URN_OGC_SERVICE_TYPE_WFS:                           
//                            DataStore data;
//                            String wfsUrl=layerType.getServer().get(0).getOnlineResource().get(0).getHref();
//                            String version =  layerType.getServer().get(0).getVersion();
//                            if(wfsUrl.contains("?")){
//                                wfsUrl+="SERVICE=WFS&REQUEST=GetCapabilities&VERSION="+version;
//                            }else{
//                                wfsUrl+="?SERVICE=WFS&REQUEST=GetCapabilities&VERSION="+version;
//                            }
//                            if(wfsDataStores==null){
//                                wfsDataStores=new HashMap<String,DataStore>(); 
//                            }
//                            if( wfsDataStores.get(wfsUrl)==null){    
//                                Map connectionParameters = new HashMap();
//                                connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", wfsUrl );
//                                if(layerType.getMaxFeatures()!=null)
//                                    connectionParameters.put("WFSDataStoreFactory:MAXFEATURES",layerType.getMaxFeatures());
//                                // Step 2 - connection
//                                data = DataStoreFinder.getDataStore( connectionParameters );
//                                wfsDataStores.put(wfsUrl,data);
//                            }else{
//                                 data = wfsDataStores.get(wfsUrl);
//                            }                                 
//                           
//                            // Step 3 - discouvery
//                            String typeName = layerType.getName();
//                            SimpleFeatureType schema = data.getSchema( typeName );
//                            
//                           /* Style style = getWfsLayerStyle(layerType,schema);
//                            // Step 4 - target    
//                            DefaultMapLayer wfsLayer = new DefaultMapLayer(data.getFeatureSource(typeName),style);  
//                            
//                            if(layerType.getId()==null)
//                                layerType.setId(Utils.generateUniqueId("MapFaces_Layer_WFS_"));                            
//                            wfsLayer.setId(layerType.getId());
//                            
//                            if(layerType.getOpacity()!=null)
//                                wfsLayer.setOpacity(layerType.getOpacity().toString());
//                             
//                            if (wfsLayer == null) {
//                                 break;
//                            }
//                            mapLayers[i] = wfsLayer;  */                     
//                            break;
//                        case URN_OGC_SERVICE_TYPE_GML:
//                           
//                           //create the parser with the gml 2.0 configuration
//                          /* GML DataStore was not very efficient
//                           
//                           
//                           Configuration configuration = new org.geotools.gml2.GMLConfiguration();
//
//                           Parser parser = new org.geotools.xml.Parser( configuration );
//                            
//                            //the xml instance document above
//                            InputStream gml =new URL(layerType.getServer().get(0).getOnlineResource().get(0).getHref()).openStream();
//                           
//
//                            //parse
//                            FeatureCollection fc = (FeatureCollection) parser.parse( gml );
//                            
//                            // Step 3 - discouvery
//                            String gmlName = layerType.getName();
//                            SimpleFeatureType gmlSchema = (SimpleFeatureType) fc.getSchema();
//                            Style gmlStyle = getWfsLayerStyle(layerType,gmlSchema);
//                    
//                            // Step 4 - target    
//                            DefaultMapLayer gmlLayer = new DefaultMapLayer(fc, gmlStyle);
//                            // wfsLayer.setSEStyle((org.opengis.style.Style) style);
//                             
//                            if (gmlLayer == null) {
//                                 break;
//                            }
//                            mapLayers[i] = gmlLayer;  */      
//                            break;
//                        case URN_OGC_SERVICE_TYPE_KML:
//                            break;
//                        case URN_OGC_SERVICE_TYPE_WCS:
//                            break;
//                        case URN_OGC_SERVICE_TYPE_SLD:
//                            break;
//                        case URN_OGC_SERVICE_TYPE_FES:
//                            break;
//                        default:
//                            break;
//                    }
//                    i++;
//                } catch (SAXException ex) {
//                    Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (JAXBException ex) {
//                    Logger.getLogger(OWC_v030.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//                } 
//            }
//            return mapLayers;
//        } catch (NoSuchAuthorityCodeException ex) {
//            Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FactoryException ex) {
//            Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    } 
    
    
    public void setLayers( Layer[] layers) {
        this.setLayers(layers);
    }
    
    @Override
    public String getLayersId(){
        List<LayerType> layerList = getDoc().getResourceList().getLayer();
        String layersId  = "";
        for (LayerType layer : layerList) {
            layersId += ","+layer.getId();
        }
        return layersId.replaceFirst(",","");
    }

    
    /*******************************Dimension functions*************************/
   /* private HashMap parseDimensionList(LayerType layer) {
        HashMap allDims = new HashMap<String,String>();
        String wmsUrl = layer.getServer().get(0).getOnlineResource().get(0).getHref();
        if(layer.getDimensionList()==null){
           //TODO find dimension into getcapabilities
           setDimensionList(layer, wmsServers.get(wmsUrl).getJaxbCapabilities());
        }
        if(layer.getDimensionList()!=null && layer.getDimensionList().getDimension().size()>0){                                
            for (DimensionType dim : layer.getDimensionList().getDimension()) {
                if(dim.getUserValue()==null){
                    if(dim.getDefault()==null){
                        if(dim.getValue()==null){
                            //TODO find dimension into getcapabilities
                            setDimensionList(layer, wmsServers.get(wmsUrl).getJaxbCapabilities());
                        }else{
                            // select the first value and add it as default and userValue value
                            String tmp = (dim.getValue().split("/")[0]).split(",")[0];
                            dim.setDefault(tmp);
                            dim.setUserValue(tmp);
                            allDims.put(dim.getName(),tmp);  
                        }                                                 
                     }else{
                        //select default value and add it as  userValue value
                        dim.setUserValue(dim.getDefault());
                        allDims.put(dim.getName(),dim.getUserValue());                                            
                     }
                 }else{
                     allDims.put(dim.getName(),dim.getUserValue());
                 }
            }
        }
        return allDims;
    }*/

    private HashMap parseDimensionList(LayerType layer) {
        HashMap allDims = new HashMap<String,Dimension>();
        String wmsUrl = layer.getServer().get(0).getOnlineResource().get(0).getHref();
        if(layer.getDimensionList()==null){
           //TODO find dimension into getcapabilities
           setDimensionList(layer, wmsServers.get(wmsUrl).getJaxbCapabilities());
        }
        if(layer.getDimensionList()!=null && layer.getDimensionList().getDimension().size()>0){                                
            for (DimensionType dim : layer.getDimensionList().getDimension()) {
                if(dim.getUserValue()==null){
                    if(dim.getDefault()==null){
                        if(dim.getValue()==null){
                            //TODO find dimension into getcapabilities
                            setDimensionList(layer, wmsServers.get(wmsUrl).getJaxbCapabilities());
                        }else{
                            /* select the first value and add it as default and userValue value*/
                            String tmp = (dim.getValue().split("/")[0]).split(",")[0];
                            dim.setDefault(tmp);
                            dim.setUserValue(tmp);
                            allDims.put(dim.getName(),transformDimTypeToDim(dim));  
                        }                                                 
                     }else{
                        /* select default value and add it as  userValue value*/
                        dim.setUserValue(dim.getDefault());
                        allDims.put(dim.getName(),transformDimTypeToDim(dim));                                            
                     }
                 }else{
                     allDims.put(dim.getName(),transformDimTypeToDim(dim));
                 }
            }
        }
        System.out.println("testttttttttttttt"+allDims.get("elevation"));
        return allDims;
    }
   

    private void setDimensionList(LayerType layer, AbstractWMSCapabilities jaxbCapabilities) {
        if(jaxbCapabilities != null){
            AbstractLayer layer2 = jaxbCapabilities.getLayerFromName(layer.getName());
            if(layer2!=null){
                List<AbstractDimension> dims = layer2.getAbstractDimension();
                if(dims.size()>0){
                    DimensionListType dimList = new DimensionListType();
                    for(AbstractDimension tmp : dims){
                        DimensionType dim = new DimensionType();
                        dim.setName(tmp.getName());
                        dim.setUnits(tmp.getUnits());
                        dim.setDefault(tmp.getDefault());
                        dim.setValue(tmp.getValue());
                        dim.setUnitSymbol(tmp.getUnitSymbol());
                        dimList.getDimension().add(dim);
                    }
                   layer.setDimensionList(dimList);
                }
            }
        }
    }
    
    @Override
    public void setLayerDimension(String layerName, String dimName, String value){
        getLayerDimensionNodeFromName(layerName, dimName).setValue(value);        
    };
    
    @Override
    public void setLayerAttrDimension(String layerName, String dimName, String attrName, String value){
        if(attrName.equals("userValue")){
            getLayerDimensionNodeFromName(layerName, dimName).setUserValue(value);
        }
    };
    public void setLayerAttrDimensionFromId(String layerId, String dimName, String attrName, String value){
        if(attrName.equals("userValue")){
            getLayerDimensionNodeFromId(layerId, dimName).setUserValue(value);
        }
    };
    private DimensionType getLayerDimensionNodeFromName(String layerName,String dimName) {
        
       LayerType layer=getLayerNodeFromName(layerName);
       for(DimensionType tmp : layer.getDimensionList().getDimension()){
            if(tmp != null && tmp.getName().equals(dimName)){
                return tmp;
            }
        } 
        return null;
    }
    private DimensionType getLayerDimensionNodeFromId(String layerId,String dimName) {
        
       LayerType layer=getLayerNodeFromId(layerId);
       for(DimensionType tmp : layer.getDimensionList().getDimension()){
            if(tmp != null && tmp.getName().equals(dimName)){
                return tmp;
            }
        } 
        return null;
    }
    /******************************Layer Node functions*****************************************/
     
    private LayerType getLayerNodeFromName(String name) {
        
       List<LayerType> layers=getDoc().getResourceList().getLayer();
       for(LayerType tmp : layers){
            if(tmp != null && tmp.getName().equals(name)){
                return tmp;
            }
        } 
        return null;
    }
    private LayerType getLayerNodeFromId(String id) {
       System.out.println("getlayernodeId "+id);
       List<LayerType> layers=getDoc().getResourceList().getLayer();
       for(LayerType tmp : layers){
            if(tmp != null && tmp.getId().equals(id)){
                return tmp;
            }
        } 
        return null;
    }
    
    /******************************Map Layer functions*****************************************/
     
    public Layer getLayerFromName(String name) {
        
       Layer[] layers=getLayers();
       for(Layer tmp : layers){
            if(tmp != null && tmp.getName().equals(name)){
                return tmp;
            }
        } 
        return null;
    }

    public

    OWSContextType getDoc() {
        return doc;
    }

    public void setDoc(OWSContextType doc) {
        this.doc = doc;
    }

    @Override
    public void setHidden(String layerId, boolean vis) {
        List<LayerType> layerList = getDoc().getResourceList().getLayer();
        for (LayerType layer : layerList) {
            if(layer.getId().equals(layerId)){
                layer.setHidden(vis);
                break;
            }
        }
        
    }

    @Override
    public  boolean isHidden(String layerId) {
        List<LayerType> layerList = getDoc().getResourceList().getLayer();
        for (LayerType layer : layerList) {
            if(layer.getId().equals(layerId)){
                return layer.isHidden();
            }
        }
        return true;
    }

    @Override
    public void setOpacity(String layerId, double b) {
         List<LayerType> layerList = getDoc().getResourceList().getLayer();
        for (LayerType layer : layerList) {
            if(layer.getId().equals(layerId)){
                layer.setOpacity(BigDecimal.valueOf(b));
                break;
            }
        }
    }

    @Override
    public String getOpacity(String layerId) {
        List<LayerType> layerList = getDoc().getResourceList().getLayer();
        for (LayerType layer : layerList) {
            if(layer.getId().equals(layerId)){
                return String.valueOf(layer.getOpacity());
            }
        }
        return null;
    }

    @Override
    public String getLayerDimension(String layerId, String dimName, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLayerAttrDimension(String layerId, String dimName, String attrName, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLayers(MapLayer[] layers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Dimension transformDimTypeToDim(DimensionType dim) {
        Dimension tmp = new DefaultDimension();
        tmp.setValue(dim.getValue());
        tmp.setUserValue(dim.getUserValue());
        tmp.setDefault(dim.getDefault());
        tmp.setCurrent(true);
        tmp.setMultipleValues(false);
        tmp.setName(dim.getName());
        tmp.setNearestValues(false);
        tmp.setUnitSymbol(dim.getUnitSymbol());
        tmp.setUnits(dim.getUnits());       
        System.out.println(dim.getName()+" "+dim.getValue());
        return tmp;
    }


}
