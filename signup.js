$(document).ready(function(){

//var intitle="title";
//var incontent="my content";

$("#register").click(function(){
 
            /*if(email == '' || atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
                {
                  //document.getElementById('error').innerHTML="*Please Enter Email";
                  alert("Please Enter valid Email");
                  return false;
                }*/
           /* if(pass == '')
                {
                  //document.getElementById('error').innerHTML="*Please Enter Password";
                  alert("Please Enter Password");
                  return false;
                }
            if(repass != pass)
                {
                  //document.getElementById('error').innerHTML="*Passwords do not match";
                  alert("Passwords do not match");
                  return false;
                }*/
                if(user == '')
                {
                  //document.getElementById('error').innerHTML="*Please Enter Username";
                  alert("Please Enter Username");                
                  var username=$("#username").val();
                  alert(username);
                }
var email=$("#email").val();
var password=$("#password").val();
var password=$("#passwordconfirm").val();

 
          var radioValue = $("input[name='gender']:checked").val();
            if(radioValue){
                alert("Your are a - " + radioValue);
            }
        
console.log(username);
console.log(email);
console.log(password);


//var intitle="title";
//var incontent="my content";


/*var postdata={
  'title':intitle,
  'content':incontent
}*/


//var datasent=JSON.stringify(postdata);
//console.log(datasent);
$.ajaxSetup({
  contentType: "multipart/form-data",
});


$.post('http://localhost:8080/api/signupuser', { name: username,email:email,password:password }, 
    function(returnedData){
         console.log(returnedData);
        // window.location.href = "file:///C:/Users/hp/Desktop/Mini%20Project/test_forum-master/test_webapp/test_home.html";
          var account = localStorage.getItem('_iduser');
          console.log(account);

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

});
 



});
});

