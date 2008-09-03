package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;

/**
 *
 * @author kevindelfour
 */
public class UITreeNodeInfo extends UIAbstractTreeNodeInfo {

    private final String TREENODEINFO_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeNodeInfo";
    private final String TREENODEINFO_COMP_FAMILY = "org.mapfaces.treetable.treepanel.treelines.TreeNodeInfo";

    @Override
    public String getFamily() {
        return TREENODEINFO_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREENODEINFO_RENDERER_TYPE;
    }
}