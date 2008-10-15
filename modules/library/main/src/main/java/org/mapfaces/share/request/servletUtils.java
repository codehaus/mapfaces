/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.share.request;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kdelfour
 */
public class servletUtils {

    public static String getUserAgent() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
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
