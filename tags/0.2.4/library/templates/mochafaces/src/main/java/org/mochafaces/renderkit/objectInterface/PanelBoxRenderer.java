/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.renderkit.objectInterface;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.share.utils.FacesUtils;
import org.mochafaces.component.objectInterface.UIPanelBox;
import org.mochafaces.share.style.PanelBoxStyleManager;

/**
 *
 * @author kevindelfour
 */
public class PanelBoxRenderer extends Renderer {

    private final static String BUT_PREFIXID = "panelButton_";
    private final static String CONTENT_PREFIXID = "panelContent_";
    private final static String PANELBOX_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOX_STYLE);
    private final static String PANELBOXHEADER_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOXHEADER_STYLE);
    private final static String PANELBOXHEADERCONTENT_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOXHEADERCONTENT_STYLE);
    private final static String PANELBOXTOOLBOX_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOXTOOLBOX_STYLE);
    private final static String PANELBOX_BUTTON_SIZE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOX_BUTTON_SIZE);
    private final static String PANELBOX_BUTTON_COLL_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOX_BUTTON_COLL_STYLE);
    private final static String PANELBOX_BUTTON_EXP_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOX_BUTTON_EXP_STYLE);
    private final static String PANELBOXCONTENT_STYLE = PanelBoxStyleManager.getAttributes(PanelBoxStyleManager.Key.DEFAULT_PANELBOXCONTENT_STYLE);

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        final ResponseWriter writer = context.getResponseWriter();
        final UIPanelBox uipb = (UIPanelBox) component;

        if (!uipb.isRendered()) {
            return;
        }

        String dimensions = "";
        if (uipb.getHeight() > 0) {
            dimensions = "height:" + uipb.getHeight() + "px;";
        }
        if (uipb.getWidth() > 0) {
            dimensions += "width:" + uipb.getWidth() + "px;";
        }

        //Main
        writer.startElement("div", uipb);
        writer.writeAttribute("class", PANELBOX_STYLE + " " + uipb.getStyle(), null);
        writer.writeAttribute("style", dimensions + " " + uipb.getStyle(), null);

        //Header
        writer.startElement("div", uipb);
        writer.writeAttribute("class", PANELBOXHEADER_STYLE + " " + uipb.getHeaderStyleClass(), null);
        writer.writeAttribute("style", uipb.getHeaderStyle(), null);

        writer.startElement("div", uipb);
        writer.writeAttribute("class", PANELBOXHEADERCONTENT_STYLE, null);
        writer.writeAttribute("style", uipb.getHeaderStyle(), null);

        if (uipb.isCollapsible()) {
            writer.startElement("div", uipb);
            writer.writeAttribute("class", PANELBOXTOOLBOX_STYLE, null);

            writer.startElement("div", uipb);
            writer.writeAttribute("id", BUT_PREFIXID + uipb.getId(), null);
            writer.writeAttribute("style", PANELBOX_BUTTON_SIZE, null);
            if (uipb.isCollapseByDefault()) {
                writer.writeAttribute("class", PANELBOX_BUTTON_EXP_STYLE, null);
            } else {
                writer.writeAttribute("class", PANELBOX_BUTTON_COLL_STYLE, null);
            }
            writer.endElement("div");

            writer.endElement("div");
        }

        if (uipb.getTitle() != null) {
            writer.startElement("span", uipb);
            writer.write(uipb.getTitle());
            writer.endElement("span");
        } else if (uipb.getValueExpression("title") != null) {
            String titleValue = uipb.getValueExpression("title").getExpressionString();
            ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), titleValue, java.lang.String.class);
            String title = (String) ve.getValue(context.getELContext());
            writer.startElement("span", uipb);
            writer.write(title);
            writer.endElement("span");
        }

        writer.endElement("div");

        writer.endElement("div");

        //Content
        writer.startElement("div", uipb);
        writer.writeAttribute("id", CONTENT_PREFIXID + uipb.getId(), null);
        if (uipb.isCollapseByDefault()) {
            writer.writeAttribute("style", "display:none; " + uipb.getContentStyle(), null);
        } else {
            writer.writeAttribute("style", uipb.getContentStyle(), null);
        }

        writer.writeAttribute("class", PANELBOXCONTENT_STYLE + " " + uipb.getContentStyleClass(), null);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        final UIPanelBox uipb = (UIPanelBox) component;

        if (!uipb.isRendered()) {
            return;
        }

        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIPanelBox uipb = (UIPanelBox) component;

        if (!uipb.isRendered()) {
            return;
        }

        writer.endElement("div");
        writer.endElement("div");

        if (uipb.isCollapsible()) {
            final StringBuilder script = new StringBuilder();
            script.append("var ").append(CONTENT_PREFIXID).append(uipb.getId()).append(" = $('").append(CONTENT_PREFIXID).append(uipb.getId()).append("'); var ").append(BUT_PREFIXID).append(uipb.getId()).append(" = $('").append(BUT_PREFIXID).append(uipb.getId()).append("');");
            script.append("").append(BUT_PREFIXID).append(uipb.getId()).append(".addEvent('click',function(){");
            script.append("if (").append(BUT_PREFIXID).append(uipb.getId()).append(".get('class') == '").append(PANELBOX_BUTTON_COLL_STYLE).append("'){").append(BUT_PREFIXID).append(uipb.getId()).append(".set('class','").append(PANELBOX_BUTTON_EXP_STYLE).append("');").append(CONTENT_PREFIXID).append(uipb.getId()).append(".style.display ='none';}");
            script.append("else {").append(BUT_PREFIXID).append(uipb.getId()).append(".set('class','").append(PANELBOX_BUTTON_COLL_STYLE).append("');").append(CONTENT_PREFIXID).append(uipb.getId()).append(".style.display ='block';}");
            script.append("});");
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.write(script.toString());
            writer.endElement("script");
        }
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
