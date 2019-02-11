$(document).ready(function(){

console.log("success");

$.ajax({

	url:"http://localhost:8080/api/getbook",
	type:"GET",
	dataType:"json",
	success:function(response)
	{
		console.log(response);
		alert("click");	
		
	},
	error:function()
	{
		alert("uyt");
	}

});


//console.log(arr);

//function getid(id)
//{
//		console.log(id);
//}

$("#request1").click(function(){
		console.log("clicked");
		alert("success");
	
	$.ajax({

			url:"http://localhost:8080/api/mail",
			type:"GET",
			success:function(response)
			{
				alert("Mail Sent");
			}

	});






});

});


