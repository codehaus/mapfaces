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
import org.mochafaces.taglib.BaseELTag;

/**
 *
 * @author kevindelfour
 */
public class HeaderTag extends BaseELTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.HeaderObject";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLHeaderObject";
    private ValueExpression dir;
    private ValueExpression lang;
    private ValueExpression profile;
    private ValueExpression title;

    /* Methods*/
    /**
     * @see getComponentType in class UITreeTableELTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * @see getComponentType in class UITreeTableELTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @override setProperties in class UITreeTableELTag
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("dir", dir);
        component.setValueExpression("lang", lang);
        component.setValueExpression("profile", profile);
        component.setValueExpression("title", title);
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setDir(null);
        setLang(null);
        setProfile(null);
        setTitle(null);
    }

    /**
     * @return the dir
     */
    public ValueExpression getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(ValueExpression dir) {
        this.dir = dir;
    }

    /**
     * @return the lang
     */
    public ValueExpression getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(ValueExpression lang) {
        this.lang = lang;
    }

    /**
     * @return the profile
     */
    public ValueExpression getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(ValueExpression profile) {
        this.profile = profile;
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }
}
