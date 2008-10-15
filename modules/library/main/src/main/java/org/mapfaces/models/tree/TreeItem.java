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
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.mapfaces.models.Context;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;

/**
 *
 * @author Mehdi Sidhoum
 */
public class TreeItem implements Layer, Context {

    Object object;

    public TreeItem(Object obj) {
        object = obj;
        if (obj != null) {
            if (obj instanceof Layer) {
                // do something
            } else if (obj instanceof Context) {
                //do something
            }
        }
    }

    @Override
    public void setUserValue(String string, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDataUrl() {
        if (object == null || !(object instanceof Context)) {
            return "";
        }
        //@TODO by reflection.
        return ((Layer) object).getDataUrl();
    }

    @Override
    public String getDepth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    @Override
    public String getInlineGeometry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMaxFeatures() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMaxScaleDenominator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMetadataUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMinScaleDenominator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        if (object == null || object instanceof Context) {
            return "";
        }
        if (object instanceof Layer) {
            return ((Layer) object).getName();
        }
        return "";
    }

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

    @Override
    public boolean isQueryable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReferencedEnvelope getRefEnv() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getResX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getResY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getResZ() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getResponseCRS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTitle() {
        if (object == null) {
            return "";
        }
        if (object instanceof Context) {
            return ((Context) object).getTitle();
        }
        if (object instanceof Layer) {
            return ((Layer) object).getTitle();
        }
        return "";
    }

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

    @Override
    public void setDataUrl(String dataUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDepth(String depth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setGroup(String group) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHidden(boolean hidden) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setInlineGeometry(String inlineGeometry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxFeatures(String maxFeatures) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxScaleDenominator(String maxScaleDenominator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMetadataUrl(String metadataUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMinScaleDenominator(String minScaleDenominator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOpacity(String opacity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOutputFormat(String outputFormat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setQueryable(boolean queryable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRefEnv(ReferencedEnvelope refEnv) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setResX(String resX) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setResY(String resY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setResZ(String resZ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setResponseCRS(String responseCRS) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setServer(Server server) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    @Override
    public void setLegendUrl(String legendUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getElevations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTimes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setElevations(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    @Override
    public String getUserValueTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUserValueDimension(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    @Override
    public Dimension getDimension(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAttrDimension(String name, String attrName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDimension(String name, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDimension(Dimension dim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAttrDimension(String name, String attrName, String attrValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDimensionList(HashMap<String, Dimension> dimensionList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getStyles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSldBody() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setStyles(String styles) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSld(String sld) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSldBody(String sldBody) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setVersion(String version) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWindowSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWindowSize(String width, String height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWindowWidth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWindowWidth(String windowWidth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWindowHeight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWindowHeight(String windowHeight) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBoundingBox(String minx, String miny, String maxx, String maxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMinx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMinx(String minx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMiny() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMiny(String miny) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMaxx() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxx(String maxx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMaxy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxy(String maxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSrs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSrs(String srs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLayers(List<Layer> layers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getHiddenLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getVisibleLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getQueryableLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getNoQueryableLayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Layer> getGroupLayers(String nameOfGroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Layer getLayerFromId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getOpacity(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOpacity(String id, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isHidden(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHidden(String id, boolean test) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addLayer(Layer layer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeLayerFromId(String layerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dimension getLayerDimension(String layerId, String dimName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLayerDimension(String layerId, Dimension dim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLayerAttrDimension(String layerId, String dimName, String attrDimName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLayerAttrDimension(String layerId, String dimName, String attrDimName, String attrValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getHiddenLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getVisibleLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getQueryableLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNoQueryableLayersId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getGroupLayersId(String nameOfGroup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, Server> getWmsServers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWmsServers(HashMap<String, Server> servers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, Server> getWfsServers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWfsServers(HashMap<String, Server> servers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
