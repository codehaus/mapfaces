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

package org.mapfaces.widget.util;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;

import org.geotoolkit.skos.xml.Concept;
import org.geotoolkit.skos.xml.RDF;
import org.w3c.dom.Node;
import org.opengis.metadata.citation.OnLineResource;
import org.xml.sax.InputSource;

/**
 *
 * @author Olivier Terral (Geomatys)
 */
public class XMLThesaurusUtilities {

    private static final String JAXBINSTANCE = "org.geotoolkit.skos.xml";

    private XMLThesaurusUtilities() {}

    public static List<Concept> readThesaurus(Object source, String outputFormat) throws JAXBException, UnsupportedEncodingException {
        return readSKOS(unmarshal(source));
    }

    private static List<Concept> readSKOS(RDF elt) {
        return elt.getConcept();
    }
    private static RDF unmarshal(Object source) throws JAXBException {
        final JAXBContext jcontext = JAXBContext.newInstance(JAXBINSTANCE);
        final Unmarshaller unmarshaller = jcontext.createUnmarshaller();
        return (RDF) unmarshall(source,unmarshaller);
    }
    private static Object unmarshall(Object source, Unmarshaller unMarshaller) throws JAXBException{
        if(source instanceof File){
            final File s = (File) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof InputSource){
            final InputSource s = (InputSource) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof InputStream){
            final InputStream s = (InputStream) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Node){
            final Node s = (Node) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Reader){
            final Reader s = (Reader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof Source){
            final Source s = (Source) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof URL){
            final URL s = (URL) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof XMLEventReader){
            final XMLEventReader s = (XMLEventReader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof XMLStreamReader){
            final XMLStreamReader s = (XMLStreamReader) source;
            return unMarshaller.unmarshal(s);
        }else if(source instanceof OnLineResource){
            final OnLineResource online = (OnLineResource) source;
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
}
