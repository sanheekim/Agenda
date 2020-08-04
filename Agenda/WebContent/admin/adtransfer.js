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
		url: "../adController",
		method: "post",
		data: {"obj" : JSON.stringify(data), command:"../dnController?command=donation"},
		success: function(msg){
			location.href="../adController?command=alldonation";
		},
		error:function(){
			alert("통신 실패");
		}
	});
}