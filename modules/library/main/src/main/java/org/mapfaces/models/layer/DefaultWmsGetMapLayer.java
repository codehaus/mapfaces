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

package org.mapfaces.models.layer;

import java.util.List;
import org.mapfaces.models.Feature;
import org.mapfaces.util.FacesUtils;

/**
 * this model is for wms layers with an existing url getMap, 
 * this layer have a list of features and a list of sublayers composite 
 * when a factorization process has been launched on the layercontrol component.
 * 
 * @author Mehdi Sidhoum (Geomatys).
 */
public class DefaultWmsGetMapLayer extends DefaultWmsLayer {

    /**
     * This is a list of sublayers, most of time this list is null, but it can take values when a factorization is launched.
     */
    private List<WmsGetMapEntry> composite;
    /**
     * This is the list of features. it is usefull to have this list for getFeatureInfo request on this layer.
     */
    private List<Feature> features = null;

    public List<WmsGetMapEntry> getComposite() {
        return composite;
    }

    public void setComposite(List<WmsGetMapEntry> composite) {
        this.composite = composite;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    /**
     * Returns a boolean value if the composite list of this layer contains the WmsGetMapEntry entry passed in parameter. 
     * @param entry
     * @return
     */
    public boolean containsEntryLayer(WmsGetMapEntry entry) {
        if (entry != null) {
            for (WmsGetMapEntry ent : this.composite) {
                if (entry.getIdentifier() != null && entry.getIdentifier().equals(ent.getIdentifier())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHidden(final boolean hidden, final String identifierSLD) {
        if (identifierSLD != null) {
            String sld_body = FacesUtils.getParameterValue("SLD_BODY", getUrlGetMap());
            if (hidden) {
                //remove the identifier from the getMapUrl if exists
                if (sld_body != null && sld_body.contains(identifierSLD)) {
                    sld_body = sld_body.replaceFirst(identifierSLD, "");
                }
            } else {
                //add the identifier in the getMapurl if not exists
                String literaltagBegin = "%3Cogc:Literal%3E";
                String literaltagEnd = "%3C%2Fogc:Literal%3E";
                if (sld_body.contains(literaltagBegin + literaltagEnd) && ! sld_body.contains(identifierSLD)) {
                    sld_body = sld_body.replaceFirst(literaltagBegin + literaltagEnd, literaltagBegin + identifierSLD + literaltagEnd);
                }
            }
            String newUrl = FacesUtils.setParameterValueAndGetUrl("SLD_BODY", sld_body, getUrlGetMap());
            this.setUrlGetMap(newUrl);
        } else {
            this.setHidden(hidden);
        }
        
    }
    
}
