/*
 * UIToolBase.java
 *
 * Created on 28 decembre 2007, 15:10
 */

package org.mapfaces.util;

import javax.faces.component.UIComponentBase;

/**
 *
 * @author Mehdi Sidhoum
 */
public abstract class UIToolBase extends UIComponentBase {
    
    private String targetModel;
    private String mouseHandler;
    private String scriptFile;
    
    /** Creates a new instance of UIToolBase */
    public UIToolBase() {
    }

    public String getTargetModel() {
        return targetModel;
    }

    public void setTargetModel(String targetModel) {
        this.targetModel = targetModel;
    }

    public String getMouseHandler() {
        return mouseHandler;
    }

    public void setMouseHandler(String mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public String getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(String scriptFile) {
        this.scriptFile = scriptFile;
    }
    
}
