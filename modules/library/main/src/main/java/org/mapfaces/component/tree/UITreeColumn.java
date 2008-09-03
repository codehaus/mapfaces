package org.mapfaces.component.tree;

import org.mapfaces.component.abstractTree.UIAbstractTreeColumn;

/**
 *
 * @author kevindelfour
 */
public class UITreeColumn extends UIAbstractTreeColumn {

    private final String TREECOLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeColumn";
    private final String TREECOLUMN_COMP_FAMILY = "org.mapfaces.treetable.treepanel.TreeColumn";
    
    @Override
    public String getFamily() {
        return TREECOLUMN_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREECOLUMN_RENDERER_TYPE;
    }
    }
