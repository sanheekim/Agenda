<%@page import="com.agenda.login.LoginDao"%>
<%@page import="com.agenda.login.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	LoginDto logindto = (LoginDto) session.getAttribute("logindto");
	LoginDao logindao = new LoginDao();

	String member_id =  logindto.getMember_id();
	String member_pw = logindto.getMember_pw();
	
	LoginDto input = new LoginDto(member_id, member_pw);
	logindto = logindao.login(input);

	session.setAttribute("dto", logindto);
%>

	<jsp:forward page="..//qnaController.do?command=list&curPage=1&searchOption=all&keyword="></jsp:forward>
	
</body>
</html>