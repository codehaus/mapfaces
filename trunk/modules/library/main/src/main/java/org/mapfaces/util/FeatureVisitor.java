
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

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import org.geotools.display.primitive.GraphicFeatureJ2D;
import org.geotools.display.primitive.GraphicJ2D;
import org.geotools.display.service.AbstractGraphicVisitor;
import org.geotools.map.CoverageMapLayer;
import org.opengis.feature.Feature;

/**
 *
 * @author Mehdi Sidhoum
 */
public class FeatureVisitor extends AbstractGraphicVisitor {
    
    List<Feature> featureList = new ArrayList<Feature>();
    
    @Override
    public void visit(GraphicFeatureJ2D graphic, Shape shape) {
        featureList.add(graphic.getUserObject());
    }

    @Override
    public void visit(GraphicJ2D arg0, CoverageMapLayer arg1, Shape arg2) {
    }
    
    public List<Feature> getFeatureList() {
        return featureList;
    }

    @Override
    public String getResult() {
        return "";
    }

}
