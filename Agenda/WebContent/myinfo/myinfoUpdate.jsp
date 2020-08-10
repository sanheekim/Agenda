<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	#back {
		background-color: black;
		color: white;
		cursor: pointer;
	}
	
	section{
		background-color: whitesmoke;
		width : 100%;
		height : 100vh;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	
	table{
		border-collapse: collapse;
		margin-top : 100px;
		margin-bottom: 50px;
	}
	
	tr,td{
		padding : 5px;
	}
	

</style>
</head>
<body>

<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>
	
	<section>
<form action="MyinfoController?command=myinfoUpdate" method="post">
<input type="hidden" name="member_id" value="${dto.member_id}">
	<table>
		<tr>
			<th>아이디</th>
			<td>${dto.member_id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.member_name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" value="${dto.member_email}"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="addr" value="${dto.member_addr}"/></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정"/>
				<input id="back" type="button" onclick="location.href=location.href='${pageContext.request.contextPath}/MyinfoController?command=myinfo&member_id=${dto.member_id }'" value="돌아가기">
			</td>
		</tr>
	</table>
</form>
</section>

<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
</body>
</html>