package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractColumn;

/**
 *
 * @author kevindelfour
 */
public class UIColumn extends UIAbstractColumn {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";


//    // =========== ATTRIBUTES ACCESSORS ======================================== //
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }


}
