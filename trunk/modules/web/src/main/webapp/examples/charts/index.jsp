<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>

<f:view>
    <html xml:lang="en" lang="en" dir="ltr">
        
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
            <script type="text/javascript">                                                                        
                function onloadChart(embed) {               
                };
            </script>
        </head>        
        <body>
            <br/>
            <h1><h:outputText value="JavaServer Faces" /></h1>
            <div id="canvas3" class="canvas" style="border: 1px solid black; width: 1000px; height: 500px; position: relative;">
                <div id="canvasi3" class="canvas" style="position: relative; width: 1000px; height: 500px; cursor: move; left: 0pt; top: 0pt;">
                    <%--embed  width="1000" height="500" src="drag.svg" type="image/svg+xml" onload="onloadChart(this);"/--%>
                    <%--embed  width="1000" height="500" src="drag.svg" type="image/svg+xml" onload="onloadChart(this);"/--%>
                    <object id="form1:chart2" height="300" border="0" width="600" 
                    data="chart.svg" 
                    pluginspage="http://www.adobe.com/svg/viewer/install/main.html" type="image/svg+xml">
                </div>
            </div>       
            <a onclick="addEvents();">addEvents</a>
        </body>
        
    </html>
</f:view>
