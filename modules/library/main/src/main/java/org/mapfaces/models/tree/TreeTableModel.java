package org.mapfaces.models.tree;

import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.models.tree.TreeNodeModel;

/**
 *  A TreeTableModel with TreeNodeModel node
 * @author kdelfour
 */
public class TreeTableModel extends DefaultTreeModel {

    /**
     * Creates a tree table in which any node can have children.
     * @param node
     */
    public TreeTableModel(TreeNodeModel node) {
        super(node);
    }

    @Override
    public TreeNodeModel getRoot() {
        return (TreeNodeModel) super.getRoot();
    }

    public TreeNodeModel getChildAt(int index) {
        TreeNodeModel node = (TreeNodeModel) getRoot().getChildAt(index);
        System.out.println("CHILD : return (TreeNodeModel) getRoot().getChildAt(" + index + "); " + node.toString());
        return node;
    }

    public TreeNodeModel getById(int index) {
        TreeNodeModel node = (TreeNodeModel) getRoot();
        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            if (currentNode.getId() == index) {
                return currentNode;
            } else if (currentNode.getChildCount() > 0) {
                TreeNodeModel search = ssgetById(currentNode, index);
                if (search!=null){
                    return search;
                }
            }
        }
        return null;
    }

    public TreeNodeModel ssgetById(TreeNodeModel node, int index) {
        TreeNodeModel nodefound = null;
        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            if (currentNode.getId() == index) {
                return currentNode;
            } else if (currentNode.getChildCount() > 0) {
                 TreeNodeModel search = ssgetById(currentNode, index);
                if (search!=null){
                    return search;
                }
            }
        }
        return null;
    }
}
