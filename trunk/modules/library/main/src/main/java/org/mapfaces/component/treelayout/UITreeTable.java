package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractTreeTable;

/**
 *
 * @author kdelfour
 */
public class UITreeTable extends UIAbstractTreeTable {
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
