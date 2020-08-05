<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="pharmacy_map.css">
<script src="pharmacy_map.js" defer="defer"></script>
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

	<section>
		<div id="map_header">
		<h2>약국찾기</h2>
		</div>
		<div class="map_wrap">
			<div id="map"></div>
			<div id="menu_wrap">
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
		</div>
	</section>
	
	<jsp:include page="../footer/mainFooter.jsp" />

</body>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbd355e231cd5468c69e01f2e5801a5d&libraries=services"></script>

</html>