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
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.request.servletUtils;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.tree.TreeStyle;
import org.mapfaces.util.treetable.TreeTableConfig;

/**
 * @author Kevin Delfour
 */
public abstract class AbstractTreePanelRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fiels */
    private boolean debug;
    private TreeTableConfig config = new TreeTableConfig();
    private Date renderStart,  renderEnd;
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
        final UITreeTableBase treetable = getForm(component);
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        UITreeToolbarBase toolbar = null;
        final boolean loadAll;

        treepanel.setOddEvenCountLine(1);

        /*
         * Look client Browser, if it's Opera, we fix loadAll option
         */
        if (servletUtils.isOpera()) {
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
        final Date renderStart = phaseStart;
        String styleUser = "";
        String styleClass = "";
        boolean haveToolbar = false;

        /*
         * Tests
         * Firstly, test before rendering if the component is present in a UIForm
         * Then verify if the component haven't been rendered yet
         */
        if (!(getForm(component) instanceof UITreeTableBase)) {
            return;
        }
        /* Is the component haven't been rendered yet ? */
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        /* GetAttributes from the UIComponent  */
        if (treepanel.isDebug()) {
            debug = treepanel.isDebug();
            System.out.println("[DEBUG] AbstractTreePanelRenderer loadAll attribute = " + loadAll);
            System.out.println("[DEBUG] AbstractTreePanelRenderer renderHeader attribute = " + renderHeader);
            System.out.println("[DEBUG] AbstractTreePanelRenderer renderFrame attribute = " + renderFrame);
            System.out.println("[DEBUG] AbstractTreePanelRenderer makeCollapsible attribute = " + makeCollapsible);
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
            if (child instanceof UITreeToolbarBase) {
                haveToolbar = true;
                toolbar = (UITreeToolbarBase) child;
            }
        }

        /* Before encodeBegin, any method declared in a component extends this class can be launch here*/
        if (debug) {
            System.out.println("[INFO] beforeEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }

        beforeEncodeBegin(context, component);

        /* Start encoding */
        if (debug) {
            System.out.println("[INFO] encodeBegin : " + AbstractTreePanelRenderer.class.getName());
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
            writer.writeAttribute("style", "overflow:auto;height:100%;", null);

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
            //if (treepanel.getView() == null) {
            treepanel.setView(treetable.getTree());
            //}
            final TreeNodeModel root = treepanel.getView().getRoot();
            final ExternalContext extContext = context.getExternalContext();

            //if (!extContext.getRequestMap().containsKey("treePanelRendered_" + component.getClientId(context))) {
            extContext.getRequestMap().put("treePanelRendered_" + component.getClientId(context), Boolean.TRUE);
            final List<UIComponent> backup = new ArrayList<UIComponent>();
            final List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                if (!(tmp instanceof HtmlPanelGroup) && !(tmp instanceof UITreeToolbarBase)) {
                    tmp.setId(component.getId() + "_" + tmp.getId());
                    backup.add(tmp);
                }
            }

            /* Create all Treelines for the context */
            final long start = System.currentTimeMillis();
            createTreeLines(((UITreePanelBase) component), root, backup, loadAll);
            final long time = System.currentTimeMillis() - start;
            if (debug) {
                System.out.println("[INFO] createTreeLines times in " + time + " mlls");
            }

            treepanel.setInit(true);
            //}

            /* After encodeBegin, any method declared in a component extends this class can be launch here*/
            if (debug) {
                System.out.println("[INFO] afterEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
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

        /* Initialisation */
        phaseStart = new Date();

        if (debug) {
            System.out.println("[INFO] encodeChildren : " + AbstractTreePanelRenderer.class.getName());
        }

        for (final UIComponent child : treepanel.getChildren()) {
            if (!(child instanceof HtmlPanelGroup) && !(child instanceof UITreeToolbarBase)) {
                if (extContext.getRequestMap().containsKey("treePanelRendered_" + component.getClientId(context))) {
                    child.setTransient(true);
                }
            } else {
                if (treepanel.isShowRoot()) {
                    ((UITreeLinesBase) Utils.findComponent(context, treepanel.getClientId(context) + "_line_1")).setToRender(true);
                }
            }
        }

        final TreeTableModel tree = treepanel.getView();
        final TreeNodeModel root = tree.getRoot();

        for (int i = 0, n = root.getChildCount(); i < n; i++) {
            final TreeNodeModel child = (TreeNodeModel) root.getChildAt(i);
            if (child.isLeaf()) {
                if (Utils.findComponent(context, treepanel.getClientId(context) + "_panel_" + child.getId()) != null) {
                    Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_panel_" + child.getId())));
                } else {
                    System.out.println("[WARNING] encodeChildren in " + AbstractTreePanelRenderer.class.getName() + " : findComponent " +
                            "throw java.nullPointerException to " + treepanel.getClientId(context) + "_panel_" + child.getId());
                }
            }
        }

        for (int i = 0, n = root.getChildCount(); i < n; i++) {
            final TreeNodeModel child = (TreeNodeModel) root.getChildAt(i);
            if (!child.isLeaf()) {
                if (Utils.findComponent(context, treepanel.getClientId(context) + "_panel_" + child.getId()) != null) {
                    Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_panel_" + child.getId())));
                } else {
                    System.out.println("[WARNING] encodeChildren in " + AbstractTreePanelRenderer.class.getName() + " : findComponent " +
                            "throw java.nullPointerException to " + treepanel.getClientId(context) + "_panel_" + child.getId());
                }
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
            System.out.println("[INFO] beforeEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }

        beforeEncodeEnd(context, component);

        if (debug) {
            System.out.println("[INFO] encodeEnd : " + AbstractTreePanelRenderer.class.getName());
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
            System.out.println("[INFO] afterEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }

        afterEncodeEnd(context, component);

        phaseEnd = new Date();
        if (debug) {
            renderEnd = new Date();
            //long timeEncode = renderEnd.getTime() - renderStart.getTime();
            encodeEndTime = phaseStart.getTime() - phaseEnd.getTime();
            System.out.println("[INFO] encodeBegin have been rendered in " + encodeBeginTime + " mlls");
            System.out.println("[INFO] encodeChildren have been rendered in " + encodeChildrenTime + " mlls");
            System.out.println("[INFO] encodeEnd have been rendered in " + encodeEndTime + " mlls");
        //System.out.println("[INFO] encode TreeTable have been rendered in " + timeEncode + " mlls");
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
        final UITreePanelBase treepanel = (UITreePanelBase) component;
        super.decode(context, component);
        if (debug) {
            System.out.println("[INFO] decode : " + AbstractTreePanelRenderer.class.getName());
        }

    }

    /* Others methods */
    /**
     * <p>Get container Treetable of the UIComponent</p>
     * @param component UIComponent to be rendered
     * @return UIAbstractTreeTable the treetable container of the component if exist else return null
     */
    private UITreeTableBase getForm(final UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UITreeTableBase) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a form!");
        }
        return (UITreeTableBase) parent;
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
        final UITreeLinesBase treeLinesToDrag = (UITreeLinesBase) Utils.findComponentById(context, context.getViewRoot(), TreeLineinDragId);
        final UITreeLinesBase treeLinesToDragIn = (UITreeLinesBase) Utils.findComponentById(context, context.getViewRoot(), TreeLineNewParentId);

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
            writer.startElement("img", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, (String) attributs.get("headerIcon"), null), null);
            if (attributs.get("headerTitle") != null) {
                writer.writeAttribute("title", (String) attributs.get("headerTitle"), null);
            }
            writer.endElement("img");
        } else if (attributs.get("headerTitle") != null) {
            writer.write((String) attributs.get("headerTitle"));
        }
        writer.endElement("center");
        writer.endElement("div");
        writer.endElement("div");

    }

    private void renderToolbar(final FacesContext context, final UITreeToolbarBase toolbar) throws IOException {
        if (toolbar.getChildCount() > 0) {
            Utils.encodeRecursive(context, toolbar);
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
