package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractTreeTable;

/**
 *
 * @author kdelfour
 */
public class UITreeTable extends UIAbstractTreeTable {

    private final String TREETABLE_RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTreeTable";
    private final String TREETABLE_COMP_FAMILY = "javax.faces.Output";

    @Override
    public String getFamily() {
        return TREETABLE_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREETABLE_RENDERER_TYPE;
    }
}
