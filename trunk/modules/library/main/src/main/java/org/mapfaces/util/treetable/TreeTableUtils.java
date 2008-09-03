package org.mapfaces.util.treetable;

import org.mapfaces.share.requestmap.RequestMapUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.component.tree.UITreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.renderkit.html.tree.TreePanelRenderer;

/**
 *
 * @author kdelfour
 */
public class TreeTableUtils {

    private int count = 0;
    private String NOVALUE = "No value found!";

    /* ======================= TREE METHODS ====================================*/
    /**
     * 
     * @param tree a DefaultTreeModel or a TreeTableModel
     * @return
     */
    public int getTreeNodeCount(TreeTableModel tree) {
        TreeNodeModel leaf = tree.getRoot();
        return SsTreeNodeCount(leaf);
    }

    /**
     * 
     * @param node
     * @return
     */
    public int SsTreeNodeCount(TreeNodeModel node) {
        int result = 0;
        result += 1;
        for (int i = 0; i < node.getChildCount(); i++) {
            result += SsTreeNodeCount((TreeNodeModel) node.getChildAt(i));
        }
        return result;
    }

    /**
     * Method to transform a DefaultMutableTreeNode to a TreeNodeModel
     * @param node the DefaultMutableTreeNode  to change
     * @param id the id give to identify the node as a String
     * @param depth 
     * @param row 
     * @return a TreeNodeModel node with an id and a content
     */
    public TreeNodeModel transformNode(DefaultMutableTreeNode node, int id, int depth, int row) {
        if (node.getUserObject() == null) {
            node.setUserObject("NoName");
        }
        TreeNodeModel mynode = new TreeNodeModel(node.getUserObject(), id, depth, row);
        return mynode;
    }

    /**
     * Method to transform a DefaultTreeModel to a TreeTableModel
     * @param tree Initial tree to transform
     * @return a TreeTableModel
     */
    public TreeTableModel transformTree(DefaultTreeModel tree) {
        count = 0;
        DefaultMutableTreeNode initial_root = (DefaultMutableTreeNode) tree.getRoot();

        if (initial_root.getUserObject() == null) {
            initial_root.setUserObject("NoValue");
        }

        TreeNodeModel root = new TreeNodeModel(initial_root.getUserObject(), count, 0, count);

        int depthnode = root.getDepth() + 1;
        count++;
        for (int i = 0; i < initial_root.getChildCount(); i++) {
            count++;
            if (initial_root.getChildAt(i).isLeaf()) {
                TreeNodeModel leaf = transformNode((DefaultMutableTreeNode) initial_root.getChildAt(i), count, depthnode, count);
                root.add(leaf);
            } else {
                root.add(sstransformTree(root, (DefaultMutableTreeNode) initial_root.getChildAt(i)));
            }
        }

        TreeTableModel mytree = new TreeTableModel(root);
        return mytree;
    }

    /**
     * 
     * @param parent
     * @param node
     * @return
     */
    public TreeNodeModel sstransformTree(TreeNodeModel parent, DefaultMutableTreeNode node) {
        TreeNodeModel leaf = transformNode(node, count++, parent.getDepth() + 1,count++);

        if (!node.isLeaf()) {
            for (int i = 0; i < node.getChildCount(); i++) {
                leaf.add(sstransformTree(leaf, (DefaultMutableTreeNode) node.getChildAt(i)));
            }
        }

        return leaf;
    }

    /* ======================= OTHERS METHODS ==================================*/
    /**
     * 
     * @param list
     * @param node
     * @return
     */
    public List<UIComponent> duplicate(List<UIComponent> list, TreeNodeModel node) {
        List<UIComponent> backup = new ArrayList<UIComponent>();
        for (UIComponent tmp : list) {
            try {
                UIComponent toduplic = duplicate(tmp, node);
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
    public UIComponent duplicate(UIComponent component, TreeNodeModel node) throws InstantiationException, IllegalAccessException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent news = component.getClass().newInstance();

        Map<String, Object> mapAttributes = component.getAttributes();
        Set<String> keys = mapAttributes.keySet();

        news.getAttributes().putAll(mapAttributes);
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
            news.setId(component.getId() + "_" + node.getId() + "_" + requestMap.get("property"));

        } else {
            news.setId(component.getId() + "_" + node.getId());
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
        if (!((UITreePanel) component).isInit()) {
            for (int i = 0; i < node.getChildCount(); i++) {

                TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
                RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

                UITreeLines mytreelines = new UITreeLines();

                String id = "line_" + String.valueOf(currentNode.getId());
                mytreelines.setId(id);
                mytreelines.setNodeInstance(currentNode);

                List<UIComponent> tocopy = duplicate(list, currentNode);
                mytreelines.getChildren().addAll(tocopy);
                if (!currentNode.isLeaf()) {
                    mytreelines.setHaveTreelinesChildren(true);
                    createTreeLinesRecurs(((UITreePanel) component), mytreelines, currentNode, list);
                }
                component.getChildren().add(mytreelines);
            }
        }
    }

    /**
     * 
     * @param mytreepanel 
     * @param component
     * @param node
     * @param list
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
    public void createTreeLinesRecurs(UITreePanel mytreepanel, UIComponent component, TreeNodeModel node, List<UIComponent> list) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        UITreeLines mytreelines = null;
        TreeTableConfig config = new TreeTableConfig();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        requestMap.put("Elresolver.called", false);

        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

            mytreelines = new UITreeLines();
            String id = "line_" + String.valueOf(currentNode.getId());

            mytreelines.setId(id);
            mytreelines.setNodeInstance(currentNode);


            List<UIComponent> tocopy = duplicate(list, currentNode);
            mytreelines.getChildren().addAll(tocopy);

            if (!mytreepanel.isInit()) {
                if (node.getDepth() > config.getDEFAULT_DEPTH_VIEW()) {
                    mytreelines.setToRender(false);
                    mytreelines.setRendered(false);
                }
            }
            if (!currentNode.isLeaf()) {
                mytreelines.setHaveTreelinesChildren(true);
                createTreeLinesRecurs(mytreepanel, mytreelines, currentNode, list);
            }
            component.getChildren().add(mytreelines);
        }

    }
}

