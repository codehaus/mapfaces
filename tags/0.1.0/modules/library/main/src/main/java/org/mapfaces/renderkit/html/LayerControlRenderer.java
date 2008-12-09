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
 */
public class LayerControlRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        final UILayerControl comp = (UILayerControl) component;
        final Context model;

        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("[LayerControlRenderer] The model context is null or not supported yet !");
        }
        comp.setTree(Adapter.context2Tree(context, model));

        getWriter().startElement("div", comp);
        getWriter().writeAttribute("id", getClientId(), "id");
        getWriter().writeAttribute("style", getStyle(), "style");

        if (getStyleClass() == null) {
            getWriter().writeAttribute("class", "mfLayerControl", "styleClass");
        } else {
            getWriter().writeAttribute("class", getStyleClass(), "styleClass");
        }
        final DefaultTreeModel tree = comp.getTree();
        if (tree == null) {
            throw new NullPointerException("L'arbre est vide");
        //<mf:TreeTable value="#{treebean.tree}" var="layer" id="Treetable" width="500">
        }

        final String styleTreetable         = comp.getStyleTreeTable();
        final String styleTreePanel         = comp.getStyleTreePanel();
        final String widthTreeColumn        = comp.getWidthTreeColumn();
        final String widthVisibilityColumn  = comp.getWidthVisibilityColumn();
        final String widthOpacityColumn     = comp.getWidthOpacityColumn();
        final String widthElevationColumn   = comp.getWidthElevationColumn();
        final String widthTimeColumn        = comp.getWidthTimeColumn();
        final String titlePanel             = comp.getTitlePanel();
        final String headerTreeColumn       = comp.getHeaderTreeColumn();
        final String styleOddLines          = comp.getStyleOddLines();
        final String styleEvenLines         = comp.getStyleEvenLines();
        final UITreeTable treeTable         = new UITreeTable();

        treeTable.setId("TreeTable");
        treeTable.setTree((new TreeModelsUtils()).transformTree(tree));
        treeTable.setVarName("layer");
        treeTable.setMootools(comp.isMootools());
        treeTable.setMinifyJS(comp.isMinifyJS());
        treeTable.setWidth(450);
        treeTable.setStyle("background-color:#fff;");
        //<mf:TreePanel header="true" id="panel1" title="A tree" rowId="true" >

        final UITreePanel treePanel = new UITreePanel();
        treePanel.setId(treeTable.getId()+"_TreePanel");
        treePanel.setStyleEven(styleEvenLines);
        treePanel.setStyleOdd(styleOddLines);
        treePanel.setHeader(true);
        if (titlePanel == null || titlePanel.isEmpty()) {
            treePanel.setTitle("List of layers");
        } else {
            treePanel.setTitle(titlePanel);
        }
        treePanel.setRowId(false);
        treePanel.setEnableDragDrop(false);
        treePanel.setShowRoot(true);
        treePanel.setStyle(styleTreePanel);
        treeTable.setStyle("border: 1px outset rgb(214, 227, 242);" + styleTreetable);
        // treePanel.setStyle("height:400px;overflow:scroll;");
        //<mf:TreeColumn header="Tree Items" width="300" value="#{layer.name}"/>

        final UITreeColumn tc = new UITreeColumn();
        tc.setId(treePanel.getId() + "_Layers");
        tc.setValue("#{layer.title}");

        if (headerTreeColumn == null || headerTreeColumn.isEmpty()) {
            tc.setHeaderTitle("Layers grouped");
        } else {
            tc.setHeaderTitle(headerTreeColumn);
        }
        if (widthTreeColumn == null || widthTreeColumn.isEmpty()) {
            tc.setWidth("200");
        } else {
            tc.setWidth(widthTreeColumn);
        }
        treePanel.getChildren().add(tc);
        //Add Visibility column
        if (comp.isVisibilityColumn()){

            final UIVisibilityColumn vc = new UIVisibilityColumn();
            vc.setId(vc.getLayerProperty());
            vc.setValue("#{!layer.hidden}");
            vc.setHeaderIcon("/org/mapfaces/resources/img/eye.png");
            vc.setHeaderTitle("Display or Hide the layer in the MapPane component.");
            if (widthVisibilityColumn == null || widthVisibilityColumn.isEmpty()) {
                vc.setWidth("26");
            } else {
                vc.setWidth(widthVisibilityColumn);
            }
            treePanel.getChildren().add(vc);

        }

        //Add Opacity column
        if (comp.isOpacityColumn()) {

            final UIOpacityColumn oc = new UIOpacityColumn();
            oc.setId(oc.getLayerProperty());
            oc.setValue("#{layer.opacity}");
            oc.setHeaderIcon("/org/mapfaces/resources/img/weather_cloudy.png");
            if (widthOpacityColumn == null || widthOpacityColumn.isEmpty()) {
                oc.setWidth("70");
            } else {
                oc.setWidth(widthOpacityColumn);
            }
            treePanel.getChildren().add(oc);

        }

        //Add Elevation column
        if (comp.isElevationColumn()) {

            final UIElevationColumn ec = new UIElevationColumn();
            ec.setId(ec.getLayerProperty());
            ec.setValue("#{layer.userValueElevation}");
            ec.setHeaderIcon("/org/mapfaces/resources/img/weather_cloudy.png");
            if (widthElevationColumn == null || widthElevationColumn.isEmpty()) {
                ec.setWidth("100");
            } else {
                ec.setWidth(widthElevationColumn);
            }
            treePanel.getChildren().add(ec);

        }

        //Add Time column
        if (comp.isTimeColumn()) {
            final UITimeColumn tic = new UITimeColumn();
            tic.setId(tic.getLayerProperty());
            tic.setHeaderTitle("To display the corresponding timeline of a layer.");
            // tic.setValue("#{layer.userValueDate}");
            tic.setHeaderIcon("/org/mapfaces/resources/img/calendar_select.png");
            if (widthTimeColumn == null || widthTimeColumn.isEmpty()) {
                tic.setWidth("28");
            } else {
                tic.setWidth(widthTimeColumn);
            }
            treePanel.getChildren().add(tic);
        }


        if (comp.isLayerInfo() || comp.isColorMapEditor()){

            final UITreeNodeInfo tni = new UITreeNodeInfo();
            tni.setTitle("Info");
            tni.setStyle("border:none;");
            if(comp.isLayerInfo()){
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
            if(comp.isColorMapEditor()){
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
            comp.getChildren().set(component.getChildren().indexOf(treetableTmp), treeTable);
        } else {
            comp.getChildren().add(0, treeTable);
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
