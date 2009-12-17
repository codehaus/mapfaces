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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.ajax4jsf.component.html.HtmlLoadStyle;
import org.widgetfaces.adapter.autocompletion.Adapter;
import org.widgetfaces.component.autocompletion.UIAutocompletion;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.share.utils.WebContainerUtils;

/**
 * @author Mehdi Sidhoum (Geomatys)
 * @author kevin Delfour
 * @since 0.2
 */
public class AutocompletionRenderer extends Renderer {

    private static final Logger LOGGER = Logger.getLogger(AutocompletionRenderer.class.getName());
    private static final String MAPFACES_WIDGETS_CSS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.css";
    private static final String MOOTOOLS_CORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private static final String MOOTOOLS_MORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";
    private static final String PROTOTYPE_JS = "/org/mapfaces/resources/js/prototype/prototype-1.6.1.js";
    private static final String SCRIPTACULOUS_JS = "/org/mapfaces/resources/js/scriptaculous/scriptaculous.js";
    //builder,effects,dragdrop,controls,slider,sound
    private static final String SCRIPTACULOUS_BUILDER_JS = "/org/mapfaces/resources/js/scriptaculous/builder.js";
    private static final String SCRIPTACULOUS_EFFECTS_JS = "/org/mapfaces/resources/js/scriptaculous/effects.js";
    private static final String SCRIPTACULOUS_DND_JS = "/org/mapfaces/resources/js/scriptaculous/dragdrop.js";
    private static final String SCRIPTACULOUS_CONTROLS_JS = "/org/mapfaces/resources/js/scriptaculous/controls.js";
    private static final String SCRIPTACULOUS_SLIDER_JS = "/org/mapfaces/resources/js/scriptaculous/slider.js";
    private static final String SCRIPTACULOUS_SOUND_JS = "/org/mapfaces/resources/js/scriptaculous/sound.js";
    
    private static final String MAPFACES_WIDGETS_JS = "/org/widgetfaces/resources/compressed/mapfaces-widgets.js";
    private static final String VALUE_KEY = "value";

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
        final UIAutocompletion comp = (UIAutocompletion) component;
        final ResponseWriter writer = context.getResponseWriter();
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

        //@TODO write for all events attributs onclick, on....
        if (comp.getOnkeyup() != null && !comp.getOnkeyup().equals("")) {
            writer.writeAttribute(HTML.onkeyup_ATTRIBUTE, comp.getOnkeyup(), null);
        }

        if (comp.getTitle() != null && !comp.getTitle().isEmpty()) {
            writer.writeAttribute(HTML.title_ATTRIBUTE, comp.getTitle(), "title");
        }


        writer.endElement(HTML.INPUT_ELEM);

        switch (comp.getVersion()) {

            case SCRIPTACULOUS:
                writer.startElement(HTML.DIV_ELEM, component);
                writer.writeAttribute(HTML.id_ATTRIBUTE, comp.getId() + "_list", null);
                writer.writeAttribute(HTML.class_ATTRIBUTE, "autocomplete", null);
                writer.endElement(HTML.DIV_ELEM);
                break;

            default:
                break;
        }

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
        super.encodeEnd(context, component);

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
        String str = null;

        final String actionUrl = WebContainerUtils.getAjaxActionURL(context);
        switch (comp.getVersion()) {
            
            case SCRIPTACULOUS:
                str = buildJsScriptaculous(context, comp, actionUrl);
                break;

            default:
                str = buildJsMootools(context, comp, actionUrl);
                break;
        }
        
        writer.write(str);
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
        final String newValue = (String) parameterMap.get(comp.getId() + "_input");

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
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final List<UIComponent> childrens = component.getChildren();
        for (final UIComponent tmp : childrens) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    /**
     * 
     * @param context
     * @param component
     * @return
     * @throws java.io.IOException
     */
    private String buildOptions(final UIAutocompletion comp) throws IOException {
        final StringBuilder str = new StringBuilder();
        str.append("'selectMode' : 'selection'").
                append(",'minLength' : ").append(comp.getMinLength()).
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

    //see http://wiki.github.com/madrobby/scriptaculous/ajax-autocompleter for more details
    private String buildJsScriptaculous(final FacesContext context, final UIAutocompletion comp, final String actionUrl) throws IOException {
        final StringBuilder str = new StringBuilder();
        final String clientId = comp.getClientId(context);
        final String id = comp.getId();
        final String inputId = id + "_input";
        final String listId = id + "_list";
        str.append("document.observe('dom:loaded',  function(){");

        /* If we use a web thesaurus service*/
        if (comp.getWtsUrl() != null) {
            final StringBuilder constructor = new StringBuilder();
            
            //Creation of the Autocompleter with default options
            constructor.append(" new Ajax.Autocompleter('").append(inputId).append("','").
                    append(listId).append("','").
                    append(actionUrl).append("', ").
                    append("{ paramName: 'value', tokens: ','").
                    append(",parameters:'").
                        append(AjaxUtils.AUTOCOMPLETER_VERSION).append("=").append(comp.getVersion()).append("&").
                        append(AjaxUtils.AUTOCOMPLETION_MODE).append("=").append(AjaxUtils.AUTOCOMPLETION_MODE_REQUEST_HTML).append("&").
                        append(AjaxUtils.AUTOCOMPLETION_CLIENTID).append("=" + clientId + "&").
                        append(AjaxUtils.THESAURUS_WS_URL).append("=").append(comp.getWtsUrl()).append("&").
                        append(AjaxUtils.THESAURUS_WS_REQUEST).append("=").append(AjaxUtils.THESAURUS_WS_REQUEST_GetConceptsMatchingKeyword).
                    append("'}").
            append(");");
            str.append(constructor);

        } else {
            final String tokenJsObj = id + "_token";

            //Creation of the array of words
            //the services value can be a List of String or a String as ['keyword1','keyword2']
            Object servicesValue = (comp.getValueExpression("services") != null) ?
                comp.getValueExpression("services").getExpressionString() : comp.getServices();
            str.append("var ").append(tokenJsObj).append("=").
                    append(Adapter.array2token(servicesValue, context)).append(";");

            //Creation of the Autocompleter with default options
            str.append("new Autocompleter.Local('").
                    append(inputId).append("','").
                    append(listId).append("',").
                    append(tokenJsObj).append(",{").
                    append("choices: 5, ").
                    append("partialSearch: false, ").
                    append("fullSearch: true, ").
                    append("partialChars: 1, ").
                    append("ignoreCase: true ").
            append("});");

        }

        str.append("});");
        return str.toString();
    }

    private String buildJsMootools(final FacesContext context, final UIAutocompletion comp, final String actionUrl) throws IOException {

        final StringBuilder str = new StringBuilder();
        final String clientId = comp.getClientId(context);
        final String id = comp.getId();
        final String inputId = id + "_input";
        final String tokenId = id + "_token";
        str.append("document.addEvent('domready', function(){");
        /* If we use a web thesaurus service*/
        if (comp.getWtsUrl() != null) {
            
            final StringBuilder ajaxrequest = new StringBuilder();
            ajaxrequest.append("new Autocompleter.Request.HTML($('").
                    append(inputId).append("'),").
                    append("'").append(actionUrl).append("',{zIndex:10000,").
                    append("postData:{").
                    append("'").append(AjaxUtils.AUTOCOMPLETER_VERSION).append("':'").append(comp.getVersion()).append("',").
                    append("'").append(AjaxUtils.AUTOCOMPLETION_MODE).append("': '").append(AjaxUtils.AUTOCOMPLETION_MODE_REQUEST_HTML).append("',").
                    append("'").append(AjaxUtils.AUTOCOMPLETION_CLIENTID).append("': '" + clientId + "',").
                    append("'").append(AjaxUtils.THESAURUS_WS_URL).append("': '").append(comp.getWtsUrl()).append("',").
                    append("'").append(AjaxUtils.THESAURUS_WS_REQUEST).append("': '").append(AjaxUtils.THESAURUS_WS_REQUEST_GetConceptsMatchingKeyword).append("'").
                    append("}});");
            str.append(ajaxrequest);

        } else {
            //the service value can be a List of String or a String as ['keyword1','keyword2']
            Object servicesValue = (comp.getValueExpression("services") != null) ? comp.getValueExpression("services").getExpressionString() : comp.getServices();
            str.append("var ").append(tokenId).append("=").
                    append(Adapter.array2token(servicesValue, context)).
                    append(";");
            str.append("new Autocompleter.Local('").append(inputId).append("',").
                    append(tokenId).append(",{").
                    append(buildOptions(comp)).append("});");

        }

        str.append("});");
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

        switch (comp.getVersion()) {

            case SCRIPTACULOUS:

                if (comp.isLoadJs()) {
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, PROTOTYPE_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_BUILDER_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_EFFECTS_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_DND_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_CONTROLS_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_SLIDER_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, SCRIPTACULOUS_SOUND_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                }
                break;

            default:
                
                if (comp.isLoadJs()) {

                    if (comp.isLoadMootools()) {
                        writer.startElement(HTML.SCRIPT_ELEM, comp);
                        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                        writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLS_CORE_JS, null), null);
                        writer.endElement(HTML.SCRIPT_ELEM);
                        writer.startElement(HTML.SCRIPT_ELEM, comp);
                        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                        writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLS_MORE_JS, null), null);
                        writer.endElement(HTML.SCRIPT_ELEM);
                    }
                    writer.startElement(HTML.SCRIPT_ELEM, comp);
                    writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
                    writer.writeAttribute(HTML.SRC_ATTRIBUTE, ResourcePhaseListener.getURL(context, MAPFACES_WIDGETS_JS, null), null);
                    writer.endElement(HTML.SCRIPT_ELEM);
                }
                break;
        }

        if (comp.isLoadCss()) {
            final HtmlLoadStyle css = new HtmlLoadStyle();
            css.setSrc(ResourcePhaseListener.getLoadStyleURL(context, MAPFACES_WIDGETS_CSS, null));
            comp.getChildren().add(css);
        }
    }
}
