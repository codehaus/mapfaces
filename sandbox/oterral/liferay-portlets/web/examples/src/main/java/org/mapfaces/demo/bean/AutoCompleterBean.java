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

/**
 * Bean to initialize properties of the component. It's just a bean example.
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.2
 */
public class AutoCompleterBean {

    private String enteredKeyword = "";
    private boolean rendered = true;
    private boolean loadCss = true;
    private boolean loadJs = true;
    private boolean loadMootools = true;
    private int maxChoices = 5;
    private int overflowMargin = 5;
    private boolean filterSubset = true;
    private boolean overflow = false;
    private boolean multiple=  true;

    /**
     * Creates a new instance of AutoCompleterBean.
     */
    public AutoCompleterBean(){

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
     * @return the loadCss
     */
    public boolean isLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(boolean loadCss) {
        this.loadCss = loadCss;
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
     * @return the enteredKeyword
     */
    public String getEnteredKeyword() {
        return enteredKeyword;
    }

    /**
     * @param enteredKeyword the enteredKeyword to set
     */
    public void setEnteredKeyword(String enteredKeyword) {
        this.enteredKeyword = enteredKeyword;
    }

    /**
     * @return the maxChoices
     */
    public int getMaxChoices() {
        return maxChoices;
    }

    /**
     * @param maxChoices the maxChoices to set
     */
    public void setMaxChoices(int maxChoices) {
        this.maxChoices = maxChoices;
    }

    /**
     * @return the overflowMargin
     */
    public int getOverflowMargin() {
        return overflowMargin;
    }

    /**
     * @param overflowMargin the overflowMargin to set
     */
    public void setOverflowMargin(int overflowMargin) {
        this.overflowMargin = overflowMargin;
    }

    /**
     * @return the filterSubset
     */
    public boolean isFilterSubset() {
        return filterSubset;
    }

    /**
     * @param filterSubset the filterSubset to set
     */
    public void setFilterSubset(boolean filterSubset) {
        this.filterSubset = filterSubset;
    }

    /**
     * @return the overflow
     */
    public boolean isOverflow() {
        return overflow;
    }

    /**
     * @param overflow the overflow to set
     */
    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    /**
     * @return the multiple
     */
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * @param multiple the multiple to set
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


}
