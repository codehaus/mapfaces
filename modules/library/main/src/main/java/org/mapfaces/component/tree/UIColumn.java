package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractColumn;

/**
 *
 * @author kevindelfour
 */
public class UIColumn extends UIAbstractColumn {

    private final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLColumn";
    private final String COLUMN_COMP_FAMILY = "org.mapfaces.treetable.treepanel.Column";

    @Override
    public String getFamily() {
        return COLUMN_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
    }
