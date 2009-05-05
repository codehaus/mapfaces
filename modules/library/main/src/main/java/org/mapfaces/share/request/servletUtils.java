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

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Delfour
 */
public class servletUtils {

    public static String getUserAgent() {
        final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return request.getHeader("user-agent");
    }

    /* Browser Id */
    public static boolean isChrome() {
        return getUserAgent().contains("Chrome");
    }

    public static boolean isOmniWeb() {
        return getUserAgent().contains("OmniWeb");
    }

    public static boolean isSafari() {
        return getUserAgent().contains("Apple");
    }

    public static boolean isOpera() {
        return getUserAgent().contains("Opera");
    }

    public static boolean isICab() {
        return getUserAgent().contains("iCab");
    }

    public static boolean isKonqueror() {
        return getUserAgent().contains("KDE");
    }

    public static boolean isFirefox() {
        return getUserAgent().contains("Firefox");
    }

    public static boolean isCamino() {
        return getUserAgent().contains("Camino");
    }

    public static boolean isNetscape() {
        return getUserAgent().contains("Netscape");
    }

    public static boolean isExplorer() {
        return getUserAgent().contains("MSIE");
    }

    public static boolean isMozilla() {
        return getUserAgent().contains("Gecko");
    }

    /* Platform Id */
    public static boolean isLinuxPlatform() {
        return getUserAgent().contains("Linux");
    }

    public static boolean isMacPlatform() {
        return getUserAgent().contains("Mac");
    }

    public static boolean isWindowsPlatform() {
        return getUserAgent().contains("Win");
    }
}
