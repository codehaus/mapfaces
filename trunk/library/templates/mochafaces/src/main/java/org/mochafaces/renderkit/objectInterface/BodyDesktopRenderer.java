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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mochafaces.component.objectInterface.UIBodyDesktop;
import org.mochafaces.component.objectInterface.UIBodyDockWrapper;
import org.mochafaces.component.objectInterface.UIBodyPageWrapper;
import org.mochafaces.renderkit.html.DivRenderer;

/**
 *
 * @author kevindelfour
 */
public class BodyDesktopRenderer extends DivRenderer {

    private final static String DESKTOP_ID = "desktop";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
     
        final UIBodyDesktop uibd = (UIBodyDesktop) component;

        boolean declarePageWrapper = true;
        boolean declareDockWrapper = true;

        for (UIComponent uIComponent : uibd.getChildren()) {
            if (uIComponent instanceof UIBodyPageWrapper) {
                declarePageWrapper = false;
            }
            if (uIComponent instanceof UIBodyDockWrapper) {
                declareDockWrapper = false;
            }
        }

        if (declareDockWrapper) {
            Logger.getLogger(BodyDesktopRenderer.class.getName()).log(Level.FINEST, "Adding a dock wrapper to the desktop");
            final UIBodyDockWrapper uibdw = new UIBodyDockWrapper();
            uibd.getChildren().add(uibdw);
        }
        
        if (declarePageWrapper) {
            Logger.getLogger(BodyDesktopRenderer.class.getName()).log(Level.FINEST, "Adding a page wrapper to the desktop");
            final UIBodyPageWrapper uibpw = new UIBodyPageWrapper();
            uibd.getChildren().add(uibpw);
        }
        
        //Fix this div id for help Mocha to know where start the desktop
        uibd.setId(DESKTOP_ID);
        
        super.encodeBegin(context, uibd);
    }
}
