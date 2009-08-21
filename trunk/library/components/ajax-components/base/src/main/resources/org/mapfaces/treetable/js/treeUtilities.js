var TREETABLE = new Hash({
    version: '0.0.1 development',
    author: 'kdelfour'
});

TREETABLE.switchView = function(parentID, children){
    var parentnode = document.getElementById(parentID);
    var parentClass = parentnode.getAttribute('class');
    var nextNodeClass = parentnode.nextSibling.getAttribute('class');
    var reg, regfolder;
    if (parentClass.search('treenode-expand_symbol') > -1){
        reg = new RegExp("treenode-expand_symbol", "g");
        parentClass = parentClass.replace(reg, 'treenode-collapse_symbol');
        regfolder = new RegExp("treeicon treenode-expanded", "g");
        nextNodeClass = nextNodeClass.replace(regfolder, 'treeicon treenode-collapsed');
        TREETABLE.collapse(children);
    }else{
        reg = new RegExp("(treenode-collapse_symbol)", "g");
        parentClass = parentClass.replace(reg, 'treenode-expand_symbol');
        regfolder = new RegExp("treeicon treenode-collapsed", "g");
        nextNodeClass = nextNodeClass.replace(regfolder, 'treeicon treenode-expanded');
        TREETABLE.expand(children);
    }
    parentnode.nextSibling.setAttribute("class", nextNodeClass);
    parentnode.setAttribute("class", parentClass);
}

TREETABLE.collapse = function(children){
    document.getElementById(children).setAttribute('style', 'display:none');
}

TREETABLE.collapseAll = function(){
    var list = document.getElementsByTagName('.ul-pattern');
    for(i=0; i<list.length; i++){
        list[i].setAttribute('style', 'display:none');
    }
}

TREETABLE.expand = function(children){
    document.getElementById(children).setAttribute('style', 'display:block');
}

TREETABLE.expandAll = function(){
    var list = document.getElementsByTagName('.ul-pattern');
    for(i=0; i<list.length; i++){
        list[i].setAttribute('style', 'display:block');
    }
}
