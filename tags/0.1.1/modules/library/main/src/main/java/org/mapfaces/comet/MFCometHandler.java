/*
 *    Mapfaces - http://www.mapfaces.org
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

package org.mapfaces.comet;

import com.sun.grizzly.comet.CometContext;
import com.sun.grizzly.comet.CometEngine;
import com.sun.grizzly.comet.CometEvent;
import com.sun.grizzly.comet.CometHandler;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract implementation of a CometHandler.
 * A basic subclass override the onInitialize method and start the her operations.
 * Call removeThisFromContext when operations are finished.
 *
 * @author Johann Sorel (Geomatys)
 */
public class MFCometHandler implements CometHandler<HttpServletResponse> {

    private HttpServletResponse response;

    private String contextPath = null;

    /**
     * {@inheritDoc }
     */
    @Override
    public final void attach(HttpServletResponse attachment) {
        this.response = attachment;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void onEvent(CometEvent arg0) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void onInitialize(CometEvent arg0) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void onTerminate(CometEvent arg0) throws IOException {
        removeThisFromContext();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void onInterrupt(CometEvent arg0) throws IOException {
        removeThisFromContext();
    }

    /**
     * Unregister the handler from the CometEngine.
     * Release the http connexion.
     * 
     * @throws java.io.IOException
     */
    protected final void removeThisFromContext() throws IOException {
        response.getWriter().close();
        final CometContext context = CometEngine.getEngine().getCometContext(contextPath);
        context.removeCometHandler(this);
    }

    /**
     * Returns the response for the client.
     * You can use this method anytime to send information to the client.
     *
     * @return HttpServletResponse
     */
    protected final HttpServletResponse getHttpResponse(){
        return response;
    }

    /**
     * Used by the servlet to set the context path.
     * @param path , can not be null
     */
    public final void setContextPath(final String path){
        this.contextPath = path;
    }

}
