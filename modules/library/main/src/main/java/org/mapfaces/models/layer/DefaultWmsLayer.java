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

import org.mapfaces.models.LayerType;
import org.mapfaces.models.Server;



public class DefaultWmsLayer extends DefaultLayer implements WmsLayer {

    
    public LayerType type = LayerType.WMS;
    
    public String styles = null;
    
    public String sldBody = null;
    
    public String sld = null;
    
    public Server server = null;
    
    
    public String getStyles() {
        return this.styles;
    }

    public String getSld() {
        return this.sld;
    }

    public String getSldBody() {
        return this.sldBody;
    }

    public void setStyles(final String styles) {
        this.styles = styles;
    }

    public void setSld(final String sld) {
        this.sld = sld;
    }

    public void setSldBody(final String sldBody) {
        this.sldBody = sldBody;
    }

    public void setServer(final Server server) {
        this.server = server;
    }

    public Server getServer() {
        return this.server;
    }
}