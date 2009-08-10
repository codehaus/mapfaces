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
import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.ImgColumnRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
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
        final TreeNodeModel currentNode = ((UITreeLines) (component.getParent())).getNodeInstance();

        if (currentNode.isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeBegin(context, component);
            final HashMap<String, String> paramsMap1 = new HashMap();
            paramsMap1.put("hidden", "true");

            final HtmlGraphicImage child = (HtmlGraphicImage) component.getChildren().get(0);
            final TreeItem currentTreeItem = (TreeItem) currentNode.getUserObject();

            if (currentTreeItem.getUserObject() instanceof Layer) {
                final Layer layer = (Layer) currentTreeItem.getUserObject();

                if (layer.isDisable()) {
                    child.setRendered(false);

                } else {
                    //Adding a new img component to remove the corresponding bandInfo on the timeline.
                    final HtmlGraphicImage img = new HtmlGraphicImage();
                    img.setId(component.getId() + "-off");

                    child.getFacets().put("a4jsupport",
                            FacesUtils.createTreeAjaxSupportWithParameters(context,
                            child,
                            "onclick",
                            getVarId(context, (UIColumnBase) component),
                            FacesUtils.getFormId(context, component) + ":timeline",
                            paramsMap1,
                            "hideOrDisplay(\"" + child.getClientId(context) + "\",\"" + img.getClientId(context) + "\")",
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
                    img.getFacets().put("a4jsupport", FacesUtils.createTreeAjaxSupportWithParameters(context,
                            img, "onclick", getVarId(context, (UIColumnBase) component),
                            FacesUtils.getFormId(context, component) + ":timeline",
                            paramsMap2, "hideOrDisplay(\"" + img.getClientId(context) + "\",\"" + child.getClientId(context) + "\")",
                            "resizeDivs()"));

                    component.getChildren().add(img);
                }
            }
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
        final TreeItem ti = (TreeItem) tnm.getUserObject();
        Layer layer = null;
        if (ti.getUserObject() instanceof Layer) {
            layer = (Layer) ti.getUserObject();
        }

        if (tnm.isLeaf() && layer != null && layer.getDimensionList() != null) {
            if (layer.getTime() != null) {
                return layer.getTime().getValue();
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
        writer.write(new StringBuilder("function hideOrDisplay(id1, id2){").append("if( document.getElementById(id1).style.display==\"none\" ){").append("document.getElementById(id1).style.display=\"block\";").append("document.getElementById(id2).style.display=\"none\";").append("}else{").append("document.getElementById(id1).style.display=\"none\";").append("document.getElementById(id2).style.display=\"block\";").append("}}").toString());
        writer.write("if(typeof(resizeDivs)=='undefined'){resizeDivs = function(){};}");
        writer.endElement("script");
    }
}