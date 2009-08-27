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
package org.widgetfaces.renderkit.html.autocompletion;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import org.ajax4jsf.ajax.html.HtmlLoadStyle;
import org.widgetfaces.adapter.autocompletion.Adapter;
import org.widgetfaces.component.autocompletion.UIAutocompletion;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;

/**
 * @author kevin Delfour
 */
public class AutocompletionRenderer extends Renderer {

    private static final Logger LOGGER = Logger.getLogger(AutocompletionRenderer.class.getName());
    private static final String MAPFACES_WIDGETS_CSS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.css";
    private static final String MOOTOOLS_JS = "/org/widgetfaces/resources/compressed/mootools.min.js";
    private static final String MAPFACES_WIDGETS_JS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.js";

    private static final String VALUE_KEY =   "value";
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
        final UIAutocompletion comp = (UIAutocompletion) component;
        final ResponseWriter writer      = context.getResponseWriter();
        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.Autocompleter")) {
            extContext.getRequestMap().put("ajaxflag.Autocompleter", Boolean.TRUE);
            writeHeaders(context, component);
        }
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, component.getClientId(context), null);
        writer.startElement(HTML.INPUT_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, comp.getId() + "_input", null);
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, comp.getId() + "_input", null);
        final ValueExpression ve = comp.getValueExpression(VALUE_KEY);
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                comp.setValue(ve.getValue(context.getELContext()));
                comp.setValueExpression("value", ve);
                writer.writeAttribute(VALUE_KEY, ve.getValue(context.getELContext()), null);
            }
        }
        writer.endElement(HTML.INPUT_ELEM);
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
        final UIAutocompletion comp = (UIAutocompletion) component;
        final StringBuilder str = new StringBuilder();
        final String clientId = comp.getClientId(context);
        final String id = comp.getId();
        final String inputId = id + "_input";
        final String tokenId = id + "_token";
        super.encodeEnd(context, component);

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);

       

        str.append("document.addEvent('domready', function(){");
        /* Enable ajax request */
        if (comp.isEnableAjax()) {
            
            final String urlRequest = AjaxUtils.getAjaxServer((HttpServletRequest) context.getExternalContext().getRequest());            
            final StringBuilder ajaxrequest = new StringBuilder();
            ajaxrequest.append("new Autocompleter.Request.HTML($('").
                    append(inputId). append("'),").
                    append("'").append(urlRequest).append("',{").
                    append("postData:{").
                    //append("'").append(AjaxUtils.AUTOCOMPLETION_VALUE).append("': $('").append(inputId).append("').value,").
                    append("'").append(AjaxUtils.AUTOCOMPLETION_MODE).append("': 'request.html',").
                    append("'").append(AjaxUtils.AUTOCOMPLETION_WS_URL).append("': '").append(comp.getWsUrl()).append("',").
                    append("'").append(AjaxUtils.AUTOCOMPLETION_CLIENTID).append("': '" + clientId + "'}});");

//            str.append("$('").append(inputId).append("').addEvent('keydown',function(event){if(event.enter)").
//                    append(ajaxrequest).append("});");
//            str.append("$('").append(inputId).append("').addEvent('blur',function(event){").
//                    append(ajaxrequest).append("});");
            str.append(ajaxrequest);

        } else {
             str.append("var ").append(tokenId).append("=").
                append(Adapter.array2token(comp.getValueExpression("services").getExpressionString(), context)).
                append(";");
             str.append("new Autocompleter.Local('").append(inputId).append("',").
                append(tokenId).append(",{").
                append(buildOptions(component)).append("});");
                
        }
       
        str.append("});");
        writer.write(str.toString());
        writer.endElement(HTML.SCRIPT_ELEM);

        writer.endElement(HTML.DIV_ELEM);

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
        final ExternalContext ext = context.getExternalContext();
        final UIAutocompletion comp = (UIAutocompletion) component;
        final Map parameterMap = ext.getRequestParameterMap();
        final String newValue = (String) parameterMap.get(comp.getId()+"_input");

        final ValueExpression ve = comp.getValueExpression(VALUE_KEY);
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                ve.setValue(context.getELContext(), newValue);
                comp.setValue(ve.getValue(context.getELContext()));
                comp.setValueExpression(VALUE_KEY, ve);
            }
        }
        comp.setSubmittedValue(newValue);
    }


    /**
     * 
     * @param context
     * @param component
     * @return
     * @throws java.io.IOException
     */
    private String buildOptions(final UIComponent component) throws IOException {
        final UIAutocompletion comp = (UIAutocompletion) component;
        final StringBuilder str = new StringBuilder();
        str.append("'selectMode' : 'selection','minLength' : ").append(comp.getMinLength()).
                append(",'markQuery' : ").append(comp.isMarkQuery()).
                append(",'maxChoices' : ").append(comp.getMaxChoices()).
                append(",'delay' : ").append(comp.getDelay()).
                append(",'autoSubmit' : ").append(comp.isAutoSubmit()).
                append(",'overflow' : ").append(comp.isOverflow()).
                append(",'overflowMargin' : ").append(comp.getOverflowMargin()).
                append(",'selectFirst' : ").append(comp.isSelectFirst()).
                append(",'filterCase' : ").append(comp.isFilterCase()).
                append(",'filterSubset' : ").append(comp.isFilterSubset()).
                append(",'multiple' : ").append(comp.isMultiple());

        return str.toString();
    }

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIAutocompletion comp = (UIAutocompletion) component;

        
        if (comp.isLoadMootools()) {
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLS_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

         if (comp.isLoadJs()) {
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MAPFACES_WIDGETS_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

        if (comp.isLoadCss()) {
            final HtmlLoadStyle css = new HtmlLoadStyle();
            css.setSrc(ResourcePhaseListener.getLoadStyleURL(context, MAPFACES_WIDGETS_CSS, null));
            comp.getChildren().add(css);
        }


    }
}
