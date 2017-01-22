
$(document).ready(function(){  
	$(document).find('#close').click(function(){ 
		//function for addComment
		close();                                     
	});
	$(document).find('.namespace').click(function(){
		ppt();
	});
	$('div.comment-list-item').hide();
	//hide all comments
	$("#addComment").click(function(){ 
		//function for addComment
		newData();                                     
	});   
}); 
function warn(){
	alert("请不要频繁增删评论（若想删除请重加载）")
}
function ppt(){
	$(document).find('#zoom').show();
}
function close(){
	$(document).find('#zoom').hide();
}

function deleteData(id){
	choosePic=$(".submit").attr("data-id");
	$('#commentList li').each(function() {
		if($(this).hasClass(id)) {
			$(this).remove();
		}	
	});
	
	//delete the li has the id in the page
	$.ajax({                                            
		type: "POST",                                    
		url: "comment/deleteComment.action",                                      
		data: {
			id:id,
			photoId:choosePic
		},
		success: function(data){
			
			$(".comment-num").html("("+data+")");
		}
	});
}
