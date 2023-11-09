//Range value
 const rangeInput = document.getElementById("rangeInput");
 const rangeValue = document.getElementById("rangeValue");

 rangeInput.addEventListener("input", (e) => {
     rangeValue.textContent = e.target.value;
 });

 // Get references to the file input and the image container
 const imageInput = document.getElementById("imageInput");
 const selectedImage = document.getElementById("selectedImage");

 // Add an event listener to the file input
 imageInput.addEventListener("change", function () {
     // Check if a file has been selected
     if (imageInput.files && imageInput.files[0]) {
         // Create a FileReader to read the selected image file
         const reader = new FileReader();

         reader.onload = function (e) {
             // Set the source of the image element to the selected image
             selectedImage.src = e.target.result;
             selectedImage.style.display = "block"; // Make sure the image is visible
         };

         // Read the selected image file as a data URL
         reader.readAsDataURL(imageInput.files[0]);
     } else {
         // Handle the case where no file is selected or the browser doesn't support the File API
         selectedImage.src = "";
         selectedImage.style.display = "none"; // Hide the image
     }
 });
