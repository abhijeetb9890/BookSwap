$(document).ready(function(){



$.ajax({ 
    url :"http://localhost:8080/profile.img", 
    cache: true,
    processData : false,
}).always(function(){
    $("#profileimage").attr("src", "http://localhost:8080/profile.img").fadeIn();
     $("#prof").attr("src", "http://localhost:8080/profile.img").fadeIn();
});   


    $.ajax({
        url: /*window.location.host*/ "http://localhost:8080" + "/api/useranalysis",
        dataType:"json",
        type : "GET",
        cache: false,
        success: function(response){
            console.log(response);
               console.log(response.name);
                   
                   var item = $('<table>', { class : "table table-user-information",style : "padding: 1%;color:#000000"});
                   var tbody=$('<tbody>');
                   var row1=$('<tr>');
                   var col1=$('<td>No Of Books Of user:'+'<td>'+response.noofbooks+'</td>'+'</td>');
                  /* var row2=$('<tr>');
                   var col2=$('<td>Email:'+'<td>'+response.email+'</td>'+'</td>');
                   //var name=$('<td>'+response.name);
                   var row3=$('<tr>');
                   var col3=$('<td>Contact No:'+'<td>'+response.contact_no+'</td>'+'</td>');

                   var row4=$('<tr>');
                   var col4=$('<td>Gender:'+'<td>'+response.gender+'</td>'+'</td>');
*/
                   //col.append(name);
                  // heading.append(col);
                   row1.append(col1);
                 //  row2.append(col2);
                   //row3.append(col3);
                   //row4.append(col4);

                   tbody.append(row1);
                  // tbody.append(row2);
                   //tbody.append(row3);
                   //tbody.append(row4);
                   item.append(tbody);


          
                     $('#usercollection').append(item);
               

        }
    });





    
});