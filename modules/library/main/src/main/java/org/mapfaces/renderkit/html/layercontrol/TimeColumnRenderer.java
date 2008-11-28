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

package org.mapfaces.renderkit.html.layercontrol;

import java.io.IOException;

import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.layercontrol.UITimeColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.ImgColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 */
public class TimeColumnRenderer extends ImgColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UITimeColumn comp = (UITimeColumn) component;

        //Write the scripts once per page
        final ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.ajaxScript.timecolumn")) {
            extContext.getRequestMap().put("ajaxflag.ajaxScript.timecolumn", Boolean.TRUE);
            writeTimeColumnScripts(context, comp);
        }

        comp.setHeaderTitle("Display or hide temporal information in the timeline.");
        comp.setStyleImg("display:none;");

        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeBegin(context, component);
            final HashMap<String, String> paramsMap1 = new HashMap();
            paramsMap1.put("hidden", "true");

            final UIComponent uicomp = (UIComponent) component.getChildren().get(0);

            //Adding a new img component to remove the corresponding bandInfo on the timeline.
            final HtmlGraphicImage img = new HtmlGraphicImage();
            img.setId(component.getId() + "-off");

            uicomp.getChildren().add(FacesUtils.createTreeAjaxSupportWithParameters(context,
                    (UIComponent) component.getChildren().get(0),
                    "onclick",
                    getVarId(context, (UIColumnBase) component),
                    FacesUtils.getFormId(context, component) + ":timeline",
                    paramsMap1,
                    "hideOrDisplay(\"" + uicomp.getClientId(context) + "\",\"" + img.getClientId(context) + "\")",
                    "resizeDivs()"));

            img.setTitle(comp.getHeaderTitle());
            img.setStyle("cursor:pointer;");

            final String urlOff = comp.getImg();
            if (urlOff != null) {
                if (urlOff.lastIndexOf(".") != -1) {
                    final String extension = urlOff.substring(urlOff.lastIndexOf(".")); //.png
                    String preUrl = urlOff.substring(0, urlOff.lastIndexOf(".")); // path/name
                    preUrl += "-off"; //an image file with name imgurl concated with "-off" must be added in the resources packages.
                    img.setUrl(preUrl + extension);
                }
            }

            final HashMap<String, String> paramsMap2 = new HashMap();
            paramsMap2.put("hidden", "false");
            img.getChildren().add(FacesUtils.createTreeAjaxSupportWithParameters(context,
                    img, "onclick", getVarId(context, (UIColumnBase) component),
                    FacesUtils.getFormId(context, component) + ":timeline",
                    paramsMap2, "hideOrDisplay(\"" + img.getClientId(context) + "\",\"" + uicomp.getClientId(context) + "\")",
                    "resizeDivs()"));

            component.getChildren().add(img);

        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeEnd(context, component);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(final FacesContext context, final UIComponent component, final String event) throws IOException {
    }

    public String getTimes(final FacesContext context, final UITimeColumn comp) {
        final TreeNodeModel tnm = ((UITreeLines) (comp.getParent())).getNodeInstance();
        if (tnm.isLeaf() && ((Layer) (tnm.getUserObject())).getDimensionList() != null) {
            if (((Layer) (tnm.getUserObject())).getTime() != null) {
                return ((Layer) (tnm.getUserObject())).getTime().getValue();
            }
        }
        return null;
    }

    private void writeTimeColumnScripts(final FacesContext context, final UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.write(new StringBuffer("function hideOrDisplay(id1, id2){\n")
                              .append("if( document.getElementById(id1).style.display==\"none\" ){\n")
                              .append("document.getElementById(id1).style.display=\"block\";\n")
                              .append("document.getElementById(id2).style.display=\"none\";\n")
                              .append("}else{\n")
                              .append("document.getElementById(id1).style.display=\"none\";\n")
                              .append("document.getElementById(id2).style.display=\"block\";\n")
                              .append("}}").toString());
        writer.write("if(typeof(resizeDivs)=='undefined'){resizeDivs = function(){};}\n");
        writer.endElement("script");
    }
}