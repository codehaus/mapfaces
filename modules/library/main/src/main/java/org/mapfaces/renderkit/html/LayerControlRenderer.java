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

import org.mapfaces.adapter.owc.Adapter;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultTreeModel;
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

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        super.encodeBegin(context, component);

        UILayerControl comp = (UILayerControl) component;
        Context model;
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
        DefaultTreeModel tree = comp.getTree();
        if (tree == null) {
            throw new NullPointerException("L'arbre est vide");
        //<mf:TreeTable value="#{treebean.tree}" var="layer" id="Treetable" width="500">                    
        }

        String styleTreetable = comp.getStyleTreeTable();
        String styleTreePanel = comp.getStyleTreePanel();
        String widthTreeColumn = comp.getWidthTreeColumn();
        String widthVisibilityColumn = comp.getWidthVisibilityColumn();
        String widthOpacityColumn = comp.getWidthOpacityColumn();
        String widthElevationColumn = comp.getWidthElevationColumn();
        String widthTimeColumn = comp.getWidthTimeColumn();
        String titlePanel = comp.getTitlePanel();
        String headerTreeColumn = comp.getHeaderTreeColumn();
        
        UITreeTable treeTable = new UITreeTable();
        treeTable.setId(component.getId()+"TreeTable");
        treeTable.setTree((new TreeModelsUtils()).transformTree(tree));
        treeTable.setVarName("layer");
        treeTable.setWidth(456);
        //<mf:TreePanel header="true" id="panel1" title="A tree" rowId="true" >
        UITreePanel treePanel = new UITreePanel();
        treePanel.setId(component.getId()+"TreePanel");
        treePanel.setHeader(true);
        if (titlePanel == null || titlePanel.equals("")) {
            treePanel.setTitle("List of layers");
        }else {
            treePanel.setTitle(titlePanel);
        }
        
        treePanel.setRowId(false);
        treePanel.setEnableDragDrop(false);
        treePanel.setShowRoot(true);
        treePanel.setStyle(styleTreePanel);
        treeTable.setStyle("border: 1px outset rgb(214, 227, 242);"+styleTreetable);
        // treePanel.setStyle("height:400px;overflow:scroll;");
        //<mf:TreeColumn header="Tree Items" width="300" value="#{layer.name}"/> 
        UITreeColumn tc = new UITreeColumn();
        tc.setId(component.getId()+"Layers");
        tc.setValue("#{layer.title}");
        
        if (headerTreeColumn == null || headerTreeColumn.equals("")) {
            tc.setHeader("Layers grouped");
        }else {
            tc.setHeader(headerTreeColumn);
        }
        if (widthTreeColumn == null || widthTreeColumn.equals("")) {
            tc.setWidth("200");
        } else {
            tc.setWidth(widthTreeColumn);
        }
        

        /* <mf:CheckColumn icon="/org/mapfaces/resources/treetable/images/default/layout/stuck.gif"   id="visible" 
        value="#{layer.visible}"
        width="30"/>*/
        /* UICheckColumn cc = new UICheckColumn();
        cc.setId("Visible");                 
        cc.setValue("#{layer.visible}");
        treePanel.getChildren().add(cc);*/
        /* <mf:CheckColumn icon="/org/mapfaces/resources/treetable/images/default/layout/stuck.gif"   id="visible" 
        value="#{layer.visible}"
        width="30"/>*/
        UIVisibilityColumn vc = new UIVisibilityColumn();
        vc.setId(component.getId()+"Visible");
        vc.setValue("#{!layer.hidden}");
        vc.setIcon("/org/mapfaces/resources/img/eye.png");
        if (widthVisibilityColumn == null || widthVisibilityColumn.equals("")) {
            vc.setWidth("26");
        } else {
            vc.setWidth(widthVisibilityColumn);
        }

        UIOpacityColumn oc = new UIOpacityColumn();
        oc.setId(component.getId()+"Opacity");
        oc.setValue("#{layer.opacity}");
        oc.setIcon("/org/mapfaces/resources/img/weather_cloudy.png");
        if (widthOpacityColumn == null || widthOpacityColumn.equals("")) {
            oc.setWidth("70");
        } else {
            oc.setWidth(widthOpacityColumn);
        }

        UIElevationColumn ec = new UIElevationColumn();
        ec.setId(component.getId()+"Elevation");
        ec.setValue("#{layer.userValueElevation}");
        ec.setIcon("/org/mapfaces/resources/img/weather_cloudy.png");
        if (widthElevationColumn == null || widthElevationColumn.equals("")) {
            ec.setWidth("100");
        } else {
            ec.setWidth(widthElevationColumn);
        }

        UITimeColumn tic = new UITimeColumn();
        tic.setId(component.getId()+"Time");
        // tic.setValue("#{layer.userValueDate}");
        tic.setIcon("/org/mapfaces/resources/img/calendar_select.png");
        if (widthTimeColumn == null || widthTimeColumn.equals("")) {
            tic.setWidth("28");
        } else {
            tic.setWidth(widthTimeColumn);
        }

        UITreeNodeInfo tni = new UITreeNodeInfo();
        tni.setHeader("Info");
        tni.setStyle("border:none;");
        UIOutput o4 = new UIOutput();
        o4.setValue("Id : #{layer.id}");
        UIOutput o1 = new UIOutput();
        o1.setValue("Name : #{layer.name}");
        UIOutput o3 = new UIOutput();
        o3.setValue("Group : #{layer.group}");
        UIOutput o5 = new UIOutput();
        o5.setValue("Format : #{layer.outputFormat}");
//        UIOutput o6 = new UIOutput();
//        o6.setValue("Legende : #{layer.legendUrl}");
        UIDimRange dr = new UIDimRange();
        //dr.setUIModel(getUIModel());
        dr.setValue("#{layer.userValueDimRange}");
        dr.setLayerCompId("#{layer.id}");
        //dr.setDebug(true);
//            HtmlGraphicImage img = new HtmlGraphicImage();
//            img.setId("img_" + comp.getId());
//            /**
//             * Problem with url of an HtmlGraphicImage , when the first character of the url is a slash , the compoennt
//             * addd automatically a /webappname/[the url we want] so we have specified directly in the property imgData
//             * of the column the url with resource.jsf
//             * 
//             */
//            img.setValue("#{layer.legendUrl}");
        tni.getChildren().add(o4);
        tni.getChildren().add(o1);
        //         tni.getChildren().add(o2);
        tni.getChildren().add(o3);
        tni.getChildren().add(o5);
        //tni.getChildren().add(o6);
//            tni.getChildren().add(img);
        tni.getChildren().add(dr);
        treePanel.getChildren().add(tc);
        treePanel.getChildren().add(vc);
        treePanel.getChildren().add(oc);
        treePanel.getChildren().add(ec);

        treePanel.getChildren().add(tic);
        treePanel.getChildren().add(tni);
        treeTable.getChildren().add(treePanel);

        UIComponent treetableTmp = FacesUtils.findComponentById(context, component, treeTable.getId());

        if (treetableTmp != null) {
            comp.getChildren().set(component.getChildren().indexOf(treetableTmp), treeTable);
        } else {
            comp.getChildren().add(0, treeTable);
        }

        getWriter().flush();

    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        getWriter().endElement("div");
        getWriter().flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        super.decode(context, component);
    }
}
