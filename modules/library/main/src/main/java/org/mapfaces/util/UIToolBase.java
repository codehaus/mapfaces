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
