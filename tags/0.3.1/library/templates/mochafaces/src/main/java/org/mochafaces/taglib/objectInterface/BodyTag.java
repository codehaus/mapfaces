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
import org.mochafaces.taglib.BaseExtendELTag;

/**
 *
 * @author kevindelfour
 */
public class BodyTag extends BaseExtendELTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.BodyObject";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLBodyObject";
    private ValueExpression dir;
    private ValueExpression lang;
    private ValueExpression title;
    private ValueExpression onload;
    private ValueExpression onunload;

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
        component.setValueExpression("dir", dir);
        component.setValueExpression("lang", lang);
        component.setValueExpression("title", title);
        component.setValueExpression("onload", onload);
        component.setValueExpression("onunload", onunload);

    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setDir(null);
        setLang(null);
        setTitle(null);
        setOnload(null);
        setOnunload(null);
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

    /**
     * @return the onload
     */
    public ValueExpression getOnload() {
        return onload;
    }

    /**
     * @param onload the onload to set
     */
    public void setOnload(ValueExpression onload) {
        this.onload = onload;
    }

    /**
     * @return the onunload
     */
    public ValueExpression getOnunload() {
        return onunload;
    }

    /**
     * @param onunload the onunload to set
     */
    public void setOnunload(ValueExpression onunload) {
        this.onunload = onunload;
    }
}
