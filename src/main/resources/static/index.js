$(document).ready(function() {
	$('#newUserSubmit').click(function(){
		$.ajax({
			type: "POST", 
			url: "/",
			data: {}
		})
	})
	$.ajax({
		type: "GET", 
		url: "/post",
		data: {id: "id", message: "message", timeStamp: "timeStamp", authorID: "authorID"},
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		success: function(data) {
			var result = "";
			$.each(data, function(id, post){
				result += '<b>Id : </b>' + post.id + '<br/>' +
							'<b>AuthorID : </b>' + post.authorID + '<br/>' +
							'<b>Post : </b>' + post.message + '<br/>'
							'<b>Time Posted: </b>' + post.timeStamp + '<hr/>'
				$('#posts').html('');
				$('#posts').html(result);
			})
			
		},
		error: function(result){
			alert('error');
		}
	})
})