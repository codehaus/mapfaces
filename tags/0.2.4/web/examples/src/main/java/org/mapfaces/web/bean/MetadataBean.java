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

package org.mapfaces.web.bean;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import org.ajax4jsf.ajax.repeat.UIRepeat;
import org.mapfaces.util.XMLMetadataUtilities;
import org.opengis.metadata.MetaData;

public class MetadataBean
{

    private MetaData metaData;
    private String id;
    private String url;
    private FacesContext context;
    private static final Logger LOGGER = Logger.getLogger(MetadataBean.class.getName());
    HtmlInputText priceRef;
    private UIRepeat repeater;
    private Set keys;

    public FacesContext getContext()
    {
        return context;
    }

    public void setContext(FacesContext context)
    {
        this.context = context;
    }

    public MetadataBean()
        throws Exception
    {
        metaData = null;
        url = null;
        keys = new HashSet(1);
        context = FacesContext.getCurrentInstance();
        System.out.println("Je rentre dans le constructeur de MetadatBean");
        url = "http://localhost:8084/mf/data/metadata/sage_thau.xml";//(String) context.getExternalContext().getRequestParameterMap().get("url");
        if(metaData == null && url != null)
            loadMetaData(url, "ISO19139FRA");
    }

    public String getId()
    {
        return id;
    }

    public void setId(String aId)
    {
        id = aId;
    }

    public String getUrl()
    {
        return url;
    }

    public void seturl(String url)
    {
        this.url = url;
    }

    public MetaData getMetaData()
    {
        return metaData;
    }

    public void setMetaData(MetaData metaData)
    {
        this.metaData = metaData;
    }

    private void loadMetaData(String url, String type)
    {
        try
        {
            setMetaData(XMLMetadataUtilities.readMetaData(new URL(url), type));
        }
        catch(MalformedURLException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        catch(JAXBException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        catch(UnsupportedEncodingException ex)
        {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        if(metaData == null)
            LOGGER.log(Level.SEVERE, "The Metadata file is null !!!");
    }

    public Set getKeys()
    {
        return keys;
    }

    public void setKeys(Set keys)
    {
        this.keys = keys;
    }

    public void setRepeater(UIRepeat repeater)
    {
        this.repeater = repeater;
    }

    public UIRepeat getRepeater()
    {
        return repeater;
    }

    public HtmlInputText getPriceRef()
    {
        return priceRef;
    }

    public void setPriceRef(HtmlInputText priceRef)
    {
        this.priceRef = priceRef;
    }

    public String change()
    {
        System.out.println("change");
        priceRef.processValidators(FacesContext.getCurrentInstance());
        priceRef.processUpdates(FacesContext.getCurrentInstance());
        return null;
    }


}
