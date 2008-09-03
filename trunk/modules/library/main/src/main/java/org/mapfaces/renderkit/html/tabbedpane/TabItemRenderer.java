package org.mapfaces.renderkit.html.tabbedpane;

import java.io.IOException;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.mapfaces.component.tabbedpane.UITabItem;
import org.mapfaces.component.tabbedpane.UITabPanel;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kevindelfour
 */
public class TabItemRenderer extends Renderer {

    private static final transient Log log = LogFactory.getLog(TabItemRenderer.class);

    private UITabPanel getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UITabPanel) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tab panel!");
        }
        return (UITabPanel) parent;
    }

    private String getPostbackFunctionName(UIComponent component) {
        UITabItem tabitem = (UITabItem) component;
        return tabitem.getId() + "PostBack";
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
        if (!(getForm(component) instanceof UITabPanel)) {
            return;
        }
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //Start encoding
        log.info("encodeBegin : " + TabItemRenderer.class.getName());

        UITabItem tabitem = (UITabItem) component;
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", tabitem);
        writer.writeAttribute("id", tabitem.getTitle(), null);
        if (tabitem.isActive()) {
            writer.writeAttribute("class", "tabs_panel active", null);
        } else {
            writer.writeAttribute("class", "tabs_panel", null);
        }
        writer.startElement("p", tabitem);
    }

    /**
     * Encode all children of TreeTable component
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        log.info("encodeChildren : " + TabItemRenderer.class.getName());
        UITabItem tabitem = (UITabItem) component;

        if (tabitem.getChildCount() != 0) {
            List<UIComponent> children = tabitem.getChildren();
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
        log.info("encodeEnd : " + TabItemRenderer.class.getName());
        ResponseWriter writer = context.getResponseWriter();

        writer.endElement("p");
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
}
