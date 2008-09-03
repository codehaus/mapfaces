/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models;

import java.util.HashMap;
import java.util.List;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapLayer;

/**
 *
 * @author olivier
 */
public interface Layer {

    void setUserValue(String string, String value);

    String getDataUrl();

    String getDepth();

    HashMap<String, Dimension> getDimensionList();

    String getGroup();

    String getId();

    String getInlineGeometry();

    String getMaxFeatures();

    String getMaxScaleDenominator();

    String getMetadataUrl();

    String getMinScaleDenominator();

    String getName();

    String getOpacity();

    String getOutputFormat();

    boolean getQueryable();

    ReferencedEnvelope getRefEnv();

    String getResX();

    String getResY();

    String getResZ();

    String getResponseCRS();

    //Server getServer();

    //List<Style> getStyle();

    String getTitle();

    boolean isHidden();

    void setDataUrl(String dataUrl);

    void setDepth(String depth);

    void setDimensionList(HashMap<String, Dimension> dimensionList);

    void setGroup(String group);

    void setHidden(boolean hidden);

    void setId(String id);

    void setInlineGeometry(String inlineGeometry);

    void setMaxFeatures(String maxFeatures);

    void setMaxScaleDenominator(String maxScaleDenominator);

    void setMetadataUrl(String metadataUrl);

    void setMinScaleDenominator(String minScaleDenominator);

    void setName(String name);

    void setOpacity(String opacity);

    void setOutputFormat(String outputFormat);

    void setQueryable(boolean queryable);

    void setRefEnv(ReferencedEnvelope refEnv);

    void setResX(String resX);

    void setResY(String resY);

    void setResZ(String resZ);

    void setResponseCRS(String responseCRS);

   // void setServer(Server server);

    //void setStyle(List<Style> style);

    void setTitle(String title);
    
    MapLayer getMapLayer();

    void setMapLayer(MapLayer mapLayer);
    
    void get(MapLayer mapLayer);
    
    Dimension getElevation();
    
    Dimension getTime();
    
    Dimension getDimension(String name);
    
    void setDimension(String name, String value);

    String getType();

    void setType(String type);
    
    /*
     * These function are added to easily configure the layercontrol tag
     * */
    /*
     * Elevations is the dimension node value for elevation dimension
     * */
            
    String getElevations();
    String getTimes();
    
    String setElevations();
     
    String getUserValueElevation();
    String getUserValueTime();
    String getUserValueDimension(String name);
}
