/*
 *    Mapfaces - 
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.models.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Tools for create and manipulate TreeTableModel and TreeNodeModel
 * @author kdelfour
 */
public class TreeModelsUtils {

    private int count = 0;

    /**
     * Get a count of treenode in a treetable
     * @param tree a TreeTableModel to explore
     * @return a count as an Int
     */
    public int getTreeNodeCount(TreeTableModel tree) {
        TreeNodeModel leaf = tree.getRoot();
        return SsTreeNodeCount(leaf);
    }

    private int SsTreeNodeCount(TreeNodeModel node) {
        int result = 0;
        result += 1;
        for (int i = 0; i < node.getChildCount(); i++) {
            result += SsTreeNodeCount((TreeNodeModel) node.getChildAt(i));
        }
        return result;
    }

    /**
     * Method to transform a DefaultMutableTreeNode to a TreeNodeModel
     * @param node the DefaultMutableTreeNode  to change
     * @param id the id give to identify the node as a String
     * @param depth 
     * @param row 
     * @return a TreeNodeModel node with an id and a content
     */
    public TreeNodeModel transformNode(DefaultMutableTreeNode node, int id, int depth, int row) {
        if (node.getUserObject() == null) {
            node.setUserObject("NoName");
        }
        TreeNodeModel treenode = new TreeNodeModel(node.getUserObject(), id, depth, row);
        return treenode;
    }

    /**
     * Method to transform a DefaultTreeModel to a TreeTableModel
     * @param tree Initial tree to transform
     * @return a TreeTableModel
     */
    public TreeTableModel transformTree(DefaultTreeModel tree) {
        DefaultMutableTreeNode initial_root = (DefaultMutableTreeNode) tree.getRoot();

        if (initial_root.getUserObject() == null) {
            initial_root.setUserObject("NoValue");
        }

        TreeNodeModel root = new TreeNodeModel(initial_root.getUserObject(), count, 0, count);

        int depthnode = root.getDepth() + 1;
        for (int i = 0; i < initial_root.getChildCount(); i++) {
            if (initial_root.getChildAt(i).isLeaf()) {
                TreeNodeModel leaf = transformNode((DefaultMutableTreeNode) initial_root.getChildAt(i), count, depthnode, count);
                root.add(leaf);
            } else {
                root.add(sstransformTree(root, (DefaultMutableTreeNode) initial_root.getChildAt(i)));
            }
        }

        TreeTableModel treetablemodel = new TreeTableModel(root);
        return treetablemodel;
    }

    private TreeNodeModel sstransformTree(TreeNodeModel parent, DefaultMutableTreeNode node) {
        count++;
        TreeNodeModel leaf = transformNode(node, count, parent.getDepth() + 1, count);

        if (!node.isLeaf()) {
            for (int i = 0; i < node.getChildCount(); i++) {
                leaf.add(sstransformTree(leaf, (DefaultMutableTreeNode) node.getChildAt(i)));
            }
        }

        return leaf;
    }

    /**
     * Print a TreeTableModel Structure
     * @param tree the tree to print
     */
    public void printTree(TreeTableModel tree) {
        TreeNodeModel root = tree.getRoot();
        if (root.getChildCount() > 0) {
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNodeModel tmp = (TreeNodeModel) root.getChildAt(i);
                if (root.getChildAt(i).isLeaf()) {
                    System.out.println("(leaf)" + tmp + "->" + tmp.getParent());
                } else {
                    ssprintTree(tmp);
                }
            }
        } else {
            System.out.println("Tree is null !");
        }
    }

    private void ssprintTree(TreeNodeModel node) {
        if (node.isLeaf()) {
            System.out.println("(leaf)" + node + "->" + node.getParent());
        } else {
            System.out.println("(node)" + node + "->" + node.getParent());
            for (int i = 0; i < node.getChildCount(); i++) {
                TreeNodeModel tmp = (TreeNodeModel) node.getChildAt(i);
                if (node.getChildAt(i).isLeaf()) {
                    System.out.println("(leaf)" + tmp + "->" + tmp.getParent());
                } else {
                    ssprintTree(tmp);
                }
            }
        }
    }

    /**
     * 
     * @param tree
     * @param movedNode
     * @param targetNode
     * @return
     */
    public TreeTableModel moveTo(TreeTableModel tree, int movedNode, int targetNode) {

        if (tree.getById(movedNode).getParent().equals(tree.getById(targetNode))) {
            //Change the row of the movedNode
        } else {
            TreeNodeModel tmpMmovedNode = tree.getById(movedNode);
            tree.getById(movedNode).removeFromParent();
            tree.getById(targetNode).insert(tmpMmovedNode, 0);
        }
        return tree;
    }

    /**
     * 
     * @param tree
     * @param movedNode
     * @param targetNode
     * @param position
     * @return
     */
    public TreeTableModel moveTo(TreeTableModel tree, int movedNode, int targetNode, int position) {
        if (tree.getById(movedNode).getParent().equals(tree.getById(targetNode))) {
            //Change the row of the movedNode
        } else {
            TreeNodeModel tmpMmovedNode = tree.getById(movedNode);
            tree.getById(movedNode).removeFromParent();
            if (targetNode == 0) {
                tree.getRoot().insert(tmpMmovedNode, position);
            } else {
                if (position > tree.getById(targetNode).getChildCount()) {
                    tree.getById(targetNode).add(tmpMmovedNode);
                } else {
                    tree.getById(targetNode).insert(tmpMmovedNode, position);
                }
            }
        }
        return tree;
    }

    public TreeTableModel insertBefore(TreeTableModel tree, int movedNode, int nodeBefore) {
        TreeNodeModel MovedNode = tree.getById(movedNode);
        TreeNodeModel NodeBefore = tree.getById(nodeBefore);
        TreeNodeModel ParentNode = (TreeNodeModel) NodeBefore.getParent();
        
        int position = ParentNode.getIndex(NodeBefore);
        
        tree.getById(movedNode).removeFromParent();
        int targetNode = ((TreeNodeModel) tree.getById(nodeBefore).getParent()).getId();
        
        if (targetNode == tree.getRoot().getId()) {
            tree.getRoot().insert(MovedNode, position);
        } else {
            tree.getById(targetNode).insert(MovedNode, position);
        }
        return tree;
    }
    
    public TreeTableModel insertAfter(TreeTableModel tree, int movedNode, int nodeAfter) {
        TreeNodeModel MovedNode = tree.getById(movedNode);
        TreeNodeModel NodeBefore = tree.getById(nodeAfter);
        TreeNodeModel ParentNode = (TreeNodeModel) NodeBefore.getParent();
        
        int position = ParentNode.getIndex(NodeBefore) + 1;
        
        tree.getById(movedNode).removeFromParent();
        int targetNode = ((TreeNodeModel) tree.getById(nodeAfter).getParent()).getId();
        
        if (targetNode == tree.getRoot().getId()) {
            tree.getRoot().insert(MovedNode, position);
        } else {
            tree.getById(targetNode).insert(MovedNode, position);
        }
        return tree;
    }
}

