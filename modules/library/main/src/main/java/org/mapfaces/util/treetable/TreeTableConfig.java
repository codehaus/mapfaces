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

package org.mapfaces.util.treetable;

/**
 *
 * @author kevindelfour
 */
public class TreeTableConfig {
    /**
     * Values by default
     */
     private String DEFAULT_SIZE_COLUMN = "100px";
     private static int DEFAULT_DEPTH_VIEW = 2;

     /**
      * Access Methods
      * @return 
      */
    public String getDEFAULT_SIZE_COLUMN() {
        return DEFAULT_SIZE_COLUMN;
    }
    public void setDEFAULT_SIZE_COLUMN(String DEFAULT_SIZE_COLUMN) {
        this.DEFAULT_SIZE_COLUMN = DEFAULT_SIZE_COLUMN;
    }

    public static int getDEFAULT_DEPTH_VIEW() {
        return DEFAULT_DEPTH_VIEW;
    }
    
    public void setDEFAULT_DEPTH_VIEW(int DEFAULT_DEPTH_VIEW) {
        this.DEFAULT_DEPTH_VIEW = DEFAULT_DEPTH_VIEW;
    }

}
