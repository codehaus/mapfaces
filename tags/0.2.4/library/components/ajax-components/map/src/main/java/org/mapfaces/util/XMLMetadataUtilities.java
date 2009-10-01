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
package org.mapfaces.util;

import org.mapfaces.share.utils.XMLUtilities;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import org.geotoolkit.metadata.iso.DefaultMetaData;
import org.opengis.metadata.MetaData;

public class XMLMetadataUtilities extends XMLUtilities {

    private static final Class jaxbInstanceISO1939;
    public static final Class jaxbInstanceISO1939FRA[];

    static {
        jaxbInstanceISO1939 = org.geotoolkit.metadata.iso.DefaultMetaData.class;
        jaxbInstanceISO1939FRA = new Class[7];
        jaxbInstanceISO1939FRA[0] = org.geotoolkit.metadata.fra.FRA_Constraints.class;
        jaxbInstanceISO1939FRA[1] = org.geotoolkit.metadata.fra.FRA_DataIdentification.class;
        jaxbInstanceISO1939FRA[2] = org.geotoolkit.metadata.fra.FRA_DirectReferenceSystem.class;
        jaxbInstanceISO1939FRA[3] = org.geotoolkit.metadata.fra.FRA_IndirectReferenceSystem.class;
        jaxbInstanceISO1939FRA[4] = org.geotoolkit.metadata.fra.FRA_LegalConstraints.class;
        jaxbInstanceISO1939FRA[5] = org.geotoolkit.metadata.fra.FRA_SecurityConstraints.class;
        jaxbInstanceISO1939FRA[6] = jaxbInstanceISO1939;
    }

    public XMLMetadataUtilities() {
    }

    public static MetaData readMetaData(Object source, String type)
            throws JAXBException, UnsupportedEncodingException {
        MetaData elt = null;

        if (type.equals("ISO19139")) {
            elt = (DefaultMetaData) unmarshal(source, new Class[]{
                        jaxbInstanceISO1939
                    });

        } else if (type.equals("ISO19139FRA")) {
            elt = (DefaultMetaData) unmarshal(source, jaxbInstanceISO1939FRA);
            
        }
        return elt;
    }
}
