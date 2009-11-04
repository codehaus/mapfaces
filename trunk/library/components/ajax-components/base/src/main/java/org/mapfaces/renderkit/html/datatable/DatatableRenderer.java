/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.renderkit.html.datatable;

import com.sun.faces.renderkit.html_basic.TableRenderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


import org.mapfaces.component.datatable.UIColumns;
import org.mapfaces.component.datatable.UIDatatable;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.FacesUtils;

/**
 * This is the renderer class for UIDatatable component that allows a sorting filter 
 * (by mootools script)
 *
 * @author Mehdi Sidhoum (Geomatys)
 * @author Kevin Delfour (IRD)
 * @since 0.3
 */
public class DatatableRenderer extends TableRenderer {

    private static final String MOOTOOLS_CORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private static final String MOOTOOLS_MORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";
    private static final String DATATABLE_CSS = "/org/mapfaces/resources/datatable/css/sortableTable.css";
    private static final String DATATABLE_JS = "/org/mapfaces/resources/datatable/js/sortableTable.js";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        final UIDatatable comp = (UIDatatable) component;
        /*
         * Applying style class only for this datatable family (mapfaces)
         * This is to avoid to conflict with another table.
         */
        if (comp.getStyleClass() != null) {
            if (!comp.getStyleClass().contains("mfdatatable")) {
                comp.setStyleClass(comp.getStyleClass() + " mfdatatable");
            }
        } else {
            comp.setStyleClass("mfdatatable");
        }

        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }

        super.encodeBegin(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        final ResponseWriter writer = context.getResponseWriter();
        final UIDatatable comp = (UIDatatable) component;

        /* Adding JS and CSS files needed */
        writeHeaders(context, component);

        /**
         * @Todo :JS Addiction for sortable functionnality
         * new sortableTable('basicTable', {
        overCls: 'over', sortOn: '0', sortBy: 'ASC'
        });
         *
         * filterHide:false
         * <form id="tableFilter" onsubmit="advancedTable.filter(this.id); return false;">Filter:
        <select id="column">
        <option value="1">Firstname</option>
        <option value="2">Lastname</option>
        <option value="3">Department</option>
        <option value="4">Start Date</option>
        </select>
        <input type="text" id="keyword" />
        <input type="submit" value="Submit" />
        <input type="reset" value="Clear" />
        </form>
         * @todo : add options filter ...
         */
        if (comp.isSortable()) {

            // Add OverCls class
            String overCls = comp.getOverCls();
            if (overCls == null) {
                overCls = "over";
            }

            //Modify sortOn attribute
            final int sortOn = comp.getSortOn();

            //Modify sortBy attribute
            String sortBy = "ASC";
            if (comp.getSortBy() != null) {
                if (comp.getSortBy().toUpperCase().equals("DESC")) {
                    sortBy = comp.getSortBy().toUpperCase();
                }
            }

            //Adding js script to add axis attributes to all column
            /*
            var ths = ($('userHome:all').getChildren()[0]).getChildren()[0];
            ths.getChildren()[0].set('axis','string');
             */

            final StringBuilder st = new StringBuilder();
            st.append("var ths = ($('").append(comp.getClientId(context)).append("').getChildren()[0]).getChildren()[0];");

            int i = 0;
            for (UIComponent column : comp.getChildren()) {
                if (column instanceof UIColumns) {
                    st.append("ths.getChildren()[").append(i).append("].set('axis','").append(((UIColumns) column).getAxis()).append("');");
                    i++;
                }
            }
            st.append("function ").append(comp.getId()).append("_loading(){var ").append(comp.getId()).append("_datatable = new SortableTable('").append(comp.getClientId(context)).append("',{");
            st.append("overCls:'").append(overCls).append("',");
            st.append("sortOn:'").append(sortOn).append("',");
            st.append("sortBy:'").append(sortBy).append("'");
            st.append("});};").append(comp.getId()).append("_loading();");
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", "type");
            writer.write(st.toString());
            writer.endElement("script");
        }
    }

    /**
     * Write headers css and js with the resource
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    private void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final UIDatatable comp = (UIDatatable) component;
        final ResponseWriter writer = context.getResponseWriter();

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_CORE_JS, null), null);
        writer.endElement("script");
        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_MORE_JS, null), null);
        writer.endElement("script");

        writer.startElement("link", comp);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, DATATABLE_CSS, null), null);
        writer.endElement("link");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, DATATABLE_JS, null), null);
        writer.endElement("script");
    }
}
