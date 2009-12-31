/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */

package org.mapfaces.models;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import java.util.Map;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * This is a feature implementation used as a model for FeatureLayer.
 * 
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.2
 */
public class DefaultFeature implements Feature {

    private static final long serialVersionUID = 7526471155622776147L;
    private String id;
    private String name;
    private CoordinateReferenceSystem crs;
    private Map<String, Serializable> attributes;
    private Geometry geometry;
    /**
     * This object must be serializable
     */
    private Serializable userObject;

    public DefaultFeature() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CoordinateReferenceSystem getCrs() {
        return crs;
    }

    @Override
    public void setCrs(CoordinateReferenceSystem crs) {
        this.crs = crs;
    }

    @Override
    public Map<String, Serializable> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<String, Serializable> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public Serializable getUserObject() {
        return userObject;
    }

    @Override
    public void setUserObject(Serializable object) {
        this.userObject = object;
    }

    /**
     * Compares this metadata with the specified element for equality.
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && object.getClass().equals(getClass())) {
            final DefaultFeature that = (DefaultFeature) object;
            return this.id != null && this.id.equals(that.id) &&
                    this.geometry != null && this.geometry.equals(that.getGeometry()) &&
                    this.userObject != null && this.userObject.equals(that.getUserObject());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.crs != null ? this.crs.hashCode() : 0);
        hash = 17 * hash + (this.geometry != null ? this.geometry.hashCode() : 0);
        return hash;
    }

    /**
     * Returns a string representation of this element.
     * This is mostly for debugging purpose.
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(getClass().getSimpleName());
        sb.append(" id : ").append(id);
        sb.append(" crs : ").append(crs);
        sb.append(" geometry : ").append(geometry);
        sb.append(" userObject : ").append(userObject);
        sb.append(" ]");
        return sb.toString();
    }
}
