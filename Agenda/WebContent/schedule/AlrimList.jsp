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

<style type="text/css">

	* {
		margin: 0px;
		padding: 0px;
	}
	
	#popUp {
		width: 100vw;
		height: 100vh;
		background-color: whitesmoke;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	#popUp table{
		width: 1000px;
		heigth: 100%;
		padding: 30px;
		margin: 80px auto;
		font-family: 'Nanum Gothic Light';
	}
	
	h1 {
		font-family: 'Nanum Gothic Bold';
	}
	
	tr th{
		padding : 5px;
		text-align: center;
	}
	
	tr td{
		padding : 5px;
		text-align: center;
	}
	
	th, td {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		
		padding: 8px;
		font-size : 1em;
	}
	
	th:first-child {
	width: 100px;
	}
	
	th:nth-child(2) {
		width: 300px;
	}
	
	th:nth-child(3) {
		width: 30px;
	}
	
	th:nth-child(4) {
		width: 100px;
	}
	
	th:nth-child(5) {
		width: 30px;
	}
	
	#check, #del {
		padding : 5px 5px;
		border : none;
		cursor: pointer;
		background-color: gray;
		color : white;
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
	
	<div id="popUp">
	<div id="subpopUp">
	<h1>알람 리스트</h1>
	
	<table border="0">
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
					<input type="submit" id="del" value="삭제"/>
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
					<input id="check"type="submit" value="확인"/>
				</form>
			</div>
		</tr>
		
	</table>
	</div>
	</div>
	<jsp:include page="../footer/mainFooter.jsp" />
		
</body>
</html>