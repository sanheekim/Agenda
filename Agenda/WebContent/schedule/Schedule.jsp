<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">


$(function(){
		
		$("#submit").click(function(){
			
			var member_id = $("#member_id").val();
			var sche_time = $("#sche_time").val();
			console.log(typeof(sche_time));
			var sche_content = $("#sche_content").val();
		
			
			$.ajax({//비동기 통신으로 보내고 받는다.
				type : "post",
				url:"../ScheduleController",//어디로 보낼거냐, 요청하는건 데이터
				data: {
					command : "insert",
					member_id : member_id,
					sche_time : sche_time,
					sche_content : sche_content,
				} ,//응답되는건 데이터 타입, 성공시,json형태의 문자열을 json객체로 바꿔줌
				success:function(msg){//성공시,
					//alert(msg.name);//value값이 나온다
					alert("알람이 등록되었습니다.");
					window.close("schedule/Schedule.jsp");
				},
				error:function(){//실패시
					alert("알람 등록에 실패하였습니다");
					window.close("schedule/Schedule.jsp");
					
				}
				
				
			});
	
		});

	});
</script>
<body>

	 <form>
      	<p><input type="time" id="sche_time"></p>
      	<input type="hidden" id ="member_id" name="member_id" value="${logindto.member_id }"/>
      	<p><textarea cols="50" rows="10" id="sche_content"></textarea></p>
      	<p><input type="button" value="submit" id="submit"/></p>
      	<div id="result"></div>
    </form>

	



</body>
</html>