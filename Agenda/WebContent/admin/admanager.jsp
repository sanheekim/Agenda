<%@page import="java.util.Date"%>
<%@page import="com.agenda.admin.adDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

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

<script>

function changeRole(){
	
	/* var target = document.getElementsByClassName("rlchoose");
	var option = target.options[target.selectedIndex].text;
	console.log(option); */
	
	var realrole = $("#ID option:selected").val();
	console.log(realrole);
		
		$.ajax({
			url: "../adController?command=allUpdate",
			method: "post",
			data: {realrole : realrole},
			success: function(data){
				alert("등급이 수정되었습니다.");
				location.href="admin/admanager.jsp";
			},
			error:function(){
				alert("통신 실패");
			}
	});
}

</script>

</head>
<body>

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
			<td>등급수정</td>
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
						<select name="rlupdate" class="rlchoose">
							<option value="rlnow">${dto.member_role }</option>
							<option value="rladmin">ADMIN</option>
							<option value="rluser">USER</option>
						</select>
					</td>
					<td><input type="button" value="수정" id="update" onclick="changeRole()"/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty list }">
			<div>회원정보가 없습니다.</div>
			</c:if>
		</tbody>
	</table>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/admin/adrolemanage.js"></script>
</body>
</html>