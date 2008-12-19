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
package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultTreeModel;

import org.mapfaces.adapter.owc.Adapter;
import org.mapfaces.component.UIDimRange;
import org.mapfaces.component.UILayerControl;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.layercontrol.UIOpacityColumn;
import org.mapfaces.component.layercontrol.UITimeColumn;
import org.mapfaces.component.layercontrol.UIVisibilityColumn;
import org.mapfaces.component.treelayout.UITreeColumn;
import org.mapfaces.component.treelayout.UITreeNodeInfo;
import org.mapfaces.component.treelayout.UITreePanel;
import org.mapfaces.component.treelayout.UITreeTable;
import org.mapfaces.models.Context;
import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 * @author Kevin Delfour.
 */
public class LayerControlRenderer extends WidgetBaseRenderer {

    /* Messages */
    private static final String _Info_TreePanel_Title = "List of layers";
    private static final String _Info_Visibility_Title = "Display or Hide the layer in the MapPane component.";
    private static final String _Info_TimeLine_Title = "To display the corresponding timeline of a layer.";
    private static final String _Info_TreeNodeInfo_Title = "More informations";
    /* Error messages*/
    private static final String _Error_Null_Context = "[WARNING] LayerControlRenderer : The model context is null or not supported yet !";
    private static final String _Error_Null_Tree = "[WARNING] LayerControlRenderer : The tree model is null or empty";
    /* Defaults Styles */
    private static final String _Style_Class_LayerControl = "mfLayerControl";
    private static final String _Style_TreeTable = "border:none;";
    private static final String _Style_TreeNodeInfo = "border:none;";
    private static final String _Style_EyeImage_Url = "/org/mapfaces/resources/img/eye.png";
    private static final String _Style_CloudyImage_Url = "/org/mapfaces/resources/img/weather_cloudy.png";
    private static final String _Style_CalendarImage_Url = "/org/mapfaces/resources/img/calendar_select.png";
    /* Defaults Sizes */
    private static final int _Style_Width_Treetable = 450;
    private static final String _Style_Width_Treecolumn = "200";
    private static final String _Style_Width_Visibilitycolumn = "26";
    private static final String _Style_Width_Opacitycolumn = "70";
    private static final String _Style_Width_Elevationcolumn = "100";
    private static final String _Style_Width_Timecolumn = "28";

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        final UILayerControl layerControl = (UILayerControl) component;
        final Context model;

        /* Control */
        if (layerControl.getModel() != null && layerControl.getModel() instanceof Context) {
            model = (Context) layerControl.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException(_Error_Null_Context);
        }
        layerControl.setTree(Adapter.context2Tree(context, model));

        final DefaultTreeModel tree = layerControl.getTree();
        if (tree == null) {
            throw new NullPointerException(_Error_Null_Tree);
        }

        /* Initialisation */
        final String styleTreetable = layerControl.getStyleTreeTable();
        final String styleClassTreeTable = layerControl.getStyleClassTreeTable();
        final String titlePanel = layerControl.getTitlePanel();
        final String styleTreePanel = layerControl.getStyleTreePanel();
        final String styleClassTreePanel = layerControl.getStyleClassTreePanel();
        final String headerTreeColumn = layerControl.getHeaderTreeColumn();
        final String widthTreeColumn = layerControl.getWidthTreeColumn();
        final String widthVisibilityColumn = layerControl.getWidthVisibilityColumn();
        final String widthOpacityColumn = layerControl.getWidthOpacityColumn();
        final String widthElevationColumn = layerControl.getWidthElevationColumn();
        final String widthTimeColumn = layerControl.getWidthTimeColumn();
        final String styleLeafLines = layerControl.getStyleLeafLines();
        final String styleNodeLines = layerControl.getStyleNodeLines();
        final String styleOddLines = layerControl.getStyleOddLines();
        final String styleEvenLines = layerControl.getStyleEvenLines();
        final boolean addMootools = layerControl.isMootools();
        final boolean addMinifyJs = layerControl.isMinifyJS();
        final boolean displayHeader = layerControl.isDisplayHeader();
        final boolean displayAllLayers = layerControl.isDisplayAllLayers();
        final boolean addVisibilityColumn = layerControl.isVisibilityColumn();
        final boolean addOpacityColumn = layerControl.isOpacityColumn();
        final boolean addElevationColumn = layerControl.isElevationColumn();
        final boolean addTimeColumn = layerControl.isTimeColumn();
        final boolean addLayerInfo = layerControl.isLayerInfo();
        final boolean addColorMapEditor = layerControl.isColorMapEditor();
        final boolean activateDnd = layerControl.isActivateDnd();

        /* Start rendering */
        getWriter().startElement("div", layerControl);
        getWriter().writeAttribute("id", getClientId(), "id");
        getWriter().writeAttribute("style", getStyle(), "style");
        if (getStyleClass() == null) {
            getWriter().writeAttribute("class", _Style_Class_LayerControl, "styleClass");
        } else {
            getWriter().writeAttribute("class", getStyleClass(), "styleClass");
        }

        /* -- Treetable Declaration -- */
        final UITreeTable treeTable = new UITreeTable();
        treeTable.setId(layerControl.getId() + "_TreeTable");
        treeTable.setTree((new TreeModelsUtils()).transformTree(tree));
        treeTable.setVarName("layer");
        treeTable.setMootools(addMootools);
        treeTable.setMinifyJS(addMinifyJs);
        treeTable.setStyle(_Style_TreeTable + styleTreetable);
        treeTable.setStyleClass(styleClassTreeTable);

        /* -- TreePanel Declaration -- */
        final UITreePanel treePanel = new UITreePanel();
        treePanel.setId(treeTable.getId() + "_TreePanel");
        treePanel.setStyleLeaf(styleLeafLines);
        treePanel.setStyleNode(styleNodeLines);
        treePanel.setStyleEven(styleEvenLines);
        treePanel.setStyleOdd(styleOddLines);
        treePanel.setStyle(styleTreePanel);
        treePanel.setRowId(false);
        treePanel.setEnableDragDrop(activateDnd);
        treePanel.setShowRoot(true);
        treePanel.setLoadAll(displayAllLayers);
        treePanel.setStyleClass(styleClassTreePanel);
        treePanel.setHeader(displayHeader);
        if (titlePanel == null || titlePanel.isEmpty()) {
            treePanel.setTitle(_Info_TreePanel_Title);
        } else {
            treePanel.setTitle(titlePanel);
        }

        /* -- TreeColumn Declaration -- */
        final UITreeColumn treecolumn = new UITreeColumn();
        treecolumn.setId(treePanel.getId() + "_Layers");
        treecolumn.setValue("#{layer.title}");
        if (headerTreeColumn == null || headerTreeColumn.isEmpty()) {
            treecolumn.setHeaderTitle("Layers grouped");
        } else {
            treecolumn.setHeaderTitle(headerTreeColumn);
        }
        if (widthTreeColumn == null || widthTreeColumn.isEmpty()) {
            treecolumn.setWidth(_Style_Width_Treecolumn);
        } else {
            treecolumn.setWidth(widthTreeColumn);
        }
        treePanel.getChildren().add(treecolumn);

        /* -- VisibilityColumn Declaration -- */
        if (addVisibilityColumn) {
            final UIVisibilityColumn vc = new UIVisibilityColumn();
            vc.setId(vc.getLayerProperty());
            vc.setValue("#{!layer.hidden}");
            vc.setHeaderIcon(_Style_EyeImage_Url);
            vc.setHeaderTitle(_Info_Visibility_Title);
            if (widthVisibilityColumn == null || widthVisibilityColumn.isEmpty()) {
                vc.setWidth(_Style_Width_Visibilitycolumn);
            } else {
                vc.setWidth(widthVisibilityColumn);
            }
            treePanel.getChildren().add(vc);
        }

        /* -- OpacityColumn Declaration -- */
        if (addOpacityColumn) {
            final UIOpacityColumn oc = new UIOpacityColumn();
            oc.setId(oc.getLayerProperty());
            oc.setValue("#{layer.opacity}");
            oc.setHeaderIcon(_Style_CloudyImage_Url);
            if (widthOpacityColumn == null || widthOpacityColumn.isEmpty()) {
                oc.setWidth(_Style_Width_Opacitycolumn);
            } else {
                oc.setWidth(widthOpacityColumn);
            }
            treePanel.getChildren().add(oc);
        }

        /* -- ElevationColumn Declaration -- */
        if (addElevationColumn) {
            final UIElevationColumn ec = new UIElevationColumn();
            ec.setId(ec.getLayerProperty());
            ec.setValue("#{layer.userValueElevation}");
            ec.setHeaderIcon(_Style_CloudyImage_Url);
            if (widthElevationColumn == null || widthElevationColumn.isEmpty()) {
                ec.setWidth(_Style_Width_Elevationcolumn);
            } else {
                ec.setWidth(widthElevationColumn);
            }
            treePanel.getChildren().add(ec);
        }

        /* -- TimeColumn Declaration -- */
        if (addTimeColumn) {
            final UITimeColumn tic = new UITimeColumn();
            tic.setId(tic.getLayerProperty());
            tic.setHeaderTitle(_Info_TimeLine_Title);
            tic.setHeaderIcon(_Style_CalendarImage_Url);
            if (widthTimeColumn == null || widthTimeColumn.isEmpty()) {
                tic.setWidth(_Style_Width_Timecolumn);
            } else {
                tic.setWidth(widthTimeColumn);
            }
            treePanel.getChildren().add(tic);
        }

        if (addLayerInfo || addColorMapEditor) {
            final UITreeNodeInfo tni = new UITreeNodeInfo();
            tni.setTitle(_Info_TreeNodeInfo_Title);
            tni.setStyle(_Style_TreeNodeInfo);
            if (layerControl.isLayerInfo()) {
                final UIOutput o1 = new UIOutput();
                final UIOutput o3 = new UIOutput();
                final UIOutput o4 = new UIOutput();
                final UIOutput o5 = new UIOutput();
                final UIOutput o6 = new UIOutput();
                o4.setValue("Id : #{layer.id}");
                o1.setValue("Name : #{layer.name}");
                o3.setValue("Group : #{layer.group}");
                o5.setValue("Format : #{layer.outputFormat}");
                o6.setValue("Legende : #{layer.legendUrl}");
                tni.getChildren().add(o4);
                tni.getChildren().add(o1);
                tni.getChildren().add(o6);
                tni.getChildren().add(o3);
                tni.getChildren().add(o5);
            }
            if (layerControl.isColorMapEditor()) {
                final UIDimRange dr = new UIDimRange();
                //            dr.setUIModel(getUIModel());
                dr.setValue("#{layer.userValueDimRange}");
                dr.setLayerCompId("#{layer.id}");
                tni.getChildren().add(dr);
            }
            treePanel.getChildren().add(tni);
        }

        treeTable.getChildren().add(treePanel);

        final UIComponent treetableTmp = FacesUtils.findComponentById(context, component, treeTable.getId());
        if (treetableTmp != null) {
            layerControl.getChildren().set(component.getChildren().indexOf(treetableTmp), treeTable);
        } else {
            layerControl.getChildren().add(0, treeTable);
        }

        getWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        getWriter().endElement("div");
        getWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        super.decode(context, component);
    }
}
