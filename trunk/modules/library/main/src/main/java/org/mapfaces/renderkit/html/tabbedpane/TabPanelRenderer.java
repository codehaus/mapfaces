package org.mapfaces.renderkit.html.tabbedpane;

import java.io.IOException;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.mapfaces.component.tabbedpane.UITabItem;
import org.mapfaces.component.tabbedpane.UITabPanel;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kevindelfour
 */
public class TabPanelRenderer extends Renderer {

    private static final transient Log log = LogFactory.getLog(TabPanelRenderer.class);
    private final String TABCSS_CSS = "/org/mapfaces/resources/tabbedpane/css/domtab.css";
    private final String TABJS = "/org/mapfaces/resources/tabbedpane/js/domtab.js";

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
        UITabPanel tabpanel = (UITabPanel) component;
        return tabpanel.getId() + "PostBack";
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
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //Start encoding
        log.info("encodeBegin : " + TabPanelRenderer.class.getName());

        UITabPanel tabpanel = (UITabPanel) component;
        ResponseWriter writer = context.getResponseWriter();

        String dimensionsW = "width:auto";
        String dimensionsH = "height:auto";

        if (tabpanel.getAttributes().get("width") != null) {
            dimensionsW = "width :" + tabpanel.getAttributes().get("width") + "px";
        }
        if (tabpanel.getAttributes().get("height") != null) {
            dimensionsH = "height : " + tabpanel.getAttributes().get("height") + "px";
        }
        writeHeaders(context, component);

        writer.startElement("div", tabpanel);
        writer.writeAttribute("id", "tabs:" + tabpanel.getClientId(context), null);
        writer.writeAttribute("class", "tabs", null);
        writer.writeAttribute("style", dimensionsW + ";" + dimensionsH + ";", null);

        boolean active = true;
        List<UIComponent> children = tabpanel.getChildren();
        writer.startElement("ul", tabpanel);
        writer.writeAttribute("id", tabpanel.getTitle(), null);
        writer.writeAttribute("class", "tabs_title", null);
        for (UIComponent child : children) {
            writer.startElement("li", tabpanel);
            writer.writeAttribute("id", "item:"+((UITabItem) child).getTitle(), null);
            writer.writeAttribute("onclick", "display('"+"tabs:" +tabpanel.getClientId(context)+"','"+((UITabItem) child).getTitle()+"');", null);
            if (active) {
                writer.writeAttribute("class", "active", null);
                ((UITabItem) child).setActive(true);
                active = false;
            } else {
                ((UITabItem) child).setActive(false);
            }
            writer.write(((UITabItem) child).getTitle());
            writer.endElement("li");
        }
        writer.endElement("ul");
    }

    /**
     * Encode all children of TreeTable component
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        log.info("encodeChildren : " + TabPanelRenderer.class.getName());
        UITabPanel tabpanel = (UITabPanel) component;

        if (tabpanel.getChildCount() != 0) {
            List<UIComponent> children = tabpanel.getChildren();
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
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        log.info("encodeEnd : " + TabPanelRenderer.class.getName());
        UITabPanel tabpanel = (UITabPanel) component;
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
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

    /* ======================= OTHERS METHODS ==================================*/
    /**
     * Write headers css and js with the resource 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    private void writeHeaders(FacesContext context, UIComponent component) throws IOException {
        UITabPanel tabpanel = (UITabPanel) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("link", tabpanel);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, TABCSS_CSS, null), null);
        writer.endElement("link");

        writer.startElement("script", tabpanel);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, TABJS, null), null);
        writer.endElement("script");
    }
}
