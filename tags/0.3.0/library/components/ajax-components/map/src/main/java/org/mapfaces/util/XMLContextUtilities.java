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

package org.mapfaces.util;

import org.mapfaces.share.utils.XMLUtilities;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;



import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;


import org.mapfaces.models.Context;

public  class XMLContextUtilities extends XMLUtilities {

    private static final String jaxbInstance = "org.geotoolkit.owc.xml.v030:org.geotoolkit.wmc.xml.v110";

    private static final org.geotoolkit.owc.xml.v030.ObjectFactory factory_owc_030 = new org.geotoolkit.owc.xml.v030.ObjectFactory();
    private static final org.geotoolkit.wmc.xml.v110.ObjectFactory factory_wmc_110 = new org.geotoolkit.wmc.xml.v110.ObjectFactory();



    public static Context readContext(Object source) throws JAXBException, UnsupportedEncodingException {
        JAXBElement elt =  unmarshal(source, jaxbInstance);
        if(elt.getName().toString().equals("{http://www.opengis.net/ows-context}OWSContext"))
            return readOWC(elt);
        else if(elt.getName().toString().equals("{http://www.opengis.net/context}Context"))
            return readWMC(elt);
        else
            throw new NullPointerException("Bad file type");
    }

    private static Context readOWC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        if(elt.getDeclaredType().toString().equals("class org.geotoolkit.owc.xml.v030.OWSContextType")){
            return OWCv030toMFTransformer.visit( (org.geotoolkit.owc.xml.v030.OWSContextType) elt.getValue());
        }else throw new UnsupportedOperationException("Bad file version, versions available are : owc 0.3.0 ");

    }

    private static Context readWMC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        if(elt.getDeclaredType().toString().equals("class org.geotoolkit.wmc.xml.v110.ViewContextType")){
            return (new WMCv110toMFTransformer()).visit( (org.geotoolkit.wmc.xml.v110.ViewContextType) elt.getValue());
        }else throw new UnsupportedOperationException("Bad file version, versions available are : owc 0.3.0 ");
    }


    public static void  writeContext(Context ctx, File output) throws JAXBException, UnsupportedEncodingException, IOException {
        JAXBElement elt;
        if(ctx.getContextType().contains("OWSContextType")){
            elt = writeOWC(ctx);
        }else if( ctx.getContextType().contains("ViewContextType") ){
            elt = writeWMC(ctx);
        }else throw new UnsupportedOperationException("The version of your context file " +
                               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
                               ", only OWC 0.3.0 is supported !!!!!");

        marshal(elt, output, jaxbInstance);
    }

    private static JAXBElement writeOWC(Context ctx) throws UnsupportedEncodingException, JAXBException {
        if (ctx.getVersion().equals("0.3.0")){
            return writeOWC030(ctx);
        }
        throw new UnsupportedOperationException("The version of your context file " +
               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
               ", only OWC 0.3.0 is supported !!!!!");
    }

   
    private static JAXBElement writeOWC030(Context ctx) throws UnsupportedEncodingException, JAXBException {
        return factory_owc_030.createOWSContext((new MFtoOWCv030Transformer()).visit(ctx));
    }

    private static JAXBElement writeWMC(Context ctx) {
        throw new UnsupportedOperationException("The version of your context file " +
               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
               ", only OWC 0.3.0 is supported !!!!!");
    }

}
