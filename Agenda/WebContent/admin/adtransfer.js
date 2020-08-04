var ad01 = function(){
	$.ajax({
		url: "adController",
		method: "post",
		data: {},
		success: function(msg){
			location.href="./adController?command=";
		},
		error:function(){
			alert("통신 실패");
		}
	});
}

var ad02 = function(){
	
}

var ad03 = function(){
	
}

var ad04 = function(data){
	$.ajax({
		url: "../dnController",
		method: "post",
		data: {"obj" : JSON.stringify(data),command:"donation"},
		success: function(msg){
			location.href="../dnController?command=dnlist";
		},
		error:function(){
			alert("통신 실패");
		}
	});
}