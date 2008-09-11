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

package org.mapfaces.models;

import java.util.HashMap;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapLayer;

/**
 *
 * @author Olivier Terral.
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
