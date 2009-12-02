/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
 
 /**
 * @author Mehdi Sidhoum (Geomatys)
 */
 
// moves all the items to the selected list
function mapfaces_picklist_addAllToSelected(availableListId, selectedListId, hiddenId)
{
	var availableList = document.getElementById(availableListId);
    var selectedList = document.getElementById(selectedListId);

	mapfaces_picklist_moveAll(availableList, selectedList, hiddenId);
	mapfaces_picklist_updateHidden(selectedList, hiddenId);
}

// removes all the items from the selected list
function mapfaces_picklist_removeAllFromSelected(availableListId, selectedListId, hiddenId)
{
    var availableList = document.getElementById(availableListId);
    var selectedList = document.getElementById(selectedListId);

	mapfaces_picklist_moveAll(selectedList, availableList, hiddenId);
	mapfaces_picklist_updateHidden(selectedList, hiddenId);
}

// moves an item to the selected list
function mapfaces_picklist_addToSelected(availableListId, selectedListId, hiddenId)
{
	var availableList = document.getElementById(availableListId);
    var selectedList = document.getElementById(selectedListId);

	mapfaces_picklist_move(availableList, selectedList, hiddenId);
	mapfaces_picklist_updateHidden(selectedList, hiddenId);
}

// removes an item from the selected list
function mapfaces_picklist_removeFromSelected(availableListId, selectedListId, hiddenId)
{
    var availableList = document.getElementById(availableListId);
    var selectedList = document.getElementById(selectedListId);

	mapfaces_picklist_move(selectedList, availableList, hiddenId);
	mapfaces_picklist_updateHidden(selectedList, hiddenId);
}

function mapfaces_picklist_move(fromList, toList, hiddenId) {
	// Return, if no items selected
	var selectedIndex = fromList.selectedIndex;
	if(selectedIndex < 0) { return; }

	// Decremental loop, so the index is not affected in the moves
	for (var i = fromList.options.length - 1; i >= 0; i--) {
		if(fromList.options[i].selected) {
			var tLen = toList.options.length;
			toList.options[tLen] = new Option(fromList.options[i].text);
			toList.options[tLen].value = fromList.options[i].value;
			fromList.options[i] = null;
		}
	}
}

function mapfaces_picklist_moveAll(fromList, toList, hiddenId) {

	// Decremental loop, so the index is not affected in the moves
	for (var i = fromList.options.length - 1; i >= 0; i--) {
		var tLen = toList.options.length;
		toList.options[tLen] = new Option(fromList.options[i].text);
		toList.options[tLen].value = fromList.options[i].value;
		fromList.options[i] = null;
	}
}

// Selection - invoked on submit
function mapfaces_picklist_updateHidden(selectedList, hiddenId) {
	var hiddenField = document.getElementById(hiddenId);
	
	var arrValues = new Array(selectedList.options.length);
	for (var i = 0; i<selectedList.options.length; i++) {
	    arrValues[i] = selectedList.options[i].value;
	}
	
	hiddenField.value = arrValues.join();
}