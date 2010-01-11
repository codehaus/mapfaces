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
package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.Context;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.share.utils.WebContainerUtils;
import org.mapfaces.util.FacesMapUtils;
import org.widgetfaces.component.autocompletion.UIAutocompletion;
import org.widgetfaces.renderkit.html.autocompletion.AutocompletionRenderer;

public class AutocompletionAndZoomRenderer extends AutocompletionRenderer {
     private static final String ZOOM_TO_IMG = "/org/mapfaces/resources/img/zoomTo.png";
    /**
     * <p> Render the beginning specified Component to the output stream or writer associated
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
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final ResponseWriter writer = context.getResponseWriter();
        
        //Add the image who permits to zoom if the selected word has an extent
        writer.startElement(HTML.IMG_ELEM, component);
        writer.writeAttribute(HTML.width_ATTRIBUTE, 24, null);
        writer.writeAttribute(HTML.height_ATTRIBUTE, 24, null);
        writer.writeAttribute(HTML.alt_ATTRIBUTE, "zoom to", null);
        writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context,ZOOM_TO_IMG, null), null);
        writer.writeAttribute(HTML.onclick_ATTRIBUTE, createOnclick(context, component), null);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "cursor:pointer;", null);
        writer.endElement(HTML.IMG_ELEM);

    }

    
    private String createOnclick(final FacesContext context, final UIComponent component) {
        final UIAutocompletion comp = (UIAutocompletion) component;
        final StringBuilder sb = new StringBuilder();
        final UIContext contextComp = FacesMapUtils.getParentUIContext(context, component);
        String currentEpsg = ((Context) contextComp.getModel()).getSrs();
        if (currentEpsg == null)
            currentEpsg = "EPSG:4326";
        String str = null;
        final String actionUrl = WebContainerUtils.getAjaxActionURL(context);
        switch (comp.getVersion()) {

            case SCRIPTACULOUS:
                str = createOnclickScriptaculous(context, comp, actionUrl);
                break;

            default:
                str = createOnclickMootools(context, comp, actionUrl);
                break;
        }
        return str;
    }


    private String createOnclickScriptaculous(final FacesContext context, final UIAutocompletion comp, final String actionUrl) {
        final StringBuilder sb = new StringBuilder();
        sb.append(buildCommonParams(context, comp));
        sb.append("var ajaxRequest = new Ajax.Request('").append(actionUrl).append("',{").
                append("method: 'post',").
                append("encoding: 'utf-8',").
                append("onSuccess: window.loadGeoJSON, ").
                append("parameters:params").
                append("});");
        return sb.toString();
    }

    private String createOnclickMootools(final FacesContext context, final UIAutocompletion comp, final String actionUrl) {
        final StringBuilder sb = new StringBuilder();
        sb.append(buildCommonParams(context, comp));
        sb.append("var ajaxRequest = new Request({").
                append("url: '").append(actionUrl).append("',").
                append("method: 'post',").
                append("encoding: 'utf-8',").
                append("onComplete: window.loadGeoJSON, ").
                append("data:params").
                append("}).send();");
        return sb.toString();
    }

    private String buildCommonParams(FacesContext context, UIAutocompletion comp) {
        final StringBuilder sb = new StringBuilder();
        final UIContext contextComp = FacesMapUtils.getParentUIContext(context, comp);
        String currentEpsg = ((Context) contextComp.getModel()).getSrs();
        if (currentEpsg == null)
            currentEpsg = "EPSG:4326";
        sb.append("var params = { ").
                append("targetMapId: '" + FacesMapUtils.getJsVariableFromClientId(FacesMapUtils.getChildUIMapPane(context, contextComp).getClientId(context)) + "', ").
                append("targetLayerId: '" + FacesMapUtils.getJsVariableFromClientId(comp.getClientId(context)) + "', ").
                append("'").append(AjaxUtils.THESAURUS_OUTPUT_EPSG).append("': '").append(currentEpsg).append("',").
                append("'").append(AjaxUtils.THESAURUS_WS_URL).append("': '").append(comp.getWtsUrl()).append("',").
                append("'").append(AjaxUtils.THESAURUS_WS_REQUEST).append("': '").append(AjaxUtils.THESAURUS_WS_REQUEST_GetGeometricConcept).append("',").
                append("'").append(AjaxUtils.AUTOCOMPLETION_CLIENTID).append("': '").append(comp.getClientId(context)).append("',").
                append("'").append(AjaxUtils.AUTOCOMPLETION_MODE).append("': '").append(AjaxUtils.AUTOCOMPLETION_MODE_REQUEST_HTML).append("',").
                append("'value': ").append("document.getElementById('").append(comp.getId()).append("_input').value").
                append("};");
        return sb.toString();
    }
}
