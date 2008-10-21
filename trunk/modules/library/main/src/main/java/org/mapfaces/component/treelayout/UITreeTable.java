package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UITreeTableBase;

/**
 *
 * @author kdelfour
 */
public class UITreeTable extends UITreeTableBase {
    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeTable";
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
