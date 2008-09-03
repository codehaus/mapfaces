package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.treetable.TreeTableConfig;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author kevindelfour
 */
public abstract class AbstractColumnRenderer extends Renderer implements AjaxRendererInterface {

    private TreeTableConfig config = new TreeTableConfig();
    private static final transient Log log = LogFactory.getLog(AbstractColumnRenderer.class);

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component - 
     * @return
     */
    private static UIAbstractTreePanel getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIAbstractTreePanel) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UIAbstractTreePanel) parent;
    }

    /**
     * 
     * @param component
     * @return
     */
    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractColumn column = (UIAbstractColumn) component;
        return column.getId() + "PostBack";
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        //Method to apply before encodeBegin
        if (debug()) {
            log.info("beforeEncodeBegin : " + AbstractColumnRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encodeBegin
        if (debug()) {
            log.info("encodeBegin : " + AbstractColumnRenderer.class.getName());
        }
        ResponseWriter writer = context.getResponseWriter();

        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();

        String size = config.getDEFAULT_SIZE_COLUMN();
        if (component.getAttributes().get("width") != null) {
            size = String.valueOf(component.getAttributes().get("width")) + "px";
        }
        System.out.println("getWidth " +((UIAbstractColumn)component).getWidth());
        System.out.println("getAttributesWidth " + component.getAttributes().get("width"));
        writer.startElement("div", component);
        writer.writeAttribute("id", "treecol:" + component.getId() + ":" + node.getId(), null);
        writer.writeAttribute("class", "x-tree-col", null);
        writer.writeAttribute("style", "width:" + size + ";", null);
        writer.startElement("div", component);
        writer.writeAttribute("style", "padding-left:5px;", null);



        //Method to apply before encodeBegin
        if (debug()) {
            log.info("afterEncodeBegin : " + AbstractColumnRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        ResponseWriter writer = context.getResponseWriter();

        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                Utils.encodeRecursive(context, tmp);
            }
        }

    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();
        ResponseWriter writer = context.getResponseWriter();

        if (debug()) {
            log.info("beforeEncodeEnd : " + AbstractColumnRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        /*
         * Prepare informations for making any Ajax request
         */
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        AjaxUtils ajaxtools = new AjaxUtils();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY(), "'+value+'");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_TARGET_ID_KEY(), "'+target+'");
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
        String Request = ajaxtools.getRequestJs("get", AJAX_SERVER, AJAX_PARAMETERS);

        writer.startElement("script", component);

        writer.write("document.getElementById('treecol:" + component.getId() + ":" + node.getId() + "').addEvent('keypress', function(event){" +
                "if(event.key=='enter'){" +
                "value = event.target.value;" +
                "target = event.target.name;" +
                "viewstate = document.getElementById('javax.faces.ViewState').value;" +
                Request +
                "}" +
                "});");
        writer.endElement("script");

        writer.endElement("div");
        writer.endElement("div");

        if (debug()) {
            log.info("afterEncodeEnd : " + AbstractColumnRenderer.class.getName());
        }
        afterEncodeEnd(context, component);
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component.getParent();
        Object userObject = treeline.getNodeInstance().getUserObject();
        AjaxUtils ajaxtools = new AjaxUtils();
        TreeNodeModel node = treeline.getNodeInstance();
        Object item = node.getUserObject();

        boolean haveBeenResolved = false;

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String new_value = request.getParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY());
        String targetId = request.getParameter(ajaxtools.getAJAX_TARGET_ID_KEY());

        String[] targetNameSplit = component.getId().split("_");
        String targetProperty = targetNameSplit[targetNameSplit.length - 1];
        String propName = StringUtils.capitalize(targetProperty.toString());
        Method methode = getMethod(userObject, propName);

        // Search in base class methods the getter correspond to the attribut
        if (methode != null) {
            try {
                methode.invoke(userObject, new_value);
                methode.invoke(item, new_value);
                treeline.getNodeInstance().setUserObject(userObject);
                haveBeenResolved = true;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        HttpServletResponse response = null;
        try {
            response = (HttpServletResponse) context.getExternalContext().getResponse();
            StringBuffer sb = new StringBuffer();
            response.setContentType("text/xml;charset=UTF-8");
            // need to set no cache or IE will not make future requests when same URL used.
            response.setHeader("Pragma", "No-Cache");
            response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
            response.setDateHeader("Expires", 1);
            sb.append("<response>");
            if (haveBeenResolved) {
                sb.append("OK");
            } else {
                sb.append("FAILED");
            }
            sb.append("</response>");
            response.getWriter().write(sb.toString());
            System.out.println("Response : " + sb.toString());
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    /**
     *    Fonction to  factorize the code of a column rendereer 
     * 
     * 
     */
    public HttpServletResponse createResponse(FacesContext context, boolean haveBeenResolved) throws IOException {

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        StringBuffer sb = new StringBuffer();
        response.setContentType("text/xml;charset=UTF-8");
        // need to set no cache or IE will not make future requests when same URL used.
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setDateHeader("Expires", 1);
        sb.append("<response>");
        if (haveBeenResolved) {
            sb.append("OK");
        } else {
            sb.append("FAILED");
        }
        sb.append("</response>");
        response.getWriter().write(sb.toString());
        System.out.println("Response : " + sb.toString());
        return response;
    }

    /**
     * 	Research getter method corresponding to attribute property
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return if the getter method exist, return this method else return null 
     */
    public Method getMethod(Object base, Object property) {
        // Fisrt capitalize PropName
        String propName = StringUtils.capitalize(property.toString());
        Class Classe = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (Method method : Classe.getMethods()) {
            if (method.getName().equals("set" + propName)) {
                return method;
            }
        }
        return null;
    }

    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        /*
         * Prepare informations for making any Ajax request
         */
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        AjaxUtils ajaxtools = new AjaxUtils();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY(), "'+value+'");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_TARGET_ID_KEY(), "'+target+'");
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
        String Request = ajaxtools.getRequestJs("get", AJAX_SERVER, AJAX_PARAMETERS);

        writer.startElement("script", component);
        writer.write(
                "document.getElementById('" + component.getClientId(context) + "').addEvent('" + event + "', function(event){" +
                addBeforeRequestScript(context, component) +
                "value = event.target.value;" +
                "target = event.target.name;" +
                "viewstate = document.getElementById('javax.faces.ViewState').value;" +
                Request +
                addAfterRequestScript(context, component) +
                "});");
        writer.endElement("script");
    }
    

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException;

    public abstract String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException;

    public abstract boolean debug();
       /**
     * Extra fonction useful for layercontrol columns
     * 
     * 
     * @param context
     * @param comp
     * @return
     */
    public String getVarId(FacesContext context, UIAbstractColumn comp) {
        if (((UIAbstractTreeLines) (comp.getParent())).getNodeInstance().isLeaf()) {
            ((UIAbstractTreeLines) (comp.getParent())).setVarId(((Layer) (((UIAbstractTreeLines) (comp.getParent())).getNodeInstance().getUserObject())).getId());
            if (((UIAbstractTreeLines) (comp.getParent())).getVarId() == null) {
                throw new NullPointerException("Var id is null so we can't update the context doc");
            }
            return FacesUtils.getFormId(context, comp) + ":" + ((UIAbstractTreeLines) (comp.getParent())).getVarId();
        }
        return null;
    }
}
