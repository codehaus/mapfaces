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

package org.widgetfaces.renderkit.html.temporal;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajax4jsf.ajax.html.HtmlLoadStyle;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.widgetfaces.component.temporal.UIDatepicker;

/**
 * @author Mehdi Sidhoum (Geomatys)
 * @author kevin Delfour
 * @since 0.2
 */
public class DatepickerRenderer extends Renderer implements AjaxRendererInterface {

    private static final Logger LOGGER = Logger.getLogger(DatepickerRenderer.class.getName());

    private static final String MAPFACES_CSS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.css";
    private static final String MOOTOOLS_CORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private static final String MOOTOOLS_MORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";
    private static final String MAPFACES_JS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.js";

    private static final String DATEPICKER_STYLECLASS = "﻿w8em format-y-m-d highlight-days-67 divider-dash";
    private static final String INPUTDATE_SUFFIX =   "_inputdate";
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
        // suppress rendering if "rendered" property on the component is false.
        if (!component.isRendered()) {
            return;
        }
        FacesUtils.assertValid(context, component);
        super.encodeBegin(context, component);
        
        final UIDatepicker comp = (UIDatepicker) component;
        

        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.DatePickerjs")) {
            extContext.getRequestMap().put("ajaxflag.DatePickerjs", Boolean.TRUE);
            writeHeaders(context, component);
        }

        HtmlInputText input = new HtmlInputText();
        input.setId(comp.getId() + INPUTDATE_SUFFIX);
        input.setStyle(comp.getStyle());
        input.setStyleClass(DATEPICKER_STYLECLASS + " " + comp.getStyleClass());
        input.setOnblur(comp.getOnblur());
        input.setOnchange(comp.getOnchange());
        input.setOnclick(comp.getOnclick());
        input.setOndblclick(comp.getOndblclick());
        input.setOnfocus(comp.getOnfocus());
        input.setOnkeydown(comp.getOnkeydown());
        input.setOnkeypress(comp.getOnkeypress());
        input.setOnkeyup(comp.getOnkeyup());
        input.setOnmousedown(comp.getOnmousedown());
        input.setOnmousemove(comp.getOnmousemove());
        input.setOnmouseout(comp.getOnmouseout());
        input.setOnmouseover(comp.getOnmouseover());
        input.setOnmouseup(comp.getOnmouseup());
        input.setOnselect(comp.getOnselect());
        input.setTitle(comp.getTitle());

        boolean inputPresence = false;
        for (UIComponent uIComponent : comp.getChildren()) {
            if (uIComponent instanceof HtmlInputText) {
                inputPresence = true;
                input = (HtmlInputText) uIComponent;
                break;
            }
        }

        final ValueExpression ve = comp.getValueExpression(VALUE_KEY);
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                input.setValue(ve.getValue(context.getELContext()));
                input.setValueExpression(VALUE_KEY, ve);
            }
        }

        if (!inputPresence) {
            comp.getChildren().add(input);
        }
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
     * <p>Render the child components of this UIComponent, following the rules described for encodeBegin()
     * to acquire the appropriate value to be rendered.</p>
     * <p>This method will only be called if the rendersChildren property of this component is true.</p>
     * @param context FacesContext for the response we are creating
     * @param component UIComponent whose children are to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        
        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIDatepicker comp = (UIDatepicker) component;
        final StringBuilder str = new StringBuilder();
        final UIForm formContainer = getForm(component);

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
        str.append("document.addEvent('domready', function(){").append("DatePickerReloading();");

        /* Enable ajax request */
        if (comp.isEnableAjax()) {
            final StringBuilder ajaxrequest = new StringBuilder();

            final String urlRequest = AjaxUtils.getAjaxServer((HttpServletRequest) context.getExternalContext().getRequest());
            ajaxrequest.append("new Request.HTML({").
                    append("url:'").append(urlRequest).append("',").
                    append("data:{").append("'javax.faces.ViewState':").
                        append("$('javax.faces.ViewState').value").append(",").
                        append("'" + AjaxUtils.AJAX_REQUEST_PARAM_KEY + "':").append("'true'"). append(",").
                        append("'" + AjaxUtils.AJAX_COMPONENT_VALUE_KEY + "':").
                        append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input').value").append(",").
                        append("'" + AjaxUtils.AJAX_CONTAINER_ID_KEY + "':").append("'" + comp.getId() + "'").append(",").
                        append("'" + AjaxUtils.AJAX_COMPONENT_ID_KEY + "':").append("'" + comp.getId() + "'").
                        append("}}).send();");
            str.append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input").append("').addEvent('keydown',function(event){if(event.enter)").append(ajaxrequest).append("});").append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input").append("').addEvent('blur',function(event){").append(ajaxrequest).append("});");
        }
        str.append("});");

        writer.write(str.toString());
        writer.endElement(HTML.SCRIPT_ELEM);
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
        final UIDatepicker comp = (UIDatepicker) component;
        final Map parameterMap = ext.getRequestParameterMap();

        final UIForm formContainer = getForm(component);
        final String keyParameterInput = formContainer.getId() + ":" + comp.getId() + INPUTDATE_SUFFIX;
       final  String newValue = (String) parameterMap.get(keyParameterInput);

        HtmlInputText inputchild = null;
        if (comp.getChildren().size() != 0) {
            for (UIComponent uIComponent : comp.getChildren()) {
                if (uIComponent instanceof HtmlInputText) {
                    inputchild = (HtmlInputText) uIComponent;
                    break;
                }
            }
        }

        final ValueExpression ve = comp.getValueExpression(VALUE_KEY);
        if (ve != null && inputchild != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                inputchild.setValue(ve.getValue(context.getELContext()));
                inputchild.setValueExpression(VALUE_KEY, ve);
                ve.setValue(context.getELContext(), newValue);
            }
        }

    }


    /* Others methods */
    /**
     * <p>Get container form of the UIComponent</p>
     * @param component UIComponent to be rendered
     * @return UIForm the form container of the component if exist else return null
     */
    private UIForm getForm(UIComponent component) {

        UIComponent parent = component.getParent();
        while (parent != null && !(parent instanceof UIForm)) {
            parent = parent.getParent();
        }

        if (parent == null) {
            throw new IllegalStateException("Not nested inside a form!");
        }

        return (UIForm) parent;
    }    

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIDatepicker comp = (UIDatepicker) component;


//        writer.startElement(HTML.SCRIPT_ELEM, comp);
//        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
//        writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, LOAD_Datepicker, null), null);
//        writer.endElement(HTML.SCRIPT_ELEM);

        if (comp.isLoadMootools()) {
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLS_CORE_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLS_MORE_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

        if (comp.isLoadJs()) {
            writer.startElement(HTML.SCRIPT_ELEM, comp);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MAPFACES_JS, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

        if (comp.isLoadCss()) {
            final HtmlLoadStyle css = new HtmlLoadStyle();
            css.setSrc(ResourcePhaseListener.getLoadStyleURL(context, MAPFACES_CSS, null));
            comp.getChildren().add(css);
        }

    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        final StringBuilder sb = new StringBuilder();
        final ExternalContext ext = context.getExternalContext();
        final UIDatepicker comp = (UIDatepicker) component;
        final Map parameterMap = ext.getRequestParameterMap();
        
        final UIForm formContainer = getForm(component);
        final String keyParameterInput = formContainer.getId() + ":" + comp.getId() + INPUTDATE_SUFFIX;
        final String newValue = (String) parameterMap.get(keyParameterInput);

        final ValueExpression ve = comp.getValueExpression(VALUE_KEY);
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                ve.setValue(context.getELContext(), newValue);
            }
        }

        response.setContentType("text/xml;charset=UTF-8");
        // need to set no cache or IE will not make future requests when same URL used.
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setDateHeader("Expires", 1);
        sb.append("");
        sb.append("<response>");
        sb.append("OK");
        sb.append("</response>");
        try {
            response.getWriter().write(sb.toString());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
