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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.w3c.dom.Node;
import org.opengis.metadata.citation.OnLineResource;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import org.mapfaces.models.Context;

public  class XMLContextUtilities {

    private final String jaxbInstance = "org.geotoolkit.owc.xml.v030:org.geotoolkit.wmc.xml.v110";

    private org.geotoolkit.owc.xml.v030.ObjectFactory factory_owc_030 = new org.geotoolkit.owc.xml.v030.ObjectFactory();
    //private org.geotoolkit.context.v110.ObjectFactory factory_wmc_100 = new org.geotoolkit.context.v100.ObjectFactory();
    private org.geotoolkit.wmc.xml.v110.ObjectFactory factory_wmc_110 = new org.geotoolkit.wmc.xml.v110.ObjectFactory();
//    private org.geotools.internal.jaxb.v110.sld.ObjectFactory factory_sld_110 = new org.geotools.internal.jaxb.v110.sld.ObjectFactory();
//    private org.geotools.internal.jaxb.v110.se.ObjectFactory factory_se_110 = new org.geotools.internal.jaxb.v110.se.ObjectFactory();
//    private org.geotools.internal.jaxb.v100.ogc.ObjectFactory factory_ogc_100 = new org.geotools.internal.jaxb.v100.ogc.ObjectFactory();
//    private org.geotools.internal.jaxb.v110.ogc.ObjectFactory factory_ogc_110 = new org.geotools.internal.jaxb.v110.ogc.ObjectFactory();
//
//    private SLD110toGTTransformer TRANSFORMER_GT_V110 = null;
//    private GTtoSLD100Transformer TRANSFORMER_XML_V100 = null;
//    private GTtoSLD110Transformer TRANSFORMER_XML_V110 = null;



    public Context readContext(Object source) throws JAXBException, UnsupportedEncodingException {
        JAXBElement elt =  unmarshal(source);
        if(elt.getName().toString().equals("{http://www.opengis.net/ows-context}OWSContext"))
            return readOWC(elt);
        else if(elt.getName().toString().equals("{http://www.opengis.net/context}Context"))
            return readWMC(elt);
        else
            throw new NullPointerException("Bad file type");
    }

    private Context readOWC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        if(elt.getDeclaredType().toString().equals("class org.geotoolkit.owc.xml.v030.OWSContextType")){
            return OWCv030toMFTransformer.visit( (org.geotoolkit.owc.xml.v030.OWSContextType) elt.getValue());
        }else throw new UnsupportedOperationException("Bad file version, versions available are : owc 0.3.0 ");

    }

    private Context readWMC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        if(elt.getDeclaredType().toString().equals("class org.geotoolkit.wmc.xml.v110.ViewContextType")){
            return (new WMCv110toMFTransformer()).visit( (org.geotoolkit.wmc.xml.v110.ViewContextType) elt.getValue());
        }else throw new UnsupportedOperationException("Bad file version, versions available are : owc 0.3.0 ");
    }


    public void  writeContext(Context ctx, File output) throws JAXBException, UnsupportedEncodingException, IOException {
        JAXBElement elt;
        if(ctx.getContextType().contains("OWSContextType")){
            elt = writeOWC(ctx);
        }else if( ctx.getContextType().contains("ViewContextType") ){
            elt = writeWMC(ctx);
        }else throw new UnsupportedOperationException("The version of your context file " +
                               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
                               ", only OWC 0.3.0 is supported !!!!!");

        marshal(elt,output);
    }

    private JAXBElement writeOWC(Context ctx) throws UnsupportedEncodingException, JAXBException {
        if (ctx.getVersion().equals("0.3.0")){
            return writeOWC030(ctx);
        }
        throw new UnsupportedOperationException("The version of your context file " +
               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
               ", only OWC 0.3.0 is supported !!!!!");
    }

    private JAXBElement unmarshal(Object source) throws JAXBException {
       JAXBContext Jcontext;
        Jcontext = JAXBContext.newInstance(jaxbInstance);
        Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
        return (JAXBElement) unmarshall(source,unmarshaller);
    }



    public  void  marshal(Object jaxbElement, File output) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(jaxbInstance);
        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",namespace);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshall(output,jaxbElement,marshaller);

    }
    private final Object unmarshall(Object source, Unmarshaller unMarshaller) throws JAXBException{
        if(source instanceof File){
            File s = (File) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof InputSource){
            InputSource s = (InputSource) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof InputStream){
            InputStream s = (InputStream) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Node){
            Node s = (Node) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Reader){
            Reader s = (Reader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Source){
            Source s = (Source) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof URL){
            URL s = (URL) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof XMLEventReader){
            XMLEventReader s = (XMLEventReader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof XMLStreamReader){
            XMLStreamReader s = (XMLStreamReader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof OnLineResource){
            OnLineResource online = (OnLineResource) source;
            try {
                URL url = online.getLinkage().toURL();
                return unMarshaller.unmarshal(url);
            } catch (MalformedURLException ex) {
                return null;
            }

        }else{
            throw new IllegalArgumentException("Source object is not a valid class :" + source.getClass());
        }

    }
    private final void marshall(Object target, Object jaxbElement, Marshaller marshaller) throws JAXBException{

        if(target instanceof File){
            File s = (File) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof ContentHandler){
            ContentHandler s = (ContentHandler) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof OutputStream){
            OutputStream s = (OutputStream) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof Node){
            Node s = (Node) target;
            marshaller.marshal(jaxbElement,s);
        }
        else if(target instanceof Writer){
            Writer s = (Writer) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof Result){
            Result s = (Result) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof XMLEventWriter){
            XMLEventWriter s = (XMLEventWriter) target;
            marshaller.marshal(jaxbElement,s);
        }else if(target instanceof XMLStreamWriter){
            XMLStreamWriter s = (XMLStreamWriter) target;
            marshaller.marshal(jaxbElement,s);
        }else{
            throw new IllegalArgumentException("target object is not a valid class :" + target.getClass());
        }

    }
    private JAXBElement writeOWC030(Context ctx) throws UnsupportedEncodingException, JAXBException {
        return factory_owc_030.createOWSContext((new MFtoOWCv030Transformer()).visit(ctx));
    }

    private JAXBElement writeWMC(Context ctx) {
//        if (ctx.getVersion().equals("1.0.0")){
//           return factory_wmc_100.createViewContext((new MFtoWMCv100Transformer()).visit(ctx));
//        }else if( ctx.getVersion().equals("1.1.0") ){
//           return factory_wmc_110.createViewContext((new MFtoWMCv110Transformer()).visit(ctx));
//        }
        throw new UnsupportedOperationException("The version of your context file " +
               "isn't supported yet !!!!! Type : "+ ctx.getContextType() +", version : "+ctx.getVersion()+
               ", only OWC 0.3.0 is supported !!!!!");
    }


    public  static void main(String[] args) throws FileNotFoundException, JAXBException, UnsupportedEncodingException{
        try {
            Context ctx = (new XMLContextUtilities()).readContext(new FileReader(new File("D://svn/mapfaces/trunk/modules/web/src/main/webapp/data/context/tasmania.xml")));
            if (ctx == null) 
                     Logger.getLogger(XMLContextUtilities.class.getName()).log(Level.SEVERE,"context is null");
            (new XMLContextUtilities()).writeContext(ctx, new File("C://Documents and Settings/Sangoku/Mes documents/NetBeansProjects/mf-web/build/web/data/context/owctest.xml"));
        } catch (IOException ex) {
            Logger.getLogger(XMLContextUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    private final Object unmarshallWMCv110(Object source) throws JAXBException{
////        JAXBContext jaxbContext;
////        Unmarshaller unMarshaller;
////
////        if (TRANSFORMER_MF_V100 == null) {
////            TRANSFORMER_GT_V100 = new SLD100toGTTransformer();
////        }
////        jaxbContext = JAXBContext.newInstance(org.geotools.internal.jaxb.v100.sld.StyledLayerDescriptor.class);
////        unMarshaller = jaxbContext.createUnmarshaller();
////        return unmarshall(source, unMarshaller);
//        return null;
//    }
//
//    private final Object unmarshallOWCv030(Object source) throws JAXBException{
//        JAXBContext jaxbContext;
//        Unmarshaller unMarshaller;
//
//        if (TRANSFORMER_MF_OWCv030 == null) {
//            TRANSFORMER_MF_OWCv030 = new OWCv030toMFTransformer();
//        }
//        jaxbContext = JAXBContext.newInstance(org.geotoolkit.owc.v030.OWSContextType.class);
//        unMarshaller = jaxbContext.createUnmarshaller();
//        return unmarshall(source, unMarshaller);
//    }
//
//
//
//    private final void marshallWMCv110(Object target, Object jaxElement, NamespacePrefixMapperImpl namespace) throws JAXBException {
////        JAXBContext jaxbContext;
////        Marshaller marshaller;
////
////        jaxbContext = JAXBContext.newInstance(org.geotools.internal.jaxb.v100.sld.StyledLayerDescriptor.class);
////        marshaller = jaxbContext.createMarshaller();
////        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",namespace);
////        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
////        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////        marshall(target, jaxElement, marshaller);
//    }
//
//    private final void marshallOWCv030(Object target, Object jaxElement, NamespacePrefixMapperImpl namespace) throws JAXBException {
//        JAXBContext jaxbContext;
//        Marshaller marshaller;
//
//        jaxbContext = JAXBContext.newInstance(org.geotoolkit.owc.v030.OWSContextType.class);
//        marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",namespace);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshall(target, jaxElement, marshaller);
//    }
}
