package bean;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.mapfaces.adapter.owc.Adapter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreetableBean {

    private DefaultMutableTreeNode rootnode,  node,  child,  children;
    private DefaultTreeModel tree, tmp;

    public TreetableBean() {
        String fileUrl = "data/context/owc030Cut.xml";
        try {
            tree = Adapter.OWC2Tree(fileUrl);
        } catch (JAXBException ex) {
            Logger.getLogger(TreetableBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TreetableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }
}
