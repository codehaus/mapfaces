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
import org.mochafaces.component.UIBaseExtend;

/**
 *
 * @author kevindelfour
 */
public abstract class BaseExtendRenderer extends BaseRenderer {

    @Override
    public void writeDefaultAttribute(FacesContext context, UIComponent component) {
        super.writeDefaultAttribute(context, component);
        
        final ResponseWriter writer = context.getResponseWriter();
        final UIBaseExtend uib = (UIBaseExtend) component;

        try {
            if (uib.getOnclick() != null) {
                writer.writeAttribute("onclick", uib.getOnclick(), null);
            }

            if (uib.getOndblclick() != null) {
                writer.writeAttribute("ondblclick", uib.getOndblclick(), null);
            }

            if (uib.getOnkeydown() != null) {
                writer.writeAttribute("onkeydown", uib.getOnkeydown(), null);
            }

            if (uib.getOnkeypress() != null) {
                writer.writeAttribute("onkeypress", uib.getOnkeypress(), null);
            }

            if (uib.getOnkeyup() != null) {
                writer.writeAttribute("onkeyup", uib.getOnkeyup(), null);
            }

            if (uib.getOnmousedown() != null) {
                writer.writeAttribute("onmousedown", uib.getOnmousedown(), null);
            }

            if (uib.getOnmousemove() != null) {
                writer.writeAttribute("onmousemove", uib.getOnmousemove(), null);
            }

            if (uib.getOnmouseout() != null) {
                writer.writeAttribute("onmouseout", uib.getOnmouseout(), null);
            }

            if (uib.getOnmouseover() != null) {
                writer.writeAttribute("onmouseover", uib.getOnmouseover(), null);
            }

            if (uib.getOnmouseup() != null) {
                writer.writeAttribute("onmouseup", uib.getOnmouseup(), null);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(BaseExtendRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
