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

package org.mapfaces.component.layer;

import java.util.logging.Logger;
import org.geotools.map.MapContext;
import org.mapfaces.component.UILayer;

public class UIMapContextLayer extends UILayer {

    public static final String FAMILIY = "org.mapfaces.MapContextLayer";
    
    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.component.layer.UIMapContextLayer");
    
    public UIMapContextLayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.MapContextLayer");    // this component has a renderer
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }


}
