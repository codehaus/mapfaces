package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractTreeLines;

/**
 *
 * @author kevindelfour
 */
public class UITreeLines extends UIAbstractTreeLines {

    private final String TREELINES_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeLines";
    private final String TREELINES_COMP_FAMILY = "org.mapfaces.treetable.treepanel.TreeLines";

    @Override
    public String getFamily() {
        return TREELINES_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREELINES_RENDERER_TYPE;
    }
}
