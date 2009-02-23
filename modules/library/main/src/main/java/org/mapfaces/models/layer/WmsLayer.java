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

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
import java.io.Serializable;
import org.mapfaces.models.*;


public interface  WmsLayer extends Layer,Serializable {
    
    String getStyles();

    String getSld();

    String getSldBody();

    void setStyles(String styles);

    void setSld(String sld);

    void setSldBody(String sldBody);

    public void setServer(final Server server);

    public Server getServer();
}