$(document).ready(function(){

 $.ajax({

 	url:"http://localhost:8080/api/swapslist",
 	type:"GET",
 	dataType:"json",
 	success:function(response)
 	{

 		var arr=response.result;
        console.log(arr);
 		   for (var i = 0; i < arr.length; i++) {


                    //console.log(arr[i]._id);
                            var item = $('<div>', { class : 'list-group-item col-sm-12 col-lg-3 col-md-3 ', style : "padding: 1%;color:#111;margin-right:7%;margin-bottom:10%;margin-left:10px"});
                // title
                        var thumbnail=$('<div>',{class:'thumbnail',style:"width:100%;height:100%"});
                        thumbnail.css({'background-image':'url(back.jpg)'});
                          
                        var caption=$('<div>',{class:'caption'});

                    var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+arr[i].bookid,style:"height: 250px; width:100%; display: block;margin-bottom:20px"});
                    thumbnail.append(image);
                    
                  var heading= $("<h4>");

                    var title =  $('<h2>', {id:arr[i]._id,class : 'title',style:"display:block;color:#FFFFFF"} );
                   
                    
                    title.append(document.createTextNode(arr[i].NameUser));

                    caption.append(title);
                    //console.log(arr[i].title);

                    var p = $('<h2>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color:#FFFFFF"} );
                    p.append(arr[i].BookName);
                    caption.append(p);

                    var ratings=$('<div>',{class:"ratings"});

                    thumbnail.append(caption);
                    thumbnail.append(ratings);
                    item.append(thumbnail);


                    
                     $('#feedback-collection').append(item);








                     /*    var item = $('<div>', {class : 'list-group-item  col-md-3 container', style : "padding: 1%;color:#000000;margin:60px"});
                          var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+arr[i].bookid,style:"height: 150px; width:40%; display: block"});
                    item.append(image);
                    //item.css({'background-image':'url(back.jpg)'});
                // title
                console.log(arr[i].NameUser);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].NameUser));
                    item.append(title);

                    var p = $("<p>" + arr[i].BookName + "</p>");
                    item.append(p);
                    
                     $('#feedback-collection').append(item);
*/
                       }
 	}



 });

	});




