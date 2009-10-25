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
package org.widgetfaces.renderkit.html.temporal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlPanelGroup;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.widgetfaces.component.temporal.UITimePicker;

/**
 *
 * @author leopratlong (Geomatys)
 * @since 0.2
 */
public class TimePickerRenderer extends Renderer {

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

        UITimePicker timepicker = (UITimePicker) component;


        // Constants for the page html content.
        final String BLOCK = "block";

        /**
         * JAVASCRIPT / css
         */
        final String NOGRAYSRC = "/org/widgetfaces/widget/timepicker/js/nogray_time_picker.js";
        final String MOOTOOLSSRC = "/org/widgetfaces/resources/compressed/mootools.min.js";
        final String TIMEPICKERCSS = "/org/widgetfaces/widget/timepicker/css/timepicker_style.css";

        final String COMPID = component.getAttributes().get(HTML.id_ATTRIBUTE).toString();

        final String MAINDIVCLASS = "TimePickermainDiv";
        final String BOTTOMDIVCLASS = "TimePickerbottomDiv";

        final String IMG = HTML.IMG_ELEM;
        final String FIRSTIMGCLASS = "TimePickerfirstImg";
        final String FIRSTIMGSRC = "/org/widgetfaces/widget/timepicker/img/clock_face.gif";
        final String SECONDIMGCLASS = "TimePickersecondImg";
        final String SECONDIMGSRC = "/org/widgetfaces/widget/timepicker/img/clock_center.gif";

        final String LINK = "a";
        final String HREF = "href";
        final String WRONGLINK = "#";

        final String FORMID = FacesUtils.getFormId(context, timepicker);

        final String INSTANCEJS = COMPID + "_tpvalue";
        final String TIMEPICKERID = COMPID + "_timepicker";

        final String OUTPUTLABELID = COMPID + "_opLabel";
        final String OUTPUTLABELBOTTOMCLASS = "TimePickeroutputLabelBottom";
        final String OUTPUTLABELTOPCLASS = "TimePickeroutputLabelTop";
        final String OUTPUTLABELVALUE = "hh:mm";

        final String BUTTONLEFTID = "submittime_previous";
        final String BUTTONLEFTIMG = "/org/widgetfaces/widget/timepicker/img/minus.png";
        final String BUTTONLEFTONCLICK = "decreaseTimePicker(" + INSTANCEJS + ",'" + FORMID + ":" + OUTPUTLABELID + "');return false;" +
                "A4J.AJAX.Submit('" + FORMID + "','" + FORMID + "',event,{'single':true,'parameters':" +
                "{'" + FORMID + ":submittime_previous':'" + FORMID + ":submittime_previous'} ," +
                "'actionUrl':window.location.href} );return false;";

        final String BUTTONRIGHTID = "submittime_next";
        final String BUTTONCLASS = "TimePickerbuttonClass";
        final String BUTTONRIGHTIMG = "/org/widgetfaces/widget/timepicker/img/more.png";
        final String BUTTONRIGHTONCLICK = "increaseTimePicker(" + INSTANCEJS + ",'" + FORMID + ":" + OUTPUTLABELID + "');return false;" +
                "A4J.AJAX.Submit('" + FORMID + "','" + FORMID + "',event,{'single':true,'parameters':" +
                "{'" + FORMID + ":submittime_next':'" + FORMID + ":submittime_next'} ," +
                "'actionUrl':window.location.href} );return false;";

        final String LONGFORMATDATE = "yyyy/mm/dd - hh:mm";
        final String SHORTFORMATDATE = "hh:mm";
        final boolean loadMootools = (Boolean) timepicker.isLoadMootools();

        // Allows to initialize the hour in the Javascript.
        String hour = "0";
        String minute = "0";

        boolean loadBuildFile = false;
        boolean outputTop = false;

        outputTop = timepicker.isOutputTop();


        assertValid(context, component);
        ResponseWriter writer = context.getResponseWriter();

        if (timepicker.isLoadCss()) {
            writer.startElement("link", component);
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, TIMEPICKERCSS, null), null);
            writer.writeAttribute(HTML.TYPE_ATTR, "text/css", null);
            writer.writeAttribute(HTML.REL_ATTR, "stylesheet", null);
            writer.endElement("link");
        }

        // Verify if we need to load MooTools in the jsp.
        if (loadMootools) {
            // Loading of Mootools.
            writer.startElement(HTML.SCRIPT_ELEM, component);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, MOOTOOLSSRC, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

        // Verify if we need to load No_Gray library in the jsp.
        if (timepicker.isLoadJs()) {
            loadBuildFile = true;
            // Loading of the plugin Nogray TimePicker.
            writer.startElement(HTML.SCRIPT_ELEM, component);
            writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);
            writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, NOGRAYSRC, null), null);
            writer.endElement(HTML.SCRIPT_ELEM);
        }

        // Writing of the global DIV container.
        writer.startElement(HTML.DIV_ELEM, component);

        writer.writeAttribute(HTML.id_ATTRIBUTE, COMPID, HTML.id_ATTRIBUTE);
        // If the parameter cssClass has been defined for the component, we put this css class for the component.
        final String cssClass;
        if (timepicker.getStyleClass() != null) {
            cssClass = timepicker.getStyleClass();
        } else {
            cssClass = MAINDIVCLASS;
        }

        writer.writeAttribute(HTML.class_ATTRIBUTE, cssClass, "styleClass");

        // adding style value if specified by user.
        if (timepicker.getStyle() != null) {
            writer.writeAttribute(HTML.style_ATTRIBUTE, timepicker.getStyle(), HTML.style_ATTRIBUTE);
        }

        if (outputTop) {
            writer.startElement(HTML.DIV_ELEM, component);
            writer.writeAttribute(HTML.id_ATTRIBUTE, FORMID + ':' + OUTPUTLABELID, null);
            writer.writeAttribute(HTML.class_ATTRIBUTE, OUTPUTLABELTOPCLASS, null);
            Date dateValue = this.retrieveDateValue(component);
            if (dateValue != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(LONGFORMATDATE);
                String date = sdf.format(dateValue);
                writer.write(date);
                // we initialize the hour et minute param for the javascript function.
                hour = date.substring(13, 15);
                minute = date.substring(16, 18);
            } else {
                writer.write(LONGFORMATDATE);
            }

            writer.endElement(HTML.DIV_ELEM);
        }

        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, FORMID + ':' + TIMEPICKERID, null);

        writer.startElement(HTML.IMG_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, FIRSTIMGCLASS, null);
        writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, FIRSTIMGSRC, null), null);

        writer.startElement(HTML.DIV_ELEM, component);
        writer.endElement(HTML.DIV_ELEM);

        writer.startElement(HTML.DIV_ELEM, component);
        writer.endElement(HTML.DIV_ELEM);

        writer.startElement(HTML.IMG_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, SECONDIMGCLASS, null);
        writer.writeAttribute(HTML.src_ATTRIBUTE, ResourcePhaseListener.getURL(context, SECONDIMGSRC, null), null);
        writer.endElement(HTML.IMG_ELEM);

        writer.startElement(LINK, component);
        writer.writeAttribute(HREF, WRONGLINK, HREF);
        writer.endElement(LINK);

        writer.endElement(HTML.DIV_ELEM);

        //adding if not exists the bottomPanel
        boolean existsBottomPanel = false;
        foo:
        for (UIComponent child : timepicker.getChildren()) {
            if (child.getId().equals(TIMEPICKERID + "BottomPanel")) {
                existsBottomPanel = true;
                break foo;
            }
        }
        if (!existsBottomPanel) {
            final HtmlPanelGroup containerBottom = new HtmlPanelGroup();
            containerBottom.setId(TIMEPICKERID + "BottomPanel");
            containerBottom.setStyleClass(BOTTOMDIVCLASS);
            containerBottom.setLayout(BLOCK);
            containerBottom.getChildren().add(this.encodeButton(context, component, TIMEPICKERID + '_' + BUTTONLEFTID, BUTTONLEFTIMG, BUTTONLEFTONCLICK, BUTTONCLASS));

            if (!outputTop) {
                final HtmlOutputLabel outputLabel = new HtmlOutputLabel();
                outputLabel.setId(OUTPUTLABELID);
                outputLabel.setStyleClass(OUTPUTLABELBOTTOMCLASS);
                Date dateValue = this.retrieveDateValue(component);
                if (dateValue != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat(SHORTFORMATDATE);
                    String date = sdf.format(dateValue);
                    outputLabel.setValue(date);
                    // we initialize the hour et minute param for the javascript function.
                    hour = date.substring(0, 2);
                    minute = date.substring(3, 5);
                } else {
                    outputLabel.setValue(OUTPUTLABELVALUE);
                }

                containerBottom.getChildren().add(outputLabel);
            }
            containerBottom.getChildren().add(encodeButton(context, component, TIMEPICKERID + '_' + BUTTONRIGHTID, BUTTONRIGHTIMG, BUTTONRIGHTONCLICK, BUTTONCLASS));
            component.getChildren().add(containerBottom);
        }

        // Verify if we need to load No_Gray library in the jsp.
        if (loadBuildFile) {
            // Load JS File to build the object.
            wrtieActivationScript(context, timepicker, writer);
        }

        writer.startElement(HTML.SCRIPT_ELEM, component);
        writer.write("var " + INSTANCEJS + " = loadTimePicker('" + FORMID + ":" + TIMEPICKERID + "', '" + FORMID + ':' + OUTPUTLABELID + "', " + outputTop + ", " + hour + ", " + minute + ");");
        writer.endElement(HTML.SCRIPT_ELEM);

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

        ResponseWriter writer = context.getResponseWriter();
        writer.endElement(HTML.DIV_ELEM);
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
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
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
    private HtmlGraphicImage encodeButton(FacesContext context, UIComponent component, String id, String img, String onClick, String cssClass) {
        final HtmlGraphicImage imgComp = new HtmlGraphicImage();
        imgComp.setId(id);
        imgComp.setStyle("cursor:pointer;");
        imgComp.setStyleClass(cssClass);
        imgComp.setUrl("/resource.jsf?r=" + img);
        imgComp.setOnclick(onClick);
        return imgComp;
    }

    /**
     * Retrieve the date contained in the Value parameter of the component;
     * @param component It must be an UITimePicker instance to get a date.
     * @return Date The date contained in the value parameter (null else).
     */
    private Date retrieveDateValue(UIComponent component) {
        /*
         * returns the date instance from the component if exists, otherwise @code{null}.
         */
        return (component instanceof UITimePicker && ((UITimePicker) component).getValue() != null) ? ((UITimePicker) component).getValue() : null;
    }

    /**
     * This function write the activation script for targeted components.
     * @TODO the timepicker component should have attributes targetInputId to fill the time value
     * @TODO the timezone must be displayed with the current locale timezone
     * @param context
     * @param timepicker
     * @param writer
     */
    public void wrtieActivationScript(FacesContext context, UITimePicker timepicker, ResponseWriter writer) throws IOException {
        writer.startElement(HTML.SCRIPT_ELEM, timepicker);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.TEXTJAVASCRIPT_VALUE, null);

        //@TODO write decreaseTimePicker function with targetInputId as attribute value
        writer.write("function decreaseTimePicker(timepicker, targetInputId){var minutes=timepicker.time.minute;" +
                "if(minutes==0){minutes=59;}else{minutes-=1;}updateTimePicker(timepicker,minutes,targetInputId);" +
                "}\n");
        //@TODO write increaseTimePicker function with targetInputId as attribute value
        writer.write("function increaseTimePicker(timepicker, targetInputId){var minutes=timepicker.time.minute;" +
                "if(minutes==59){minutes=0;this.previousMinute=59;}else{minutes+=1;}updateTimePicker(timepicker,minutes,targetInputId);" +
                "}\n");
        writer.write("function updateTimePicker(timepickerComp,minutes,datepickerId) {if (typeof timepickerComp != 'undefined') {" +
                "timepickerComp.time.minute = minutes;timepickerComp.updateField();timepickerComp.moveHands();timepickerComp.fireEvent('onChange');" +
                "}}");
        //@TODO the function loadTimePicker needs to take in arguments targetId for the inputText components that will be completed by a datepicker component.
        writer.write("function loadTimePicker(tpId, tpValueId, onTop, heure, minute) {" +
                "var timepicker = new TimePicker(tpId, null, null, {imagesPath:'" + ResourcePhaseListener.getURL(context, "/org/widgetfaces/widget/timepicker/img", null) + "', visible:true,offset:1000," +
                "startTime: {hour:heure, minute:minute}, previousMinute: minute,onChange:function(){" +
                "if (this.time.hour < 12) var ampm = this.options.lang.am;else var ampm = this.options.lang.pm;var hour = this.time.hour%12;" +
                "var hourinput = this.time.hour;if ((this.time.minute == 0) && (this.previousMinute != 0)) {" +
                "if (this.previousMinute <= 30) {this.time.hour--;} else if (30 < this.previousMinute) {this.time.hour++;}}" +
                "this.previousMinute = this.time.minute;this.updateAmPm();if (hour < 10) hour = '0'+hour;if (hour == 0 && ampm == this.options.lang.am) hour = '12';" +
                "var minute = this.time.minute;if (minute < 10) minute = '0'+minute;if(!onTop && $(tpValueId))$(tpValueId).innerHTML=hour+':'+minute+' '+ampm;" +
                "else {var inputvalue = $(tpValueId).value+'T';var substr = inputvalue.substring(0, inputvalue.indexOf('T'));if($(tpValueId)){$(tpValueId).innerHTML = '';" +
                "$(tpValueId).innerHTML = substr+'T'+hourinput+':'+minute+':'+new Date().getSeconds()+'+01' ;}}}" +
                "});return timepicker;}");
        writer.endElement(HTML.SCRIPT_ELEM);
    }
}
