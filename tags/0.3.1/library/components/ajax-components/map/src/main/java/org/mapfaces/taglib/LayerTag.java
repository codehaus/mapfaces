/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 *
 * @author leopratlong
 */
public class LayerTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Layer";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Layer";
    
    private ValueExpression layer;
    private ValueExpression contextPath;
    private ValueExpression dir;
    private ValueExpression style;

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("layer", layer);
        component.setValueExpression("contextPath", contextPath);
        component.setValueExpression("dir", dir);
        component.setValueExpression("style", style);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        setLayer(null);
        setContextPath(null);
        setDir(null);
        setStyle(null);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }
    
    /**
     * @return the layer
     */
    public ValueExpression getLayer() {
        return layer;
    }

    /**
     * @param layer the layer to set
     */
    public void setLayer(ValueExpression layer) {
        this.layer = layer;
    }

    /**
     * @return the contextPath
     */
    public ValueExpression getContextPath() {
        return contextPath;
    }

    /**
     * @param contextPath the contextPath to set
     */
    public void setContextPath(ValueExpression contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * @return the dir
     */
    public ValueExpression getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(ValueExpression dir) {
        this.dir = dir;
    }

    /**
     * @return the style
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
    }

}
