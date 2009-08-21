package org.mapfaces.share.util.tree;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.model.tree.ExtendMutableTreeNode;
import org.mapfaces.model.tree.ExtendTreeModel;

/**
 *
 * @author kevindelfour
 */
public class TreeModelAdapter {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    protected static int length(final ExtendTreeModel tree) {
        return nodeLength((ExtendMutableTreeNode) tree.getRoot());
    }

    protected static int nodeLength(final ExtendMutableTreeNode node) {
        int result = 1;
        for (int i = 0, n = node.getChildCount(); i < n; i++) {
            result += nodeLength((ExtendMutableTreeNode) node.getChildAt(i));
        }
        return result;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /**
     * Method to transform a DefaultMutableTreeNode to a TreeNodeModel
     * @param node the DefaultMutableTreeNode  to change
     * @param id the id give to identify the node as a String
     * @param depth
     * @param row
     * @return a TreeNodeModel node with an id and a content
     */
    public static ExtendMutableTreeNode transformNode(final DefaultMutableTreeNode node, final int id, final int depth, int row) {
        if (node.getUserObject() == null) {
            node.setUserObject("No UserObject");
        }
        final ExtendMutableTreeNode treenode = new ExtendMutableTreeNode(node.getUserObject(), id, depth, true);
        return treenode;
    }

    /**
     * Method to transform a DefaultTreeModel to a TreeTableModel
     * @param tree Initial tree to transform
     * @return a TreeTableModel
     */
    public static ExtendTreeModel defaultTreeModel2Extend(final DefaultTreeModel tree) {

        final AtomicInteger inc = new AtomicInteger();
        int count = inc.get();

        DefaultMutableTreeNode initial_root = (DefaultMutableTreeNode) tree.getRoot();
        final ExtendMutableTreeNode root;
        if (initial_root != null) {
            if (initial_root.getChildCount() == 1) {
                final DefaultMutableTreeNode FirstChild = (DefaultMutableTreeNode) initial_root.getFirstChild();
                initial_root = (DefaultMutableTreeNode) initial_root.getFirstChild();
                root = new ExtendMutableTreeNode(FirstChild.getUserObject(), count, count, true);
            } else {
                root = new ExtendMutableTreeNode(initial_root.getUserObject(), count, count, true);
            }

            count = inc.incrementAndGet();

            final int depthnode = 1;
            for (int i = 0, n = initial_root.getChildCount(); i < n; i++) {
                final DefaultMutableTreeNode child = (DefaultMutableTreeNode) initial_root.getChildAt(i);

                if (initial_root.getChildAt(i).isLeaf()) {
                    final ExtendMutableTreeNode leaf = transformNode(child, count, depthnode, count);
                    root.add(leaf);
                } else {
                    root.add(sstransformTree(root, child, inc));
                }
                count = inc.incrementAndGet();
            }
            return new ExtendTreeModel(root);
        } else {
            return new ExtendTreeModel(new ExtendMutableTreeNode("Tree is empty", 0, 0, true));
        }
    }

    private static ExtendMutableTreeNode sstransformTree(
            ExtendMutableTreeNode root, DefaultMutableTreeNode child, AtomicInteger inc) {
        int count = inc.get();

        final ExtendMutableTreeNode leaf = transformNode(child, count, root.getDepth() + 1, count);

        if (!child.isLeaf()) {
            for (int i = 0, n = child.getChildCount(); i <
                    n; i++) {
                count = inc.incrementAndGet();
                leaf.add(sstransformTree(leaf, (DefaultMutableTreeNode) child.getChildAt(i), inc));
            }

        }

        return leaf;
    }
}
