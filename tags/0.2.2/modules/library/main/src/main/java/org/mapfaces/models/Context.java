/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */

package org.mapfaces.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import org.opengis.geometry.Envelope;

/**
 * @author Olivier Terral.
 */
public interface Context extends Serializable {


    String getId();

    void setId(String id);

    String getContextType();
    
    void setType(String type);

    String getVersion();

    void setVersion(String version);

    String getTitle();

    void setTitle(String title);

    String getWindowSize();

    void setWindowSize(String width, String height);

    String getWindowWidth();

    void setWindowWidth(String windowWidth);

    String getWindowHeight();

    void setWindowHeight(String windowHeight);

    String getBoundingBox();

    void setBoundingBox(String minx, String miny, String maxx, String maxy);

    String getMinx();

    void setMinx(String minx);

    String getMiny();

    void setMiny(String miny);

    String getMaxx();

    void setMaxx(String maxx);

    String getMaxy();

    void setMaxy(String maxy);

    String getSrs();

    void setSrs(String srs);

    Envelope getEnvelope();
    
    java.awt.Dimension getDimension();
    
    DescriptionURL getLogoURL();

    String getMaxScaleDenominator();

    String getMinScaleDenominator();

    DescriptionURL getDescriptionURL();

    String getAbstract();


/*******************************Layers functions*******************************/

    List<Layer> getLayers();

    void setLayers(List<Layer> layers);

    List<Layer> getHiddenLayers();

    List<Layer> getVisibleLayers();

    List<Layer> getQueryableLayers();

    List<Layer> getNoQueryableLayers();

    List<Layer> getGroupLayers(String nameOfGroup);
    
    List<Layer> getQueryableAndVisibleLayers();


/*********************************** Layer functions***************************/

    Layer getLayerFromId(String id);

    String getLayerOpacity(String id);

    void setLayerOpacity(String id, String value);

    boolean isLayerHidden(String id);

    void setLayerHidden(String id, boolean test);

    void addLayer(Layer layer);

    void removeLayerFromId(String layerId);

    void clearMapContextLayers();

/**************************** Layer Dimension functions ***********************/

    Dimension getLayerDimension(String layerId, String dimName);

    void setLayerDimension(String layerId, Dimension dim);

    String getLayerAttrDimension(String layerId, String dimName, String attrDimName);

    void setLayerAttrDimension(String layerId, String dimName, String attrDimName, String attrValue);


/*********************************** LayersId functions*******************************/

    /*
    * These functions are used to pass all the layers ids to the javascript
    */

    String getLayersId();

    String getLayersCompId();

    String getHiddenLayersId();

    String getVisibleLayersId();

    String getQueryableLayersId();

    String getNoQueryableLayersId();

    String getGroupLayersId(String nameOfGroup);


/*********************************** Servers functions*******************************/

    HashMap<String, Server> getWmsServers();

    void setWmsServers(HashMap<String, Server> servers);

    HashMap<String, Server> getWfsServers();

    void setWfsServers(HashMap<String, Server> servers);

    void save(ServletContext sc, String fileUrl);

}
