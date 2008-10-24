/*
 *    Mapfaces - 
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.mapfaces.component.treebuilder;

import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UITreePanelBase;

/**
 *
 * @author kdelfour
 */
public class UITreePanel extends UITreePanelBase {

    /* Fields */
    private boolean template;
    private boolean cloneView;
    private boolean emptyView;
    private String target;
    private final String TREEPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.treebuilder.HTMLTreePanel";
    private final String TREEPANEL_COMP_FAMILY = "javax.faces.Output";

    /* Methods */
    @Override
    public String getFamily() {
        return TREEPANEL_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TREEPANEL_RENDERER_TYPE;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = isTemplate();
        values[2] = isCloneView();
        values[3] = isEmptyView();
        values[4] = getTarget();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTemplate((Boolean) values[1]);
        setCloneView((Boolean) values[2]);
        setEmptyView((Boolean) values[3]);
        setTarget((String) values[4]);
        
    }

    /* Accessors */
    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public boolean isCloneView() {
        return cloneView;
    }

    public void setCloneView(boolean cloneView) {
        this.cloneView = cloneView;
    }

    public boolean isEmptyView() {
        return emptyView;
    }

    public void setEmptyView(boolean emptyView) {
        this.emptyView = emptyView;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
