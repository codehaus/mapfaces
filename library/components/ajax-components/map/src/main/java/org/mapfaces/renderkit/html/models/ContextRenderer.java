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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.portlet.PortletRequest;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.ajax4jsf.ajax.html.HtmlLoadStyle;
import org.geotoolkit.map.MapContext;

import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.MapContextLayer;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.util.XMLContextUtilities;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class ContextRenderer extends Renderer {

    private static final String WIDGET_CSS = "/org/mapfaces/resources/css/widget.css";
    private static final String MAPFACES_CSS = "/org/mapfaces/resources/compressed/mapfaces.css";
    private static final String OPENLAYERS_JS = "/org/mapfaces/resources/openlayers/custom/OpenLayers.js";
    private static final String OPENLAYERS_MINIFY_JS = "/org/mapfaces/resources/compressed/openlayers.min.js";
    private static final String MOOTOOLS_CORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private static final String MOOTOOLS_MORE_JS = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";
    private static final String PROTOTYPE_JS = "/org/mapfaces/resources/scriptaculous/lib/prototype.js";
    private static final String SCRIPTACULOUS_JS = "/org/mapfaces/resources/scriptaculous/src/scriptaculous.js";
    private static final String SCRIPTACULOUS_MINIFY_JS = "/org/mapfaces/resources/scriptaculous/src/scriptaculous.js";
    private static final Logger LOGGER = Logger.getLogger(ContextRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UIContext comp = (UIContext) component;
        
        if (comp.isDebug()) {
            LOGGER.log(Level.INFO, "[DEBBUG] ContextRenderer ENCODE BEGIN");
        }
        assertValid(context, component);
        final ResponseWriter writer = context.getResponseWriter();
        final ExternalContext sc = context.getExternalContext();

        //Add the context path variable to load openlayers with the good url , see  custom/OpenLayers.js
        /*writer.startElement("script", component);
        writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
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
            HtmlLoadStyle css = new HtmlLoadStyle();
            css.setSrc(ResourcePhaseListener.getLoadStyleURL(context, MAPFACES_CSS, null));
            comp.getChildren().add(css);
        }

        /*@TODO remove all Mootools reference from MapFaces */
        if (comp.isMootools() && resourcesFlag) {
            writer.startElement("script", component);
            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_CORE_JS, null), null);
            writer.endElement("script");
            writer.startElement("script", component);
            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, MOOTOOLS_MORE_JS, null), null);
            writer.endElement("script");
        }

        final boolean isMinifyJS = comp.isMinifyJS();

        /* Scriptaculous never be used anymore */
//        if (comp.isScriptaculous() && resourcesFlag) {
//
//            //Add Prototype script
//            writer.startElement("script", component);
//            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, PROTOTYPE_JS, null), null);
//            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
//            writer.endElement("script");
//
//            //Add Scriptaculous scripts
//            if (isMinifyJS) {
//                writer.startElement("script", component);
//                writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
//                writer.write("window.SCRIPTACULOUS_SINGLE_FILE = true;");
//                writer.endElement("script");
//            }
//            writer.startElement("script", component);
//            if (isMinifyJS) {
//                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, SCRIPTACULOUS_MINIFY_JS, null), null);
//            } else {
//                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, SCRIPTACULOUS_JS, null), null);
//            }
//            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
//            writer.endElement("script");
//        }

//Add OpenLayers scripts
//        if (isMinifyJS && resourcesFlag) {
//            writer.startElement("script", component);
//            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
//            writer.write("var OpenLayers = { singleFile: true };");
//            writer.endElement("script");
//        }

        if (comp.isOpenlayers() && resourcesFlag) {
            writer.startElement("script", component);

            if (isMinifyJS) {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_MINIFY_JS, null), null);
            } else {
                writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_JS, null), null);
            }
            writer.writeAttribute("type", HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.endElement("script");
        }


        //Loading the context file if the model is null.
        Context ctx = null;
        
        if (comp.getModel() == null) {
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
            String filePath = null;

            if (fileUrl.startsWith("file://")) {

                if (fileUrl.startsWith("file://.sicade")) {
                    filePath = fileUrl.replaceFirst("file://", System.getProperty("user.home"));

                    if (System.getProperty("os.name", "").startsWith("Windows")) {
                        filePath = filePath.replaceFirst(".sicade", "\\Application Data\\Sicade");

                    } else {
                        filePath = filePath.replaceFirst(".sicade", "/.sicade");
                    }

                } else {
                    // remove the "file://" prefix
                    filePath = fileUrl.replaceFirst("file://", "");
                }

                if (comp.isDebug()) {
                    LOGGER.log(Level.INFO, "[DEBUG] [Try to load mapcontext file] path = " + filePath);
                }
                
                try {
                    ctx = XMLContextUtilities.readContext(new FileReader(new File(filePath)));

                } catch (JAXBException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);

                } catch (UnsupportedEncodingException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }

            } else {
            //if fileUrl is an URL
                
                //if fileUrl is a completeURL
                if (fileUrl.startsWith("http://")) {
                    filePath = fileUrl;

                } else {
                //if fileUrl is a relative path
                    filePath = "http://" + sc.getRequestHeaderMap().get("host") + sc.getRequestContextPath();

                    if (fileUrl.startsWith("/")) {
                        filePath += fileUrl;

                    } else {
                        final String servletPath = sc.getRequestServletPath();
                        filePath += servletPath.substring(0, servletPath.lastIndexOf("/") + 1) + fileUrl;
                    }
                }

                if (comp.isDebug()) {
                    LOGGER.log(Level.INFO, "[DEBUG] [Try to load mapcontext file] url = " + filePath);
                }
                try {
                    ctx = XMLContextUtilities.readContext(new URL(filePath));

                } catch (JAXBException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);

                } catch (UnsupportedEncodingException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }

        } else {
            ctx = (Context) comp.getModel();
            if (comp.isDebug()) {
                LOGGER.log(Level.INFO, "[DEBBUG] Context already exist");
            }
        }
        Object obj = comp.getValue();
        //If a MapContext is specified in value attribute, 2 ways: 
        //- add a Layer who will display the context in a allInOne layer
        //- TODO add all the layers separetely

        final ContextFactory contextFactory = new DefaultContextFactory();

        if (ctx == null) {
            ctx = contextFactory.createDefaultContext();
        }
        
        if (obj instanceof MapContext) {
            //add all the MapContext (gt) layers  into an allInOne layer.
            MapContextLayer mcLayer = (MapContextLayer) contextFactory.createDefaultMapContextLayer(FacesMapUtils.getNewLayerIndex(ctx));
            String mapcontextKey = FacesMapUtils.getCurrentSessionId() + mcLayer.getId() + UIMapPane.MAPCONTEXT_KEY_SUFFIX;

            //putting this layer's mapcontext into session map and set key into mclayer model
            FacesMapUtils.putAtSessionMap(context, mapcontextKey, (MapContext) obj);
            mcLayer.setMapContextKeyInSession(mapcontextKey);
            ctx.addLayer(mcLayer);

        } else if (obj instanceof Context) {
            //TODO add layers at the end of the context if it exists

        } else if (obj instanceof List) {
            //add the list of feature (mapfaces) into a FeatureLayer
            List list = (List) obj;

            if (list != null && list.size() != 0 && list.get(0) instanceof Feature) {
                FeatureLayer layer = (FeatureLayer) contextFactory.createDefaultFeatureLayer(FacesMapUtils.getNewLayerIndex(ctx));
                layer.setFeatures(list);
                layer.setImage("http://localhost:8080/mf/resource/skin/default/img/europa.gif");
                layer.setGroup("features");
                layer.setRotation(20);
                layer.setSize(10);
                ctx.addLayer(layer);

            } else {
                //else if there are no features in the list but it contains Layers

                //clear the context from all WmsGetMapLayers.
                List<Layer> layerToRemove = new ArrayList<Layer>();

                for (Layer lay : ctx.getLayers()) {
                    if (lay instanceof DefaultWmsGetMapLayer) {
                        layerToRemove.add(lay);
                    }
                }

                for (Layer lay : layerToRemove) {
                    ctx.removeLayerFromId(lay.getId());
                }

                int layercount = (ctx != null && ctx.getLayers() != null) ? ctx.getLayers().size() : 0;
                layercount = layercount - FacesMapUtils.getCountWMSGetMapLayers(ctx);
                int loop = 0;

                for (Object l : list) {
                    loop++;

                    if (l instanceof DefaultWmsGetMapLayer) {
                        DefaultWmsGetMapLayer wmsLayer = (DefaultWmsGetMapLayer) l;

                        if (ctx.getLayers().contains(wmsLayer)) {
                            continue;
                        }

                        wmsLayer.setId("MapFaces_Layer_WMS_" + (layercount + loop));

                        if (!ctx.getLayersId().contains(wmsLayer.getId())) {
                            ctx.addLayer(wmsLayer);
//                            System.out.println("=========   wmsLayer getGroup = " + wmsLayer.getGroup());
//                            System.out.println("=========   wmsLayer getName = " + wmsLayer.getName());
//                            System.out.println("=========   wmsLayer server getHref = " + wmsLayer.getServer().getHref());
//                            System.out.println("=========   wmsLayer getId = " + wmsLayer.getId());
                        }

                    }
                    //@TODO add here more cases for instanceof layer.
                }
            }
        }
        comp.setModel((AbstractModelBase) ctx);
        /* Add a4j:support component */

        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(comp.getId() + "_Ajax");
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setImmediate(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setIgnoreDupResponses(true);
        //ajaxComp.setBypassUpdates(true);
        //ajaxComp.setEventsQueue("org.mapfaces.ajax.AJAX_LAYER_ID");
        ajaxComp.setRequestDelay(5);
        ajaxComp.setReRender(comp.getId());

        if (FacesMapUtils.findComponentById(context, component, ajaxComp.getId()) == null) {
            comp.getFacets().put("a4jsupport", ajaxComp);
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
            LOGGER.log(Level.INFO, "[DEBBUG] ContextRenderer ENCODE CHILDREN");
        }

        for (final UIComponent tmp : component.getChildren()) {

            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel(comp.getModel());
            }
            FacesMapUtils.encodeRecursive(context, tmp);
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
            LOGGER.log(Level.INFO, "[DEBBUG] ContextRenderer ENCODE END");
        }
        setModelAtSession(context, comp);
        context.getResponseWriter().flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UIContext comp = (UIContext) component;

        if (comp.isDebug()) {
            LOGGER.log(Level.INFO, "[DEBBUG] ContextRenderer DECODE");
        }

        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Context tmp = (Context) comp.getModel();

            final Map params = context.getExternalContext().getRequestParameterMap();
            final String title = (String) params.get(FacesMapUtils.getFormId(context, component) + ":title");
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
                LOGGER.log(Level.INFO, "[DEBBUG] ContextRenderer New context parameters : " + tmp.getTitle() + " " + tmp.getMinx() + " " + tmp.getMiny().toString() + " " + tmp.getMaxx() + " " + tmp.getMaxy() + "");
            }
        }
    }

    private void assertValid(FacesContext context, UIComponent component) {

        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
    // creates and puts the model data to session for this chart object

    public void setModelAtSession(FacesContext facesContext, UIContext comp) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String compClientId = comp.getClientId(facesContext);
        session.put("model", comp.getModel());
        
        if (comp.isDebug()) {
            LOGGER.log(Level.INFO, "[MapContextLayerRenderer] model saved in  session map for this layer,  clientId : " + compClientId + "\n");
        }
    }
}

