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
 * This is an interface to represent all features that can be serializable.
 * 
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.2
 */
public interface Feature extends Serializable {

    /**
     * @TODO switch to enum
     */
    static final String POINT = "POINT";
    static final String LINESTRING = "LINESTRING";
    static final String POLYGON = "POLYGON";
    
    String getId();
    
    void setId(String id);

    String getName();

    void setName(String name);

    CoordinateReferenceSystem getCrs();

    void setCrs(CoordinateReferenceSystem crs);

    Map<String, Serializable> getAttributes();

    void setAttributes(Map<String, Serializable> attributes);

    Geometry getGeometry();

    void setGeometry(Geometry geometry);
    
    Serializable getUserObject();

    void setUserObject(Serializable object);
}
