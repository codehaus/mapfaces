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

import com.sun.faces.renderkit.html_basic.OutputLinkRenderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultTreeModel;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandLink;
import org.mapfaces.adapter.owc.Adapter;
import org.mapfaces.component.UIDimRange;
import org.mapfaces.component.UILayerControl;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.layercontrol.UIOpacityColumn;
import org.mapfaces.component.layercontrol.UITimeColumn;
import org.mapfaces.component.layercontrol.UIVisibilityColumn;
import org.mapfaces.component.tree.UITreeColumn;
import org.mapfaces.component.tree.UITreeNodeInfo;
import org.mapfaces.component.tree.UITreePanel;
import org.mapfaces.component.tree.UITreeTable;
import org.mapfaces.models.Context;
import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 * @author Kevin Delfour.
 */
public class LayerControlRenderer extends WidgetBaseRenderer {

    /* Messages */
    private static final String INFO_TREEPANEL_TITLE = "List of layers";
    private static final String INFO_VISIBILITY_TITLE = "Display or Hide the layer in the MapPane component.";
    private static final String INFOTIMELINE_TITLE = "To display the corresponding timeline of a layer.";
    private static final String INFOTREENODEINFO_TITLE = "More informations";
    /* Error messages*/
    private static final String ERROR_NULL_CONTEXT = "[WARNING] LayerControlRenderer : The model context is null or not supported yet !";
    private static final String ERROR_NULL_TREE = "[WARNING] LayerControlRenderer : The tree model is null or empty";
    /* Defaults Styles */
    private static final String STYLE_CLASS_LAYERCONTROL = "mfLayerControl";
    private static final String STYLE_TREETABLE = "border:none;";
    private static final String STYLE_TREENODEINFO = "border:none;";
    /* Defaults Sizes */
    //private static final int Style_Width_Treetable = 450;
    private static final String STYLE_WIDTH_TREECOLUMN = "200";
    private static final String STYLE_WIDTH_VISIBILITYCOLUMN = "26";
    private static final String STYLE_WIDTH_OPACITYCOLUMN = "70";
    private static final String STYLE_WIDTH_ELEVATIONCOLUMN = "100";
    private static final String STYLE_WIDTH_TIMECOLUMN = "28";

    /*DefaultsClassName*/
     private static final String VISIBILITYHEADERSTYLECLASS = "eye";
     private static final String OPACITYHEADERSTYLECLASS= "weather_cloudy";
     private static final String ELEVATIONHEADERSTYLECLASS = "seadepth-16";
     private static final String TIMEHEADERSTYLECLASS = "calendar_select";
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        final UILayerControl layerControl = (UILayerControl) component;
        final Context model;

        /* Control */
        if (layerControl.getModel() instanceof Context) {
            model = (Context) layerControl.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException(ERROR_NULL_CONTEXT);
        }
        
        layerControl.setTree(Adapter.ContextGroupedLayers2Tree(model));
//        layerControl.setTree(Adapter.context2Tree(context, model));

        final DefaultTreeModel tree = layerControl.getTree();
        if (tree == null) {
            throw new NullPointerException(ERROR_NULL_TREE);
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
        writer.startElement("div", layerControl);
        writer.writeAttribute("id", getClientId(), "id");
        writer.writeAttribute("style", getStyle(), "style");

        final String styleClass = getStyleClass();
        if (styleClass == null) {
            writer.writeAttribute("class", STYLE_CLASS_LAYERCONTROL, "styleClass");
        } else {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        /* -- Treetable Declaration -- */
        final UITreeTable treeTable = new UITreeTable();
        treeTable.setId(layerControl.getId() + "_TreeTable");
        treeTable.setValue(TreeModelsUtils.transformTree(tree));
        treeTable.setVarName("treeItem");
        treeTable.setMootools(addMootools);
        treeTable.setMinifyJS(addMinifyJs);
        treeTable.setStyle(STYLE_TREETABLE + styleTreetable);
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
        treePanel.setShowRoot(false);
        treePanel.setLoadAll(displayAllLayers);
        treePanel.setStyleClass(styleClassTreePanel);
        treePanel.setHeader(displayHeader);
        if (titlePanel == null || titlePanel.isEmpty()) {
            treePanel.setTitle(INFO_TREEPANEL_TITLE);
        } else {
            treePanel.setTitle(titlePanel);
        }

        /* -- TreeColumn Declaration -- */
        final UITreeColumn treecolumn = new UITreeColumn();
        treecolumn.setId(treePanel.getId() + "_Layers");
        treecolumn.setValue("#{treeItem.title}");
        if (headerTreeColumn == null || headerTreeColumn.isEmpty()) {
            treecolumn.setHeaderTitle("");
        } else {
            treecolumn.setHeaderTitle(headerTreeColumn);
        }
        if (widthTreeColumn == null || widthTreeColumn.isEmpty()) {
            treecolumn.setWidth(STYLE_WIDTH_TREECOLUMN);
        } else {
            treecolumn.setWidth(widthTreeColumn);
        }
        treePanel.getChildren().add(treecolumn);

        /* -- VisibilityColumn Declaration -- */
        if (addVisibilityColumn) {
            final UIVisibilityColumn vc = new UIVisibilityColumn();
            vc.setId(vc.getLayerProperty());
            vc.setValue("#{!treeItem.hidden}");
            vc.setHeaderIcon(VISIBILITYHEADERSTYLECLASS);
            vc.setHeaderTitle(INFO_VISIBILITY_TITLE);
            if (widthVisibilityColumn == null || widthVisibilityColumn.isEmpty()) {
                vc.setWidth(STYLE_WIDTH_VISIBILITYCOLUMN);
            } else {
                vc.setWidth(widthVisibilityColumn);
            }
            treePanel.getChildren().add(vc);
        }

        /* -- OpacityColumn Declaration -- */
        if (addOpacityColumn) {
            final UIOpacityColumn oc = new UIOpacityColumn();
            oc.setId(oc.getLayerProperty());
            oc.setValue("#{treeItem.opacity}");
            oc.setHeaderIcon(OPACITYHEADERSTYLECLASS);
            if (widthOpacityColumn == null || widthOpacityColumn.isEmpty()) {
                oc.setWidth(STYLE_WIDTH_OPACITYCOLUMN);
            } else {
                oc.setWidth(widthOpacityColumn);
            }
            treePanel.getChildren().add(oc);
        }

        /* -- ElevationColumn Declaration -- */
        if (addElevationColumn) {
            final UIElevationColumn ec = new UIElevationColumn();
            ec.setId(ec.getLayerProperty());
            ec.setValue("#{treeItem.userValueElevation}");
            ec.setHeaderIcon(ELEVATIONHEADERSTYLECLASS);
            if (widthElevationColumn == null || widthElevationColumn.isEmpty()) {
                ec.setWidth(STYLE_WIDTH_ELEVATIONCOLUMN);
            } else {
                ec.setWidth(widthElevationColumn);
            }
            treePanel.getChildren().add(ec);
        }

        /* -- TimeColumn Declaration -- */
        if (addTimeColumn) {
            final UITimeColumn tic = new UITimeColumn();
            tic.setId(tic.getLayerProperty());
            tic.setHeaderTitle(INFOTIMELINE_TITLE);
            tic.setHeaderIcon(TIMEHEADERSTYLECLASS);
            if (widthTimeColumn == null || widthTimeColumn.isEmpty()) {
                tic.setWidth(STYLE_WIDTH_TIMECOLUMN);
            } else {
                tic.setWidth(widthTimeColumn);
            }
            treePanel.getChildren().add(tic);
        }

        if (addLayerInfo || addColorMapEditor) {
            final UITreeNodeInfo tni = new UITreeNodeInfo();
            tni.setTitle(INFOTREENODEINFO_TITLE);
            tni.setStyle(STYLE_TREENODEINFO);
            if (layerControl.isLayerInfo()) {

                final UIOutput o4 = new UIOutput();
                o4.setValue("Id : #{treeItem.id}");
                tni.getChildren().add(o4);

                final UIOutput o1 = new UIOutput();
                o1.setValue("Name : #{treeItem.name}");
                tni.getChildren().add(o1);

                final UIOutput o3 = new UIOutput();
                o3.setValue("Group : #{treeItem.group}");
                tni.getChildren().add(o3);

                final UIOutput o5 = new UIOutput();
                o5.setValue("Format : #{treeItem.outputFormat}");
                tni.getChildren().add(o5);

                final HtmlGraphicImage o6 = new HtmlGraphicImage();
                o6.setUrl("#{treeItem.legendUrl}");
                tni.getChildren().add(o6);
//
                final HtmlOutputLink o7 = new HtmlOutputLink();
                o7.setValue("../metaDataPopup.jsf?url=#{treeItem.metadataUrl}");
                o7.setTarget("_blank");
                final UIOutput o71 = new UIOutput();
                o71.setId("metadataUrl_label");
                o71.setValue("link to metadata");
                o7.getChildren().add(o71);
                tni.getChildren().add(o7);

                final HtmlOutputLink o8 = new HtmlOutputLink();
                o8.setValue("#{treeItem.dataUrl}");
                o8.setTarget("_blank");
                final UIOutput o81 = new UIOutput();
                o81.setId("dataUrl_label");
                o81.setValue("link to data");
                o8.getChildren().add(o81);
                tni.getChildren().add(o8);
            }
            if (layerControl.isColorMapEditor()) {
                final UIDimRange dr = new UIDimRange();
                //            dr.setUIModel(getUIModel());
                dr.setValue("#{treeItem.userValueDimRange}");
                dr.setLayerCompId("#{treeItem.id}");
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
