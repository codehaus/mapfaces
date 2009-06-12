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

import java.io.Serializable;
import java.util.HashMap;
import org.opengis.geometry.Envelope;
import org.opengis.util.Cloneable;

/**
 * @author Olivier Terral (Geomatys).
 */
public interface Layer extends Serializable, Cloneable {

//    public Parameter getParameterList();
    
    /**
     * This method returns a hashmap that contains all values of tokens in the group property when group1\group2\... are the map keys.
     * @return
     */
    HashMap<String, Serializable> getMapGroupHierarchiesValues();
    
    void setMapGroupHierarchiesValues(HashMap<String, Serializable> map);

    DescriptionURL getMetadataURL();

    void setUserValue(String string, String value);

    String getDataUrl();

    String getDepth();

    HashMap<String, Dimension> getDimensionList();

    String getGroup();
    
    int getGroupId();

    String getId();

    String getInlineGeometry();

    String getMaxFeatures();

    Double getMaxScaleDenominator();

    String getMetadataUrl();

    Double getMinScaleDenominator();

    String getName();

    String getOpacity();

    String getOutputFormat();

    boolean isQueryable();

    Envelope getRefEnv();

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

    void setGroup(String group);
    
    void setGroupId(int group);    

    void setHidden(boolean hidden);
    
    void setHidden(boolean hidden, String SLDidentifier);

    void setId(String id);

    void setInlineGeometry(String inlineGeometry);

    void setMaxFeatures(String maxFeatures);

    void setMaxScaleDenominator(Double maxScaleDenominator);

    void setMetadataUrl(String metadataUrl);

    void setMinScaleDenominator(Double minScaleDenominator);

    void setName(String name);

    void setOpacity(String opacity);

    void setOutputFormat(String outputFormat);

    void setQueryable(boolean queryable);

    void setRefEnv(Envelope refEnv);

    void setResX(String resX);

    void setResY(String resY);

    void setResZ(String resZ);

    void setResponseCRS(String responseCRS);

    void setTitle(String title);

    LayerType getType();

    void setType(LayerType type);

    String getLegendUrl();

    void setLegendUrl(String legendUrl);

    /*
     * Getters and setters for dimensions parameters
     * */
    String getElevations();

    String getTimes();

    void setElevations(String value);

    String getUserValueElevation();

    String getUserValueTime();

    String getUserValueDimension(String name);

    String getUserValueDimRange();

    Dimension getElevation();

    Dimension getTime();

    Dimension getDimension(String name);

    String getAttrDimension(String name, String attrName);

    void setDimension(String name, String value);

    void setDimension(Dimension dim);

    void setAttrDimension(String name, String attrName, String attrValue);

    void setDimensionList(HashMap<String, Dimension> dimensionList);

    /*
     * properties needed by JSF
     * */

    String getCompId();

    void setCompId(String id);
    
   
    
}