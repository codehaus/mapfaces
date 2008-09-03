package org.mapfaces.component.timeline;

import javax.faces.context.FacesContext;
import java.io.IOException;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ExternalContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;
 
public class UISliderInput extends UIComponentBase {

    public UISliderInput() {
        setRendererType(null);       // this component renders itself
    }
    private String maxval = "100";  // default
    private // default
            String forid = "0";
    private String horizontal = "true";
    private // default
            String length = "300";
    final static String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        //Write the scripts once per page
        ExternalContext extContext = context.getExternalContext();
        if (!extContext.getRequestMap().containsKey("ajaxflag.sliderScript")) {
            extContext.getRequestMap().put("ajaxflag.sliderScript", Boolean.TRUE);
            writeSilderScripts(context);
        }

        if (getAttributes().get("maxval") != null) {
            setMaxval((String) getAttributes().get("maxval"));
        }
        if (getAttributes().get("for") != null) {
            setForid((String) getAttributes().get("for"));
        }
        if (getAttributes().get("horizontal") != null) {
            setHorizontal((String) getAttributes().get("horizontal"));
        }
        if (getAttributes().get("length") != null) {
            setLength((String) getAttributes().get("length"));
        }
        String comp_id = getId().replace("-", "_");
        String valchangehandler = "valChangeHandler" + comp_id;
        String destinationDiv = "dest" + comp_id;

        // prepend base client Id to forid
        //forid = getBaseClientId(context) + forid;


        ResponseWriter writer = context.getResponseWriter();

        // Render client Javascript for slider
        writer.write("<div id=\"" + destinationDiv + "\"><!-- slider goes here --></div>");
        String idlabel = comp_id.concat("-label");

        String intervalUnit = "";
        int strtResult = 0;
        UICommand bandInfo = null;
        if (getParent() instanceof UIBandInfo) {
            bandInfo = (UIBandInfo) getParent();
            intervalUnit = ((UIBandInfo) bandInfo).getIntervalUnit();

        } else if (getParent() instanceof UIHotZoneBandInfo) {
            bandInfo = (UIHotZoneBandInfo) getParent();
            intervalUnit = ((UIHotZoneBandInfo) bandInfo).getIntervalUnit();
        }
        
        int startValue = getIndexFromUnit(intervalUnit);
        strtResult = (startValue * 2 * 100) / 22;

        writer.startElement("label", this);
        writer.writeAttribute("id", idlabel, "id");
        writer.write(intervalUnit);
        writer.endElement("label");


        String idjs = bandInfo.getId().replace("-", "_");
        writer.write("<script>var " + destinationDiv + " = document.getElementById(\"" + destinationDiv + "\"); " +
                destinationDiv + ".appendChild(JSSlider.getInstance(\"" +
                comp_id + "\", " + getHorizontal() + ", " +
                "10, " + getLength() + ", " + strtResult + ", undefined, undefined, \"" + valchangehandler + "\", false).render());\n" +
                " function " + valchangehandler + "(newStartPercent0To100, newEndPercent0To100) " +
                "{ var slideVal = Math.round(" +
                getMaxval() + "*newStartPercent0To100/100); \n");

        writer.write("var interval;\n" +
                "if (slideVal <= 2){\n" +
                "interval = 'MILLISECOND';}\n" +
                "if (slideVal <= 4 && slideVal > 2){\n" +
                "interval = 'SECOND';}\n" +
                "if (slideVal <=6 && slideVal >4){\n" +
                "interval = 'MINUTE';}\n" +
                "if (slideVal <=8 && slideVal >6){\n" +
                "interval = 'HOUR';}\n" +
                "if (slideVal <=10 && slideVal >8){\n" +
                "interval = 'DAY';}\n" +
                "if (slideVal <=12 && slideVal >10){\n" +
                "interval = 'WEEK';}\n" +
                "if (slideVal <=14 && slideVal >12){\n" +
                "interval = 'MONTH';}\n" +
                "if (slideVal <=16 && slideVal >14){\n" +
                "interval = 'YEAR';}\n" +
                "if (slideVal <=18 && slideVal >16){\n" +
                "interval = 'DECADE';}\n" +
                "if (slideVal <=20 && slideVal >18){\n" +
                "interval = 'CENTURY';}\n" +
                "if (slideVal <=22 && slideVal >20){\n" +
                "interval = 'MILLENNIUM';}\n" +
                "document.getElementById(\"" +
                //getBaseClientId(context) + 
                idlabel + "\").innerHTML = interval; \n" +
                idjs + "_changeIntervalUnit(" + getForid() + ",'Timeline.DateTime.'+interval);" + "}</script>");
    }

    public void writeSilderScripts(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        writer.startElement("script", this);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/timeline/slider/js/JSSlider.js", null), null);
        writer.writeAttribute("type", "text/javascript", null);
        writer.endElement("script");
    }

    private String getBaseClientId(FacesContext context) {
        // Simple method to return "base" of ClientId
        String clientId = getClientId(context);
        return clientId.substring(0, clientId.indexOf(":") + 1);

    }

    @Override
    public String getFamily() {
        // This component renders itself
        return null;
    }

    public String getMaxval() {
        return maxval;
    }

    public void setMaxval(String maxval) {
        this.maxval = maxval;
    }

    public String getForid() {
        return forid;
    }

    public void setForid(String forid) {
        this.forid = forid;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getIndexFromUnit(String intervalUnit) {
        for (int i = 0; i < intervalNames.length; i++) {
            if (intervalNames[i].equals(intervalUnit)) {
                return intervalNames.length - i - 1;
            }
        }
        return -1;
    }
}
