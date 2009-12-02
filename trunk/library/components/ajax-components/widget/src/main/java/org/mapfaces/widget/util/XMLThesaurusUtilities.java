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
import java.io.UnsupportedEncodingException;


import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.geotoolkit.skos.xml.Concept;
import org.geotoolkit.skos.xml.RDF;
import org.mapfaces.share.utils.XMLUtilities;

/**
 *
 * @author Olivier Terral (Geomatys)
 */
public class XMLThesaurusUtilities extends XMLUtilities{

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
   
}
