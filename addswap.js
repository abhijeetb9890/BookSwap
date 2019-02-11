$(document).ready(function(){





    $.ajax({
        url: /*window.location.host*/ "http://localhost:8080" + "/api/swap",
        dataType: "json",
        type : "GET",
        cache: false,
        success: function(response){
            console.log("successfully added to swaps");
               }
});
});