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

/**
 * Default implementation of WMS layer.
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.2
 */
public class DefaultWmsLayer extends DefaultLayer implements WmsLayer {
    
    private String styles = null;
    private String sldBody = null;
    private String sld = null;
    private Server server = null;
    private String urlGetMap = null;

    public DefaultWmsLayer() {
        setType(LayerType.WMS);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getStyles() {
        return this.styles;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSld() {
        return this.sld;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getSldBody() {
        return this.sldBody;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setStyles(final String styles) {
        this.styles = styles;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSld(final String sld) {
        this.sld = sld;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setSldBody(final String sldBody) {
        this.sldBody = sldBody;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setServer(final Server server) {
        this.server = server;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Server getServer() {
        return this.server;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUrlGetMap() {
        return urlGetMap;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUrlGetMap(String urlGetMap) {
        this.urlGetMap = urlGetMap;
    }

}