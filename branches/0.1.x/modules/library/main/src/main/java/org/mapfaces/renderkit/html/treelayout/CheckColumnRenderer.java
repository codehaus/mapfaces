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

package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

/**
 * @author Kevin Delfour
 */
public class CheckColumnRenderer extends AbstractColumnRenderer{

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UICheckColumn comp                 = (UICheckColumn) component;
        final ResponseWriter writer              = context.getResponseWriter();
        final HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();

        checkbox.setId("check_" + comp.getId());
        checkbox.setValue(comp.getValue());
        //checkbox.setStyle("cursor:pointer;position: absolute; margin-left:-7px;left: 50%; margin-top: -7px; top: 50%; ");
        comp.getChildren().add(checkbox);
        writer.startElement("center", component);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

}
