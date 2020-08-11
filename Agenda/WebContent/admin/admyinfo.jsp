<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<style type="text/css">

	#back {
		background-color: black;
		color: white;
		cursor: pointer;
	}
	
	section{
		width : 100%;
		height : 100vh;
		background-color : whitesmoke;
		display : flex;
		justify-content: center;
		align-items: center;
	}
	
	table{
		border-collapse: collapse;
		border : 1px solid black;
		width: 650px;
	}
	
	th,td{
		padding : 10px;
	}
	
	input{
		padding : 3px 5px;
		border-radius: 0px;
		border : 1px solid gray;
	}
	
	tr:last-child {
	text-align: right;
}

</style>
</head>
<body>
<!-- 헤더 -->
	<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>
	
<!-- 섹션 -->
<section>
<form action="${pageContext.request.contextPath}/adController" method="post" >
<input type="hidden" name="command" value="admyinfoUpdateform">
<input type="hidden" name="member_id" value="${logindto.member_id }">
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
			<td>${dto.member_email}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${dto.member_addr}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<input type="button" value="회원탈퇴" onclick="location.href='${pageContext.request.contextPath}/adController?command=admyinfoDelete&member_id=${logindto.member_id }'"/> 				
				<input type="button" value="후원내역조회" onclick="location.href='${pageContext.request.contextPath}/dnController?command=dnlist&member_id=${logindto.member_id }'"/>
				<input id="back" type="button" onclick="location.href='admin/adlayout.jsp'" value="돌아가기">
			</td>
		</tr>
	</table>
</form>
</section>

	<!-- 풋터-->
	<jsp:include page="../footer/mainFooter.jsp" />
	
</body>
</html>