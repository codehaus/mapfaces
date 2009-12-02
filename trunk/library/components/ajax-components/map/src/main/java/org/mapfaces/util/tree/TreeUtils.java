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

package org.mapfaces.util.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.ReflectionUtils;

/**
 * @author Kevin Delfour
 */
public class TreeUtils {

    private static final Logger LOGGER = Logger.getLogger(TreeUtils.class.getName());
    private static final Collection<String> NO_COPY_PROPERTIES = new ArrayList<String>();

    static{
        NO_COPY_PROPERTIES.add("Id");
        NO_COPY_PROPERTIES.add("Converter");
        NO_COPY_PROPERTIES.add("ValueExpression");
        NO_COPY_PROPERTIES.add("RendererType");
        NO_COPY_PROPERTIES.add("Parent");
        NO_COPY_PROPERTIES.add("Value");
        NO_COPY_PROPERTIES.add("ValueBinding");
    }

    /**
     * Duplicate method to clone a list Of UIComponent with specified value from
     * a TreeNodeModel
     * @param list a list to make copy of UIComponent to clone
     * @param node a node who have values for duplicate the UIComponent
     * with specified values
     * @return List<UIComponent> initiate with TreeNodeModels values
     */
    public static List<UIComponent> duplicate(final List<UIComponent> list, final TreeNodeModel node) {
        final List<UIComponent> copy = new ArrayList<UIComponent>();
        for (final UIComponent tmp : list) {
            try {
                final UIComponent itemCopy = duplicate(tmp, node);
                itemCopy.saveState(FacesContext.getCurrentInstance());
                copy.add(itemCopy);
            } catch (InstantiationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return copy;
    }

    private static UIComponent duplicate(final UIComponent component, final TreeNodeModel node)
            throws InstantiationException, IllegalAccessException {
        final FacesContext context      = FacesContext.getCurrentInstance();
        final UIComponent copy          = component.getClass().newInstance();
        final String treepanelId        = FacesMapUtils.getParentComponentClientIdByClass(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) FacesMapUtils.findComponentByClientId(context, component, treepanelId);

        //Copy specific attributes from component to news
        ReflectionUtils.copyAttributes(component, copy,NO_COPY_PROPERTIES);

        ValueExpression vex = component.getValueExpression("value");
        final Object value = component.getAttributes().get("value");
        if (value != null) {
            if (value instanceof String) {
                final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                copy.getAttributes().put("value", ve.getValue(context.getELContext()));
            } else {
                copy.getAttributes().put("value", value);
            }

            final ExternalContext ec = context.getExternalContext();
            final Map requestMap = ec.getRequestMap();
            if(component.getId() != null && treepanel.getId() != null && component.getId().contains(treepanel.getId()))
                copy.setId(component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));
            else
                copy.setId(treepanel.getId() + "_" + component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));

        } else {
           if(component.getId() != null && treepanel.getId() != null && component.getId().contains(treepanel.getId()))
               copy.setId(component.getId() + "_" + node.getId());
           else
               copy.setId(treepanel.getId() + "_" + component.getId() + "_" + node.getId());
        }

        if (component.getChildCount() > 0) {
            final List<UIComponent> children = copy.getChildren();
            for (final UIComponent tmp : component.getChildren()) {
                children.add(duplicate(tmp, node));
            }
        }
        copy.setValueExpression("value", vex);
        return copy;
    }

}
