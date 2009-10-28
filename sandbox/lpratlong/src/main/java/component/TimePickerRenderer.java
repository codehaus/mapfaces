/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlPanelGroup;

/**
 *
 * @author leopratlong
 */
public class TimePickerRenderer extends Renderer {
    
    /**
     * This member is used both in the encodeBegin and in the encodeEnd.
     */
    private final String DIV = "div";

    /**
     * Encode the component and its values.
     * @param context Context of the JSF page.
     * @param component Custom component we want to show.
     * @throws IOException Input / Ouput Error that can occur when we write on the "writer".
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
       super.encodeBegin(context, component);
       if (!component.isRendered()) {
           return;
       }

       UITimePicker timepicker = (UITimePicker)component;

       // Constants for the page html content.
       final String ID = "id";
       final String STYLE = "style";
       final String CLASS = "class";
       final String SRC = "src";
       final String BLOCK = "block";
       final String SCRIPT = "script";
       final String TYPE = "type";
       final String JAVASCRIPT = "text/javascript";

       /**
        * JAVASCRIPT
        */
       final String NOGRAYSRC = "timepicker/nogray_time_picker.js";
       final String MOOTOOLSCORESRC = "js/mootools-1.2-core.js";
       final String MOOTOOLSMORESRC = "js/mootools-1.2-more.js";
       final String TIMEPICKERSRC = "timepicker/timepicker.js";

       final String COMPID = component.getAttributes().get(ID).toString();
       
       final String MAINDIVCLASS = "mainDiv";
       final String BOTTOMDIVCLASS = "bottomDiv";

       final String IMG = "img";
       final String FIRSTIMGCLASS = "firstImg";
       final String FIRSTIMGSRC = "images/clock_face.gif";
       final String SECONDIMGCLASS = "secondImg";
       final String SECONDIMGSRC = "images/clock_center.gif";

       final String LINK = "a";
       final String HREF = "href";
       final String WRONGLINK = "#";

       final String FORMID = findParentComponentByClass(component, UIForm.class).getId();

       final String INSTANCEJS = COMPID + "_tpvalue";
       final String TIMEPICKERID = COMPID + "_timepicker";

       final String OUTPUTLABELID = COMPID + "_opLabel";
       final String OUTPUTLABELBOTTOMCLASS = "outputLabelBottom";
       final String OUTPUTLABELTOPCLASS = "outputLabelTop";
       final String OUTPUTLABELVALUE = "hh:mm";

       final String BUTTONLEFTID = "submittime_previous";
       final String BUTTONLEFTIMG = "/images/minus.png";
       final String BUTTONLEFTONCLICK = "decreaseTimePicker(" + INSTANCEJS + ",'" + FORMID + ":" + OUTPUTLABELID + "');return false;" +
               "A4J.AJAX.Submit('main_form:stat1','" + FORMID + "',event,{'single':true,'parameters':" +
               "{'main_form:submittime_previous':'main_form:submittime_previous'} ," +
               "'actionUrl':'/timepicker/demo.jsf;jsessionid=4512C549FE618EBF02D10C24263524C9'} );return false;";

       final String BUTTONRIGHTID = "submittime_next";
       final String BUTTONCLASS = "buttonClass";
       final String BUTTONRIGHTIMG = "/images/more.png";
       final String BUTTONRIGHTONCLICK = "increaseTimePicker(" + INSTANCEJS + ",'" + FORMID + ":" + OUTPUTLABELID + "');return false;" +
               "A4J.AJAX.Submit('main_form:stat1','" + FORMID + "',event,{'single':true,'parameters':" +
               "{'main_form:submittime_next':'main_form:submittime_next'} ," +
               "'actionUrl':'/timepicker/demo.jsf;jsessionid=4512C549FE618EBF02D10C24263524C9'} );return false;";

       final String LONGFORMATDATE = "yyyy/mm/dd - hh:mm";
       final String SHORTFORMATDATE = "hh:mm";
       final boolean loadMootools = (Boolean)timepicker.isLoadMootools();

       // Allows to initialize the hour in the Javascript.
       String hour = "0";
       String minute = "0";

       boolean loadBuildFile = false;

       assertValid(context, component);
       ResponseWriter writer = context.getResponseWriter();

       // Verify if we need to load MooTools in the jsp.
       if (loadMootools) {
           // Loading of Mootools.
           writer.startElement(SCRIPT, component);
           writer.writeAttribute(TYPE, JAVASCRIPT, TYPE);
           writer.writeAttribute(SRC, MOOTOOLSCORESRC, SRC);
           writer.endElement(SCRIPT);
           writer.startElement(SCRIPT, component);
           writer.writeAttribute(TYPE, JAVASCRIPT, TYPE);
           writer.writeAttribute(SRC, MOOTOOLSMORESRC, SRC);
           writer.endElement(SCRIPT);
       }

       // Verify if we need to load No_Gray library in the jsp.
       if (timepicker.isLoadJs()) {
           loadBuildFile = true;
           // Loading of the plugin Nogray TimePicker.
           writer.startElement(SCRIPT, component);
           writer.writeAttribute(TYPE, JAVASCRIPT, TYPE);
           writer.writeAttribute(SRC, NOGRAYSRC, SRC);
           writer.endElement(SCRIPT);
       }

       // Writing of the global DIV container.
       writer.startElement(DIV, component);
       
       writer.writeAttribute(ID, COMPID, ID);
       // If the parameter cssClass has been defined for the component, we put this css class for the component.
       final String cssClass;
       if (timepicker.getStyleClass() != null) {
           cssClass = timepicker.getStyleClass();
       } else {
           cssClass = MAINDIVCLASS;
       }

       writer.writeAttribute(CLASS, cssClass, CLASS);

       // If the parameter cssStyle has been defined for the component, we put this css style for the component.
       if (timepicker.getStyle() != null) {
           writer.writeAttribute(STYLE, timepicker.getStyle(), STYLE);
       }

       writer.startElement(DIV, component);
       writer.writeAttribute(ID, FORMID + ':' + TIMEPICKERID, ID);

       writer.startElement(IMG, component);
       writer.writeAttribute(CLASS, FIRSTIMGCLASS, CLASS);
       writer.writeAttribute(SRC, FIRSTIMGSRC, SRC);
       
       writer.startElement(DIV, component);
       writer.endElement(DIV);

       writer.startElement(DIV, component);
       writer.endElement(DIV);

       writer.startElement(IMG, component);
       writer.writeAttribute(CLASS, SECONDIMGCLASS, CLASS);
       writer.writeAttribute(SRC, SECONDIMGSRC, SRC);
       writer.endElement(IMG);

       writer.startElement(LINK, component);
       writer.writeAttribute(HREF, WRONGLINK, HREF);
       writer.endElement(LINK);

       writer.endElement(DIV);

       final HtmlPanelGroup containerBottom = new HtmlPanelGroup();
       containerBottom.setStyleClass(BOTTOMDIVCLASS);
       containerBottom.setLayout(BLOCK);
       containerBottom.getChildren().add(this.encodeButton(component, TIMEPICKERID + '_' + BUTTONLEFTID, BUTTONLEFTIMG, BUTTONLEFTONCLICK, BUTTONCLASS));

       final HtmlOutputLabel outputLabel = new HtmlOutputLabel();
       outputLabel.setId(OUTPUTLABELID);
       outputLabel.setStyleClass(OUTPUTLABELBOTTOMCLASS);
       Date dateValue = this.retrieveDateValue(component);
       if (dateValue != null) {
           SimpleDateFormat sdf = new SimpleDateFormat(SHORTFORMATDATE);
           String date = sdf.format(dateValue);
           outputLabel.setValue(date);
           // we initialize the hour et minute param for the javascript function.
           hour = date.substring(0,2);
           minute = date.substring(3, 5);
       } else {
           outputLabel.setValue(OUTPUTLABELVALUE);
       }

       containerBottom.getChildren().add(outputLabel);
       
       containerBottom.getChildren().add(this.encodeButton(component, TIMEPICKERID + '_' + BUTTONRIGHTID, BUTTONRIGHTIMG, BUTTONRIGHTONCLICK, BUTTONCLASS));
       component.getChildren().add(containerBottom);

       // Verify if we need to load No_Gray library in the jsp.
       if (loadBuildFile) {
           // Load JS File to build the object.
           writer.startElement(SCRIPT, component);
           writer.writeAttribute(TYPE, JAVASCRIPT, TYPE);
           writer.writeAttribute(SRC, TIMEPICKERSRC, SRC);
           writer.endElement(SCRIPT);
       }

       final String targetInput;
       if (timepicker.getTargetInput() != null) {
           targetInput = timepicker.getTargetInput();
       } else {
           targetInput = "";
       }

       writer.startElement(SCRIPT, component);
       writer.write("var " + INSTANCEJS + " = loadTimePicker('" + FORMID + ":" + TIMEPICKERID + "', '" + FORMID + ':' + OUTPUTLABELID + "'," + hour + ", " + minute + ", '" + targetInput + "');");
       writer.endElement(SCRIPT);

    }

    /**
     * Encode the end of the component and its values (permits us to close a div
     * after the encoding of the children components).
     * @param context Context of the JSF page.
     * @param component Custom component we want to show.
     * @throws IOException Input / Ouput Error that can occur when we write on the "writer".
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        if (!component.isRendered()) {
            return;
        }

        assertValid(context, component);
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement(DIV);
    }

    /**
     * Returns a prent component which match with class type.
     * @param component
     * @param c
     * @return
     */
    public static UIComponent findParentComponentByClass(final UIComponent component, final Class c) {
        UIComponent parent = component;
        while (!(c.isInstance(parent))) {
            parent = parent.getParent();
        }
        return parent;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    /**
     * Decode the component to read the data sent by the user (from his page).
     * @param context Context of the JSF page.
     * @param component Custom component we want to read.
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
       //
    }

    @Override
    public boolean getRendersChildren() {
        return super.getRendersChildren();
    }

    /**
     * Encodes the button children of the component.
     * @param component The TimePicker component.
     * @param id ID of the button.
     * @param img Image for the button.
     * @param onClick Onclick Event of the button.
     * @param cssClass cssClass of the button.
     * @return HtmlGraphicImage Button component.
     */
    private HtmlGraphicImage encodeButton(UIComponent component, String id, String img, String onClick, String cssClass) {
       final HtmlGraphicImage buttonLeft = new HtmlGraphicImage();
       buttonLeft.setId(id);
       buttonLeft.setStyleClass(cssClass);
       buttonLeft.setUrl(img);
       buttonLeft.setOnclick(onClick);
       return buttonLeft;
    }

    /**
     * Retrieve the date contained in the Value parameter of the component;
     * @param component It must be an UITimePicker instance to get a date.
     * @return Date The date contained in the value parameter (null else).
     */
    private Date retrieveDateValue(UIComponent component) {
        Date date;
        // Try if the component is an instance of UITimePicker...
        if (component instanceof UITimePicker) {
           // then verify if the value of the component is not null.
           if (((UITimePicker)component).getValue() != null) {
               // we have already verified on the Tag Class if the value is an instance of Date,
               // so we can Cast it directly...
               date = (Date)((UITimePicker)component).getValue();
           } else {
               date = null;
           }
        } else {
           date = null;
        }

        return date;
    }
}
