(function($) {
	'use strict'

	$('#agreementForm').validator().on('submit', function (e) {
		if (!e.isDefaultPrevented()) {
			showFormPage();
		}

		return false;
	});

	$('#hackerInfo').validator().on('submit', function (e) {
		if (!e.isDefaultPrevented()) {
			$("#cameraInput").click();
		}
		return false;
	});

    $("#confirm-button").click(function(){
        var $button = $(this);
        $button.button('loading');
    });
})($);

function showFormPage() {
    reduceHeaderSize();
    reduceSubHeaderSize();
    changeToScreen("#formulario-informacoes");
}

function photoTaken() {
    var img = $("#final-screen-image")[0];
    var fReader = new FileReader();
    fReader.readAsDataURL(getPhotoFromFileInput());
    fReader.onloadend = function(event) {

    changeToScreen("#final-screen");

    img.src = event.target.result;

    setHackersName();

    populateFinalForm(img);
    }
}

function getPhotoFromFileInput(){
    return $("#cameraInput")[0].files[0];
}
function changeToScreen(component){
	$('.screen').css('display','none');;
	$(component).css('display','block');;
}

function populateFinalForm(img){
    var firstName = $("#inputFirstName").val();
    var lastName = $("#inputLastName").val();
    var email = $("#inputEmail").val();

    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#email").val(email);
    $("#imageData").val(img.src);
}


function setHackersName(){
    var name = $("#inputFirstName").val().toUpperCase();
    $("#thankyou").text("MUITO OBRIGADO, "+name+"!");
}

function reduceHeaderSize(){
    var header = $("#main-header");
    header.removeClass("main-header-normal-font");
    header.addClass("main-header-small-font");
}

function reduceSubHeaderSize(){
    var subHeader = $("#sub-header");
    subHeader.removeClass('sub-header-normal-font');
    subHeader.addClass('sub-header-small-font');
}