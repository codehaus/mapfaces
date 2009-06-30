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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.geotoolkit.skos.xml.Concept;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.widget.util.XMLThesaurusUtilities;
import org.widgetfaces.component.autocompletion.UIAutocompletion;

/**
 *
 * @author Olivier terral (Geomatys)
 */
public class AutocompletionListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        Map<String, String> requestMap = phaseEvent.getFacesContext().getExternalContext().getRequestParameterMap();
        String compClientId = requestMap.get(AjaxUtils.AUTOCOMPLETION_CLIENTID);
        if (compClientId != null) {
            String value = requestMap.get("value");
            handleRequest(phaseEvent, value);
        }
    }

   
    /**
     *
     * @param phaseEvent
     * @param id
     * @param width
     * @param height
     */
    private void handleRequest(PhaseEvent phaseEvent, String value) {
        FacesContext facesContext = phaseEvent.getFacesContext();
            try {
                //If the comp is ajax enable , request response must be in HTML :
                //<li><span>$word</span><a class=\"demo-info\" href=\"http://www.spanishdict.com/translate?word=$word\">[ Ref ]</a></li>
                // or
                //<li>$word</li>
                //Url of thesaurus web-service
                ExternalContext externalContext = facesContext.getExternalContext();
                if (externalContext.getResponse() instanceof HttpServletResponse) {                    
                    Map<String, String> requestMap =
                            phaseEvent.getFacesContext().getExternalContext().getRequestParameterMap();
                    String mode = requestMap.get(AjaxUtils.AUTOCOMPLETION_MODE);
                    List<Concept> words = new ArrayList<Concept>();
                    if (mode.equals("request.html")) {
                        words = XMLThesaurusUtilities.readThesaurus(
                                buildRequestUrl(requestMap.get(AjaxUtils.AUTOCOMPLETION_WS_URL), value, "GEMET",
                                "getConceptsMatchingKeyword", "RDF"), "RDF");

                    } else if (mode.equals("request.json")) {

                    } else if (mode.equals("local")) {
                    }
                    writeHtmlWithServletResponse((HttpServletResponse) facesContext.getExternalContext().getResponse(), words);
                }
            } catch (IOException ex) {
                Logger.getLogger(AutocompletionListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(AutocompletionListener.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                facesContext.responseComplete();
            }
    }

    public URL buildRequestUrl(String wsUrl, String value, String webServiceType, String request, String outputFormat) throws MalformedURLException {
        StringBuilder sb = new StringBuilder(wsUrl);
        if (webServiceType.equals("GEMET")) {
            if (request.equals("getConceptsMatchingKeyword")) {
                return buildGetConceptsMatchingKeyword(sb, value, "1", "fr", outputFormat);
            }
        }
        return new URL(sb.toString());
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private void writeHtmlWithServletResponse(HttpServletResponse response, List<Concept> words) throws IOException {
        ServletOutputStream stream = response.getOutputStream();
        response.setContentType("text/html");
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < words.size(); i++) {
            sb.append("<li>" + words.get(i).getPrefLabel() + "</li>");
        }

        stream.print(sb.toString());
        stream.flush();
        stream.close();
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
    private URL buildGetConceptsMatchingKeyword(StringBuilder sb, String value, String searchMode, String lang, String outputFormat) throws MalformedURLException {
        sb.append("?keyword=").append(value).
                append("&request=getConceptsMatchingKeyword").
                append("&search_mode=").append(searchMode).
                append("&language=").append(lang).
                append("&outputformat=").append(outputFormat);
        return new URL(sb.toString());
    }

}
