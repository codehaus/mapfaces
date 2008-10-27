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
    public static final String AJAX_REQUEST_PARAM_KEY = "org.mapfaces.ajax.AJAX_REQUEST";
    public static final String AJAX_CONTAINER_ID_KEY = "org.mapfaces.ajax.AJAX_CONTAINER_ID";
    public static final String AJAX_PANEL_ID_KEY = "org.mapfaces.ajax.AJAX_PANEL_ID";
    public static final String AJAX_FORM_ID_KEY = "org.mapfaces.ajax.AJAX_FORM_ID";
    public static final String AJAX_NODE_ID_KEY = "org.mapfaces.ajax.AJAX_NODE_ID";
    public static final String AJAX_COMPONENT_ID_KEY = "org.mapfaces.ajax.AJAX_COMPONENT_ID";
    public static final String AJAX_CHECKOPTION_ID_KEY = "org.mapfaces.ajax.AJAX_CHECKOPTION_ID";
    public static final String AJAX_RENDERCHILD_ID_KEY = "org.mapfaces.ajax.AJAX_RENDERCHILD_ID_ID";
    public static final String AJAX_COMPONENT_VALUE_KEY = "org.mapfaces.ajax.AJAX_COMPONENT_VALUE";
    public static final String AJAX_TARGET_ID_KEY = "org.mapfaces.ajax.AJAX_TARGET_ID";
    public static final String AJAX_LAYER_ID = "org.mapfaces.ajax.AJAX_LAYER_ID";
    
    /*
     * Parameter's name for DND 
     */
    private final String DND_NEW_PARENT_COMPONENT = "org.mapfaces.ajax.DND_NEW_PARENT_COMPONENT";
    private final String DND_NEW_PARENT_LINE = "org.mapfaces.ajax.DND_NEW_PARENT_LINE";
    private final String DND_OLD_PARENT_COMPONENT = "org.mapfaces.ajax.DND_OLD_PARENT_COMPONENT";
    private final String DND_OLD_PARENT_LINE = "org.mapfaces.ajax.DND_OLD_PARENT_LINE";
    private final String DND_POSITION_LINE = "org.mapfaces.ajax.DND_POSITION_LINE";
    
    /**
     * Constructor
     */
    public AjaxUtils(){
        //Erase all previous parameters
        clearAjaxParameters();
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
    public synchronized String getAjaxParameters(){
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
    public synchronized void addAjaxParameter(String key, String value){
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
    
    public String getAJAX_LAYER_ID() {
        return AJAX_LAYER_ID;
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

    public String getDND_NEW_PARENT_COMPONENT() {
        return DND_NEW_PARENT_COMPONENT;
    }

    public String getDND_NEW_PARENT_LINE() {
        return DND_NEW_PARENT_LINE;
    }

    public String getDND_OLD_PARENT_COMPONENT() {
        return DND_OLD_PARENT_COMPONENT;
    }

    public String getDND_OLD_PARENT_LINE() {
        return DND_OLD_PARENT_LINE;
    }

    public String getDND_POSITION_LINE() {
        return DND_POSITION_LINE;
    }
}
