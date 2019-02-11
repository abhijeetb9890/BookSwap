$(document).ready(function(){
var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#welcumimg").attr("src", url).fadeIn();
    // $("#book1").attr("src", url).fadeIn();
}); 

});  
