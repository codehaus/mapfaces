/*
 * MapSizeRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import adapter.owc.Adapter;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultTreeModel;
import org.mapfaces.component.UILayerControl;
import org.mapfaces.component.layercontrol.UIElevationColumn;
import org.mapfaces.component.layercontrol.UIOpacityColumn;
import org.mapfaces.component.layercontrol.UITimeColumn;
import org.mapfaces.component.layercontrol.UIVisibilityColumn;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.treelayout.UITreeColumn;
import org.mapfaces.component.treelayout.UITreePanel;
import org.mapfaces.component.treelayout.UITreeTable;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.util.treelayout.TreeLayoutUtils;


public class LayerControlRenderer extends WidgetBaseRenderer {
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);
        
        AbstractContext model = ((UIContext) getUIModel()).getModel();         
        UILayerControl comp = (UILayerControl) component;
        comp.setTree(Adapter.abstractContext2Tree(context,model));
        
        
        getWriter().startElement("div", comp);
        getWriter().writeAttribute("id",getClientId(),"id");
        getWriter().writeAttribute("style",getStyle(),"style");
        
        if(getStyleClass() == null)
            getWriter().writeAttribute("class","mfLayerControl","styleClass");
        else
            getWriter().writeAttribute("class",getStyleClass(),"styleClass");
        
        DefaultTreeModel tree = comp.getTree();
        if (tree==null)
            throw new NullPointerException("L'arbre est vide");
        //<mf:TreeTable value="#{treebean.tree}" var="layer" id="Treetable" width="500">                    
        UITreeTable treeTable = new UITreeTable();
        treeTable.setId("TreeTable");
        treeTable.setTree((new TreeLayoutUtils()).transformTree(tree));
        treeTable.setVarName("layer");
        treeTable.setWidth(800);        
        
        //<mf:TreePanel header="true" id="panel1" title="A tree" rowId="true" >
        UITreePanel treePanel = new UITreePanel();
        treePanel.setId("TreePanel");
        treePanel.setHeader(true);
        treePanel.setTitle("List of layers");
        treePanel.setRowId(true);
        
        //<mf:TreeColumn header="Tree Items" width="300" value="#{layer.name}"/> 
        UITreeColumn tc = new UITreeColumn();
        tc.setId("Layers");
        tc.setValue("#{layer.name}");
        tc.setHeader("Layers grouped");
        tc.setWidth("400");    
        
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
        vc.setId("Visible");           
        vc.setValue("#{!layer.hidden}");
        vc.setIcon("/org/mapfaces/resources/img/eye.png");
        vc.setWidth("30");
        
        UIOpacityColumn oc = new UIOpacityColumn();
        oc.setId("Opacity");             
        oc.setValue("#{layer.opacity}");
        oc.setIcon("/org/mapfaces/resources/img/weather_cloudy.png");
        oc.setWidth("90");
        
        UIElevationColumn ec = new UIElevationColumn();
        ec.setId("Elevation");             
        ec.setValue("#{layer.userValueElevation}");
        ec.setIcon("/org/mapfaces/resources/img/weather_cloudy.png");
        ec.setWidth("100");
        
        UITimeColumn tic = new UITimeColumn();
        tic.setId("Time");                     
        ec.setValue("#{layer.userValueDate}");
        tic.setIcon("/org/mapfaces/resources/img/calendar_select.png");
        tic.setWidth("100");
        
        /*UICalendarColumn tic = new UICalendarColumn();
        tic.setId("Time");             
        tic.setIcon("/org/mapfaces/resources/img/calendar_select.png");
        tic.setWidth("100");*/
//        
        treePanel.getChildren().add(tc);        
        treePanel.getChildren().add(vc);
        treePanel.getChildren().add(oc);
        treePanel.getChildren().add(ec);        
        treePanel.getChildren().add(tic);
        treeTable.getChildren().add (treePanel);
        comp.getChildren().add(treeTable);
        
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
