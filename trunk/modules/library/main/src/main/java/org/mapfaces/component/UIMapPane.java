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

package org.mapfaces.component;

import java.util.List;
import javax.faces.context.FacesContext;
import org.geotools.map.MapContext;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIMapPane extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.MapPane";
    private MapContext defaultMapContext;
    /**
     * for untiled wms layers: how many times should the map image be
     * larger than the visible map. Large values mean slow loading, small
     * ones mean many reloads when panning. Recommended values: 1-3,
     * default is 2.
     */
    private Integer imageBuffer = 1;
    /**
     * Number of layers that are currently being loaded
     */
    private int loadingLayers = 0;
    /**
     * For tiled wms layers: how many rows of tiles should be preloaded
     * outside the visible map? Large values mean slow loading, small
     * ones mean longer delays when panning. Recommended values: 1-3,
     * default is 2.
     */
    private Integer tileBuffer = 2;
    /**
     * For tiled wms layers: Overlap of map tiles in pixels. Useful for
     * preventing rendering artefacts at tile edges. Recommended values:
     * 0-15, default is 0 (no gutter at all).
     */
    private Integer tileGutter = 0;
    /**
     * For tiled wms layers: how many pixels should the size of one tile
     * be? Default is 256.
     */
    private Integer tileSize = 256;
    /**
     * For WMS on top of Google Maps you need to reproject the WMS image. This will stretch
     * the WMS images to fit the odd sized google tiles. Default is false
     */
    private boolean imageReproject = false;
    /**
     * Should layers also be rendered outside the map extent? Default is false.
     */
    private boolean displayOutsideMaxExtent = true;
    private String  maxExtent ;
    private String maxResolution = "auto";
    private String minResolution = null;
    private List<Float> resolutions = null;
    //fixed scales - overrides resolutions
    private List<Float> scales = null;
    private String units = "degrees";
    private Boolean fixedSize = false;
    private Boolean fractionalZoom = true;
    private Boolean singleTile = true;
    private Integer numZoomLevels = 16;
    /**
     * Control options
     */
    private Boolean panZoomBar = false;
    private Boolean panZoom = false;
    private Boolean keyboardDefaults = false;
    private Boolean layerSwitcher = false;
    private Boolean mousePosition = false;
    /**
     * Commercial layers
     */
    private Boolean google = false;
    private Boolean yahoo = false;
    private Boolean virtualEarth = false;
    
    private String ajaxCompId ;  
    public String getAjaxCompId() {
        return ajaxCompId;
    }
    public void setAjaxCompId(String ajaxCompId) {
        this.ajaxCompId = ajaxCompId;
    }
    /**
     * Option to know if the layers should be displayed
     * (set to true after the first page loads)     * 
     */
    private boolean initDisplay = false;

    /** Creates a new instance of UIMapPane */
    public UIMapPane() {
        super();
        if (isDebug()) {
            System.out.println("[UIMapPane] constructor----------------------");
        }
        setRendererType("org.mapfaces.renderkit.html.MapPane");    // this component has a renderer
    }

    public String getFamily() {
        return FAMILIY;
    }

    public Integer getImageBuffer() {
        return imageBuffer;
    }

    public void setImageBuffer(Integer imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    public int getLoadingLayers() {
        return loadingLayers;
    }

    public void setInitDisplay(boolean b) {
        this.initDisplay = b;
    }

    public boolean getInitDisplay() {
        return initDisplay;
    }

    public void setLoadingLayers(int loadingLayers) {
        this.loadingLayers = loadingLayers;
    }

    public Integer getTileBuffer() {
        return tileBuffer;
    }

    public void setTileBuffer(Integer tileBuffer) {
        this.tileBuffer = tileBuffer;
    }

    public Integer getTileGutter() {
        return tileGutter;
    }

    public void setTileGutter(Integer tileGutter) {
        this.tileGutter = tileGutter;
    }

    public Integer getTileSize() {
        return tileSize;
    }

    public void setTileSize(Integer tileSize) {
        this.tileSize = tileSize;
    }

    public boolean isImageReproject() {
        return imageReproject;
    }

    public void setImageReproject(boolean imageReproject) {
        this.imageReproject = imageReproject;
    }

    public boolean isDisplayOutsideMaxExtent() {
        return displayOutsideMaxExtent;
    }

    public void setDisplayOutsideMaxExtent(boolean displayOutsideMaxExtent) {
        this.displayOutsideMaxExtent = displayOutsideMaxExtent;
    }

    public String getMaxExtent() {
        return maxExtent;
    }

    public void setMaxExtent(String maxExtent) {
        this.maxExtent = maxExtent;
    }

    public String getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(String maxResolution) {
        this.maxResolution = maxResolution;
    }

    public String getMinResolution() {
        return minResolution;
    }

    public void setMinResolution(String minResolution) {
        this.minResolution = minResolution;
    }

    public List<Float> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Float> resolutions) {
        this.resolutions = resolutions;
    }

    public List<Float> getScales() {
        return scales;
    }

    public void setScales(List<Float> scales) {
        this.scales = scales;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public boolean isFixedSize() {
        return getFixedSize();
    }

    public void setFixedSize(boolean fixedSize) {
        this.setFixedSize((Boolean) fixedSize);
    }

    public Boolean isPanZoomBar() {
        return panZoomBar;
    }

    public void setPanZoomBar(Boolean panZoomBar) {
        this.panZoomBar = panZoomBar;
    }

    public Boolean isKeyboardDefaults() {
        return keyboardDefaults;
    }

    public void setKeyboardDefaults(Boolean keyboardDefaults) {
        this.keyboardDefaults = keyboardDefaults;
    }

    public Boolean isLayerSwitcher() {
        return layerSwitcher;
    }

    public void setLayerSwitcher(Boolean layerSwitcher) {
        this.layerSwitcher = layerSwitcher;
    }

    public Boolean isGoogle() {
        return google;
    }

    public void setGoogle(Boolean google) {
        this.google = google;
    }

    public Boolean isYahoo() {
        return yahoo;
    }

    public void setYahoo(Boolean yahoo) {
        this.yahoo = yahoo;
    }

    public Boolean isVirtualEarth() {
        return virtualEarth;
    }

    public void setVirtualEarth(Boolean virtualEarth) {
        this.virtualEarth = virtualEarth;
    }

    public Boolean isMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Boolean mousePosition) {
        this.mousePosition = mousePosition;
    }

    public Boolean getSingleTile() {
        return singleTile;
    }

    public void setSingleTile(Boolean singleTile) {
        this.singleTile = singleTile;
    }

    public Boolean getFixedSize() {
        return fixedSize;
    }

    public void setFixedSize(Boolean fixedSize) {
        this.fixedSize = fixedSize;
    }

    public Boolean getFractionalZoom() {
        return fractionalZoom;
    }

    public void setFractionalZoom(Boolean fractionalZoom) {
        this.fractionalZoom = fractionalZoom;
    }

    public Boolean getPanZoom() {
        return panZoom;
    }

    public void setPanZoom(Boolean panZoom) {
        this.panZoom = panZoom;
    }

    public Integer getNumZoomLevels() {
        return numZoomLevels;
    }

    public void setNumZoomLevels(Integer numZoomLevels) {
        this.numZoomLevels = numZoomLevels;
    }

    public MapContext getDefaultMapContext() {
        return defaultMapContext;
    }

    public void setDefaultMapContext(MapContext defaultMapContext) {
        this.defaultMapContext = defaultMapContext;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        //values[1] = defaultMapContext;
        values[2] = maxExtent;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);

    }
}

