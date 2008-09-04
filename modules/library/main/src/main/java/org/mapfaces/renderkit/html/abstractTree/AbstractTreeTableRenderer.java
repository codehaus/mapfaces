package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.component.abstractTree.UIAbstractTreeTable;
import org.mapfaces.util.treetable.TreeTableUtils;

/**
 * 
 * @author kevindelfour
 */
public abstract class AbstractTreeTableRenderer extends Renderer {

    private static final transient Log log = LogFactory.getLog(AbstractTreeTableRenderer.class);
    private boolean debug = false;
    private final String TREETABLE_CSS = "/org/mapfaces/resources/treetable/css/treetable.css";
    private final String TREETABLE_JS = "/org/mapfaces/resources/treetable/js/treepanel.1.0.js";
    private final String MOOTOOLS_JS = "/org/mapfaces/resources/js/mootools.1.2.js";

    private UIForm getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIForm) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a form!");
        }
        return (UIForm) parent;
    }

    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractTreeTable tree = (UIAbstractTreeTable) component;
        return tree.getId() + "PostBack";
    }

    /**
     *   By default, getRendersChildren returns true, so encodeChildren() will be invoked
     * @return True
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Firstly, get the tree value from the bean
     * Then translate into a TreeTableModel
     * and write Css and Js headers before all
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        //Test before rendering if the component is present in an UIForm
        if (!(getForm(component) instanceof UIForm)) {
            return;
        }
        //Then verify if the component haven't been rendered yet
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }
        
        if (debug) {
            log.info("beforeEncodeBegin : " + AbstractTreeTableRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        UIAbstractTreeTable treetable = (UIAbstractTreeTable) component;
        ResponseWriter writer = context.getResponseWriter();
        TreeTableUtils tabletools = new TreeTableUtils();

        if (debug) {
            log.info("encodeBegin : " + AbstractTreeTableRenderer.class.getName());
        }

        /*
         * Get DefaultTreeModel value
         * and  convert toTreeTable Model 
         * for jsf treetable component
         */
        DefaultTreeModel tree = treetable.getTree();

        String var = (String) component.getAttributes().get("var");
        if (var != null) {
            treetable.setVarName(var);
        }

        Object value = component.getAttributes().get("value");

        if (treetable.getTree() == null) {
            if (value != null) {
                if (value.getClass().toString().contains("java.lang.String")) {
                    ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                    tree = (DefaultTreeModel) ve.getValue(context.getELContext());
                } else {
                    tree = (DefaultTreeModel) value;
                }
                treetable.setTree(tabletools.transformTree(tree));
                treetable.setNodeCount(treetable.getTree());
            } else {
                TreeNodeModel node = new TreeNodeModel("root", 0, 0, 0);
                treetable.setTree(new TreeTableModel(node));
            }
        }
        treetable.setNodeCount(treetable.getTree());

        /*
         * Writing Javascript header and Css styles
         * Style by default : treetable.css
         */
        writeHeaders(context, component);

        String width = "width:auto";
        String height = "height:auto";

        if (component.getAttributes().get("width") != null) {
            width = "width :" + treetable.getAttributes().get("width") + "px";
        }
        if (component.getAttributes().get("height") != null) {
            height = "height : " + component.getAttributes().get("height") + "px";
        }

        writer.startElement("div", component);
        writer.writeAttribute("id", "treetable:" + component.getClientId(context), null);
        writer.writeAttribute("style", width + ";" + height + ";", null);

        if (debug) {
            log.info("afterEncodeBegin : " + AbstractTreeTableRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    /**
     * Encode all children of TreeTable component
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        if (debug) {
            log.info("encodeChildren : " + AbstractTreeTableRenderer.class.getName());
        }

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                Utils.encodeRecursive(context, tmp);
            }
        }
    }

    /**
     * Close tree generated div
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractTreeTableRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug) {
            log.info("encodeEnd : " + AbstractTreeTableRenderer.class.getName());
        }
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        writer.endElement("div");

        if (debug) {
            log.info("afterEncodeEnd : " + AbstractTreeTableRenderer.class.getName());
        }
        afterEncodeEnd(context, component);
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if (debug) {
            log.info("decode : " + AbstractTreeTableRenderer.class.getName());
        }
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /* ======================= OTHERS METHODS ==================================*/
    /**
     * Write headers css and js with the resource 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    private void writeHeaders(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (debug) {
            log.info("decode : " + AbstractTreeTableRenderer.class.getName());
        }

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, TREETABLE_CSS, null), null);
        writer.endElement("link");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, TREETABLE_JS, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_JS, null), null);
        writer.endElement("script");

    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;
}
