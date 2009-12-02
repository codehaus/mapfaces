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
package org.mochafaces.taglib.resource;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mochafaces.taglib.BaseELTag;

/**
 *
 * @author kevindelfour
 */
public class MetaResourceTag extends BaseELTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.MetaResourceLoader";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLMetaResourceLoader";

    private ValueExpression httpEquiv = null;
    private ValueExpression content = null;
    private ValueExpression name = null;
    private ValueExpression lang = null;

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
        component.setValueExpression("httpEquiv", getHttpEquiv());
        component.setValueExpression("content", getContent());
        component.setValueExpression("name", getName());
        component.setValueExpression("lang", getLang());
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setHttpEquiv(null);
        setContent(null);
        setName(null);
        setLang(null);
    }

    /**
     * @return the httpEquiv
     */
    public ValueExpression getHttpEquiv() {
        return httpEquiv;
    }

    /**
     * @param httpEquiv the httpEquiv to set
     */
    public void setHttpEquiv(ValueExpression httpEquiv) {
        this.httpEquiv = httpEquiv;
    }

    /**
     * @return the content
     */
    public ValueExpression getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(ValueExpression content) {
        this.content = content;
    }

    /**
     * @return the name
     */
    public ValueExpression getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(ValueExpression name) {
        this.name = name;
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

}