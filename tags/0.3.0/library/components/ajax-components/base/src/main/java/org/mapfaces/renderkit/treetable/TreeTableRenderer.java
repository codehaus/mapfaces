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

package org.mapfaces.renderkit.treetable;

import com.sun.faces.renderkit.AttributeManager;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.component.treetable.UITreeColumn;
import org.mapfaces.component.treetable.UITreeTable;
import org.mapfaces.model.tree.ExtendMutableTreeNode;
import org.mapfaces.models.tree.AbstractTreeItem;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.style.TreeTableStyleManager;
import org.mapfaces.share.utils.RendererUtils;

/**
 *
 * @author Kevin Delfour
 */
public class TreeTableRenderer extends BaseTreeTableRenderer {

    private static final String[] ATTRIBUTES = AttributeManager.getAttributes(AttributeManager.Key.DATATABLE);

    /* CSS Styles */
    private static final String NODE_EXPAND_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.NODE_EXPAND_STYLE);
    private static final String NODE_EXPAND_SYMBOLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.NODE_EXPAND_SYMBOLE);
    private static final String NODE_COLLAPSE_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.NODE_COLLAPSE_STYLE);
    private static final String NODE_COLLAPSE_SYMBOLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.NODE_COLLAPSE_SYMBOLE);
    private static final String LEAF_EXPAND_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.LEAF_EXPAND_STYLE);
    private static final String ITEM_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.ITEM_STYLE);
    private static final String ELBOW_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.ELBOW_STYLE);
    private static final String ELBOW_LINE_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.ELBOW_LINE_STYLE);
    private static final String ELBOW_END_STYLE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.ELBOW_END_STYLE);
    private static final String DEFAULT_WIDTH_COLUMN_VALUE = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.DEFAULT_WIDTH_COLUMN_VALUE);
    private static final String UL_CLASS = TreeTableStyleManager.getAttributes(TreeTableStyleManager.Key.UL_CLASS);

    /* Resources */
    private static final String CSS_CORE = "/org/mapfaces/treetable/css/tree.css";
    private static final String JS_CORE = "/org/mapfaces/treetable/js/treeUtilities.js";

    // ---------------------------------------------------------- Public Methods
    @Override
    public void encodeBegin(final FacesContext context,
            final UIComponent component)
            throws IOException {

        rendererParamsNotNull(context, component);

        if (!RendererUtils.shouldEncode(component)) {
            return;
        }

        final UITreeTable data = (UITreeTable) component;
        final ResponseWriter writer = context.getResponseWriter();
        data.setRowIndex(-1);

        // Render js and css header for treetable
        writeHeaders(context, component, writer);

        renderTableStart(context, component, writer, ATTRIBUTES);

        // Render the caption if present
        renderCaption(context, data, writer);
        // Render the header facets (if any)
        renderHeader(context, component, writer);
        // Render the footer facets (if any)
        renderFooter(context, component, writer);

    }

    @Override
    public void encodeChildren(final FacesContext context,
            final UIComponent component)
            throws IOException {

        rendererParamsNotNull(context, component);

        if (!RendererUtils.shouldEncodeChildren(component)) {
            return;
        }

        final UITreeTable data = (UITreeTable) component;
        final ResponseWriter writer = context.getResponseWriter();
        
        // Iterate over the rows of data that are provided
        int processed = 0;
        data.setRowIndex(data.getFirst() - 1);
        final int rows = data.getRows();

        renderTableBodyStart(component, writer);
        while (true) {

            // Have we displayed the requested number of rows?
            if ((rows > 0) && (++processed > rows)) {
                break;
            }
            int rowIndex = data.getRowIndex() + 1;
            // Select the current row
            data.setRowIndex(rowIndex);
            if (!data.isRowAvailable()) {
                break; // Scrolled past the last row
            }

            // Render the beginning of this row
            renderRowStart(component, writer);
            // Render the row content
            renderRow(context, component, null, writer);
            // Render the ending of this row
            renderRowEnd(component, writer);

        }
        renderTableBodyEnd(component, writer);

        // Clean up after ourselves
        data.setRowIndex(-1);
    }

    @Override
    public void encodeEnd(final FacesContext context,
            final UIComponent component)
            throws IOException {

        rendererParamsNotNull(context, component);

        if (!RendererUtils.shouldEncode(component)) {
            return;
        }

        final UITreeTable data = (UITreeTable) component;

        clearMetaInfo(data);
        data.setRowIndex(-1);

        // Render the ending of this table
        renderTableEnd(component, context.getResponseWriter());
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    // ------------------------------------------------------- Protected Methods
    @Override
    protected void renderFooter(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        final TableMetaInfo info = getMetaInfo(table);
        final UIComponent footer = getFacet(table, "footer");
        final String footerClass = (String) table.getAttributes().get("footerClass");

        if ((footer != null) || info.hasFooterFacets) {
            writer.startElement("div", table);
            writer.writeText("\n", table, null);
        }

        if (info.hasFooterFacets) {
            writer.startElement("div", table);
            writer.writeText("\n", table, null);
            for (UITreeColumn column : info.columns) {
                String columnFooterClass =
                        (String) column.getAttributes().get("footerClass");
                writer.startElement("div", column);
                if (columnFooterClass != null) {
                    writer.writeAttribute("class", columnFooterClass,
                            "columnFooterClass");
                } else if (footerClass != null) {
                    writer.writeAttribute("class", footerClass, "footerClass");
                }
                UIComponent facet = getFacet(column, "footer");
                if (facet != null) {
                    encodeRecursive(context, facet);
                }
                writer.endElement("div");
                writer.writeText("\n", table, null);
            }
            renderRowEnd(table, writer);
        }

        if (footer != null) {
            writer.startElement("div", footer);
            writer.startElement("div", footer);
            if (footerClass != null) {
                writer.writeAttribute("class", footerClass, "footerClass");
            }
            writer.writeAttribute("colspan", String.valueOf(info.columns.size()), null);
            encodeRecursive(context, footer);
            writer.endElement("div");
            renderRowEnd(table, writer);
        }

        if ((footer != null) || (info.hasFooterFacets)) {
            writer.endElement("div");
            writer.writeText("\n", table, null);
        }

    }

    @Override
    protected void renderHeader(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        final TableMetaInfo info = getMetaInfo(table);
        final UIComponent header = getFacet(table, "header");
        final String headerClass = (String) table.getAttributes().get("headerClass");

        if ((header != null) || (info.hasHeaderFacets)) {
            writer.startElement("div", table);
            writer.writeText("\n", table, null);
        }

        if (header != null) {
            writer.startElement("div", header);
            writer.startElement("div", header);
            if (headerClass != null) {
                writer.writeAttribute("class", headerClass + " " + ITEM_STYLE, "headerClass");
            }

            encodeRecursive(context, header);
            writer.endElement("div");
            renderRowEnd(table, writer);
        }

        if (info.hasHeaderFacets) {
            writer.startElement("div", table);
            writer.writeText("\n", table, null);
            for (UITreeColumn column : info.columns) {
                String columnHeaderClass =
                        (String) column.getAttributes().get("headerClass");
                writer.startElement("div", column);

                final String columnClass;
                if (columnHeaderClass != null) {
                    columnClass = columnHeaderClass;
                } else if (headerClass != null) {
                    columnClass = headerClass;
                } else {
                    columnClass = "";
                }
                writer.writeAttribute("class", columnClass + " " + ITEM_STYLE, null);
                if (column.getWidth() != null) {
                    writer.writeAttribute("style", "width:" + column.getWidth() + ";", "itemClass");
                } else {
                    writer.writeAttribute("style", "width:" + DEFAULT_WIDTH_COLUMN_VALUE + ";", "itemClass");
                }
                UIComponent facet = getFacet(column, "header");
                if (facet != null) {
                    encodeRecursive(context, facet);
                }
                writer.endElement("div");
                writer.writeText("\n", table, null);

            }
            renderRowEnd(table, writer);
        }

        if ((header != null) || info.hasHeaderFacets) {
            writer.endElement("div");
            writer.startElement("div", table);
            writer.writeAttribute("class", "x-clear", "itemClass");
            writer.endElement("div");
            writer.writeText("\n", table, null);
        }

    }

    @Override
    protected void renderRow(final FacesContext context,
            final UIComponent table,
            final UIComponent child,
            final ResponseWriter writer)
            throws IOException {

        // Iterate over the child UITreeColumn components for each row
        int columnStyleIdx = 0;

        final UITreeTable data = (UITreeTable) table;
        final TableMetaInfo info = getMetaInfo(table);
        final ExtendMutableTreeNode node;
        final int collapseDepth = (Integer) table.getAttributes().get("collapseDepth");

        if (data.getRowData() != null) {
            node = (ExtendMutableTreeNode) data.getRowData();
        } else {
            return;
        }

        writer.startElement("div", table);
        final StringBuilder styles = new StringBuilder();
        final StringBuilder classes = new StringBuilder();
        final String rowClasses = (String) table.getAttributes().get("rowClasses");
        final String nodeStyle = (String) table.getAttributes().get("nodeStyle");
        final String nodeClass = (String) table.getAttributes().get("nodeClass");
        final String leafStyle = (String) table.getAttributes().get("leafStyle");
        final String leafClass = (String) table.getAttributes().get("leafClass");
        final String oddLineStyle = (String) table.getAttributes().get("oddLineStyle");
        final String oddLineClass = (String) table.getAttributes().get("oddLineClass");
        final String evenLineStyle = (String) table.getAttributes().get("evenLineStyle");
        final String evenLineClass = (String) table.getAttributes().get("evenLineClass");
        //Style for node and leaf
        if (node.getChildCount() > 0) {
            if (nodeStyle != null) {
                styles.append(nodeStyle);
            }
            if (nodeClass != null) {
                classes.append(nodeClass);
            }
        } else {
            if (leafStyle != null) {
                styles.append(leafStyle);
            }
            if (leafClass != null) {
                classes.append(leafClass);
            }
        }

        //Style for odd and even row
        if (oddLineStyle != null && node.getId() % 2 == 0) {
            styles.append(oddLineStyle);
        } else {
            if (evenLineStyle != null) {
                styles.append(evenLineStyle);
            }
        }
        if (oddLineClass != null && node.getId() % 2 == 0) {
            styles.append(oddLineClass);
        } else {
            if (evenLineClass != null) {
                styles.append(evenLineClass);
            }
        }

        //Style for all rows
        if (rowClasses != null) {
            classes.append(rowClasses);
        }

        if (styles.length() > 0) {
            writer.writeAttribute("style", styles.toString(), "styles");
        }
        if (classes.length() > 0) {
            writer.writeAttribute("class", classes.toString(), "classes");
        }
        for (UITreeColumn column : info.columns) {
            // Render the beginning of this cell
            writer.startElement("div", column);
            if (info.columnClasses.length > 0) {
                writer.writeAttribute("class", info.columnClasses[columnStyleIdx++], "columnClasses");
                if (columnStyleIdx >= info.columnClasses.length) {
                    columnStyleIdx = 0;
                }
            }

            if (column.getWidth() != null) {
                writer.writeAttribute("style", "width:" + column.getWidth() + ";", "itemClass");
            } else {
                writer.writeAttribute("style", "width:" + DEFAULT_WIDTH_COLUMN_VALUE + ";", "itemClass");
            }

            writer.writeAttribute("class", ITEM_STYLE, "itemClass");
            if (column.isViewControls()) {
                // Add images and actions controls
                renderControls(context, table, column, node, writer);
            }

            // Render the contents of this cell by iterating over
            // the kids of our kids
            for (Iterator<UIComponent> gkids = getChildren(column);
                    gkids.hasNext();) {
                encodeRecursive(context, gkids.next());
            }
            writer.write("&nbsp;");

            // Render the ending of this cell
            writer.endElement("div");
            writer.writeText("\n", table, null);


            // Render the clear div of this cell if it's the last cell of the row
            List<UITreeColumn> list = info.columns;
            if (list.get(list.size() - 1).equals(column)) {

                writer.startElement("div", column);
                writer.writeAttribute("class", "x-clear", "itemClass");
                writer.endElement("div");

                if (node.getChildCount() > 0) {
                    writer.endElement("div");

                    writer.startElement("ul", table);
                    writer.writeAttribute("id", "ul:" + table.getClientId(context), "id");
                    if (node.getDepth() > 0) {
                        writer.writeAttribute("class", UL_CLASS, "class");
                        if (data.isCollapse() && collapseDepth < node.getDepth()) {
                            writer.writeAttribute("style", "display:none;", "style");
                        }
                    }
                    for (int i = 0; i < node.getChildCount(); i++) {
                        int rowIndex = data.getRowIndex();
                        data.setRowIndex(rowIndex + 1);

                        if (!data.isRowAvailable()) {
                            break; // Scrolled past the last row
                        }
                        renderRow(context, table, writer);
                    }
                    writer.endElement("ul");
                }
            }
        }

    }

    protected void renderRow(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException {
        
        final UITreeTable data = (UITreeTable) table;
        final ExtendMutableTreeNode node = (ExtendMutableTreeNode) data.getRowData();
        final int collapseDepth = (Integer) table.getAttributes().get("collapseDepth");

        if ((data.isActivateAjaxLoading() && node.getDepth() < collapseDepth && !node.isRendered())
                || (!data.isActivateAjaxLoading())
                || (node.isRendered())) {
            
            // Render the beginning of this row
            renderRowStart(table, writer);
            // Render the row content
            renderRow(context, table, null, writer);
            // Render the ending of this row
            renderRowEnd(table, writer);
            
        }

    }

    protected void renderControls(final FacesContext context,
            final UIComponent component,
            final UITreeColumn column,
            final ExtendMutableTreeNode node,
            final ResponseWriter writer)
            throws IOException {

        final UITreeTable treetable = (UITreeTable) component;
        final int collapseDepth = treetable.getCollapseDepth();

        for (int i = 0; i < node.getDepth(); i++) {
            writer.startElement("div", component);
            writer.writeAttribute("class", ELBOW_LINE_STYLE, "elbowClass");
            writer.endElement("div");
        }

        writer.startElement("div", component);
        writer.writeAttribute("id", "act:" + component.getClientId(context), "id");
        if (!node.isLeaf()) {
            if (treetable.isCollapse() && node.getDepth() > collapseDepth) {
                writer.writeAttribute("class", NODE_COLLAPSE_SYMBOLE, "iconClass");
            } else {
                writer.writeAttribute("class", NODE_EXPAND_SYMBOLE, "iconClass");
            }
            writer.writeAttribute("onclick", "TREETABLE.switchView('act:" + component.getClientId(context) + "', 'ul:" + component.getClientId(context) + "')", "actions");
        } else {
            if (!node.isRoot()) {
                ExtendMutableTreeNode parent = (ExtendMutableTreeNode) node.getParent();
                if (parent.getLastChild() != node) {
                    writer.writeAttribute("class", ELBOW_STYLE, "elbowClass");
                } else {
                    writer.writeAttribute("class", ELBOW_END_STYLE, "elbowClass");
                }

            }
        }
        writer.endElement("div");

        writer.startElement("div", component);
        
        /*
         * Render a specific icon as a background-image style of DIV element
         * only if the treeitem contains an icon defined by the user.
         */
        String iconUrl = null;
        if (node.getUserObject() instanceof AbstractTreeItem) {
            AbstractTreeItem treeItem = (AbstractTreeItem) node.getUserObject();
            iconUrl = treeItem.getIcon();
        }

        if (!node.isLeaf()) {
            if (treetable.isCollapse() && node.getDepth() > collapseDepth) {
                writer.writeAttribute("class", NODE_COLLAPSE_STYLE, "iconClass");
            } else {
                writer.writeAttribute("class", NODE_EXPAND_STYLE, "iconClass");
            }
        } else {
            writer.writeAttribute("class", LEAF_EXPAND_STYLE, "iconClass");
        }
        if(iconUrl != null){
            writer.writeAttribute("style", "background-image:url('"+iconUrl+"')", "style");
        }
        writer.endElement("div");

    }

    protected void writeHeaders(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        UITreeTable treetable = (UITreeTable) table;
        if(treetable.isLoadCss()){
            writer.startElement("link", table);
            writer.writeAttribute("type", "text/css", null);
            writer.writeAttribute("rel", "stylesheet", null);
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_CORE, null), null);
            writer.endElement("link");
        }
        if(treetable.isLoadJs()){
            writer.startElement("script", table);
            writer.writeAttribute("type", "text/javascript", null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_CORE, null), null);
            writer.endElement("script");
        }

    }
}
