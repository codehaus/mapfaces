/*
 *    Mapfaces -
 *    http://www.mapfaces.org
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

package org.mapfaces.web.bean;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.geotoolkit.feature.collection.FeatureCollection;
import org.geotoolkit.feature.FeatureCollectionUtilities;
import org.geotoolkit.feature.simple.DefaultSimpleFeature;
import org.geotoolkit.feature.simple.SimpleFeatureTypeBuilder;
import org.geotoolkit.filter.identity.DefaultFeatureId;
import org.geotoolkit.map.FeatureMapLayer;
import org.geotoolkit.map.MapBuilder;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.geotoolkit.style.MutableStyle;
import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.component.tree.UITreePanel;
import org.mapfaces.component.tree.UITreeTable;
import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Feature;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.treetable.TreeTableUtils;
import org.mapfaces.web.model.ModelTreeRow;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author Mehdi Sidhoum (Geomatys)
 */
public class MapBean {

    private static final Logger LOGGER = Logger.getLogger(MapBean.class.getName());
    public MapContext mapContext = null;
    private List<Feature> features = null;
    private DefaultTreeModel exampleModel;
    private List<ModelTreeRow> rowList = new ArrayList<ModelTreeRow>();

    public MapBean() {
        ModelTreeRow node1 = new ModelTreeRow(0, "node1");
        DefaultMutableTreeNode tnode = new DefaultMutableTreeNode(new TreeItem(node1));
        ModelTreeRow leaf1 = new ModelTreeRow(1, "leaf1");
        leaf1.setRead(true);
        leaf1.setWrite(true);
        DefaultMutableTreeNode nleaf1 = new DefaultMutableTreeNode(new TreeItem(leaf1));
        ModelTreeRow leaf2 = new ModelTreeRow(2, "leaf2");
        DefaultMutableTreeNode nleaf2 = new DefaultMutableTreeNode(new TreeItem(leaf2));
        ModelTreeRow node3 = new ModelTreeRow(3, "node3");
        DefaultMutableTreeNode nnode3 = new DefaultMutableTreeNode(new TreeItem(node3));
        ModelTreeRow leaf3 = new ModelTreeRow(4, "leaf3");
        DefaultMutableTreeNode nleaf3 = new DefaultMutableTreeNode(new TreeItem(leaf3));
        rowList.add(node1);
        rowList.add(leaf1);
        rowList.add(leaf2);
        rowList.add(node3);
        rowList.add(leaf3);
        nnode3.add(nleaf3);
        tnode.add(nleaf1);
        tnode.add(nleaf2);
        tnode.add(nnode3);


        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TreeItem("root"));
        root.add(tnode);
        DefaultTreeModel model = new DefaultTreeModel(root);
        exampleModel = model;
    }

    public void updateRows() {
        FacesContext context = FacesContext.getCurrentInstance();
        List<ModelTreeRow> treeRowsList = new ArrayList<ModelTreeRow>();

        UIComponent component = FacesUtils.findComponentById(context, context.getViewRoot(), "treetable");
        if (component instanceof UITreeTable) {
            UITreeTable treetable = (UITreeTable) component;
            UITreePanel treePanel = TreeTableUtils.getChildTreePanel(treetable);
            if (treePanel != null) {
                List<UITreeLines> treeLinesList = new ArrayList<UITreeLines>();
                for (UIComponent child : treePanel.getChildren()) {
                    if (child.getChildCount() > 0 && child.getChildren().get(0) instanceof UITreeLines) {
                        UITreeLines treeline = (UITreeLines) child.getChildren().get(0);
                        treeLinesList.add(treeline);
                    }
                }
                for (UITreeLines treeLineComp : treeLinesList) {
                    Object userObject = null;
                    TreeItem ti = null;
                    if (treeLineComp.getNodeInstance().getUserObject() instanceof TreeItem) {
                        ti = (TreeItem) treeLineComp.getNodeInstance().getUserObject();
                        userObject = ti.getUserObject();
                    }

                    for (UIComponent treelineChild : treeLineComp.getChildren()) {
                        if (treelineChild instanceof UICheckColumn) {
                            UICheckColumn checkColumn = (UICheckColumn) treelineChild;
                            if (checkColumn.getChildCount() > 0 && checkColumn.getChildren().get(0) instanceof HtmlSelectBooleanCheckbox) {
                                HtmlSelectBooleanCheckbox selectBooleanCheckbox = (HtmlSelectBooleanCheckbox) checkColumn.getChildren().get(0);
//                                System.out.println("===>  value = "+selectBooleanCheckbox.getValue()+"    property = "+checkColumn.getValueExpression("value").getExpressionString());

                                ELContext elContext = context.getELContext();
                                String property = checkColumn.getValueExpression("value").getExpressionString();
                                String prop = property.substring(property.lastIndexOf("."));
                                String pro = prop.substring(1, prop.lastIndexOf("}"));

                                Object value = selectBooleanCheckbox.getValue();
                                elContext.getELResolver().setValue(elContext, userObject, pro, value);
                            }
                        }
                    }
                    treeRowsList.add((ModelTreeRow) userObject);
                }
            }

        }


//
//        int countModels = rowsList.size();
//        for (int i = 1; i <= countModels; i++) {
//            String idCheckboxRead = "main_form:check_treepanel_readrightsRoleCheckbox_" + i + "_userObject";
//            UIComponent compRead = FacesUtils.findComponentByClientId(context, context.getViewRoot(), idCheckboxRead);
//            String idCheckboxWrite = "main_form:check_treepanel_writerightsRoleCheckbox_" + i + "_userObject";
//            UIComponent compWrite = FacesUtils.findComponentByClientId(context, context.getViewRoot(), idCheckboxWrite);
//            if (compRead != null && compWrite != null) {
//                if (compRead instanceof HtmlSelectBooleanCheckbox && compWrite instanceof HtmlSelectBooleanCheckbox) {
//                    HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) compRead;
//                    HtmlSelectBooleanCheckbox checkbox2 = (HtmlSelectBooleanCheckbox) compWrite;
//                    boolean read = checkbox.isSelected();
//                    boolean write = checkbox2.isSelected();
//
//                    //getting the treeItem
//                    if (checkbox.getParent().getParent() instanceof UITreeLines) {
//                        UITreeLines treeLine = (UITreeLines) checkbox.getParent().getParent();
//                        if (treeLine.getNodeInstance().getUserObject() instanceof TreeItem) {
//                            TreeItem ti = (TreeItem) treeLine.getNodeInstance().getUserObject();
//                            if (ti.getUserObject() instanceof ModelTreeRow) {
//                                ModelTreeRow treeRow = (ModelTreeRow) ti.getUserObject();
//                                treeRow.setRead(read);
//                                treeRow.setWrite(write);
//                                treeRowsList.add(treeRow);
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
        rowList = treeRowsList;
    }

    /**
     * This method returns a list that contains all userobject which are instance of Class c and contained into the treeitem of all nodes.
     * @param treeModel
     * @param c
     * @return
     */
    public static List<Object> mfTreeAsList(DefaultTreeModel treeModel, Class c) {
        List<Object> result = result = new ArrayList<Object>();
        if (treeModel != null) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
            appendNodeToList(root, result, c);
        }
        return result;

    }

    public static void appendNodeToList(DefaultMutableTreeNode node, List list, Class c) {
        Object obj = node.getUserObject();
        if (obj instanceof TreeItem) {
            Object treeItemUserObject = ((TreeItem) obj).getUserObject();
            if (c.isInstance(treeItemUserObject) && !list.contains(treeItemUserObject)) {
                list.add(treeItemUserObject);
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                appendNodeToList((DefaultMutableTreeNode) node.getChildAt(i), list, c);
            }
        }
    }

    public MapContext getMapContext() {
        if (mapContext == null) {
            Context model = (Context) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("model");
            System.out.println("##################### model ? " + model);
            if (model != null) {
                final String srs = model.getSrs();
                final CoordinateReferenceSystem crs;
                try {
                    crs = CRS.decode(srs);
                } catch (FactoryException ex) {
                    LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
                    return null;
                }
                mapContext = MapBuilder.createContext(crs);
            }
        }

        System.out.println("##################### mapContext ? " + mapContext);
        return mapContext;
    }

    public void addMapContextLayer(ActionEvent actionEvent) {
        try {

            final MutableStyle mutableStyle;
            //building a FeatureCollection for this layer.
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = FeatureCollectionUtilities.createCollection();
            long featureId = 0;
            SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
            List<Feature> features = buildFeatureList("EPSG:4326");
            mutableStyle = FacesUtils.createStyle("http://localhost:8084/mf/resource/skin/default/img/europa.gif", 10, 0, new Integer(String.valueOf(Math.round(Math.random()) * 10)));
            if (features != null && features.size() != 0) {
                Feature f = features.get(0);
                builder.setName(f.getName());
                builder.setCRS(f.getCrs());
                for (String key : f.getAttributes().keySet()) {
                    if (key.equals("geometry")) {
                        builder.add(key, Geometry.class);
                    } else {
                        builder.add(key, f.getAttributes().get(key).getClass());
                    }
                }
            }
            SimpleFeatureType sft = builder.buildFeatureType();
            for (Feature f : features) {
                List<Object> objects = new ArrayList<Object>();
                for (String key : f.getAttributes().keySet()) {
                    objects.add(f.getAttributes().get(key));
                }

                SimpleFeature sf = new DefaultSimpleFeature(objects, sft, new DefaultFeatureId(String.valueOf(featureId)));
                featureCollection.add(sf);
                featureId++;
            }
            FeatureMapLayer layer = MapBuilder.createFeatureLayer(featureCollection, mutableStyle);
            if (mapContext != null) {
                mapContext.layers().add(layer);
                System.out.println("Le mapConetxt a " + mapContext.layers().size());
            } else {
                System.out.println("MapContext is null");
            }
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    public void addFeatureLayer(ActionEvent actionEvent) {
        features = buildFeatureList("EPSG:4326");
    }

    public static List<Feature> buildFeatureList(String srs) {
        List<Feature> result = new ArrayList<Feature>();
        GeometryFactory geomBuilder = new GeometryFactory();

        for (int i = 0; i < 100; i++) {
            DefaultFeature feature = new DefaultFeature();
            feature.setName("Feature " + i);
            try {
                feature.setCrs((DefaultGeographicCRS) CRS.decode(srs));
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            Map<String, Serializable> attributes = new HashMap<String, Serializable>();
            List<Object> objects = new ArrayList<Object>();
            double x1 = (Math.random() - Math.random()) * 180;
            double x2 = (Math.random() - Math.random()) * 180;
            double y1 = (Math.random() - Math.random()) * 90;
            double y2 = (Math.random() - Math.random()) * 90;
            double minx = Math.min(x1, x2);
            double miny = Math.min(y1, y2);
            double maxx = Math.max(x1, x2);
            double maxy = Math.max(y1, y2);
            Coordinate[] coords = new Coordinate[]{
                new Coordinate(minx, miny),
                new Coordinate(minx, maxy),
                new Coordinate(maxx, maxy),
                new Coordinate(maxx, miny),
                new Coordinate(minx, miny),};
            LinearRing linear = geomBuilder.createLinearRing(coords);
            Geometry geometry = geomBuilder.createPolygon(linear, new LinearRing[0]);

            final String featuretype;

            if (geometry.getArea() == 0) {
                featuretype = Feature.POINT;
                geometry = geomBuilder.createPoint(coords[0]);
            } else {
                featuretype = Feature.POLYGON;
            }

            objects.add(geometry);

            attributes.put("geometry", geometry);
            attributes.put("type", featuretype);
            attributes.put("toponym", "feature name");
            attributes.put("title", "title feature");
            attributes.put("object", "object attached for this feature ie: Result object");
            //  you can add more attributes for this feature.
            feature.setAttributes(attributes);
            feature.setGeometry(geometry);

            result.add(feature);
        }

        return result;
    }

    public void clearCache() {
        System.out.println("Map bean : clear cache ... Done");
    }

    public void dispose() {
        System.out.println("Map bean : dispose ... Done");
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    /**
     * @return the exampleModel
     */
    public DefaultTreeModel getExampleModel() {
        return exampleModel;
    }

    /**
     * @param exampleModel the exampleModel to set
     */
    public void setExampleModel(DefaultTreeModel exampleModel) {
        this.exampleModel = exampleModel;
    }

    /**
     * @return the rowList
     */
    public List<ModelTreeRow> getRowList() {
        return rowList;
    }

    /**
     * @param rowList the rowList to set
     */
    public void setRowList(List<ModelTreeRow> rowList) {
        this.rowList = rowList;
    }
}
