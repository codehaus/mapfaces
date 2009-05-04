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

package org.mapfaces.share.listener;

import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;

/**
 * This is a browser detect listener that can detects every full jsf life cycle process.
 * 
 * @author Mehdi Sidhoum (Geomatys).
 */
public class DetectBrowserListener implements PhaseListener {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.share.listener.DetectBrowserListener");
    private boolean flag = false;
    private boolean iE = false;
    private boolean opera = false;
    private boolean chrome = false;
    private boolean safari = false;
    private boolean fireFox = false;
    private boolean netScape = false;

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent e) {
        if (e.getPhaseId() == PhaseId.RESTORE_VIEW && !flag) {
            flag = true;
            FacesContext context = FacesContext.getCurrentInstance();
            proceedToDetect(context);
        }
    }

    public void proceedToDetect(FacesContext context) {
        if (context != null) {
            HttpServletRequest servletReq = (HttpServletRequest) context.getExternalContext().getRequest();
            String useragent = servletReq.getHeader("User-Agent");
            if (useragent != null) {
                String user = useragent.toLowerCase();
                if ((user.indexOf("msie") != -1)) {
                    iE = true;
                } else {
                    iE = false;
                }
                if (user.indexOf("netscape6") != -1) {
                    netScape = true;
                } else {
                    netScape = false;
                }
                if (user.indexOf("firefox") != -1) {
                    fireFox = true;
                } else {
                    fireFox = false;
                }
                if (user.indexOf("chrome") != -1) {
                    chrome = true;
                } else {
                    chrome = false;
                }
                if (user.indexOf("safari") != -1 && !chrome) {
                    safari = true;
                } else {
                    safari = false;
                }
                if (user.indexOf("opera") != -1) {
                    opera = true;
                } else {
                    opera = false;
                }
            }
        }
    }

    public void afterPhase(PhaseEvent e) {
        if (e.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            flag = false;
        }
    }

    public boolean IsIE() {
        return iE;
    }

    public boolean isOpera() {
        return opera;
    }

    public boolean isChrome() {
        return chrome;
    }

    public boolean isSafari() {
        return safari;
    }

    public boolean isFireFox() {
        return fireFox;
    }

    public boolean isNetScape() {
        return netScape;
    }
}