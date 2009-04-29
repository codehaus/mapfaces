package org.widgetfaces.component;

import javax.faces.component.StateHolder;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

/**
 * UIBase is a UICommand that represents a user interface component which,
 * when activated by the user, triggers an application specific "command" or "action".
 * Such a component is typically rendered as a push button, a menu item, or a hyperlink.
 * @author kdelfour
 */
public class UIBase extends UICommand implements StateHolder {

    /* Fields */
    private String style;
    private String styleClass;
    private boolean debug;

    /* Methods */
    /**
     * <p>Gets the state of the instance as a Serializable Object.</p>
     * <p>If the class that implements this interface has references to instances that implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>saveState(javax.faces.context.FacesContext) method on all those instances as well.</p>
     * <p>This method must not save the state of children and facets. That is done via the StateManager</p>
     * @param context The FacesContext for the current request
     * @return a Serializable Object
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[4];
        values[0] = super.saveState(context);
        values[1] = isDebug();
        values[2] = getStyle();
        values[3] = getStyleClass();
        return values;
    }

    /**
     * <p>Perform any processing required to restore the state from the entries in the state Object.</p>
     * <p>If the class that implements this interface has references to instances that also implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>restoreState(javax.faces.context.FacesContext, java.lang.Object) method on all those instances as well.</p>
     * @param context The FacesContext for the current request
     * @param state the state Object
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setDebug((Boolean) values[1]);
        setStyle((String) values[2]);
        setStyleClass((String) values[3]);
    }

    /* Accessors */
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setSubmittedValue(Object submitted){

    }
}
