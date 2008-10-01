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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class DefaultContext extends AbstractModelBase implements Context {
    
    private String type;
    private String id;
    private String version;
    private String title;
    private String windowWidth;
    private String windowHeight;
    private String name;
    private String minx;
    private String miny;
    private String maxx;
    private String maxy;
    private String srs;
    private List<Layer> layers;
    private HashMap<String, Server> wmsServers;
    private HashMap<String, Server> wfsServers;
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(String windowWidth) {
        this.windowWidth = windowWidth;
    }

    public String getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(String windowHeight) {
        this.windowHeight = windowHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinx() {
        return minx;
    }

    public void setMinx(String minx) {
        this.minx = minx;
    }

    public String getMiny() {
        return miny;
    }

    public void setMiny(String miny) {
        this.miny = miny;
    }

    public String getMaxx() {
        return maxx;
    }

    public void setMaxx(String maxx) {
        this.maxx = maxx;
    }

    public String getMaxy() {
        return maxy;
    }

    public void setMaxy(String maxy) {
        this.maxy = maxy;
    }

    public String getSrs() {
        return srs;
    }

    public void setSrs(String srs) {
        this.srs = srs;
    }
    
    

    public HashMap<String, Server> getWmsServers() {
        return wmsServers;
    }
    
    public void setWmsServers(HashMap<String, Server> wmsServers) {
        this.wmsServers = wmsServers;
    }
    
    public HashMap<String, Server> getWfsServers() {
        return wfsServers;
    }

    public void setWfsServers(HashMap<String, Server> servers) {
        this.wfsServers = servers;
    }
    
    public String getWindowSize() {
        return getWindowWidth()+","+getWindowHeight();
    }

    public void setWindowSize(String width, String height) {
        setWindowWidth(width);
        setWindowHeight(height);
    }

    public String getBoundingBox() {
        return getMinx()+","+getMiny()+","+getMaxx()+","+getMaxy();
    }

    public void setBoundingBox(String minx, String miny, String maxx, String maxy) {
        setMinx(minx);
        setMiny(miny);
        setMaxx(maxx);
        setMaxy(maxy);
    }
    
/*******************************Layers functions*******************************/
    
    public List<Layer> getLayers() {
        return layers;
    }
    
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
    
    public List<Layer> getHiddenLayers() {
        List<Layer> layerList = getLayers();
        List<Layer> layersTmp = new ArrayList<Layer>();
        for(Layer tmp : layerList){
            if(tmp.isHidden())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    public List<Layer> getVisibleLayers() {
        List<Layer> layerList = getLayers();
        List<Layer> layersTmp = new ArrayList<Layer>();
        for(Layer tmp : layerList){
            if(!tmp.isHidden())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    public List<Layer> getQueryableLayers() {
        List<Layer> layerList = getLayers();
        List<Layer> layersTmp = new ArrayList<Layer>();
        for(Layer tmp : layerList){
            if(tmp.isQueryable())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    public List<Layer> getNoQueryableLayers() {
        List<Layer> layerList = getLayers();
        List<Layer> layersTmp = new ArrayList<Layer>();
        for(Layer tmp : layerList){
            if(!tmp.isQueryable())
                layersTmp.add(tmp);
        }
        return layersTmp;}

    public List<Layer> getGroupLayers(String groupName) {
        List<Layer> layerList = getLayers();
        List<Layer> layersTmp = new ArrayList<Layer>();
        for(Layer tmp : layerList){
            if(tmp.getGroup().equals(groupName))
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    
/*********************************** Layer functions***************************/
    
    public Layer getLayerFromId(String id) {
        List<Layer> layerList = getLayers();
        for(Layer tmp : layerList){
            if(tmp.getId().equals(id))
                return tmp;
        }
        return null;
    }
    
    public String getOpacity(String layerId) {
        Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            return tmp.getOpacity();    
        return "1";
    }
    
    public void setOpacity(String layerId, String value) {
        Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            tmp.setOpacity(value);    
    }
    
    public boolean isHidden(String layerId) {
        Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            return tmp.isHidden();    
        return true;
    }
  
    public void setHidden(String layerId, boolean vis) {
        Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            tmp.setHidden(vis);
    }
    
    public void addLayer(Layer layer) {
        getLayers().add(layer);
    }

    public void removeLayerFromId(String layerId) {
        Layer tmp = getLayerFromId(layerId);
        if( tmp == null)
            return;
        else
            getLayers().remove(tmp);
    }
    
/**************************** Layer Dimension functions ***********************/
    
    public Dimension getLayerDimension(String layerId, String dimName) {        
        List<Layer> layerList = getLayers();
        for( Layer tmp : layerList){
            if (tmp.getId().equals(layerId)) {
                return tmp.getDimension(dimName);
            }
        }
        return null;
    }
    
    public void setLayerDimension(String layerId, Dimension dim) {
        getLayerFromId(layerId).setDimension(dim);
    }
    
    public String getLayerAttrDimension(String layerId, String dimName, String attrName) {
         return getLayerDimension(layerId, dimName).getAttribute(attrName);
    }
    
    public void setLayerAttrDimension(String layerId, String dimName, String attrName, String value) {
        getLayerDimension(layerId, dimName).setAttribute(attrName, value);
    }
    
/*********************************** LayersId functions*******************************/ 
    
    public String getLayersId() {
        String layersId="";
        List<Layer> layerList = getLayers();
        for(Layer tmp : layerList){
            layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }
    public String getHiddenLayersId() {
        String layersId="";
        List<Layer> layerList = getHiddenLayers();
        for(Layer tmp : layerList){
                layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }

    public String getVisibleLayersId() {
        String layersId="";
        List<Layer> layerList = getVisibleLayers();
        for(Layer tmp : layerList){
                layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }

    public String getQueryableLayersId() {
        String layersId="";
        List<Layer> layerList = getQueryableLayers();
        for(Layer tmp : layerList){
                layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }

    public String getNoQueryableLayersId() {
        String layersId="";
        List<Layer> layerList = getNoQueryableLayers();
        for(Layer tmp : layerList){
            layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }

    public String getGroupLayersId(String groupName) {
        String layersId="";
        List<Layer> layerList = getGroupLayers(groupName);
        for(Layer tmp : layerList){
            layersId=layersId+","+tmp.getId();
        }
        if(layersId.length() > 0)
            return layersId.substring(1,layersId.length());
        else
            return null;
    }
   
    
  
    public String save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   


}