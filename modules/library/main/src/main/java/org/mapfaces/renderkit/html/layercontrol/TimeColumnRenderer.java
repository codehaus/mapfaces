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

public class TimeColumnRenderer extends ImgColumnRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        UITimeColumn comp = (UITimeColumn) component;

        //Write the scripts once per page
        ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.ajaxScript.timecolumn")) {
            extContext.getRequestMap().put("ajaxflag.ajaxScript.timecolumn", Boolean.TRUE);
            writeTimeColumnScripts(context, comp);
        }

        comp.setHeaderTitle("Display or hide temporal information in the timeline.");
        comp.setStyleImg("display:none;");

        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeBegin(context, component);
            HashMap<String, String> paramsMap1 = new HashMap();
            paramsMap1.put("hidden", "true");
            UIComponent uicomp = (UIComponent) component.getChildren().get(0);

            //Adding a new img component to remove the corresponding bandInfo on the timeline.
            HtmlGraphicImage img = new HtmlGraphicImage();
            img.setId(component.getId() + "-off");

            uicomp.getChildren().add(FacesUtils.createTreeAjaxSupportWithParameters(context,
                    (UIComponent) component.getChildren().get(0),
                    "onclick",
                    getVarId(context, (UIColumnBase) component),
                    FacesUtils.getFormId(context, component) + ":timeline",
                    paramsMap1,
                    "hideOrDisplay(\"" + uicomp.getClientId(context) + "\",\"" + img.getClientId(context) + "\")",
                    "setBandsColors()"));

            img.setTitle(comp.getHeaderTitle());
            img.setStyle("cursor:pointer;");

            String urlOff = comp.getImg();
            String extension;
            String preUrl;
            if (urlOff != null) {
                if (urlOff.lastIndexOf(".") != -1) {
                    extension = urlOff.substring(urlOff.lastIndexOf(".")); //.png
                    preUrl = urlOff.substring(0, urlOff.lastIndexOf(".")); // path/name
                    preUrl += "-off"; //an image file with name imgurl concated with "-off" must be added in the resources packages.
                    img.setUrl(preUrl + extension);
                }
            }

            HashMap<String, String> paramsMap2 = new HashMap();
            paramsMap2.put("hidden", "false");
            img.getChildren().add(FacesUtils.createTreeAjaxSupportWithParameters(context,
                    img, "onclick", getVarId(context, (UIColumnBase) component),
                    FacesUtils.getFormId(context, component) + ":timeline",
                    paramsMap2, "hideOrDisplay(\"" + img.getClientId(context) + "\",\"" + uicomp.getClientId(context) + "\")",
                    "setBandsColors()"));

            component.getChildren().add(img);

        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf() && getTimes(context, (UITimeColumn) component) != null) {
            super.encodeEnd(context, component);
        }
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    public String getTimes(FacesContext context, UITimeColumn comp) {
        TreeNodeModel tnm = ((UITreeLines) (comp.getParent())).getNodeInstance();
        if (tnm.isLeaf() && ((Layer) (tnm.getUserObject())).getDimensionList() != null) {
            if (((Layer) (tnm.getUserObject())).getTime() != null) {
                return ((Layer) (tnm.getUserObject())).getTime().getValue();
            }
        }
        return null;
    }

    private void writeTimeColumnScripts(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.write("function hideOrDisplay(id1, id2){\n" +
                "if( document.getElementById(id1).style.display==\"none\" ){\n" +
                "document.getElementById(id1).style.display=\"block\";\n" +
                "document.getElementById(id2).style.display=\"none\";\n" +
                "}else{\n" +
                "document.getElementById(id1).style.display=\"none\";\n" +
                "document.getElementById(id2).style.display=\"block\";\n" +
                "}}");
        writer.write("function setBandsColors(){\n"+
	"var j = 0;\n"+
	"for (var i = timeline_Container_bandInfos.length; i > 0; i--) {\n"+
		"var colors = new Array();\n"+
		"colors[0] = \"#feff86;\";"+
		"colors[1] = \"#BAD3E8;\";\n"+
		"colors[2] = \"#DFE8F6;\";\n"+
		"colors[3] = \"#ffd1b0;\";\n"+
		"colors[4] = \"#deffd8;\";\n"+
		"colors[5] = \"#fde5ff;\";\n"+
		"colors[6] = \"#cfffe6;\";\n"+
		"timeline_Container_bandInfos[i-1].etherPainter._backgroundLayer.style.backgroundColor = colors[j];\n"+
		"j++;\n"+
	"}}");
        writer.endElement("script");
    }
}