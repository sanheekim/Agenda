<%@page import="com.agenda.admin.adDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등급 변경</title>
<style type="text/css">
	#title{
		font-size: 16pt;
		text-align: center;
	}
	
	.btn {
		background-color: black;
		color: white;
		cursor: pointer;
	}
</style>
</head>
<body>
<%
	adDto dto = (adDto) request.getAttribute("dto");
%>
	<form action="../Agenda/adController?command=updateres" method="post">
	<input type="hidden" name="command" value="member_id"/>
	<input type="hidden" name="command" value="member_role"/>
		
		<table border="1">
			<col width="200" />
			<col width="100" />
			<col width="100" />
			<tr>
				<td id="title" colspan="5">회원등급 변경</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input name="member_id" value="<%=dto.getMember_id() %>"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=dto.getMember_name() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="member_role">
						<option value="USER" <%=dto.getMember_role().equals("USER")?"selected":"" %> >일반회원</option>
						<option value="ADMIN" <%=dto.getMember_role().equals("ADMIN")?"selected":"" %> >관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn" type="submit" value="변경 완료" />
					<input class="btn" type="button" value="목록" onclick="location.href='../Agenda/adController?command=allMember'" />
				</td>
			</tr>
		</table>
	</form>	


</body>
</html>