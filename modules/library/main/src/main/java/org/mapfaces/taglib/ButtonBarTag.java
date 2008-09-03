/*
 * MapPaneTag.java
 *
 * Created on 27 decembre 2007, 16:28
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 *
 * @authorOlivier Terral
 */
public class ButtonBarTag extends WidgetBaseTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.ButtonBar";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.ButtonBar";
    
    private ValueExpression empty = null;
   
    /**
     * OpenLayers.Control
     * */
    private ValueExpression zoomIn = null;
    private ValueExpression zoomOut = null;
    private ValueExpression pan = null; 
    private ValueExpression zoomMaxExtent = null;
    private ValueExpression history = null;
    private ValueExpression panEffect = null;
    
  
        
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
        component.setValueExpression("zoomIn",zoomIn);
        component.setValueExpression("zoomOut",zoomOut);
        component.setValueExpression("pan",pan);
        component.setValueExpression("zoomMaxExtent",zoomMaxExtent);
        component.setValueExpression("history",history);
        component.setValueExpression("panEffect",panEffect);
        
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();        
        setEmpty(null);
        setZoomIn(null);
        setZoomOut(null);
        setPan(null);
        setZoomMaxExtent(null);
        setHistory(null);
        setPanEffect(null);
    }



    public void setEmpty(ValueExpression empty) {
        this.empty = empty;
    }

    public ValueExpression getZoomIn() {
        return zoomIn;
    }

    public ValueExpression getZoomOut() {
        return zoomOut;
    }

    public ValueExpression getPan() {
        return pan;
    }

    public void setZoomIn(ValueExpression zoomIn) {
        this.zoomIn = zoomIn;
    }

    public void setZoomOut(ValueExpression zoomOut) {
        this.zoomOut = zoomOut;
    }

    public void setPan(ValueExpression pan) {
        this.pan = pan;
    }

    public ValueExpression getZoomMaxExtent() {
        return zoomMaxExtent;
    }

    public void setZoomMaxExtent(ValueExpression zoomMaxExtent) {
        this.zoomMaxExtent = zoomMaxExtent;
    }

    public ValueExpression getHistory() {
        return history;
    }

    public void setHistory(ValueExpression history) {
        this.history = history;
    }

    public ValueExpression getPanEffect() {
        return panEffect;
    }

    public void setPanEffect(ValueExpression panEffect) {
        this.panEffect = panEffect;
    }
    
}
