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
				<c:chart output="svg" id="chart1" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" legend="false" xlabel="X" ylabel="Y"></c:chart>
				<c:chart output="svg" id="chart2" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="stackedarea"  background="#FFFFCC" title="I love JSF Chart Creator"></c:chart>
				<c:chart output="svg" id="chart3" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" orientation="horizontal" height="150" width="200"></c:chart>
				<c:chart output="svg" id="chart4" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" orientation="vertical" background="yellow" xlabel="X" ylabel="Y"></c:chart>
				<c:chart output="svg" id="chart5" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="stackedarea" antialias="true" outline="true" legend="false" background="#33CC33"></c:chart>
				<c:chart output="svg" id="chart6" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" title="Forca Barca" orientation="horizontal"></c:chart>
                                <c:chart id="chart11" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" legend="false" xlabel="X" ylabel="Y"></c:chart>
				<c:chart id="chart12" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="stackedarea"  background="#FFFFCC" title="I love JSF Chart Creator"></c:chart>
				<c:chart id="chart13" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" orientation="horizontal" height="150" width="200"></c:chart>
				<c:chart id="chart14" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" orientation="vertical" background="yellow" xlabel="X" ylabel="Y"></c:chart>
				<c:chart id="chart15" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="stackedarea" antialias="true" outline="true" legend="false" background="#33CC33"></c:chart>
				<c:chart id="chart16" datasource="#{AreaCharts.sourceProvider.categoryDataset}" type="area" title="Forca Barca" orientation="horizontal"></c:chart>
			
                        </h:form>
		</f:view>
	</body>	
</html>  
