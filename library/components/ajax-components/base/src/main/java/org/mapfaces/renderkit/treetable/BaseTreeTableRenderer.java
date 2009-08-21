package org.mapfaces.renderkit.treetable;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;

import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;
import com.sun.faces.util.Util;

import org.mapfaces.component.treetable.UITreecolumn;
import org.mapfaces.component.treetable.UITreeData;
import org.mapfaces.component.treetable.UITreetable;
import org.mapfaces.model.tree.ExtendMutableTreeNode;
import org.mapfaces.share.utils.FacesUtils;

/**
 * Base class for concrete Tree Table renderers.
 */
public abstract class BaseTreeTableRenderer extends HtmlBasicRenderer {

    // ------------------------------------------------------- Protected Methods
    /**
     * Called to render the opening/closing <code>thead</code> elements
     * and any content nested between.
     * @param context the <code>FacesContext</code> for the current request
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected abstract void renderHeader(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException;

    /**
     * Called to render the opening/closing <code>tfoot</code> elements
     * and any content nested between.
     * @param context the <code>FacesContext</code> for the current request
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected abstract void renderFooter(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException;

    /**
     * Call to render the content that should be included between opening
     * and closing <code>tr</code> elements.
     * @param context the <code>FacesContext</code> for the current request
     * @param table the table that's being rendered
     * @param row the current row (if any - an implmenetation may not need this)
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected abstract void renderRow(final FacesContext context,
            final UIComponent table,
            final UIComponent row,
            final ResponseWriter writer)
            throws IOException;

    /**
     * Renders the start of a treetable and applies the value of
     * <code>styleClass</code> if available and renders any
     * pass through attributes that may be specified.
     * @param context the <code>FacesContext</code> for the current request
     * @param table the table that's being rendered
     * @param writer the current writer
     * @param passThroughAttributes pass-through attributes that the component
     *  supports
     * @throws IOException if content cannot be written
     */
    protected void renderTableStart(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer,
            final String[] passThroughAttributes)
            throws IOException {

        writer.startElement("div", table);
        writeIdAttributeIfNecessary(context, writer, table);

        final String styleClass = (String) table.getAttributes().get("styleClass");
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        final String style = (String) table.getAttributes().get("style");
        final String bgcolor = (String) table.getAttributes().get("bgcolor");
        final Integer border = (Integer) table.getAttributes().get("border");

        String styleConcate = null;
        if (bgcolor != null) {
            styleConcate = "background-color:" + bgcolor + ";";
        }
        if (border != null) {
            styleConcate += "border:" + border + "px solid;";
        }
        if (style != null) {
            styleConcate += style;
        }
        if (styleConcate != null) {
            writer.writeAttribute("style", styleConcate, "styleClass");
        }

        //RenderKitUtils.renderPassThruAttributes(writer, table, passThroughAttributes);

        writer.writeText("\n", table, null);

    }

    /**
     * Renders the closing <code>div</code> element for the treetable.
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderTableEnd(final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        writer.endElement("div");
        writer.writeText("\n", table, null);

    }

    /**
     * Renders the caption of the table applying the values of
     * <code>captionClass</code> as the class and <code>captionStyle</code>
     * as the style if either are present.
     * @param context the <code>FacesContext</code> for the current request
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderCaption(final FacesContext context,
            final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        final UIComponent caption = getFacet(table, "caption");
        if (caption != null) {
            final String captionClass = (String) table.getAttributes().get("captionClass");
            final String captionStyle = (String) table.getAttributes().get("captionStyle");
            writer.startElement("caption", table);
            if (captionClass != null) {
                writer.writeAttribute("class", captionClass, "captionClass");
            }
            if (captionStyle != null) {
                writer.writeAttribute("style", captionStyle, "captionStyle");
            }
            FacesUtils.encodeRecursive(context, caption);
            writer.endElement("caption");
        }

    }

    /**
     * Renders the starting <code>ul</code> of body element.
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderTableBodyStart(final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        writer.startElement("ul", table);
        writer.writeText("\n", table, null);


    }

    /**
     * Renders the closing <code>ul</code> of body element.
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderTableBodyEnd(final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        writer.endElement("ul");
        writer.writeText("\n", table, null);

    }

    /**
     * Renders the starting <code>li</code> element applying any values
     * from the <code>rowClasses</code> attribute.
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderRowStart(final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        final UITreetable data = (UITreetable) table;
        final TableMetaInfo info = getMetaInfo(table);

        if (data.getRowData() instanceof String) {
            writer.startElement("li", table);
            if (info.rowClasses.length > 0) {
                writer.writeAttribute("class", info.getCurrentRowClass(), "rowClasses");
            }
        } else {
            writer.startElement("li", table);
            writer.writeAttribute("id", "li:" + table.getId() + ":" + data.getRowIndex(), "id");           
        }
        writer.writeText("\n", table, null);
    }

    /**
     * Renders the closing <code>li</code> element.
     * @param table the table that's being rendered
     * @param writer the current writer
     * @throws IOException if content cannot be written
     */
    protected void renderRowEnd(final UIComponent table,
            final ResponseWriter writer)
            throws IOException {

        writer.endElement("li");
        writer.writeText("\n", table, null);

    }

    /**
     * Returns a <code>TableMetaInfo</code> object containing details such
     * as row and column classes, columns, and a mechanism for scrolling through
     * the row/column classes.
     * @param table the table that's being rendered
     * @return the <code>TableMetaInfo</code> for provided table
     */
    protected TableMetaInfo getMetaInfo(final UIComponent table) {

        TableMetaInfo info = (TableMetaInfo) table.getAttributes().get(TableMetaInfo.KEY);
        if (info == null) {
            info = new TableMetaInfo(table);
            table.getAttributes().put(TableMetaInfo.KEY, info);
        }
        return info;

    }

    /**
     * Removes the cached TableMetaInfo from the specified component.
     * @param table the table from which the TableMetaInfo will be removed
     */
    protected void clearMetaInfo(UIComponent table) {

        table.getAttributes().remove(TableMetaInfo.KEY);

    }

    // ----------------------------------------------------------- Inner Classes
    protected static class TableMetaInfo {

        private final static UITreecolumn PLACE_HOLDER_COLUMN = new UITreecolumn();
        private final static String[] EMPTY_STRING_ARRAY = new String[0];
        public final static String KEY = TableMetaInfo.class.getName();
        public final String[] rowClasses;
        public final String[] columnClasses;
        public final List<UITreecolumn> columns;
        public final boolean hasHeaderFacets;
        public final boolean hasFooterFacets;
        public int columnStyleCounter;
        public int rowStyleCounter;

        // -------------------------------------------------------- Constructors
        public TableMetaInfo(UIComponent table) {

            rowClasses = getRowClasses(table);
            columnClasses = getColumnClasses(table);
            columns = getColumns(table);
            hasHeaderFacets = hasFacet("header", columns);
            hasFooterFacets = hasFacet("footer", columns);

        }

        // ------------------------------------------------------ Public Methods
        /**
         * Obtain the column class based on the current counter.  Calling this
         * method automatically moves the pointer to the next style.  If the
         * counter is larger than the number of total classes, the counter will
         * be reset.
         * @return the current style
         */
        public String getCurrentColumnClass() {

            String style = columnClasses[columnStyleCounter++];
            if (columnStyleCounter >= columnClasses.length) {
                columnStyleCounter = 0;
            }
            return style;

        }

        /**
         * Obtain the row class based on the current counter.  Calling this
         * method automatically moves the pointer to the next style.  If the
         * counter is larger than the number of total classes, the counter will
         * be reset.
         * @return the current style
         */
        public String getCurrentRowClass() {

            String style = rowClasses[rowStyleCounter++];
            if (rowStyleCounter >= rowClasses.length) {
                rowStyleCounter = 0;
            }
            return style;

        }

        // ----------------------------------------------------- Private Methods
        /**
         * <p>Return an array of stylesheet classes to be applied to each column in
         * the table in the order specified. Every column may or may not have a
         * stylesheet.</p>
         *
         * @param table {@link javax.faces.component.UIComponent} component being rendered
         *
         * @return an array of column classes
         */
        private static String[] getColumnClasses(UIComponent table) {

            String values = (String) table.getAttributes().get("columnClasses");
            if (values == null) {
                return EMPTY_STRING_ARRAY;
            }
            return Util.split(values.trim(), ",");

        }

        /**
         * <p>Return an Iterator over the <code>UIColumn</code> children of the
         * specified <code>UITreeData</code> that have a <code>rendered</code> property
         * of <code>true</code>.</p>
         *
         * @param table the table from which to extract children
         *
         * @return the List of all UIColumn children
         */
        private static List<UITreecolumn> getColumns(UIComponent table) {

            if (table instanceof UITreeData) {
                int childCount = table.getChildCount();
                if (childCount > 0) {
                    List<UITreecolumn> results =
                            new ArrayList<UITreecolumn>(childCount);
                    for (UIComponent kid : table.getChildren()) {
                        if ((kid instanceof UITreecolumn) && kid.isRendered()) {
                            results.add((UITreecolumn) kid);
                        }
                    }
                    return results;
                } else {
                    return Collections.emptyList();
                }
            } else {
                int count;
                Object value = table.getAttributes().get("columns");
                if ((value != null) && (value instanceof Integer)) {
                    count = ((Integer) value);
                } else {
                    count = 2;
                }
                if (count < 1) {
                    count = 1;
                }
                List<UITreecolumn> result = new ArrayList<UITreecolumn>(count);
                for (int i = 0; i < count; i++) {
                    result.add(PLACE_HOLDER_COLUMN);
                }
                return result;
            }

        }

        /**
         * <p>Return the number of child <code>UIColumn</code> components nested in
         * the specified <code>UITreeData</code> that have a facet with the specified
         * name.</p>
         *
         * @param name    Name of the facet being analyzed
         * @param columns the columns to search
         *
         * @return the number of columns associated with the specified Facet name
         */
        private static boolean hasFacet(String name, List<UITreecolumn> columns) {

            if (!columns.isEmpty()) {
                for (UITreecolumn column : columns) {
                    if (column.getFacetCount() > 0) {
                        if (column.getFacets().containsKey(name)) {
                            return true;
                        }
                    }
                }
            }
            return false;

        }

        /**
         * <p>Return an array of stylesheet classes to be applied to each row in the
         * table, in the order specified.  Every row may or may not have a
         * stylesheet.</p>
         *
         * @param table {@link javax.faces.component.UIComponent} component being rendered
         *
         * @return an array of row classes
         */
        private static String[] getRowClasses(UIComponent table) {

            String values = (String) table.getAttributes().get("rowClasses");
            if (values == null) {
                return (EMPTY_STRING_ARRAY);
            }
            return Util.split(values.trim(), ",");

        }
    } // END UITreeDataMetaInfo
}
