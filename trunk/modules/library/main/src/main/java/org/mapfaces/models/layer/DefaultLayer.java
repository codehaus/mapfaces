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

package org.mapfaces.models.layer;


import java.io.Serializable;
import java.util.HashMap;

import org.mapfaces.models.DescriptionURL;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.LayerType;
import org.opengis.geometry.Envelope;

public class DefaultLayer implements Layer {

    private static final long serialVersionUID = 7526471155622776147L;
    //static private DefaultLayer singleton = null;
    private boolean edit;
    private boolean lock;
    private int groupId;
    /**
     * bbox
     */
    private Envelope refEnv;
    private LayerType type = LayerType.DEFAULT;
    private String id;
    private String name;
    private boolean queryable;
    private String group;
    private String title;
    private boolean hidden;
    private String opacity;
    private String outputFormat;
    private String legendUrl;
    private Double minScaleDenominator;
    private Double maxScaleDenominator;
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
    private HashMap<String, Serializable> MapGroupHierarchiesValues;

    /*
     * JSF properties we need to know the id of its component
     * */
    private String compId;

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

    /**
     * {@inheritDoc }
     */
    @Override
    public Envelope getRefEnv() {
        return refEnv;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setRefEnv(final Envelope refEnv) {
        this.refEnv = refEnv;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isQueryable() {
        return queryable;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getGroup() {
        return group;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isHidden() {
        return hidden;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void setHidden(final boolean hidden, final String identifierSLD) {
        this.hidden = hidden;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOpacity() {
        return opacity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setOpacity(final String opacity) {
        this.opacity = opacity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOutputFormat() {
        return outputFormat;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setOutputFormat(final String outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Double getMinScaleDenominator() {
        return minScaleDenominator;
    }

    /**
     * {@inheritDoc }
     */
    public void setMinScaleDenominator(final Double minScaleDenominator) {
        this.minScaleDenominator = minScaleDenominator;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Double getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    /**
     * {@inheritDoc }
     */
    public void setMaxScaleDenominator(final Double maxScaleDenominator) {
        this.maxScaleDenominator = maxScaleDenominator;
    }

//    public List<Style> getStyle() {
//        return style;
//    }
//
//    public void setStyle(List<Style> style) {
//        this.style = style;
//    }

    /**
     * {@inheritDoc }
     */
    @Override
    public HashMap<String, Dimension> getDimensionList() {
        return dimensionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimensionList(final HashMap<String, Dimension> dimensionList) {
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

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxFeatures() {
        return maxFeatures;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxFeatures(final String maxFeatures) {
        this.maxFeatures = maxFeatures;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getInlineGeometry() {
        return inlineGeometry;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setInlineGeometry(final String inlineGeometry) {
        this.inlineGeometry = inlineGeometry;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDataUrl() {
        return dataUrl;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDataUrl(final String dataUrl) {
        this.dataUrl = dataUrl;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMetadataUrl() {
        return metadataUrl;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMetadataUrl(final String metadataUrl) {
        this.metadataUrl = metadataUrl;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResponseCRS() {
        return responseCRS;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResponseCRS(final String responseCRS) {
        this.responseCRS = responseCRS;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDepth() {
        return depth;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDepth(final String depth) {
        this.depth = depth;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResX() {
        return resX;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResX(final String resX) {
        this.resX = resX;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResY() {
        return resY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResY(final String resY) {
        this.resY = resY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResZ() {
        return resZ;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResZ(final String resZ) {
        this.resZ = resZ;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getElevation() {
        if (dimensionList != null) {
            return dimensionList.get("elevation");
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getTime() {
        if (dimensionList != null) {
            return dimensionList.get("time");
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getDimension(final String name) {
        if (dimensionList != null) {
            return dimensionList.get(name);
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueElevation() {
        if (getElevation() != null) {
            return getElevation().getUserValue();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getElevations() {
        return getElevation().getValue();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getTimes() {
        return getTime().getValue();
    }

    /**
     * {@inheritDoc }
     */
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
    public void setDimension(final String name, final Dimension dim) {
        if (dimensionList == null) {
            dimensionList = new HashMap<String, Dimension>();
        }
        if (dimensionList.get(name) != null) {
            dimensionList.remove(name);
        }
        dimensionList.put(name, dim);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public LayerType getType() {
        return type;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setType(final LayerType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUserValue(final String dimName, final String value) {
        if (dimensionList == null) {
            dimensionList = new HashMap<String, Dimension>();
        }
        if (dimensionList.get(dimName) != null) {
            dimensionList.get(dimName).setUserValue(value);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimension(final String name, final String value) {
        getDimension(name).setValue(value);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimension(final Dimension dim) {
        HashMap<String, Dimension> dimList = getDimensionList();
        if (dimList != null) {
            Dimension oldDim = dimList.get(dim.getName());
            if (oldDim != null) {
                dimList.remove(oldDim);
            }
            dimList.put(dim.getName(), dim);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAttrDimension(final String name, final String attrName) {
        return getDimension(name).getAttribute(attrName);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAttrDimension(final String name, final String attrName, final String attrValue) {
        getDimension(name).setAttribute(attrName, attrValue);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setElevations(final String value) {
        getDimension("elevation").setValue(value);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueTime() {
        if (getTime() != null) {
            return getTime().getUserValue();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueDimension(final String name) {
        if (dimensionList != null) {
            return dimensionList.get(name).getUserValue();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLegendUrl() {
        return legendUrl;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLegendUrl(final String legendUrl) {
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

    public void setEdit(final boolean edit) {
        this.edit = edit;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(final boolean lock) {
        this.lock = lock;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(final int groupId) {
        this.groupId = groupId;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void setQueryable(final boolean queryable) {
        this.queryable = queryable;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DescriptionURL getMetadataURL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getCompId() {
        return compId;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setCompId(final String compId) {
        this.compId = compId;
    }

    @Override
    public HashMap<String, Serializable> getMapGroupHierarchiesValues() {
        return MapGroupHierarchiesValues;
    }

    @Override
    public void setMapGroupHierarchiesValues(HashMap<String, Serializable> map) {
        this.MapGroupHierarchiesValues = map;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public DefaultLayer clone() {
        DefaultLayer object = null;
        try {
            object =  (DefaultLayer) super.clone();
        } catch(CloneNotSupportedException cnse) {
		cnse.printStackTrace(System.err);
        }
        return object;
    }

}