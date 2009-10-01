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
package org.mochafaces.component.objectInterface;

import javax.faces.context.FacesContext;
import org.mochafaces.component.html.UIDiv;

/**
 *
 * @author kevindelfour
 */
public class UIBodyDesktopHeader extends UIDiv {

    private static final String FAMILY = "org.mochafaces.BodyDesktopHeaderObject";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLBodyDesktopHeaderObject";

    private boolean enableApplicationMainNavBar;
    private boolean enableApplicationTitleBar;
    private boolean enableApplicationTopNavBar;
    private String applicationLogoURL;
    private String applicationTagline;
    private String applicationTitle;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

     /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(FacesContext context) {
        final Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = isEnableApplicationMainNavBar();
        values[2] = isEnableApplicationTitleBar();
        values[3] = isEnableApplicationTopNavBar();
        values[4] = getApplicationLogoURL();
        values[5] = getApplicationTagline();
        values[6] = getApplicationTitle();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setEnableApplicationMainNavBar((Boolean) values[1]);
        setEnableApplicationTitleBar((Boolean) values[2]);
        setEnableApplicationTopNavBar((Boolean) values[3]);
        setApplicationLogoURL((String) values[4]);
        setApplicationTagline((String) values[5]);
        setApplicationTitle((String) values[6]);
    }

    /**
     * @return the enableApplicationTitleBar
     */
    public boolean isEnableApplicationTitleBar() {
        return enableApplicationTitleBar;
    }

    /**
     * @param enableApplicationTitleBar the enableApplicationTitleBar to set
     */
    public void setEnableApplicationTitleBar(boolean enableApplicationTitleBar) {
        this.enableApplicationTitleBar = enableApplicationTitleBar;
    }

    /**
     * @return the enableApplicationTopNavBar
     */
    public boolean isEnableApplicationTopNavBar() {
        return enableApplicationTopNavBar;
    }

    /**
     * @param enableApplicationTopNavBar the enableApplicationTopNavBar to set
     */
    public void setEnableApplicationTopNavBar(boolean enableApplicationTopNavBar) {
        this.enableApplicationTopNavBar = enableApplicationTopNavBar;
    }

    /**
     * @return the enableApplicationMainNavBar
     */
    public boolean isEnableApplicationMainNavBar() {
        return enableApplicationMainNavBar;
    }

    /**
     * @param enableApplicationMainNavBar the enableApplicationMainNavBar to set
     */
    public void setEnableApplicationMainNavBar(boolean enableApplicationMainNavBar) {
        this.enableApplicationMainNavBar = enableApplicationMainNavBar;
    }

    /**
     * @return the applicationTitle
     */
    public String getApplicationTitle() {
        return applicationTitle;
    }

    /**
     * @param applicationTitle the applicationTitle to set
     */
    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    /**
     * @return the applicationLogoURL
     */
    public String getApplicationLogoURL() {
        return applicationLogoURL;
    }

    /**
     * @param applicationLogoURL the applicationLogoURL to set
     */
    public void setApplicationLogoURL(String applicationLogoURL) {
        this.applicationLogoURL = applicationLogoURL;
    }

    /**
     * @return the applicationTagline
     */
    public String getApplicationTagline() {
        return applicationTagline;
    }

    /**
     * @param applicationTagline the applicationTagline to set
     */
    public void setApplicationTagline(String applicationTagline) {
        this.applicationTagline = applicationTagline;
    }
}
