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

import java.io.IOException;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple comet servlet, keep an http connexion open for each client
 * can be used to send events at random times.
 *
 * @author Johann Sorel (Geomatys)
 */
public abstract class MFCometServlet extends HttpServlet{

    private String contextPath = null;

    protected abstract String getServletPath();

    protected abstract MFCometHandler getHandler();

    /**
     * {@inheritDoc }
     */
    @Override
    public final void init(final ServletConfig config) throws ServletException {
        final ServletContext context    = config.getServletContext();
        contextPath                     = context.getContextPath() + "/" + getServletPath();
        final CometEngine engine        = CometEngine.getEngine();
        final CometContext cometContext = engine.register(contextPath);
        cometContext.setExpirationDelay(-1);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {

        final MFCometHandler handler = getHandler();
        handler.setContextPath(contextPath);
        handler.attach(res);

        final CometEngine engine   = CometEngine.getEngine();
        final CometContext context = engine.getCometContext(contextPath);

        context.addCometHandler(handler);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    }

    /**
     * Write a hidden iframe that will be used as a buffer page for the client
     * to receive events from the server.
     */
    public static final void insertHTMLIFrame(final ResponseWriter writer, final String cometContextPath) throws IOException{
        writer.startElement("iframe", null);
        writer.writeAttribute("src", cometContextPath, null);
        writer.writeAttribute("frameborder", "0", null);
        writer.writeAttribute("height", "0", null);
        writer.writeAttribute("width", "0", null);
        writer.writeAttribute("style","display:none",null);
        writer.endElement("iframe");
    }

}
