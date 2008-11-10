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
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;

/**
 * @author Kevin Delfour
 */
public interface ContextFactory {

    public Context createDefaultContext();
    public Layer createDefaultLayer();
    public Server createDefaultServer();
    public Dimension createDefaultDimension();

}
