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
import java.util.HashMap;
import java.util.Map;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 *
 * @author leopratlong (Geomatys).
 */
public class BasicFeature implements Feature {


    private static final long serialVersionUID = 7526479565456578L;
    private String id;
    private CoordinateReferenceSystem crs;
    private Geometry geometry;
    private String name;
    
    /**
     * This object must be serializable
     */
    private Serializable userObject;

    public BasicFeature() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry the geometry to set
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return the userObject
     */
    public Serializable getUserObject() {
        return userObject;
    }

    /**
     * @param userObject the userObject to set
     */
    public void setUserObject(Serializable userObject) {
        this.userObject = userObject;
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
    public Map<String, Serializable> getAttributes() {
        return new HashMap<String, Serializable>();
    }

    @Override
    public void setAttributes(Map<String, Serializable> attributes) {
    }

    /**
     * @return the crs
     */
    public CoordinateReferenceSystem getCrs() {
        return crs;
    }

    /**
     * @param crs the crs to set
     */
    public void setCrs(CoordinateReferenceSystem crs) {
        this.crs = crs;
    }

}
