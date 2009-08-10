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
        <body >         
            <iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
                <h:form>
                    <h:commandButton value="submit" style="margin-left:-60px;left:50%;top:300px;width:100px;z-index:1000;position:absolute;"/>
                    <mf-model:Context minifyJS="false"  debug="true" service="data/context/owc030.xml">
                            <mf:MapPane/>	
                            <mf:ScaleBar style="background-color: red;"/>              
                     </mf-model:Context>
                </h:form>
	</body>
    </html>
</f:view>
 