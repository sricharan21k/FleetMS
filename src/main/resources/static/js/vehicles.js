$(document).ready(function() {
console.log("hello");
  $("#deleteToggle").click(function() {
    $(".card .card-body .hidden").toggle();
  });
});