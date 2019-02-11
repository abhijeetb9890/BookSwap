$(document).ready(function(){

var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#profilei").attr("src", url).fadeIn();
    // $("#book1").at
  });

  $(".list-group-item").click(function()
                {
                    //alert($(this).attr('value'));

                    var cat=$(this).attr('value');

                    $.ajax({

                  url:"http://localhost:8080/api/filter",
                  type:"POST",
                  async:false,
                  data:{category:cat},
                  dataType:"json",
                  success:function(response)
                  {
                    console.log(response);
                  }
                });
                  });

                       









                     $.ajax({

                     url:"http://localhost:8080/api/filter",
                  type:"GET",
                  dataType:"json",
                  success:function(response)
                  {
                    console.log(response);
                      var arr=response.result;

                      for (var i = 0; i < arr.length; i++) {
              
                    var item = $('<div>', { class : 'list-group-item col-sm-12 col-lg-3 col-md-3 ', style : "padding: 1%;color:#111;margin-right:7%;margin-bottom:10%;"});
                // title
                        var thumbnail=$('<div>',{class:'thumbnail',style:"width:100%;height:100%"});
                        thumbnail.css({'background-image':'url(back.jpg)'});
                          
                        var caption=$('<div>',{class:'caption'});

                    var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+ arr[i]._id,style:"height: 250px; width:100%; display: block;margin-bottom:20px"});
                    thumbnail.append(image);
                    
                  var heading= $("<h4>",{class:"pull-bottom"});

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

                    var p = $('<h4>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color:#FFFFFF"} );
                    p.append(arr[i].synopsis);
                    caption.append(p);

                    var ratings=$('<div>',{class:"ratings"});

                    thumbnail.append(caption);
                    thumbnail.append(ratings);
                    item.append(thumbnail);
                    
                     $('#feedback-collection').append(item);

                      }
                    }

                  });
});


