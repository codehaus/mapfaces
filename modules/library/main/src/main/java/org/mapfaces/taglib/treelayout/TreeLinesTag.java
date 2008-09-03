/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.taglib.treelayout;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kevindelfour
 */
public class TreeLinesTag extends UIComponentELTag {

    private final String COLUMN_COMP_TYPE = "org.mapfaces.treelayout.treetable.treepanel.TreeLines";
    private final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.treepanel.HTMLTreeLines";

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    @Override
    public void release() {
        super.release();
    }

    @Override
    public String getComponentType() {
        return COLUMN_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
}

