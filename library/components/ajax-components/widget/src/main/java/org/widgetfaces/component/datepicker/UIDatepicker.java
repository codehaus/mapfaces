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
package org.widgetfaces.component.datepicker;

import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 * <p>This Datepicker component using script for MooTools provides the
 * functionality for date suggestion and completion.</p>
 * <p>
 * List of attributes :
 * <ul>
 * <li><b>enableAjax</b> - (boolean) enable or disable ajax request for the component</li>
 * </ul>
 * </p>
 * @author kevin delfour
 */
public class UIDatepicker extends HtmlInputText implements AjaxInterface {

    public static final String FAMILY = "org.mapfaces.Datepicker";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.HTMLDatepicker";
    
    /* Fields */
    private boolean enableAjax = false;
    private boolean loadMootools = true;
    private boolean loadCss = true;
    private boolean loadJs = true;
    private String title;

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
        final Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = isEnableAjax();
        values[2] = isLoadMootools();
        values[3] = isLoadJs();
        values[4] = isLoadCss();
        values[5] = getTitle();
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
        setEnableAjax((Boolean) values[1]);
        setLoadMootools((Boolean) values[2]);
        setLoadJs((Boolean) values[3]);
        setLoadCss((Boolean) values[4]);
        setTitle((String)values[5]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }


    /* Accessors */
    

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}