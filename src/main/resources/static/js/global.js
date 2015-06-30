(function($) {
	'use strict'

	$('#agreementForm').validator().on('submit', function (e) {
		if (!e.isDefaultPrevented()) {
			showFormPage();
			//showCameraPage();
		}
		//showFormPage();

		return false;
	});

	$('#hackerInfo').validator().on('submit', function (e) {
		if (!e.isDefaultPrevented()) {
			$("#cameraInput").click();
		}
		return false;
	});



})($);