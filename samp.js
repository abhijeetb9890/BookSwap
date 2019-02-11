



 $("#register").click(function()
 {
$("#registrationform").submit(function(e)
{
    var formObj = $(this);
    var formData = new FormData();
    var imgsm=document.getElementById("user");


  $('#user').change(function () {  
    var files = this.files;  
        for (var i = 0; i < files.length; i++) {
            var file = files[i].name;
             $reader = new FileReader(); 
            $reader.readAsDataURL(files[i]);
             $reader.onload = function (e) {  
                  console.log(e);
            }
          }
});     


var file_data = $("#user").prop("files")[0]; 
   
   
    
     var username=$("#username").val();
     formData.append("name",username);


   var email=$("#email").val();
    formData.append("email",email);


    formData.append("password",$("#password").val());
    formData.append("contact_no",$("#contact").val());
     formData.append("image",file_data);
    
    console.log(formData);
    
  
    

    
    $.ajax({
        url:"http://localhost:8080/api/signupuser",
        type: 'POST',
        data:  formData,
        mimeType:"multipart/form-data",
        contentType: false,
        cache: false,
        processData:false,
    success: function(data, textStatus, jqXHR)
    {
            alert("success");
            
         
    },
     error: function(jqXHR, textStatus, errorThrown) 
     {
        alert("error");
     }          
    });
  
  });
//$("#registrationform").submit(); 
    //e.preventDefault(); //Prevent Default action. 
   //e.unbind();
}); 

