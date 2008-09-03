/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package org.mapfaces.models;
//
//import com.vividsolutions.jts.geom.Envelope;
//import java.io.File;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import net.opengis.context.v110.FormatType;
//import net.opengis.context.v110.LayerType;
//import net.opengis.context.v110.StyleType;
//import net.opengis.context.v110.ViewContextType;
//import org.geotools.data.DataStore;
//import org.geotools.data.wms.WebMapServer;
//import org.geotools.geometry.jts.ReferencedEnvelope;
//import org.geotools.map.MapLayer;
//import org.geotools.map.WMSMapLayer;
//import org.geotools.ows.ServiceException;
//import org.geotools.referencing.CRS;
//import org.opengis.referencing.FactoryException;
//import org.opengis.referencing.NoSuchAuthorityCodeException;
/*
 *: URL source          = new URL(request.getSource());
                 URLConnection conec = source.openConnection();
                 
                 // we get the source document
               File fileToHarvest = File.createTempFile("harvested", "xml");
               InputStream in = conec.getInputStream();
                 FileOutputStream out = new FileOutputStream(fileToHarvest);
                byte[] buffer = new byte[1024];
                 int size;
               while ((size = in.read(buffer, 0, 1024)) > 0) {
                    out.write(buffer, 0, size);
             }
              
                //TODO find a way to know if the source is another csw or directly the resource (resourceType?)
                // for now we consider that is directly the resource to harvest
                 if (resourceType.equals("http://www.isotc211.org/2005/gmd")      || 
                     resourceType.equals("http://www.opengis.net/cat/csw/2.0.2")  ||
                     resourceType.equals("http://www.isotc211.org/2005/gfc"))        {
                         Object harvested = unmarshaller.unmarshal(fileToHarvest); 
 */
/**
 *
 * @author olivier
 */
//public class WMC_v110 extends AbstractWMC {
//    
//    ViewContextType doc;
//
//    /*Context server used*/
//    private HashMap<String,WebMapServer> wmsServers;
//    
//     /*Context server used*/
//    private HashMap<String,DataStore> wfsDataStores;
//    
//    public WMC_v110(Object  JAXBValue) {
//        this.doc =(ViewContextType) JAXBValue;
//    }
//    
//    @Override
//    public String getId() {
//        return doc.getId();
//    }
//    
//    @Override
//    public String getVersion() {
//        return doc.getVersion();
//    }
//    
//    @Override
//    public String getTitle() {
//        return doc.getGeneral().getTitle();
//    }     
//
//    @Override
//    public List<String> getKeywordList() {
//        return doc.getGeneral().getKeywordList().getKeyword();
//    }
//
//    @Override
//    public String getAbstracts() {
//        return doc.getGeneral().getAbstract();
//    }
//
//    @Override
//    public String getLogoUrl() {
//        return doc.getGeneral().getLogoURL().getOnlineResource().getHref();
//    }
//    
//    @Override
//    public String getDescriptionUrl() {
//        return doc.getGeneral().getDescriptionURL().getOnlineResource().getHref();
//    }
//    
//    
//    @Override
//    public BigInteger getWindowWidth() {
//        return doc.getGeneral().getWindow().getWidth();
//    }
//
//    @Override
//    public void setWindowWidth(BigInteger windowWidth) {
//        doc.getGeneral().getWindow().setWidth(windowWidth);
//    }
//
//    @Override
//    public BigInteger getWindowHeight() {
//        return doc.getGeneral().getWindow().getHeight();
//    }
//
//    @Override
//    public void setWindowHeight(BigInteger windowHeight) {
//        doc.getGeneral().getWindow().setHeight(windowHeight);
//    }
//
//    @Override
//    public Double getMinx() {
//        return doc.getGeneral().getBoundingBox().getMinx().doubleValue();
//    }
//
//    @Override
//    public void setMinx(Double minx) {
//        doc.getGeneral().getBoundingBox().setMinx(new BigDecimal(minx));
//    }
//
//    @Override
//    public Double getMiny() {
//        return doc.getGeneral().getBoundingBox().getMiny().doubleValue();
//    }
//
//    @Override
//    public void setMiny(Double miny) {
//       doc.getGeneral().getBoundingBox().setMiny(new BigDecimal(miny));
//    }
//
//    @Override
//    public Double getMaxx() {
//        return doc.getGeneral().getBoundingBox().getMaxx().doubleValue();
//    }
//
//    @Override
//    public void setMaxx(Double maxx) {
//        doc.getGeneral().getBoundingBox().setMaxx(new BigDecimal(maxx));
//    }
//
//    @Override
//    public Double getMaxy() {
//        return doc.getGeneral().getBoundingBox().getMaxy().doubleValue();
//    }
//
//    @Override
//    public void setMaxy(Double maxy) {
//        doc.getGeneral().getBoundingBox().setMaxy(new BigDecimal(maxy));
//    }
//
//    @Override
//    public String getSrs() {
//        return doc.getGeneral().getBoundingBox().getSRS();
//    }
//
//    @Override
//    public void setSrs(String srs) {
//       doc.getGeneral().getBoundingBox().setSRS(srs);
//    }
//    
//    @Override
//    public MapLayer[] getLayers() {
//        try {
//            Double minx =  getMinx().doubleValue();
//            Double miny = getMiny().doubleValue();
//            Double maxx = getMaxx().doubleValue();
//            Double maxy = getMaxy().doubleValue();
//            String srs = getSrs();
//            ReferencedEnvelope refEnv = new ReferencedEnvelope(new Envelope(minx, maxx, miny, maxy), CRS.decode(srs));
//            List<LayerType> layerList = doc.getLayerList().getLayer();
//            MapLayer[] mapLayers = new MapLayer[layerList.size()];
//            int i = 0;
//            for (LayerType layer : layerList) {
//                try {
//                    switch (layer.getServer().getService()) {
//                        case OGC_WMS:
//
//                            WMSMapLayer wmsLayer = new WMSMapLayer(new WebMapServer(new URL(layer.getServer().getOnlineResource().getHref())),refEnv);
//                            System.out.println(layer.getServer().getOnlineResource().getHref());
//                            wmsLayer.setName(layer.getName());
//                            System.out.println(layer.getName());
//                            wmsLayer.setTitle(layer.getTitle());
//                            System.out.println(layer.getTitle());
//                            wmsLayer.setVersion(layer.getServer().getVersion());
//                            System.out.println(layer.getServer().getVersion());
//                            
//                            /*set the output format*/
//                            for (FormatType format : layer.getFormatList().getFormat()) {
//                                if (format.isCurrent()) {
//                                    wmsLayer.setOutputFormat(format.getValue());
//                                    break;
//                                } else {
//                                    wmsLayer.setOutputFormat("image/gif");
//                                }
//                            }
//                            
//                            if(layer.getStyleList()!=null){
//                                for (StyleType style : layer.getStyleList().getStyle()) {
//                                         if (style.isCurrent()!=null && style.isCurrent()) { 
//                                            if(style.getName()==null){
//                                                if(style.getSLD().getOnlineResource()!=null){
//                                                    wmsLayer.setSld(style.getSLD().getOnlineResource().getHref());
//                                                }
//                                                else if(style.getSLD().getStyledLayerDescriptor()!=null){                                                   
//                                                    JAXBContext Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");                                               
//                                                    Marshaller marshaller = Jcontext.createMarshaller();
//                                                    StringWriter test = new StringWriter();
//                                                    marshaller.marshal(style.getSLD().getStyledLayerDescriptor(),test);
//                                                    wmsLayer.setSld_body(URLEncoder.encode(test.toString(), "UTF-8")); 
//                                                }else if(style.getSLD().getFeatureTypeStyle()!=null){
//                                                    //TODO transformFeatureTypeStyleToString
//                                                }else{
//                                                     wmsLayer.setStyles(""); 
//                                                }
//                                            }else{
//                                                wmsLayer.setStyles(style.getName());
//                                            }                                           
//                                            break;
//                                        }
//                                        wmsLayer.setStyles("");                                   
//                                }
//                            }else{
//                                 wmsLayer.setStyles("");   
//                            }
//                            if (wmsLayer == null) {
//                                System.out.println("layer est null");
//                                break;
//                            }
//                             System.out.println(wmsLayer.toString());
//                            mapLayers[i] = wmsLayer;
//                            
//                            break;
//                        case OGC_WFS:
//                            break;
//                        case OGC_WMS_C:
//                            break;
//                        default:
//                            break;
//                    }
//                    
//                    System.out.println(layerList.size()+" et "+mapLayers.length);
//                    i++;
//                } catch (JAXBException ex) {
//                    Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ServiceException ex) {
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
//    @Override
//    public void setLayers( MapLayer[] layers) {
//        this.setLayers(layers);
//    }
//        
//    @Override
//    public HashMap<String, WebMapServer> getWmsServers() {
//        return wmsServers;
//    }
//
//    @Override
//    public void setWmsServers(HashMap<String, WebMapServer> wmsServers) {
//        this.wmsServers = wmsServers;
//    }
//
//    @Override
//    public HashMap<String, DataStore> getWfsDataStores() {
//        return wfsDataStores;
//    }
//
//    @Override
//    public void setWfsDataStores(HashMap<String, DataStore> wfsDataStores) {
//         this.wfsDataStores = wfsDataStores;
//    }
//
//    @Override
//    public void setId(String id) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setVersion(String version) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public String getName() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setName(String name) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setTitle(String title) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//   public String save() { 
//        try {
//            JAXBContext.newInstance("net.opengis.context.v110").createMarshaller().marshal(doc,  File.createTempFile("wmc", ".xml"));
//            return "/tmp/wmc.xml";
//        } catch (JAXBException ex) {
//            Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(WMC_v110.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return null;          
//    }
//
//    @Override
//    public MapLayer getMapLayerFromName(String name) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setLayerDimension(String layerName, String dimName, String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void setLayerAttrDimension(String layerName, String dimName, String attrName, String value) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//     @Override
//    public String getLayersId(){
//       throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public ViewContextType getDoc() {
//        return doc;
//    }
//
//    public void setDoc(ViewContextType doc) {
//        this.doc = doc;
//    }
//
//   @Override
//    public void setHidden(String layerId, boolean vis) {
//        List<LayerType> layerList = getDoc().getLayerList().getLayer();
//        for (LayerType layer : layerList) {
//            if(layer.getName().equals(layerId)){
//                layer.setHidden(vis);
//                break;
//            }
//        }
//        
//    }
//
//    @Override
//    public  boolean isHidden(String layerId) {
//        List<LayerType> layerList = getDoc().getLayerList().getLayer();
//        for (LayerType layer : layerList) {
//            if(layer.getName().equals(layerId)){
//                return layer.isHidden();
//            }
//        }
//        return true;
//    }
//    
//     @Override
//    public void setOpacity(String id, double b) {
//        throw new UnsupportedOperationException("Opacity is not deined in spec WMC 1.0.0");
//    }
//
//    @Override
//    public String getOpacity(String layerId) {
//        throw new UnsupportedOperationException("Opacity is not deined in spec WMC 1.0.0");
//    }
//}
