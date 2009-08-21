package org.mapfaces.model.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kevindelfour
 */
public class ExtendMutableTreeNode extends DefaultMutableTreeNode {

    private int id;
    private int depth;

    private boolean rendered;

    public ExtendMutableTreeNode(Object userObject, int id, int depth, boolean rendered) {
        setUserObject(userObject);
        setId(id);
        setDepth(depth);
        setRendered(rendered);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the depth
     */
    @Override
    public int getDepth(){
        return this.depth;
    }

    public int length() {

        int length = 0;
        for (int cpt = 0; cpt < this.getChildCount(); cpt++) {
            if (!this.getChildAt(cpt).isLeaf()) {
                final ExtendMutableTreeNode nodeAt = (ExtendMutableTreeNode) this.getChildAt(cpt);
                length += nodeAt.length() + 1;
            } else {
                length++;
            }
        }
        return length;
    }

    /**
     * @return the rendered
     */
    public boolean isRendered() {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

}
