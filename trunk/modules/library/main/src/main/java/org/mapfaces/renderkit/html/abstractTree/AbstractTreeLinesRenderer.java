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
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.tree.UITreeTable;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.tree.TreeStyle;

/**
 * @author Kevin Delfour
 */
public abstract class AbstractTreeLinesRenderer extends Renderer implements AjaxRendererInterface, A4JRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fields */
    private boolean debug;
    private AjaxUtils ajaxtools = new AjaxUtils();
    private static String DEFAULT_STYLE_LINE;
    private static String CLASS_NODE_LI = "x-tree-node x-tree-lines";
    private static String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";
    private static final Logger LOGGER = Logger.getLogger(AbstractTreeLinesRenderer.class.getName());

    /**
     * <p> Render the beginning specified TreeTable Component to the output stream or writer associated
     * with the response we are creating. If the conversion attempted in a previous call to getConvertedValue()
     * for this component failed, the state information saved during execution of decode() should be used to
     * reproduce the incorrect input.</p>
     * <ul><li>Firstly, get the tree value from the bean</li>
     * <li>Then translate into a TreeTableModel and write Css and Js headers before all</li>
     * </ul>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeLinesBase treeline = (UITreeLinesBase) component;
        final ResponseWriter writer = context.getResponseWriter();
        final UITreePanelBase treepanel = (UITreePanelBase) FacesUtils.findParentComponentByClass(component, UITreePanelBase.class);
        final String treepanelId = treepanel.getClientId(context);

        final int countLine = treepanel.getOddEvenCountLine();

        treepanel.setOddEvenCountLine(countLine + 1);

        if (!component.isRendered()) {
            return;
        }

        assertValid(context, component);

        if (treeline.isDebug()) {
            debug = treeline.isDebug();
        }

        if (debug) {
            LOGGER.info("beforeEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
        }


        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            LOGGER.info("encodeBegin : " + AbstractTreeLinesRenderer.class.getName() + " Component Id : " + component.getId());
        }


        /* Get the node instance for rendering lines */
        final TreeNodeModel node = treeline.getNodeInstance();
        treeline.setRow(node.getId());

        /* Prepare informations for making any Ajax request */
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_REQUEST_PARAM_KEY, "true");
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_CONTAINER_ID_KEY, treepanelId + "_line_" + node.getId());
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");

        boolean isFolder = !(node.isLeaf());

        final String classStyle = "";

        if (treeline.isToRender()) {
            final TreeNodeModel root = treepanel.getView().getRoot();
            if (node.getParent().equals(root)) {
                writer.startElement("div", component);
                writer.writeAttribute("id", "ul:" + treepanelId + ":0", null);
                if (treepanel.isEnableDragDrop()) {
                    writer.startElement("div", treeline);
                    writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":inList", null);
                    writer.writeAttribute("class", " x-tree-droppable x-tree-droppable-zone", null);
                    writer.writeAttribute("name", treeline.getId(), null);
                    writer.writeAttribute("dest", "ul:" + treepanelId + ":0", null);
                    writer.writeAttribute("pos", node.getId(), null);
                    writer.writeAttribute("depth", node.getDepth() + 1, null);
                    writer.writeAttribute("where", "firstitem", null);
                    writer.endElement("div");
                }
            }

            writer.startElement("div", component);
            writer.writeAttribute("id", "line:" + treepanelId + ":" + node.getId(), null);
            writer.writeAttribute("class", classStyle, null);
            writer.writeAttribute("name", treeline.getId(), null);
            writer.writeAttribute("parent", treepanel.getId(), null);
            writer.writeAttribute("depth", node.getDepth(), null);

//            if (treepanel.isEnableDragDrop()) {
//                //First zone to drop : before the folder with the same depth or before a node item
//                writer.startElement("div", treeline);
//                writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":before", null);
//                writer.writeAttribute("class", " x-tree-droppable x-tree-droppable-zone", null);
////                writer.writeAttribute("style", "height:1px;width:auto; margin-left:" + (node.getDepth()) * 25 + "px", null);
//                writer.writeAttribute("where", "before", null);
//                writer.writeAttribute("name", treeline.getId(), null);
//                writer.writeAttribute("pos", node.getId(), null);
//                writer.endElement("div");
//            }

//            writer.startElement("li", component);
            writer.startElement("div", component);
            writer.writeAttribute("id", "li:" + treepanelId + ":" + node.getId(), null);
            writer.writeAttribute("depth", node.getDepth(), null);
            writer.writeAttribute("class", CLASS_NODE_LI, null);
            writer.writeAttribute("pos", node.getId(), null);
            writer.writeAttribute("name", treeline.getId(), null);

            String styleLeafUser = "",
                    styleNodeUser = "",
                    styleOddLine = "",
                    styleEvenLine = "";

            if (treepanel.getStyleLeaf() != null) {
                styleLeafUser = treepanel.getStyleLeaf();
            }

            if (treepanel.getStyleNode() != null) {
                styleNodeUser = treepanel.getStyleNode();
            }

            if (treepanel.getStyleOdd() != null) {
                styleOddLine = treepanel.getStyleOdd();
            }

            if (treepanel.getStyleEven() != null) {
                styleEvenLine = treepanel.getStyleEven();
            }

            DEFAULT_STYLE_LINE = TreeStyle.getRowStyle();
            DEFAULT_STYLE_LINE += (countLine % 2 == 0) ? styleOddLine : styleEvenLine;

            if (isFolder) {
                writer.writeAttribute("style", DEFAULT_STYLE_LINE + styleNodeUser, null);
            } else {
                writer.writeAttribute("style", DEFAULT_STYLE_LINE + styleLeafUser, null);
            }

            if (treepanel.isRowId()) {
                writer.startElement("div", component);
                writer.writeAttribute("style", "width: 30px; text-align:center;", null);
                writer.writeAttribute("class", CLASS_LEAF_DIV, null);
                writer.write(node.getId() + "");
                writer.endElement("div");
            }

            if (debug) {
                LOGGER.info("afterEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
            }

            afterEncodeBegin(context, component);
        }
    }

    /**
     * <p>Render the child components of this UIComponent, following the rules described for encodeBegin()
     * to acquire the appropriate value to be rendered.</p>
     * <p>This method will only be called if the rendersChildren property of this component is true.</p>
     * @param context FacesContext for the response we are creating
     * @param component UIComponent whose children are to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeLinesBase treeline = (UITreeLinesBase) component;
        final ResponseWriter writer = context.getResponseWriter();
        final UITreePanelBase treepanel = (UITreePanelBase) FacesUtils.findParentComponentByClass(component, UITreePanelBase.class);
        final String treepanelId = treepanel.getClientId(context);
        final UITreeTable treetable = (treepanel.getParent() instanceof UITreeTable) ? (UITreeTable) treepanel.getParent() : null;
        final TreeTableModel tree = treepanel.getView();
        final TreeNodeModel node = tree.getById(treeline.getNodeInstance().getId());
        final boolean isFolder = !(node.isLeaf());

        if (debug) {
            LOGGER.info("encodeChildren : " + AbstractTreeLinesRenderer.class.getName());
        }

        if (treeline.isToRender()) {
            if (debug) {
                LOGGER.info("encodeChildren : Encode line !");
            }

            for (final UIComponent tmp : treeline.getChildren()) {
                if (!(tmp instanceof HtmlPanelGroup)) {
                    FacesUtils.encodeRecursive(context, tmp);
                }
            }
        }

        if (isFolder) {
            if (debug) {
                LOGGER.info("encodeChildren : Encode folder !");
            }


            writer.startElement("div", treeline);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");

            if (treeline.hasChildren()) {
                if (debug) {
                    LOGGER.info("encodeChildren : Encode children !");
                }

                writer.startElement("div", component);
                writer.writeAttribute("id", "ul:" + treepanelId + ":" + node.getId(), null);

                if (treetable.isCollapsed()) {
                    int collapsedDepth = 0;
                    collapsedDepth = treetable.getCollapseDepth();
                    if (node.getDepth() > collapsedDepth) {
                        writer.writeAttribute("style", "margin-left: 0; display: none;", null);
                    } else {
                        writer.writeAttribute("style", "margin-left: 0;", null);
                    }
                } else {
                    writer.writeAttribute("style", "margin-left: 0;", null);
                }

                if (node.getId() > 1) {
                    writer.writeAttribute("class", "collapsible", null);
                }

                //Encode child
                for (int i = 0, n = node.getChildCount(); i < n; i++) {
                    final TreeNodeModel child = (TreeNodeModel) node.getChildAt(i);
                    if (debug) {
                        LOGGER.info("encodeChildren : (TreeNodeModel) node.getChildAt(" + i + ")");
                    }

                    if (child.isChecked()) {
                        if (debug) {
                            LOGGER.info("encodeChildren : Encode this child : " + treepanel.getClientId(context) + "_panel_" + child.getId());
                        }
                        FacesUtils.encodeRecursive(context, (FacesUtils.findComponentByClientId(context, treepanel.getClientId(context) + "_panel_" + child.getId())));
                    }
                }

                writer.endElement("div");
            }

        }
    }

    /**
     * <p>Render the ending of the current state of the specified UIComponent, following the rules described for
     * encodeBegin() to acquire the appropriate value to be rendered.</p>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        if (debug) {
            LOGGER.info("beforeEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
        }

        beforeEncodeEnd(context, component);

        final UITreeLinesBase treeline = (UITreeLinesBase) component;
        final UITreePanelBase treepanel = (UITreePanelBase) FacesUtils.findParentComponentByClass(component, UITreePanelBase.class);
        final String treepanelId = treepanel.getClientId(context);
        final TreeNodeModel node = treeline.getNodeInstance();

        if (debug) {
            LOGGER.info("encodeEnd : " + AbstractTreeLinesRenderer.class.getName());
        }

        final ResponseWriter writer = context.getResponseWriter();

        if (treeline.isToRender()) {
            writer.startElement("div", treeline);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");
            writer.endElement("div");
//            writer.endElement("li");
            if (!addLinesEvent(context, component).equals("")) {
                writer.startElement("script", component);
                writer.writeAttribute("type", "text/javascript", null);
                writer.write(addLinesEvent(context, component));
                writer.endElement("script");
            }

            final int indentStyle;
            if (!treepanel.isShowRoot()) {
                indentStyle = (node.getDepth() - 2) * 12;
            } else {
                indentStyle = (node.getDepth() - 1) * 12;
            }

            if (treepanel.isEnableDragDrop()) {
                writer.startElement("div", treeline);
                writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId(), null);
                writer.writeAttribute("class", "x-tree-droppable x-tree-droppable-zone", null);
                writer.writeAttribute("style", "text-align:left; padding-left :" + indentStyle + "px", null);
                writer.writeAttribute("name", treeline.getId(), null);
                writer.writeAttribute("where", "after", null);
                writer.writeAttribute("pos", node.getId(), null);
                writer.endElement("div");
            }
            writer.endElement("div");

            final TreeNodeModel root = treepanel.getView().getRoot();

            if (node.getParent().equals(root)) {
//                writer.endElement("ul");
                writer.endElement("div");
            }

            if (debug) {
                LOGGER.info("afterEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
            }

            afterEncodeEnd(context, component);
        }
    }

    /**
     * <p>Decode any new state of the specified UIComponent  from the request contained in the specified FacesContext,
     * and store that state on the UIComponent.</p>
     * <p>During decoding, events may be enqueued for later processing (by event listeners that have registered an interest),
     * by calling queueEvent() on the associated UIComponent. </p>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be decoded.
     * @throws java.lang.NullPointerException if context  or component is null
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
    }

    /**
     * <p>Return a flag indicating whether this Renderer is responsible for rendering the
     * children the component it is asked to render. The default implementation returns false.</p>
     * <p>By default, getRendersChildren returns true, so encodeChildren() will be invoked</p>
     * @return True
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Test if context or the UICompoent exist and are not null
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be tested
     */
    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }

    }

    /**
     * This method is executed by the Ajax listener when an Ajax call for this component is detected.
     * @param context FacesContext for the request we are processing
     * @param component UIComponent who get the Ajax request
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void A4JPostRequest(final FacesContext context, final UIComponent component) {
    }

    /**
     * <p>This method can be declared in each class who extends of AbstractTreeLinesRenderer to add javascript event
     * for each lines</p>
     * @param context  FacesContext for the request we are processing
     * @param component UIComponent base
     * @return a string
     */
    public abstract String addLinesEvent(FacesContext context, UIComponent component);
}
