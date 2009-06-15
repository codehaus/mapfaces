/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;
import java.util.Map;
import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.component.treelayout.UICheckColumn;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author Kevin Delfour
 * @author Mehdi Sidhoum (Geomatys)
 * 
 */
public class CheckColumnRenderer extends AbstractColumnRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UICheckColumn comp = (UICheckColumn) component;
        final ResponseWriter writer = context.getResponseWriter();
        final HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();

        checkbox.setId("check_" + comp.getId());
        checkbox.setValue(comp.getValue());

        String styleClass = (comp.getStyleClass() != null) ? comp.getStyleClass() + "_check" : comp.getId() + "Class";
        checkbox.setStyleClass(styleClass);
        comp.getChildren().add(checkbox);
        writer.startElement("center", component);
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if (component instanceof UICheckColumn) {
            final UICheckColumn comp = (UICheckColumn) component;
            if (comp.getParent() instanceof UITreeLines) {
                final UITreeLines treeLine = (UITreeLines) comp.getParent();
                final ExternalContext ext = context.getExternalContext();
                final ELContext elContext = context.getELContext();
                final Map parameterMap = ext.getRequestParameterMap();
                final UIForm formContainer = Utils.getForm(component);
                String keyParameterInput = formContainer.getId() + ":check_" + comp.getId();
                String newValue = (String) parameterMap.get(keyParameterInput);
                boolean booleanValue = false;
                if (newValue != null && newValue.equals("on")) {
                    booleanValue = true;
                } else {
                    booleanValue = false;
                }

                String expression = "";
                if (comp.getValueExpression("value") != null) {
                    expression = comp.getValueExpression("value").getExpressionString();
                    String tmp = expression.substring(expression.lastIndexOf("."));
                    String property = tmp.substring(1, tmp.lastIndexOf("}"));
                    Object userObject = null;
                    if (treeLine.getNodeInstance() != null && treeLine.getNodeInstance().getUserObject() instanceof TreeItem) {
                        TreeItem ti = (TreeItem) treeLine.getNodeInstance().getUserObject();
                        userObject = ti.getUserObject();
                    }
                    if (userObject != null) {
                        elContext.getELResolver().setValue(elContext, userObject, property, booleanValue);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
}
