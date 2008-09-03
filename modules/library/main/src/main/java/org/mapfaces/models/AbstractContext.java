/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models;

import java.math.BigInteger; 
import java.util.HashMap;
import java.util.List;
import org.geotools.data.DataStore;
import org.geotools.data.wms.WebMapServer;
import org.geotools.map.MapLayer;

/**
 *
 * @author olivier
 */
public abstract class AbstractContext extends AbstractModelBase {  
    
    
    public abstract String getOpacity(String layerId);

    public abstract boolean isHidden(String layerId);

    /*Context server used*/
    //private WebFeatureServer[] wmsServers;
    
    public abstract String getId();

    //public abstract MapLayer getLayerFromId(String id);
    
    public abstract String getVersion();

    public abstract String getTitle();

    public abstract List<String> getKeywordList();

    public abstract String getAbstracts();

    public abstract String getLogoUrl();

    public abstract String getDescriptionUrl();
    
    public abstract BigInteger getWindowWidth();

    public abstract void setOpacity(String layerId, double b);

    public abstract void setWindowWidth(BigInteger windowWidth);

    public abstract BigInteger getWindowHeight();

    public abstract  void setWindowHeight(BigInteger windowHeight);

    public abstract Double getMinx();

    public abstract  void setMinx(Double minx);

    public abstract  Double getMiny();

    public abstract void setMiny(Double miny);

    public abstract Double getMaxx();

    public abstract void setMaxx(Double maxx) ;

    public abstract Double getMaxy();

    public abstract void setMaxy(Double maxy);

    public abstract String getSrs();

    public abstract void setSrs(String srs) ;
    
    public abstract Layer[] getLayers();
    
    public abstract void setLayers( MapLayer[] layers);

    public abstract HashMap<String,WebMapServer> getWmsServers();

    public abstract void setWmsServers(HashMap<String,WebMapServer> wmsServers);

    public abstract HashMap<String, DataStore> getWfsDataStores();

    public abstract void setWfsDataStores(HashMap<String, DataStore> wfsDataStores);

    public abstract void setId(String id);

    public abstract void setVersion(String version);

    public abstract String getName();

    public abstract void setName(String name) ;

    public abstract void setTitle(String title);

    public abstract String save();
    
    public abstract void setLayerDimension(String layerId, String dimName, String value);
    
    public abstract void setLayerAttrDimension(String layerName, String dimName, String attrName, String value);
    
    public abstract void setLayerAttrDimensionFromId(String layerId, String dimName, String attrName, String value);
    
    public abstract String getLayerDimension(String layerId, String dimName, String value);
    
    public abstract String getLayerAttrDimension(String layerId, String dimName, String attrName, String value);
    
    public abstract String getLayersId();
    
    public abstract Object getDoc();  
    
    public abstract void setHidden(String layerId, boolean vis);
}
