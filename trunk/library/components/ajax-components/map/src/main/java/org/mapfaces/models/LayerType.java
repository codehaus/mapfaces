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

package org.mapfaces.models;

public enum LayerType {
    
    //OWC types
    WMS("WMS"),
    WFS("WFS"),
    WCS("WCS"),
    GML("GML"),
    SLD("SLD"),
    FES("FES"),
    KML("KML"),
    
    //MF types
    FEATURE("FEATURE"),
    MAPCONTEXT("MAPCONTEXT"),
    SVG("SVG"),
    DEFAULT("DEFAULT"); 
    
    private final String value;

    LayerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LayerType fromValue(String v) {
        for (LayerType c: LayerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
