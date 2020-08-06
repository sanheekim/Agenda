<%@page import="com.agenda.admin.adDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급변경 페이지</title>
</head>
<body>
<%
	adDto dto = (adDto)request.getAttribute("dto");
%>
	<form action="adController.jsp" method="post">
		<input type="hidden" name="command" value="updateroleres">
		<input type="hidden" name="myno" value="<%=dto.getMember_no() %>">
		<table border="1">
			<tr>
				<th>현재 회원등급</th>
				<td><input type="text" name="currentRole" value="<%=dto.getMember_role() %>" disabled="disabled" /></td>
			</tr>
			<tr>
				<th>변경할 등급</th>
				<td>
				<input type="radio" name="myrole" value="ADMIN" id="admin"
				onclick="document.getElementById('roletext').disabled = true;"><label for="admin">관리자</label>
				<input type="radio" name="myrole" value="USER" id="user"
				onclick="document.getElementById('roletext').disabled = true;"><label for="user">이용자</label>
				<input type="radio" name="myrole" value="" id="etc" onclick="document.getElementById('roletext').disabled = false;">
				<label for="etc"><input type="text" name=myrole  id="roletext" disabled="disabled"/></label>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="변경">
					<input type="button" value="취소" onclick="location.href='adController.jsp?command=admanager'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>