<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 실패</title>
<style type="text/css">
section {
	width: 1000px;
	height: 100%;
	margin: 80px auto;
}

.searchId__Title{
font-size:30px;
}

</style>
</head>
<body>

<%
 String id = (String)request.getAttribute("member_id");
 System.out.println("id:" + id);
%>	
	<section>
		<div id=searchIdFalse>
			<div class=searchId__Title>아이디 찾기 실패</div>

			<div class=searchId__FalseResult>아이디가 존재하지 않습니다.</div>
		</div>
		
	</section>
	
</body>
</html>