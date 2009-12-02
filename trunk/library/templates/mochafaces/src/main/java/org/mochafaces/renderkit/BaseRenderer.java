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
package org.mochafaces.renderkit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mochafaces.component.UIBase;

/**
 *
 * @author kevindelfour
 */
public abstract class BaseRenderer extends Renderer {

    public void writeDefaultAttribute(FacesContext context, UIComponent component) {
        final ResponseWriter writer = context.getResponseWriter();
        final UIBase uib = (UIBase) component;

        if (uib.isDebug()) {
            Logger.getLogger(BaseRenderer.class.getName()).log(Level.INFO, "writeDefaultAttribute for " + uib.getClientId(context));
        }
        
        try {
            if (uib.getStyle() != null) {
                writer.writeAttribute("style", uib.getStyle(), null);
            }

            if (uib.getStyleClass() != null) {
                writer.writeAttribute("class", uib.getStyleClass(), null);
            }
        } catch (IOException ex) {
            Logger.getLogger(BaseRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
