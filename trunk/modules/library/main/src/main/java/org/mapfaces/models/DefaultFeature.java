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
import java.util.Map;
import org.geotools.referencing.crs.DefaultGeographicCRS;

/**
 *
 * @author Mehdi Sidhoum
 */
public class DefaultFeature implements Feature {

    private String name;
    private DefaultGeographicCRS crs;
    private Map<String, Object> attributes;
    private Geometry geometry;

    public DefaultFeature() {
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
    public DefaultGeographicCRS getCrs() {
        return crs;
    }

    @Override
    public void setCrs(DefaultGeographicCRS crs) {
        this.crs = crs;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
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
}
