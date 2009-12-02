function display(divContainer, toActivate){
    var toDesactivate = whosActivated(divContainer);
    //Remove "item:" from the id
    var idToDesactivate = toDesactivate.id.substring(5, toDesactivate.id.length);
    if(document.getElementById(idToDesactivate)) {
        document.getElementById(idToDesactivate).className="tabs_panel";
    }
    toDesactivate.className="";
    
    if(document.getElementById(toActivate)) {
      document.getElementById(toActivate).className="tabs_panel active";
    }
    if (document.getElementById("item:"+toActivate)) {
        document.getElementById("item:"+toActivate).className="active";
    }   
}

function whosActivated(divContainer){
    var Container = document.getElementById(divContainer);
    var Ul = Container.getElementsByTagName("li");
    for(i=0;i<Ul.length;i++){
        var li = Ul[i];
        if(li.className == "active"){
              return li;
        }
    }
    return false;
}