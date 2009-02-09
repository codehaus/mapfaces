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
				<%--c:chart output="svg" id="chart1" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" depth="50" title="Barca" alpha="75" is3d="true" startAngle="90" legend="false" colors="magenta,#CACACA,blue"></c:chart>
				<c:chart output="svg" id="chart2" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" title="What a nice chart" is3d="true" alpha="75" startAngle="90" legend="false"></c:chart>
				<c:chart output="svg" id="chart3" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" height="150" width="200"></c:chart>
				<c:chart output="svg" id="chart4" datasource="#{PieCharts.sourceProvider.pieDataset}" type="ring" is3d="true" alpha="90" startAngle="25" background="#0066CC"></c:chart>
				<c:chart output="svg" id="chart5" datasource="#{PieCharts.sourceProvider.pieDataset}" title="I love JSF Chart" type="pie" is3d="true"></c:chart>
				<c:chart output="svg" id="chart6" datasource="#{PieCharts.sourceProvider.pieDataset}" type="ring" is3d="false" depth="20" alpha="75" startAngle="180" background="yellow"></c:chart>
				--%>
                                <c:chart output="svg" id="chart7" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" title="Denedmemoos" is3d="false" depth="0" legend="false"></c:chart>
				<%--c:chart id="chart11" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" depth="50" title="Barca" alpha="75" is3d="true" startAngle="90" legend="false" colors="magenta,#CACACA,blue"></c:chart>
				<c:chart id="chart12" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" title="What a nice chart" is3d="true" alpha="75" startAngle="90" legend="false"></c:chart>
				<c:chart id="chart13" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" height="150" width="200"></c:chart>
				<c:chart id="chart14" datasource="#{PieCharts.sourceProvider.pieDataset}" type="ring" is3d="true" alpha="90" startAngle="25" background="#0066CC"></c:chart>
				<c:chart id="chart15" datasource="#{PieCharts.sourceProvider.pieDataset}" title="I love JSF Chart" type="pie" is3d="true"></c:chart>
				<c:chart id="chart16" datasource="#{PieCharts.sourceProvider.pieDataset}" type="ring" is3d="false" depth="20" alpha="75" startAngle="180" background="yellow"></c:chart>
				<c:chart id="chart17" datasource="#{PieCharts.sourceProvider.pieDataset}" type="pie" title="Denedmemoos" is3d="true" depth="0" legend="false"></c:chart>
                                --%>
                        </h:form>
		</f:view>
	</body>	
</html>  

