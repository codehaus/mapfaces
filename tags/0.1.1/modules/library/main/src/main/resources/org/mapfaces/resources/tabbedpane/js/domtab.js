function display(divContainer, toActivate){
    var toDesactivate = whosActivated(divContainer);
    //Remove "item:" from the id
    var idToDesactivate = toDesactivate.id.substring(5, toDesactivate.id.length);
    document.getElementById(idToDesactivate).className="tabs_panel";
    toDesactivate.className="";
    
    document.getElementById(toActivate).className="tabs_panel active";
    document.getElementById("item:"+toActivate).className="active";
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