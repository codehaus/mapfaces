function display(divContainer, toActivate){
    var toDesactivate = whosActivated(divContainer);
    
    document.getElementById(toDesactivate).setAttribute("class","tabs_panel");
    document.getElementById("item:"+toDesactivate).setAttribute("class", "");
    
    document.getElementById(toActivate).setAttribute("class", "tabs_panel active");
    document.getElementById("item:"+toActivate).setAttribute("class", "active");
}

function whosActivated(divContainer){
    var Container = document.getElementById(divContainer);
    var Ul = Container.getElementsByTagName("li");
    for(i=0;i<Ul.length;i++){
        var li = Ul[i];
      if (li.hasAttribute("class")){
          if(li.getAttribute("class") == "active"){
              return li.innerHTML;
          }
      }
    }
    return false;
}