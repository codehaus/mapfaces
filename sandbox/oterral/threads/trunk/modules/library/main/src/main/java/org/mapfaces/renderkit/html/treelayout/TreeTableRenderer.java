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
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreeTableRenderer;

/**
 *
 * @author kevindelfour
 */
public class TreeTableRenderer extends AbstractTreeTableRenderer{

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
          
        beforeEncodeEnd(context, component);
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");        
        afterEncodeEnd(context, component);
     }

}
