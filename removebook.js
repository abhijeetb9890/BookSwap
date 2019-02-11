$(document).ready(function(){



/*$.ajaxSetup({
  xhrFields: {
    withCredentials: true
  }
});*/

//console.log(vart);


var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#removebooksprof").attr("src", url).fadeIn();
     //$("#book1").attr("src", url).fadeIn();
});   

    $.ajax({
        url: /*window.location.host*/ "http://localhost:8080" + "/api/mybooks",
        dataType: "json",
        type : "GET",
        cache: false,
        success: function(response){
            console.log(response);
                var arr=response.result;
                alert(arr.length);
               // console.log(globalvar.userid);
                for (var i = 0; i < arr.length; i++) {
                         var item = $('<div>', {class : 'list-group-item col-sm-12 col-lg-3 col-md-3 container', style : "padding: 1%;color:#111;margin:60px"});

                         var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+ arr[i]._id,style:"height: 250px; width:100%; display: block"});
                    item.append(image);
                // title
                console.log(arr[i].title);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].name));
                    item.append(title);

                    var p = $("<p>" + arr[i].synopsis + "</p>");
                    item.append(p);

                    var button= $('<input>', {id:arr[i]._id,type : 'submit',value:'Remove',class:"btn btn-primary"});
                    item.append(button);
                    
                     $('#feedback-collection').append(item);

                       }

                      $("body").on("click","input",function(){
                                {
                                  var value=$(this).attr('id');
                                  console.log(value);
                        console.log("cliked");
                      $.ajax({

                            url:"http://localhost:8080/api/removebook",
                            type:"POST",
                            data:{_id:value},
                            success:function(response)
                            {
                                 //alert("success");
                                 console.log(value);
                                 window.location.href="removebook.html";
                            }


                      });
                    }});




        }
    });

    
    








    
});