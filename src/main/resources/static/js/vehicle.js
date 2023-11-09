const inputFields = document.querySelectorAll('input[type="text"]');
const editButton = document.getElementById('editButton');

editButton.addEventListener('click', function(){
     inputFields.forEach(function(field){
        field.readOnly = !field.readOnly;
     });
});
