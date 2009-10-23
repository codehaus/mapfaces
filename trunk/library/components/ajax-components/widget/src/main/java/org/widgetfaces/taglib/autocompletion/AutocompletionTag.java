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

package org.widgetfaces.taglib.autocompletion;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.widgetfaces.taglib.BaseELTag;

/**
 * @author Kevin Delfour
 */
public class AutocompletionTag extends BaseELTag {

    /* Fields */
    private ValueExpression debug = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private ValueExpression loadMootools = null;
    private ValueExpression loadCss = null;
    private ValueExpression loadJs = null;
    private static final String COMP_TYPE = "org.mapfaces.Autocompletion";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.HTMLAutocompletion";
    private ValueExpression minLength = null;
    private ValueExpression markQuery = null;
    private ValueExpression maxChoices = null;
    private ValueExpression delay = null;
    private ValueExpression autoSubmit = null;
    private ValueExpression overflow = null;
    private ValueExpression overflowMargin = null;
    private ValueExpression selectFirst = null;
    private ValueExpression filterCase = null;
    private ValueExpression filterSubset = null;
    private ValueExpression multiple = null;
    private ValueExpression onblur = null;
    private ValueExpression onchange = null;
    private ValueExpression onclick = null;
    private ValueExpression ondblclick = null;
    private ValueExpression onfocus = null;
    private ValueExpression onkeydown = null;
    private ValueExpression onkeypress = null;
    private ValueExpression onkeyup = null;
    private ValueExpression onmousedown = null;
    private ValueExpression onmousemove = null;
    private ValueExpression onmouseout = null;
    private ValueExpression onmouseover = null;
    private ValueExpression onmouseup = null;
    private ValueExpression onselect = null;
    private ValueExpression value = null;
    private ValueExpression services = null;
    private ValueExpression enableAjax = null;
    private ValueExpression rendered = null;
    private ValueExpression title = null;

    /* Methods*/
    /**
     * @see getComponentType in class BaseElTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * @see getComponentType in class BaseElTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @see setProperties in class BaseElTag
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("minLength", getMinLength());
        component.setValueExpression("markQuery", getMarkQuery());
        component.setValueExpression("maxChoices", getMaxChoices());
        component.setValueExpression("delay", getDelay());
        component.setValueExpression("autoSubmit", getAutoSubmit());
        component.setValueExpression("overflow", getOverflow());
        component.setValueExpression("overflowMargin", getOverflowMargin());
        component.setValueExpression("selectFirst", getSelectFirst());
        component.setValueExpression("filterCase", getFilterCase());
        component.setValueExpression("filterSubset", getFilterSubset());
        component.setValueExpression("multiple", getMultiple());
        component.setValueExpression("onblur", getOnblur());
        component.setValueExpression("onchange", getOnchange());
        component.setValueExpression("onclick", getOnclick());
        component.setValueExpression("ondblclick", getOndblclick());
        component.setValueExpression("onfocus", getOnfocus());
        component.setValueExpression("onkeydown", getOnkeydown());
        component.setValueExpression("onkeypress", getOnkeypress());
        component.setValueExpression("onkeyup", getOnkeyup());
        component.setValueExpression("onmousedown", getOnmousedown());
        component.setValueExpression("onmousemove", getOnmousemove());
        component.setValueExpression("onmouseout", getOnmouseout());
        component.setValueExpression("onmouseover", getOnmouseover());
        component.setValueExpression("onmouseup", getOnmouseup());
        component.setValueExpression("onselect", getOnselect());
        component.setValueExpression("value", getValue());
        component.setValueExpression("services", getServices());
        component.setValueExpression("enableAjax", getEnableAjax());
        component.setValueExpression("rendered", getRendered());
        component.setValueExpression("loadMootools", getLoadMootools());
        component.setValueExpression("loadJs", getLoadJs());
        component.setValueExpression("title", getTitle());
        component.setValueExpression("debug", getDebug());
        component.setValueExpression("style", getStyle());
        component.setValueExpression("styleClass", getStyleClass());
    }

    /**
     * @see release in class BaseElTag
     */
    @Override
    public void release() {
        super.release();
        setMinLength(null);
        setMarkQuery(null);
        setMaxChoices(null);
        setDelay(null);
        setAutoSubmit(null);
        setOverflow(null);
        setOverflowMargin(null);
        setSelectFirst(null);
        setFilterCase(null);
        setFilterSubset(null);
        setMultiple(null);
        setOnblur(null);
        setOnchange(null);
        setOnclick(null);
        setOndblclick(null);
        setOnfocus(null);
        setOnkeydown(null);
        setOnkeypress(null);
        setOnkeyup(null);
        setOnmousedown(null);
        setOnmousemove(null);
        setOnmouseout(null);
        setOnmouseover(null);
        setOnmouseup(null);
        setOnselect(null);
        setValue(null);
        setServices(null);
        setEnableAjax(null);
        setRendered(null);
        setLoadMootools(null);
        setLoadJs(null);
        setTitle(null);
        setDebug(null);
        setStyle(null);
        setStyleClass(null);
        setLoadCss(null);
    }

    /**
     * @return the minLength
     */
    public ValueExpression getMinLength() {
        return minLength;
    }

    /**
     * @param minLength the minLength to set
     */
    public void setMinLength(ValueExpression minLength) {
        this.minLength = minLength;
    }

    /**
     * @return the markQuery
     */
    public ValueExpression getMarkQuery() {
        return markQuery;
    }

    /**
     * @param markQuery the markQuery to set
     */
    public void setMarkQuery(ValueExpression markQuery) {
        this.markQuery = markQuery;
    }

    /**
     * @return the maxChoices
     */
    public ValueExpression getMaxChoices() {
        return maxChoices;
    }

    /**
     * @param maxChoices the maxChoices to set
     */
    public void setMaxChoices(ValueExpression maxChoices) {
        this.maxChoices = maxChoices;
    }

    /**
     * @return the delay
     */
    public ValueExpression getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(ValueExpression delay) {
        this.delay = delay;
    }

    /**
     * @return the autoSubmit
     */
    public ValueExpression getAutoSubmit() {
        return autoSubmit;
    }

    /**
     * @param autoSubmit the autoSubmit to set
     */
    public void setAutoSubmit(ValueExpression autoSubmit) {
        this.autoSubmit = autoSubmit;
    }

    /**
     * @return the overflow
     */
    public ValueExpression getOverflow() {
        return overflow;
    }

    /**
     * @param overflow the overflow to set
     */
    public void setOverflow(ValueExpression overflow) {
        this.overflow = overflow;
    }

    /**
     * @return the overflowMargin
     */
    public ValueExpression getOverflowMargin() {
        return overflowMargin;
    }

    /**
     * @param overflowMargin the overflowMargin to set
     */
    public void setOverflowMargin(ValueExpression overflowMargin) {
        this.overflowMargin = overflowMargin;
    }

    /**
     * @return the selectFirst
     */
    public ValueExpression getSelectFirst() {
        return selectFirst;
    }

    /**
     * @param selectFirst the selectFirst to set
     */
    public void setSelectFirst(ValueExpression selectFirst) {
        this.selectFirst = selectFirst;
    }

    /**
     * @return the filterCase
     */
    public ValueExpression getFilterCase() {
        return filterCase;
    }

    /**
     * @param filterCase the filterCase to set
     */
    public void setFilterCase(ValueExpression filterCase) {
        this.filterCase = filterCase;
    }

    /**
     * @return the filterSubset
     */
    public ValueExpression getFilterSubset() {
        return filterSubset;
    }

    /**
     * @param filterSubset the filterSubset to set
     */
    public void setFilterSubset(ValueExpression filterSubset) {
        this.filterSubset = filterSubset;
    }

    /**
     * @return the multiple
     */
    public ValueExpression getMultiple() {
        return multiple;
    }

    /**
     * @param multiple the multiple to set
     */
    public void setMultiple(ValueExpression multiple) {
        this.multiple = multiple;
    }

    /**
     * @return the onblur
     */
    public ValueExpression getOnblur() {
        return onblur;
    }

    /**
     * @param onblur the onblur to set
     */
    public void setOnblur(ValueExpression onblur) {
        this.onblur = onblur;
    }

    /**
     * @return the onchange
     */
    public ValueExpression getOnchange() {
        return onchange;
    }

    /**
     * @param onchange the onchange to set
     */
    public void setOnchange(ValueExpression onchange) {
        this.onchange = onchange;
    }

    /**
     * @return the onclick
     */
    public ValueExpression getOnclick() {
        return onclick;
    }

    /**
     * @param onclick the onclick to set
     */
    public void setOnclick(ValueExpression onclick) {
        this.onclick = onclick;
    }

    /**
     * @return the ondblclick
     */
    public ValueExpression getOndblclick() {
        return ondblclick;
    }

    /**
     * @param ondblclick the ondblclick to set
     */
    public void setOndblclick(ValueExpression ondblclick) {
        this.ondblclick = ondblclick;
    }

    /**
     * @return the onfocus
     */
    public ValueExpression getOnfocus() {
        return onfocus;
    }

    /**
     * @param onfocus the onfocus to set
     */
    public void setOnfocus(ValueExpression onfocus) {
        this.onfocus = onfocus;
    }

    /**
     * @return the onkeydown
     */
    public ValueExpression getOnkeydown() {
        return onkeydown;
    }

    /**
     * @param onkeydown the onkeydown to set
     */
    public void setOnkeydown(ValueExpression onkeydown) {
        this.onkeydown = onkeydown;
    }

    /**
     * @return the onkeypress
     */
    public ValueExpression getOnkeypress() {
        return onkeypress;
    }

    /**
     * @param onkeypress the onkeypress to set
     */
    public void setOnkeypress(ValueExpression onkeypress) {
        this.onkeypress = onkeypress;
    }

    /**
     * @return the onkeyup
     */
    public ValueExpression getOnkeyup() {
        return onkeyup;
    }

    /**
     * @param onkeyup the onkeyup to set
     */
    public void setOnkeyup(ValueExpression onkeyup) {
        this.onkeyup = onkeyup;
    }

    /**
     * @return the onmousedown
     */
    public ValueExpression getOnmousedown() {
        return onmousedown;
    }

    /**
     * @param onmousedown the onmousedown to set
     */
    public void setOnmousedown(ValueExpression onmousedown) {
        this.onmousedown = onmousedown;
    }

    /**
     * @return the onmousemove
     */
    public ValueExpression getOnmousemove() {
        return onmousemove;
    }

    /**
     * @param onmousemove the onmousemove to set
     */
    public void setOnmousemove(ValueExpression onmousemove) {
        this.onmousemove = onmousemove;
    }

    /**
     * @return the onmouseout
     */
    public ValueExpression getOnmouseout() {
        return onmouseout;
    }

    /**
     * @param onmouseout the onmouseout to set
     */
    public void setOnmouseout(ValueExpression onmouseout) {
        this.onmouseout = onmouseout;
    }

    /**
     * @return the onmouseover
     */
    public ValueExpression getOnmouseover() {
        return onmouseover;
    }

    /**
     * @param onmouseover the onmouseover to set
     */
    public void setOnmouseover(ValueExpression onmouseover) {
        this.onmouseover = onmouseover;
    }

    /**
     * @return the onmouseup
     */
    public ValueExpression getOnmouseup() {
        return onmouseup;
    }

    /**
     * @param onmouseup the onmouseup to set
     */
    public void setOnmouseup(ValueExpression onmouseup) {
        this.onmouseup = onmouseup;
    }

    /**
     * @return the onselect
     */
    public ValueExpression getOnselect() {
        return onselect;
    }

    /**
     * @param onselect the onselect to set
     */
    public void setOnselect(ValueExpression onselect) {
        this.onselect = onselect;
    }

    /**
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    /**
     * @return the inputValue
     */
    public ValueExpression getServices() {
        return services;
    }

    /**
     * @param inputValue the inputValue to set
     */
    public void setServices(ValueExpression services) {
        this.services = services;
    }

    /**
     * @return the enableAjax
     */
    public ValueExpression getEnableAjax() {
        return enableAjax;
    }

    /**
     * @param enableAjax the enableAjax to set
     */
    public void setEnableAjax(ValueExpression enableAjax) {
        this.enableAjax = enableAjax;
    }

    /**
     * @return the loadMootools
     */
    public ValueExpression getLoadMootools() {
        return loadMootools;
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(ValueExpression loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @return the loadJs
     */
    public ValueExpression getLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(ValueExpression loadJs) {
        this.loadJs = loadJs;
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }

     /**
     * Accessor for debug.
     * @return true if debug is activated, false if debug is not activated
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * Mutator for debug.
     * @param debug New value for debug.
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    /**
     * Accessor for style.
     * @return the style value
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     *  Mutator for style.
     * @param style New value for style
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    /**
     * Accessor for styleClass
     * @return the styleClass value
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     *  Mutator for styleClass.
     * @param styleClass New value for styleClass
     */
    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the loadCss
     */
    public ValueExpression getLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(ValueExpression loadCss) {
        this.loadCss = loadCss;
    }

    /**
     * @return the rendered
     */
    public ValueExpression getRendered() {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(ValueExpression rendered) {
        this.rendered = rendered;
    }
}

