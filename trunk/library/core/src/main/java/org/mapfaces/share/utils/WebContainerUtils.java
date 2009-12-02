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
package org.mapfaces.share.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class regroup all  methods to differentiate ServletContainer and PortletContainer
 *
 * @author olivier Terral (Geomatys)
 * @since 0.3
 */
public class WebContainerUtils {

    private static final Logger LOGGER = Logger.getLogger(WebContainerUtils.class.getName());

    public static String getRequestURL(final FacesContext facesContext) {
        final Object request = facesContext.getExternalContext().getRequest();

        if (request instanceof HttpServletRequest) {
            HttpServletRequest svRequest = (HttpServletRequest) request;
            return svRequest.getScheme() + "://" + svRequest.getServerName() + ":" + svRequest.getServerPort() + svRequest.getContextPath();

        } else if (request instanceof PortletRequest) {
            PortletRequest plRequest = (PortletRequest) request;
            return plRequest.getScheme() + "://" + plRequest.getServerName() + ":" + plRequest.getServerPort() + plRequest.getContextPath();

        }
        return null;
    }
    
    //TODO : remove this function and modify  WebContainerUtils.getRequestURL to match the result of this one
    public static String getHostUrl() {
        final Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (request instanceof HttpServletRequest) {
            HttpServletRequest svRequest = (HttpServletRequest) request;
            final String url = svRequest.getRequestURL().toString();
            final String uri = svRequest.getRequestURI();
            return url.substring(0, url.indexOf(uri));

        } else if (request instanceof PortletRequest) {
            //TODO : not tested
            PortletRequest plRequest = (PortletRequest) request;
            return plRequest.getScheme() + "://" + plRequest.getServerName() + ":" + plRequest.getServerPort();

        }
        return null;
    }

    //TODO : remove this function and modify  WebContainerUtils.getRequestURL to match the result of this one
    public static String getAjaxServer(final FacesContext facesContext) {
        final Object request = facesContext.getExternalContext().getRequest();

        if (request instanceof HttpServletRequest) {
            HttpServletRequest svRequest = (HttpServletRequest) request;
            return svRequest.getScheme() + "://" + svRequest.getServerName() + ":" + svRequest.getServerPort() + svRequest.getRequestURI();

        } else if (request instanceof PortletRequest) {
            //TODO : not tested, probably doesn't work
            PortletRequest plRequest = (PortletRequest) request;
            return plRequest.getScheme() + "://" + plRequest.getServerName() + ":" + plRequest.getServerPort() + plRequest.getContextPath();

        }
        return null;
    }


    static String getSessionId(final FacesContext context) {
        final Object session = context.getExternalContext().getSession(true);

        if (session instanceof HttpSession) {
            return ((HttpSession) session).getId();

        } else if (session instanceof PortletSession) {
            return ((PortletSession) session).getId();

        }
        return null;
    }
    /**
     * Returns the current outpustream
     * @param facesContext
     * @return String A String representing the action URL
     */
    public static OutputStream getResponseOutpustream(final FacesContext facesContext,final String contentType, final Object cache) throws IOException {
        final Object response = facesContext.getExternalContext().getResponse();
        setResponseProperties(facesContext, contentType, cache);

        if (response instanceof HttpServletResponse) {
            return ((HttpServletResponse) response).getOutputStream();

        } else if (response instanceof PortletResponse) {
            //A PortletResponse can be a RenderResponse or a ActionResponse

            if (response instanceof RenderResponse) {
                return ((RenderResponse) response).getPortletOutputStream();

            } else if (response instanceof ActionResponse) {
                return null;
            }
        }
        return null;
    }
     /**
     * Returns the current writer
     * @param facesContext
     * @return String A String representing the action URL
     */
    public static PrintWriter getResponseWriter(final FacesContext facesContext,final String contentType, final Object cache) throws IOException {
        final Object response = facesContext.getExternalContext().getResponse();
        setResponseProperties(facesContext, contentType, cache);
        
        if (response instanceof HttpServletResponse) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            return servletResponse.getWriter();

        } else if (response instanceof PortletResponse) {
            //A PortletResponse can be a RenderResponse or a ActionResponse

            if (response instanceof RenderResponse) {
                RenderResponse portletResponse = (RenderResponse) response;
                return portletResponse.getWriter();
            }
        }
        return null;
    }

    public static String getUserAgent(final FacesContext context) {
            final Object request = context.getExternalContext().getRequest();

            if (request instanceof HttpServletRequest) {
                HttpServletRequest servletRequest = (HttpServletRequest) request;
                return servletRequest.getHeader("User-Agent");

            } else if (request instanceof PortletRequest) {
                //TODO find a way to get the user-agent, it seems portlet doesn't have access to HTTP headers
                //PortletRequest portletRequest = (PortletRequest) request;
                return "Firefox";
            }
            return null;
    }
    
    public static String getUserAgent() {
        return getUserAgent(FacesContext.getCurrentInstance());
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
        return getUserAgent().contains("getus");
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

    public static void setResponseProperties(final FacesContext facesContext, final String contentType, final Object cache) {
        final Object response = facesContext.getExternalContext().getResponse();

        if (response instanceof HttpServletResponse) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;

            if (contentType != null)
                servletResponse.setContentType(contentType);

            //TODO may be we should use reflection to pass dynamic parameter
            if (cache instanceof Boolean) {

                if (!((Boolean) cache)) {
                    // need to set no cache or IE will not make future requests when same URL used.
                    servletResponse.setHeader("Pragma", "No-Cache");
                    servletResponse.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
                    servletResponse.setDateHeader("Expires", 1);

                }  else {
                    servletResponse.setDateHeader("Last-Modified", System.currentTimeMillis());
                    servletResponse.setDateHeader("Expires", System.currentTimeMillis() + 10L);
                    servletResponse.setHeader("Cache-control", "max-age=" + (10L / 1000));
                }
                
            } else if (cache instanceof Long)  {
                servletResponse.setDateHeader("Last-Modified", System.currentTimeMillis());
                servletResponse.setDateHeader("Expires", System.currentTimeMillis() + ((Long)cache));
                servletResponse.setHeader("Cache-control", "max-age=" + (((Long)cache) / 1000));
            }

        } else if (response instanceof PortletResponse) {
            //A PortletResponse can be a RenderResponse or a ActionResponse

            if (response instanceof RenderResponse) {
                RenderResponse portletResponse = (RenderResponse) response;
                portletResponse.setContentType(contentType);

            }

        }
    }

}
