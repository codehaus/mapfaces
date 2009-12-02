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
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

/**
 * @author Kevin Delfour
 */
public class ColumnRenderer extends AbstractColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        addRequestScript(context, component, "keypress");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(final FacesContext context, final UIComponent component, final String event) throws IOException {

        final ResponseWriter writer = context.getResponseWriter();
        /*
         * Prepare informations for making any Ajax request
         */
       /* HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        AjaxUtils ajaxtools = new AjaxUtils();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY(), "'+value+'");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_TARGET_ID_KEY(), "'+target+'");
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
        String Request = ajaxtools.getRequestJs("get", AJAX_SERVER, AJAX_PARAMETERS);

        writer.startElement("script", component);
        writer.write(
                "document.getElementById('" + component.getClientId(context) + "').addEvent('" + event + "', function(event){" +
                addBeforeRequestScript(context, component) +
                "value = event.target.value;" +
                "target = event.target.name;" +
                "viewstate = document.getElementById('javax.faces.ViewState').value;" +
                Request +
                addAfterRequestScript(context, component) +
                "});");
        writer.endElement("script");*/
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
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

}
