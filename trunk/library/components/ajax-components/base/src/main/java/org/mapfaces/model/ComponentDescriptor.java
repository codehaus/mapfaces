/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.model;

import java.util.Map;

/**
 * Model for the ComponentSelector.
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public interface ComponentDescriptor {

    public String getTitle();

    public void setTitle(String title);

    public String getKey();

    public void setKey(String key);

    public Object getValue();

    public void setValue(Object value);

    public String getType();

    public void setType(String type);

    public int getMaxCar();

    public void setMaxCar(int maxCar);
    
    public Map<Object, String> getSelectMap();

    public void setSelectMap(Map<Object, String> selectMap);

    public boolean isMandatory();

    public void setMandatory(boolean mandatory);

    public int getMaxOccurence();

    public void setMaxOccurence(int maxOccurence);

    public boolean isRendered();

    public void setRendered(boolean rendered);
}
