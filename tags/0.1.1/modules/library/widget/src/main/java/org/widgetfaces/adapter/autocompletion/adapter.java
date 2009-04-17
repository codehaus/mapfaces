/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
