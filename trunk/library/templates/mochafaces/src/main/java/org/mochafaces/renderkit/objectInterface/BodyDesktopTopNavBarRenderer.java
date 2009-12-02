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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mochafaces.component.objectInterface.UIBodyDesktopTopNavBar;
import org.mochafaces.renderkit.html.DivRenderer;

/**
 *
 * @author kevindelfour
 */
public class BodyDesktopTopNavBarRenderer extends DivRenderer {

    private final static String TOPNAVBAR_ID = "topNav";
    private final static String TOPNAVBAR_CLASS = "menu-right";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
                
        final UIBodyDesktopTopNavBar topNavBar = (UIBodyDesktopTopNavBar) component;
        final ResponseWriter writer = context.getResponseWriter();
        //Fix this div id for help Mocha to know where start the desktop
        topNavBar.setId(TOPNAVBAR_ID);
        
        super.encodeBegin(context, topNavBar);
        writer.startElement("div", component);
        writer.writeAttribute("class", TOPNAVBAR_CLASS, null);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        super.encodeEnd(context, component);
    }


}
