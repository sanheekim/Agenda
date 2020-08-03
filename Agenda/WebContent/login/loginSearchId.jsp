<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style type="text/css">

section {
	width: 1000px;
	height: 100%;
	margin: 80px auto;
}


</style>
</head>
<body>
<%
 String id = (String)request.getAttribute("member_id");
 System.out.println("id:" + id);
%>
	 <jsp:include page="../header/header.jsp" />
	<section>
	<div id=searchId>
	<h2 class=searchId__Title>아이디 찾기 성공!</h2></div>
	<div class=searchId__Container>찾으시는 아이디는
	<div class=searchId__Result><%=id %>입니다.</div>
</div>
<div>

</div>
</section>
 <jsp:include page="../footer/mainFooter.jsp" />
</body>
</html>