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

import java.util.List;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kevin Delfour (IRD)
 */
public final class Adapter {

    public static String array2token(Object obj, FacesContext context) {
        
        if (obj != null) {
            final List<String> list;
            if (obj instanceof String) {
                final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) obj, java.lang.Object.class);
                if (ve.getValue(context.getELContext()) instanceof List){
                    list = (List<String>) ve.getValue(context.getELContext());
                }else{
                    return (String) ve.getValue(context.getELContext());
                }
            } else {
                list = (List<String>) obj;
            }

            final StringBuilder sb = new StringBuilder('[');
            final int n =list.size();
            for(int i=0;i<n;i++){
                sb.append('\"').append(list.get(i)).append('\"');
                if(i+1<n) sb.append(',');
            }
            sb.append(']');

            return sb.toString();
        }

        return "['no value']";
    }
}
