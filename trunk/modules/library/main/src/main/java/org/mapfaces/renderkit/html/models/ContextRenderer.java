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

package org.mapfaces.renderkit.html.models;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ContextRenderer extends Renderer {

    private final String WIDGET_CSS = "/org/mapfaces/resources/css/widget.css";
    private final String OPENLAYERS_JS = "/org/mapfaces/resources/openlayers/custom/OpenLayers.js";
    private final String MOOTOOLS_JS = "/org/mapfaces/resources/js/mootools.1.2.js";    
    private final String PROTOTYPE_JS = "/org/mapfaces/resources/scriptaculous/lib/prototype.js";
    private final String SCRIPTACULOUS_JS = "/org/mapfaces/resources/scriptaculous/src/scriptaculous.js";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        UIContext comp = (UIContext) component;
        try {
            if (comp.isDebug()) {
                System.out.println("ContextRenderer encodeBegin");
            }
            
            assertValid(context, component);
            ResponseWriter writer = context.getResponseWriter();
            ServletContext sc = (ServletContext) context.getExternalContext().getContext();
            
            //Add the context path varaible to load openlayers with the good url , see  custom/OpenLayers.js
            /*writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.write("window.contextpath=\""+sc.getContextPath()+"\"");
            writer.endElement("script");*/

            //Add MapFaces css
            writer.startElement("link", component);
            writer.writeAttribute("rel", "stylesheet", "rel");
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, WIDGET_CSS, null), null);
            writer.writeAttribute("type", "text/css", null);

            //Add OpenLayers css
            /*writer.endElement("link");
            writer.startElement("link", component);
            writer.writeAttribute("rel","stylesheet","rel");
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/openlayers/theme/default/style.css", null), null);
            writer.writeAttribute("type", "text/css", null);
            writer.endElement("link");*/

                writer.startElement("script", component);
                writer.writeAttribute("type", "text/javascript", null);
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_JS, null), null);
                writer.endElement("script");
            
            
           if (comp.isScriptaculous()) {
               //Add Prototype script
                writer.startElement("script", component);
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, PROTOTYPE_JS, null), null);
                writer.writeAttribute("type", "text/javascript", null);
                writer.endElement("script");

                //Add Scriptaculous scripts
                writer.startElement("script", component);
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, SCRIPTACULOUS_JS, null), null);
                writer.writeAttribute("type", "text/javascript", null);
                writer.endElement("script");
            }
            
            //Add OpenLayers scripts
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_JS, null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");

            //Loading the context file if the model is null.
            if (comp.getModel() == null) {
                String fileUrl = (String) component.getAttributes().get("service");
                if (fileUrl == null || fileUrl.length() < 1) {
                    throw new IllegalArgumentException("You must indicate a path to file to read");
                }
                // Unmarshalles the given XML file to objects
                JAXBContext Jcontext;
                Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");
                Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
                JAXBElement elt = (JAXBElement) unmarshaller.unmarshal(new FileReader(sc.getRealPath(fileUrl)));
                comp.setJAXBElt(elt);                
            } else {
                if (comp.isDebug()) {
                    System.out.println("AbstractContext already exist");
                }
            }

            /* Add a4j:support component */
            
            HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
            ajaxComp.setId(comp.getId()+"_Ajax");
            ajaxComp.setAjaxSingle(true);
            ajaxComp.setImmediate(true);
            
            if (FacesUtils.findComponentById(context, component, ajaxComp.getId()) == null) {
                comp.getChildren().add(ajaxComp);
                comp.setAjaxCompId(ajaxComp.getClientId(context));
            }
            

        } catch (JAXBException ex) {
            Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer  children");
        }
        List<UIComponent> childrens = component.getChildren();
        for (UIComponent tmp : childrens) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel(comp.getModel());
            }
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public boolean getRendersChildren() {

        return true;
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer encodeEnd");
        }
        ResponseWriter writer = context.getResponseWriter();

        writer.flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer decode");
        }
        if (context.getExternalContext().getRequestParameterMap() != null) {
            AbstractContext tmp = (AbstractContext) comp.getModel();

            //tmp.setI
            Map params = context.getExternalContext().getRequestParameterMap();
            String title = (String) params.get(FacesUtils.getFormId(context, component) + ":title");
            if (title != null && !title.equals(tmp.getTitle())) {
                tmp.setTitle(title);
            }
            comp.setModel(tmp);
            if (comp.isDebug()) {
                System.out.println("    Nouveaux parametres du context : " + tmp.getTitle() + " " + tmp.getMinx() + " " + tmp.getMiny().toString() + " " + tmp.getMaxx() + " " + tmp.getMaxy() + "");
            }
        }
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}