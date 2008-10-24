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

package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.treelayout.UIImgColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

/**
 * @author Olivier Terral.
 */
public class ImgColumnRenderer extends AbstractColumnRenderer {

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        UIImgColumn comp = (UIImgColumn) component;
        ResponseWriter writer = context.getResponseWriter();


        HtmlGraphicImage img = new HtmlGraphicImage();
        img.setId("img_" + comp.getId());
        /**
         * Problem with url of an HtmlGraphicImage , when the first character of the url is a slash , the compoennt
         * addd automatically a /webappname/[the url we want] so we have specified directly in the property imgData
         * of the column the url with resource.jsf
         * 
         */
        //System.out.println(ResourcePhaseListener.getURL(context,comp.getImg(), null));
        img.setUrl(comp.getImg());
        img.setTitle(comp.getHeaderTitle());
        //img.setStyle("cursor:pointer;position: absolute; margin-left:-13px;left: 50%; margin-top: -13px; top: 50%; ");
        img.setStyle("cursor:pointer;");
        comp.getChildren().add(img);

        writer.startElement("center", comp);

    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        //addRequestScript(context, component, "change");
    }

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }
    
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
}
