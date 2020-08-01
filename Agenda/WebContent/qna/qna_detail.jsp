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
<link rel="stylesheet" href="${pageContext.request.contextPath}/qna/qna_detail.css">
<title>Insert title here</title>
</head>
<body>
  	 
  	<jsp:include page="../header/header.jsp" />
	
	<section>
		<h1>Q&A 글보기</h1>
		<hr>
		<!-- 글 상세내역 부분 -->
		<form action="${pageContext.request.contextPath}/qnaController.do" method="post">
			<input type="hidden" name="qna_no" value="${detail.qna_no }">
			<input type="hidden" name="command" value="comment">
			<table id="board">
				<tr>
					<td colspan="6"><input type="hidden" name="qna_title"
						value="${detail.qna_title }">${detail.qna_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="hidden" name="member_id"
						value="${detail.member_id }">${detail.member_id}</td>
					<th>작성시간</th>
					<td>작성시간</td>
					<th>조회</th>
					<td>조회수</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="viewer" name="qna_content" value="${detail.qna_content }"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="button" value="수정하기"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=update&qna_no=${detail.qna_no }'">
						<input type="button" value="목록으로"
						onclick="location.href='${pageContext.request.contextPath}/qna/index.jsp'"> 
						<input type="button" value="글삭제"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=delete&qna_no=${detail.qna_no }'">
					</td>
				</tr>
				<!-- 댓글영역 -->
				<%-- <c:if test="댓글이 있으면">
					<c:forEach var="" items="">
						<tr>
							<td>아이디</td>
							<td>내용</td>
							<td>작성일</td>
						</tr>
					</c:forEach>
				</c:if> --%>
			</table>
		</form>
		
		<!-- 댓글 작성 영역 -->
		<form action="commController.do" method="post">
			<input type="hidden" name="comm_step" value="1">
			<input type="hidden" name="qna_no" value="${detail.qna_no }">
			<table>
				<tr>
					<th>작성자</th>
					<td><textarea name="comm_content"></textarea></td>
					<td>작성시간</td>
					<td><input type="button" value="작성" onclick="location.href='../commController.do?command=write'"></td>
				</tr>
			</table>
		</form>
	</section>
	
    <jsp:include page="../footer/mainFooter.jsp" />
</body>
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>
<script>
	const viewer = new toastui.Editor({
		el : document.querySelector('#viewer'),
		height : '300px',
		initialValue : `${detail.qna_content }`
	});
</script>
</html>