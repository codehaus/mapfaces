package org.mapfaces.renderkit.html.tree;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;

public class ColumnRenderer extends AbstractColumnRenderer {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
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
