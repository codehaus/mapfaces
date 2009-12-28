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

package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import org.mapfaces.component.UITest;
import org.mapfaces.share.utils.FacesUtils;

/**
 * This class render an element html DIV that can be called to rerender by ajax4jsf
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.2
 */
public class TestRenderer extends Renderer {
    private static final Logger LOGGER = Logger.getLogger(TestRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        FacesUtils.assertValid(context, component);
        UITest test = (UITest) component;
        
        System.out.println((test.getValue() instanceof String) ? "VALUE INSTANCEOF STRING" : "VALUE NON STRING");
        if ((test.getValue() instanceof String) && (test.getId() != null)) {
            System.out.println("test.getValue() ====> " + test.getValue());
            if (test.getName() != null) {
                System.out.println("NAME ===> " + test.getName());
            }
            HtmlInputText input = new HtmlInputText();
            input.setId(test.getId());
            input.setValue(test.getValue());
        }

    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    public void decode(FacesContext context, UIComponent component) {
    }
    


}
