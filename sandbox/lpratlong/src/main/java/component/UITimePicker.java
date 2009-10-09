package component;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 * This class is a User Interface used to parameter the TimePicker component.
 * @author leopratlong
 */
public class UITimePicker extends UIInput {

    /**
     * Define the family of the component.
     */
    private static String FAMILY = "components.timepickers";

    /**
     * Define the date value of the time picker component.
     */
    private Date value;
    /**
     * Define if we have to load the MooTools library on the View page.
     */
    private boolean loadMootools;
    /**
     * Define if we have to load the No_Gray TimePicker library on the View page.
     */
    private boolean loadJs;
    /**
     * Define the css Style of the component.
     */
    private String style;
    /**
     * Define the CSS Class of the component.
     */
    private String styleClass;
    /**
     * Define the position of the Output Label
     */
    private boolean outputTop;

    /**
     * Default constructor.
     * Create a new instance of UIHorloge
     */
    public UITimePicker() {
        super();
        setRendererType("renderer.timepickers");
    }

    /**
     * Save the state of the component (members and values).
     * @param context FacesContext of the component.
     * @return Object Array of object containing the values of the component;
     */
    @Override
    public Object saveState(FacesContext context) {
        final Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = this.getValue();
        values[2] = this.isLoadMootools();
        values[3] = this.isLoadJs();
        values[4] = this.getStyle();
        values[5] = this.getStyleClass();
        values[6] = this.isOutputTop();
        return values;
    }

    /**
     * Restore the state of the component (members and values).
     * @param context FacesContext of the component
     * @param state Values of the component to restore.
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        // value is got on the Tag Class.
        this.setLoadMootools((boolean) (Boolean)values[2]);
        this.setLoadJs((boolean) (Boolean)values[3]);
        this.setStyle(values[4].toString());
        this.setStyleClass(values[5].toString());
        this.setOutputTop((boolean) (Boolean)values[6]);
    }

    /**
     * @return the FAMILY
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Date value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public Date getValue() {
        return value;
    }

    /**
     * @return the loadMootools
     */
    public boolean isLoadMootools() {
        return loadMootools;
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(boolean loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @return the loadJs
     */
    public boolean isLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the styleClass
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the outputTop
     */
    public boolean isOutputTop() {
        return outputTop;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setOutputTop(boolean outputTop) {
        this.outputTop = outputTop;
    }
    
}
