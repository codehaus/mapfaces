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
    public static Boolean isChrome() {
        return getUserAgent().contains("Chrome");
    }

    public static Boolean isOmniWeb() {
        return getUserAgent().contains("OmniWeb");
    }

    public static Boolean isSafari() {
        return getUserAgent().contains("Apple");
    }

    public static Boolean isOpera() {
        return getUserAgent().contains("Opera");
    }

    public static Boolean isICab() {
        return getUserAgent().contains("iCab");
    }

    public static Boolean isKonqueror() {
        return getUserAgent().contains("KDE");
    }

    public static Boolean isFirefox() {
        return getUserAgent().contains("Firefox");
    }

    public static Boolean isCamino() {
        return getUserAgent().contains("Camino");
    }

    public static Boolean isNetscape() {
        return getUserAgent().contains("Netscape");
    }

    public static Boolean isExplorer() {
        return getUserAgent().contains("MSIE");
    }

    public static Boolean isMozilla() {
        return getUserAgent().contains("Gecko");
    }

    /* Platform Id */
    public static Boolean isLinuxPlatform() {
        return getUserAgent().contains("Linux");
    }

    public static Boolean isMacPlatform() {
        return getUserAgent().contains("Mac");
    }

    public static Boolean isWindowsPlatform() {
        return getUserAgent().contains("Win");
    }
}
