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

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.measure.unit.Unit;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.measure.Units;
import org.geotoolkit.referencing.CRS;
import org.mapfaces.models.Context;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

/**
 * @author Olivier Terral.(Geomatys)
 * @author Mehdi Sidhoum (Geomatys).
 */
public class UIMapPane extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.MapPane";
    //public static final double INCHES_PER_UNIT = 4374754;
    static final HashMap<String, Double> INCHES_PER_UNIT = new HashMap<String, Double>();


    static {
        INCHES_PER_UNIT.put("inches", 1.0);
        INCHES_PER_UNIT.put("ft", 12.0);
        INCHES_PER_UNIT.put("mi", 63360.0);
        INCHES_PER_UNIT.put("m", 39.3701);
        INCHES_PER_UNIT.put("km", 39370.1);
        INCHES_PER_UNIT.put("degree", 4374754.0);
        INCHES_PER_UNIT.put("yd", 36.0);
    }
//    {
//        'inches': 1.0,
//    'ft': 12.0,
//    'mi': 63360.0,
//    'm': 39.3701,
//    'km': 39370.1,
//    'dd': 4374754,
//    'yd': 36
//};
    /**
     * Constant: DOTS_PER_INCH
     * {Integer} 72 (A sensible default)
     */
    public static final int DOTS_PER_INCH = 72;
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
    private Unit unit = null;
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

    public Double getScale(Context model) {
        try {
            final int width = Integer.valueOf(model.getWindowWidth());
            final double widthBbox = Double.valueOf(model.getMaxx()) - Double.valueOf(model.getMinx());
            final String unitName;
            if (unit == null) {
                unit = CRS.decode(model.getSrs()).getCoordinateSystem().getAxis(0).getUnit();
            }
            if (Units.isLinear(unit)) {
                unitName = "m";
            } else {
                unitName = "degree";
            }
            return ((widthBbox / width) * INCHES_PER_UNIT.get(unitName) * DOTS_PER_INCH) / 1000000; // /1000000 to obtain kilometers
        //@TOD0 actually we use the OpenLayers method to calculate scale but the geotoolkit method seems to be better but gives bad result when the extent is
        //equal or bigger than -180,180

//            final double minx = Double.valueOf(model.getMinx());
//            final double maxx = Double.valueOf(model.getMaxx());
//            final double miny = Double.valueOf(model.getMiny());
//            final double maxy = Double.valueOf(model.getMaxy());
////            final double minx = Double.valueOf("-179");
////            final double maxx = Double.valueOf("179");
////            final double miny = Double.valueOf("-90");
////            final double maxy = Double.valueOf("90");
//            final double centery = (maxy+miny)/2;
//            //@TODO specific to GeoTK
//            Measure mes = ((AbstractCRS) CRS.decode("EPSG:4326")).distance(new double[]{minx,centery}, new double[]{maxx,centery});
//            System.out.println(mes.doubleValue());
//            return mes.getUnit().getConverterTo(SI.KILO(SI.METRE)).convert(mes.doubleValue());

        } catch (NoSuchAuthorityCodeException ex) {
            Logger.getLogger(UIMapPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(UIMapPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(final Unit unit) {
        this.unit = unit;
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
        final Object values[] = new Object[27];
        values[0] = super.saveState(context);
        values[1] = imageBuffer;
        values[2] = maxExtent;
        values[3] = loadingLayers;
        values[4] = tileBuffer;
        values[5] = tileGutter;
        values[6] = tileSize;
        values[7] = imageReproject;
        values[8] = displayOutsideMaxExtent;
        values[9] = maxResolution;
        values[10] = minResolution;
        values[11] = resolutions;
        values[12] = scales;
        values[13] = unit;
        values[14] = fixedSize;
        values[15] = fractionalZoom;
        values[16] = singleTile;
        values[17] = numZoomLevels;
        values[18] = panZoomBar;
        values[19] = panZoom;
        values[20] = keyboardDefaults;
        values[21] = layerSwitcher;
        values[22] = mousePosition;
        values[23] = google;
        values[24] = yahoo;
        values[25] = virtualEarth;
        values[26] = ajaxCompId;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        imageBuffer = (Integer) values[1];
        maxExtent = (String) values[2];
        loadingLayers = (Integer) values[3];
        tileBuffer = (Integer) values[4];
        tileGutter = (Integer) values[5];
        tileSize = (Integer) values[6];
        imageReproject = (Boolean) values[7];
        displayOutsideMaxExtent = (Boolean) values[8];
        maxResolution = (String) values[9];
        minResolution = (String) values[10];
        resolutions = (List) values[11];
        scales = (List) values[12];
        unit = (Unit) values[13];
        fixedSize = (Boolean) values[14];
        fractionalZoom = (Boolean) values[15];
        singleTile = (Boolean) values[16];
        numZoomLevels = (Integer) values[17];
        panZoomBar = (Boolean) values[18];
        panZoom = (Boolean) values[19];
        keyboardDefaults = (Boolean) values[20];
        layerSwitcher = (Boolean) values[21];
        mousePosition = (Boolean) values[22];
        google = (Boolean) values[23];
        yahoo = (Boolean) values[24];
        virtualEarth = (Boolean) values[25];
        ajaxCompId = (String) values[26];
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

