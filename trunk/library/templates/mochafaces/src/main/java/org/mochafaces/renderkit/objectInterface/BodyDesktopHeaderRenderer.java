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
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.mochafaces.component.html.UIDiv;
import org.mochafaces.component.objectInterface.UIBodyDesktopAppNavBar;
import org.mochafaces.component.objectInterface.UIBodyDesktopHeader;
import org.mochafaces.component.objectInterface.UIBodyDesktopTopNavBar;
import org.mochafaces.renderkit.html.DivRenderer;

/**
 *
 * @author kevindelfour
 */
public class BodyDesktopHeaderRenderer extends DivRenderer {

    private final static String DESKTOPHEADER_ID = "desktopHeader";
    private final static String DESKTOPTITLEBARWRAPPER_ID = "desktopTitlebarWrapper";
    private final static String DESKTOPNAVBARWRAPPER_ID = "desktopNavbar";
    private final static String DESKTOPTITLEBAR_ID = "desktopTitlebar";
    private final static String APPLICATIONTITLE_CLASS = "applicationTitle";
    private final static String TAGLINE_CLASS = "tagline";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        
        final UIBodyDesktopHeader uibdh = (UIBodyDesktopHeader) component;

        if (uibdh.isEnableApplicationTitleBar()) {
            final UIDiv desktopTitlebarWrapper = new UIDiv();
            final UIDiv desktopTitlebar = new UIDiv();

            for (UIComponent uIComponent : uibdh.getChildren()) {
                if (uIComponent instanceof UIBodyDesktopTopNavBar) {
                    desktopTitlebar.getChildren().add(uIComponent);
                }
            }

            final UIDiv applicationTitleBar = new UIDiv();
            desktopTitlebarWrapper.setId(DESKTOPTITLEBARWRAPPER_ID);
            desktopTitlebar.setId(DESKTOPTITLEBAR_ID);
            applicationTitleBar.setStyleClass(APPLICATIONTITLE_CLASS);

            if (uibdh.getApplicationLogoURL() != null) {
                final String logoUrl = "background: transparent url('" + uibdh.getApplicationLogoURL() + "') no-repeat scroll 0% 0%;";
                desktopTitlebar.setStyle(logoUrl);
            }

            if (uibdh.getApplicationTitle() != null) {
                final HtmlOutputText applicationTitle = new HtmlOutputText();
                applicationTitle.setValue(uibdh.getApplicationTitle());
                applicationTitle.setStyleClass(APPLICATIONTITLE_CLASS);
                desktopTitlebar.getChildren().add(applicationTitle);
            }

            if (uibdh.getApplicationTagline() != null) {
                final HtmlOutputText applicationTagline = new HtmlOutputText();
                applicationTagline.setValue(uibdh.getApplicationTagline());
                applicationTagline.setStyleClass(TAGLINE_CLASS);
                desktopTitlebar.getChildren().add(applicationTagline);
            }

            desktopTitlebarWrapper.getChildren().add(desktopTitlebar);
            uibdh.getChildren().add(desktopTitlebarWrapper);
        } else {
            for (UIComponent uIComponent : uibdh.getChildren()) {
                if (uIComponent instanceof UIBodyDesktopTopNavBar) {
                    uIComponent.setRendered(false);
                }
            }
        }

        if (uibdh.isEnableApplicationMainNavBar()) {
            final UIDiv desktopNavbarWrapper = new UIDiv();
            desktopNavbarWrapper.setId(DESKTOPNAVBARWRAPPER_ID);
            for (UIComponent uIComponent : uibdh.getChildren()) {
                if (uIComponent instanceof UIBodyDesktopAppNavBar) {
                    UIBodyDesktopAppNavBar tmp = (UIBodyDesktopAppNavBar) uIComponent;
                    desktopNavbarWrapper.getChildren().add(tmp);
                    if (tmp.getStyle() != null){
                        desktopNavbarWrapper.setStyle(tmp.getStyle());
                        tmp.setStyle(null);
                    }
                    if (tmp.getStyleClass() != null){
                        desktopNavbarWrapper.setStyleClass(tmp.getStyleClass());
                        tmp.setStyleClass(null);
                    }
                }
            }
            uibdh.getChildren().add(desktopNavbarWrapper);
        } else {
            for (UIComponent uIComponent : uibdh.getChildren()) {
                if (uIComponent instanceof UIBodyDesktopAppNavBar) {
                    uIComponent.setRendered(false);
                }
            }
        }


        //Fix this div id for help Mocha to know where start the desktop
        uibdh.setId(DESKTOPHEADER_ID);

        super.encodeBegin(context, uibdh);
    }
}
