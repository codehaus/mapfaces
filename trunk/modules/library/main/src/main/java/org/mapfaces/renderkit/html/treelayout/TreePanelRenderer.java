package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreePanelRenderer;
import org.mapfaces.util.treelayout.TreeLayoutUtils;

/**
 *
 * @author kdelfour
 */
public class TreePanelRenderer extends AbstractTreePanelRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list) {
        try {
            TreeLayoutUtils tools = new TreeLayoutUtils();
            tools.createTreeLines(component, node, list);
        } catch (IOException ex) {
            Logger.getLogger(TreePanelRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

}
