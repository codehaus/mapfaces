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

package org.mapfaces.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Kevin Delfour
 */
public class AjaxUtils {

    public final List<String> AjaxParam = new ArrayList<String>();
    /*
     * Parameter's names
     */
    public static final String AJAX_REQUEST_PARAM_KEY   = "org.mapfaces.ajax.AJAX_REQUEST";
    public static final String AJAX_CONTAINER_ID_KEY    = "org.mapfaces.ajax.AJAX_CONTAINER_ID";
    public static final String AJAX_PANEL_ID_KEY        = "org.mapfaces.ajax.AJAX_PANEL_ID";
    public static final String AJAX_FORM_ID_KEY         = "org.mapfaces.ajax.AJAX_FORM_ID";
    public static final String AJAX_NODE_ID_KEY         = "org.mapfaces.ajax.AJAX_NODE_ID";
    public static final String AJAX_COMPONENT_ID_KEY    = "org.mapfaces.ajax.AJAX_COMPONENT_ID";
    public static final String AJAX_CHECKOPTION_ID_KEY  = "org.mapfaces.ajax.AJAX_CHECKOPTION_ID";
    public static final String AJAX_RENDERCHILD_ID_KEY  = "org.mapfaces.ajax.AJAX_RENDERCHILD_ID_ID";
    public static final String AJAX_COMPONENT_VALUE_KEY = "org.mapfaces.ajax.AJAX_COMPONENT_VALUE";
    public static final String AJAX_TARGET_ID_KEY       = "org.mapfaces.ajax.AJAX_TARGET_ID";
    public static final String AJAX_LAYER_ID            = "org.mapfaces.ajax.AJAX_LAYER_ID";
    /*
     * Parameter's name for DND
     */
    public static final String DND_NEW_PARENT_COMPONENT = "org.mapfaces.ajax.DND_NEW_PARENT_COMPONENT";
    public static final String DND_NEW_PARENT_LINE      = "org.mapfaces.ajax.DND_NEW_PARENT_LINE";
    public static final String DND_OLD_PARENT_COMPONENT = "org.mapfaces.ajax.DND_OLD_PARENT_COMPONENT";
    public static final String DND_OLD_PARENT_LINE      = "org.mapfaces.ajax.DND_OLD_PARENT_LINE";
    public static final String DND_POSITION_LINE        = "org.mapfaces.ajax.DND_POSITION_LINE";


    /**
     * Autocompletion listener parameters
     */
    public static final String AUTOCOMPLETION_MODE     = "org.mapfaces.widget.AUTOCOMPLETION_MODE";
    public static final String AUTOCOMPLETION_WS_URL     = "org.mapfaces.widget.AUTOCOMPLETION_WS_URL";
    public static final String AUTOCOMPLETION_VALUE        = "org.mapfaces.widget.AUTOCOMPLETION_VALUE";
    public static final String AUTOCOMPLETION_CLIENTID     = "org.mapfaces.widget.AUTOCOMPLETION_CLIENTID";
    
    /**
     * Constructor
     */
    public AjaxUtils(){
        //Erase all previous parameters
        clearAjaxParameters();
    }

    public static String getRequestJs (String method, String url, String parameters){
//        return "var requete = new Request({method: '"+method+"', url: '" +url+ "'}).send('" +parameters+ "');";
        return "var requete = new Request({method: '"+method+"',url: '" +url+ "'}).send('" +parameters+ "');";
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

    public synchronized void addAjaxParameter(final String key, final String value){
        AjaxParam.add(key+"="+value);
    }

    public void clearAjaxParameters(){
        AjaxParam.clear();
    }

}
