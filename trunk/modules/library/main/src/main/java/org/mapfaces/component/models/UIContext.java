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

package org.mapfaces.component.models;

import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.mapfaces.models.Context;

/**
 *
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIContext extends UIModelBase {
    
    public static final String FAMILIY = "org.mapfaces.model.Context";
    public final String jaxbInstance = "net.opengis.owc.v030:net.opengis.context.v110";
    
    
    
    private boolean scriptaculous = true;
    private boolean mootools = true;
    private boolean minifyJS = true;
    
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
    public Object saveState(FacesContext context) {
        Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = scriptaculous;
        values[2] = mootools;
        values[3] = minifyJS;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        scriptaculous = (Boolean) values[1];
        mootools = (Boolean) values[2];
        minifyJS = (Boolean) values[3];
    }
    
    /**
     * This method saves the model into a xml file in a temporary folder.
     * @param context
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public String saveModel(FacesContext context) throws JAXBException {
//        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
//        String fileUrl = sc.getRealPath("tmp/owc.xml");
//        File t = new File(fileUrl);
//        
//        //casting the model to Context for this UIContext component.
//        Context modelContext = (Context) getModel();
//        JAXBContext.newInstance(jaxbInstance).createMarshaller().marshal(modelContext.getDoc(),new File(fileUrl)); 
//        return fileUrl;
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isScriptaculous() {
        return scriptaculous;
    }

    public void setScriptaculous(boolean scriptaculous) {
        this.scriptaculous = scriptaculous;
    }

    public boolean isMootools() {
        return mootools;
    }

    public void setMootools(boolean mootools) {
        this.mootools = mootools;
    }

    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(boolean minifyJS) {
        this.minifyJS = minifyJS;
    }

}
