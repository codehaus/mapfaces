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

package org.mapfaces.util;

import java.util.HashMap;
import org.mapfaces.models.Context;

/**
 *
 * @author Olivier Terral (Geomatys)
 */
public class MapUtils {
 public static final HashMap<String, Double> INCHES_PER_UNIT = new HashMap<String, Double>();
    static {
        INCHES_PER_UNIT.put("inches", 1.0);
        INCHES_PER_UNIT.put("ft", 12.0);
        INCHES_PER_UNIT.put("mi", 63360.0);
        INCHES_PER_UNIT.put("m", 39.3701);
        INCHES_PER_UNIT.put("km", 39370.1);
        INCHES_PER_UNIT.put("dd", 4374754.0);
        INCHES_PER_UNIT.put("yd", 36.0);
    }
//    {
//        'inches': 1.0,
//    'ft': 12.0,
//    'mi': 63360.0,
//    'm': 39.3701,
//    'km': 39370.1,
//    'dd': 4374754,
//    'yd': 36
//};
    /**
     * Constant: DOTS_PER_INCH
     * {Integer} 72 (A sensible default)
     */
    public static final int DOTS_PER_INCH = 72;
     public static Double getScale(Context model) {
    //        try {
            final int width = Integer.valueOf(model.getWindowWidth());
            final double widthBbox = Double.valueOf(model.getMaxx()) - Double.valueOf(model.getMinx());
    //        System.out.println(CRS.decode(model.getSrs()).getCoordinateSystem().getAxis(0).getUnit().getDimension().toString());
            return ((widthBbox / width) * INCHES_PER_UNIT.get("dd") * DOTS_PER_INCH);
    //            final double minx = Double.valueOf(model.getMinx());
    //            final double maxx = Double.valueOf(model.getMaxx());
    //            final double miny = Double.valueOf(model.getMiny());
    //            final double maxy = Double.valueOf(model.getMaxy());
    ////            final double minx = Double.valueOf("-179");
    ////            final double maxx = Double.valueOf("179");
    ////            final double miny = Double.valueOf("-90");
    ////            final double maxy = Double.valueOf("90");
    //            final double centery = (maxy+miny)/2;
    //            //@TODO specific to GeoTK
    //            Measure mes = ((AbstractCRS) CRS.decode("EPSG:4326")).distance(new double[]{minx,centery}, new double[]{maxx,centery});
    //            System.out.println(mes.doubleValue());
    //            return mes.getUnit().getConverterTo(SI.KILO(SI.METRE)).convert(mes.doubleValue());
    //        } catch (NoSuchAuthorityCodeException ex) {
    //            Logger.getLogger(UIMapPane.class.getName()).log(Level.SEVERE, null, ex);
    //        } catch (FactoryException ex) {
    //            Logger.getLogger(UIMapPane.class.getName()).log(Level.SEVERE, null, ex);
    //        }
    //        return null;
    }
}
