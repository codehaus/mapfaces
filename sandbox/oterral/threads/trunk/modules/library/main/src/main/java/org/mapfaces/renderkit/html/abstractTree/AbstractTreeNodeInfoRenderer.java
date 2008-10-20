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

package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kdelfour
 */
public abstract class AbstractTreeNodeInfoRenderer extends Renderer {

    private static final transient Log log = LogFactory.getLog(AbstractTreeNodeInfoRenderer.class);
    private static String DESC_STYLE_CLASS = "x-tree-node-info";
    private boolean debug = false;

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component - 
     * @return
     */
    private static UIAbstractTreePanel getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIAbstractTreePanel) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UIAbstractTreePanel) parent;
    }

    /**
     * 
     * @param component
     * @return
     */
    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractTreeNodeInfo treenodeinfo = (UIAbstractTreeNodeInfo) component;
        return treenodeinfo.getId() + "PostBack";
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }
        
        if (debug) {
            log.info("beforeEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        if (debug) {
            log.info("encodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        //Start encoding
        UIAbstractTreeNodeInfo treenodeinfo = (UIAbstractTreeNodeInfo) component;
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) treenodeinfo.getParent();
        TreeNodeModel node = treeline.getNodeInstance();
        ResponseWriter writer = context.getResponseWriter();
        String treepanelId = Utils.getWrappedComponentId(context, component, UIAbstractTreePanel.class);

        UIAbstractTreePanel treetable = getForm(treenodeinfo);
        String styleUser = "";
        if (treenodeinfo.getStyle() !=null){
            styleUser = treenodeinfo.getStyle();
        }
        
        String classUser = "";
        if (treenodeinfo.getStyleClass() !=null){
            classUser = treenodeinfo.getStyleClass();
        }
        
        if (treetable != null) {
            
            writer.startElement("div", treenodeinfo);
            writer.writeAttribute("class",classUser, null);
            writer.writeAttribute("id", "info:" + treepanelId + ":" + node.getId(), null);
            if (treenodeinfo.getAttributes().get("hide") != null) {
                if (!(Boolean) treenodeinfo.getAttributes().get("hide")) {
                    writer.writeAttribute("style", "display:block;"+styleUser, null);
                }
            } else {
                writer.writeAttribute("style", "display:none;", null);
            }
        }

        if (debug) {
            log.info("afterEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("encodeChildren : " + AbstractTreeNodeInfoRenderer.class.getName());
        }

        ResponseWriter writer = context.getResponseWriter();

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");
            for (UIComponent tmp : children) {
                writer.startElement("div", component);
                writer.writeAttribute("class", DESC_STYLE_CLASS, null);
                Utils.encodeRecursive(context, tmp);
                writer.endElement("div");
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug) {
            log.info("encodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }

        writer.endElement("div");

        if (debug) {
            log.info("afterEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        afterEncodeEnd(context, component);
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;
}
