/*
 *    Mapfaces - http://www.mapfaces.org
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

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.datatable.UIDataScroller;
import org.mapfaces.share.utils.FacesUtils;

/**
 * This is the renderer od datascroller component
 * It renders a set of links to each page of a table, to the next and previous pages,
 * and if there are a great number of pages, to the next and previous batch of pages.
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class DataScrollerRenderer extends Renderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        // suppress rendering if "rendered" property on the component is false.
        if (!component.isRendered()) {
            return;
        }
        FacesUtils.assertValid(context, component);

        super.encodeBegin(context, component);

        UIDataScroller pager = (UIDataScroller) component;

        String id = pager.getClientId(context);

        String formId = FacesUtils.getFormClientId(context, pager);

        ResponseWriter writer = context.getResponseWriter();

        String style = pager.getStyle();
        String styleClass = pager.getStyleClass();
        String selectedStyleClass = pager.getSelectedStyleClass();
        String dataTableId = pager.getDataTableId();

        int showpages = pager.getShowpages();

        // find the component with the given ID
        UIData data = (UIData) FacesUtils.findComponentById(context, context.getViewRoot(), dataTableId);
        if (data == null) {
            if(pager.isDebug()){
                Logger.getLogger(DataScrollerRenderer.class.getName()).log(Level.WARNING, "There is no datatable component specified by the dataTableId property : "+dataTableId);
            }
            return; //the renderer cannot continue if the target datatable component is not present in the page.
        }
        int first = data.getFirst();
        System.out.println(">>>>>>> first = "+first);
        int rowcount = data.getRowCount();
        System.out.println(">>>>>>> rowCount = "+rowcount);
        int pagesize = data.getRows();
        System.out.println(">>>>>>>>>  rows = "+pagesize);
        if (pagesize <= 0) {
            pagesize = rowcount;
        }

        int pages = rowcount / pagesize;
        if (rowcount % pagesize != 0) {
            pages++;
        }

        int currentPage = first / pagesize;
        if (first >= rowcount - pagesize) {
            currentPage = pages - 1;
        }
        int startPage = 0;
        int endPage = pages;
        if (showpages > 0) {
            startPage = (currentPage / showpages) * showpages;
            endPage = Math.min(startPage + showpages, pages);
        }

        if (currentPage > 0) {
            writeLink(writer, pager, formId, id, "<", styleClass);
        }

        if (startPage > 0) {
            writeLink(writer, pager, formId, id, "<<", styleClass);
        }

        for (int i = startPage; i < endPage; i++) {
            writeLink(writer, pager, formId, id, "" + (i + 1),
                    i == currentPage ? selectedStyleClass : styleClass);
        }

        if (endPage < pages) {
            writeLink(writer, pager, formId, id, ">>", styleClass);
        }

        if (first < rowcount - pagesize) {
            writeLink(writer, pager, formId, id, ">", styleClass);
        }

        // hidden field to hold result
        writeHiddenField(writer, pager, id);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        String id = component.getClientId(context);
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();

        String response = (String) parameters.get(id);
        if (response == null || response.equals("")) {
            return;
        }

        String dataTableId = (String) component.getAttributes().get("dataTableId");
        Integer a = (Integer) component.getAttributes().get("showpages");
        int showpages = a == null ? 0 : a.intValue();

        UIData data = (UIData) component.findComponent(dataTableId);

        int first = data.getFirst();
        int itemcount = data.getRowCount();
        int pagesize = data.getRows();
        if (pagesize <= 0) {
            pagesize = itemcount;
        }

        if (response.equals("<")) {
            first -= pagesize;
        } else if (response.equals(">")) {
            first += pagesize;
        } else if (response.equals("<<")) {
            first -= pagesize * showpages;
        } else if (response.equals(">>")) {
            first += pagesize * showpages;
        } else {
            int page = Integer.parseInt(response);
            first = (page - 1) * pagesize;
        }
        if (first + pagesize > itemcount) {
            first = itemcount - pagesize;
        }
        if (first < 0) {
            first = 0;
        }
        data.setFirst(first);
    }

    private void writeLink(ResponseWriter writer, UIComponent component,
            String formId, String id, String value, String styleClass)
            throws IOException {
        writer.writeText(" ", null);
        writer.startElement("a", component);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("onclick", onclickCode(formId, id, value), null);
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }
        writer.writeText(value, null);
        writer.endElement("a");
    }

    private String onclickCode(String formId, String id, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append("document.forms[");
        builder.append("'");
        builder.append(formId);
        builder.append("'");
        builder.append("]['");
        builder.append(id);
        builder.append("'].value='");
        builder.append(value);
        builder.append("';");
        builder.append(" document.forms[");
        builder.append("'");
        builder.append(formId);
        builder.append("'");
        builder.append("].submit()");
        builder.append("; return false;");
        return builder.toString();
    }

    private void writeHiddenField(ResponseWriter writer, UIComponent component,
            String id) throws IOException {
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("name", id, null);
        writer.endElement("input");
    }
}
