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
import javax.faces.component.UIComponent;
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
    private String maxExtent;
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
    private String ajaxCompId;

    public String getAjaxCompId() {
        return ajaxCompId;
    }

    public void setAjaxCompId(final String ajaxCompId) {
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
        setRendererType("org.mapfaces.renderkit.html.MapPane");    // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    public Integer getImageBuffer() {
        return imageBuffer;
    }

    public void setImageBuffer(final Integer imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    public int getLoadingLayers() {
        return loadingLayers;
    }

    public void setInitDisplay(final boolean b) {
        this.initDisplay = b;
    }

    public boolean getInitDisplay() {
        return initDisplay;
    }

    public void setLoadingLayers(final int loadingLayers) {
        this.loadingLayers = loadingLayers;
    }

    public Integer getTileBuffer() {
        return tileBuffer;
    }

    public void setTileBuffer(final Integer tileBuffer) {
        this.tileBuffer = tileBuffer;
    }

    public Integer getTileGutter() {
        return tileGutter;
    }

    public void setTileGutter(final Integer tileGutter) {
        this.tileGutter = tileGutter;
    }

    public Integer getTileSize() {
        return tileSize;
    }

    public void setTileSize(final Integer tileSize) {
        this.tileSize = tileSize;
    }

    public boolean isImageReproject() {
        return imageReproject;
    }

    public void setImageReproject(final boolean imageReproject) {
        this.imageReproject = imageReproject;
    }

    public boolean isDisplayOutsideMaxExtent() {
        return displayOutsideMaxExtent;
    }

    public void setDisplayOutsideMaxExtent(final boolean displayOutsideMaxExtent) {
        this.displayOutsideMaxExtent = displayOutsideMaxExtent;
    }

    public String getMaxExtent() {
        return maxExtent;
    }

    public void setMaxExtent(final String maxExtent) {
        this.maxExtent = maxExtent;
    }

    public String getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(final String maxResolution) {
        this.maxResolution = maxResolution;
    }

    public String getMinResolution() {
        return minResolution;
    }

    public void setMinResolution(final String minResolution) {
        this.minResolution = minResolution;
    }

    public List<Float> getResolutions() {
        return resolutions;
    }

    public void setResolutions(final List<Float> resolutions) {
        this.resolutions = resolutions;
    }

    public List<Float> getScales() {
        return scales;
    }

    public void setScales(final List<Float> scales) {
        this.scales = scales;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(final String units) {
        this.units = units;
    }

    public boolean isFixedSize() {
        return getFixedSize();
    }

    public void setFixedSize(final boolean fixedSize) {
        this.setFixedSize((Boolean) fixedSize);
    }

    public Boolean isPanZoomBar() {
        return panZoomBar;
    }

    public void setPanZoomBar(final Boolean panZoomBar) {
        this.panZoomBar = panZoomBar;
    }

    public Boolean isKeyboardDefaults() {
        return keyboardDefaults;
    }

    public void setKeyboardDefaults(final Boolean keyboardDefaults) {
        this.keyboardDefaults = keyboardDefaults;
    }

    public Boolean isLayerSwitcher() {
        return layerSwitcher;
    }

    public void setLayerSwitcher(final Boolean layerSwitcher) {
        this.layerSwitcher = layerSwitcher;
    }

    public Boolean isGoogle() {
        return google;
    }

    public void setGoogle(final Boolean google) {
        this.google = google;
    }

    public Boolean isYahoo() {
        return yahoo;
    }

    public void setYahoo(final Boolean yahoo) {
        this.yahoo = yahoo;
    }

    public Boolean isVirtualEarth() {
        return virtualEarth;
    }

    public void setVirtualEarth(final Boolean virtualEarth) {
        this.virtualEarth = virtualEarth;
    }

    public Boolean isMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(final Boolean mousePosition) {
        this.mousePosition = mousePosition;
    }

    public Boolean getSingleTile() {
        return singleTile;
    }

    public void setSingleTile(final Boolean singleTile) {
        this.singleTile = singleTile;
    }

    public Boolean getFixedSize() {
        return fixedSize;
    }

    public void setFixedSize(final Boolean fixedSize) {
        this.fixedSize = fixedSize;
    }

    public Boolean getFractionalZoom() {
        return fractionalZoom;
    }

    public void setFractionalZoom(final Boolean fractionalZoom) {
        this.fractionalZoom = fractionalZoom;
    }

    public Boolean getPanZoom() {
        return panZoom;
    }

    public void setPanZoom(final Boolean panZoom) {
        this.panZoom = panZoom;
    }

    public Integer getNumZoomLevels() {
        return numZoomLevels;
    }

    public void setNumZoomLevels(final Integer numZoomLevels) {
        this.numZoomLevels = numZoomLevels;
    }

    public MapContext getDefaultMapContext() {
        return defaultMapContext;
    }

    public void setDefaultMapContext(final MapContext defaultMapContext) {
        this.defaultMapContext = defaultMapContext;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        //values[1] = defaultMapContext;
        values[2] = maxExtent;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        maxExtent = (String) values[2];
    }

    /**
     * This method remove a layer child of this Mappane if exists.
     * @param layerComp
     */
    public void removeLayer(final UIComponent layerComp) {
        for (UIComponent comp : this.getChildren()) {
            if (comp.getId().equals(layerComp.getId())) {
                this.getChildren().remove(comp);
            }
        }
    }

    /**
     * this method add a layer as children of this Mappane
     * @param layerComp
     */
    public void addLayer(final UIComponent layerComp) {
        this.getChildren().add(layerComp);
    }
}

