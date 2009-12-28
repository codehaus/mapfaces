/*
 *    Mapfaces - http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.demo.bean;

import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.models.tree.TreeItem;

/**
 *
 * @author Kevin Delfour
 */
public class TreetableBean {


    private String name = "ouiiiiiiii je suis le name";
    private String value = "ouiiiiiiii je suis la value";

    private static final Logger LOGGER = Logger.getLogger(TreetableBean.class.getName());
    private DefaultTreeModel tree;
    private DefaultTreeModel treemodel;
    private String type = "readonly";

    public TreetableBean() {
//        String fileUrl = "data/context/owc030Cut.xml";
//        try {
//            tree = Adapter.OWC2Tree(fileUrl);
//
//        } catch (JAXBException ex) {
//            LOGGER.log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            LOGGER.log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            LOGGER.log(Level.SEVERE, null, ex);
//        } catch (MalformedURLException ex) {
//            LOGGER.log(Level.SEVERE, null, ex);
//        }
        TreeItem treeItem = new TreeItem("root", "metadata root");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(treeItem);
        root.add(new DefaultMutableTreeNode(new TreeItem("title1","Citation")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title2","Responsible party")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title3","Geographic Extent")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title4","Contacts")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title5","Tempoaral Extent")));
        treemodel = new DefaultTreeModel(root);

    }

    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }

    /**
     * @return the treemodel
     */
    public DefaultTreeModel getTreemodel() {
        return treemodel;
    }

    /**
     * @param treemodel the treemodel to set
     */
    public void setTreemodel(DefaultTreeModel treemodel) {
        this.treemodel = treemodel;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
