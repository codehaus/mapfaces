package bean;

import org.mapfaces.adapter.owc.Adapter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreetableBean {

    private DefaultMutableTreeNode rootnode,  node,  child,  children;
    private DefaultTreeModel tree, tmp;

    public TreetableBean() {
        String fileUrl = "data/context/owc030Cut.xml";
        tree = Adapter.OWC2Tree(fileUrl);
    }

    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }
}
