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
	</head>
        <body>
            <h:form>
                <h:commandButton value="submit" style="position:relative;" />
                <mf-model:Context minifyJS="false"  service="data/context/ifremer.xml">                              
                        <mf:MapPane></mf:MapPane>
                        <mf:LayerControl minifyJS="false" ></mf:LayerControl>
                </mf-model:Context>
            </h:form>
	</body>
    </html>
</f:view>