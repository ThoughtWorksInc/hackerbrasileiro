(function($) {
	'use strict'

	// function (){
	// 	this.final_screen_image = document.getElementById("final-screen-image");
	// }

	function Camera() {
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		var video = document.getElementById("video");
		var imageData = document.getElementById("imageData");
		return {
			start: function() {
				var	videoObj = { "video": true },
					errBack = function(error) {
						console.log("Video capture error: ", error.code);
					};

				// Put video listeners into place, based on each
				// browser implementation
				if(navigator.getUserMedia) { // Standard
					navigator.getUserMedia(videoObj, function(stream) {
						this.video.src = stream;
						this.video.play();
					}, errBack);
				} else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
					navigator.webkitGetUserMedia(videoObj, function(stream){
						this.video.src = window.webkitURL.createObjectURL(stream);
						this.video.play();
					}, errBack);
				}
				else if(navigator.mozGetUserMedia) { // Firefox-prefixed
					navigator.mozGetUserMedia(videoObj, function(stream){
						this.video.src = window.URL.createObjectURL(stream);
						this.video.play();
					}, errBack);
				}
			},

			stop: function() {
				this.video.pause();
			},

			takePhoto: function() {
				canvas.width = document.getElementById('video').offsetWidth;
				canvas.height = document.getElementById('video').offsetHeight;
				context.drawImage(video, 0, 0, canvas.width, canvas.height);

				var pngData = canvas.toDataURL('image/png');
			}
		};
	}

	window.onload = function() {
		var camera = new Camera();
		camera.start();

		$("#take-picture").click(function() {
			camera.takePhoto();

			var elementToBeHidden = document.getElementById("camera-show");
			elementToBeHidden.style.display = 'none';

			var elementToBeShown = document.getElementById("camera-picture");
			elementToBeShown.style.display = 'block';
			return true;
		});

		$("#restart-camera").click(function() {
			var elementToBeShown = document.getElementById("camera-picture");
			elementToBeShown.style.display = 'none';

			var elementToBeHidden = document.getElementById("camera-show");
			elementToBeHidden.style.display = 'block';
		});

		$("#show-final-screen").click(function() {
			var elementToBeShown = document.getElementById("camera-picture");
			elementToBeShown.style.display = 'none';

			var elementToBeHidden = document.getElementById("final-screen");
			elementToBeHidden.style.display = 'block';
		});
	};
})($);
