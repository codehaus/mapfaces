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

package org.mapfaces.models.layer;

import org.mapfaces.models.*;
import java.util.List;

public interface FeatureLayer extends Layer {
    
    public List<Feature> getFeatures();

    public void setFeatures(List<Feature> features);

    public int getSize();

    public void setSize(int size);

    public double getRotation();

    public void setRotation(double rotation);    
    
    public String getImage();
    
    public void setImage(String image);
    
}