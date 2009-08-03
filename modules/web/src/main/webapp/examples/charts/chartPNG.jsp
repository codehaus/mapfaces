<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head></head>
        <body>
            <h1>PNG chart example</h1>
            <br/>
            <h:form>
                <h:commandButton value="submit" />
                <c:chart output="png"
                         background="white"
                         id="chartSVG"
                         width="800"
                         height="400"
                         rangeGridLines="true"
                         domainGridLines="true"
                         datasource="#{OtherCharts.sourceProvider.multipleXYDataset}"
                         title="Multiple XYDataset Demo"
                         type="timeseries"
                         legend="true"
                         xlabel="Time of Day"
                         ylabel="Primary Axes" >
                </c:chart>
            </h:form>
           
        </f:view>
    </body>
</html>  