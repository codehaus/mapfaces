/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
     private int DEFAULT_DEPTH_VIEW = 1;

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

    public int getDEFAULT_DEPTH_VIEW() {
        return DEFAULT_DEPTH_VIEW;
    }
    public void setDEFAULT_DEPTH_VIEW(int DEFAULT_DEPTH_VIEW) {
        this.DEFAULT_DEPTH_VIEW = DEFAULT_DEPTH_VIEW;
    }

}
