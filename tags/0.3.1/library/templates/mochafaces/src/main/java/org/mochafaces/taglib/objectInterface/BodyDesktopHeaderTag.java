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
package org.mochafaces.taglib.objectInterface;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mochafaces.taglib.html.DivTag;

/**
 *
 * @author kevindelfour
 */
public class BodyDesktopHeaderTag extends DivTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.BodyDesktopHeaderObject";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLBodyDesktopHeaderObject";
    private ValueExpression enableApplicationMainNavBar;
    private ValueExpression enableApplicationTitleBar;
    private ValueExpression enableApplicationTopNavBar;
    private ValueExpression applicationLogoURL;
    private ValueExpression applicationTagline;
    private ValueExpression applicationTitle;

    /* Methods*/
    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
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
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("enableApplicationMainNavBar",enableApplicationMainNavBar);
        component.setValueExpression("enableApplicationTitleBar",enableApplicationTitleBar);
        component.setValueExpression("enableApplicationTopNavBar",enableApplicationTopNavBar);
        component.setValueExpression("applicationLogoURL",applicationLogoURL);
        component.setValueExpression("applicationTagline",applicationTagline);
        component.setValueExpression("applicationTitle",applicationTitle);
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setEnableApplicationMainNavBar(null);
        setEnableApplicationTitleBar(null);
        setEnableApplicationTopNavBar(null);
        setApplicationLogoURL(null);
        setApplicationTagline(null);
        setApplicationTitle(null);
    }

    /**
     * @return the enableApplicationMainNavBar
     */
    public ValueExpression getEnableApplicationMainNavBar() {
        return enableApplicationMainNavBar;
    }

    /**
     * @param enableApplicationMainNavBar the enableApplicationMainNavBar to set
     */
    public void setEnableApplicationMainNavBar(ValueExpression enableApplicationMainNavBar) {
        this.enableApplicationMainNavBar = enableApplicationMainNavBar;
    }

    /**
     * @return the enableApplicationTitleBar
     */
    public ValueExpression getEnableApplicationTitleBar() {
        return enableApplicationTitleBar;
    }

    /**
     * @param enableApplicationTitleBar the enableApplicationTitleBar to set
     */
    public void setEnableApplicationTitleBar(ValueExpression enableApplicationTitleBar) {
        this.enableApplicationTitleBar = enableApplicationTitleBar;
    }

    /**
     * @return the enableApplicationTopNavBar
     */
    public ValueExpression getEnableApplicationTopNavBar() {
        return enableApplicationTopNavBar;
    }

    /**
     * @param enableApplicationTopNavBar the enableApplicationTopNavBar to set
     */
    public void setEnableApplicationTopNavBar(ValueExpression enableApplicationTopNavBar) {
        this.enableApplicationTopNavBar = enableApplicationTopNavBar;
    }

    /**
     * @return the applicationLogoURL
     */
    public ValueExpression getApplicationLogoURL() {
        return applicationLogoURL;
    }

    /**
     * @param applicationLogoURL the applicationLogoURL to set
     */
    public void setApplicationLogoURL(ValueExpression applicationLogoURL) {
        this.applicationLogoURL = applicationLogoURL;
    }

    /**
     * @return the applicationTagline
     */
    public ValueExpression getApplicationTagline() {
        return applicationTagline;
    }

    /**
     * @param applicationTagline the applicationTagline to set
     */
    public void setApplicationTagline(ValueExpression applicationTagline) {
        this.applicationTagline = applicationTagline;
    }

    /**
     * @return the applicationTitle
     */
    public ValueExpression getApplicationTitle() {
        return applicationTitle;
    }

    /**
     * @param applicationTitle the applicationTitle to set
     */
    public void setApplicationTitle(ValueExpression applicationTitle) {
        this.applicationTitle = applicationTitle;
    }
}
