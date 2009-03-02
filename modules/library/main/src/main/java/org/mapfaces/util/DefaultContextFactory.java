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
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.models.layer.DefaultWmsLayer;

/**
 * @author Olivier Terral (Geomatys).
 */
public class DefaultContextFactory implements ContextFactory {

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
    public Layer createDefaultMapContextLayer(int indexLayer) {

        Layer layer = new DefaultMapContextLayer();
        layer.setId("MapFaces_Layer_MC_" + String.valueOf(indexLayer));
        layer.setGroup("mapfaces_group");
        layer.setName("abstractlayer");
        layer.setHidden(false);
        layer.setOpacity("1");
        layer.setTitle("MapContext 'all in one' layer" + String.valueOf(indexLayer));
        layer.setType(org.mapfaces.models.LayerType.MAPCONTEXT);
        layer.setOutputFormat("image/png");
        layer.setQueryable(true);

        return layer;
    }

    public Layer createDefaultWmsLayer() {
        Layer layer = new DefaultWmsLayer();
        layer.setType(org.mapfaces.models.LayerType.WMS);
        return layer;
    }
    
    public Layer createDefaultWmsGetMapLayer() {
        Layer layer = new DefaultWmsGetMapLayer();
        layer.setType(org.mapfaces.models.LayerType.WMS);
        return layer;
    }

    public Layer createDefaultFeatureLayer(int indexLayer) {
        Layer layer = new DefaultFeatureLayer();
        layer.setId("MapFaces_Layer_F_" + String.valueOf(indexLayer));
        layer.setGroupId(indexLayer);
        layer.setGroup("mapfaces_group");
        layer.setName("markers");
        layer.setHidden(false);
        layer.setOpacity("1");
        layer.setTitle("Feature layer" + String.valueOf(indexLayer));
        layer.setOutputFormat("image/gif");
        layer.setType(org.mapfaces.models.LayerType.FEATURE);
        layer.setQueryable(true);
        return layer;
    }
}
