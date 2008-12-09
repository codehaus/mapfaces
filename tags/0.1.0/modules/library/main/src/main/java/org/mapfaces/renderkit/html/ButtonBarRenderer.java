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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UIButtonBar;
import org.mapfaces.component.UIMapPane;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ButtonBarRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        final UIButtonBar comp = (UIButtonBar) component;
        final String clientId  = comp.getClientId(context);

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");

        final String style = (String) comp.getAttributes().get("style");
        if (style != null) writer.writeAttribute("style",style,"style");
        else               writer.writeAttribute("style","position:absolute;z-index:1000;","style");

        final String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null) writer.writeAttribute("class",styleclass,"styleclass");
        else                    writer.writeAttribute("class","mfButtonBar","styleclass");

        writer.startElement("script", comp);
        writer.writeAttribute("type","text/javascript","text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement.
        String jsObject = null ;
        comp_loop :
        for (UIComponent comps : comp.getParent().getChildren()){
            if(comps instanceof UIMapPane){
                jsObject = comps.getClientId(context);
                break comp_loop;
            }
        }
        /*
         * @todo : Allow to specify by an attribute, the mappane component to attach NavigationHistory control
         */
        if(jsObject.contains(":"))
            jsObject = jsObject.replace(":","");

        if(comp.isHistory()){
            writer.write("var nav = new OpenLayers.Control.NavigationHistory();\n");
            writer.write(jsObject+".addControl(nav);\n");
        }
        if(comp.isZoomIn() || comp.isHistory() || comp.isZoomOut() || comp.isPan() || comp.isZoomMaxExtent()){

            final String idDivbar;
            if (comp.isFloatingBar()) idDivbar = comp.getId();
            else                      idDivbar = comp.getClientId(context);

            writer.write("var "+jsObject+comp.getId()+" = new OpenLayers.Control.NavToolbar({'div':OpenLayers.Util.getElement('"+idDivbar+"')");

            if(comp.isZoomIn())
                writer.write(",\nzoomIn: true");

            if(comp.isZoomOut())
                writer.write(",\nzoomOut: true");

            if(comp.isPan())
                writer.write(",\npan: true");

            if(comp.isZoomMaxExtent())
                writer.write(",\nzoomMaxExtent: true");

            if(comp.isHistory())
                writer.write(",\nhistory: true");

            if(comp.isGraticule())
                writer.write(",\ngraticule: true");

            if(comp.isSave())
                writer.write(",\nsave: true");

            if(comp.isPan() && comp.isPanEffect())
                writer.write(",\npanEffect: true");

            if (comp.isFeatureInfo()) {
                writer.write(",\ngetFeatureInfo: true");
            }
            if (comp.isMeasureDistance()) {
                writer.write(",\nmeasureDistance: true");
            }
            if (comp.isMeasureArea()) {
                writer.write(",\nmeasureArea: true");
            }

            writer.write("\n});\n");

        writer.write(jsObject+".addControl("+jsObject+comp.getId()+");");
        }
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
