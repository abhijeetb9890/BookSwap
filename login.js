$(document).ready(function(){

//var intitle="title";
//var incontent="my content";

$("#loginadmin").click(function(){


var inemail=$("#userid").val();
var inpassword=$("#pwd").val();


$.post('http://localhost:8080/api/loginuser', {email:inemail , password:inpassword}, 
    function(returnedData){
        
         console.log(returnedData);
         // var c=JSON.parse(returnedData);
          //console.log(c._id);
            //   var globalvar={userid:c._id};
               window.location.href="history.html";

               }
               );
});




$("#login").click(function(){


var inemail=$("#userid").val();
var inpassword=$("#pwd").val();

console.log(inemail);
console.log(inpassword);
//var intitle="title";
//var incontent="my content";


/*var postdata={
  'title':intitle,
  'content':incontent
}*/


//var datasent=JSON.stringify(postdata);
//console.log(datasent);

$.post('http://localhost:8080/api/loginuser', {email:inemail , password:inpassword}, 
    function(returnedData){
        
         console.log(returnedData);
          var c=JSON.parse(returnedData);
          console.log(c._id);
               var globalvar={userid:c._id};
               window.location.href="welcome.html";

               }
       


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

);
 



});
});


