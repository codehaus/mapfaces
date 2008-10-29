/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

import org.geotools.sld.MutableStyledLayerDescriptor;
import org.geotools.style.MutableFeatureTypeStyle;
import org.geotools.style.MutableRule;
import org.geotools.style.MutableStyle;
import org.opengis.filter.Filter;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.sld.StyledLayerDescriptor;
import org.opengis.style.FeatureTypeStyle;
import org.opengis.style.Rule;
import org.opengis.style.Style;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.mapfaces.models.Context;

/**
 *
 * @author olivier
 */
public  class XMLContextUtilities {
    

    
    private net.opengis.owc.v030.ObjectFactory factory_owc_030 = new net.opengis.owc.v030.ObjectFactory();


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
    
    private  Context readOWC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        
        System.out.println(elt.getDeclaredType().toString());
        if(elt.getDeclaredType().toString().equals("class net.opengis.owc.v030.OWSContextType")){
                 return (new OWCv030toMFTransformer()).visit( (net.opengis.owc.v030.OWSContextType) elt.getValue());
        }else
            throw new NullPointerException("Bad file version, versions available are : owc 0.3.0 and wmc 1.1.0");
        
    }

    private   Context readWMC(JAXBElement elt) throws UnsupportedEncodingException, JAXBException {
        if(elt.getDeclaredType().toString().equals("class net.opengis.context.v110.ViewContextType")){
                 return (new WMCv110toMFTransformer()).visit( (net.opengis.context.v110.ViewContextType) elt.getValue());
        }else
            throw new NullPointerException("Bad file version, versions available are : owc 0.3.0 and wmc 1.1.0");
    }

    private JAXBElement unmarshal(Object source) throws JAXBException {
       JAXBContext Jcontext;
        Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");
        Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
        return (JAXBElement) unmarshall(source,unmarshaller);
       
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
    

    public  static void main(String[] args) throws FileNotFoundException, JAXBException, UnsupportedEncodingException{
            Context ctx = (new XMLContextUtilities()).readContext("/home/olivier/svn/mapfaces/trunk/modules/web/src/main/webapp/data/context/ifremer.xml");

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
//        jaxbContext = JAXBContext.newInstance(net.opengis.owc.v030.OWSContextType.class);
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
//        jaxbContext = JAXBContext.newInstance(net.opengis.owc.v030.OWSContextType.class);
//        marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",namespace);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshall(target, jaxElement, marshaller);
//    }
   
}
