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

package org.mapfaces.adapter.owc;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.faces.context.FacesContext;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBException;

import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultContext;
import org.mapfaces.models.DefaultDimension;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.models.layer.DefaultWmsLayer;
import org.mapfaces.models.layer.WmsGetMapEntry;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.Utils;

/**
 * this adapter 
 * 
 * @author Kevin Delfour.
 * @author Mehdi Sidhoum (Geomatys).
 */
public class Adapter {
    private static String rootKey = "root";

    public static DefaultTreeModel OWC2Tree(final String fileUrl) throws JAXBException, UnsupportedEncodingException, FileNotFoundException, MalformedURLException {

        final FacesContext context = FacesContext.getCurrentInstance();
        final Context model = FacesMapUtils.getContext(context, fileUrl, false);
        final DefaultTreeModel tree = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootKey);
        final DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(model);
        final List<Layer> layers = model.getLayers();
        final int depth = 1;

        //Then we get a list layers to construct the tree
        final int n = layers.size();
        for (int id = 0; id < n; id++) {
            final Layer layer = layers.get(id);
            contextOwc.add(new TreeNodeModel(layer, id + 1, depth, id + 1, false));
        }

        root.add(contextOwc);
        tree.setRoot(root);
        return tree;
    }

    /**
     * This method returns a DefaultTreeModel builded from model context.
     * @param context
     * @param model
     * @return
     * @deprecated
     */
    public static DefaultTreeModel context2Tree(final FacesContext context, final Context model) {

        final DefaultTreeModel tree = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootKey);
        final TreeItem treeItemRoot = new TreeItem(model);
        final DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(treeItemRoot);
        final List<Layer> layers = model.getLayers();
        final int depth = 1;

        root.add(contextOwc);

        //Then we get a list layers to construct the tree
        final int n = layers.size();
        for (int id = 0; id < n; id++) {
            final Layer layer = layers.get(id);
            final TreeItem treeItem = new TreeItem(layer);
            final TreeNodeModel item = new TreeNodeModel(treeItem, id + 1, depth, id + 1, false);
            contextOwc.add(item);
        }

        tree.setRoot(root);
        return tree;
    }
    /**
     * @deprecated
     */
    public static DefaultTreeModel contextGrp2Tree(final FacesContext context, final Context model) {

        final DefaultTreeModel tree = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootKey);
        boolean allLayerHaveGrp = true;
        final List<Layer> layers = model.getLayers();
        final int layerId = layers.size() - 1;
        final int n = -1;
        for (int id = layerId; id > n; id--) {
            final Layer layer = layers.get(id);

            if (layer != null && layer.getGroup() != null && !layer.getGroup().equals("Raster") && !layer.getGroup().equals("Shape")) {
                allLayerHaveGrp = false;
            }
        }

        if (allLayerHaveGrp) {
            final TreeItem treeItemRoot = new TreeItem(model);
            final TreeItem treeItemRaster = new TreeItem("Fond de cartes", "Fond de cartes");
            final TreeItem treeItemShape = new TreeItem("Couche de données", "Couche de données");

            final DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(treeItemRoot);
            final DefaultMutableTreeNode contextRaster = new DefaultMutableTreeNode(treeItemRaster);
            final DefaultMutableTreeNode contextShape = new DefaultMutableTreeNode(treeItemShape);


            final int depth = 1;

            contextOwc.add(contextShape);
            contextOwc.add(contextRaster);

            root.add(contextOwc);

            //Then we get a list layers to construct the tree
            for (int id = layerId; id > n; id--) {
                final Layer layer = layers.get(id);
                final TreeItem treeItem = new TreeItem(layer);
                final TreeNodeModel item = new TreeNodeModel(treeItem, id + 1, depth, id + 1, false);
                if (layer != null && layer.getGroup() != null && layer.getGroup().equals("Raster")) {
                    contextRaster.add(item);
                } else {
                    contextShape.add(item);
                }

            }

            tree.setRoot(root);
        } else {
            return context2Tree(context, model);
        }
        return tree;
    }

    /**
     * This method returns a DefaultTreeModel from a context which have layers
     * grouped by group1/group2/.... and  take the MapGroupHierarchiesValues
     * of the layer if exists.
     * It also add an icon if the layer have an image ie for featureLayers.
     * @param model
     * @return
     */
    public static DefaultTreeModel ContextGroupedLayers2Tree(final Context model) {
        final DefaultTreeModel tree = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TreeItem(rootKey));
        final List<Layer> layers = model.getLayers();
        final int depth = 1;
        int i = 0;
        final Map<List, DefaultMutableTreeNode> nodes = new HashMap<List, DefaultMutableTreeNode>();

        for (Layer layer : layers) {
            final List path = new ArrayList();

            if (layer != null && layer.getGroup() != null && !layer.getGroup().equals("")) {
                final String group = layer.getGroup();
                final HashMap<String, Serializable> map = (HashMap) layer.getMapGroupHierarchiesValues();
                final String[] array = Utils.splitStringToArray(group, "/");

                if (array.length != 0) {
                    final String key = array[0];
                    if (map != null && map.get(key) != null) {
                        TreeNodeModel item;
                        path.add(map.get(key));

                        if (!nodes.containsKey(path)) {
                            final TreeItem treeItem = new TreeItem(map.get(key));
                            item = new TreeNodeModel(treeItem, i + 1, depth, i + 1, false);
                            concatNodesFromArray(item, array, map, i, 0, nodes, layer, path);
                            root.add(item);
                            nodes.put(path, item);
                        } else {
                            item = (TreeNodeModel) nodes.get(path);
                            concatNodesFromArray(item, array, map, i, 0, nodes, layer, path);
                        }
                    } else {
                        TreeNodeModel item;
                        path.add(key);

                        if (!nodes.containsKey(path)) {
                            final TreeItem treeItem = new TreeItem(key, key);
                            item = new TreeNodeModel(treeItem, i + 1, depth, i + 1, false);
                            concatNodesFromArray(item, array, map, i, 0, nodes, layer, path);
                            root.add(item);
                            nodes.put(path, item);
                        } else {
                            item = (TreeNodeModel) nodes.get(path);
                            concatNodesFromArray(item, array, map, i, 0, nodes, layer, path);
                        }
                    }
                }
            } else {
                final TreeItem treeItem = new TreeItem(layer);
                final TreeNodeModel item = new TreeNodeModel(treeItem, i + 1, depth, i + 1, false);

                path.add(layer);
                if (!nodes.containsKey(path)) {
                    nodes.put(path, item);
                    root.add(item);
                }
            }
            i++;
        }
        nodes.clear();
        tree.setRoot(root);

//  Use for debugging treenodemodel
//         JFrame frame = new JFrame();
//        JTree jtree = new JTree();
//        jtree.setModel(tree);
//        frame.setContentPane(new JScrollPane(jtree));
//        frame.setSize(640, 480);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);


        return tree;
    }

    public static void concatNodesFromArray(TreeNodeModel node, String[] array, Map<String, Serializable> map, int indexNode, int indexArray, Map<List, DefaultMutableTreeNode> nodes, Layer layer, List path) {

        if (indexArray + 1 < array.length) {
            final String key = array[indexArray + 1];
            if (map != null && map.get(key) != null) {

                final List appendpath = new ArrayList();
                appendpath.addAll(path);
                appendpath.add(map.get(key));

                if (!nodes.containsKey(appendpath)) {
                    final TreeItem treeItem = new TreeItem(map.get(key));
                    final TreeNodeModel item = new TreeNodeModel(treeItem, indexNode + 1, node.getDepth() + 1, indexNode + 1, false);
                    node.add(item);
                    concatNodesFromArray(item, array, map, indexNode, indexArray + 1, nodes, layer, appendpath);
                    nodes.put(appendpath, item);
                } else {
                    concatNodesFromArray((TreeNodeModel) nodes.get(appendpath), array, map, indexNode, indexArray + 1, nodes, layer, appendpath);
                }
            } else {

                final List appendpath = new ArrayList();
                appendpath.addAll(path);
                appendpath.add(key);

                if (!nodes.containsKey(appendpath)) {
                    final TreeItem treeItem = new TreeItem(key, key);
                    final TreeNodeModel item = new TreeNodeModel(treeItem, indexNode + 1, node.getDepth() + 1, indexNode + 1, false);
                    concatNodesFromArray(item, array, map, indexNode, indexArray + 1, nodes, layer, appendpath);
                    node.add(item);
                    nodes.put(appendpath, item);
                } else {
                    concatNodesFromArray((TreeNodeModel) nodes.get(appendpath), array, map, indexNode, indexArray + 1, nodes, layer, appendpath);
                }
            }
        } else {
            final TreeItem treeItemLayer = new TreeItem(layer);

            /**
             * set an icon for the treeitem if an icon has been specified for the layer
             */
            treeItemLayer.setIcon(layer.getIcon());
            
            final TreeNodeModel itemLayer = new TreeNodeModel(treeItemLayer, indexNode + 1, node.getDepth(), indexNode + 1, false);
            
            //Adding children for the layer if it contains elements in the composite list.
            if (layer instanceof DefaultWmsGetMapLayer) {
                final List<WmsGetMapEntry> entriesLayers = ((DefaultWmsGetMapLayer) layer).getComposite();
                if (entriesLayers != null && entriesLayers.size() != 0) {
                    for (WmsGetMapEntry entry : entriesLayers) {
                        final TreeItem treeItemEntryLayer = new TreeItem(entry);
                        final TreeNodeModel itemEntryLayer = new TreeNodeModel(treeItemEntryLayer, indexNode + 1, node.getDepth() + 1, indexNode + 1, false);
                        itemLayer.add(itemEntryLayer);
                    }
                }
            }
            node.add(itemLayer);
        }
    }

    public static void displayTree(DefaultMutableTreeNode node) {
        final Enumeration children = node.children();
        while (children.hasMoreElements()) {
            final DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
            System.out.println("-----   child : " + child);
            displayTree((DefaultMutableTreeNode) child);
        }
    }

    public static void main(String... s) {
        final  DefaultContext context = new DefaultContext();
        final List<WmsLayer> layers = new CopyOnWriteArrayList<WmsLayer>();

        final WmsLayer layer0 = new DefaultWmsLayer();
        layer0.setName("Layer0");
        layer0.setGroup("feature/context");

        final WmsLayer layer1 = new DefaultWmsLayer();
        layer1.setName("Layer1");
        layer1.setGroup("/dimension/feature/context");
        //HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        //layer1.setMapGroupHierarchiesValues(map);

        final WmsLayer layer2 = new DefaultWmsLayer();
        layer2.setName("Layer2");
        layer2.setGroup("dimension/feature/node2/context");
        HashMap<String, Serializable> map2 = new HashMap<String, Serializable>();
//        map2.put("dimension", new DefaultDimension());
        final DefaultFeature f2 = new DefaultFeature();
        f2.setName("feature2");
        final DefaultContext c2 = new DefaultContext();
        c2.setName("context2");
        map2.put("feature", f2);
        map2.put("context", c2);
        layer2.setMapGroupHierarchiesValues(map2);

        final WmsLayer layer3 = new DefaultWmsLayer();
        layer3.setName("Layer3");
        layer3.setGroup("dimension/feature/context");
        final HashMap<String, Serializable> map3 = new HashMap<String, Serializable>();
        final DefaultDimension d3 = new DefaultDimension();
        d3.setName("dimension3");
        final DefaultFeature f3 = new DefaultFeature();
        f3.setName("feature3");
        final DefaultContext c3 = new DefaultContext();
        c3.setName("context3");
        map3.put("dimension", d3);
        map3.put("feature", f3);
        map3.put("context", c3);
        layer3.setMapGroupHierarchiesValues(map3);

        final WmsLayer layer4 = new DefaultWmsLayer();
        layer4.setName("Layer4");
        layer4.setGroup("node4/dimension/feature/context");
       final  HashMap<String, Serializable> map4 = new HashMap<String, Serializable>();
        final DefaultFeature f4 = new DefaultFeature();
        final DefaultContext c4 = new DefaultContext();
        c4.setName("context4");
        f4.setName("feature4");
        map4.put("dimension", d3);
        map4.put("feature", f4);
        map4.put("context", c4);
        layer4.setMapGroupHierarchiesValues(map4);

       final  WmsLayer layer5 = new DefaultWmsLayer();
        layer5.setName("Layer5");
        layer5.setGroup("dimension/feature/context");
        final HashMap<String, Serializable> map5 = new HashMap<String, Serializable>();
        final DefaultContext c5 = new DefaultContext();
        c5.setName("context5");
        map5.put("dimension", d3);
        map5.put("feature", f3);
        map5.put("context", c5);
        layer5.setMapGroupHierarchiesValues(map5);

        final WmsLayer layer6 = new DefaultWmsLayer();
        layer6.setName("Layer6");
        layer6.setGroup("dimension/feature/context");
        final HashMap<String, Serializable> map6 = new HashMap<String, Serializable>();
        map6.put("feature", f4);
        layer6.setMapGroupHierarchiesValues(map6);

        final WmsLayer layer7 = new DefaultWmsLayer();
        layer7.setName("Layer7");
        layer7.setGroup("dimension/feature/context");
        final HashMap<String, Serializable> map7 = new HashMap<String, Serializable>();
        layer7.setMapGroupHierarchiesValues(map7);

        final WmsLayer layer8 = new DefaultWmsLayer();
        layer8.setName("Layer8");
        layer8.setGroup("dimension/feature/context");

        final WmsLayer layer9 = new DefaultWmsLayer();
        layer9.setName("Layer9");

        final WmsLayer layer10 = new DefaultWmsLayer();
        layer10.setName("Layer10");
        layer10.setGroup("dimension/feature/context");
        final HashMap<String, Serializable> map10 = new HashMap<String, Serializable>();
        final DefaultDimension d10 = new DefaultDimension();
        d10.setName("dimension10");
        final DefaultFeature f10 = new DefaultFeature();
        f10.setName("feature10");
        map10.put("dimension", d10);
        map10.put("feature", f10);
        map10.put("context", c3);
        layer10.setMapGroupHierarchiesValues(map10);

        final WmsLayer layer11 = new DefaultWmsLayer();
        layer11.setName("Layer11");
        layer11.setGroup("dimension/node11/feature/context");
        final HashMap<String, Serializable> map11 = new HashMap<String, Serializable>();
        map11.put("dimension", d3);
        map11.put("feature", f3);
        map11.put("context", c5);
        layer11.setMapGroupHierarchiesValues(map11);

        final WmsLayer layer12 = new DefaultWmsLayer();
        layer12.setName("bluemarble");
        //layer12.setGroup("world maps");

        final WmsLayer layer13 = new DefaultWmsLayer();
        layer13.setName("Demis world");
        //layer13.setGroup("world maps");
        
        final DefaultWmsGetMapLayer layer14 = new DefaultWmsGetMapLayer();
        layer14.setGroup("dimension/node11/feature/context");
        final List<WmsGetMapEntry> entires = new ArrayList<WmsGetMapEntry>();
        entires.add(new WmsGetMapEntry());
        entires.add(new WmsGetMapEntry());
        entires.add(new WmsGetMapEntry());
        entires.add(new WmsGetMapEntry());
        entires.add(new WmsGetMapEntry());
        layer14.setComposite(entires);
        
        

        layers.add(layer12);
        layers.add(layer13);
        //layers.add(layer0);
//        layers.add(layer1);
//        layers.add(layer2);
//        layers.add(layer3);
//        layers.add(layer4);
//        layers.add(layer5);
//        layers.add(layer6);
//        layers.add(layer7);
//        layers.add(layer8);
//        layers.add(layer9);
//        layers.add(layer10);
        layers.add(layer11);
        layers.add(layer14);


        context.setLayers((List) layers);

        final DefaultTreeModel tree = ContextGroupedLayers2Tree(context);

        //displayTree((DefaultMutableTreeNode) tree.getRoot());

        final JFrame frame = new JFrame();
        final JTree jtree = new JTree();
        jtree.setModel(tree);
        frame.setContentPane(new JScrollPane(jtree));
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
