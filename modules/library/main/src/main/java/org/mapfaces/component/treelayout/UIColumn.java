package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIColumnBase;

/**
 *
 * @author kevindelfour
 */
public class UIColumn extends UIColumnBase {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    private String varId;
    
     public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }
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
