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

package org.mapfaces.component;

import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.models.AbstractModelBase;

/**
 * This is the main class of mapfaces components.
 *
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.1
 */
public abstract class UIWidgetBase extends UICommand implements StateHolder {

    private String outputNodeId;
    private AbstractModelBase model;
    private AbstractModelBase targetModel;
    private String autoRefresh;
    private String toolId;

    private Object[] values;

    /*
     *  Debug property
     */
    private boolean debug;

    /**
     * Creates a new instance of UIWidgetBase
     */
    public UIWidgetBase() {
    }

    public String getOutputNodeId() {
        return outputNodeId;
    }

    public void setOutputNodeId(final String outputNodeId) {
        this.outputNodeId = outputNodeId;
    }

    public AbstractModelBase getTargetModel() {
        return targetModel;
    }

    public void setTargetModel(final AbstractModelBase model) {
        this.targetModel = model;
    }

    public AbstractModelBase getModel() {
        //Applied a lazy load for this property.
        return (model == null && this.getParent() instanceof UIModelBase) ? ((UIModelBase) this.getParent()).getModel() : model;
    }

    public void setModel(final AbstractModelBase model) {
        this.model = model;
    }

    public String getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(final String autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(final String toolId) {
        this.toolId = toolId;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        if (values == null) {
            values = new Object[7];
        }

        values[0] = super.saveState(context);
        values[1] = outputNodeId;
        values[2] = model;
        values[3] = targetModel;
        values[4] = autoRefresh;
        values[5] = toolId;
        values[6] = debug;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        values = (Object[]) state;
        super.restoreState(context, values[0]);
        outputNodeId = (String) values[1];
        model = (AbstractModelBase) values[2];
        targetModel = (AbstractModelBase) values[3];
        autoRefresh = (String) values[4];
        toolId = (String) values[5];
        debug = (Boolean) values[6];
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
}
