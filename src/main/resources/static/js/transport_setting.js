var buttonLoad;
var dropDownTransport;
var labelTransportType;
var fieldTransportType;
var buttonAddTransport;
var buttonUpdateTransport;
var buttonDeleteTransport;

$(document).ready(function() {
	buttonLoad = $("#buttonLoadTransport");
	dropDownTransport = $("#dropDownTransport");
	buttonAddTransport = $("#buttonAddTransport");
 	buttonUpdateTransport = $("#buttonUpdateTransport");
 	buttonDeleteTransport = $("#buttonDeleteTransport");
	labelTransportType = $("#labelTransportType");
	fieldTransportType = $("#fieldTransportType");
	
	
	buttonLoad.click(function() {
		loadTransport();
	});	
	
	dropDownTransport.on("change", function() {
		changeFormStateToSelectedTransport();
	});
	
	buttonAddTransport.click(function() {
		if (buttonAddTransport.val() == "Add") {
			addTransport();
		} else {
			changeFormStateToNewTransport();
		}
	});
	
	buttonUpdateTransport.click(function() {
		updateTransport();
	});
	
	buttonDeleteTransport.click(function() {
		deleteTransport();
	});
	
});

function deleteTransport() {
	optionValue = dropDownTransport.val();
	transportId = optionValue;
	
	url = contextPath + "transport/delete/" + transportId;
	
	$.ajax({
		type: 'DELETE',
		url: url		
	}).done(function() {
		$("#dropDownTransport option[value='" + optionValue + "']").remove();
		changeFormStateToNewTransport();		
		showToastMessage("The transport has been deleted");
	}).fail(function() {
		showToastMessage("Error: Could not connect to server or server encountered an error");
	});
}

function updateTransport() {
	url = contextPath + "transport/save";
	transportType = fieldTransportType.val();
	
	transportId = dropDownTransport.val();
	
	jsonData = {id: transportId, type: transportType};
	
	$.ajax({
		type: 'POST',
		url: url,		
		data: JSON.stringify(jsonData),
		contentType: 'application/json'
	}).done(function(transportId) {
		$("#dropDownTransport option:selected").val(transportId);
		$("#dropDownTransport option:selected").text(transportType);
		showToastMessage("The transport has been updated.");
		
		changeFormStateToNewTransport();
	}).fail(function() {
		showToastMessage("Error: Could not connect to server or server encountered an error");
	});
}

function addTransport() {
	url = contextPath + "transport/save";
	transportType = fieldTransportType.val();
	
	jsonData = {type: transportType};
	
	$.ajax({
		type: 'POST',
		url: url,
		data: JSON.stringify(jsonData),
		contentType: 'application/json'
	}).done(function(transportId) {
		selectNewlyAddedTransport(transportId, transportType);
		showToastMessage("The new transport has been added.");
	}).fail(function() {
		showToastMessage("Error: Could not connect to server or server encountered an error");
	});
	
}

function selectNewlyAddedTransport(transportId, transportType) {
	optionValue = transportId;
	$("<option>").val(optionValue).text(transportType).appendTo(dropDownTransport);
	
	$("#dropDownTransport option[value='" + optionValue + "']").prop("selected", true);
	
	fieldTransportType.val("").focus();
}

function changeFormStateToNewTransport() {
	buttonAddTransport.val("Add");
	labelTransportType.text("Transport Type:");
	
	buttonUpdateTransport.prop("disabled", true);
	buttonDeleteTransport.prop("disabled", true);
	
	fieldTransportType.val("").focus();
}

function changeFormStateToSelectedTransport(){
	buttonAddTransport.prop("value", "New");
	buttonUpdateTransport.prop("disabled", false);
	buttonDeleteTransport.prop("disabled", false);
	
	labelTransportType.text("Selected Transport:");
	
	selectedTransportType = $("#dropDownTransport option:selected").text();
	fieldTransportType.val(selectedTransportType);	
	
}

function loadTransport() {
	url = contextPath + "transport/list";
	$.get(url, function(responseJSON) {
		dropDownTransport.empty();
		
		$.each(responseJSON, function(index, transport) {
			optionValue = transport.id;
			$("<option>").val(optionValue).text(transport.type).appendTo(dropDownTransport);
		});
		
	}).done(function() {
		buttonLoad.val("Refresh Transport List");
		showToastMessage("All transport have been loaded");
	}).fail(function() {
		showToastMessage("Error: Could not connect to server or server encountered an error");
	});
}

function showToastMessage(message) {
	$("#toastMessage").text(message);
	$(".toast").toast('show');
}