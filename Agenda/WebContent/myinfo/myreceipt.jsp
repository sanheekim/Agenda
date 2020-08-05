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
<title>마이페이지 개인후원내역조회 (특정 아이디 뽑아서 조회되도록 해야 함)</title>
<style type="text/css">

	#allreceipt {
		width: 100vw;
		height: 100vh;
		background-color: white;
	}
	
</style>

</head>
<body>


<div id="myreceipt">
	<h1>개인후원내역</h1>
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
				
			<tr>
				<td>${dto.dona_no }</td>
				<td>${dto.member_id }</td>
				<td>${dto.dona_bill }</td>
				<td>${dto.dona_date }</td>
			</tr>
				
		</tbody>
	</table>
</div>


</body>
