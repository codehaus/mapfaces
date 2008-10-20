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

/**
 * A simple TreeNode with an id as a String to identify him
 * @author kdelfour
 */
public class TreeNodeModel extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 1537732742707197904L;

    private int id;
    private int depth = 0;
    private int row = 0;
    private boolean checked;

    /**
     * 
     * @param userobj
     * @param id
     * @param depth
     * @param row
     */
    public TreeNodeModel(Object userobj, int id, int depth, int row) {
        super(userobj);
        this.id = id;
        this.row= row;
        this.depth = depth;
        this.checked = true;
    }

    /**
     * 
     * @param userobj
     * @param id
     * @param depth
     * @param check
     */
    public TreeNodeModel(Object userobj, int id, int depth, boolean check) {
        super(userobj);
        this.id = id;
        this.depth = depth;
        this.checked = check;
    }

    /**
     * 
     * @param userobj
     * @param id
     * @param depth
     * @param row
     * @param check
     */
    public TreeNodeModel(Object userobj, int id, int depth, int row, boolean check) {
        super(userobj);
        this.id = id;
        this.row = row;
        this.depth = depth;
        this.checked = check;
    }

    /**
     * Get the text display for this node
     * @return String name of the node
     */
    public String getText() {
        return getUserObject().toString();
    }

    /**
     * Get the id of the node who invoke the method
     * @return a string representation of node's Id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns depth of this node ( the root node has a depth of 0)
     * @return Int
     */
    @Override
    public int getDepth() {
        return depth;
    }

    /**
     * Set the depth of this node
     * @param depth of the node
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Give a string representation of a node
     * @return a string
     */
    @Override
    public String toString() {
        return this.getText();
    }

    /**
     * 
     * @return
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * 
     * @param checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public Object getUserObject() {
        return super.getUserObject();
    }
    
    public void setUserObject(Object obj){       
        super.setUserObject(obj);
    }

    /**
     * 
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * 
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }
}
