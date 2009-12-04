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

package org.mapfaces.component.layer;

import org.mapfaces.component.*;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.mapfaces.models.Feature;

public class UIFeatureLayer extends UILayer {

    public static final String FAMILIY = "org.mapfaces.FeatureLayer";
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
    private static final Logger LOGGER = Logger.getLogger(UIFeatureLayer.class.getName());

    public UIFeatureLayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.FeatureLayer");    // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
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
        values[2] = features;
        values[3] = image;
        values[4] = size;
        values[5] = rotation;
        values[6] = contextPath;
        values[7] = index;
        values[8] = bindingIndex;
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
        setFeatures((List<Feature>) values[2]);
        setImage((String) values[3]);
        setSize((Integer) values[4]);
        setRotation((Double) values[5]);
        setContextPath((String) values[6]);
        setIndex((Integer) values[7]);
        setBindingIndex((Integer) values[8]);
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
     * Index of this layer in the Mapcontext object.
     * @return the index
     */
    public int getIndex() {
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
