<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
	</head>
        <body>
            <h:form>
            <mf:TreeTable id="treetable" value="#{map.exampleModel}" var="itemExample" style="width:100%;height:100%;overflow:auto;" debug="false">
                <mf:TreePanel id="treepanel"
               header="false"
               rowId="false"
               frame="false"
               showRoot="false"
               enableDragDrop="false"
               styleLeaf="background:#f3fbff;"
               styleNode="background:#d6e3f2;"
               loadAll="false"
               debug="false"
               >
                    <mf:TreeColumn  id="tree_column"
                     headerTitle="Equipment"
                     value="#{itemExample.name}"
                     style="width:200px;"
                     styleHeader="width:200px;">
                    </mf:TreeColumn>
                    <mf:Column id="readrightsRoleCheckbox" style="width:50px;overflow-x:auto;" styleHeader="width:50px" headerTitle="R">
                        <h:selectBooleanCheckbox id="readrightchecboxInput" value="true" />
                    </mf:Column>
                    <mf:Column id="writerightsRoleCheckbox" style="width:50px;overflow-x:auto;" styleHeader="width:50px" headerTitle="W">
                        <h:selectBooleanCheckbox id="writerightchecboxInput" value="true" />
                    </mf:Column>
                </mf:TreePanel>
            </mf:TreeTable>
                <h:commandButton value="submit" style="position:relative;" />
                <mf-model:Context  service="data/context/owc030Cut.xml" scriptaculous="false">
                        <mf:MapPane></mf:MapPane>
                        <mf:LayerControl id="treetabl" style="height:300px;" ></mf:LayerControl>
                </mf-model:Context>
               
            </h:form>
	</body>
    </html>
</f:view>