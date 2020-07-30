<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pharmacy_map/pharmacy_map.css">
</head>
<body>
	<header>
		<jsp:include page="../header/header.jsp" />
	</header>

	<section>
		<div id="map_header">
		<h2>약국찾기</h2>
		<hr>
		</div>
		<div class="map_wrap">
			<div id="map"></div>
			<div id="menu_wrap">
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
		</div>
	</section>
	
	<footer>
		<jsp:include page="../footer/mainFooter.jsp" />
	</footer>

</body>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbd355e231cd5468c69e01f2e5801a5d&libraries=services"></script>
<script src="${pageContext.request.contextPath}/pharmacy_map/pharmacy_map.js"></script>
</html>