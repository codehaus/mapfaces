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
package org.widgetfaces.component.autocompletion;

import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 * <p>This AutoCompletion component using script for MooTools provides the
 * functionality for text suggestion and completion.</p>
 * <p>
 * List of attributes :
 * <ul>
 * <li><b>minLength</b> - (integer) the minimum length of the string the user must
 *  enter before the suggestions are displayed; defaults to 1</li>
 * <li><b>markQuery</b> - (boolean) whether or not to wrap the portion of each
 * suggestion that matches the user entry with a span tag (which gets the css
 * class autocompleter-queried); defaults to true</li>
 * <li><b>maxChoices</b> - (integer) the maximum number of suggestion items to display;
 * defaults to 10</li>
 * <li><b>delay</b> - (integer) the period (in milliseconds) to wait befor the
 * suggestion dropdown is displayed or its items updated after typing in the
 * input; defaults to 400</li>
 * <li><b>autoSubmit</b> - (boolean) whether to submit the form when the user chooses
 * a suggestion item by hitting enter; defaults to false</li>
 * <li><b>overflow</b> - (boolean) If set to true, the maxChoices option is ignored and
 * all the available suggestion items are displayed; defaults to false</li>
 * <li><b>overflowMargin</b> - (integer) if overflow is true and the user moves their
 * selection (using the cursor keys) to an item that is outside the viewable
 * list, this option specifies how far to jump down the suggestions dropdown
 * to show more suggestion items; defaults to 25</li>
 * <li><b>selectFirst</b> - (boolean) whether to automatically select the first
 * suggestion item even if it doesn't completely match the user entry.
 * For instance: if the user types "aj" and the first suggestion is "ajax",
 * a true setting for this option would select that first entry even though it
 * doesn't completely match the user's entry; defaults to false</li>
 * <li><b>filterCase</b> - (boolean) if filter is not defined (and therefore the filter
 * used is the default one) this setting will, if true, filter results using a
 * case sensitive regex; defaults to false</li>
 * <li><b>filterSubset</b> - (boolean) if filter is not defined (and therefore the
 * filter used is the default one) this setting will, if true, allow for matches
 * anywhere in the suggestion, otherwise the user entry must match the beginning
 * of the suggestion; defaults to false</li>
 * <li><b>multiple</b> - (boolean) whether to split the user entered text into multiple
 * values; defaults to false; the following two options affect the behavior of
 * the split.</li>
 * <li><b>services</b> - (string) the list of tokens used by the component, this list must
 * be an array of string</li>
 * <li><b>enableAjax</b> - (boolean) enable or disable ajax request for the component</li>
 * </ul>
 * </p>
 * @author Kevin Delfour (IRD)
 */
public class UIAutocompletion extends HtmlInputText implements AjaxInterface {

    public static final String FAMILY = "org.mapfaces.Autocompletion";
    /* Fields */
    private int minLength = 1;
    private boolean markQuery = true;
    private int maxChoices = 10;
    private int delay = 400;
    private boolean autoSubmit = false;
    private boolean overflow = false;
    private int overflowMargin = 25;
    private boolean selectFirst = false;
    private boolean filterCase = false;
    private boolean filterSubset = true;
    private boolean multiple = true;
    private Object value;
    private Object services;
    private boolean enableAjax = false;

    /* Methods */
    /**
     * <p>Gets the state of the instance as a Serializable Object.</p>
     * <p>If the class that implements this interface has references to instances that implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>saveState(javax.faces.context.FacesContext) method on all those instances as well.</p>
     * <p>This method must not save the state of children and facets. That is done via the StateManager</p>
     * @param context The FacesContext for the current request
     * @return a Serializable Object
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[15];
        values[0] = super.saveState(context);
        values[1] = getMinLength();
        values[2] = isMarkQuery();
        values[3] = getMaxChoices();
        values[4] = getDelay();
        values[5] = isAutoSubmit();
        values[6] = isOverflow();
        values[7] = getOverflowMargin();
        values[8] = isSelectFirst();
        values[9] = isFilterCase();
        values[10] = isFilterSubset();
        values[11] = isMultiple();
        values[12] = getValue();
        values[13] = getServices();
        values[14] = isEnableAjax();
        return values;
    }

    /**
     * <p>Perform any processing required to restore the state from the entries in the state Object.</p>
     * <p>If the class that implements this interface has references to instances that also implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>restoreState(javax.faces.context.FacesContext, java.lang.Object) method on all those instances as well.</p>
     * @param context The FacesContext for the current request
     * @param state the state Object
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setMinLength((Integer) values[1]);
        setMarkQuery((Boolean) values[2]);
        setMaxChoices((Integer) values[3]);
        setDelay((Integer) values[4]);
        setAutoSubmit((Boolean) values[5]);
        setOverflow((Boolean) values[6]);
        setOverflowMargin((Integer) values[7]);
        setSelectFirst((Boolean) values[8]);
        setFilterCase((Boolean) values[9]);
        setFilterSubset((Boolean) values[10]);
        setMultiple((Boolean) values[11]);
        setValue(values[12]);
        setServices(values[13]);
        setEnableAjax((Boolean) values[14]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /* Accessors */
    /**
     * @return the minLength
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * @param minLength the minLength to set
     */
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    /**
     * @return the markQuery
     */
    public boolean isMarkQuery() {
        return markQuery;
    }

    /**
     * @param markQuery the markQuery to set
     */
    public void setMarkQuery(boolean markQuery) {
        this.markQuery = markQuery;
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
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * @return the autoSubmit
     */
    public boolean isAutoSubmit() {
        return autoSubmit;
    }

    /**
     * @param autoSubmit the autoSubmit to set
     */
    public void setAutoSubmit(boolean autoSubmit) {
        this.autoSubmit = autoSubmit;
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
     * @return the selectFirst
     */
    public boolean isSelectFirst() {
        return selectFirst;
    }

    /**
     * @param selectFirst the selectFirst to set
     */
    public void setSelectFirst(boolean selectFirst) {
        this.selectFirst = selectFirst;
    }

    /**
     * @return the filterCase
     */
    public boolean isFilterCase() {
        return filterCase;
    }

    /**
     * @param filterCase the filterCase to set
     */
    public void setFilterCase(boolean filterCase) {
        this.filterCase = filterCase;
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

    /**
     * @param value the value to set
     */
    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the inputValue
     */
    public Object getServices() {
        return services;
    }

    /**
     * @param inputValue the inputValue to set
     */
    public void setServices(Object services) {
        this.services = services;
    }

    /**
     * @return the enableAjax
     */
    public boolean isEnableAjax() {
        return enableAjax;
    }

    /**
     * @param enableAjax the enableAjax to set
     */
    public void setEnableAjax(boolean enableAjax) {
        this.enableAjax = enableAjax;
    }

    /* Handle Ajax request */
    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }
}
