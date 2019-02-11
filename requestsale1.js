$(document).ready(function(){


console.log("success");



var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#bookprofim").attr("src", url).fadeIn();

});   





$.ajax({


	url:"http://localhost:8080/api/getbook",
	type:"GET",
	dataType:"json",
	success:function(response)
	{
		console.log(response);		
			

			
		
		
					var item = $('<div>', {class:"list-group-item col-sm-10 ",style : "padding: 1%;color:#111;margin:50px;font-family:initial;margin-left:50px"});
					  item.css({'background-image':'url(back.jpg)'});
					var span1=$('<span>',{style:"float:left;width:50%"});
					var image =$('<img>',{src:'http://localhost:8080/book.img?_id='+ response._id,style:"height:50%;width:50%; display: block;margin:50px"});
                	span1.append(image)
                	item.append(span1);
    				
    				//var division=('<div>',{class:"pull-right"});    

    				var span2=$('<span>',{style:"float:right;width:50%;color:#FFFFFF;padding-top:100px"});
                	//var heading= $("<h2>");
                    var title =  $('<h1>', {class : 'title', href:"bookpage.html",style:"text-decoration:none;color:#FFFFFF;font-family: initial"} );
                    //heading.append(title);
                    
                	//title.setAttribute(id,arr[i]._id);
                	//var divv=$('<div>',{class:'text-right'});
                    title.append(document.createTextNode(response.name));
                    //division.append(heading);
                    span2.append(title);
                    //console.log(arr[i].title);
                    var cost=$('<h4>Cost: '+response.cost + '</h4>');
                    span2.append(cost);

                     var author=$('<h4>Author: '+response.author + '</h4>');
                    span2.append(author);

                     var category=$('<h4>Category: '+response.category + '</h4>');
                    span2.append(category);


                    var p = $("<h4>Synopsis: " + response.synopsis + "</h4>");
                    span2.append(p);
                    item.append(span2);
                     $('#book-info').append(item);


                  
				$.ajax({

			url:"http://localhost:8080/api/swap",
			type:"POST",
			data:{_id:response._id},
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				 $('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
			}

	});




		$.ajax({

			url:"http://localhost:8080/api/mail",
			type:"POST",
			data:{_id:response._id},
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				$('.error').fadeIn(400).delay(3000).fadeOut(400); 
			}

	});
	
		


	   $("#request1").click(function(){
		console.log("clicked");
		alert("Request sent for swapping");	
	$.ajax({

			url:"http://localhost:8080/api/swap",
			type:"GET",
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				 $('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
			}

	});

	$.ajax({

			url:"http://localhost:8080/api/mail",
			type:"GET",
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				$('.error').fadeIn(400).delay(3000).fadeOut(400); 
			}




	});	
	

});





	   $("#sub").click(function(){
		console.log("clicked");
		var review1=$("#review").val();
		console.log(review1);
		$.ajax({

			url:"http://localhost:8080/api/sendreview",
			type:"POST",
			data:{review:review1},
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				 $('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
			}

	});



});

}
});
});






//console.log("are yar ja na");
//console.log(arr);
 //$('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
//function getid(id)
//{
//		console.log(id);
//}

/*$("#request1").click(function(){
		console.log("clicked");
		//alert("success");
	
	$.ajax({

			url:"http://localhost:8080/api/mail",
			data:{_id:response._id},
			type:"GET",
			success:function(response)
			{
				 $('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
			}

	});*/



/*                     $("#request1").click(function(){
		console.log("clicked");
				$.ajax({

			url:"http://localhost:8080/api/swap",
			type:"POST",
			data:{_id:response._id},
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				 $('.error').fadeIn(400).delay(3000).fadeOut(400); //fade out after 3 seconds
			}

	});




		$.ajax({

			url:"http://localhost:8080/api/mail",
			type:"GET",
			dataType:"json",
			success:function(response)
			{
				alert("Added to Swaps");
				$('.error').fadeIn(400).delay(3000).fadeOut(400); 
			}

	});
	});
		

*/


 



