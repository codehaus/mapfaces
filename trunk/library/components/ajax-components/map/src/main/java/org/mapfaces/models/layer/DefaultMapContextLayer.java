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
import org.mapfaces.models.LayerType;

public class DefaultMapContextLayer extends DefaultLayer implements MapContextLayer {

    public LayerType type = LayerType.MAPCONTEXT;
    private String mapContextKeyInSession;
    private Date dateFilter;
    private boolean userValueDisableOpacity;

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

    /**
     * @return the mapContextKeyInSession
     */
    public String getMapContextKeyInSession() {
        return mapContextKeyInSession;
    }

    /**
     * @param mapContextKeyInSession the mapContextKeyInSession to set
     */
    public void setMapContextKeyInSession(String mapContextKeyInSession) {
        this.mapContextKeyInSession = mapContextKeyInSession;
    }

    /**
     * @return the userValueDisableOpacity
     */
    public boolean isUserValueDisableOpacity() {
        return userValueDisableOpacity;
    }

    /**
     * @param userValueDisableOpacity the userValueDisableOpacity to set
     */
    public void setUserValueDisableOpacity(boolean userValueDisableOpacity) {
        this.userValueDisableOpacity = userValueDisableOpacity;
    }
}