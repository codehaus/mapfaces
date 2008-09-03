package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kdelfour
 */
public abstract class AbstractTreeNodeInfoRenderer extends Renderer {

    private static final transient Log log = LogFactory.getLog(AbstractTreeNodeInfoRenderer.class);
    private static String DESC_STYLE_CLASS = "x-tree-node-info";

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component - 
     * @return
     */
    private static UIAbstractTreePanel getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIAbstractTreePanel) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UIAbstractTreePanel) parent;
    }

    /**
     * 
     * @param component
     * @return
     */
    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractTreeNodeInfo treenodeinfo = (UIAbstractTreeNodeInfo) component;
        return treenodeinfo.getId() + "PostBack";
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (debug()) {
            log.info("beforeEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        if (debug()) {
            log.info("encodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        //Start encoding
        UIAbstractTreeNodeInfo treenodeinfo = (UIAbstractTreeNodeInfo) component;
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) treenodeinfo.getParent();
        ResponseWriter writer = context.getResponseWriter();

        UIAbstractTreePanel treetable = getForm(treenodeinfo);
        if (treetable != null) {
            writer.startElement("div", treenodeinfo);
            writer.writeAttribute("class", DESC_STYLE_CLASS, null);
            writer.writeAttribute("id", "info:" + treeline.getRow(), null);
            if (treenodeinfo.getAttributes().get("hide") != null) {
                if (!(Boolean) treenodeinfo.getAttributes().get("hide")) {
                    writer.writeAttribute("style", "display:block;", null);
                }
            } else {
                writer.writeAttribute("style", "display:none;", null);
            }
        }

        if (debug()) {
            log.info("afterEncodeBegin : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (debug()) {
            log.info("encodeChildren : " + AbstractTreeNodeInfoRenderer.class.getName());
        }

        ResponseWriter writer = context.getResponseWriter();

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");
            for (UIComponent tmp : children) {
                writer.startElement("div", component);
                writer.writeAttribute("class", DESC_STYLE_CLASS, null);
                Utils.encodeRecursive(context, tmp);
                writer.endElement("div");
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (debug()) {
            log.info("beforeEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug()) {
            log.info("encodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }

        writer.endElement("div");

        if (debug()) {
            log.info("afterEncodeEnd : " + AbstractTreeNodeInfoRenderer.class.getName());
        }
        afterEncodeEnd(context, component);
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract boolean debug();
}
