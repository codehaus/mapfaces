/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function checkNode(formId,panelId,nodeId,checkcolumnId){
    var checkcolumn = document.getElementById(formId+':'+checkcolumnId).checked;
    var line2change = document.getElementById('li:'+formId+':'+panelId+':'+nodeId);
    if (checkcolumn==false){
        line2change.tween('opacity',0.2);
    }
    else {
        line2change.tween('opacity',1);
    }
}
