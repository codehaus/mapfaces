/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.component;

import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

/**
 *
 * @author leopratlong
 */
public class UITemporal extends HtmlInputText {
    public static final String FAMILY = "org.mapfaces.Temporal";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.Temporal";
    
    /**
     * Define if we have to load the No_Gray TimePicker library on the View page.
     */
    private boolean loadJs = true;
    /**
     * Define the CSS Class of the component.
     */
    private String styleClass;
    /**
     * Flag that indicates if the css resources will be loaded or not.
     */
    private boolean loadCss = true;
    /**
     * Define if we have to load the MooTools library on the View page.
     */
    private boolean loadMootools = true;
    private boolean enableAjax = false;
    
    /**
     * Default constructor.
     * Create a new instance of UIHorloge
     */
    public UITemporal() {
        super();
        setRendererType(RENDERER_TYPE);
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
        values[1] = isLoadJs();
        values[2] = getStyleClass();
        values[3] = isLoadCss();
        values[4] = isLoadMootools();
        values[5] = isEnableAjax();
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
        setLoadJs((Boolean) values[1]);
        setStyleClass((String) values[2]);
        setLoadCss((Boolean) values[3]);
        setLoadMootools((Boolean) values[4]);
        setEnableAjax((Boolean) values[5]);
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
     * @return the styleClass
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
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
}
