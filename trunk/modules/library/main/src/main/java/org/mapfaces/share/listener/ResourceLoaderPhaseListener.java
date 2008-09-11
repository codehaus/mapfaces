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
 * @author kdelfour
 */
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

public class ResourceLoaderPhaseListener implements PhaseListener {

    private static final String RESOURCE_LOADER_VIEW_ID = ".resource";

    public void beforePhase(PhaseEvent event) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String viewRootId = facesContext.getViewRoot().getViewId();

        if (viewRootId.indexOf(RESOURCE_LOADER_VIEW_ID) != -1) {
            serveResource(facesContext);
        }
    }

    private void serveResource(FacesContext facesContext) {

        Map requestMap = facesContext.getExternalContext().getRequestParameterMap();

        String resourceName = getResourceName(requestMap);
        String resourceType = getResourceType(resourceName);
        String contentType = getContentType(resourceType);

        int indice, tempIndice;
        byte tempArr[];
        byte mainArr[] = new byte[0];
        byte byteArr[] = new byte[65535];

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        try {
//            String resourcePath = RESOURCE_FOLDER + "/" + resourceName;
            String resourcePath = resourceName;
            InputStream inputStream = ResourceLoaderPhaseListener.class.getResourceAsStream(resourcePath);
            URL url = ResourceLoaderPhaseListener.class.getResource(resourcePath);
            if (url == null) {
                // resource not found
                facesContext.responseComplete();

                return;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            response.setContentType(contentType);
            response.setStatus(200);
            ServletOutputStream outputStream = response.getOutputStream();

            for (indice = 0; (indice = inputStream.read(byteArr)) > 0;) {
                tempIndice = mainArr.length + indice;
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
        String resourceName = (String) requestMap.get("name");
        return resourceName;
    }

    public static String getResourceType(String resourceName) {
        String resourceType = resourceName.substring(resourceName.lastIndexOf('.') + 1, resourceName.length());
        return resourceType;
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