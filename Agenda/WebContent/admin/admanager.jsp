<%@page import="com.agenda.admin.adDto"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 관리</title>
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<style type="text/css">

	#allmember {
		width: 100vw;
		height: 100vh;
		background-color: white;
	}
	
	#update {
		background-color: gold;
		cursor: pointer;
	}
	
</style>
<script type="text/javascript">

var changeRole;
$(document).ready(function() {
	changeRole(member_id) = function() {
		location.href="../adController?command=allUpdate&member_id="+member_id;
	}})


	/* function changeRole(member_id){
		location.href="../adController?command=allUpdate&member_id="+member_id;
	} */
	
</script>
</head>
<body>
<%
	List<adDto> list = (List<adDto>)request.getAttribute("list");
%>

<div id="allmemberlist">
	<h1>전체회원정보조회</h1>
	<table border="1">
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
			<tr>
			<td>번    호</td>
			<td>I   D</td>
			<td>P   W</td>
			<td>이     름</td>	
			<td>이 메 일</td>
			<td>핸 드 폰</td>
			<td>주     소</td>
			<td>활 성 화</td>
			<td>등     급</td>
			<td>등급변경</td>
			</tr>
<%
		if (list.size() == 0 ) {
%>
		<tr>
			<td>-----회원 정보가 없습니다.-----</td>
		</tr>
<%
		} else {
			for (adDto dto : list) {
%>
		<tr>
			<td><%=dto.getMember_no() %></td>
			<td><%=dto.getMember_id() %></td>
			<td><%=dto.getMember_pw() %></td>
			<td><%=dto.getMember_name() %></td>
			<td><%=dto.getMember_email() %></td>
			<td><%=dto.getMember_addr() %></td>
			<td><%=dto.getMember_phone() %></td>
			<td><%=dto.getMember_enabled() %></td>
			<td><%=dto.getMember_role() %></td>
			<td><button onclick="changeRole(<%=dto.getMember_id() %>);">변경</button></td>
		</tr>
<%
			}
		}
%>
		<tr>
			<td colspan="6"><input type="button" onclick="location.href='admin/adlayout.jsp'" value="돌아가기"/></td>
		</tr>
	</table>

</div>
</body>
</html>