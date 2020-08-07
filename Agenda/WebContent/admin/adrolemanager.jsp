<%@page import="com.agenda.admin.adDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급변경 페이지</title>
</head>
<body>
<%
	adDto dto = (adDto) request.getAttribute("dto");
%>

	<h1>회원등급 변경 페이지</h1>

	<form action="../Agenda/adController" method="post">
		<input type="hidden" name="command" value="updateres"/>
		<input type="hidden" name="member_role" value="<%=dto.getMember_role() %>"/>
		
		<table border="1">
			<col width="50" />
			<col width="200" />
			<col width="100" />
			<tr>
				<th>아이디</th>
				<td><%=dto.getMember_id() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=dto.getMember_name() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="myrole">
						<option value="USER" <%=dto.getMember_role().equals("USER")?"selected":"" %> >일반회원</option>
						<option value="ADMIN" <%=dto.getMember_role().equals("ADMIN")?"selected":"" %> >관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경 완료" />
					<input type="button" value="목록" onclick="location.href='../Agenda/adController?command=allMember'" />
				</td>
			</tr>
		</table>
	</form>	


</body>
</html>