package org.mapfaces.renderkit.html.tree;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreePanelRenderer;
import org.mapfaces.util.treetable.TreeTableUtils;

/**
 *
 * @author kdelfour
 */
public class TreePanelRenderer extends AbstractTreePanelRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list) {
            try {
            TreeTableUtils tools = new TreeTableUtils();
            tools.createTreeLines(component, node, list);
        } catch (IOException ex) {
            Logger.getLogger(TreePanelRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean debug() {
        return false;
    }
}
