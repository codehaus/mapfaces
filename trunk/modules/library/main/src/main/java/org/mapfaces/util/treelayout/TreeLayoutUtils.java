package org.mapfaces.util.treelayout;

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
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.component.treelayout.UITreeColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.component.treelayout.UITreeNodeInfo;
import org.mapfaces.component.treelayout.UITreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.renderkit.html.treelayout.TreePanelRenderer;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kdelfour
 */
public class TreeLayoutUtils {

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
        TreeNodeModel treenode = new TreeNodeModel(node.getUserObject(), id, depth, row);
        return treenode;
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

        TreeTableModel treetablemodel = new TreeTableModel(root);
        return treetablemodel;
    }

    /**
     * 
     * @param parent
     * @param node
     * @return
     */
    public TreeNodeModel sstransformTree(TreeNodeModel parent, DefaultMutableTreeNode node) {
        TreeNodeModel leaf = transformNode(node, count++, parent.getDepth() + 1, count++);

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
    public UIComponent duplicate(UIComponent component, TreeNodeModel node) throws InstantiationException, IllegalAccessException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent news = component.getClass().newInstance();
        String treepanelId = Utils.getWrappedComponent(context, component, UITreePanel.class);
        UITreePanel treepanel = (UITreePanel) Utils.findComponent(context, treepanelId);

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
        String treepanelId = Utils.getWrappedComponent(context, component, UITreePanel.class);
        UITreePanel treepanel = (UITreePanel) Utils.findComponent(context, treepanelId);

        if (!((UITreePanel) component).isInit()) {
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
                    treelines.setHaveTreelinesChildren(true);
                    createTreeLinesRecurs(((UITreePanel) component), treelines, currentNode, list);
                }
                component.getChildren().add(treelines);
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
    public void createTreeLinesRecurs(UITreePanel treepanel, UIComponent component, TreeNodeModel node, List<UIComponent> list) throws IOException {
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
                treelines.setHaveTreelinesChildren(true);
                createTreeLinesRecurs(treepanel, treelines, currentNode, list);
            }
            component.getChildren().add(treelines);
        }

    }

    /**
     *
     * @param component
     * @param news
     */
    private void copyAttributes(UIComponent component, UIComponent news) {
        if (component != null) {
            if (news != null) {
                if (component instanceof UITreeColumn) {
                    ((UITreeColumn) news).setHeader(((UITreeColumn) component).getHeader());
                    ((UITreeColumn) news).setWidth(((UITreeColumn) component).getWidth());
                } else if (component instanceof UIAbstractColumn) {
                    ((UIAbstractColumn) news).setHeader(((UIAbstractColumn) component).getHeader());
                    ((UIAbstractColumn) news).setWidth(((UIAbstractColumn) component).getWidth());
                    ((UIAbstractColumn) news).setIcon(((UIAbstractColumn) component).getIcon());
                } else if (component instanceof UITreeNodeInfo) {
                    ((UITreeNodeInfo) news).setHeader(((UITreeNodeInfo) component).getHeader());
                    ((UITreeNodeInfo) news).setHide(((UITreeNodeInfo) component).getHide());
                }
            }
        }
    }
}

