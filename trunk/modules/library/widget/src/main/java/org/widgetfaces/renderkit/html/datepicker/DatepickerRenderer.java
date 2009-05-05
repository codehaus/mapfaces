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

package org.widgetfaces.renderkit.html.datepicker;

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
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;
import org.widgetfaces.component.datepicker.UIDatepicker;

/**
 * @author kevin Delfour
 */
public class DatepickerRenderer extends Renderer implements AjaxRendererInterface {

    private static final Logger LOGGER = Logger.getLogger(DatepickerRenderer.class.getName());

//    private static final String LOAD_Datepicker = "/org/widgetfaces/widget/datepicker/js/datepicker.js";
    private static final String LOAD_Mootools = "/org/widgetfaces/resources/js/loading.js";
    private static final String LOAD_Datepicker_min = "/org/widgetfaces/resources/compressed/datepicker.min.js";
    private static final String LOAD_Datepicker_css = "/org/widgetfaces/widget/datepicker/css/datepicker.css";
    private static final String datepickerClass = "﻿w8em format-y-m-d highlight-days-67 divider-dash";

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
        final UIDatepicker comp = (UIDatepicker) component;

        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.DatePickerjs")) {
            extContext.getRequestMap().put("ajaxflag.DatePickerjs", Boolean.TRUE);
            writeHeaders(context, component);
        }

        HtmlInputText input = new HtmlInputText();
        input.setId(comp.getId() + "_inputdate");
        input.setStyle(comp.getStyle());
        input.setStyleClass(datepickerClass + " " + comp.getStyleClass());
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

        boolean inputPresence = false;
        for (UIComponent uIComponent : comp.getChildren()) {
            if (uIComponent instanceof HtmlInputText) {
                inputPresence = true;
                input = (HtmlInputText) uIComponent;
            }
        }

        ValueExpression ve = comp.getValueExpression("value");
        if (ve != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                input.setValue(ve.getValue(context.getELContext()));
                input.setValueExpression("value", ve);
            }
        }

        if (!inputPresence) {
            comp.getChildren().add(input);
        }
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
            Utils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIDatepicker comp = (UIDatepicker) component;
        final StringBuilder str = new StringBuilder();
        final UIForm formContainer = getForm(component);

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", null);
        str.append("document.addEvent('domready', function(){").append("DatePickerReloading();");

        /* Enable ajax request */
        if (comp.isEnableAjax()) {
            final StringBuilder ajaxrequest = new StringBuilder();

            final String urlRequest = AjaxUtils.getAjaxServer((HttpServletRequest) context.getExternalContext().getRequest());
            ajaxrequest.append("new Request.HTML({").append("url:'").append(urlRequest).append("',").append("data:{").append("'javax.faces.ViewState':").append("$('javax.faces.ViewState').value").append(",").append("'" + AjaxUtils.AJAX_REQUEST_PARAM_KEY + "':").append("'true'").append(",").append("'" + AjaxUtils.AJAX_COMPONENT_VALUE_KEY + "':").append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input').value").append(",").append("'" + AjaxUtils.AJAX_CONTAINER_ID_KEY + "':").append("'" + comp.getId() + "'").append(",").append("'" + AjaxUtils.AJAX_COMPONENT_ID_KEY + "':").append("'" + comp.getId() + "'").append("}}).send();");
            str.append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input").append("').addEvent('keydown',function(event){if(event.enter)").append(ajaxrequest).append("});").append("$('").append(formContainer.getId()).append(":").append(comp.getId()).append("_input").append("').addEvent('blur',function(event){").append(ajaxrequest).append("});");
        }
        str.append("});");

        writer.write(str.toString());
        writer.endElement("script");
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
        String keyParameterInput = formContainer.getId() + ":" + comp.getId() + "_inputdate";
        String newValue = (String) parameterMap.get(keyParameterInput);

        HtmlInputText inputchild = null;
        if (comp.getChildren().size() != 0) {
            inputchild = (HtmlInputText) comp.getChildren().get(0);
        }

        ValueExpression ve = comp.getValueExpression("value");
        if (ve != null && inputchild != null) {
            if (ve.getValue(context.getELContext()) instanceof String) {
                inputchild.setValue(ve.getValue(context.getELContext()));
                inputchild.setValueExpression("value", ve);
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
     * Test if context or the UICompoent exist and are not null
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be tested
     */
    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
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


//        writer.startElement("script", comp);
//        writer.writeAttribute("type", "text/javascript", null);
//        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, LOAD_Datepicker, null), null);
//        writer.endElement("script");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, LOAD_Mootools, null), null);
        writer.endElement("script");

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, LOAD_Datepicker_min, null), null);
        writer.endElement("script");

        writer.startElement("link", comp);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, LOAD_Datepicker_css, null), null);
        writer.endElement("link");

    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        final StringBuilder sb = new StringBuilder();
        final ExternalContext ext = context.getExternalContext();
        final UIDatepicker comp = (UIDatepicker) component;
        final Map parameterMap = ext.getRequestParameterMap();
        final String autocompleteValue = request.getParameter(AjaxUtils.AJAX_COMPONENT_VALUE_KEY);

        final UIForm formContainer = getForm(component);
        String keyParameterInput = formContainer.getId() + ":" + comp.getId() + "_inputdate";
        String newValue = (String) parameterMap.get(keyParameterInput);

        ValueExpression ve = comp.getValueExpression("value");
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
