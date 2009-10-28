/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.demo.bean;

import java.util.Date;

/**
 * Bean to initialize properties of the component. It's just a bean example.
 * @author leopratlong (Geomatys)
 */
public class TimePickerBean {

    private Date date = new Date();
    private boolean loadMootools = true;
    private boolean loadJs = true;
    private String targetInput = "formId:inputTargeted";
    private boolean targetInputActif = true;
    private boolean rendered = true;

    public TimePickerBean(){
        
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the loadMootools
     */
    public boolean isLoadMootools() {
        return loadMootools;
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(boolean loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @return the loadJs
     */
    public boolean isLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
    }

    /**
     * @return the rendered
     */
    public boolean isRendered() {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    /**
     * @return the targetInput
     */
    public String getTargetInput() {
        return targetInput;
    }

    /**
     * @param targetInput the targetInput to set
     */
    public void setTargetInput(String targetInput) {
        this.targetInput = targetInput;
    }

    /**
     * @return the targetInputActif
     */
    public boolean isTargetInputActif() {
        return targetInputActif;
    }

    /**
     * @param targetInputActif the targetInputActif to set
     */
    public void setTargetInputActif(boolean targetInputActif) {
        this.targetInputActif = targetInputActif;
    }

}
