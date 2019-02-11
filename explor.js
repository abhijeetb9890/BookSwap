$(document).ready(function(){

var arr;

        $(".list-group-item").click(function()
                {
                    //alert($(this).attr('value'));

                    var cat=$(this).attr('value');

                    $.ajax({

                  url:"http://localhost:8080/api/filter",
                  type:"POST",
                  data:{category:cat},
                  dataType:"json",
                  success:function(response,data)
                  {
                      alert(data);

                  }


    });

     });  


$.ajax({ 
    url : "http://localhost:8080/api/user", 
    cache: true,
    type:"GET",
    processData : false,
    success:function(response){
      console.log(response);
     // var p=('<h3>'+ response.name +'</h3>');
     // response.name); 
      //p.append(response.name);

      $("#profname").append(response.name);
    }
})



var url = "http://localhost:8080/profile.img";
$.ajax({ 
    url : url, 
    cache: true,
    processData : false,
}).always(function(){
    $("#profileim").attr("src", url).fadeIn();
     $("#book1").attr("src", url).fadeIn();
});   


$.ajax({

	url:"http://localhost:8080/api/findbooks",
	type:"GET",
  async:false,
	dataType:"json",
	success:function(response)
	{
		//console.log(response);
		 arr=response.result;
		  console.log(arr);
     //var jsonarray=JSON.parse(arr[i]);
     for (var i = 0; i <arr.length; i++) {
                  
             console.log(arr[i]._id);
                    

           };




    //var jsonarray=JSON.parse(arr);
    //console.log(jsonarray)

		 for (var i = 0; i < arr.length; i++) {

                        //var jsonarray=JSON.parse(arr[i]);
                      //  console.log(arr[i]._id);
                          //var array2=arr[i];
                          //console.log(array2);
                        
                        /*$.ajax({
                          console.log(arr[i]);
                          url:"http://localhost:8080/profile.img",
                          data:{_id:arr[i]._id},
                          type:"GET",
                          success:function(response,data)
                          {
                              console.log("success");
                              //console.log(arr[i]._id);
                              
                          } 

                        });*/

                   

                              
                     

                       /*    $.ajax({

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


                                  //});

                               // console.log(arr[i]._id);


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

                       $("body").on("click","a",function(){
                                {
                                  var value=$(this).attr('id');
                                  console.log(value);
                        console.log("cliked");
                      $.ajax({

                            url:"http://localhost:8080/api/getbook",
                            type:"POST",
                            async:false,
                            data:{_id:value},
                            success:function(response)
                            {
                                 console.log(value);
                            }


                      });
                    }});
                     
              
		
	},
	error: function(xhr,err){
    alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
    alert("responseText: "+xhr.responseText);
}







});

});



