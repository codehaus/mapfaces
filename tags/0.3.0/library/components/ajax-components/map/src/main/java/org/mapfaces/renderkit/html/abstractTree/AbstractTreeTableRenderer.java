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
package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import javax.swing.tree.DefaultTreeModel;

import org.ajax4jsf.ajax.html.HtmlLoadStyle;
import org.mapfaces.component.abstractTree.UITreeBase;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.component.abstractTree.UITreeTableBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.tree.TreeStyle;

/**
 * @author kevin Delfour (IRD)
 */
public abstract class AbstractTreeTableRenderer extends Renderer implements CustomizeTreeComponentRenderer {

    /* Script Js and Css Style link */
    private static final String MOOTOOLS_CORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private static final String MOOTOOLS_MORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";
    private static final String Loading_Tree_min         = "/org/mapfaces/resources/compressed/tree.min.js";
    
    /* Local fields */
    private boolean debug;
    private Date renderStart,  renderEnd;
    private long encodeBeginTime,  encodeChildrenTime,  encodeEndTime;


    //private final String MOOTOOLS_JS = "/org/mapfaces/resources/treetable/js/mootools.1.2.js";
//    private final String TREEPANEL_JS = "/org/mapfaces/resources/treetable/js/treepanel.1.0.js";
//    private final String TREETABLE_JS = "/org/mapfaces/resources/treetable/js/treetable.1.0.js";
//    private final String TREE_MINIFY_JS = "/org/mapfaces/resources/tree/js/zip.js";

    /**
     * <p> Render the beginning specified TreeTable Component to the output stream or writer associated
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
        final UITreeTableBase treetable       = (UITreeTableBase) component;
        final ResponseWriter writer           = context.getResponseWriter();
        String width, height;
        Date phaseStart, phaseEnd, getValueStart, getValueEnd;

        /* Initialisation */
        TreeStyle.initRowStyle();
        phaseStart = new Date();
        renderStart = new Date();

        width = "width:100%";
        height = "height:100%";

       
        /* Is the component haven't been rendered yet ? */
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        /* GetAttributes from the UIComponent  */
        if (treetable.isDebug()) {
            debug = treetable.isDebug();
        }

        if (component.getAttributes().get("width") != null) {
            width = "width :" + treetable.getAttributes().get("width") + "px";
        }
        if (component.getAttributes().get("height") != null) {
            height = "height : " + component.getAttributes().get("height") + "px";
        }

        /* Before encodeBegin, any method declared in a component extends this class can be launch here*/
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " beforeEncodeBegin : " + AbstractTreeTableRenderer.class.getName());

        beforeEncodeBegin(context, component);

        /* Start encoding */
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeBegin : " + AbstractTreeTableRenderer.class.getName());


        /* Get DefaultTreeModel value and  convert toTreeTable Model for jsf treetable component */
        DefaultTreeModel tree = treetable.getTree();

        /*Firts, we get the var name of the treetable */
        final String var = (String) component.getAttributes().get("var");
        if (var != null) {
            /* If the var name isn't null, we store it in the request map by using setVarName */
            treetable.setVarName(var);
        }

        getValueStart = new Date();

        /* Getting the defaultTreeModel pointed by the value */
            final Object value = component.getAttributes().get("value");
            if (value != null) {
                if (value instanceof String) {
                    ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                    tree = (DefaultTreeModel) ve.getValue(context.getELContext());
                } else {
                    tree = (DefaultTreeModel) value;
                }
                treetable.setTree(TreeModelsUtils.transformTree(tree));
                treetable.setNodeCount(treetable.getTree());
            } else {
                 Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.SEVERE, "TreeTable component have a model null");
                return;
            }
        getValueEnd = new Date();
        final long timeToGetValue = getValueEnd.getTime() - getValueStart.getTime();
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " Time to Get Value : " + timeToGetValue + " ms");


        // treetable.setNodeCount(treetable.getTree());

        /* Writing Javascript header and Css styles, the style by default : treetable.css */
        writeHeaders(context, component);

        writer.startElement("div", component);
        writer.writeAttribute("id", "treetable:" + component.getClientId(context), null);
        String styleUser = "";
        if (treetable.getStyle() != null) {
            styleUser = treetable.getStyle();
        }
        writer.writeAttribute("style", width + ";" + height + ";" + styleUser, null);

        if (treetable.getStyleClass() != null) {
            writer.writeAttribute("class", TreeStyle.default_mainStyle + treetable.getStyleClass(), null);
        } else {
            writer.writeAttribute("class", TreeStyle.default_mainStyle, null);
        }

        /* After encodeBegin, any method declared in a component extends this class can be launch here*/
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " afterEncodeBegin : " + AbstractTreeTableRenderer.class.getName());

        afterEncodeBegin(context, component);

        phaseEnd = new Date();
        encodeBeginTime = phaseStart.getTime() - phaseEnd.getTime();
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
        Date phaseStart,phaseEnd ;

        /* Initialisation */
        phaseStart = new Date();

        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeChildren : " + AbstractTreeTableRenderer.class.getName());

        for (final UIComponent tmp : component.getChildren()) {
            FacesMapUtils.encodeRecursive(context, tmp);
        }

        phaseEnd = new Date();
        encodeChildrenTime = phaseStart.getTime() - phaseEnd.getTime();
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
        final ResponseWriter writer      = context.getResponseWriter();
        final AjaxUtils ajaxtools        = new AjaxUtils();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String AJAX_SERVER         = ajaxtools.getAjaxServer(request);
        Date phaseStart,phaseEnd ;

        /* Initialisation */
        phaseStart = new Date();

        /* Before encodeEnd, any method declared in a component extends this class can be launch here*/
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " beforeEncodeEnd : " + AbstractTreeTableRenderer.class.getName());

        beforeEncodeEnd(context, component);

        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeEnd : " + AbstractTreeTableRenderer.class.getName());

        writer.endElement("div");
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("value", AJAX_SERVER, null);
        writer.writeAttribute("id", "ajax.server.request.URL", null);
        writer.writeAttribute("name", "ajax.server.request.URL", null);
        writer.endElement("input");

        /* After encodeEnd, any method declared in a component extends this class can be launch here*/
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " afterEncodeEnd : " + AbstractTreeTableRenderer.class.getName());

        afterEncodeEnd(context, component);

        phaseEnd = new Date();
        if (debug) {
            renderEnd = new Date();
            long timeEncode = renderEnd.getTime() - renderStart.getTime();
            encodeEndTime = phaseStart.getTime() - phaseEnd.getTime();
            Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeBegin have been rendered in " + encodeBeginTime + " mlls");
            Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeChildren have been rendered in " + encodeChildrenTime + " mlls");
            Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encodeEnd have been rendered in " + encodeEndTime + " mlls");
            Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " encode TreeTable have been rendered in " + timeEncode + " mlls");
        }
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
        context.getApplication().getStateManager().saveView(context);
        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " decode : " + AbstractTreeTableRenderer.class.getName());
        return;
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
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UITreeBase comp = (UITreeBase) component;

        if (debug) Logger.getLogger(AbstractTreeTableRenderer.class.getName()).log(Level.INFO, " decode : " + AbstractTreeTableRenderer.class.getName());

        //@TODO Maybe thereis a better way to not load css
        UIContext temp = FacesMapUtils.getParentUIContext(context, comp);
        if ( (temp == null) || (temp != null && !temp.isMinifyJS())) {
            HtmlLoadStyle css = new HtmlLoadStyle();
            css.setSrc(ResourcePhaseListener.getLoadStyleURL(context, TreeStyle.default_cssFilesUrl, null));
            comp.getChildren().add(css);
        }

        //If TreeTable is not a UIContext children
        //loading mootools only if it is needed
        if (temp == null && comp.isMootools()) {
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_CORE_JS, null), null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_MORE_JS, null), null);
            writer.endElement("script");
        }
        
        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, Loading_Tree_min, null), null);
        writer.endElement("script");

    }
}
