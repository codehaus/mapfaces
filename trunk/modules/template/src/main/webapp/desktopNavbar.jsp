<%@taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<div id="desktopHeader">
    <!-- Banniere -->
    <div id="desktopTitlebarWrapper" style="height:65px;">
        <div id="desktopTitlebar">
            <div id="topNav">
                <ul class="menu-right">
                    <li><a href="#" onclick="MochaUI.notification('Do Something');return false;">Login</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <!-- navigation bar -->
    
    <div id="desktopNavbar">
        <ul>
            <li>
                <a4j:commandLink id="homea4jlink" onclick=" if(_Home_Tabs){return false;}else{_Home_Tabs=true;_App_Tabs=false;}" 
                                 oncomplete="homePageWrapper();" 
                                 reRender="toRefresh" 
                                 actionListener="#{switchTab.goHomeActionEvent}">
                    <h:outputText id="homelinklabel" value="Home"/>
                </a4j:commandLink>
            </li>
            
            <li>
                <a4j:commandLink id="geovisualizationa4jlink" onclick="if(_App_Tabs){return false;}else{_Home_Tabs=false;_App_Tabs=true;}" 
                                 oncomplete="webAppPageWrapper(); main_formapp_Mappane.zoomToMaxExtent();" 
                                 reRender="toRefresh" 
                                 actionListener="#{switchTab.goAppActionEvent}">
                    <h:outputText id="geovizualizationlinklabel" value="Application"/>
                </a4j:commandLink>
            </li>
            
            <li><a class="returnFalse" href="">Tools</a>
                <ul>
                    <li>
                        <a class="returnFalse arrow-right" href="">Workspace</a>
                        <ul>
                            <li><a id="saveWorkspaceLink" href="">Save Workspace</a></li>
                            <li><a id="loadWorkspaceLink" href="">Load Workspace</a></li>
                        </ul>
                    </li>
                    <li><a class="returnFalse arrow-right" href="">View</a>
                        <ul>
                            <li><a id="cascadeLink" href="">Cascade Windows</a></li>
                            <li><a id="tileLink" href="">Tile Windows</a></li>
                            <li><a id="minimizeLink" href="">Minimize All Windows</a></li>
                            <li><a id="closeLink" href="">Close All Windows</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li><a class="returnFalse" href="">Help</a>
                <ul>
                    <li class="divider"><a class="returnFalse" href="#">Documentation</a></li>
                    <li><a id="aboutGeomatysLink" href="AboutGeomatys.jsf">About Geomatys</a></li>
 		    <li><a id="loggerpopup" href="">Show logs panel</a></li>
                </ul>
            </li>
        </ul>
        
        <div class="toolbox divider2">
            <div id="spinnerWrapper"><div id="spinner"></div></div>
        </div>
        
        <div class="toolbox divider2">
            <img src="resources/img/icons/cog.gif" onclick="MochaUI.notification('Do Something');" width="16" height="16" alt="" />
            <img src="resources/img/icons/windows.gif" onclick="MochaUI.notification('Do Something');" width="16" height="16" alt="" />
            <img src="resources/img/icons/sheet.gif" onclick="MochaUI.notification('Do Something');" width="16" height="16" alt="" />
        </div>
        
    </div>
    
</div>

<!-- float bar -->
<div id="dockWrapper" style="background:#4C4C4C none repeat scroll 0%">
    <div id="dock">
        <div id="dockPlacement" style="display:none"></div>
        <div id="dockAutoHide" style="display:none"></div>
        <div id="dockSort">
		<div id="dockClear" class="clear">
		</div>
	</div>
    </div>
</div>

<style>
#dockCanvas{display:none;}
</style>






