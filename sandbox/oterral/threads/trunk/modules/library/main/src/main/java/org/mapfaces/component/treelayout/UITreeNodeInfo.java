package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;

/**
 *
 * @author kevindelfour
 */
public class UITreeNodeInfo extends UIAbstractTreeNodeInfo{

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeNodeInfo";
    private final String FAMILY = "org.mapfaces.treelayout.TreeNodeInfo";

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

}