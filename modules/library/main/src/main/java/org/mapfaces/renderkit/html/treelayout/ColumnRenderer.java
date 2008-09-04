package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

import org.mapfaces.component.UIAbstract;
import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author kevindelfour
 */
public class ColumnRenderer extends AbstractColumnRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        addRequestScript(context, component, "keypress");
    }

    @Override
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

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
    
  
}
