<%@page import="java.util.Date"%>
<%@page import="com.agenda.donation.dnDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 후원내역 조회</title>

<style type="text/css">

	#allreceipt {
		width: 100vw;
		height: 100vh;
	}
	
	#title {
		font-size: 16pt;
	}
	
	#back {
		background-color: black;
		color: white;
		cursor: pointer;
	}
	
</style>

</head>
<body>


<div id="allreceipt">
	<table border="1">
		<caption id="title">전체 후원내역</caption>
		<thead>
			<tr>
			<td>후원 번호</td>
			<td>후원 아이디</td>
			<td>금액</td>	
			<td>후원 날짜</td>
			</tr>
		</thead>
		<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<div>후원내역이 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>${dto.dona_no }</td>
								<td>${dto.member_id }</td>
								<td>${dto.dona_bill }</td>
								<td>${dto.dona_date }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="10"><input id="back" type="button" onclick="location.href='admin/adlayout.jsp'" value="돌아가기"></button></td>
			</tr>
		</tfoot>
	</table>
</div>


</body>
