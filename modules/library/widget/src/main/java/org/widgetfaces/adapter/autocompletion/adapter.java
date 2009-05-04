/*
 *    Mapfaces - http://www.mapfaces.org
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
package org.widgetfaces.adapter.autocompletion;

import java.util.ArrayList;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public final class adapter {

    public static String array2token(Object obj, FacesContext context) {
        String tokens = "['no value']";
        List<String> list = new ArrayList<String>();
        int cpt = 0;
        if (obj != null) {
            if (obj instanceof String) {
                ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) obj, java.lang.Object.class);
                if (ve.getValue(context.getELContext()) instanceof List){
                    list = (List<String>) ve.getValue(context.getELContext());
                }else{
                    return (String) ve.getValue(context.getELContext());
                }
            }
            else{
                list = (List<String>) obj;
            }
            cpt = list.size();
                tokens = "[";
                for (String string : list) {
                    tokens += "\""+ string+"\"";
                    cpt --;
                    if (cpt != 0){
                        tokens +=",";
                    }
                }
                tokens += "]";
        }
        return tokens;
    }
}
