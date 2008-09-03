/*
 * UILayerControl.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.component;

import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UILayerControl extends UIWidgetBase{
    
    public static final String FAMILIY = "org.mapfaces.LayerControl";
    
    /**
     * Add tree parameters
     * 
     */
    private DefaultMutableTreeNode rootnode,  node,  child,  children;
    private DefaultTreeModel tree, tmp;
    
    public UILayerControl() {
        super();
        if(isDebug())
            System.out.println( "    UILayerControl constructor----------------------");
        setRendererType("org.mapfaces.renderkit.html.LayerControl"); // this component has a renderer
    }
    
    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }

    public void doAction() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getRoot();
        root.setUserObject("node_modified");
        System.out.println("Node modfied !");
        tree.setRoot(root);
    }
    
    @Override
    public String getFamily() {
        return FAMILIY;
    }
    
    @Override
     public Object saveState(FacesContext context) {
        Object values[] = new Object[1];
        values[0] = super.saveState(context); 
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]); 
    }
    
}
