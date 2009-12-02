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

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.mapfaces.models.Layer;

/**
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 * @author Kevin Delfour
 */
public class Utils {

    private final static Logger LOGGER = Logger.getLogger(Utils.class.getName());

    
    /**
     * This method returns a string array from a string and a delimiter, the array contains all tokens.
     * @param str
     * @return
     */
    public static String[] splitStringToArray(String str, String delim) {
        if (str == null) {
            return new String[]{""};
        }

        StringTokenizer tokens = new StringTokenizer(str, delim);
        String[] array = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            array[i] = tokens.nextToken();
            i++;
        }
        return array;
    }
    /**
     * this method returns a string that represents the ids to refresh from a reRender value and a form id.
     * @param formId
     * @param reRenderValue
     * @return
     */
    public static String buildRerenderStringFromString(String formId, String reRenderValue) {
        if (reRenderValue != null) {
            //cleans the string.
            reRenderValue = reRenderValue.replaceAll(" ", "");
            return buildRerenderStringFromArray(formId, splitStringToArray(reRenderValue, ","));
        }
        return null;
    }
    
    /**
     * this method returns a string that represents the ids to refresh from an array of string and the form id
     * @param formId
     * @param idsToRefresh
     * @return
     */
    public static String buildRerenderStringFromArray(String formId, String[] idsToRefresh) {
        String refresh = "";
        for (String ss : idsToRefresh) {
            refresh += formId + ":" + ss + ",";
        }
        if (refresh.endsWith(","))
            refresh = refresh.substring(0, refresh.lastIndexOf(","));
        return refresh;
    }
    
}
