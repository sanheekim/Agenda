<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Editor's Dependecy Style -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<!-- Editor's Style -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/notice/noticeUpdate.css">
<title>Insert title here</title>
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
		<h1>공지사항 글수정</h1>
		<hr>
		<form action="${pageContext.request.contextPath}/NoticeController.do" method="post">
			<input type="hidden" name="notice_no" value="${update.notice_no }">
			<input type="hidden" name="command" value="updateres">
			<table border="1">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="member_id"
						value="${update.member_id }"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="notice_title"
						value="${update.notice_title }"></td>
				</tr>
				<tr>
					<td colspan="2"><div id="editor" name="notice_content"></div></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="수정" onclick="formSubmit()"> 
					<input type="button" value="취소"
						onclick="location.href='${pageContext.request.contextPath}/notice/index.jsp'"></td>
				</tr>
			</table>
		</form>
	</section>
    <jsp:include page="../footer/mainFooter.jsp" />
</body>
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
	const editor = new toastui.Editor({
		el : document.querySelector('#editor'),
		previewStyle : 'tab',
		height : '500px',
		language : 'ko',
		initialValue : `${update.notice_content }`,
		initialEditType : 'wysiwyg'
	});

	editor.getHtml();

	function formSubmit() {
		let input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "notice_content")
		input.setAttribute("value", editor.getHtml());
		document.querySelector("#editor").append(input);
	}
</script>
</html>