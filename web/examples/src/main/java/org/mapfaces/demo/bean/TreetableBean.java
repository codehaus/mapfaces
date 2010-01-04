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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.model.ComponentDescriptor;
import org.mapfaces.model.DefaultComponentDescriptor;
import org.mapfaces.models.tree.TreeItem;

/**
 *
 * @author Kevin Delfour
 */
public class TreetableBean {

    private static final Logger LOGGER = Logger.getLogger(TreetableBean.class.getName());
    private DefaultTreeModel tree;
    private DefaultTreeModel treemodel;

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

        ComponentDescriptor value = new DefaultComponentDescriptor();
        value.setTitle("title1");
        value.setKey("key1");
        value.setType("text");
        value.setValue("Valeur1");
        value.setMaxCar(125);
        value.setMandatory(true);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(value);

        value = new DefaultComponentDescriptor();
        value.setTitle("title2");
        value.setKey("key2");
        value.setType("mail");
        value.setValue("gerard@mail.com");
        value.setMandatory(false);
        root.add(new DefaultMutableTreeNode(value));

        value = new DefaultComponentDescriptor();
        value.setTitle("title3");
        value.setKey("key3");
        value.setType("textarea");
        value.setValue("azertyuiop qsdfghjklm wxcvbn 0123456789");
        value.setMaxCar(150);
        root.add(new DefaultMutableTreeNode(value));

        Map<Object, String> selectMap = new HashMap<Object, String>();
        selectMap.put("val1", "Valeur 1");
        selectMap.put("val2", "Valeur 2");
        selectMap.put("val3", "Valeur 3");
        selectMap.put("val4", "Valeur 4");
        selectMap.put("val5", "Valeur 5");
        selectMap.put("val6", "Valeur 6");
        selectMap.put("val7", "Valeur 7");
        selectMap.put("val8", "Valeur 8");
        value = new DefaultComponentDescriptor();
        value.setTitle("title4");
        value.setKey("key4");
        value.setType("select");
        value.setValue("val4");
        value.setSelectMap(selectMap);
        root.add(new DefaultMutableTreeNode(value));

        value = new DefaultComponentDescriptor();
        value.setTitle("title5");
        value.setKey("key5");
        value.setType("date");
        value.setValue("21/11/2009");
        root.add(new DefaultMutableTreeNode(value));
        
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
}
