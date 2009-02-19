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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.models.Context;
import org.mapfaces.util.XMLContextUtilities;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ContextRenderer extends Renderer {

    private static final String WIDGET_CSS              = "/org/mapfaces/resources/css/widget.css";
    private static final String OPENLAYERS_JS           = "/org/mapfaces/resources/openlayers/custom/OpenLayers.js";
    private static final String OPENLAYERS_MINIFY_JS    = "/org/mapfaces/resources/openlayers/custom/OpenLayersLite.js";
    private static final String MOOTOOLS_JS             = "/org/mapfaces/resources/js/mootools-1.2-loading.js";
    private static final String PROTOTYPE_JS            = "/org/mapfaces/resources/scriptaculous/lib/prototype.js";
    private static final String SCRIPTACULOUS_JS        = "/org/mapfaces/resources/scriptaculous/src/scriptaculous.js";
    private static final String SCRIPTACULOUS_MINIFY_JS = "/org/mapfaces/resources/scriptaculous/src/scriptaculous.js";
    

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UIContext comp = (UIContext) component;

        if (comp.isDebug()) System.out.println("ContextRenderer encodeBegin");


        assertValid(context, component);
        final ResponseWriter writer = context.getResponseWriter();
        final ServletContext sc     = (ServletContext) context.getExternalContext().getContext();

        //Add the context path variable to load openlayers with the good url , see  custom/OpenLayers.js
        /*writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.write("window.contextpath=\""+sc.getContextPath()+"\"");
        writer.endElement("script");*/

        //Write the resources files once per page
        boolean resourcesFlag = false;
        final ExternalContext extContext = context.getExternalContext();        
        if (!extContext.getRequestMap().containsKey("mapfacesFlag.resourcesContext")) {
            extContext.getRequestMap().put("mapfacesFlag.resourcesContext", Boolean.TRUE);
            resourcesFlag = true;
        }

        //Add MapFaces css
        if (resourcesFlag) {
            writer.startElement("link", component);
            writer.writeAttribute("rel", "stylesheet", "rel");
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, WIDGET_CSS, null), null);
            writer.writeAttribute("type", "text/css", null);
            writer.endElement("link");
        }

        if (comp.isMootools() && resourcesFlag) {
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_JS, null), null);
            writer.endElement("script");
        }

        final boolean isMinifyJS = comp.isMinifyJS();

        if (comp.isScriptaculous() && resourcesFlag) {

            //Add Prototype script
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, PROTOTYPE_JS, null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");

            //Add Scriptaculous scripts
            if (isMinifyJS) {
                writer.startElement("script", component);
                writer.writeAttribute("type", "text/javascript", null);
                writer.write("window.SCRIPTACULOUS_SINGLE_FILE = true;");
                writer.endElement("script");
            }
            writer.startElement("script", component);
            if (isMinifyJS) {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, SCRIPTACULOUS_MINIFY_JS, null), null);
            } else {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, SCRIPTACULOUS_JS, null), null);
            }
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
        }

        //Add OpenLayers scripts
//        if (isMinifyJS && resourcesFlag) {
//            writer.startElement("script", component);
//            writer.writeAttribute("type", "text/javascript", null);
//            writer.write("var OpenLayers = { singleFile: true };");
//            writer.endElement("script");
//        }
        
        if (resourcesFlag) {
            writer.startElement("script", component);
            if (isMinifyJS) {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_MINIFY_JS, null), null);
            } else {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_JS, null), null);
            }
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
        }


        //Loading the context file if the model is null.
        if (comp.getModel() == null) {
            Context ctx = null;
            String fileUrl = (String) component.getAttributes().get("service");

            ValueExpression ve = comp.getValueExpression("service");
            if (ve != null) {
                if (ve.getValue(context.getELContext()) instanceof String) {
                    fileUrl = (String) ve.getValue(context.getELContext());
                }
            }
            
            
            if (fileUrl == null || fileUrl.length() < 1) {
                throw new IllegalArgumentException("You must indicate a path to file to read");
            }
            if (fileUrl.contains("http:/")) {
                try {
                    ctx = (new XMLContextUtilities()).readContext(new URL(fileUrl));
                } catch (JAXBException ex) {
                    Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (fileUrl.startsWith("file://.sicade")) {

                    File mapcontextFile = new File(System.getProperty("user.home"));
                    System.out.println("[Try to load the mapcontext from a file] user home = "+System.getProperty("user.home")+"  system = "+System.getProperty("os.name", ""));
                    if (System.getProperty("os.name", "").startsWith("Windows")) {
                        mapcontextFile = new File(mapcontextFile, "Application Data\\Sicade");
                    } else {
                        mapcontextFile = new File(mapcontextFile, ".sicade");
                    }
                    String[] tabString = fileUrl.replaceFirst("file://.sicade/", "").split("/");
                    for (String s : tabString) {
                        mapcontextFile = new File(mapcontextFile, s);
                    }
                    System.out.println("[Loading Mapcontext file] path = "+mapcontextFile);
                    try {
                        ctx = (new XMLContextUtilities()).readContext(new FileReader(mapcontextFile));
                    } catch (JAXBException ex) {
                        Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        ctx = (new XMLContextUtilities()).readContext(new FileReader(new File(sc.getRealPath(fileUrl))));
                    } catch (JAXBException ex) {
                        Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            comp.setModel((AbstractModelBase) ctx);
        } else {
            if (comp.isDebug()) {
                System.out.println("Context already exist");
            }
        }

        /* Add a4j:support component */

        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(comp.getId() + "_Ajax");
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setImmediate(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setReRender(comp.getId());
        if (FacesUtils.findComponentById(context, component, ajaxComp.getId()) == null) {
            comp.getChildren().add(ajaxComp);            
            comp.setAjaxCompId(ajaxComp.getClientId(context));
        }
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer  children");
        }
        for (final UIComponent tmp : component.getChildren()) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel(comp.getModel());
            }
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {

        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer encodeEnd");
        }
        context.getResponseWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UIContext comp = (UIContext) component;
        if (comp.isDebug()) {
            System.out.println("ContextRenderer decode");
        }
        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Context tmp = (Context) comp.getModel();

            final Map params = context.getExternalContext().getRequestParameterMap();
            final String title = (String) params.get(FacesUtils.getFormId(context, component) + ":title");
            if (!comp.getId().contains("Locator") && params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("save")) {
                final ServletContext servletCtx = (ServletContext) context.getExternalContext().getContext();
                if (!params.get("org.mapfaces.ajax.ACTION_SAVE_FILENAME").equals("null")) {
                    tmp.save(servletCtx, (String) params.get("org.mapfaces.ajax.ACTION_SAVE_FILENAME"));
                } else {
                    tmp.save(servletCtx, null);
                }
            }
            if (title != null && !title.equals(tmp.getTitle())) {
                tmp.setTitle(title);
            }
            comp.setModel((AbstractModelBase) tmp);
            if (comp.isDebug()) {
                System.out.println("    Nouveaux parametres du context : " + tmp.getTitle() + " " + tmp.getMinx() + " " + tmp.getMiny().toString() + " " + tmp.getMaxx() + " " + tmp.getMaxy() + "");
            }
        }
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null)   throw new NullPointerException("context should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }
}