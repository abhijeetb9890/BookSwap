$(document).ready(function(){

//var intitle="title";
//var incontent="my content";
var category=$("#category").val();
console.log(category);
/*$("#submit").click(function(){


var bookname=$("#book_name").val();
var authorname=$("#author_name").val();
var booksynopsis=$("#synopsis").val();
var bookcost=$("#book_cost").val();
var category=$("#category").val();



console.log(bookname);
console.log(authorname);
console.log(synopsis);
console.log(bookcost);
console.log(category);

*/
//var intitle="title";
//var incontent="my content";


/*var postdata={
  'title':intitle,
  'content':incontent
}*/


//var datasent=JSON.stringify(postdata);
//console.log(datasent);

/*$.post('http://localhost:8080/api/addbook', {name:bookname,author:authorname,synopsis:booksynopsis,category:category,cost:bookcost}, 
    function(returnedData){
        
         console.log(returnedData);
         // var c=JSON.parse(returnedData);
          //console.log(c._id);
            //   var globalvar={userid:c._id};
               window.location.href="mybooks1.html";

               }
    */   


 /*$.ajax({
  url: "http://localhost:8080/api/post/new",
  type: "POST",
  dataType:"json",
  data:JSON.stringify({"title":intitle,"content":incontent}),
  contentType:"application/x-www-form-urlencoded",

  success:function(data)
  {

  	alert("success");
  },

  
  error: function(xhr,err){
    alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
    alert("responseText: "+xhr.responseText);
}
*/

//);
 



//});
//});

 $("#submit").click(function()
 {
$("#addbook_form").submit(function(e)
{

  
    var formObj = $(this);
    var formData = new FormData();
    var imgsm=document.getElementById("user");


 
  var file_data = $("#user").prop("files")[0]; 
   
   // console.log($("#user").val());
    formData.append("name",$("#book_name").val());
    formData.append("synopsis",$("#synopsis").val());
    formData.append("cost",$("#book_cost").val());
    formData.append("category",$("#category").val());
     formData.append("imagebook",file_data);
    
    console.log(formData);

    
    $.ajax({
        url:"http://localhost:8080/api/addbook",
        type: 'POST',
        data:  formData,
        mimeType:"multipart/form-data",
        contentType: false,
        cache: false,
        processData:false,
    success: function(data, textStatus, jqXHR)
    {
      window.location.href="mybooks.html";
      alert("Book Successfully Added");
           
    },
     error: function(jqXHR, textStatus, errorThrown) 
     {
        alert("error");
     }          
    });
    e.preventDefault(); //Prevent Default action. 
   //e.unbind();
}); 
//$("#addbook_form").submit(); //Submit the form
});
});





