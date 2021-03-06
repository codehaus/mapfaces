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
package org.mapfaces.widget.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.geotoolkit.gml.xml.v311.AbstractCurveSegmentType;
import org.geotoolkit.gml.xml.v311.AbstractCurveType;
import org.geotoolkit.gml.xml.v311.AbstractGMLEntry;
import org.geotoolkit.gml.xml.v311.AbstractGeometryType;
import org.geotoolkit.gml.xml.v311.AbstractRingPropertyType;
import org.geotoolkit.gml.xml.v311.AbstractRingType;
import org.geotoolkit.gml.xml.v311.AbstractSurfacePatchType;
import org.geotoolkit.gml.xml.v311.CurvePropertyType;
import org.geotoolkit.gml.xml.v311.CurveType;
import org.geotoolkit.gml.xml.v311.DirectPositionType;
import org.geotoolkit.gml.xml.v311.GeometryPropertyType;
import org.geotoolkit.gml.xml.v311.LineStringSegmentType;
import org.geotoolkit.gml.xml.v311.LineStringType;
import org.geotoolkit.gml.xml.v311.MultiGeometryType;
import org.geotoolkit.gml.xml.v311.PointType;
import org.geotoolkit.gml.xml.v311.PolygonPatchType;
import org.geotoolkit.gml.xml.v311.PolygonType;
import org.geotoolkit.gml.xml.v311.PolyhedralSurfaceType;
import org.geotoolkit.gml.xml.v311.RingType;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.skos.xml.Concept;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.JSLibraryResource;
import org.mapfaces.share.utils.WebContainerUtils;
import org.mapfaces.widget.util.XMLThesaurusUtilities;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 *
 * @author Olivier terral (Geomatys)
 */
public class AutocompletionListener implements PhaseListener {

    private static final Logger LOGGER = Logger.getLogger(AutocompletionListener.class.getName());
    private static final boolean debug = false;

    @Override
    public void afterPhase(final PhaseEvent phaseEvent) {
        final FacesContext facesContext = phaseEvent.getFacesContext();
        final Map<String, String> requestMap = facesContext.getExternalContext().getRequestParameterMap();
        final Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();

        if (requestMap.get(AjaxUtils.AUTOCOMPLETION_CLIENTID) != null) {
            final String value = requestMap.get("value");

            if (value != null && !value.isEmpty()) {
                handleRequest(sessionMap, requestMap, phaseEvent, value);

            } else {
                //HACK to remove the vector layer if it exists when the value is null
                if (requestMap.get(AjaxUtils.THESAURUS_WS_REQUEST).
                        equals(AjaxUtils.THESAURUS_WS_REQUEST_GetGeometricConcept)) {
                    PrintWriter writer = null;
                    
                    try {
                        writer = WebContainerUtils.getResponseWriter(facesContext, "text/plain", null);
                        writer.append("var targetMapId = '" + requestMap.get("targetMapId") + "';");
                        writer.append("var targetLayerId = '" + requestMap.get("targetLayerId") + "';");
                        writer.flush();

                    } catch (IOException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    } finally {
                        writer.close();
                    }
                }
                facesContext.responseComplete();

            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    /**
     *
     * @param phaseEvent
     * @param id
     * @param width
     * @param height
     */
    private void handleRequest(final Map<String, Object> sessionMap, final Map<String, String> requestMap,
            final PhaseEvent phaseEvent, final String value) {
        final FacesContext facesContext = phaseEvent.getFacesContext();

        try {
            //If the comp is ajax enable , request response must be in HTML :
            //<li><span>$word</span><a class=\"demo-info\" href=\"http://www.spanishdict.com/translate?word=$word\">[ Ref ]</a></li>
            // or
            //<li>$word</li>
            //Url of thesaurus web-service
            final String mode = requestMap.get(AjaxUtils.AUTOCOMPLETION_MODE);
            List<Concept> concepts = new ArrayList<Concept>();

            if (mode.equals(AjaxUtils.AUTOCOMPLETION_MODE_REQUEST_HTML)) {
                final URL url =
                        buildRequestUrl(sessionMap, requestMap, value, "GEMET", "RDF", true);

                if (debug) {
                    LOGGER.log(Level.INFO, url.toString());
                }

                if (url != null) {
                    final String wsUrl = requestMap.get(AjaxUtils.THESAURUS_WS_URL);
                    final String request = requestMap.get(AjaxUtils.THESAURUS_WS_REQUEST);
                    concepts = XMLThesaurusUtilities.readThesaurus(url, "RDF");

                    if (request.equals(AjaxUtils.THESAURUS_WS_REQUEST_GetConceptsMatchingKeyword)) {
                        //putting the list of current words requested to find all the word property
                        FacesUtils.putAtSessionMap(facesContext, requestMap.get(AjaxUtils.AUTOCOMPLETION_CLIENTID), concepts);
                        final PrintWriter writer = WebContainerUtils.getResponseWriter(facesContext, "text/html", null);

                        if (isScriptaculous(requestMap)) {
                            writer.write("<ul>");
                        }

                        writeHtmlInResponse(writer, concepts, value);

                        if (isScriptaculous(requestMap)) {
                            writer.write("</ul>");
                        }

                        writer.flush();
                        writer.close();
                    } else if (request.equals(AjaxUtils.THESAURUS_WS_REQUEST_GetGeometricConcept)) {
                        final PrintWriter writer = WebContainerUtils.getResponseWriter(facesContext, "text/plain", null);
                        writer.append("var targetMapId = '" + requestMap.get("targetMapId") + "';");
                        writer.append("var targetLayerId = '" + requestMap.get("targetLayerId") + "';");

                        if (!concepts.isEmpty()) {
                            writeConceptGeoInResponse(writer, concepts.get(0), requestMap.get(AjaxUtils.THESAURUS_OUTPUT_EPSG));
                        }

                        writer.flush();
                        writer.close();
                    }
                }

            } else if (mode.equals(AjaxUtils.AUTOCOMPLETION_MODE_REQUEST_JSON)) {
            } else if (mode.equals(AjaxUtils.AUTOCOMPLETION_MODE_LOCAL)) {
            }

            //putting the list of current words requested to find all the word property

        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, "The file hasn't be parsed !!!", ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            facesContext.responseComplete();
        }
    }

    private URL buildRequestUrl(final Map<String, Object> sessionMap, final Map<String, String> requestMap,
            final String value, final String webServiceType, final String outputFormat,
            final boolean onlyGeometricConcept) throws MalformedURLException {

        final String wsUrl = requestMap.get(AjaxUtils.THESAURUS_WS_URL);
        final String request = requestMap.get(AjaxUtils.THESAURUS_WS_REQUEST);
        final StringBuilder sb = new StringBuilder(wsUrl);

        if (webServiceType.equals("GEMET")) {
            //https://svn.eionet.europa.eu/projects/Zope/wiki/GEMETWebServiceAPI
            //if we request a ThesaurusWS
            final Object tmp = sessionMap.get(requestMap.get(AjaxUtils.AUTOCOMPLETION_CLIENTID));
            List<Concept> concepts = null;

            if (tmp instanceof List) {
                concepts = (List<Concept>) tmp;
            }
            final Concept concept = findConceptByPrefLabel(concepts, value);

            if (concept != null) {

                if (request.equals(AjaxUtils.THESAURUS_WS_REQUEST_GetConceptsMatchingKeyword)) {
                    return buildGetConceptsMatchingKeyword(sb, concept.getPrefLabel(), "1", "fr", outputFormat, onlyGeometricConcept);

                } else if (request.equals(AjaxUtils.THESAURUS_WS_REQUEST_GetGeometricConcept)) {
                    System.out.println("Getgeometric concept concept.getAbout = " + concept.getAbout());
                    return buildGetGeometricConcept(sb, concept.getAbout(), outputFormat);
                }
            }

        }
        return new URL(sb.toString());
    }

    private void writeHtmlInResponse(final PrintWriter writer,
            final List<Concept> words, final String value) throws IOException {

        final StringBuilder sb = new StringBuilder("");
        int nbWordsToDisplay = 10;
        if (words.size() < nbWordsToDisplay) {
            nbWordsToDisplay = words.size();
        }
        for (int i = 0; i < nbWordsToDisplay; i++) {
            //TODO set the default Moo style, but there is a bug onclick , may be we should redefined it
            //final String prefLabel = words.get(i).getPrefLabel();
            //sb.append("<li>" + prefLabel.replaceAll(value, "<span class='autocompleter-queried'>" + value + "</span>") + "</li>");

            sb.append("<li >" + words.get(i).getPrefLabel() + "</li>");
        }
        writer.write(sb.toString());
    }

    private void writeConceptGeoInResponse(final PrintWriter writer,
            final Concept conceptGeo, String outputEpsgCode) throws IOException {

        if (outputEpsgCode == null) {
            outputEpsgCode = AjaxUtils.THESAURUS_DEFAULT_OUTPUT_EPSG;
        }

        try {
            final StringBuilder sb = new StringBuilder("var featureCollection = {").append("\"type\": \"FeatureCollection\",").
                    append("\"features\": [").
                    append("    {").
                    append("        \"type\": \"Feature\",").
                    append("        \"properties\": {},").
                    append("        \"geometry\":");

                   
            //@TODO we need a real GML parser to trasnform GML into GeoJSON
            for (int i = 0; i < conceptGeo.getGeometry().size(); i++) {
                final AbstractGMLEntry GMLEntry = conceptGeo.getGeometry().get(i);

                if (GMLEntry instanceof MultiGeometryType) {
                     sb.append("            {").
                    append("                \"type\": \"GeometryCollection\",").
                            append("                \"geometries\":").
                            append("                    [");
                    final List<GeometryPropertyType> geomMembers = ((MultiGeometryType) GMLEntry).getGeometryMember();

                    for (int j = 0; j < geomMembers.size(); j++) {
                        final AbstractGeometryType geom = geomMembers.get(j).getAbstractGeometry().getValue();

                        if (geom instanceof PolyhedralSurfaceType) {

                            if (((PolyhedralSurfaceType) geom).getPolygonPatches() != null) {
                                final List<JAXBElement<? extends PolygonPatchType>> listPolyPatchs = ((PolyhedralSurfaceType) geom).getPolygonPatches().getValue().getPolygonPatch();

                                for (int k = 0; k < listPolyPatchs.size(); k++) {
                                    final AbstractRingType ring = listPolyPatchs.get(k).getValue().getExterior().getValue().getAbstractRing().getValue();

                                    if (ring instanceof RingType) {
                                        sb.append(this.transformRingTypeToGeoJSON((RingType) ring, outputEpsgCode));
                                    }
                                }

                            } else if (((PolyhedralSurfaceType) geom).getPatches() != null) {
                                final List<JAXBElement<? extends AbstractSurfacePatchType>> listPatchs = ((PolyhedralSurfaceType) geom).getPatches().getValue().getSurfacePatch();

                                for (int k = 0; k < listPatchs.size(); k++) {
                                    final AbstractSurfacePatchType surf = listPatchs.get(k).getValue();

                                    if (surf instanceof PolygonPatchType) {
                                        final AbstractRingType ring = ((PolygonPatchType) surf).getExterior().getValue().getAbstractRing().getValue();

                                        if (ring instanceof RingType) {
                                            sb.append(this.transformRingTypeToGeoJSON((RingType) ring, outputEpsgCode));
                                        }
                                    }

                                }
                            }

                        } else if (geom instanceof PolygonType) {
                            sb.append(this.transformPolygonTypeToGeoJSON((PolygonType) geom, outputEpsgCode));
                        
                        } else if (geom instanceof LineStringType) {
                            sb.append(this.transformLineStringTypeToGeoJSON((LineStringType) geom, outputEpsgCode));

                        } else if (geom instanceof PointType) {
                            sb.append(this.transformPointTypeToGeoJSON((PointType) geom, outputEpsgCode));
                        }

                        if (j < geomMembers.size() - 1) {
                            sb.append(",");
                        }
                    }

                    sb.append("]}");
                } else if (GMLEntry instanceof PointType) {
                    sb.append(this.transformPointTypeToGeoJSON((PointType) GMLEntry, outputEpsgCode));
                }
            }
                sb.append("}]}");
            writer.write(sb.toString());
        } catch (MismatchedDimensionException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (TransformException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /*    Thif function build a getConceptsMatchingKeyword request only for GEMET web-service
     *
    getConceptsMatchingKeyword ¶

    Get a list of concepts matching a keyword for a particular thesaurus.

    Concept[] getConceptsMatchingKeyword(String keyword,
    int searchMode, URI thesaurus, String language)

    Arguments:

    searchMode - integer in the range 0 – 4 inclusive:

     * 0 – no wildcarding of any type ('accident' becomes '^accident$'). SQL syntax: term = 'accident'
     * 1 – suffix regex ('accident' becomes '^accident.+$'). SQL syntax: term LIKE 'accident%'
     * 2 – prefix regex ('accident' becomes '^.+accident$'). SQL syntax: term LIKE '%accident'
     * 3 – prefix/suffix combined ('accident' becomes '^.+accident.+$'). SQL syntax: term LIKE '%accident%'
     * 4 – auto search: each of the previous four expansions is tried in ascending order until a match is found.

    The thesaurus argument indicates which thesaurus to search in. If the argument is empty, all thesauri in the database are searched. The language argument is used both for specifying what language the keyword is and for returning the concept in the correct language.

    Example:

     * http://www.eionet.europa.eu/gemet/getConceptsMatchingKeyword?keyword=air&search_mode=0&thesaurus_uri=http://www.eionet.europa.eu/gemet/concept/&language=en
     */
    private URL buildGetConceptsMatchingKeyword(final StringBuilder sb,
            final String value, final String searchMode, final String lang,
            final String outputFormat, final boolean onlyGeometricConcept)
            throws MalformedURLException {

        sb.append("?keyword=").append(value).
                append("&request=getConceptsMatchingKeyword").
                append("&search_mode=").append(searchMode).
                append("&language=").append(lang).
                append("&outputformat=").append(outputFormat).
                append("&geometric=").append(onlyGeometricConcept);
        return new URL(sb.toString());
    }

    private URL buildGetGeometricConcept(final StringBuilder sb, final String uriConcept, final String outputFormat)
            throws MalformedURLException {
        //http://solardev:8080/mdweb/WS/thesaurus?request=getgeometricConcept&uri_concept=lagune-Canet
        sb.append("?request=getGeometricConcept").
                append("&uri_concept=").append(uriConcept).
                append("&outputformat=").append(outputFormat);
        return new URL(sb.toString());
    }

    private Concept findConceptByPrefLabel(final List<Concept> concepts, final String value) {
        if (concepts != null) {
            for (int i = 0; i < concepts.size(); i++) {
                if (concepts.get(i).getPrefLabel().equals(value)) {
                    return concepts.get(i);
                }
            }
        }
        final Concept concept = new Concept();
        concept.setPrefLabel(value);
        concept.setAbout(value);
        return concept;
    }

    private boolean isScriptaculous(Map<String, String> requestMap) {
        return (JSLibraryResource.fromValue(requestMap.get(AjaxUtils.AUTOCOMPLETER_VERSION)) == JSLibraryResource.SCRIPTACULOUS);
    }

    private String transformPosListToGeoJSON(final List<JAXBElement<?>> someList, String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");

        for (int n = 0; n < someList.size(); n++) {

            if (someList.get(n).getValue() instanceof DirectPositionType) {
                final DirectPositionType pos = (DirectPositionType) someList.get(n).getValue();
                sb.append(this.transformPosToGeoJSON(pos, outputEpsgCode));

                if (n < someList.size() - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();

    }

    private String transformPosToGeoJSON(final DirectPositionType pos, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        final CoordinateReferenceSystem oldCRS = CRS.decode(pos.getSrsName());
        if (debug) {
            LOGGER.log(Level.INFO, "current epsg code  = " + pos.getSrsName());
            LOGGER.log(Level.INFO, "output epsg code  = " + outputEpsgCode);
        }
        final CoordinateReferenceSystem newCRS = CRS.decode(outputEpsgCode, true);
        final MathTransform mt = CRS.findMathTransform(oldCRS, newCRS, true);
        final DirectPosition newPos = mt.transform(pos.getDirectPosition(), null);
        sb.append("[").
                append(newPos.getCoordinate()[0]).
                append(", ").
                append(newPos.getCoordinate()[1]).
                append("]");
        return sb.toString();

    }

    private String transformPointTypeToGeoJSON(final PointType geometry, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        sb.append("{\"type\": \"Point\",\"coordinates\":");
        sb.append(this.transformPosToGeoJSON(geometry.getPos(), outputEpsgCode));
        sb.append("}");
        return sb.toString();
    }

    private String transformRingTypeToGeoJSON(final RingType geometry, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        final List<CurvePropertyType> curveMembers = ((RingType) geometry).getCurveMember();

        for (int l = 0; l < curveMembers.size(); l++) {
            final AbstractCurveType curve = curveMembers.get(l).getAbstractCurve().getValue();

            if (curve instanceof CurveType) {
                final List<JAXBElement<? extends AbstractCurveSegmentType>> segments = ((CurveType) curve).getSegments().getAbstractCurveSegment();

                for (int m = 0; m < segments.size(); m++) {
                    final AbstractCurveSegmentType segment = segments.get(m).getValue();

                    if (segment instanceof LineStringSegmentType) {
                        sb.append(this.transformLineStringSegtTypeToGeoJSON((LineStringSegmentType) segment, outputEpsgCode));
                    }

                }
            }

        }
        return sb.toString();

    }

    private String transformPolygonTypeToGeoJSON(final PolygonType geometry, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        final PolygonType type = (PolygonType) geometry;

        if (type.getExterior() != null) {
            final AbstractRingType ring = type.getExterior().getValue().getAbstractRing().getValue();

            if (ring instanceof RingType) {
                sb.append(this.transformRingTypeToGeoJSON((RingType) ring, outputEpsgCode));
            }
        }
        return sb.toString();
    }

    private String transformLineStringTypeToGeoJSON(final LineStringType geometry, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        final LineStringType type = (LineStringType) geometry;
        sb.append("{\"type\": \"LineString\",\"coordinates\":[");

        if (type.getPosOrPointPropertyOrPointRep() != null) {
            sb.append(this.transformPosListToGeoJSON(type.getPosOrPointPropertyOrPointRep(), outputEpsgCode));
        }
        sb.append("]}");
        return sb.toString();
    }
    private String transformLineStringSegtTypeToGeoJSON(final LineStringSegmentType geometry, final String outputEpsgCode) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        final StringBuilder sb = new StringBuilder("");
        final LineStringSegmentType type = (LineStringSegmentType) geometry;
        sb.append("{\"type\": \"LineString\",\"coordinates\":[");

        if (type.getPosOrPointPropertyOrPointRep() != null) {
            sb.append(this.transformPosListToGeoJSON(type.getPosOrPointPropertyOrPointRep(), outputEpsgCode));
        }
        sb.append("]}");
        return sb.toString();
    }
}
