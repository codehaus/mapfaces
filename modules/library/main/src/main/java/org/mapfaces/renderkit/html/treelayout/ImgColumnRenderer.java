package org.mapfaces.renderkit.html.treelayout;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.treelayout.UIImgColumn;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;



public class ImgColumnRenderer extends AbstractColumnRenderer{

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        UIImgColumn comp = (UIImgColumn) component;
        ResponseWriter writer = context.getResponseWriter();


        HtmlGraphicImage img = new HtmlGraphicImage();
        img.setId("img_" + comp.getId());
        /**
         * Problem with url of an HtmlGraphicImage , when the first character of the url is a slash , the compoennt
         * addd automatically a /webappname/[the url we want] so we have specified directly in the property imgData
         * of the column the url with resource.jsf
         * 
         */
        //System.out.println(ResourcePhaseListener.getURL(context,comp.getImg(), null));
        img.setUrl(comp.getImg());
        img.setStyle("cursor:pointer;");
        comp.getChildren().add(img);

        writer.startElement("center", comp);

    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException { 
        //addRequestScript(context, component, "change");
    }

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        
    }

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public boolean debug() {
        return false;
    }
}
