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
<title>전체 회원 정보 조회</title>
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<style type="text/css">

	* {
		margin: 0px;
		padding: 0px;
	}

	section {
		
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		height : 100%;
		background-color: whitesmoke;
	}

	#allmemberlist {
		width: 100%;
		heigth: 100vh;
		padding: 30px;
		margin: 80px auto;
		font-family: 'Nanum Gothic Light';
		background-color: whitesmoke;
	}
	
	#title {
		font-size: 1.8em;
		text-align: center;
		font-family: 'Nanum Gothic Bold';
	}
	
	table{
		border-collapse: collapse;
		width : 100%;
		margin-top : 100px;
		margin-bottom: 50px;
	}
	
	td {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		
		padding: 8px;
		font-size : 1em;
	}
	
	tr {
		text-align: center;
	}
	
	
	tr,td{
		padding : 5px;
	}
	
	td:first-child {
	width: 30px;
	}
	
	td:nth-child(2) {
		width: 300px;
	}
	
	td:nth-child(3) {
		width: 30px;
	}
	
	td:nth-child(4) {
		width: 100px;
	}
	
	td:nth-child(5) {
		width: 30px;
	}
	
	td:nth-child(6) {
		width: 30px;
	}
	
	td:nth-child(7) {
		width: 30px;
	}
	
	td:nth-child(8) {
		width: 30px;
	}
	
	td:nth-child(9) {
		width: 30px;
	}
	
	td:nth-child(10) {
		width: 30px;
	}
	
	#update {
		background-color: gold;
		cursor: pointer;
	}
	
	#back,#update{
		padding : 5px 5px;
	border : none;
	cursor: pointer;
	background-color: gray;
	color : white;
	}
	
	
	
	
</style>
<script type="text/javascript">

function changeRole(member_id){
	location.href="../Agenda/adController?command=allUpdate&member_id="+member_id;
}

</script>
</head>
<body>
<%
	List<adDto> list = (List<adDto>)request.getAttribute("list");
%>
<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>
	
	<section>
<div id="allmemberlist">
	<table border="0">
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
				<td id="title" colspan="10">전체 회원 정보 조회</td>
			</tr>
				<tr style="font-family: 'Nanum Gothic Bold';">
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
			<td><button id="update" onclick="changeRole('<%=dto.getMember_id() %>');">변경</button></td>
		</tr>
<%
			}
		}
%>
		<tr>
			<td colspan="10"><input id="back" type="button" onclick="location.href='admin/adlayout.jsp'" value="돌아가기"/></td>
		</tr>
	</table>

</div>

</section>

<!-- 풋터-->
	<jsp:include page="../footer/mainFooter.jsp" />
	
	
</body>
</html>