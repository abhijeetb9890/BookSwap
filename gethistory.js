$(document).ready(function(){





		
$.ajax({

	url:"http://localhost:8080/api/gethistory",
	type:"GET",
	dataType:"json",
	success:function(response)
	{
		console.log(response);
		console.log(response.count);

			 var item = $('<div>', {class : 'list-group-item  col-md-4 container', style : "padding: 1%;color:#111;margin:60px;align:center;margin-left:400px"});
                         // var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+ response._id,style:"height:250px; width:50%; display: block"});
                    //item.append(image);

                    var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+ response._id,style:"height: 250px; width:50%; display: block;margin-bottom:20px"});
                    item.append(image);
             // var heading= $("<h2>");
                    var p = $('<h2>');

                    
                    p.append(response.bookname);
                    item.append(p);

                     var cost=$('<h4>',{style:"color:#000000"});
                     cost.append("No of times Requested: ");
                    //var cost=$('<h4>'+arr[i].cost+'</h4>');
                    cost.append(response.count);
                    item.append(cost);


                   // var button= $('<input>', {id:arr[i]._id,type : 'submit',value:'Show books',class:"btn btn-primary"});
                    //item.append(button);
                    
                     $('#feedback-collection').append(item);

                    

           }


		
});
});
