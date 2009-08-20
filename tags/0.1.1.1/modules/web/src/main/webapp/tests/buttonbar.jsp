<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
 
<f:view>
    <html>
        <head>
            <script type="text/javascript">
            function handleMeasurements(event) {
                var geometry = event.geometry;
                var units = event.units;
                var order = event.order;
                var measure = event.measure;
                var element = document.getElementById('output');
                var out = "";
                if(order == 1) {
                    out += "measure: " + measure.toFixed(3) + " " + units;

                } else {
                    out += "measure: " + measure.toFixed(3) + " " + units + "<sup>2</" + "sup>";
                }
                element.innerHTML = out;
            }
            </script>
	</head>
        <body>
            
            <iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
            <h:form>
                <mf-model:Context minifyJS="false"  debug="true" service="data/context/owc030.xml">                              
                        <mf:MapPane debug="true"></mf:MapPane>
			<mf:ButtonBar styleClass="mfButtonBar horizontal" save="true" style="top:350px"/>
                </mf-model:Context>
                <h:commandButton value="submit" style="margin-left:60px;z-index:1000;position:relative;"/>
            </h:form>
            <div id='output'></div>
	</body>
    </html>
</f:view>
 