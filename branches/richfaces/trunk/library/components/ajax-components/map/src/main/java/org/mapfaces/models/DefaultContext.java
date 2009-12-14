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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

import org.geotoolkit.geometry.Envelope2D;
import org.geotoolkit.referencing.CRS;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.MapContextLayer;
import org.mapfaces.util.XMLContextUtilities;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * This is an implementation of Context as a model of ows context.
 *
 * @author Olivier Terral (Geomatys)
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.2
 */
public class DefaultContext extends AbstractModelBase implements Context {


    private static final long serialVersionUID = 7526471155622776147L;
    private static final Logger LOGGER = Logger.getLogger(DefaultContext.class.getName());


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

    /**
     * This list should be a synchronized instance to avoid ConcurentModificationException
     */
    private List<Layer> layers = new CopyOnWriteArrayList<Layer>();


    private Map<String, Server> wmsServers;
    private Map<String, Server> wfsServers;


    /**
     * {@inheritDoc }
     */
    @Override
    public String getContextType() {
        return type;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setType(String type) {
        this.type = type;
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
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setVersion(String version) {
        this.version = version;
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
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowWidth() {
        return windowWidth;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowWidth(String windowWidth) {
        this.windowWidth = windowWidth;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowHeight() {
        return windowHeight;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowHeight(String windowHeight) {
        this.windowHeight = windowHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMinx() {
        return minx;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMinx(String minx) {
        this.minx = minx;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMiny() {
        return miny;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMiny(String miny) {
        this.miny = miny;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxx() {
        return maxx;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxx(String maxx) {
        this.maxx = maxx;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxy() {
        return maxy;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMaxy(String maxy) {
        this.maxy = maxy;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSrs() {
        return srs;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSrs(String srs) {
        this.srs = srs;
    }

    @Override
    public Envelope getEnvelope(){
        CoordinateReferenceSystem crs = null;
        try {
            crs = CRS.decode(this.srs);
        } catch (NoSuchAuthorityCodeException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        final double dminx = Double.valueOf(this.minx);
        final double dmaxx = Double.valueOf(this.maxx);
        final double dminy = Double.valueOf(this.miny);
        final double dmaxy = Double.valueOf(this.maxy);

        return new Envelope2D(crs, dminx, dminy, dmaxx-dminx, dmaxy-dminy);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Map<String, Server> getWmsServers() {
        return wmsServers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWmsServers(Map<String, Server> wmsServers) {
        this.wmsServers = wmsServers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Map<String, Server> getWfsServers() {
        return wfsServers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWfsServers(Map<String, Server> servers) {
        this.wfsServers = servers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getWindowSize() {
        return getWindowWidth()+","+getWindowHeight();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setWindowSize(String width, String height) {
        setWindowWidth(width);
        setWindowHeight(height);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getBoundingBox() {
        return getMinx()+","+getMiny()+","+getMaxx()+","+getMaxy();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setBoundingBox(String minx, String miny, String maxx, String maxy) {
        setMinx(minx);
        setMiny(miny);
        setMaxx(maxx);
        setMaxy(maxy);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DescriptionURL getLogoURL() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMaxScaleDenominator() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getMinScaleDenominator() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DescriptionURL getDescriptionURL() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAbstract() {
        return "";
    }
/*******************************Layers functions*******************************/

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getHiddenLayers() {
        final List<Layer> layersTmp = new ArrayList<Layer>();
        for(final Layer tmp :  getLayers()){
            if(tmp.isHidden())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getVisibleLayers() {
        final List<Layer> layersTmp = new ArrayList<Layer>();
        for(final Layer tmp : getLayers()){
            if(!tmp.isHidden())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getQueryableLayers() {
        final List<Layer> layersTmp = new ArrayList<Layer>();
        for(final Layer tmp : getLayers()){
            if(tmp.isQueryable())
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getNoQueryableLayers() {
        final List<Layer> layersTmp = new ArrayList<Layer>();
        for(final Layer tmp : getLayers()){
            if(!tmp.isQueryable())
                layersTmp.add(tmp);
        }
        return layersTmp;}

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Layer> getGroupLayers(final String groupName) {
        final List<Layer> layersTmp = new ArrayList<Layer>();
        for(final Layer tmp : getLayers()){
            if(tmp.getGroup().equals(groupName))
                layersTmp.add(tmp);
        }
        return layersTmp;
    }

    @Override
    public List<Layer> getQueryableAndVisibleLayers() {
        final List<Layer> queryableLayers = getQueryableLayers();
        final List<Layer> returnLayers = new ArrayList<Layer>();
        for (Layer layer : queryableLayers) {
            if (!layer.isHidden()) {
                returnLayers.add(layer);
            }
        }
        return returnLayers;
    }


/*********************************** Layer functions***************************/

    /**
     * {@inheritDoc }
     */
    @Override
    public Layer getLayerFromId(final String id) {
        for(final Layer tmp : getLayers()){
            if(tmp.getId().equals(id))
                return tmp;
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayerOpacity(final String layerId) {
        final Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            return tmp.getOpacity();
        return "1";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerOpacity(final String layerId, final String value) {
        final Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            tmp.setOpacity(value);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isLayerHidden(final String layerId) {
        final Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            return tmp.isHidden();
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerHidden(final String layerId, final boolean vis) {
        final Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            tmp.setHidden(vis);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addLayer(final Layer layer) {
        getLayers().add(layer);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeLayerFromId(final String layerId) {
        final Layer tmp = getLayerFromId(layerId);
        if( tmp == null)
            return;
        else
            getLayers().remove(tmp);
    }

/**************************** Layer Dimension functions ***********************/

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension getLayerDimension(final String layerId, final String dimName) {
        for(final Layer tmp : getLayers()){
            if (tmp.getId().equals(layerId)) {
                return tmp.getDimension(dimName);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerDimension(final String layerId, final Dimension dim) {
        getLayerFromId(layerId).setDimension(dim);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayerAttrDimension(final String layerId, final String dimName, final String attrName) {
         return getLayerDimension(layerId, dimName).getAttribute(attrName);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayerAttrDimension(final String layerId, final String dimName, final String attrName, final String value) {
        getLayerDimension(layerId, dimName).setAttribute(attrName, value);
    }

/*********************************** LayersId functions*******************************/

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayersId() {
        return toString(getLayers());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getLayersCompId() {
        final StringBuilder layersId = new StringBuilder();
        for(final Layer tmp : getLayers()){
            layersId.append(tmp.getCompId()).append(",");
        }
        if(layersId.length() > 0)
            return layersId.substring(0,layersId.length()-1);
        else
            return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHiddenLayersId() {
        return toString(getHiddenLayers());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVisibleLayersId() {
        return toString(getVisibleLayers());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getQueryableLayersId() {
        return toString(getQueryableLayers());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getNoQueryableLayersId() {
        return toString(getNoQueryableLayers());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getGroupLayersId(final String groupName) {
        return toString(getGroupLayers(groupName));
    }

    private String toString(final List<Layer> layers){
        final StringBuilder layersId = new StringBuilder();
        for(final Layer tmp : layers){
            layersId.append(tmp.getId()).append(',');
        }
        if(layersId.length() > 0)
            return layersId.substring(0,layersId.length()-1);
        else
            return null;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void save(final String fileName) {
        try {
            File output;
            if (fileName == null) {
                final File dstDir = new File(System.getProperty("user.home") + "/tmp");
                if (!dstDir.exists()) {
                    final boolean tmp = dstDir.mkdir();
                    if (tmp)
                        LOGGER.log(Level.FINE, "The directory tmp has been created !!!");
                    else
                        LOGGER.log(Level.FINE, "The directory tmp hasn't been created but it should !!!");

                }
                output = File.createTempFile("owc", ".xml", dstDir);
            } else {
                output = new File(System.getProperty("user.home") + "/tmp" + "/" + fileName);
            }
            XMLContextUtilities.writeContext(this, output);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public java.awt.Dimension getDimension() {
        return new java.awt.Dimension(
                Integer.parseInt(this.getWindowWidth()),
                Integer.parseInt(this.getWindowHeight()));
    }

    /**
     * Removes all MapContextLayer contained in the context object. 
     * it is used to clean the context for each event rerender on the mappane.
     */
    @Override
    public void clearMapContextLayers() {
        final List<Layer> listtoremove = new ArrayList<Layer>();
        for (Layer layer : this.getLayers()) {
            if (layer instanceof  MapContextLayer) {
                listtoremove.add(layer);
            }
        }
        for (Layer layer : listtoremove) {
                removeLayerFromId(layer.getId());
        }
    }

    /**
     * Removes all FeatureLayer contained in the context object.
     */
    public void clearFeatureLayers() {
        final List<Layer> listtoremove = new ArrayList<Layer>();
        for (Layer layer : this.getLayers()) {
            if (layer instanceof  FeatureLayer) {
                listtoremove.add(layer);
            }
        }
        for (Layer layer : listtoremove) {
                removeLayerFromId(layer.getId());
        }
    }

    /**
     * Define the Refresh value for a specified Layer.
     * @param layerId Id of the layer.
     * @param value Value of Refresh attribute.
     */
    @Override
    public void setLayerRefresh(final String layerId, final boolean value) {
        final Layer tmp = getLayerFromId(layerId);
        if(tmp != null)
            tmp.setRefresh(value);
    }

    /**
     * Return
     * @param layerId
     * @return
     */
    @Override
    public boolean getLayerRefresh(final String layerId) {
        final Layer tmp = getLayerFromId(layerId);
        final boolean result;
        if(tmp != null) result = tmp.getRefresh();
        else result = false;

        return result;
    }
}
