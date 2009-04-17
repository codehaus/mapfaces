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
				<c:chart output="svg" id="chart1" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" is3d="true" colors="magenta,#CACACA,blue" legendBorder="false" legendFontSize="24"></c:chart>
				<c:chart output="svg" id="chart2" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" title ="What a nice component" is3d="true" orientation="vertical"></c:chart>
				<c:chart output="svg" id="chart3" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" width="150" height="200"></c:chart>
				<c:chart output="svg" id="chart4" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" legend="false" background="yellow"></c:chart>
				<c:chart output="svg" id="chart5" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" legend="true" width="600" height="300" title="Forca Barca"></c:chart>
				<c:chart output="svg" id="chart6" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" background="orange" orientation="vertical"></c:chart>
                                <c:chart id="chart11" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" is3d="true" colors="magenta,#CACACA,blue" legendBorder="false" legendFontSize="24"></c:chart>
				<c:chart id="chart12" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" title ="What a nice component" is3d="true" orientation="vertical"></c:chart>
				<c:chart id="chart13" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" width="150" height="200"></c:chart>
				<c:chart  id="chart14" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" legend="false" background="yellow"></c:chart>
				<c:chart  id="chart15" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" legend="true" width="600" height="300" title="Forca Barca"></c:chart>
				<c:chart  id="chart16" datasource="#{LineCharts.sourceProvider.categoryDataset}" type="line" background="orange" orientation="vertical"></c:chart>
			
                        </h:form>
		</f:view>
	</body>	
</html>  
