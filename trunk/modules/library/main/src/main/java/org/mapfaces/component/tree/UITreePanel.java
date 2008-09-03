package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractTreePanel;

/**
 *
 * @author kdelfour
 */
public class UITreePanel extends UIAbstractTreePanel {

    private final String TREEPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.HTMLTreePanel";
    private final String TREEPANEL_COMP_FAMILY = "javax.faces.Output";

    @Override
    public String getFamily() {
        return TREEPANEL_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREEPANEL_RENDERER_TYPE;
    }
}
