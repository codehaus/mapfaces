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

import java.util.Date;
import org.geotools.map.MapContext;
import org.mapfaces.models.LayerType;

public class DefaultMapContextLayer extends DefaultLayer implements MapContextLayer {
    
   public LayerType type = LayerType.MAPCONTEXT;
   
   private transient MapContext mapContext = null;

   private Date dateFilter;

    public MapContext getMapContext() {
        return mapContext;
    }

    public void setMapContext(MapContext mapContext) {
        this.mapContext = mapContext;
    }

    /**
     * @return the dateFilter
     */
    public Date getDateFilter() {
        return dateFilter;
    }

    /**
     * @param dateFilter the dateFilter to set
     */
    public void setDateFilter(Date dateFilter) {
        this.dateFilter = dateFilter;
    }
}