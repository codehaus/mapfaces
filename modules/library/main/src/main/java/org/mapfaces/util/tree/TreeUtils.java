/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util.tree;

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
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.treetable.TreeTableConfig;
import org.mapfaces.util.treetable.TreeTableUtils;

/**
 *
 * @author kdelfour
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
    public static List<UIComponent> duplicate(List<UIComponent> list, TreeNodeModel node) {
        List<UIComponent> backup = new ArrayList<UIComponent>();
        for (UIComponent tmp : list) {
            try {
                UIComponent toduplic = duplicate(tmp, node);
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

    /**
     * 
     * @param component
     * @param node
     * @return
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    private static UIComponent duplicate(UIComponent component, TreeNodeModel node) throws InstantiationException, IllegalAccessException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent news = component.getClass().newInstance();
        String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);

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
     * @param news
     */
    private static void copyAttributes(UIComponent component, UIComponent news) {

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
