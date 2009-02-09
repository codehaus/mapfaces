<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <f:view>
            <h:form id="form1">	
                <%--c:chart output="svg" id="chart1" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xystep" legend="false" colors="magenta,#CACACA,blue" xlabel="X" ylabel="Y"></c:chart>
                --%><c:chart output="svg" id="chart2" width="600" height="400" rangeGridLines="true" domainGridLines="true" datasource="#{OtherCharts.sourceProvider.multipleXYDataset}" title="Multiple Axis Demo 1" type="timeseries" legend="true" xlabel="Time of Day" ylabel="Primary Axes"></c:chart>
                <%--c:chart output="svg" id="chart3" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xyline" background="#FFFFCC" title="I love JSF Chart Creator"></c:chart>
                                <c:chart output="svg" id="chart4" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="polar" orientation="horizontal" height="150" width="200"></c:chart>
                                --%>
                <%--c:chart output="svg" id="chart5"  width="400" height="300" datasource="#{OtherCharts.sourceProvider.XYDataset}" 
                domainGridLines="true" type="scatter" orientation="vertical" 
                background="yellow" xlabel="X" ylabel="Y"></c:chart--%>
                                <%--c:chart output="svg" id="chart6" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xyarea" antialias="true" outline="true" legend="false" background="#33CC33"></c:chart>
                                <c:chart output="svg" id="chart7" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xysteparea" title="Forca Barca" orientation="horizontal"></c:chart>
                                <c:chart output="svg" id="chart8" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="histogram" colors="magenta,#CACACA,blue" title="Histogram" orientation="horizontal"></c:chart>
                                --%>
                                <%--c:chart output="svg" id="chart9" legendFontSize="11"
                                        datasource="#{OtherCharts.sourceProvider.firstXYDataset}"
                                        type="xyline" colors="magenta" title="Company Analisys"
                                        xlabel="Date" ylabel="Price" orientation="vertical">
                                        <c:chartAxis id="sentimentAxis1" label="Sentiment" colors="blue" tickMarks="false" tickLabels="false"
                                                datasource="#{OtherCharts.sourceProvider.secondXYDataset}" tickLabelFontSize="12"/>
                                </c:chart--%>	
                                <%--c:chart id="chart11" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xystep" legend="false" colors="magenta,#CACACA,blue" xlabel="X" ylabel="Y"></c:chart>
                                <c:chart id="chart12" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="timeseries" legend="false" xlabel="X" ylabel="Y"></c:chart>
                                <c:chart id="chart13" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xyline" background="#FFFFCC" title="I love JSF Chart Creator"></c:chart>
                                <c:chart id="chart14" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="polar" orientation="horizontal" height="150" width="200"></c:chart>
                                <c:chart id="chart15" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="scatter" orientation="vertical" background="yellow" xlabel="X" ylabel="Y"></c:chart>
                                <c:chart id="chart16" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xyarea" antialias="true" outline="true" legend="false" background="#33CC33"></c:chart>
                                <c:chart id="chart17" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="xysteparea" title="Forca Barca" orientation="horizontal"></c:chart>
                                <c:chart id="chart18" datasource="#{OtherCharts.sourceProvider.XYDataset}" type="histogram" colors="magenta,#CACACA,blue" title="Histogram" orientation="horizontal"></c:chart>
                                <c:chart id="chart19" legendFontSize="11"
                                        datasource="#{OtherCharts.sourceProvider.firstXYDataset}"
                                        type="xyline" colors="magenta" title="Company Analisys"
                                        xlabel="Date" ylabel="Price" orientation="vertical">
                                        <c:chartAxis id="sentimentAxis2" label="Sentiment" colors="blue" tickMarks="false" tickLabels="false"
                                                datasource="#{OtherCharts.sourceProvider.secondXYDataset}" tickLabelFontSize="12"/>
                                </c:chart--%>					
            </h:form>
            
            <h2>Instructions : </h2>
            <p >Zoom in : wheel up 
            Zoom out : wheel down </p>
            Zoom box : CTRL+ mouse left click and drag
            Drag : mouse left click and drag </p>
        </f:view>
        <script type="text/javascript">
                function onSubmit() {
                    //alert(document.getElementsByTagName("object").item(0).cloneNode(true));
                    
                    if (document.getElementsByTagName("object").item(0)){                        
                        var obj = document.createElement("object");
                        var id = document.getElementsByTagName("object").item(0).id;                        
                        obj.setAttribute("id", id);
                        obj.style.display = "none";
                        document.getElementsByTagName("object").item(0).id="clone";
                        document.forms[0].insertBefore(obj,document.getElementById("clone"));
                    } else if (document.getElementsByTagName("embed").item(0)) {
                        var obj = document.createElement("embed");
                        var id = document.getElementsByTagName("embed").item(0).id;
                        obj.setAttribute("id", id);
                        obj.style.display = "none";
                        document.getElementsByTagName("embed").item(0).id="clone";
                        document.forms[0].insertBefore(obj,document.getElementById("clone"));
                    }
                }
                function onObjectLoad() {
                     if (document.getElementsByTagName("object").item(0))
                          document.getElementsByTagName("object").item(0).style.display="block";
                     if (document.getElementsByTagName("embed").item(0))
                          document.getElementsByTagName("embed").item(0).style.display="block";
                     if(document.getElementById("clone") != null)
                          document.forms[0].removeChild(document.getElementById("clone"));
                }
        </script>
    </body>	
</html>  
