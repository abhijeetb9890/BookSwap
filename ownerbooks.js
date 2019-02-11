$(document).ready(function(){

 $.ajax({

 	url:"http://localhost:8080/api/ownerbooks",
 	type:"GET",
 	dataType:"json",
 	success:function(response)
 	{
            console.log(response);
 		var arr=response.result;
        console.log(arr);
 		   for (var i = 0; i < arr.length; i++) {
                         var item = $('<div>', {class : 'list-group-item  col-md-4 container', style : "padding: 1%;color:#111;margin:60px"});
                          var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+arr[i]._id,style:"height: 180px; width:auto; display: block"});
                    item.append(image);
                // title
               /* console.log(arr[i].NameUser);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].NameUser));
                    item.append(title);
*/
                    /*var p = $("<a>",{href:"ownerbooks.html"});
                    p.append(arr[i].BookName);
                    item.append(p);

                    var button= $('<input>', {class:"btn btn-primary",id:arr[i]._id,type : 'button',value:'Confirm Swap'});
                    item.append(button);

                      var button= $('<input>', {class:"btn btn-primary",id:arr[i]._id,type : 'button',value:'Reject Swap'});
                    item.append(button);
                    
                     $('#feedback-collection').append(item);*/

                      console.log(arr[i].title);
                    var title =  $('<h2>', {class : 'title'});
                    title.append(document.createTextNode(arr[i].name));
                    item.append(title);

                    var p = $("<p>" + arr[i].synopsis + "</p>");
                    item.append(p);

                    var button= $('<input>', {id:arr[i]._id,type : 'submit',value:' Confirm Swap',class:"btn btn-primary"});
                    item.append(button);

                    
                     $('#feedback-collection').append(item);

                       }


                         $("body").on("click","input",function(){
                                {
                                  var value=$(this).attr('id');
                                  console.log(value);
                        console.log("cliked");
                        alert("clicked");

                        $.ajax({

                          url:"http://localhost:8080/api/confirm",
                          type:"POST",
                          data:{_id:value},
                          async:false,
                          dataType:"json",
                          success:function(response){
                            console.log("successful");
                            console.log("Value after success");
                           // window.location.href="ownerbooks.html";
                            alert("successful");
                          }
                        });
                      }});


                         $("body").on("click","input",function(){
                                {
                                  var value=$(this).attr('id');
                                  console.log(value);
                        console.log("cliked");
                        alert("clicked");

                        $.ajax({

                          url:"http://localhost:8080/api/confirm",
                          type:"GET",
                          async:false,
                          dataType:"json",
                          success:function(response){
                            console.log("successful");
                            console.log("Value after success");
                           // window.location.href="ownerbooks.html";
                            alert("successful");
                          }
                        });
                      }});




           

 	}



 });

	});