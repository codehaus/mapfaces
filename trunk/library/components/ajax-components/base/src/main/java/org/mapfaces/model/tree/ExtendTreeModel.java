package org.mapfaces.model.tree;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author kevindelfour
 */
public class ExtendTreeModel extends DefaultTreeModel {

    public ExtendTreeModel(ExtendMutableTreeNode node) {
        super(node);
    }

    public ExtendTreeModel(DefaultTreeModel defaultTreeModel) {
        super((DefaultMutableTreeNode) defaultTreeModel.getRoot());
    }

    public boolean isChildAvailable(int index) {
        if (index < 0) {
            Logger.getLogger(ExtendTreeModel.class.getName()).log(Level.INFO, "This index does not exit !");
            return false;
        }

        if (getRowAt(index) == null) {
            return false;
        }
        return true;
    }

    public ExtendMutableTreeNode getRowAt(int rowIndex) {

        if (rowIndex < 0) {
            Logger.getLogger(ExtendTreeModel.class.getName()).log(Level.INFO, "This index does not exit !");
            return null;
        }
        
        if(rowIndex == 0){
            return (ExtendMutableTreeNode) root;
        }

        return getRowAt((ExtendMutableTreeNode) root,rowIndex);
    }

    public ExtendMutableTreeNode getRowAt(ExtendMutableTreeNode node, int rowIndex) {
        if (rowIndex < 0) {
            Logger.getLogger(ExtendTreeModel.class.getName()).log(Level.INFO, "This index does not exit !");
            return null;
        }

        for (int cpt = 0; cpt < node.getChildCount(); cpt++) {
            ExtendMutableTreeNode child = (ExtendMutableTreeNode) node.getChildAt(cpt);
            if (child.getId() == rowIndex) {
                return child;
            } else if (!child.isLeaf()) {
                ExtendMutableTreeNode tmp = getRowAt(child, rowIndex);
                if ( tmp != null){
                    return tmp;
                }
            }
        }
        return null;
    }

    public int length() {
        int length = 0;
        for (int cpt = 0; cpt < root.getChildCount(); cpt++) {
            if (!root.getChildAt(cpt).isLeaf()) {
                final ExtendMutableTreeNode nodeAt = (ExtendMutableTreeNode) root.getChildAt(cpt);
                length += nodeAt.length() + 1;
            } else {
                length++;
            }
        }
        return length;
    }
}
