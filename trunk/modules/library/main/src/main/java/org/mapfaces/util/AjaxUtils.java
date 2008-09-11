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
 *
 * @author kdelfour
 */
public class AjaxUtils {
    
    public List<String> AjaxParam = new ArrayList<String>();
    /*
     * Parameter's names
     */
    private final String AJAX_REQUEST_PARAM_KEY = "org.mapfaces.ajax.AJAX_REQUEST";
    private final String AJAX_CONTAINER_ID_KEY = "org.mapfaces.ajax.AJAX_CONTAINER_ID";
    private final String AJAX_PANEL_ID_KEY = "org.mapfaces.ajax.AJAX_PANEL_ID";
    private final String AJAX_FORM_ID_KEY = "org.mapfaces.ajax.AJAX_FORM_ID";
    private final String AJAX_NODE_ID_KEY = "org.mapfaces.ajax.AJAX_NODE_ID";
    private final String AJAX_COMPONENT_ID_KEY = "org.mapfaces.ajax.AJAX_COMPONENT_ID";
    private final String AJAX_CHECKOPTION_ID_KEY = "org.mapfaces.ajax.AJAX_CHECKOPTION_ID";
    private final String AJAX_RENDERCHILD_ID_KEY = "org.mapfaces.ajax.AJAX_RENDERCHILD_ID_ID";
    private final String AJAX_COMPONENT_VALUE_KEY = "org.mapfaces.ajax.AJAX_COMPONENT_VALUE";
    private final String AJAX_TARGET_ID_KEY = "org.mapfaces.ajax.AJAX_TARGET_ID";
    private final String AJAX_LAYER_ID = "org.mapfaces.ajax.AJAX_LAYER_ID";
    
    /**
     * Constructor
     */
    public AjaxUtils(){
        //Erase all previous parameters
        clearAjaxParameters();
    }

    public String getAJAX_LAYER_ID() {
        return AJAX_LAYER_ID;
    }
    /**
     * 
     * @param method
     * @param url
     * @param parameters
     * @return
     */
    public String getRequestJs (String method, String url, String parameters){
//        return "var requete = new Request({method: '"+method+"', url: '" +url+ "'}).send('" +parameters+ "');";
        return "var requete = new Request({method: '"+method+"',url: '" +url+ "'}).send('" +parameters+ "');";
    }

    /**
     * 
     * @param request
     * @return
     */
    public String getAjaxServer(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }
    
    /**
     * 
     * @param params
     * @return
     */
    public String getAjaxParameters(){
        String AjaxParameters = "";
        if (AjaxParam != null){
            for (String parameter : AjaxParam){
                    AjaxParameters +=parameter+"&";
            }
        }
        return AjaxParameters;
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void addAjaxParameter(String key, String value){
        AjaxParam.add(key+"="+value);
    }
    
    /**
     * 
     */
    public void clearAjaxParameters(){
        AjaxParam.clear();
    }
    
    /* ##################################################################### */
    
    /**
     * 
     * @return
     */
    public String getAJAX_REQUEST_PARAM_KEY() {
        return AJAX_REQUEST_PARAM_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_CONTAINER_ID_KEY() {
        return AJAX_CONTAINER_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_FORM_ID_KEY() {
        return AJAX_FORM_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_NODE_ID_KEY() {
        return AJAX_NODE_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_COMPONENT_ID_KEY() {
        return AJAX_COMPONENT_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_CHECKOPTION_ID_KEY() {
        return AJAX_CHECKOPTION_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_RENDERCHILD_ID_KEY() {
        return AJAX_RENDERCHILD_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_COMPONENT_VALUE_KEY() {
        return AJAX_COMPONENT_VALUE_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_PANEL_ID_KEY() {
        return AJAX_PANEL_ID_KEY;
    }

    /**
     * 
     * @return
     */
    public String getAJAX_TARGET_ID_KEY() {
        return AJAX_TARGET_ID_KEY;
    }
    
}
