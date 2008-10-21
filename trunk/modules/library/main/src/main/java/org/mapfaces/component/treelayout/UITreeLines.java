package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UITreeLinesBase;

/**
 *
 * @author kevindelfour
 */
public class UITreeLines extends UITreeLinesBase {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeLines";
    private final String FAMILY = "org.mapfaces.treelayout.TreeLines";

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
}
