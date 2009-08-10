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
import org.mapfaces.share.utils.RendererUtils.HTML;

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

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,clientId,HTML.id_ATTRIBUTE);

        final String layerId=getVarId(context, comp).split(":")[1];

        if (getStyleClass() == null)  writer.writeAttribute(HTML.class_ATTRIBUTE,"mfDimRange","styleclass");
        if (getStyle() != null)       writer.writeAttribute(HTML.style_ATTRIBUTE,getStyle(),HTML.style_ATTRIBUTE);

        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,"track"+layerId, HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE,"width: 256px; background-image: url('"+ResourcePhaseListener.getURL(context,TRACK_SLIDER_IMG, null)+"');",getStyle());
        writer.writeAttribute(HTML.class_ATTRIBUTE,"track",HTML.class_ATTRIBUTE);
            writer.startElement(HTML.DIV_ELEM,comp);
            writer.writeAttribute(HTML.id_ATTRIBUTE,"handle1"+layerId, HTML.id_ATTRIBUTE);
            writer.writeAttribute(HTML.style_ATTRIBUTE,"margin-top:-15px;position: absolute; top: 0pt; left: 60px; width: 20px; height: 19px; ;cursor:move;",getStyle());
            writer.writeAttribute(HTML.class_ATTRIBUTE,"",HTML.class_ATTRIBUTE);
                writer.startElement(HTML.IMG_ELEM,comp);
                writer.writeAttribute(HTML.src_ATTRIBUTE,ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null), "src");
                writer.writeAttribute(HTML.class_ATTRIBUTE,"imgHandle",HTML.class_ATTRIBUTE);
                writer.endElement(HTML.IMG_ELEM);
            writer.endElement(HTML.DIV_ELEM);
            writer.startElement(HTML.DIV_ELEM,comp);
            writer.writeAttribute(HTML.id_ATTRIBUTE,"handle2"+layerId, HTML.id_ATTRIBUTE);
            writer.writeAttribute(HTML.style_ATTRIBUTE,"margin-top:-15px;position: absolute; top: 0pt; left: 150px; width: 20px; height: 19px; cursor:move;",getStyle());
            writer.writeAttribute(HTML.class_ATTRIBUTE,"selected",HTML.class_ATTRIBUTE);
                writer.startElement(HTML.IMG_ELEM,comp);
                writer.writeAttribute(HTML.src_ATTRIBUTE,ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null), "src");
                writer.writeAttribute(HTML.class_ATTRIBUTE,"imgHandle",HTML.class_ATTRIBUTE);
                writer.endElement(HTML.IMG_ELEM);
            writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.style_ATTRIBUTE,"position:relative;height:50px;margin-top:50px;",HTML.style_ATTRIBUTE);

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

        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,layerId+"_maxRange", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE,"maxRange range",HTML.class_ATTRIBUTE);
             writer.startElement(HTML.DIV_ELEM,comp);
             writer.writeAttribute(HTML.class_ATTRIBUTE,"textRange",HTML.class_ATTRIBUTE);
                 writer.write("Max : ");
             writer.endElement(HTML.DIV_ELEM);
             final UIInput maxRange = new UIInput();
             maxRange.setId(layerId+"_inputMaxRange");             
             maxRange.setValue(((String) comp.getValue()).split(",")[1]);
             comp.getChildren().add(maxRange);
             maxRange.encodeAll(context);
        writer.endElement(HTML.DIV_ELEM);
        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,layerId+"_minRange", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE,"minRange range",HTML.class_ATTRIBUTE);
             writer.startElement(HTML.DIV_ELEM,comp);
             writer.writeAttribute(HTML.class_ATTRIBUTE,"textRange",HTML.class_ATTRIBUTE);
                 writer.write("Min : ");
             writer.endElement(HTML.DIV_ELEM);
             final UIInput minRange = new UIInput();
             minRange.setId(layerId+"_inputMinRange");
             minRange.setValue(((String) comp.getValue()).split(",")[0]);
             comp.getChildren().add(minRange);
             minRange.encodeAll(context);
        writer.endElement(HTML.DIV_ELEM);
        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,layerId+"_DimRange", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE,"display:none;",HTML.style_ATTRIBUTE);
             final UIInput dimRange = new UIInput();
             dimRange.setId(layerId+"_inputDimRange");
             dimRange.setValue(((String) comp.getValue()));
             comp.getChildren().add(dimRange);
             dimRange.getFacets().put("a4jsupport", FacesUtils.createTreeAjaxSupport(context,dimRange,"onchange",getVarId(context, comp),null));
             dimRange.encodeBegin(context);
             dimRange.encodeChildren(context);
             dimRange.encodeEnd(context);
        writer.endElement(HTML.DIV_ELEM);
        writer.startElement(HTML.DIV_ELEM,comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE,layerId+"_colorRamp", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE,"colorRamp",HTML.class_ATTRIBUTE);
             final HtmlGraphicImage legend = new HtmlGraphicImage();
             legend.setUrl(getVarLegendUrl(context, comp));
             comp.getChildren().add(legend);
             legend.encodeAll(context);
        writer.endElement(HTML.DIV_ELEM);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        final UIDimRange comp = (UIDimRange) component;

        if(getVarId(context, comp) == null || comp.getValue() ==null || ((String) comp.getValue()).equals("") || ((String) comp.getValue()).equals(".")) return;

        writer.endElement(HTML.DIV_ELEM);
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR,"text/javascript","text/javascript");
        final String layerClientId = getVarId(context, comp);
        final String layerId       = layerClientId.split(":")[1];
        //suppression des ":" pour nommer l'objet javascript correspondant correctement
        String jsObject = FacesUtils.getParentUIModelBase(context, component).getClientId(context);
        if(jsObject.contains(":")) jsObject = jsObject.replace(":","");

        final String value = comp.getValue().toString();
        writer.write(new StringBuilder("  ")
            .append("if(!window.sliders)window.sliders={};")
            .append("	var min=").append(value.split(",")[0]).append(";")
            .append("	var max=").append(value.split(",")[1]).append(";")
            .append("   var namesLayers='").append(layerId).append("';")
            .append("	window.sliders['").append(layerId).append("']=new Control.Slider( ['handle1").append(layerId).append("','handle2").append(layerId).append("'],'track").append(layerId).append("',")
            .append("	{")
            .append("			sliderValue:[min, max],")
            .append("			increment:5,")
            .append("			range:$R(min,max),")
            .append("			restricted:true,")
            .append("			layer:'").append(layerId).append("',")
            .append("			onSlide:function(v){")
            .append("                                           $('").append(layerClientId).append("_inputMinRange').value=parseFloat(v[0]);")
            .append("					        $('").append(layerClientId).append("_inputMaxRange').value=parseFloat(v[1]);")
            .append("                                           $('").append(layerClientId).append("_inputDimRange').value=parseFloat(v[0])+','+parseFloat(v[1]);")
            .append("                                           },")
            .append("                   onChange:function(v){$('").append(layerClientId).append("_inputDimRange').onchange()}")
            .append("	});").toString()
        );
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.endElement(HTML.DIV_ELEM);

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
