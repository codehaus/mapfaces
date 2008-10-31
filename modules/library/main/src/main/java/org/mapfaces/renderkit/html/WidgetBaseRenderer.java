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

package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.util.FacesUtils;

/**
 * 
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class WidgetBaseRenderer extends Renderer {

    ResponseWriter writer = null;
    boolean debug;
    private String clientId ;
    private String style = null;
    private String styleClass = null;

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        writer = context.getResponseWriter();
        clientId = component.getClientId(context);   
        UIWidgetBase comp = (UIWidgetBase) component;  
        debug = comp.isDebug();
        if(debug){            
            System.out.println("    Le composant "+comp.getFamily()+" entre dans encodeBegin de widgetBaseRenderer");
        }        
        if(FacesUtils.getParentUIModelBase(context, component)==null){
            throw new NullPointerException("UIModelBase should not be null");

        } else if (comp.getModel() == null) {
            comp.setModel(FacesUtils.getParentUIModelBase(context, component).getModel());       
        }
        setStyle((String) comp.getAttributes().get("style"));

        if (getStyleClass() == null) {
            setStyleClass((String) comp.getAttributes().get("styleClass"));
        }
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        List<UIComponent> childrens = component.getChildren();
        if (isDebug()) {
            System.out.println("        Le composant " + component.getFamily() + " a " + childrens.size() + " enfants");
        }
        for (UIComponent tmp : childrens) {
            if (isDebug()) {
                System.out.println("        Famille des enfants : " + tmp.getFamily());
            }
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (isDebug()) {
            System.out.println("    Le composant " + component.getFamily() + " entre dans encodeEnd");
        }
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if (isDebug()) {
            System.out.println("    Le composant " + component.getFamily() + " entre dans decode");
        }
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    void removeChildren(FacesContext context, UIComponent component) {
        List<UIComponent> children = component.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            children.remove(i);
        }
    }

    public ResponseWriter getWriter() {
        return writer;
    }

    public void setWriter(ResponseWriter writer) {
        this.writer = writer;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
}
