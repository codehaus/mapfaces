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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.ajax4jsf.ajax.html.HtmlActionParameter;
import org.ajax4jsf.ajax.html.HtmlAjaxCommandLink;
import org.mapfaces.component.UIDiv;
import org.mapfaces.component.datatable.UIDataScroller;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.FacesUtils;

/**
 * This is the renderer of datascroller component to allow a pagination feature
 * It renders a set of links to each page of a table, to the next and previous pages,
 * and if there are a great number of pages, to the next and previous batch of pages.
 * This component is compatible with UIDatatable of mapfaces
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class DataScrollerRenderer extends Renderer {

    private String PREVIOUS_IMG = "/org/mapfaces/resources/datatable/images/resultset_previous.png";
    private String NEXT_IMG = "/org/mapfaces/resources/datatable/images/resultset_next.png";
    private String BACKWARD_IMG = "/org/mapfaces/resources/datatable/images/resultset_backward.png";
    private String FORWARD_IMG = "/org/mapfaces/resources/datatable/images/resultset_forward.png";

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

        ResponseWriter writer = context.getResponseWriter();

        String style = pager.getStyle() != null ? pager.getStyle() : "";
        String styleClass = pager.getStyleClass();
        String selectedStyleClass = pager.getSelectedStyleClass();
        String dataTableId = pager.getDataTableId();
        int showpages = pager.getShowpages();

        // find the component with the given ID
        UIData data = (UIData) FacesUtils.findComponentById(context, context.getViewRoot(), dataTableId);
        if (data == null) {
            if (pager.isDebug()) {
                Logger.getLogger(DataScrollerRenderer.class.getName()).log(Level.WARNING, "There is no datatable component specified by the dataTableId property : " + dataTableId);
            }
            return; //the renderer cannot continue if the target datatable component is not present in the page.
        }

        //Start of rendering the component
        UIDiv uiDiv = new UIDiv();
        uiDiv.setId(pager.getId()+"_div");
        uiDiv.setStyle(style);
        
        int first = data.getFirst();
        int rowcount = data.getRowCount();
        int pagesize = data.getRows();
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



        UIDiv existingDiv = null;
        foo:for(UIComponent child : pager.getChildren()){
            if(child instanceof UIDiv){
                existingDiv = (UIDiv) child;
                break foo;
            }
        }
       
        if (startPage > 0) {
            appendLink(context, writer, pager, ResourcePhaseListener.getURL(context, BACKWARD_IMG, null), "<<", styleClass, uiDiv);
        }
        if (currentPage > 0) {
            appendLink(context, writer, pager, ResourcePhaseListener.getURL(context, PREVIOUS_IMG, null), "<", styleClass, uiDiv);
        }

        for (int i = startPage; i < endPage; i++) {
            appendLink(context, writer, pager, null, "" + (i + 1), i == currentPage ? selectedStyleClass : styleClass, uiDiv);
        }

        if (first < rowcount - pagesize) {
            appendLink(context, writer, pager, ResourcePhaseListener.getURL(context, NEXT_IMG, null), ">", styleClass, uiDiv);
        }

        if (endPage < pages) {
            appendLink(context, writer, pager, ResourcePhaseListener.getURL(context, FORWARD_IMG, null), ">>", styleClass, uiDiv);
        }

        FacesUtils.removeChildren(context, pager);//clean pager children
        pager.getChildren().add(uiDiv);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        String key = component.getClientId(context)+"_key";
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();

        String response = (String) parameters.get(key);
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

    /**
     * Adds an a4j commandLink as child of the wrapper div with the associated action parameter that will
     * be catched in decode process.
     * @param context
     * @param writer
     * @param component
     * @param imgIcon
     * @param value
     * @param styleClass
     * @param wrapper
     * @throws java.io.IOException
     */
    private void appendLink(FacesContext context, ResponseWriter writer, UIDataScroller component,
            String imgIcon, String value, String styleClass, UIDiv wrapper) {
//        writer.writeText(" ", null);

        HtmlAjaxCommandLink link = new HtmlAjaxCommandLink();
        link.setReRender(component.getDataTableId()+", "+component.getId());

        String formId = FacesUtils.getFormId(context, component);

        link.setOncomplete("A4J.AJAX.Submit('"+formId+"','"+formId+"',null,{'single':'true','parameters':{'"+component.getClientId(context)+"_ajax':'"+component.getClientId(context)+"_ajax','refresh':'"+wrapper.getClientId(context)+"'},'actionUrl':window.location.href} );" +
                " var ths = ($('mainform:mfdatatable').getChildren()[0]).getChildren()[0];ths.getChildren()[0].set('axis','number');ths.getChildren()[1].set('axis','string');function mfdatatable_loading(){var mfdatatable_datatable = new SortableTable('mainform:mfdatatable',{overCls:'over',sortOn:'0',sortBy:'ASC'});};mfdatatable_loading();");

        link.setAjaxSingle(true);
        link.setValue(value);
        if (styleClass != null) {
            link.setStyleClass(styleClass);
        }

        HtmlActionParameter param = new HtmlActionParameter();
        param.setName(component.getClientId(context)+"_key");
        param.setValue(value);
        link.getChildren().add(param);
        if (!wrapper.getChildren().contains(link)) {
            wrapper.getChildren().add(link);
        }
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
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final List<UIComponent> childrens = component.getChildren();
        for (final UIComponent tmp : childrens) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }
}
