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
				<c:chart id="chart1" datasource="#{Events.sourceProvider.categoryDataset}" type="bar" is3d="true" legend="false" xlabel="X" ylabel="Y" foreground="orange" output="jpeg"></c:chart>
				<c:chart id="chart2" datasource="#{Events.sourceProvider.categoryDataset}" type="bar" is3d="false" background="#FFFFCC" title="I love JSFs Chart Creator" onclick="window.alert('Chart Clicked');"></c:chart>
				<c:chart id="chart3" datasource="#{Events.sourceProvider.categoryDataset}" type="bar" is3d="false" orientation="horizontal" height="150" width="200" onmouseout="window.alert('Cursor out of Chart');"></c:chart>
				<c:chart id="chart4" datasource="#{Events.sourceProvider.categoryDataset}" type="bar" is3d="true" legend="false" xlabel="XXX" ylabel="YYY" foreground="yellow" title="Chart with Image Map" output="jpeg" usemap="#green">
					<map name="green">
						<area shape="polygon" coords="19,44,45,11,87,37,82,76,49,98" href="#">
						<area shape="rect" coords="128,132,241,179" href="#">
						<area shape="circle" coords="68,211,35" href="#">
					</map>
				</c:chart>
			</h:form>
		</f:view>
	</body>	
</html>  
