<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<f:view>
			<h:form id="form1">	
				<%--c:chart 
					datasource="#{BarCharts.sourceProvider.categoryDataset}" 
                                        output="png"
					type="stackedbar" is3d="true" 
                                        colors="magenta,#CACACA,blue" 
                                        title="Chart with Clickable Regions and Tooltips" 
					orientation="vertical" 
                                        usemap="#stackedbarmap" generateMap="stackedbarmap" 
                                        ongeneratedimagemapclick="stackedChartClick">
					<c:chartAxis domain="true" 
                                        verticalTickLabels="true"/>
				</c:chart--%>
				<c:chart id="chart1" 
                                datasource="#{BarCharts.sourceProvider.categoryDataset}" 
                                type="#{BarCharts.type}" is3d="true" 
                                legend="false" xlabel="XXX" 
                                ylabel="YYY" foreground="red" output="svg"
                                usemap="#stackedbarmapp" generateMap="stackedbarmapp" 
                                ongeneratedimagemapclick="stackedChartClick"/>
				<c:chart id="chart8" datasource="#{BarCharts.sourceProvider.categoryDataset}" 
                                output="svg" type="bar" is3d="false" 
                                background="#FFFFCC" title="I love JSFs Chart Creator"
                                usemap="#stackedbarmappp" generateMap="stackedbarmappp" 
                                ongeneratedimagemapclick="stackedChartClick"/>
                                <c:chart id="chart6" datasource="#{BarCharts.sourceProvider.categoryDataset}" 
                                output="svg" type="bar" is3d="false" 
                                background="#FFFFCC" title="I love JSFs Chart Creator"
                                usemap="#stackedbarmapppp" generateMap="stackedbarmapppp" 
                                ongeneratedimagemapclick="stackedChartClick"/>
				<c:chart id="chart3" datasource="#{BarCharts.sourceProvider.categoryDataset}"  output="svg" type="bar" is3d="false" orientation="horizontal" height="150" width="200" title="Forca Barca"></c:chart>
				<c:chart id="chart4" datasource="#{BarCharts.sourceProvider.categoryDataset}" output="svg" type="stackedbar" is3d="true" orientation="horizontal" background="yellow" xlabel="X" ylabel="Y"></c:chart>
				<c:chart id="chart5" datasource="#{BarCharts.sourceProvider.categoryDataset}" output="svg" type="bar" is3d="false" antialias="true" outline="true" legend="false" background="#33CC33"></c:chart>
				
                                <script>
					function stackedChartClick(data) {
						alert(data);
					}
				</script>	
                                <a onclick="addEvents();">addEvents()</a>
			</h:form>
		</f:view>
	</body>	
</html>  
