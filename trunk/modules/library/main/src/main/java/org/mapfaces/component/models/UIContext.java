/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.component.models;

import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.models.OWC_v030;
//import org.mapfaces.models.WMC_v110;

/**
 *
 * @author olivier
 */
public class UIContext extends UIModelBase {
    
    public static final String FAMILIY = "org.mapfaces.model.Context";
       
    
    public static final String jaxbInstance = "net.opengis.owc.v030:net.opengis.context.v110";
    
    //Abstract context
    private transient AbstractContext model;
        
    /** Creates a new instance of UIAbstract */
    public UIContext(){
        super();
        setRendererType("org.mapfaces.renderkit.html.models.Context"); // this component has a renderer  
        System.out.println("UIContext constructor----------------------");
        
    }
    
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    @Override
    public void setJAXBElt(JAXBElement JAXBElt) {
        super.setJAXBElt(JAXBElt);
        if((JAXBElt.getName().getLocalPart()).equalsIgnoreCase("ViewContext")){
            if(isDebug())
                System.out.println("Le fichier de context est de type : "+JAXBElt.getDeclaredType()+" "+JAXBElt.getName()+" "+(JAXBElt.getName().getLocalPart()).equalsIgnoreCase("ViewContext"));
//            this.model = new WMC_v110(JAXBElt.getValue());
        }
        else if((JAXBElt.getName().getLocalPart()).equalsIgnoreCase("OWSContext")){
            if(isDebug())
                System.out.println("Le fichier de context est de type : "+JAXBElt.getDeclaredType()+" "+JAXBElt.getName()+" "+(JAXBElt.getName().getLocalPart()).equalsIgnoreCase("OWSContext"));
           this.model = new OWC_v030(JAXBElt.getValue());
        }
    }
    public AbstractContext getModel() {
        return model;
    }

    public void setModel(AbstractContext model) {
        this.model = model;
    }
     @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = model;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        model = (AbstractContext) values[1];
        
    }
    public String saveModel(FacesContext context) throws JAXBException{        
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        String fileUrl = sc.getRealPath("tmp/owc.xml");
        File t = new File(fileUrl);
        JAXBContext.newInstance(jaxbInstance).createMarshaller().marshal(model.getDoc(),new File(fileUrl)); 
        return fileUrl;
    }

}
