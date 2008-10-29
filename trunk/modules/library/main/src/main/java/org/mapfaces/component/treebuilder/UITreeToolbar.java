package org.mapfaces.component.treebuilder;

import org.mapfaces.component.abstractTree.UITreeToolbarBase;

/**
 *
 * @author kdelfour
 */
public class UITreeToolbar extends UITreeToolbarBase{

    private final String TOOLBAR_COMP_FAMILY ="org.mapfaces.treebuilder.treepanel.TreeToolbar";
    private final String TOOLBAR_RENDERER_TYPE = "org.mapfaces.renderkit.treebuilder.treepanel.HTMLTreeToolbar";

    @Override
    public String getFamily() {
        return TOOLBAR_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TOOLBAR_RENDERER_TYPE;
    }

}
