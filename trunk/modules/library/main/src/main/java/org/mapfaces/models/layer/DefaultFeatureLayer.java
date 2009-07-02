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

package org.mapfaces.models.layer;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
import java.util.List;
import org.mapfaces.models.Feature;
import org.mapfaces.models.LayerType;


public class DefaultFeatureLayer extends DefaultLayer implements FeatureLayer {   
    
    
    public LayerType type = LayerType.FEATURE;
    
    /**
     * This is the list of features for this uilayer component.
     */
    public List<Feature> features = null;
    
    /**
     * This is the image for point rendering.
     */
    public String image = null;
    
    /**
     * Size of the image.
     */
    public int size = 0;
    
    /**
     * Rotation of the image.
     */
    public double rotation = 0;
    
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


    
}