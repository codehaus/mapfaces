package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

/**
 *
 * @author kevindelfour
 */
public class CheckColumnRenderer extends AbstractColumnRenderer{

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        UICheckColumn comp = (UICheckColumn) component;
        ResponseWriter writer = context.getResponseWriter();


        HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
        checkbox.setId("check_" + comp.getId());
        checkbox.setValue(comp.getValue());
        comp.getChildren().add(checkbox);

        writer.startElement("center", comp);

    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException { 
        addRequestScript(context, component, "change");
    }

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {

    }

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public boolean debug() {
        return false;
    }
}
