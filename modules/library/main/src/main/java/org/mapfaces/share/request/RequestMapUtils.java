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
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kdelfour
 */
public class RequestMapUtils {

    /**
     * 
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void put(Object key, Object value) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();

        if (key != null) {
            requestMap.put(key, value);
        }
    }

    /**
     * 
     * @param key
     * @return
     */
    public static Object getbyKey(Object key) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        if (key != null) {
            if (requestMap.containsKey(key)) {
                return requestMap.get(key);
            }
        }
        return null;
    }

    public static void showRequestMap(HttpServletRequest request) {
        Enumeration map = request.getAttributeNames();
        while (map.hasMoreElements()) {
            System.out.println("ATTRIBUTES" + map.nextElement());
        }
    }
}
