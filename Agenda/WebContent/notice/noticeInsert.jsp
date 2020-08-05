<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Editor's Dependecy Style -->
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"
  />
  <!-- Editor's Style -->
  <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/notice/noticeInsert.css">
<title>Agenda-공지사항글쓰기</title>
</head>
<body>
<!--logindto값의 유무에 따라 헤더 변경  -->
<c:choose>
	<c:when test="${empty logindto }">
		<jsp:include page="../header/header.jsp" />
	</c:when>
	<c:otherwise>
		<jsp:include page="../header/loginMain.jsp"/>
	</c:otherwise>
</c:choose>

<!--화면-->
	<section>
		<h1>공 지 사 항 글 작 성</h1>
		<hr>
		<form action="${pageContext.request.contextPath}/NoticeController.do" method="post">
		
		<input type="hidden" name="command" value="writeres">
		
		<input type="hidden" name="member_id" value="${logindto.member_id }">
			<table>
				<tr>
					<th>작성자</th>
					<td><c:out value="${logindto.member_id }"></c:out></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="notice_title"></td>
				</tr>
				<tr>
					<td colspan="2"><div id="editor" name="notice_content"></div></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="작성" onclick="formSubmit()">
					<input type="button" value="취소"
					onclick="location.href='${pageContext.request.contextPath}/notice/index.jsp'">
					</td>
				</tr>
			</table>
		</form>

	</section>
	<jsp:include page="../footer/mainFooter.jsp" />
	<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script>
		const editor = new toastui.Editor({
			el : document.querySelector('#editor'),
			previewStyle : 'tab',
			height : '400px',
			language : 'ko',
			initialEditType: 'wysiwyg'
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
</body>
</html>