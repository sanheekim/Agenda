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

	* {
		margin: 0px;
		padding: 0px;
	}
	
	#allreceipt {
		width: 100vw;
		height: 100vh;
		background-color: whitesmoke;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	#allreceipt table{
		width: 1000px;
		heigth: 100%;
		padding: 30px;
		margin: 80px auto;
		font-family: 'Nanum Gothic Light';
	}
	
	#title {
		font-family: 'Nanum Gothic Bold';
	}
	
	table{
		border-collapse: collapse;
		width: 100%;
	}
	
	tr,td{
		padding : 5px;
		text-align: center;
	}
	
	td {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		
		padding: 8px;
		font-size : 1em;
	}
	
	thead tr td {
		font-family: 'Nanum Gothic Bold';
	}
	
	/* 컬럼크기 */
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
	/* 컬럼크기 끝 */
	
	#title {
		font-size : 1.5em;
		padding: 8px
	}
	
	#back {
		background-color: black;
		color: white;
		cursor: pointer;
	}
	
	#back {
		padding : 5px 5px;
		border : none;
		cursor: pointer;
		background-color: gray;
		color : white;
	}
	
</style>

</head>
<body>

	<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>

<div id="allreceipt">
	<table border="0">
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
				<td colspan="10"><input id="back" type="button" onclick="location.href='admin/adlayout.jsp'" value="돌아가기"></td>
			</tr>
		</tfoot>
	</table>
</div>

	<!-- 풋터-->
	<jsp:include page="../footer/mainFooter.jsp" />
	
</body>
