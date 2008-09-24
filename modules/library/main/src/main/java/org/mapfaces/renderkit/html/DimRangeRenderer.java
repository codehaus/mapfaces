/*
 * CursorTrackRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIGraphic;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.UIDimRange;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;

public class DimRangeRenderer extends WidgetBaseRenderer {
    
    private static String HANDLE_SLIDER_IMG = "/org/mapfaces/resources/img/slider-handle.png";
    private static String TRACK_SLIDER_IMG = "/org/mapfaces/resources/img/slider-track.png";
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);     
        UIDimRange comp = (UIDimRange) component;
        String clientId= comp.getClientId(context);
        
    if(getVarId(context, comp) != null && comp.getValue() !=null && !((String) comp.getValue()).equals(".")){        
        
        writer.startElement("div", comp);        
        writer.writeAttribute("id",clientId,"id");
        String layerId=getVarId(context, comp).split(":")[1];
        if (styleClass == null)
            writer.writeAttribute("class","mfDimRange","styleclass");
        
        if (style != null)
            writer.writeAttribute("style",style,"style");
        writer.startElement("div",comp);
        writer.writeAttribute("id","track"+layerId, "id");
        writer.writeAttribute("style","width: 256px; background-image: url('"+ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null)+"');",style);
        writer.writeAttribute("class","track","class");
            writer.startElement("div",comp);
            writer.writeAttribute("id","handle1"+layerId, "id");
            writer.writeAttribute("style","margin-top:-15px;position: absolute; top: 0pt; left: 60px; width: 20px; height: 19px; ;cursor:move;",style);
            writer.writeAttribute("class","","class");    
                writer.startElement("img",comp);
                writer.writeAttribute("src",ResourcePhaseListener.getURL(context,HANDLE_SLIDER_IMG, null), "src");
                writer.writeAttribute("class","imgHandle","class");
                writer.endElement("img");
            writer.endElement("div");
            writer.startElement("div",comp);
            writer.writeAttribute("id","handle2"+layerId, "id");
            writer.writeAttribute("style","margin-top:-15px;position: absolute; top: 0pt; left: 150px; width: 20px; height: 19px; cursor:move;",style);
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
    }
     @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
         
        UIDimRange comp = (UIDimRange) component;      
        if(getVarId(context, comp) != null && comp.getValue() !=null && !((String) comp.getValue()).equals(".")){            
            String layerId=getVarId(context, comp).split(":")[1];
            writer.startElement("div",comp);
            writer.writeAttribute("id",layerId+"_maxRange", "id");
            writer.writeAttribute("class","maxRange range","class");
                 writer.write("Max : ");
                 UIInput maxRange = new UIInput();
                 maxRange.setId(layerId+"_inputMaxRange");
                 maxRange.setValue(((String) comp.getValue()).split(",")[1]);
                 comp.getChildren().add(maxRange);                 
                 maxRange.encodeAll(context);
            writer.endElement("div");
            writer.startElement("div",comp);
            writer.writeAttribute("id",layerId+"_minRange", "id");
            writer.writeAttribute("class","minRange range","class");
                 writer.write("Min : ");
                 UIInput minRange = new UIInput();
                 minRange.setId(layerId+"_inputMinRange");
                 minRange.setValue(((String) comp.getValue()).split(",")[0]);
                 comp.getChildren().add(minRange);
                 minRange.encodeAll(context);
            writer.endElement("div");
            writer.startElement("div",comp);
            writer.writeAttribute("id",layerId+"_DimRange", "id");
            writer.writeAttribute("style","display:none;","style");
                 UIInput dimRange = new UIInput();
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
                 HtmlGraphicImage legend = new HtmlGraphicImage();
                 legend.setUrl(getVarLegendUrl(context, comp));
                 comp.getChildren().add(legend);
                 legend.encodeAll(context);
            writer.endElement("div");
        }
    }     
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        UIDimRange comp = (UIDimRange) component;      
        if(getVarId(context, comp) != null && comp.getValue() !=null && !((String) comp.getValue()).equals(".")){   
            writer.endElement("div");

            writer.startElement("script", comp);
            writer.writeAttribute("type","text/javascript","text/javascript");
            String layerClientId=getVarId(context, comp);
            String layerId=layerClientId.split(":")[1];
            //suppression des ":" pour nommer l'objet javascript correspondant correctement      
            String jsObject = FacesUtils.getParentUIModelBase(context, component).getClientId(context);        
            if(jsObject.contains(":"))                 
                jsObject = jsObject.replace(":","");        
                writer.write("  " +
                 "if(!window.sliders)window.sliders={};\n" +
                "	var min="+((String) comp.getValue()).split(",")[0]+";\n" +
                "	var max="+((String) comp.getValue()).split(",")[1]+";\n" +
                        "var namesLayers='"+layerId+"';\n" +
                    "	window.sliders['"+layerId+"']=new Control.Slider( ['handle1"+layerId+"','handle2"+layerId+"'],'track"+layerId+"',\n" +
                    "	{\n" +
                    "			sliderValue:[min, max],\n" +
                    "			increment:5,\n" +
                    "			range:$R(min,max),\n" +
                    "			restricted:true,\n" +
                    "			layer:'"+layerId+"',\n" +
                    "			onSlide:function(v){\n" +
                    "                                           $('"+layerClientId+"_inputMinRange').value=parseFloat(v[0]);\n" +
                    "					        $('"+layerClientId+"_inputMaxRange').value=parseFloat(v[1]);\n" +
                    "                                           $('"+layerClientId+"_inputDimRange').value=parseFloat(v[0])+','+parseFloat(v[1]);" +
                    "                                           }," +
                    "                   onChange:function(v){$('"+layerClientId+"_inputDimRange').onchange()}\n" +
                    "	});\n" +

              "");
            writer.endElement("script");
            writer.endElement("div");
        }
    }
    public void encodeChildren(){
        
    }
    @Override
    public boolean getRendersChildren() {   
        return true;
    }
     /**
     * Extra fonction useful for layercontrol columns
     * 
     * 
     * @param context
     * @param comp
     * @return
     */
    public String getVarId(FacesContext context, UIWidgetBase component) {
        UITreeLines comp = FacesUtils.getParentUITreeLines(context, component);
        if (((UIAbstractTreeLines) (comp)).getNodeInstance().isLeaf()) {
            ((UIAbstractTreeLines) (comp)).setVarId(((Layer) (((UIAbstractTreeLines) (comp)).getNodeInstance().getUserObject())).getId());
            if (((UIAbstractTreeLines) (comp)).getVarId() == null) {
                throw new NullPointerException("Var id is null so we can't update the context doc");
            }
            return FacesUtils.getFormId(context, comp) + ":" + ((UIAbstractTreeLines) (comp)).getVarId();
        }
        return null;
    }
      /**
     * Extra fonction useful for layercontrol columns
     * 
     * 
     * @param context
     * @param comp
     * @return
     */
    public String getVarLegendUrl(FacesContext context, UIWidgetBase component) {
        UITreeLines comp = FacesUtils.getParentUITreeLines(context, component);
        if (comp.getNodeInstance().isLeaf()) {
            comp.setVarId(((Layer)(comp.getNodeInstance().getUserObject())).getId());
            if ( comp.getVarId() == null) {
                throw new NullPointerException("Var id is null so we can't update the context doc");
            }
            return   ((Layer)(comp.getNodeInstance().getUserObject())).getLegendUrl();
        }
        return null;
    }
}
