/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.taglib.tree;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kevindelfour
 */
public class TreeLinesTag extends UIComponentELTag {

    private static final String COLUMN_COMP_TYPE = "org.mapfaces.treetable.treepanel.TreeLines";
    private static final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeLines";

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

