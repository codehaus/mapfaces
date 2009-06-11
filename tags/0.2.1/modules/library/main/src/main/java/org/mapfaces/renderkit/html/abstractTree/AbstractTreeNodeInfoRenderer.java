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
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreeNodeInfoBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;

/**
 * @author Kevin Delfour
 */
public abstract class AbstractTreeNodeInfoRenderer extends Renderer implements CustomizeTreeComponentRenderer{

    private static final Logger LOGGER = Logger.getLogger(AbstractTreeNodeInfoRenderer.class.getName());
    private static final String DESC_STYLE_CLASS = "x-tree-node-info";

    private boolean debug = false;

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component -
     * @return
     */
    private static UITreePanelBase getForm(final UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UITreePanelBase) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UITreePanelBase) parent;
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
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) LOGGER.info("beforeEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());

        beforeEncodeBegin(context, component);

        if (debug) LOGGER.info("encodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());

        //Start encoding
        final UITreeNodeInfoBase treenodeinfo = (UITreeNodeInfoBase) component;
        final UITreeLinesBase treeline        = (UITreeLinesBase) treenodeinfo.getParent();
        final TreeNodeModel node              = treeline.getNodeInstance();
        final ResponseWriter writer           = context.getResponseWriter();
        final String treepanelId              = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treetable       = getForm(treenodeinfo);

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

        if (debug) LOGGER.info("afterEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());

        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (debug) LOGGER.info("encodeChildren : " + AbstractTreeNodeInfoRenderer.class.getName());

        final ResponseWriter writer = context.getResponseWriter();

        if (component.getChildCount() != 0) {
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");
            for (UIComponent tmp : component.getChildren()) {
                writer.startElement("div", component);
                writer.writeAttribute("class", DESC_STYLE_CLASS, null);
                Utils.encodeRecursive(context, tmp);
                writer.endElement("div");
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();

        if (debug) LOGGER.info("beforeEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());

        beforeEncodeEnd(context, component);

        if (debug) LOGGER.info("encodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());

        writer.endElement("div");

        if (debug) LOGGER.info("afterEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());

        afterEncodeEnd(context, component);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        return;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }
}
