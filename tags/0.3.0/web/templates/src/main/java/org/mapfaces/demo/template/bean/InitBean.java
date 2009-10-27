package org.mapfaces.demo.template.bean;

import java.util.Locale;
import javax.faces.context.FacesContext;

/**
 * Java bean for initialization.
 *
 * @author Sidhoum Mehdi (Geomatys).
 */
public class InitBean {

    /**
     * Allow to hide some message used by devellopers and to log information in data file.
     */
    //private static final Logger LOGGER = Logger.getLogger(InitBean.class.getName());
    private Locale locale;

    public InitBean() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context.getViewRoot().getLocale() == null) {
            context.getViewRoot().setLocale(Locale.ENGLISH);
            locale = Locale.ENGLISH;
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
