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

import java.io.File;
import java.util.List;
import javax.faces.context.FacesContext;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;

/**
 * @author Mehdi Sidhoum.
 */
public class UIMFLayer extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.MapPane.MFLayer";
    private Layer layer;
    /*
     * Directory where image generated by the portrayal servcie were saved
     */
    private File dir;
    /*
     * Name of webapp root element
     */
    private String contextPath;
    /*
     * style of the layer div , this style is modified when we drag the map
     */
    private String style;
    /**
     * styleClass of the layer div , this style is modified when we drag the map
     */
    private String styleClass;
    /**
     * This is the list of features for this uilayer component.
     */
    private List<Feature> features;
    /**
     * This is the url for the image png....
     */
    private String image;
    /**
     * Size of the image.
     */
    private int size;
    /**
     * Rotation of the image.
     */
    private double rotation;

    /**
     * Index of this layer in the Mapcontext object.
     */
    private int index;
    
    /**
     * this index is used for binding multi components for example layer and timeline bandinfo and rows in datatables.
     */
    private int bindingIndex;

    public UIMFLayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.MapPane.MFLayer");    // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(final Layer layer) {
        this.layer = layer;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(final File dir) {
        this.dir = dir;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[15];
        values[0] = super.saveState(context);
        values[1] = dir;
        values[2] = layer;
        values[3] = style;
        values[4] = styleClass;
        values[5] = features;
        values[6] = image;
        values[7] = size;
        values[8] = rotation;
        values[9] = contextPath;
        values[10] = index;
        values[11] = bindingIndex;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        dir = (File) values[1];
        layer = (Layer) values[2];
        setStyle((String) values[3]);
        setStyleClass((String) values[4]);
        setFeatures((List<Feature>) values[5]);
        setImage((String) values[6]);
        setSize((Integer) values[7]);
        setRotation((Double) values[8]);
        setContextPath((String) values[9]);
        setIndex((Integer) values[10]);
        setBindingIndex((Integer) values[11]);
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * @return the index
     */
    public /**
     * Index of this layer in the Mapcontext object.
     */
    int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public int getBindingIndex() {
        return bindingIndex;
    }

    public void setBindingIndex(int bindingIndex) {
        this.bindingIndex = bindingIndex;
    }
}
