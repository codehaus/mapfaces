/*
 * UIWidgetBase.java
 *
 * Created on 24 decembre 2007, 15:20
 */

package org.mapfaces.component;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import org.mapfaces.component.models.UIModelBase;

/**
 * 
 * @author Mehdi Sidhoum
 */
public abstract class UIWidgetBase extends UIComponentBase {
    
    private String outputNodeId;    
    private UIModelBase model;
    private UIModelBase targetModel;
    private String autoRefresh;
    private String toolId;
    
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


    public void setOutputNodeId(String outputNodeId) {
        this.outputNodeId = outputNodeId;
    }

    public UIModelBase getUITargetModel() {
        return targetModel;
    }

    public void setUITargetModel(UIModelBase model) {
        this.targetModel = model;
    }
    public UIModelBase getUIModel() {
        return model;
    }

    public void setUIModel(UIModelBase model) {
        this.model = model;
    }
    
    public String getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(String autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }
    
    
    public Object saveState(FacesContext context) {
        Object values[] = new Object[7];
        values[0] = super.saveState(context); 
        values[1] = outputNodeId;
        values[2] = model;
        values[3] = targetModel;
        values[4] = autoRefresh;
        values[5] = toolId;
        values[6] = debug;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]); 
        outputNodeId = (String) values[1];
        model = (UIModelBase) values[2];
        targetModel = (UIModelBase) values[3];
        autoRefresh = (String) values[4];
        toolId = (String) values[5];
        debug = (Boolean) values[6];
    }


    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
