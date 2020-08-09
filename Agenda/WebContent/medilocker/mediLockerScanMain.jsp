<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/medilocker/mediLockerXml2json.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/medilocker/mediLockerScanMain.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/medilocker/mediLockerScanMain.css" >
</head>
<body>
	<header>
		<jsp:include page="../header/loginMain.jsp"></jsp:include>
	</header>

	<div id="LockerMainDiv">
		<div id="LockerThemeDiv">
			<h2>처방전 보관함</h2>		
			<button onclick="upLoad();">SCAN</button>			
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
			<div>
				<div>상세정보리스트</div>
				<div id="detailList">
				<c:choose>
					<c:when test="${empty list }"></c:when>
					<c:otherwise>
						<c:forEach items="${list }" var="dto">
							<div class="listItem">
							<c:forTokens items="${dto.pres_mediname }" delims="," var="name">
								<a href="#">${name }</a>
							</c:forTokens>
							</div>
						</c:forEach>

					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
	</footer> 	  
		<div id="popUp">
			<div id="subpopUp">
				<div>
					<div>사진을 선택해주세요</div>
					<div>
					<form method="post" enctype="multipart/form-data">
					<input type="file" value="파일 선택" name="file" class="selectFile" />
					</form>
					</div>
			   </div>
			   <input type="button" value="파일업로드" onclick="fileUpload();"/>
         	</div>
        </div>

	
</body>
</html>