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

package org.mapfaces.renderkit.html.layercontrol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import org.mapfaces.component.UILayer;
import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.layercontrol.UIVisibilityColumn;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.component.treelayout.UITreeTable;
import org.mapfaces.models.Layer;
import org.mapfaces.renderkit.html.treelayout.CheckColumnRenderer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 * 
 * @author Olivier Terral.
 */
public class VisibilityColumnRenderer extends CheckColumnRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeBegin(context, component);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (((UITreeLines) (component.getParent())).getNodeInstance().isLeaf()) {
            super.encodeEnd(context, component);
        }
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {

        /*
         * Prepare informations for making any Ajax request (TO BE FACTORIZE)
         */

        ResponseWriter writer = context.getResponseWriter();
        AjaxUtils ajaxtools = new AjaxUtils();
        String varId = getVarId(context, (UIAbstractColumn) component);
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY(), "'+value+'");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_LAYER_ID(), varId);
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
        String Request = ajaxtools.getRequestJs("post", AJAX_SERVER, AJAX_PARAMETERS);

        writer.endElement("center");

        writer.startElement("script", component);

        writer.write("document.getElementById('" + component.getChildren().get(0).getClientId(context) + "').addEvent('change', function(event){" + addBeforeRequestScript(varId));
        writer.write("value = event.target.checked;" +
                "target = event.target.name;" +
                "viewstate = document.getElementById('javax.faces.ViewState').value;" +
                Request +
                "});");
        writer.endElement("script");
    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        UIVisibilityColumn comp = (UIVisibilityColumn) component;
        AjaxUtils ajaxtools = new AjaxUtils();

        UITreeLines treelines = (UITreeLines) comp.getParent();
        Object userObject = treelines.getNodeInstance().getUserObject();
        Layer useExtend;
        String nodeId = String.valueOf(treelines.getNodeInstance().getId());

        String TreeTableId = Utils.getWrappedComponent(context, comp, UITreeTable.class);
        UITreeTable TreeTable = (UITreeTable) Utils.findComponent(context, TreeTableId);
        Object item = TreeTable.getTree().getById(Integer.valueOf(nodeId)).getUserObject();

        boolean haveBeenResolved = false;


        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        boolean new_value = Boolean.valueOf(request.getParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY()));
        String[] targetNameSplit = comp.getId().split("_");
        String targetProperty = targetNameSplit[targetNameSplit.length - 1];
        Method methode = getMethod(userObject, targetProperty);

        // Fisrt capitalize PropName
        String propName = StringUtils.capitalize(targetProperty.toString());
        Class theclass = userObject.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (Method method : theclass.getMethods()) {
            if (method.getName().equals("set" + propName)) {
                try {
                    useExtend = (Layer) userObject;
                    method.invoke(useExtend, new_value);
                    method.invoke(item, new_value);
                    treelines.getNodeInstance().setUserObject(useExtend);
                    haveBeenResolved = true;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CheckColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(CheckColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(CheckColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        try {
            HttpServletResponse response = createResponse(context, haveBeenResolved);
        } catch (IOException ex) {
            Logger.getLogger(VisibilityColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }


        UILayer uiLayer = (UILayer) Utils.findComponentById(context, context.getViewRoot(), request.getParameter(ajaxtools.getAJAX_LAYER_ID()).split(":")[1]);
        if (uiLayer == null) {
            throw new NullPointerException("Layer doesn't exist with id : " + request.getParameter(ajaxtools.getAJAX_LAYER_ID()).split(":")[1]);
        }
        uiLayer.decode(context);

    }

    private String addBeforeRequestScript(String varId) {
        return "event.target.checked?document.getElementById('" + varId + "').style.display='block':document.getElementById('" + varId + "').style.display='none';";
    }
}
