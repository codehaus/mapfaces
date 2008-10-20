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

public class DefaultLayer implements Layer {

    private static final long serialVersionUID = 7526471155622776147L;
    //static private DefaultLayer singleton = null;
    private boolean edit;
    private boolean lock;
    private int groupId;
    /**
     * bbox
     */
    private ReferencedEnvelope refEnv;
    private String type;
    private String id;
    private String name;
    private boolean queryable;
    private String group;
    private String title;
    private boolean hidden;
    private String opacity;
    private String outputFormat;
    private String legendUrl;
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
    private String styles;
    private String sldBody;
    private String sld;
    private Server server;

    public DefaultLayer(boolean edit, boolean lock, int groupId) {
        this.edit = edit;
        this.lock = lock;
        this.groupId = groupId;
    }

    /**
     * Used only for the serialization.
     */
    public DefaultLayer() {
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
    }

    public boolean isQueryable() {
        return queryable;
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

    @Override
    public String getUserValueDimRange() {
        if (getDimension("dim_range") != null) {
            return getDimension("dim_range").getUserValue();
        }
        return null;
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
    }

    @Override
    public void setDimension(String name, String value) {
        getDimension(name).setValue(value);
    }

    public void setDimension(Dimension dim) {
        HashMap<String, Dimension> dimList = getDimensionList();
        if (dimList != null) {
            Dimension oldDim = dimList.get(dim.getName());
            if (oldDim != null) {
                dimList.remove(oldDim);
            }
            dimList.put(dim.getName(), dim);
        }
    }

    public String getAttrDimension(String name, String attrName) {
        return getDimension(name).getAttribute(attrName);
    }

    public void setAttrDimension(String name, String attrName, String attrValue) {
        getDimension(name).setAttribute(attrName, attrValue);
    }

    @Override
    public void setElevations(String value) {
        getDimension("elevation").setValue(value);
    }

    @Override
    public String getUserValueTime() {
        if (getTime() != null) {
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

    public String getLegendUrl() {
        return legendUrl;
    }

    public void setLegendUrl(String legendUrl) {
        this.legendUrl = legendUrl;
    }

//    Object writeReplace() throws ObjectStreamException, CloneNotSupportedException {
//        MapLayer maplayer_ = getMapLayer();
//        DefaultLayer tmp = getSingleton();
//        tmp.setMapLayer(maplayer_);
//
//        DefaultLayer l = this;
//
//        return l;
//    }
//
//    Object readResolve() throws ObjectStreamException, CloneNotSupportedException {
//        DefaultLayer l = this;
//
//        MapLayer maplayer_ = getSingleton().getMapLayer();
//
//        l.setMapLayer(maplayer_);
//
//        return l;
//    }
//
//    static public synchronized DefaultLayer getSingleton() {
//        if (singleton == null) {
//            singleton = new DefaultLayer();
//        }
//        return singleton;
//    }
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getStyles() {
        return this.styles;
    }

    public String getSld() {
        return this.sld;
    }

    public String getSldBody() {
        return this.sldBody;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public void setSld(String sld) {
        this.sld = sld;
    }

    public void setSldBody(String sldBody) {
        this.sldBody = sldBody;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return this.server;
    }

    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }
}