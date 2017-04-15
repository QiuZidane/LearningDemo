var button = $('#send');
button.bind('click', function(e) {

	$.ajax({
		type: "post",
		url: "http://192.168.1.6:8080/Web_Demo01/RecvJson",
		async: true,
		data: data
	});

})

