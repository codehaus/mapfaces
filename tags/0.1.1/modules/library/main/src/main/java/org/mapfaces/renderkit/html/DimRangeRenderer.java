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
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;

import org.mapfaces.component.UIDimRange;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;

public class DimRangeRenderer extends WidgetBaseRenderer {

    private static final String HANDLE_SLIDER_IMG = "/org/mapfaces/resources/img/slider-handle.png";
    private static final String TRACK_SLIDER_IMG = "/org/mapfaces/resources/img/slider-track.png";

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIDimRange comp = (UIDimRange) component;
        final String clientId = comp.getClientId(context);

        //skip if UserValueDimRange is null or empty string
        if(getVarId(context, comp) == null || comp.getValue() ==null || ((String) comp.getValue()).equals("") || ((String) comp.getValue()).equals(".")) return;

        writer.startElement("div", comp);
        writer.writeAttribute("id",clientId,"id");

        final String layerId=getVarId(context, comp).split(":")[1];

        if (getStyleClass() == null)  writer.writeAttribute("class","mfDimRange","styleclass");
        if (getStyle() != null)       writer.writeAttribute("style",getStyle(),"style");

        writer.startElement("div",comp);
        writer.writeAttribute("id","track"+layerId, "id");
        writer.writeAttribute("style","width: 256px; background-image: url('"+ResourcePhaseListener.getURL(context,TRACK_SLIDER_IMG, null)+"');",getStyle());
        writer.writeAttribute("class","track","class");
            writer.startElement("div",comp);
            writer.writeAttribute("id","handle1"+layerId, "id");
            writer.writeAttribute("style","margin-top:-15px;position: absolute; top: 0pt; left: 60px; width: 20px; height: 19px; ;cursor:move;",getStyle());
            writer.writeAttribute("class","","class");
                writer.startElement("img",comp);
                writer.writeAttribute("src",ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null), "src");
                writer.writeAttribute("class","imgHandle","class");
                writer.endElement("img");
            writer.endElement("div");
            writer.startElement("div",comp);
            writer.writeAttribute("id","handle2"+layerId, "id");
            writer.writeAttribute("style","margin-top:-15px;position: absolute; top: 0pt; left: 150px; width: 20px; height: 19px; cursor:move;",getStyle());
            writer.writeAttribute("class","selected","class");
                writer.startElement("img",comp);
                writer.writeAttribute("src",ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null), "src");
                writer.writeAttribute("class","imgHandle","class");
                writer.endElement("img");
            writer.endElement("div");
        writer.endElement("div");
        writer.startElement("div",comp);
        writer.writeAttribute("style","position:relative;height:50px;margin-top:50px;","style");

        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UIDimRange comp = (UIDimRange) component;

        if(getVarId(context, comp) == null || comp.getValue() ==null || ((String) comp.getValue()).equals("") || ((String) comp.getValue()).equals(".")) return;


        final String layerId=getVarId(context, comp).split(":")[1];

        writer.startElement("div",comp);
        writer.writeAttribute("id",layerId+"_maxRange", "id");
        writer.writeAttribute("class","maxRange range","class");
             writer.startElement("div",comp);
             writer.writeAttribute("class","textRange","class");
                 writer.write("Max : ");
             writer.endElement("div");
             final UIInput maxRange = new UIInput();
             maxRange.setId(layerId+"_inputMaxRange");             
             maxRange.setValue(((String) comp.getValue()).split(",")[1]);
             comp.getChildren().add(maxRange);
             maxRange.encodeAll(context);
        writer.endElement("div");
        writer.startElement("div",comp);
        writer.writeAttribute("id",layerId+"_minRange", "id");
        writer.writeAttribute("class","minRange range","class");
             writer.startElement("div",comp);
             writer.writeAttribute("class","textRange","class");
                 writer.write("Min : ");
             writer.endElement("div");
             final UIInput minRange = new UIInput();
             minRange.setId(layerId+"_inputMinRange");
             minRange.setValue(((String) comp.getValue()).split(",")[0]);
             comp.getChildren().add(minRange);
             minRange.encodeAll(context);
        writer.endElement("div");
        writer.startElement("div",comp);
        writer.writeAttribute("id",layerId+"_DimRange", "id");
        writer.writeAttribute("style","display:none;","style");
             final UIInput dimRange = new UIInput();
             dimRange.setId(layerId+"_inputDimRange");
             dimRange.setValue(((String) comp.getValue()));
             comp.getChildren().add(dimRange);
             dimRange.getChildren().add(FacesUtils.createTreeAjaxSupport(context,dimRange,"onchange",getVarId(context, comp),null));
             dimRange.encodeBegin(context);
             dimRange.encodeChildren(context);
             dimRange.encodeEnd(context);
        writer.endElement("div");
        writer.startElement("div",comp);
        writer.writeAttribute("id",layerId+"_colorRamp", "id");
        writer.writeAttribute("class","colorRamp","class");
             final HtmlGraphicImage legend = new HtmlGraphicImage();
             legend.setUrl(getVarLegendUrl(context, comp));
             comp.getChildren().add(legend);
             legend.encodeAll(context);
        writer.endElement("div");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        final UIDimRange comp = (UIDimRange) component;

        if(getVarId(context, comp) == null || comp.getValue() ==null || ((String) comp.getValue()).equals("") || ((String) comp.getValue()).equals(".")) return;

        writer.endElement("div");
        writer.startElement("script", comp);
        writer.writeAttribute("type","text/javascript","text/javascript");
        final String layerClientId = getVarId(context, comp);
        final String layerId       = layerClientId.split(":")[1];
        //suppression des ":" pour nommer l'objet javascript correspondant correctement
        String jsObject = FacesUtils.getParentUIModelBase(context, component).getClientId(context);
        if(jsObject.contains(":")) jsObject = jsObject.replace(":","");

        final String value = comp.getValue().toString();
        writer.write(new StringBuilder("  ")
            .append("if(!window.sliders)window.sliders={};\n")
            .append("	var min=").append(value.split(",")[0]).append(";\n")
            .append("	var max=").append(value.split(",")[1]).append(";\n")
            .append("   var namesLayers='").append(layerId).append("';\n")
            .append("	window.sliders['").append(layerId).append("']=new Control.Slider( ['handle1").append(layerId).append("','handle2").append(layerId).append("'],'track").append(layerId).append("',\n")
            .append("	{\n")
            .append("			sliderValue:[min, max],\n")
            .append("			increment:5,\n")
            .append("			range:$R(min,max),\n")
            .append("			restricted:true,\n")
            .append("			layer:'").append(layerId).append("',\n")
            .append("			onSlide:function(v){\n")
            .append("                                           $('").append(layerClientId).append("_inputMinRange').value=parseFloat(v[0]);\n")
            .append("					        $('").append(layerClientId).append("_inputMaxRange').value=parseFloat(v[1]);\n")
            .append("                                           $('").append(layerClientId).append("_inputDimRange').value=parseFloat(v[0])+','+parseFloat(v[1]);")
            .append("                                           },")
            .append("                   onChange:function(v){$('").append(layerClientId).append("_inputDimRange').onchange()}\n")
            .append("	});\n").toString()
        );
        writer.endElement("script");
        writer.endElement("div");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Extra fonction useful for layercontrol columns
     */
    public String getVarId(final FacesContext context, final UIWidgetBase component) {
        final UITreeLinesBase comp = FacesUtils.getParentUITreeLines(context, component);
        if (comp.getNodeInstance().isLeaf()) {
            comp.setVarId( ((TreeItem) (comp.getNodeInstance().getUserObject())).getCompId() );
            if (comp.getVarId() == null) {
                throw new NullPointerException("Var id is null so we can't update the context doc");
            }
            return comp.getVarId();
        }
        return null;
    }

    /**
     * Extra fonction useful for layercontrol columns
     */
    public String getVarLegendUrl(final FacesContext context, final UIWidgetBase component) {
        final UITreeLines comp = FacesUtils.getParentUITreeLines(context, component);
        if (comp.getNodeInstance().isLeaf()) {
            comp.setVarId(((TreeItem)(comp.getNodeInstance().getUserObject())).getId());
            if ( comp.getVarId() == null) {
                throw new NullPointerException("Var id is null so we can't update the context doc");
            }
            return   ((TreeItem)(comp.getNodeInstance().getUserObject())).getLegendUrl();
        }
        return null;
    }
}
