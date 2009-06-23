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

package org.mapfaces.share.request;

import java.util.Enumeration;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Delfour
 */
public class RequestMapUtils {

    public static void put(final Object key, final Object value) {
        if (key != null) {
            final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getRequestMap().put(key.toString(), value);
        }
    }

    public static Object getbyKey(final Object key) {        
        if (key != null) {
            final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            return ec.getRequestMap().get(key.toString());
        }
        return null;
    }

    public static void showRequestMap(final HttpServletRequest request) {
        final Enumeration map = request.getAttributeNames();
        while (map.hasMoreElements()) {
            System.out.println("ATTRIBUTES" + map.nextElement());
        }
    }
}
