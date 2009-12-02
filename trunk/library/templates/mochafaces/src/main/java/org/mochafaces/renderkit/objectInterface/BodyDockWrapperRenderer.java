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
import javax.faces.context.FacesContext;import org.mochafaces.component.html.UIDiv;
import org.mochafaces.component.objectInterface.UIBodyDockWrapper;
import org.mochafaces.renderkit.html.DivRenderer;
;

/**
 *
 * @author kevindelfour
 */
public class BodyDockWrapperRenderer extends DivRenderer {

    private final static String DOCKWRAPPER_ID = "dockWrapper";
    private final static String DOCK_ID = "dock";
    private final static String DOCKPLACEMENT_ID = "dockPlacement";
    private final static String DOCKAUTOHIDE_ID = "dockAutoHide";
    private final static String DOCKSORT_ID = "dockSort";
    private final static String DOCKCLEAR_ID = "dockClear";
    private final static String DOCKCLEAR_CLASS = "clear";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        final UIBodyDockWrapper uibw = (UIBodyDockWrapper) component;

        //Fix this div id for help Mocha to know where start the pageWrapper
        uibw.setId(DOCKWRAPPER_ID);
        
        if (uibw.getChildren() != null) {
            uibw.getChildren().clear();
        }

        final UIDiv dock = new UIDiv();
        final UIDiv dockPlacement = new UIDiv();
        final UIDiv dockAutoHide = new UIDiv();
        final UIDiv dockSort = new UIDiv();
        final UIDiv dockClear = new UIDiv();

        dock.setId(DOCK_ID);
        dockPlacement.setId(DOCKPLACEMENT_ID);
        dockAutoHide.setId(DOCKAUTOHIDE_ID);
        dockSort.setId(DOCKSORT_ID);
        dockClear.setId(DOCKCLEAR_ID);
        dockClear.setStyleClass(DOCKCLEAR_CLASS);

        dock.getChildren().add(dockPlacement);
        dock.getChildren().add(dockAutoHide);
        dockSort.getChildren().add(dockClear);
        dock.getChildren().add(dockSort);

        uibw.getChildren().add(dock);

        super.encodeBegin(context, uibw);
    }
}
