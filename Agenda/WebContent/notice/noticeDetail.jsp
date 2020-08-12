<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/tui-editor/dist/tui-editor.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/notice/noticeDetail.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>Insert title here</title>
</head>
<script>

</script>
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
		<h1>공지사항 글보기</h1>
		
		<!-- 글 상세내역 부분 -->
			<input type="hidden" name="notice_no" value="${detail.notice_no }" id="notice_no">
			<table id="board">
				<tr>
					<td colspan="6"><input type="hidden" name="notice_title"
						value="${detail.notice_title}">${detail.notice_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="hidden" name="member_id"
						value="${detail.member_id }">${detail.member_id}</td>
					<th>작성시간</th>
					<td>${detail.notice_regdate}</td>
					<th>조회</th>
					<td>${detail.notice_hit }</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="viewer" name="notice_content" value="${detail.notice_content }"></div>
					</td>
				</tr>
				<tr>
				
				
					<td colspan="6">
					
					<c:choose>
				 		<c:when test="${logindto.member_role eq 'ADMIN'}">
				 			
							<input type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/NoticeController.do?command=update&notice_no=${detail.notice_no }'">
							<input type="button" value="목록으로" onclick="location.href='${pageContext.request.contextPath}/notice/index.jsp'">
							<input type="button" value="글삭제" onclick="location.href=' ${pageContext.request.contextPath}/NoticeController.do?command=delete&notice_no=${detail.notice_no }'">
						</c:when>
					<c:otherwise>
						
						
					</c:otherwise>
				</c:choose>
					
				
						
					
					</td>
				</tr>
			</table>
			<!-- 댓글 목록 -->
			<div id="listReply">
			<table>
				<c:forEach var="row" items="${commlist}">
				<tr>	
					<td>
						번호 : ${row.comm_no }
						<br>
						작성자 : ${row.member_id}
						<br>
						<input type="text" value="${row.comm_content }" disabled="disabled" class="comm_content">
						<br>
						날짜 : ${row.comm_regdate}
						<br>
						<hr>
						
						
						<c:if test="${logindto.member_id == row.member_id || logindto.member_role == 'ADMIN'  }">
						<input type="button" value="수정" class="btnUpdate" name="${row.comm_no }">
						</c:if>
						<c:if test="test=${logindto.member_id == row.member_id }">
						<input type="button" value="삭제" class="btnDelete" name="${row.comm_no }">
						</c:if>
						
						
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
	</section>
	
    <jsp:include page="../footer/mainFooter.jsp" />
</body>
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>
<script>
	const viewer = new toastui.Editor({
		el : document.querySelector('#viewer'),
		height : '300px',
		initialValue : `${detail.notice_content }`
	});
</script>
</html>