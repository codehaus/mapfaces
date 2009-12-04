/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
package org.mapfaces.share.utils;

import java.io.*;
import java.net.*;
import javax.xml.bind.*;
import javax.xml.stream.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.opengis.metadata.citation.OnlineResource;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

public class XMLUtilities {

    public XMLUtilities() {
    }

    public static JAXBElement unmarshal(Object source, String jaxbInstance)
            throws JAXBException {
        JAXBContext Jcontext = JAXBContext.newInstance(jaxbInstance);
        Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
        return (JAXBElement) unmarshall(source, unmarshaller);
    }

   public static void marshal(Object jaxbElement, File output, String jaxbInstance)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(jaxbInstance);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.encoding", "UTF-8");
        marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
        marshall(output, jaxbElement, marshaller);
    }

    public static Object unmarshal(Object source, Class classes[])
            throws JAXBException {
        JAXBContext Jcontext = JAXBContext.newInstance(classes);
        Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
        return unmarshall(source, unmarshaller);
    }

    public static void marshal(Object jaxbElement, File output, Class classes[])
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classes);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.encoding", "UTF-8");
        marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
        marshall(output, jaxbElement, marshaller);
    }

    public static Object unmarshall(Object source, Unmarshaller unMarshaller)
            throws JAXBException {
        if (source instanceof File) {
            File s = (File) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof InputSource) {
            InputSource s = (InputSource) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof InputStream) {
            InputStream s = (InputStream) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof Node) {
            Node s = (Node) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof Reader) {
            Reader s = (Reader) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof Source) {
            Source s = (Source) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof URL) {
            URL s = (URL) source;
            Object obj = new Object();
            try {
                obj = unMarshaller.unmarshal(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return obj;
        }
        if (source instanceof XMLEventReader) {
            XMLEventReader s = (XMLEventReader) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof XMLStreamReader) {
            XMLStreamReader s = (XMLStreamReader) source;
            return unMarshaller.unmarshal(s);
        }
        if (source instanceof OnlineResource) {
            OnlineResource online = (OnlineResource) source;
            try {
                URL url = online.getLinkage().toURL();
                return unMarshaller.unmarshal(url);
            } catch (MalformedURLException ex) {
                return null;
            }
        } else {
            throw new IllegalArgumentException((new StringBuilder()).append("Source object is not a valid class :").append(source.getClass()).toString());
        }
    }

    public static void marshall(Object target, Object jaxbElement, Marshaller marshaller)
            throws JAXBException {
        if (target instanceof File) {
            File s = (File) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof ContentHandler) {
            ContentHandler s = (ContentHandler) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof OutputStream) {
            OutputStream s = (OutputStream) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof Node) {
            Node s = (Node) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof Writer) {
            Writer s = (Writer) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof Result) {
            Result s = (Result) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof XMLEventWriter) {
            XMLEventWriter s = (XMLEventWriter) target;
            marshaller.marshal(jaxbElement, s);
        } else if (target instanceof XMLStreamWriter) {
            XMLStreamWriter s = (XMLStreamWriter) target;
            marshaller.marshal(jaxbElement, s);
        } else {
            throw new IllegalArgumentException((new StringBuilder()).append("target object is not a valid class :").append(target.getClass()).toString());
        }
    }
}
