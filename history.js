$.ajax({

	url:"http://localhost:8080/api/history",
	type:"GET",
	dataType:"json",
	success:function(response)
	{
		//console.log(response);
		 arr=response.result;
		  console.log(arr);

		 				for(var i=0;i<arr.length;i++){ 
                       	var item = $('<div>', { class : 'list-group-item col-sm-12 col-lg-3 col-md-3 ', style : "padding: 1%;color:#111;margin-right:7%;margin-bottom:10%;"});
                // title
                        var thumbnail=$('<div>',{class:'thumbnail'});
                          
                        var caption=$('<div>',{class:'caption'});

                		//var image =$('<img>',{src:'http://localhost:8080/book.img',style:"height: 140px; width:auto; display: block"});
                		//thumbnail.append(image);
           		     	
                	var heading= $("<h4>");

                    var title =  $('<p>', {id:arr[i]._id,class : 'title',style:"display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"} );
                    title.append("User1:");
                    title.append(arr[i].User1);
                    heading.append(title);
                    //title.setAttribute(id,arr[i]._id);

                    var cost=$('<h4>');
                    cost.append("Book1:")
                    //var cost=$('<h4>'+arr[i].cost+'</h4>');
                    cost.append(arr[i].Book1);
                    caption.append(heading);
                    caption.append(cost);

                   // title.append(document.createTextNode(arr[i].name));
                    
                    //console.log(arr[i].title);

                    var q = $('<h4>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"} );
                    q.append("User2:");
                    q.append(arr[i].User2);
                    caption.append(q);

                    var p = $('<h4>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"} );
                     p.append("Book2:");
                    p.append(arr[i].Book2);
                    caption.append(p);

                    var ratings=$('<div>',{class:"ratings"});

                    thumbnail.append(caption);
                    thumbnail.append(ratings);
                    item.append(thumbnail);

                    var date=$('<p>',{style:"white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"} );
                    date.append(arr[i].date);
                    caption.append(date);
                    
                     $('#feedback-collection').append(item);


                     }
                 }


                       });