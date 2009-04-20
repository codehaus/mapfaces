/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */

package org.mapfaces.models;

import java.io.Serializable;
import org.geotoolkit.internal.jaxb.backend.AbstractWMSCapabilities;

/**
 *
 * @author Mehdi Sidhoum.
 */
public interface  Server  extends Serializable {

    public void setGTCapabilities(AbstractWMSCapabilities capabilities);

    public AbstractWMSCapabilities getGTCapabilities();

    String getHref();

    void setHref(String href);

    String getService();

    void setService(String service);

    String getVersion();

    void setVersion(String version);

    String getTitle();

    void setTitle(String  title);

}
