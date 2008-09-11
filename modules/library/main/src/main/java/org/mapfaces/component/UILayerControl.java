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

package org.mapfaces.component;

import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
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
            System.out.println( "[UILayerControl] constructor----------------------");
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
