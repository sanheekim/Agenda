<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda</title>
<link rel="stylesheet" type="text/css" href="pharmacy_map.css">
<script src="pharmacy_map.js" defer></script>
</head>
<body>

	<!-- 헤더 -->
	<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>

	<!-- 메인 -->
	<section>
		<div id="map_header">
		<h2>약국찾기</h2>
		</div>
		<div class="map_wrap">
			<div id="map"></div> <!-- 지도영역 -->
			<div id="menu_wrap"> <!-- 검색 결과 출력 영역 -->
				<ul id="placesList"></ul> <!-- 검색결과 목록 -->
				<div id="pagination"></div> <!-- 검색결과 페이징 -->
			</div>
		</div>
	</section>
	
	<!-- 풋터 -->
	<jsp:include page="../footer/mainFooter.jsp" />
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbd355e231cd5468c69e01f2e5801a5d&libraries=services"></script>
</body>
</html>