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

/**
 *
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */

import java.util.HashMap;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapLayer;
import org.geotools.map.WMSMapLayer;

public class DefaultLayer implements Layer {
    
    private static final long serialVersionUID = -1378795978545632191L;

    //@TODO this pproperty will be moved.
    private static MapLayer mapLayer;
    private boolean edit;
    private boolean lock;
    private int groupId;
    /**
     * bbox
     */
    private  ReferencedEnvelope refEnv;
    private String type;
    private String id;
    private String name;
    private boolean queryable;
    private String group;
    private String title;
    private boolean hidden;
    private String opacity;
    private String outputFormat;
    private String minScaleDenominator;
    private String maxScaleDenominator;
    //private List<Style> style;
    private HashMap<String, Dimension> dimensionList;
    // private HashMap<String,Parameter> parameterList;
    //private Server server;
    private String maxFeatures;
    private String inlineGeometry;
    private String dataUrl;
    private String metadataUrl;
    private String responseCRS;
    private String depth;
    private String resX;
    private String resY;
    private String resZ;

    public DefaultLayer(boolean edit, boolean lock, int groupId) {
        this.edit = edit;
        this.lock = lock;
        this.groupId = groupId;
    }

    DefaultLayer() {
    }

    public ReferencedEnvelope getRefEnv() {
        return refEnv;
    }

    public void setRefEnv(ReferencedEnvelope refEnv) {
        this.refEnv = refEnv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        mapLayer.setName(name);
    }

    public boolean getQueryable() {
        return queryable;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getOpacity() {
        return opacity;
    }

    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
        if (type.contains("WMS")) {
            ((WMSMapLayer) mapLayer).setOutputFormat(outputFormat);
        }
    }

    public String getMinScaleDenominator() {
        return minScaleDenominator;
    }

    public void setMinScaleDenominator(String minScaleDenominator) {
        this.minScaleDenominator = minScaleDenominator;
    }

    public String getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    public void setMaxScaleDenominator(String maxScaleDenominator) {
        this.maxScaleDenominator = maxScaleDenominator;
    }

//    public List<Style> getStyle() {
//        return style;
//    }
//
//    public void setStyle(List<Style> style) {
//        this.style = style;
//    }
    public HashMap<String, Dimension> getDimensionList() {
        return dimensionList;
    }

    @Override
    public void setDimensionList(HashMap<String, Dimension> dimensionList) {

        if (this.dimensionList == null) {
            this.dimensionList = new HashMap<String, Dimension>(dimensionList);
        }
        for (String tmp : dimensionList.keySet()) {
            if (type.contains("WMS")) {
                ((WMSMapLayer) mapLayer).setDimension(tmp, dimensionList.get(tmp).getUserValue());
            }
            System.out.println(tmp + " : " + this.dimensionList.get(tmp));
        }
    }

    /*public Server getServer() {
    return server;
    }
    
    public void setServer(Server server) {
    this.server = server;
    }*/
    public String getMaxFeatures() {
        return maxFeatures;
    }

    public void setMaxFeatures(String maxFeatures) {
        this.maxFeatures = maxFeatures;
    }

    public String getInlineGeometry() {
        return inlineGeometry;
    }

    public void setInlineGeometry(String inlineGeometry) {
        this.inlineGeometry = inlineGeometry;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getMetadataUrl() {
        return metadataUrl;
    }

    public void setMetadataUrl(String metadataUrl) {
        this.metadataUrl = metadataUrl;
    }

    public String getResponseCRS() {
        return responseCRS;
    }

    public void setResponseCRS(String responseCRS) {
        this.responseCRS = responseCRS;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getResX() {
        return resX;
    }

    public void setResX(String resX) {
        this.resX = resX;
    }

    public String getResY() {
        return resY;
    }

    public void setResY(String resY) {
        this.resY = resY;
    }

    public String getResZ() {
        return resZ;
    }

    public void setResZ(String resZ) {
        this.resZ = resZ;
    }

    public MapLayer getMapLayer() {
        return mapLayer;
    }

    public void setMapLayer(MapLayer mapLayer) {
        this.mapLayer = mapLayer;
    }

    @Override
    public void get(MapLayer mapLayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dimension getElevation() {
        if (dimensionList != null) {
            return dimensionList.get("elevation");
        }
        return null;
    }

    @Override
    public Dimension getTime() {
        if (dimensionList != null) {
            return dimensionList.get("time");
        }
        return null;
    }

    @Override
    public Dimension getDimension(String name) {
        if (dimensionList != null) {
            return dimensionList.get(name);
        }
        return null;
    }

    @Override
    public String getUserValueElevation() {
        if (getElevation() != null) {
            return getElevation().getUserValue();
        }
        return null;
    }

    @Override
    public String getElevations() {
        return getElevation().getValue();
    }

    @Override
    public String getTimes() {
        return getTime().getValue();
    }

    /**
     * 
     * 
     * 
     * @param name
     * @param value
     */
    public void setDimension(String name, Dimension dim) {
        if (dimensionList == null) {
            dimensionList = new HashMap<String, Dimension>();
        }
        if (dimensionList.get(name) != null) {
            dimensionList.remove(name);
        }
        dimensionList.put(name, dim);
        if (type.contains("WMS")) {
            ((WMSMapLayer) mapLayer).setDimension(name, dim.getUserValue());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    /*
     * Set the User Value of a dimension specify with name
     * */

    @Override
    public void setUserValue(String dimName, String value) {
        if (dimensionList == null) {
            dimensionList = new HashMap<String, Dimension>();
        }
        if (dimensionList.get(dimName) != null) {
            dimensionList.get(dimName).setUserValue(value);
        }
        if (type.contains("WMS")) {
            ((WMSMapLayer) mapLayer).setDimension(dimName, dimensionList.get(dimName).getUserValue());
        }
    }

    @Override
    public void setDimension(String name, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String setElevations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUserValueTime() {
        if (getElevation() != null) {
            return getTime().getUserValue();
        }
        return null;
    }

    public String getUserValueDimension(String name) {
        if (dimensionList != null) {
            return dimensionList.get(name).getUserValue();
        }
        return null;
    }
}
