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
<form action="MyinfoController" method="post" >
<input type="hidden" name="command" value="myinfoUpdateform">
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
			<td>${dto.member_email}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${dto.member_addr}</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정"/>
				<input type="button" value="회원탈퇴" onclick="location.href='MyinfoController?command=myinfoDelete&member_id=${dto.member_id}'"/> 
				<input type="button" value="후원내역조회" onclick="location.href='dnController?command=dnlist&member_id=${dto.member_id}'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>