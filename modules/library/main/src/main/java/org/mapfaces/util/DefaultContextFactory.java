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

import java.math.BigDecimal;
import net.opengis.owc.v030.LayerType;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultContext;
import org.mapfaces.models.DefaultDimension;
import org.mapfaces.models.layer.DefaultLayer;
import org.mapfaces.models.DefaultServer;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;
import org.mapfaces.models.layer.DefaultFeatureLayer;
import org.mapfaces.models.layer.DefaultMapContextLayer;
import org.mapfaces.models.layer.DefaultWmsLayer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.MapContextLayer;
import org.mapfaces.models.layer.WmsLayer;

/**
 * @author Kevin Delfour
 */
public class DefaultContextFactory implements ContextFactory{

    /**
     * {@inheritDoc }
     */
    @Override
    public Context createDefaultContext() {
        return new DefaultContext();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Layer createDefaultLayer() {
        return new DefaultLayer();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Server createDefaultServer() {
        return new DefaultServer();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Dimension createDefaultDimension() {
        return new DefaultDimension();
    }
    
    /**
     * {@inheritDoc }
     */
    public Layer createDefaultMapContextLayer() {

        Layer layer = new DefaultMapContextLayer();
        layer.setId("MapFaces_Layer_MF_" + -1);
        layer.setGroup("mapfaces_group");
        layer.setName("abstractlayer");
        layer.setHidden(false);
        layer.setOpacity("1");
        layer.setTitle("MapContext 'all in one' layer");
        layer.setType(org.mapfaces.models.LayerType.MAPCONTEXT);
        layer.setOutputFormat("image/png");
        layer.setQueryable(true);
        
        return layer;
    }

    public Layer createDefaultWmsLayer() {
        return new DefaultWmsLayer();
    }
    public Layer createDefaultFeatureLayer() {
        return new DefaultFeatureLayer();
    }
}
