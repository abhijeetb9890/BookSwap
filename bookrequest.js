$(document).ready(function(){

 $.ajax({

 	url:"http://localhost:8080/api/bookrequest",
 	type:"GET",
 	dataType:"json",
 	success:function(response)
 	{
          console.log(response);
 		      var arr=response.result;
          console.log(arr);
 		       for (var i = 0; i < arr.length; i++) {
                         var item = $('<div>', {class : 'list-group-item  col-md-3 container', style : "padding: 1%;color:#111;margin:60px"});
                         item.css({'background-image':'url(back.jpg)'});
                          var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+arr[i].bookid,style:"height:400px; width:100%; display: block"});
                    item.append(image);
                // title
               /* console.log(arr[i].NameUser);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].NameUser));
                    item.append(title);
*/                    
                  var heading= $("<h2>");
                    var p = $('<a>',{id:arr[i]._id,value:arr[i].bookid,href:"ownerbooks.html",style:"text-align:center"});

                    heading.append(p);
                    p.append(arr[i].BookName);
                    item.append(heading);

                    var button= $('<input>', {id:arr[i]._id,type : 'submit',value:'Show books of User',class:"btn btn-primary"});
                    item.append(button);
                    
                     $('#feedback-collection').append(item);

                       }


                           $("body").on("click","input",function(){
                                {
                                  var value=$(this).attr('id');
                                  var bookid=$(this).attr('value');
                                  console.log(value);
                                  console.log(bookid);
                        console.log("cliked");
                        alert("clicked");
                          window.location.href="ownerbooks.html";
                        $.ajax({

                          url:"http://localhost:8080/api/ownerbooks",
                          type:"POST",
                          data:{_id:value},
                          async:false,
                          dataType:"json",
                          success:function(response){
                            console.log("successful");
                            console.log("Value after success");
                            window.location.href="ownerbooks.html";
                            alert("successful");
                          }
                        });


                         /*$.ajax({

                          url:"http://localhost:8080/api/confirm",
                          type:"POST",
                          data:{_id:bookid},
                          async:false,
                          dataType:"json",
                          success:function(response){
                            console.log("successful");
                            console.log("Value after success");
                           // window.location.href="ownerbooks.html";
                            alert("successful in posting");
                          }
                        });*/




                      }});



           

 	}



 });

	});