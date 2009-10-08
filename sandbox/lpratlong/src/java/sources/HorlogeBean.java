/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sources;

import java.util.Date;

/**
 * Bean to initialize properties of the component. It's just a bean example.
 * @author leopratlong
 */
public class HorlogeBean {

    private Date date = new Date();
    private boolean loadMootools = true;
    private boolean loadJs = true;
    private boolean outputTop = false;

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
     * @return the outputTop
     */
    public boolean isOutputTop() {
        return outputTop;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setOutputTop(boolean outputTop) {
        this.outputTop = outputTop;
    }
}
