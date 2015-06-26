function showFormPage() {
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

    	var elementToBeHidden = document.getElementById("final-screen");
    	elementToBeHidden.style.display = 'block';

    	img.src = event.target.result;
    }
}
