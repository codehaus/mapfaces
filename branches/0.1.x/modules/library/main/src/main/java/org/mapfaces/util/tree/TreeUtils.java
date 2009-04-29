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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.treetable.TreeTableConfig;
import org.mapfaces.util.treetable.TreeTableUtils;

/**
 * @author Kevin Delfour
 */
public class TreeUtils {

    /**
     * Duplicate method to clone a list Of UIComponent with specified value from
     * a TreeNodeModel
     * @param list a list to make copy of UIComponent to clone
     * @param node a node who have values for duplicate the UIComponent
     * with specified values
     * @return List<UIComponent> initiate with TreeNodeModels values
     */
    public static List<UIComponent> duplicate(final List<UIComponent> list, final TreeNodeModel node) {
        final List<UIComponent> backup = new ArrayList<UIComponent>();
        for (final UIComponent tmp : list) {
            try {
                final UIComponent toduplic = duplicate(tmp, node);
                toduplic.saveState(FacesContext.getCurrentInstance());
                backup.add(toduplic);
            } catch (InstantiationException ex) {
                Logger.getLogger(TreeTableUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TreeTableUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return backup;
    }

    private static UIComponent duplicate(final UIComponent component, final TreeNodeModel node)
            throws InstantiationException, IllegalAccessException {
        final FacesContext context      = FacesContext.getCurrentInstance();
        final UIComponent news          = component.getClass().newInstance();
        final String treepanelId        = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);

        //Copy specific attributes from component to news
        copyAttributes(component, news);

        final Object value = component.getAttributes().get("value");
        if (value != null) {
            if (value.getClass().toString().contains("java.lang.String")) {
                final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                news.getAttributes().put("value", ve.getValue(context.getELContext()));
            } else {
                news.getAttributes().put("value", value);
            }

            final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            final Map requestMap = ec.getRequestMap();
            if(component.getId() != null && treepanel.getId() != null && component.getId().contains(treepanel.getId()))
               news.setId(component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));
            else
                news.setId(treepanel.getId() + "_" + component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));

        } else {
           if(component.getId() != null && treepanel.getId() != null && component.getId().contains(treepanel.getId()))
                news.setId(component.getId() + "_" + node.getId());
            else
                news.setId(treepanel.getId() + "_" + component.getId() + "_" + node.getId());
        }

        if (component.getChildCount() > 0) {
            for (UIComponent tmp : component.getChildren()) {
                UIComponent toduplic = duplicate(tmp, node);
                news.getChildren().add(toduplic);
            }
        }
        return news;
    }

    private static void copyAttributes(final UIComponent component, final UIComponent news) {

        final Class newClasse = news.getClass();
        final Class oldClasse = component.getClass();
        Object resultGet;

        if (component != null) {
            if (news != null) {
                for (final Method method : newClasse.getMethods()) {
                    if (method.getName().startsWith("set")) {
                        try {
                            final String Propertie = method.getName().substring(3);
                            if (!(Propertie.equals("Id")) && !(Propertie.equals("Converter")) && !(Propertie.equals("ValueExpression")) && !(Propertie.equals("RendererType")) && !(Propertie.equals("Parent")) && !(Propertie.equals("Value"))) {

                                Method Getter = null;
                                if (oldClasse.getMethod("get" + Propertie) != null) {
                                    Getter = oldClasse.getMethod("get" + Propertie);
                                    try {
                                        resultGet = Getter.invoke(component);
                                        method.invoke(news, resultGet);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                if (oldClasse.getMethod("is" + Propertie) != null) {
                                    Getter = oldClasse.getMethod("is" + Propertie);
                                    try {
                                        resultGet = Getter.invoke(component);
                                        method.invoke(news, resultGet);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        } catch (NoSuchMethodException ex) {
                            //Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.INFO, null, ex.getMessage());
                            } catch (SecurityException ex) {
                            Logger.getLogger(TreeTableConfig.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
}
