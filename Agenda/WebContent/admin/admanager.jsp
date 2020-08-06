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
function changeRole(){
	
	// 아래 거 쓰면 rlnow가 아니라 3개 값 다 나옴.
	// var rlvalue = $("#rolechoose").text();
	// var indexNo = rlvalue[0].selectedIndex; null 뜸
	
	var rlvalue = $("#rolechoose option:selected").val();
	
	// var rlvalue = document.getElementsByClassName("rolechoose");
	//     rlvalue = rlvalue.options[rlvalue.selectedIndex].value;
	
	// indexNo = document.getElementById("rolechoose").text;

	// var test = document.getElementsByClassName("rolechoose");
	// var indexNo = test[0].selectedIndex;
	// console.log(indexNo);
	
	$.ajax({
		url: "./adController",
		method: "post",
		data: {"rlvalue" : rlvalue, command : "allUpdate"},
		success: function(){
			location.href="./adController?command=updateres";
		},
		error:function(){
			alert("통신 실패");
		}
	});		
}
</script>
</head>
<body>
<%
	List<adDto> list = (List<adDto>)request.getAttribute("list");
%>

<div id="allmemberlist">
	<h1>전체회원정보조회</h1>
	<table border="1">
		<thead>
			<tr>
			<td>회원번호</td>
			<td>ID</td>
			<td>PW</td>
			<td>이름</td>	
			<td>이메일</td>
			<td>핸드폰</td>
			<td>주소</td>
			<td>활성화여부</td>
			<td>등급</td>
			<td>등급변경</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.member_no }</td>
					<td>${dto.member_id }</td>
					<td>${dto.member_pw }</td>
					<td>${dto.member_name }</td>
					<td>${dto.member_email }</td>
					<td>${dto.member_addr }</td>
					<td>${dto.member_phone }</td>
					<td>${dto.member_enabled }</td>
					<td>
						<select name="rlchoose" id="rolechoose">
							<option value="ADMIN" <%=list.equals("ADMIN")?"selected":"" %>>관리자</option>
							<option value="USER" <%=list.equals("USER")?"selected":"" %>>일반회원</option>
						</select>
					</td>
					<td><input type="button" value="변경" id="update" onclick="changeRole(<%=list.get(0)%>)" /></td>
					
				</tr>
			<c:if test="${empty list }">
			<div>회원정보가 없습니다.</div>
			</c:if>
			</c:forEach>
		</tbody>
	</table>

</div>
</body>
</html>