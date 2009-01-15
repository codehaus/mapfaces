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

package org.widgetfaces.share.listener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kevin Delfour
 */
public class ResourceLoaderPhaseListener implements PhaseListener {

    private static final String RESOURCE_LOADER_VIEW_ID = ".resource";

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterPhase(final PhaseEvent event) {
        final FacesContext facesContext = event.getFacesContext();
        final String viewRootId         = facesContext.getViewRoot().getViewId();

        if (viewRootId.indexOf(RESOURCE_LOADER_VIEW_ID) != -1) {
            serveResource(facesContext);
        }
    }

    private void serveResource(final FacesContext facesContext) {

        final Map requestMap = facesContext.getExternalContext().getRequestParameterMap();

        final String resourceName = getResourceName(requestMap);
        final String resourceType = getResourceType(resourceName);
        final String contentType  = getContentType(resourceType);

        byte tempArr[];
        byte mainArr[] = new byte[0];
        byte byteArr[] = new byte[65535];

        final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        try {
//            String resourcePath = RESOURCE_FOLDER + "/" + resourceName;
            final String resourcePath     = resourceName;
            final InputStream inputStream = ResourceLoaderPhaseListener.class.getResourceAsStream(resourcePath);
            final URL url                 = ResourceLoaderPhaseListener.class.getResource(resourcePath);
            if (url == null) {
                // resource not found
                facesContext.responseComplete();

                return;
            }

            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            final BufferedReader bufferedReader       = new BufferedReader(inputStreamReader);

            response.setContentType(contentType);
            response.setStatus(200);
            final ServletOutputStream outputStream = response.getOutputStream();

            for (int indice = 0; (indice = inputStream.read(byteArr)) > 0;) {
                int tempIndice = mainArr.length + indice;
                tempArr = new byte[tempIndice];
                System.arraycopy(mainArr, 0, tempArr, 0, mainArr.length);
                System.arraycopy(byteArr, 0, tempArr, mainArr.length, indice);
                mainArr = tempArr;
            }

            outputStream.write(mainArr);
            outputStream.flush();
            outputStream.close();

            facesContext.responseComplete();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getResourceName(Map requestMap) {
        return (String) requestMap.get("name");
    }

    public static String getResourceType(String resourceName) {
        return resourceName.substring(resourceName.lastIndexOf('.') + 1, resourceName.length());
    }

    public static String getContentType(String resourceType) {
        String contentType = null;
        if (resourceType.equals("js")) {
            contentType = "text/javascript";
        } else if (resourceType.equals("css")) {
            contentType = "text/css";
        } else if (resourceType.equals("jpg")) {
            contentType = "image/jpeg";
        } else if (resourceType.equals("gif")) {
            contentType = "image/gif";
        }
        return contentType;
    }
}