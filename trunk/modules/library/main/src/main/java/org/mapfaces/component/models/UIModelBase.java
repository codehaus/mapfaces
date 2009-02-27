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

package org.mapfaces.component.models;

import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

import org.mapfaces.models.AbstractModelBase;

/**
 * @author Mehdi Sidhoum (Geomatys).
 */
public abstract class UIModelBase extends UICommand implements StateHolder {

    private String title;
    private String defaultModelUrl;
    private String method;
    private String namespace;
    private Object[] values;

    /*
     *  Debug property
     */
    private boolean debug;
    /*
     *  Ajax component id
     *
     */
    private String ajaxCompId;
    /**
     * This is the model context of this UIcomponent.
     */
    private AbstractModelBase model;

    /** Creates a new instance of UIModelBase */
    public UIModelBase() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDefaultModelUrl() {
        return defaultModelUrl;
    }

    public void setDefaultModelUrl(final String defaultModelUrl) {
        this.defaultModelUrl = defaultModelUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        if (values == null) {
            values = new Object[9];
        }
        values[0] = super.saveState(context);
        values[1] = title;
        values[2] = defaultModelUrl;
        values[3] = method;
        values[4] = namespace;
        //values[5] = JAXBElt;
        values[6] = ajaxCompId;
        values[7] = model;
        values[8] = debug;
        return values;
    }

    @Override
    public void restoreState(final FacesContext context, final Object state) {
        values = (Object[]) state;
        super.restoreState(context, values[0]);

        title           = (String) values[1];
        defaultModelUrl = (String) values[2];
        method          = (String) values[3];
        namespace       = (String) values[4];
        //JAXBElt         = (JAXBElement) values[5];
        ajaxCompId      = (String) values[6];
        model           = (AbstractModelBase) values[7];
        debug           = (Boolean) values[8];
    }

    public String getAjaxCompId() {
        return ajaxCompId;
    }

    public void setAjaxCompId(final String ajaxCompId) {
        this.ajaxCompId = ajaxCompId;
    }


    public boolean isDebug() {
        return debug;
    }

    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

    public AbstractModelBase getModel() {
        return model;
    }

    public void setModel(final AbstractModelBase model) {
        this.model = model;
    }
}
