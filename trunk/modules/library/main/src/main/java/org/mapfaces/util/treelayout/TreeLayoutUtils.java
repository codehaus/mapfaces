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
package org.mapfaces.util.treelayout;

import org.mapfaces.share.requestmap.RequestMapUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.treelayout.TreePanelRenderer;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kdelfour
 */
public class TreeLayoutUtils {

    /**
     * Duplicate method to clone a list Of UIComponent with specified value from
     * a TreeNodeModel
     * @param list a list to make copy of UIComponent to clone
     * @param node a node who have values for duplicate the UIComponent 
     * with specified values
     * @return List<UIComponent> initiate with TreeNodeModels values
     */
    public List<UIComponent> duplicate(List<UIComponent> list, TreeNodeModel node) {
        List<UIComponent> backup = new ArrayList<UIComponent>();
        for (UIComponent tmp : list) {
            try {
                UIComponent toduplic = duplicate(tmp, node);
                toduplic.saveState(FacesContext.getCurrentInstance());
                backup.add(toduplic);
            } catch (InstantiationException ex) {
                Logger.getLogger(TreePanelRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TreePanelRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return backup;
    }

    /**
     * 
     * @param component
     * @param node
     * @return
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    private UIComponent duplicate(UIComponent component, TreeNodeModel node) throws InstantiationException, IllegalAccessException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent news = component.getClass().newInstance();
        String treepanelId = Utils.getWrappedComponent(context, component, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);

        //Copy specific attributes from component to news
        copyAttributes(component, news);

        Object value = component.getAttributes().get("value");
        ValueExpression ve;
        if (value != null) {
            if (value.getClass().toString().contains("java.lang.String")) {
                ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                news.getAttributes().put("value", ve.getValue(context.getELContext()));
            } else {
                news.getAttributes().put("value", value);
            }

            ELContext el = context.getELContext();
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Map requestMap = ec.getRequestMap();
            news.setId(treepanel.getId() + "_" + component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));

        } else {
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

    /**
     * 
     * @param component
     * @param node
     * @param list
     * @throws java.io.IOException
     */
    public void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String treepanelId = Utils.getWrappedComponent(context, component, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);
        System.out.println("Treepanel id :" + treepanelId);
        if (!((UIAbstractTreePanel) component).isInit()) {
            for (int i = 0; i < node.getChildCount(); i++) {
                TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
                RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

                //Create a new treeline and get all component to make a backup
                UITreeLines treelines = new UITreeLines();
                String id = treepanel.getId() + "_" + "line_" + String.valueOf(currentNode.getId());
                treelines.setId(id);
                treelines.setNodeInstance(currentNode);

                List<UIComponent> tocopy = duplicate(list, currentNode);

                treelines.getChildren().addAll(tocopy);
                if (!currentNode.isLeaf()) {
                    treelines.setHasChildren(true);
                    createTreeLinesRecurs(((UIAbstractTreePanel) component), treelines, currentNode, list);
                }
                treepanel.getChildren().add(treelines);
            }
        }
    }

    /**
     * 
     * @param treepanel 
     * @param component
     * @param node
     * @param list
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
    private void createTreeLinesRecurs(UIAbstractTreePanel treepanel, UIComponent component, TreeNodeModel node, List<UIComponent> list) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        UITreeLines treelines = null;
        TreeLayoutConfig config = new TreeLayoutConfig();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        requestMap.put("Elresolver.called", false);

        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

            treelines = new UITreeLines();
            String id = treepanel.getId() + "_" + "line_" + String.valueOf(currentNode.getId());

            treelines.setId(id);
            treelines.setNodeInstance(currentNode);


            List<UIComponent> tocopy = duplicate(list, currentNode);
            treelines.getChildren().addAll(tocopy);

            if (!treepanel.isInit()) {
                if (node.getDepth() > config.getDEFAULT_DEPTH_VIEW()) {
                    treelines.setToRender(false);
                    treelines.setRendered(false);
                }
            }
            if (!currentNode.isLeaf()) {
                treelines.setHasChildren(true);
                createTreeLinesRecurs(treepanel, treelines, currentNode, list);
            }
            treepanel.getChildren().add(treelines);
        }

    }

    /**
     * 
     * @param component
     * @param news
     */
    private void copyAttributes(UIComponent component, UIComponent news) {

        Class newClasse = news.getClass();
        Class oldClasse = component.getClass();
        Object resultGet;

        if (component != null) {
            if (news != null) {
                for (Method method : newClasse.getMethods()) {
                    if (method.getName().startsWith("set")) {
                        try {
                            String Propertie = method.getName().substring(3);
                            if (!(Propertie.equals("Id")) && !(Propertie.equals("Converter")) && !(Propertie.equals("ValueExpression")) && !(Propertie.equals("RendererType")) && !(Propertie.equals("Parent")) && !(Propertie.equals("Value"))) {

                                Method Getter = null;
                                if (oldClasse.getMethod("get" + Propertie) != null) {
                                    Getter = oldClasse.getMethod("get" + Propertie);
                                    try {
                                        resultGet = Getter.invoke(component);
                                        method.invoke(news, resultGet);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                if (oldClasse.getMethod("is" + Propertie) != null) {
                                    Getter = oldClasse.getMethod("is" + Propertie);
                                    try {
                                        resultGet = Getter.invoke(component);
                                        method.invoke(news, resultGet);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        } catch (NoSuchMethodException ex) {
                            //Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.INFO, null, ex.getMessage());
                            } catch (SecurityException ex) {
                            Logger.getLogger(TreeLayoutUtils.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
}

