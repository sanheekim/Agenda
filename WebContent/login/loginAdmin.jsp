<%@page import="com.agenda.login.LoginDao" %>
<%@page import="com.agenda.login.LoginDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<%
	LoginDto dto = (LoginDto)session.getAttribute("dto");
%>

<body>
	<div class="admin__welcome__title"><%=dto.getMember_name() %>님  환영합니다.</div>
	<div> 등급 : <%=dto.getMember_role() %></div>
	<div><a href="loginController.jsp?command=logout">로그아웃</a></div>
	<div>
		<div><a href="loginController.jsp?command=userlistall">회원정보 전체 조회</a></div>
		<div><a href="loginController.jsp?command=userlisten">회원정보 조회 (member_enabled=y)</a></div>
	</div>
</body>
</html>