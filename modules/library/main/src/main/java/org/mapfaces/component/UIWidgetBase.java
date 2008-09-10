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
    private UIModelBase UIModel;
    private UIModelBase UITargetModel;
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
        return UITargetModel;
    }

    public void setUITargetModel(UIModelBase model) {
        this.UITargetModel = model;
    }
    public UIModelBase getUIModel() {
        //Applied a lazy load for this critical property.
        return (UIModel == null && this.getParent() instanceof UIModelBase) ? (UIModelBase) this.getParent() : UIModel;
    }

    public void setUIModel(UIModelBase model) {
        this.UIModel = model;
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
        values[2] = UIModel;
        values[3] = UITargetModel;
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
        UIModel = (UIModelBase) values[2];
        UITargetModel = (UIModelBase) values[3];
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
