
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
import org.geotoolkit.display2d.canvas.AbstractGraphicVisitor;
import org.geotoolkit.display2d.primitive.ProjectedCoverage;
import org.geotoolkit.display2d.primitive.ProjectedFeature;
import org.opengis.feature.Feature;

/**
 *
 * @author Mehdi Sidhoum
 */
public class FeatureVisitor extends AbstractGraphicVisitor {
    
    List<Feature> featureList = new ArrayList<Feature>();
    
    @Override
    public void visit(ProjectedFeature graphic, Shape shape) {
        featureList.add(graphic.getFeature());
    }

    @Override
    public void visit(ProjectedCoverage arg0, Shape arg2) {
    }
    
    public List<Feature> getFeatureList() {
        return featureList;
    }

}
