
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
