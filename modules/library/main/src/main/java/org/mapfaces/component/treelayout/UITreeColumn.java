package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractTreeColumn;


public class UITreeColumn extends UIAbstractTreeColumn {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";    

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }



}
