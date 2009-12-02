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
import org.mochafaces.component.objectInterface.UIBodyDesktopAppNavBar;
import org.mochafaces.renderkit.html.DivRenderer;

/**
 *
 * @author kevindelfour
 */
public class BodyDesktopAppNavBarRenderer extends DivRenderer {

    private final static String APPNAVBAR_ID = "desktopNavigation";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        final UIBodyDesktopAppNavBar appNavBar = (UIBodyDesktopAppNavBar) component;
        
        appNavBar.setId(APPNAVBAR_ID);

        super.encodeBegin(context, appNavBar);
    }
}
