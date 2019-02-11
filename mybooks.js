$(document).ready(function(){

var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#mybooksprof").attr("src", url).fadeIn();
     //$("#book1").attr("src", url).fadeIn();
});   

//console.log(vart);

    $.ajax({
        url: /*window.location.host*/ "http://localhost:8080" + "/api/mybooks",
        dataType: "json",
        type : "GET",
        cache: false,
        success: function(response){
            console.log(response);
                var arr=response.result;
                //alert(arr.length);
               // console.log(globalvar.userid);
                for (var i = 0; i < arr.length; i++) {

                         /*$.ajax({

                                  url:"http://localhost:8080/book.img",
                                  type:"POST",
                                  async:false,
                                  data:{_id:arr[i]._id},
                                  success:function(response)
                                  {
                                      console.log("success");
                                      console.log("The book id is"+arr[i]._id);
                                  } 


                         });*/




                        var item = $('<div>', { class : 'list-group-item col-sm-12 col-lg-3 col-md-3 ', style : "padding: 1%;color:#111;margin-right:7%;margin-bottom:10%;"});
                // title
                        var thumbnail=$('<div>',{class:'thumbnail',style:"width:100%;height:100%"});
                        thumbnail.css({'background-image':'url(back.jpg)'});
                          
                        var caption=$('<div>',{class:'caption'});

                    var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+arr[i]._id,style:"height: 250px; width:100%; display: block;margin-bottom:20px"});
                    thumbnail.append(image);
                    
                  var heading= $("<h4>");

                    var title =  $('<a>', {id:arr[i]._id,class : 'title', href:"bookpage.html",style:"display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"} );
                    heading.append(title);
                    //title.setAttribute(id,arr[i]._id);

                    var cost=$('<h4>',{class:"pull-right",style:"color:#FFFFFF"});
                    //var cost=$('<h4>'+arr[i].cost+'</h4>');
                    cost.append(arr[i].cost);
                    caption.append(cost);

                    title.append(document.createTextNode(arr[i].name));
                    caption.append(heading);
                    //console.log(arr[i].title);

                    var p = $('<p>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color:#FFFFFF"} );
                    p.append(arr[i].synopsis);
                    caption.append(p);

                    var ratings=$('<div>',{class:"ratings"});

                    thumbnail.append(caption);
                    thumbnail.append(ratings);
                    item.append(thumbnail);


                    
                     $('#feedback-collection').append(item);


                     

                      /*   var item = $('<div>', {class : 'list-group-item col-md-12  container', style : "padding: 1%;color:#111;margin:80px"});

                         var image =$('<img>',{src:'http://localhost:8080/book.img',style:"height: 140px; width:40%; display: block"});
                    item.append(image);
                    
                // title
                console.log(arr[i].title);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].name));
                    item.append(title);

                    var p = $("<p>" + arr[i].synopsis + "</p>");
                    item.append(p);
                    
                     $('#feedback-collection').append(item);*/

                       }

        }
    });
    
});