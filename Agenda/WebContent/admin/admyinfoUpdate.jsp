<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 - 내 정보 수정 - 수정</title>
<style type="text/css">

	#back {
		background-color: black;
		color: white;
		cursor: pointer;
	}
	
	section{
		width : 100%;
		height : 100vh;
		display : flex;
		justify-content: center;
		align-items: center;
		background-color: whitesmoke;
	}
	
	table{
		border-collapse: collapse;
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
<form action="adController?command=admyinfoUpdate" method="post">
<input type="hidden" name="member_id" value="${logindto.member_id}">
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
			<td><input type="text" name="member_email" value="${dto.member_email}"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="member_addr" value="${dto.member_addr}"/></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정"/>
				<input id="back" type="button" onclick="location.href=location.href='${pageContext.request.contextPath}/adController?command=admyinfo&member_id=${logindto.member_id }'" value="돌아가기">
			</td>
		</tr>
	</table>
</form>
</section>

	<!-- 풋터-->
	<jsp:include page="../footer/mainFooter.jsp" />
	
</body>
</html>