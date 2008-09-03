package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractTreePanel;

/**
 *
 * @author kdelfour
 */
public class UITreePanel extends UIAbstractTreePanel{

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreePanel";
    private final String FAMILY = "javax.faces.Output";

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
}
