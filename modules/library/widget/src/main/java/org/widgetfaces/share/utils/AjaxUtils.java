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

package org.widgetfaces.share.utils;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Delfour
 */
public class AjaxUtils {

    public static final List<String> AjaxParam = new ArrayList<String>();
    /*
     * Parameter's names
     */
    public static final String AJAX_REQUEST_PARAM_KEY   = "org.widgetfaces.ajax.AJAX_REQUEST";
    public static final String AJAX_CONTAINER_ID_KEY    = "org.widgetfaces.ajax.AJAX_CONTAINER_ID";
    
    public static final String AJAX_FORM_ID_KEY         = "org.widgetfaces.ajax.AJAX_FORM_ID";
    public static final String AJAX_COMPONENT_ID_KEY    = "org.widgetfaces.ajax.AJAX_COMPONENT_ID";
    
    public static final String AJAX_RENDERCHILD_ID_KEY  = "org.widgetfaces.ajax.AJAX_RENDERCHILD_ID_ID";
    public static final String AJAX_COMPONENT_VALUE_KEY = "org.widgetfaces.ajax.AJAX_COMPONENT_VALUE";
    

    /**
     * Constructor
     */
    public AjaxUtils(){
        //Erase all previous parameters
        clearAjaxParameters();
    }

    public static String getRequestJs (String method, String url, String parameters){
 //       return "var requete = new Request({method: '"+method+"', url: '" +url+ "'}).send('" +parameters+ "');";
        return "var requete = new Request({method: '"+method+"',url: '" +url+ "'}).send(" +parameters+ ");";
    }

    public static String getAjaxServer(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }

    public synchronized String getAjaxParameters(){
        final StringBuilder AjaxParameters = new StringBuilder("");
        if (AjaxParam != null){
            for (final String parameter : AjaxParam){
                AjaxParameters.append(parameter).append("&");
            }
        }
        return AjaxParameters.toString();
    }

    public static synchronized void addAjaxParameter(final String key, final String value){
        AjaxParam.add(key+":"+value);
    }

    public void clearAjaxParameters(){
        AjaxParam.clear();
    }

}
