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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    private String name = "ouiiiiiiii je suis le name";
    private String value = "20/11/2009";

    private static final Logger LOGGER = Logger.getLogger(TreetableBean.class.getName());
    private DefaultTreeModel tree;
    private DefaultTreeModel treemodel;
    private String type = "date";
    private Map<Object, String> selectMap;
    private Object selectedKey;
    private List<ComponentDescriptor> testMap;

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
//
        selectMap = new HashMap<Object, String>();
        selectMap.put("val1", "Valeur 1");
        selectMap.put("val2", "Valeur 2");
        selectMap.put("val3", "Valeur 3");
        selectMap.put("val4", "Valeur 4");
        selectMap.put("val5", "Valeur 5");
        selectMap.put("val6", "Valeur 6");
        selectMap.put("val7", "Valeur 7");
        selectMap.put("val8", "Valeur 8");
        selectedKey = "val4";

        TreeItem treeItem = new TreeItem("root", "metadata root");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(treeItem);
        root.add(new DefaultMutableTreeNode(new TreeItem("title1","Citation")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title2","Responsible party")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title3","Geographic Extent")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title4","Contacts")));
        root.add(new DefaultMutableTreeNode(new TreeItem("title5","Tempoaral Extent")));
        treemodel = new DefaultTreeModel(root);

        testMap = new ArrayList<ComponentDescriptor>();
        ComponentDescriptor test = new DefaultComponentDescriptor();
        test.setTitle("title1");
        test.setType("text");
        test.setValue("Valeur1");
        test.setMaxCar(125);
        test.setMandatory(true);
        testMap.add(test);
        test = new DefaultComponentDescriptor();
        test.setTitle("title2");
        test.setType("mail");
        test.setValue("gerard@gmail.com");
        test.setMandatory(false);
        testMap.add(test);
        test = new DefaultComponentDescriptor();
        test.setTitle("title3");
        test.setType("textarea");
        test.setValue("azertyuiopqsdfghjklmwxcvbn0123456789123456789");
        test.setMaxCar(150);
        testMap.add(test);
        test = new DefaultComponentDescriptor();
        test.setTitle("title4");
        test.setType("select");
        test.setValue(selectedKey);
        test.setSelectMap(selectMap);
        testMap.add(test);
        test = new DefaultComponentDescriptor();
        test.setTitle("title5");
        test.setType("date");
        test.setValue("21/11/2009");
        testMap.add(test);
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
        System.out.println("VALUE IN DA BEAN ==> " + this.value);
    }

    /**
     * @return the selectMap
     */
    public Map<Object, String> getSelectMap() {
        return selectMap;
    }

    /**
     * @param selectMap the selectMap to set
     */
    public void setSelectMap(Map<Object, String> selectMap) {
        this.selectMap = selectMap;
    }

    /**
     * @return the selectedKey
     */
    public Object getSelectedKey() {
        return selectedKey;
    }

    /**
     * @param selectedKey the selectedKey to set
     */
    public void setSelectedKey(Object selectedKey) {
        this.selectedKey = selectedKey;
    }

    /**
     * @return the testMap
     */
    public List<ComponentDescriptor> getTestMap() {
        return testMap;
    }

    /**
     * @param testMap the testMap to set
     */
    public void setTestMap(List<ComponentDescriptor> testMap) {
        this.testMap = testMap;
    }

}
