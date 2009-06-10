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

import javax.swing.tree.DefaultTreeModel;

/**
 *  A TreeTableModel with TreeNodeModel node
 * @author Kevin Delfour (Geomatys)
 */
public class TreeTableModel extends DefaultTreeModel {

    /**
     * Creates a tree table in which any node can have children.
     * @param node
     */
    public TreeTableModel(TreeNodeModel node) {
        super(node);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TreeNodeModel getRoot() {
        return (TreeNodeModel) super.getRoot();
    }

    public TreeNodeModel getChildAt(final int index) {
        final TreeNodeModel node = (TreeNodeModel) getRoot().getChildAt(index);
        //System.out.println("CHILD : return (TreeNodeModel) getRoot().getChildAt(" + index + "); " + node.toString());
        return node;
    }

    public TreeNodeModel getById(final int index) {
        final TreeNodeModel node = (TreeNodeModel) getRoot();
        return ssgetById(node, index);
    }

    public TreeNodeModel ssgetById(final TreeNodeModel node, final int index) {

        for (int i=0,n=node.getChildCount(); i<n; i++) {
            final TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            if (currentNode.getId() == index) {
                return currentNode;
            } else if (!currentNode.isLeaf()) {
                 TreeNodeModel search = ssgetById(currentNode, index);
                if (search!=null){
                    return search;
                }
            }
        }
        return null;
    }
}
