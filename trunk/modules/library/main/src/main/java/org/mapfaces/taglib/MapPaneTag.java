/*
 * MapPaneTag.java
 *
 * Created on 27 decembre 2007, 16:28
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum
 */
public class MapPaneTag extends WidgetBaseTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.MapPane";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.MapPane";
    
    
    private ValueExpression empty = null;
   
    /**
     * OpenLayers.Control
     * */
    private ValueExpression panZoom = null;
    private ValueExpression panZoomBar = null;
    private ValueExpression navigation = null;
    private ValueExpression keyboardDefaults = null;
    private ValueExpression layerSwitcher = null;
    private ValueExpression mousePosition = null; 
    private ValueExpression navToolBar = null;   
    private ValueExpression numZoomLevels = null;   
    
    /**
     * OpenLayers.Map options
     */
    private ValueExpression imageBuffer = null;      
    private ValueExpression singleTile = null;     
    private ValueExpression fixedSize = null;
    private ValueExpression fractionalZoom = null;
    
    /**
     * Commercial OpenLayers.Layer
     */
    private ValueExpression google = null;
    private ValueExpression yahoo = null;
    private ValueExpression virtualEarth = null;
        
    public String getComponentType() {
        return COMP_TYPE;
    }

    public String getRendererType() {
        return RENDER_TYPE;
    }

    
    @Override
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);        
        component.setValueExpression("empty",empty);
        component.setValueExpression("panZoomBar",panZoomBar);
        component.setValueExpression("panZoom",panZoom);
        component.setValueExpression("navigation",navigation);
        component.setValueExpression("keyboardDefaults",keyboardDefaults);
        component.setValueExpression("layerSwitcher",layerSwitcher);
        component.setValueExpression("mousePosition",mousePosition);
        component.setValueExpression("navToolBar",navToolBar);
        
        
        component.setValueExpression("imageBuffer",imageBuffer);
        component.setValueExpression("singleTile",singleTile);
        component.setValueExpression("fixedSize",fixedSize);
        component.setValueExpression("fractionalZoom",fractionalZoom);
        component.setValueExpression("numZoomLevels",numZoomLevels);
        
        component.setValueExpression("google",google);
        component.setValueExpression("yahoo",yahoo);
        component.setValueExpression("virtualEarth",virtualEarth);
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        setempty(null);
        panZoomBar = null;
        navigation = null;
        keyboardDefaults = null;
        layerSwitcher = null;
        mousePosition = null;
        navToolBar = null;
        setPanZoom(null);        
        
        imageBuffer = null;   
        singleTile = null;   
        fixedSize = null;
        fractionalZoom = null;        
        
        google = null;
        yahoo = null;
        virtualEarth = null;
    }

    public void setPanZoomBar(ValueExpression panZoomBar) {
        this.panZoomBar = panZoomBar;
    }

    public void setNavigation(ValueExpression navigation) {
        this.navigation = navigation;
    }

    public void setKeyboardDefaults(ValueExpression keyboardDefaults) {
        this.keyboardDefaults = keyboardDefaults;
    }

    public void setLayerSwitcher(ValueExpression layerSwitcher) {
        this.layerSwitcher = layerSwitcher;
    }

    public void setGoogle(ValueExpression google) {
        this.google = google;
    }

    public void setYahoo(ValueExpression yahoo) {
        this.yahoo = yahoo;
    }

    public void setVirtualEarth(ValueExpression virtualEarth) {
        this.virtualEarth = virtualEarth;
    }


    public void setMousePosition(ValueExpression mousePosition) {
        this.mousePosition = mousePosition;
    }

    public void setImageBuffer(ValueExpression imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    public void setSingleTile(ValueExpression singleTile) {
        this.singleTile = singleTile;
    }

    public void setFixedSize(ValueExpression fixedSize) {
        this.fixedSize = fixedSize;
    }


    public void setFractionalZoom(ValueExpression fractionalZoom) {
        this.fractionalZoom = fractionalZoom;
    }

    public void setNavToolBar(ValueExpression navToolBar) {
        this.navToolBar = navToolBar;
    }

    public void setPanZoom(ValueExpression panZoom) {
        this.panZoom = panZoom;
    }


    public void setNumZoomLevels(ValueExpression numZoomLevels) {
        this.numZoomLevels = numZoomLevels;
    }

    public void setempty(ValueExpression empty) {
        this.empty = empty;
    }
    
}
