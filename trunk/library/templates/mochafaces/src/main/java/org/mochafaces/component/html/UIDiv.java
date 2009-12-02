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
package org.mochafaces.component.html;

import javax.faces.context.FacesContext;
import org.mochafaces.component.UIBaseExtend;

/**
 *
 * @author kevindelfour
 */
public class UIDiv extends UIBaseExtend {

    private static final String FAMILY = "org.mochafaces.DivHtml";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLDiv";
    /* Fields */
    private boolean dir;
    private String lang;
    private String title;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }
    /* Methods */

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = isDir();
        values[2] = getLang();
        values[3] = getTitle();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setDir((Boolean) values[1]);
        setLang((String) values[2]);
        setTitle((String) values[3]);
    }

    /**
     * @return the dir
     */
    public boolean isDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(boolean dir) {
        this.dir = dir;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
