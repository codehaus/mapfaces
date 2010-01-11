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
public class CustomResourceTag extends BaseELTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.CustomResourceLoader";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLCustomResourceLoader";

    private ValueExpression src = null;
    private ValueExpression media = null;

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
        component.setValueExpression("src", getSrc());
        component.setValueExpression("media", getMedia());
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setSrc(null);
        setMedia(null);
    }

    /**
     * @return the src
     */
    public ValueExpression getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(ValueExpression src) {
        this.src = src;
    }

    /**
     * @return the media
     */
    public ValueExpression getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(ValueExpression media) {
        this.media = media;
    }
}