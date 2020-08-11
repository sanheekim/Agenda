<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


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
	
	<div id="popUp">
	<div id="subpopUp">
	<h1>알람 리스트</h1>
	
	<table border="1">
		<col width="50"/>
		<col width="100"/>
		<col width="300"/>
		<col width="100"/>
		<tr>
			<th>알람 번호</th>
			<th>알람 시간</th>
			<th>알람 제목</th>
			<th>등록 일자</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.sche_no }</td>
				<td>${dto.sche_time }</td>
				<td>${dto.sche_content }</td>
				<td>${dto.sche_regdate }</td>
				<td>
				<form action=AlrimController method="post">
					<input type="submit" value="삭제"/>
					<input type="hidden" name="command" value="delete"/>
					<input type="hidden" name="sche_no" value="${dto.sche_no }"/>
					<input type="hidden" name="member_id" value="${dto.member_id }"/>
					
				</form>
				</td>
			</tr>
		</c:forEach>
		<tr>
			
			<div>
				<form action="main/main.jsp" method="post">
					<input type="submit" value="확인"/>
				</form>
			</div>
		</tr>
		
	</table>
	</div>
	</div>
	<jsp:include page="../footer/mainFooter.jsp" />
		
</body>
</html>