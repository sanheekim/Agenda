<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/medilocker/mediLockerScanMain.css" >
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/medilocker/mediLockerScanMain.js"></script>
<script src="${pageContext.request.contextPath}/medilocker/mediLockerXml2json.js"></script>
</head>
<body>
	<header>
		<jsp:include page="../header/loginMain.jsp"></jsp:include>
	</header>
	<div id="LockerMainDiv">
		<div id="LockerThemeDiv">
			<h2>처방전 보관함</h2>
			<button onclick="docScan();">SCAN</button>
		</div>
		<div id="Locker2ndDiv">
			<div id="Locker3rdDiv">
				<c:choose>
					<c:when test="${empty list }">
						<div>등록된 처방전이 없습니다.</div>
						<div>스캔버튼을 눌러 처방전을 등록해주세요!</div>
					</c:when>
					<c:otherwise>
						<div>${dto.member_id }님의 처방전 리스트 ♥</div>
						<div class="LockerListDiv">
							<div>번호</div>
							<div>처방전이름</div>
						</div>
						<c:forEach var="dto" items="${list }">
						<div class="LockerListDiv">
							<div>${dto.pres_no }</div>
							<div onclick="LockerDetail();">${dto.pres_name }</div>
						</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div>상세정보리스트</div>
			
		</div>
	</div>
	<footer>
		<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
	</footer>
	  <c:forEach items="${list }" var="scan">
 		<li> ${scan }</li>
 	  </c:forEach>

	
</body>
</html>