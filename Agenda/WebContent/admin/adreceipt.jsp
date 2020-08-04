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
<title>관리자페이지 전체후원내역조회</title>
<style type="text/css">

	#allreceipt {
		width: 100vw;
		height: 100vh;
		background-color: white;
	}
	
</style>

</head>
<body>


<div id="allreceipt">
	<h1>전체후원내역</h1>
	<table border="1">
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
	</table>
</div>


</body>
