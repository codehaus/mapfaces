<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head></head>
        <body>
            <h1>MapPane example</h1>
            <br/>
            <h:form>
                <h:commandButton value="submit"/>
                <rich:panel header="Simple Echo">
                    <h:outputText value="Your message: "/>
                    <h:inputText size="50" value="#{echo.text}">
                       <a4j:support event="onkeyup" reRender="rep"/>
                    </h:inputText>
                    <h:outputText id="rep" value="#{echo.text}"/>
                 </rich:panel>
                <%--mf-model:Context service="file://.sicade/blueMarble.xml"--%>
                <%--<mf-model:Context service="file:///home/olivier/svn/mapfaces/trunk/web/examples/src/main/webapp/data/context/blueMarble.xml">--%>
                <%--<mf-model:Context service="data/context/blueMarble.xml">--%>
                <%--mf-model:Context minifyJS="false" service="/data/context/blueMarble.xml">
                    <mf:MapPane navigation="true"></mf:MapPane>
                </mf-model:Context--%>
            </h:form>
        </body>
    </html>
</f:view>