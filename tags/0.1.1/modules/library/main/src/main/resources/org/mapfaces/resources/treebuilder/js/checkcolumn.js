function checkNode(formId,panelId,nodeId,checkcolumnId){
    var checkcolumn = document.getElementById(formId+':'+checkcolumnId).checked;
    var line2change = document.getElementById('li:'+formId+':'+panelId+':'+nodeId);
    if (checkcolumn==false){
        if (browser=="Microsoft Internet Explorer"){
            line2change.style.opacity='0.2';
        }else{
            line2change.tween('opacity',0.2);
        }
        if(line2change.style.color!='white'){
            line2change.style.display= 'none';
        }
    }
    else {
        if (browser=="Microsoft Internet Explorer"){
            line2change.style.opacity='1';
        }else{
            line2change.tween('opacity',1);
        }
        if(line2change.style.color!='white'){
            line2change.style.display= 'block';
        }
    }
}

function checkAllInputs(panelTemplateId, panelTargetId){
    var check;
    $$('input').each(function(div) {
        if(div.id.contains(panelTemplateId)){
            if (check==null){
                check = !div.checked;
            }
            div.checked = check;
            showAllLines(panelTargetId,check);
            
        }
    });
}

function showAllLines(panelTargetId, showBool){
    $$('.x-tree-lines').each(function(div) {
        if (div.id.contains(panelTargetId)){
            if (showBool==false){
                if (browser=="Microsoft Internet Explorer"){
                    div.style.opacity='0.2';
                }
                else{
                    div.tween('opacity',0.2);
                }
                if(div.style.color!='white'){
                    div.style.display= 'none';
                }
            }else{
                if (browser=="Microsoft Internet Explorer"){
                    div.style.opacity='1';
                }
                else{
                    div.tween('opacity',1);
                }
                if(div.style.color!='white'){
                    div.style.display= 'block';
                }
            }
        }
    });
}