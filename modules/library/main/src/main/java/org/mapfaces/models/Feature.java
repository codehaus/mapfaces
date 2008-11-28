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
import org.geotools.referencing.crs.DefaultGeographicCRS;

/**
 * This is an interface to represent all features that can be serializable.
 * 
 * @author Mehdi Sidhoum.
 */
public interface Feature extends Serializable {

    public String getName();

    public void setName(String name);

    public DefaultGeographicCRS getCrs();

    public void setCrs(DefaultGeographicCRS crs);

    public Map<String, Object> getAttributes();

    public void setAttributes(Map<String, Object> attributes);

    public Geometry getGeometry();

    public void setGeometry(Geometry geometry);
}
