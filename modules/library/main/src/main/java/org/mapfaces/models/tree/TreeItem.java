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
package org.mapfaces.models.tree;

import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;

import org.geotools.geometry.jts.ReferencedEnvelope;

import org.mapfaces.models.Context;
import org.mapfaces.models.DescriptionURL;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;

/**
 * @author Kevin Delfour (Geomatys)
 */
public class TreeItem implements Layer, Context {

    Object object;
    String title, name;


    public TreeItem(final Object obj) {
        object = obj;
        if (obj != null) {
            if (obj instanceof Layer) {
                // do something
            } else if (obj instanceof Context) {
                //do something
            }
        }
    }

    public TreeItem(String title, String name){
        setTitle(title);
        setName(name);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUserValue(final String string, final String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDataUrl() {
        if (object == null || !(object instanceof Context)) {
            return "";
        }
        //@TODO by reflection.
        return ((Layer) object).getDataUrl();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDepth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public HashMap<String, Dimension> getDimensionList() {
        if (object == null || object instanceof Context) {
            return null;
        }
        if (object instanceof Layer) {
            return ((Layer) object).getDimensionList();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getGroup() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getGroup();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getId() {
        if (object == null) {
            return "";
        }
        if (object instanceof Context) {
            return ((Context) object).getId();
        }
        if (object instanceof Layer) {
            return ((Layer) object).getId();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getInlineGeometry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxFeatures() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxScaleDenominator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMetadataUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMinScaleDenominator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getName() {
        if (object == null) {
            return name;
        }
        if (object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getName();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOpacity() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getOpacity();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOutputFormat() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getOutputFormat();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isQueryable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferencedEnvelope getRefEnv() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResZ() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getResponseCRS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getTitle() {
        if (object == null) {
            return title;
        }
        if (object instanceof Context) {
            return ((Context) object).getTitle();
        }
        if (object instanceof Layer) {
            return ((Layer) object).getTitle();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isHidden() {
        if (object == null) {
            return false;
        }
        if (object instanceof Context) {
            return ((Context) object).isHidden(null);
        }
        if (object instanceof Layer) {
            return ((Layer) object).isHidden();
        }
        return false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDataUrl(String dataUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDepth(String depth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setGroup(String group) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHidden(boolean hidden) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setInlineGeometry(String inlineGeometry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxFeatures(String maxFeatures) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxScaleDenominator(String maxScaleDenominator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMetadataUrl(String metadataUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMinScaleDenominator(String minScaleDenominator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setOpacity(String opacity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setOutputFormat(String outputFormat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setQueryable(boolean queryable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setRefEnv(ReferencedEnvelope refEnv) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResX(String resX) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResY(String resY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResZ(String resZ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setResponseCRS(String responseCRS) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setServer(Server server) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLegendUrl() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getLegendUrl();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLegendUrl(String legendUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getElevations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getTimes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setElevations(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueElevation() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getUserValueElevation();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueDimension(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValueDimRange() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getUserValueDimRange();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getElevation() {
        if (object == null || object instanceof Context) {
            return null;
        }
        if (object instanceof Layer) {
            return ((Layer) object).getElevation();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getTime() {
        if (object == null || object instanceof Context) {
            return null;
        }
        if (object instanceof Layer) {
            return ((Layer) object).getTime();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getDimension(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAttrDimension(String name, String attrName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimension(String name, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimension(Dimension dim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAttrDimension(String name, String attrName, String attrValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDimensionList(HashMap<String, Dimension> dimensionList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getStyles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSldBody() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setStyles(String styles) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSld(String sld) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSldBody(String sldBody) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setVersion(String version) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowSize(String width, String height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowWidth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowWidth(String windowWidth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowHeight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowHeight(String windowHeight) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setBoundingBox(String minx, String miny, String maxx, String maxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMinx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMinx(String minx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMiny() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMiny(String miny) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxx(String maxx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxy(String maxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSrs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSrs(String srs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayers(List<Layer> layers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getHiddenLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getVisibleLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getQueryableLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getNoQueryableLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getGroupLayers(String nameOfGroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Layer getLayerFromId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOpacity(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setOpacity(String id, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isHidden(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHidden(String id, boolean test) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addLayer(Layer layer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeLayerFromId(String layerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getLayerDimension(String layerId, String dimName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerDimension(String layerId, Dimension dim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayerAttrDimension(String layerId, String dimName, String attrDimName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerAttrDimension(String layerId, String dimName, String attrDimName, String attrValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHiddenLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVisibleLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getQueryableLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getNoQueryableLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getGroupLayersId(String nameOfGroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public HashMap<String, Server> getWmsServers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWmsServers(HashMap<String, Server> servers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public HashMap<String, Server> getWfsServers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWfsServers(HashMap<String, Server> servers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void save(ServletContext sc, String fileUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DescriptionURL getLogoURL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DescriptionURL getDescriptionURL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAbstract() {
        throw new UnsupportedOperationException("Not supported yet.");
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
       if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getCompId();
        }
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setCompId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayersCompId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Feature> getFeatures() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFeatures(List<Feature> features) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getImage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setImage(String image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSize(int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getRotation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRotation(double rotation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
