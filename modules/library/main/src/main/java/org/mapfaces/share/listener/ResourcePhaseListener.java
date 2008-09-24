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

/**
 *
 * @author Mehdi Sidhoum
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class ResourcePhaseListener implements PhaseListener {

    public static final String RESOURCE_PREFIX = "/resource";
    public static final String RESOURCE_LOCATION_PARAM = "r";
    public static final String CONTENT_TYPE_PARAM = "ct";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    private Map<String, String> extensionToContentType = null;
    private static final Map<String, String> POOL = new HashMap<String, String>();

    public ResourcePhaseListener() {
        extensionToContentType = new HashMap<String, String>();
        extensionToContentType.put(".js", "text/javascript");
        extensionToContentType.put(".gif", "image/gif");
        extensionToContentType.put(".jpg", "image/jpeg");
        extensionToContentType.put(".jpeg", "image/jpeg");
        extensionToContentType.put(".png", "image/png");
        extensionToContentType.put(".css", "text/css");
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public void beforePhase(PhaseEvent phaseEvent) {
    }

    public void afterPhase(PhaseEvent event) {
        if (event.getFacesContext().getViewRoot().getViewId().startsWith(
                RESOURCE_PREFIX)) {
            FacesContext context = event.getFacesContext();
            ExternalContext external = context.getExternalContext();

            String resourcePath =
                    (String) external.getRequestParameterMap().get(
                    RESOURCE_LOCATION_PARAM);
            if (resourcePath == null) {
                return;
            }

            String contentType =
                    (String) external.getRequestParameterMap().get(
                    CONTENT_TYPE_PARAM);
            if (contentType == null) {
                int extensionIndex = resourcePath.lastIndexOf(".");
                if (extensionIndex != -1) {
                    contentType =
                            extensionToContentType.get(resourcePath.substring(extensionIndex));
                }
                if (contentType == null) {
                    contentType = DEFAULT_CONTENT_TYPE;
                }
            }

            InputStream in = getClass().getResourceAsStream(resourcePath);
            HttpServletResponse servletResponse =
                    (HttpServletResponse) external.getResponse();
            try {
                OutputStream out = servletResponse.getOutputStream();

                //PrintWriter out = servletResponse.getWriter();

                servletResponse.setContentType(contentType);
                int ch;
                if (in == null) {
                    return;
                }
                while ((ch = in.read()) != -1) {
                    out.write(ch);
                }

            //out.flush();

            } catch (IOException ex) {
                throw new FacesException(ex);
            }
            context.responseComplete();
        }
    }

    /**
     * Returns a URL for fetching a resource through this listener
     * 
     * @param context the faces context
     * @param String resourcePath the path to the resource
     * @param String contentType the content type to include in the URL, or null
     *           if no content type should be included
     * @return the URL of the form
     *         /appname/resource.faces?r=resourcePath,ct=contentType or
     *         /appname/faces/resource?r=resourcePath,ct=contentType
     */
    public static String getURL(FacesContext context, String resourcePath,
            String contentType) {
        if (POOL.containsKey(resourcePath)) {
            return getURLvalue(resourcePath);
        }
        System.out.println("getuuuuuuuuuuuuuuuuuuuuuuuuuuuuuurl"+getURLvalue(resourcePath));
        ViewHandler handler = context.getApplication().getViewHandler();
        String url = handler.getActionURL(context, RESOURCE_PREFIX);
        
        StringBuilder r = new StringBuilder(url);
        r.append("?" + RESOURCE_LOCATION_PARAM + "=").append(resourcePath);
        if (contentType != null) {
            r.append("," + CONTENT_TYPE_PARAM + "=").append(contentType);
        }
        POOL.put(resourcePath, r.toString());
        
        System.out.println("getuuuuuuuuuuuuuuuuuuuuuuuuuuuuuurl"+r);
        return r.toString();
    }

    public static String getURLvalue(final String key) {
        synchronized (POOL) {
            return POOL.get(key);
        }
    }
}
