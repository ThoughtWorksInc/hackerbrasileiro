function showFormPage() {
    reduceHeaderSize();
    var elementToBeHidden = document.getElementById("tela-inicial");
    elementToBeHidden.style.display = 'none';

    var elementToBeShown = document.getElementById("formulario-informacoes");
    elementToBeShown.style.display = 'block';
}

function showCameraPage() {
    var elementToBeHidden = document.getElementById("formulario-informacoes");
    elementToBeHidden.style.display = 'none';

    var elementToBeShown = document.getElementById("camera-show");
    elementToBeShown.style.display = 'block';
}

function photoTaken() {
    var img = document.getElementById("final-screen-image");
    var fReader = new FileReader();
    fReader.readAsDataURL(document.getElementById("cameraInput").files[0]);
    fReader.onloadend = function(event) {
        var elementToBeShown = document.getElementById("camera-show");
        elementToBeShown.style.display = 'none';
        var elementToBeShown = document.getElementById("formulario-informacoes");
                elementToBeShown.style.display = 'none';

    	var elementToBeHidden = document.getElementById("final-screen");
    	elementToBeHidden.style.display = 'block';

    	img.src = event.target.result;

        var firstName = $("#inputFirstName").val();
        var lastName = $("#inputLastName").val();
        var email = $("#inputEmail").val();

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#email").val(email);
        $("#imageData").val(getBase64Image(img));
    }
}

function getBase64Image(img) {
  var canvas = document.createElement("canvas");
  canvas.width = img.width;
  canvas.height = img.height;
  var ctx = canvas.getContext("2d");
  ctx.drawImage(img, 0, 0);
  return canvas.toDataURL("image/png");
}

function reduceHeaderSize(){
    var header = document.getElementById("main-header");
    header.classList.add('main-header-small-font');
    header.classList.remove('main-header-normal-font');

    var subHeader = document.getElementById("sub-header");
    subHeader.classList.add('sub-header-small-font');
    subHeader.classList.remove('sub-header-normal-font');
}