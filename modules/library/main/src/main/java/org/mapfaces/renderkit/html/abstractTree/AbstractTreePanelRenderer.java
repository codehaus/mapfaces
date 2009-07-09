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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;

import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.abstractTree.UITreeColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.abstractTree.UITreeTableBase;

import org.mapfaces.component.abstractTree.UITreeToolbarBase;
import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.request.ServletUtils;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.tree.TreeStyle;
import org.mapfaces.util.treetable.TreeTableConfig;

/**
 * @author Kevin Delfour
 */
public abstract class AbstractTreePanelRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    private static final Logger LOGGER = Logger.getLogger(AbstractTreePanelRenderer.class.getName());
    /* Local Fiels */
    private boolean debug;
    private long encodeBeginTime,  encodeChildrenTime,  encodeEndTime;

    /**
     * <p> Render the beginning specified TreeTable Component to the output stream or writer associated
     * with the response we are creating. If the conversion attempted in a previous call to getConvertedValue()
     * for this component failed, the state information saved during execution of decode() should be used to
     * reproduce the incorrect input.</p>
     * <ul>
     * <li>Firstly, get the TreeTableModel from the treetable</li>
     * <li>Then create Treelines Component with the View present in each Treepanel </li>
     * </ul>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UITreeTableBase treetable = (UITreeTableBase) FacesUtils.findParentComponentByClass(component, UITreeTableBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        UITreeToolbarBase toolbar = null;
        final boolean loadAll;

        treepanel.setOddEvenCountLine(1);

        /*
         * Look client Browser, if it's Opera, we fix loadAll option
         */
        if (ServletUtils.isOpera()) {
            loadAll = true;
        } else {
            loadAll = treepanel.isLoadAll();
        }

        final String clientId = component.getClientId(context);
        final boolean renderHeader = treepanel.isHeader();
        final boolean renderFrame = treepanel.isFrame();
        final boolean makeCollapsible = treepanel.isCollapsible();
        String title = "";
        final Date phaseStart = new Date();
        String styleUser = "";
        String styleClass = "";
        boolean haveToolbar = false;

        /* Is the component haven't been rendered yet ? */
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        /* GetAttributes from the UIComponent  */
        if (treepanel.isDebug()) {
            debug = treepanel.isDebug();
            LOGGER.info(" AbstractTreePanelRenderer loadAll attribute = " + loadAll);
            LOGGER.info(" AbstractTreePanelRenderer renderHeader attribute = " + renderHeader);
            LOGGER.info(" AbstractTreePanelRenderer renderFrame attribute = " + renderFrame);
            LOGGER.info(" AbstractTreePanelRenderer makeCollapsible attribute = " + makeCollapsible);
        }
        if (treepanel.getStyle() != null) {
            styleUser = treepanel.getStyle();
        }
        if (treepanel.getStyleClass() != null) {
            styleClass = treepanel.getStyleClass();
        }
        if (treepanel.getTitle() != null) {
            title = treepanel.getTitle();
        }

        for (final UIComponent child : component.getChildren()) {
            if (child instanceof UITreeToolbarBase && child.isRendered()) {
                haveToolbar = true;
                toolbar = (UITreeToolbarBase) child;
                break;
            }
        }

        /* Before encodeBegin, any method declared in a component extends this class can be launch here*/
        if (debug) {
            LOGGER.info(" beforeEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }

        beforeEncodeBegin(context, component);

        /* Start encoding */
        if (debug) {
            LOGGER.info(" encodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }

        if (component != null) {
            // DIV Start
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel:" + clientId, null);
            writer.writeAttribute("style", styleUser, null);
            if (!styleClass.isEmpty()) {
                writer.writeAttribute("class", styleClass, null);
            }

            //HEADER Attribute
            if (renderHeader) {
                //FRAME Attribute
                if (renderFrame) {
                    writer.startElement("div", component);
                    writer.writeAttribute("class", TreeStyle.default_frameTlStyle, null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", TreeStyle.default_frameTrStyle, null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", TreeStyle.default_frameTcStyle, null);
                }


                // TITLE Attribute
                if (!title.isEmpty()) {
                    writer.startElement("div", component);
                    writer.writeAttribute("id", "panel_title:" + clientId, null);
                    writer.writeAttribute("class", TreeStyle.default_mainHeaderStyle, null);

                    if (makeCollapsible) {
                        writer.writeAttribute("onclick", "collapse('" + component.getId() + "');", null);
                    }

                    writer.startElement("div", component);
                    writer.writeAttribute("class", TreeStyle.default_mainHeaderTextStyle, null);
                    writer.write(title);
                    writer.endElement("div");
                    writer.endElement("div");
                }

                if (renderFrame) {
                    writer.endElement("div");
                    writer.endElement("div");
                    writer.endElement("div");
                }

                if (haveToolbar) {
                    // DIV Toolbar
                    writer.startElement("div", component);
                    writer.writeAttribute("id", "panel_toolbar:" + clientId, null);
                    writer.writeAttribute("class", TreeStyle.default_toolbarStyle, null);
                    // Rendering toolbar components
                    renderToolbar(context, toolbar);
                    writer.endElement("div");
                }
            }

            //DIV Content
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_content:" + clientId, null);
            writer.writeAttribute("class", TreeStyle.default_mainBwrapStyle, null);
            writer.writeAttribute("style", "height:100%;", null);

            //DIV Headers
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_headers:" + clientId, null);
            writer.writeAttribute("class", TreeStyle.default_headersStyle, null);
            renderHeadColumn(context, component);

            writer.endElement("div");

            //DIV Lines
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_lines:" + clientId, null);
            writer.writeAttribute("class", "droppable-holder", null);
            String styleLine = treepanel.getStyleLinesContainer();
            if (styleLine == null)
                styleLine = "";
            writer.writeAttribute("style", "overflow:auto; height:100%; " + styleLine, null);

            if (treepanel.isFrame()) {
                writer.startElement("div", component);
                writer.writeAttribute("class", TreeStyle.default_frameMlStyle, null);
                writer.startElement("div", component);
                writer.writeAttribute("class", TreeStyle.default_frameMrStyle, null);
                writer.startElement("div", component);
                writer.writeAttribute("class", TreeStyle.default_frameMcStyle, null);
            }

            /* Tree view ans Tree lines Component contruction
             * At this state, we are getting the TreeTable model store in the TreeTable Component
             * and we store it ine the TreePanel Component.
             * We can apply some methods to modify Treetable model in View.
             * After that, we take this model to create Treelines Component recursively
             * All Treelines will be children of this Treepanel
             */
            if (treetable.getTree() != null) {
                treepanel.setView(treetable.getTree());
            }else{
                return;
            }


            final TreeNodeModel root = treepanel.getView().getRoot();
            final ExternalContext extContext = context.getExternalContext();

            //if (!extContext.getRequestMap().containsKey("treePanelRendered_" + component.getClientId(context))) {
            extContext.getRequestMap().put("treePanelRendered_" + component.getClientId(context), Boolean.TRUE);
            final List<UIComponent> backup = new ArrayList<UIComponent>();
            final List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                if (!(tmp instanceof HtmlPanelGroup) && !(tmp instanceof UITreeToolbarBase) && tmp.isRendered()) {
                    tmp.setId(component.getId() + "_" + tmp.getId());
                    backup.add(tmp);
                }
            }

            /* Create all Treelines for the context */
            final long start = System.currentTimeMillis();
            createTreeLines(((UITreePanelBase) component), root, backup, loadAll);
            final long time = System.currentTimeMillis() - start;
            if (debug) {
                LOGGER.info(" createTreeLines times in " + time + " mlls");
            }

            treepanel.setInit(true);
            //}

            /* After encodeBegin, any method declared in a component extends this class can be launch here*/
            if (debug) {
                LOGGER.info(" afterEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
            }

            afterEncodeBegin(context, component);
        }

        final Date phaseEnd = new Date();
        encodeBeginTime = phaseStart.getTime() - phaseEnd.getTime();
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
        final ExternalContext extContext = context.getExternalContext();
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        Date phaseStart, phaseEnd;

        if (treepanel.getView() == null){
            LOGGER.severe("encodeChildren : Treepanel view is null");
            return;
        }
        /* Initialisation */
        phaseStart = new Date();

        if (debug) {
            LOGGER.info(" encodeChildren : " + AbstractTreePanelRenderer.class.getName());
        }

        for (final UIComponent child : treepanel.getChildren()) {
            if (!(child instanceof HtmlPanelGroup) && !(child instanceof UITreeToolbarBase)) {
                if (extContext.getRequestMap().containsKey("treePanelRendered_" + component.getClientId(context))) {
                    child.setTransient(true);
                }
            } else {
                if (treepanel.isShowRoot()) {
                    ((UITreeLinesBase) FacesUtils.findComponentByClientId(context, context.getViewRoot(), treepanel.getClientId(context) + "_line_1")).setToRender(true);
                }
            }
        }

        final TreeTableModel tree = treepanel.getView();
        final TreeNodeModel root = tree.getRoot();

        for (int i = 0, n = root.getChildCount(); i < n; i++) {
            final TreeNodeModel child = (TreeNodeModel) root.getChildAt(i);
            if (FacesUtils.findComponentByClientId(context, context.getViewRoot(), treepanel.getClientId(context) + "_panel_" + child.getId()) != null) {
                FacesUtils.encodeRecursive(context, (FacesUtils.findComponentByClientId(context,context.getViewRoot(), treepanel.getClientId(context) + "_panel_" + child.getId())));
            } else {
                System.out.println("[WARNING] encodeChildren in " + AbstractTreePanelRenderer.class.getName() + " : findComponent " +
                        "throw java.nullPointerException to " + treepanel.getClientId(context) + "_panel_" + child.getId());
            }
        }

        phaseEnd = new Date();
        encodeChildrenTime = phaseStart.getTime() - phaseEnd.getTime();
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
        final ResponseWriter writer = context.getResponseWriter();
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        Date phaseStart, phaseEnd;

        /* Initialisation */
        phaseStart = new Date();

        if (debug) {
            LOGGER.info(" beforeEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }

        beforeEncodeEnd(context, component);

        if (debug) {
            LOGGER.info(" encodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }


        writer.endElement("div");
        if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
            writer.endElement("div");
            writer.endElement("div");
            writer.endElement("div");
            writer.startElement("div", component);
            writer.writeAttribute("class", TreeStyle.default_frameBlStyle, null);
            writer.startElement("div", component);
            writer.writeAttribute("class", TreeStyle.default_frameBrStyle, null);
            writer.startElement("div", component);
            writer.writeAttribute("class", TreeStyle.default_frameBcStyle, null);
            writer.endElement("div");
            writer.endElement("div");
            writer.endElement("div");
        }
        writer.endElement("div");
        writer.endElement("div");
        writer.startElement("div", component);
        writer.writeAttribute("class", TreeStyle.default_clearStyle, null);
        writer.endElement("div");

        if (debug) {
            LOGGER.info(" afterEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }

        afterEncodeEnd(context, component);

        phaseEnd = new Date();
        if (debug) {
            encodeEndTime = phaseStart.getTime() - phaseEnd.getTime();
            LOGGER.info(" encodeBegin have been rendered in " + encodeBeginTime + " mlls");
            LOGGER.info(" encodeChildren have been rendered in " + encodeChildrenTime + " mlls");
            LOGGER.info(" encodeEnd have been rendered in " + encodeEndTime + " mlls");
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
    public void decode(final FacesContext context, final UIComponent component) throws NullPointerException {
        super.decode(context, component);
        if (debug) {
            LOGGER.info(" decode : " + AbstractTreePanelRenderer.class.getName());
        }

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
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /**
     * This method is executed by the Ajax listener when an Ajax call for this component is detected.
     * This Ajax request concern Drag and Drop option.
     * @param context FacesContext for the request we are processing
     * @param component UIComponent who get the Ajax request
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if (treepanel.isDebug()) {
            debug = treepanel.isDebug();
        }

        /* First we get attributes from the request*/
        final String TreeLineNewParentId = request.getParameter(AjaxUtils.DND_NEW_PARENT_COMPONENT);
        final String TreeLineinDragId = request.getParameter(AjaxUtils.AJAX_COMPONENT_ID_KEY);
        final String dropPosition = request.getParameter(AjaxUtils.DND_POSITION_LINE);
        final UITreeLinesBase treeLinesToDrag = (UITreeLinesBase) FacesUtils.findComponentById(context, context.getViewRoot(), TreeLineinDragId);
        final UITreeLinesBase treeLinesToDragIn = (UITreeLinesBase) FacesUtils.findComponentById(context, context.getViewRoot(), TreeLineNewParentId);

        TreeTableModel tree = treepanel.getView();

        /* After we get tje targetNode  and the node to move */
        final int targetNode = treeLinesToDragIn.getNodeInstance().getId();
        final int movedNode = treeLinesToDrag.getNodeInstance().getId();

        /* We put the right node at the right place */
        if (dropPosition.equals("lastitem")) {
            final int position = treeLinesToDrag.getNodeInstance().getChildCount();
            tree = TreeModelsUtils.moveTo(tree, movedNode, targetNode, position + 1);
        } else if (dropPosition.equals("before")) {
            tree = TreeModelsUtils.insertBefore(tree, movedNode, targetNode);
        } else if (dropPosition.equals("after")) {
            tree = TreeModelsUtils.insertAfter(tree, movedNode, targetNode);
        } else if (dropPosition.equals("firstitem")) {
            tree = TreeModelsUtils.moveTo(tree, movedNode, targetNode);
        } else {
            tree = TreeModelsUtils.moveTo(tree, movedNode, targetNode);
        }

        /* We save the new Tree to the treepanel */
        treepanel.setView(tree);
    }

    /**
     * <p>Render two default column included in the treetable if necessary.</p>
     * <ul>
     * <li>The first column is the  a check column</li>
     * <li>The second column show node Id</li>
     * </ul>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered head column
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void renderHeadColumn(final FacesContext context, final UIComponent component) throws IOException {
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        final ResponseWriter writer = context.getResponseWriter();

        int id = 0;

        //RENDER ROW ID NUMBER IF IS NECESSARY
        if (treepanel.isRowId()) {
            writer.startElement("div", component);
            writer.writeAttribute("id", treepanel.getId() + ":tree-hd:" + id, null);
            writer.writeAttribute("class", TreeStyle.default_headerStyle, null);
            writer.writeAttribute("style", "width: 30px;", null);
            writer.startElement("div", component);
            writer.writeAttribute("id", treepanel.getId() + "tree-hd-text:" + id, null);
            writer.writeAttribute("class", TreeStyle.default_headerTextStyle, null);
            writer.writeAttribute("style", "text-align:center;", null);
            writer.write("Id");
            writer.endElement("div");
            writer.endElement("div");
        }

        for (UIComponent child : component.getChildren()) {
            if (! child.isRendered())
                continue;
            id++;
            if ((child instanceof UITreeColumnBase) || (child instanceof UIColumnBase)) {
                renderHeaders(context, child, id);
            }
        }

        writer.startElement("div", component);
        writer.writeAttribute("class", "x-clear", null);
        writer.endElement("div");
    }

    /**
     * <p>Render the header of a column or a treecolumn</p>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered head column
     * @param idnumbers The Id number of the Uicomponent
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void renderHeaders(final FacesContext context, final UIComponent component, final int idnumbers) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();

        /* if a style have to be apply to the header column of a component,
         * we get it and we apply it
         */
        String styleHeader = "";
        if (component instanceof UIColumnBase) {
            if (((UIColumnBase) component).getStyleHeader() != null) {
                styleHeader = ((UIColumnBase) component).getStyleHeader();
            }
        }

        writer.startElement("div", component);
        writer.writeAttribute("id", component.getId() + ":tree-hd:" + idnumbers, null);
        writer.writeAttribute("class", TreeStyle.default_headerStyle, null);

        /* Then render head column of th UIcomponent */
        final Map<String, Object> attributs = component.getAttributes();
        String size = TreeTableConfig.DEFAULT_SIZE_COLUMN;
        if (attributs.get("width") != null) {
            size = String.valueOf(attributs.get("width"));
            /* If size exist, we remove one pixel to align with the rest of the treetable,
             * this is due to border
             */
            int sizeInt = Integer.valueOf(size);
            sizeInt -= 1;
            size = String.valueOf(sizeInt);
            styleHeader += "width:" + size + "px";
        }

        writer.writeAttribute("style", styleHeader, null);
        writer.startElement("div", component);
        writer.writeAttribute("id", component.getId() + "tree-hd-text:" + idnumbers, null);
        writer.writeAttribute("class", TreeStyle.default_headerTextStyle, null);
        writer.startElement("center", component);

        /* If an icon is declared, we put in priority the icon, else we put the text header */
        if (attributs.get("headerIcon") != null) {
            writer.startElement("div", component);
            writer.writeAttribute("class", (String) attributs.get("headerIcon"), null);
            if (attributs.get("headerTitle") != null) {
                writer.writeAttribute("title", (String) attributs.get("headerTitle"), null);
            }
            writer.endElement("div");
           /* writer.startElement("img", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, (String) attributs.get("headerIcon"), null), null);
            if (attributs.get("headerTitle") != null) {
                writer.writeAttribute("title", (String) attributs.get("headerTitle"), null);
            }
            writer.endElement("img");*/
        } else if (attributs.get("headerTitle") != null) {
            writer.write((String) attributs.get("headerTitle"));
        }
        writer.endElement("center");
        writer.endElement("div");
        writer.endElement("div");

    }

    private void renderToolbar(final FacesContext context, final UITreeToolbarBase toolbar) throws IOException {
        if (toolbar.getChildCount() > 0) {
            FacesUtils.encodeRecursive(context, toolbar);
        }
    }
    /* Abstracts Methods */

    /**
     * <p>This method have to be declared in each extends of AbastractTreePanelRenderer and point to a method who permit to
     * build treelines</p>
     * @param component omponent UIComponent base
     * @param node The Root node of the TreePanel view
     * @param list A list of template component.
     */
    public abstract void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list, boolean LoadingOption);
}
