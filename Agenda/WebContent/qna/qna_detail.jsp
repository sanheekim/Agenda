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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>Insert title here</title>
</head>
<script>
$(document).ready(function(){
	/* --------------- 댓글 관련 -------------- */
	// 1. 댓글 입력
	$("#btnReply").click(function(){
		//reply(); // 폼데이터 형식으로 입력
		replyJson(); // json 형식으로 입력
	});
	
	// 2. 댓글 목록
	listReply(); // 댓글 목록 불러오기
	//listReply2(); // json 리턴방식
	//listReplyRest("1"); // rest방식
	
});

/* --------------- 댓글 관련 -------------- */

// 1_1. 댓글 입력 함수(폼 데이터 방식)
function reply(){
	var contentReply=$("#contentReply").val();
	var qna_no="${detail.qna_no}"
	var param="contentReply="+contentReply+"&qna_no="+qna_no;
	$.ajax({				
		type: "post",
		url: "${pageContext.request.contextPath}/commController.do?command=write",
		data: param,
		success: function(){
			alert("댓글이 등록되었습니다.");
			//listReply2();
			listReply();
		}
	});
}

// 1_2. 댓글 입력 함수(json방식)
function replyJson(){
	var contentReply=$("#contentReply").val();
	var qna_no="${detail.qna_no}"
	$.ajax({				
		type: "post",
		url: "${pageContext.request.contextPath}/commController.do?command=write",
		dateType: "text",
		// param형식보다 간편
		data: JSON.stringify({
			contentReply : contentReply,
			qna_no : qna_no
		}),
		success: function(){
			alert("댓글이 등록되었습니다.");
			// 댓글 입력 완료후 댓글 목록 불러오기 함수 호출
			listReply(); 	// 전통적인 Controller방식
			//listReply2(); 	// json리턴 방식
			//listReplyRest("1"); // Rest 방식
		}
	});
}

// 2_1. 댓글 목록 - 전통적인 Controller방식
function listReply(){
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/commController.do?command=commlist&qna_no=${detail.qna_no }",
		success: function(result){
		// responseText가 result에 저장됨.
			$("#listReply").html(result);
		}
	});
}

// 2_2. 댓글 목록 - RestController를 이용 json형식으로 리턴
function listReply2(){
	$.ajax({
		type: "get",
		//contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
		url: "${path}/reply/listJson.do?qna_no=${dto.qna_no}",
		success: function(result){
			console.log(result);
			var output = "<table>";
			for(var i in result){
				output += "<tr>";
				output += "<td>"+result[i].member_in;
				output += result[i].contentReply+"</td>";
				output += "<tr>";
			}
			output += "</table>";
			$("#listReply").html(output);
		}
	});
};

</script>
<body>
  	 
  	<jsp:include page="../header/header.jsp" />
	
	<section>
		<h1>Q&A 글보기</h1>
		<hr>
		<!-- 글 상세내역 부분 -->
		<form action="${pageContext.request.contextPath}/commController.do" method="post">
			<input type="hidden" name="command" value="write">
			<input type="hidden" name="comm_step" value="1">
			<input type="hidden" name="qna_no" value="${detail.qna_no }" id="qna_no">
			<table id="board">
				<tr>
					<td colspan="6">
					<input type="hidden" name="qna_title" value="${detail.qna_title }">${detail.qna_title }
					</td>
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
			<!-- 댓글 작성 영역 -->
				<tr>
					<th>작성자</th>
					<td colspan="3"><textarea name="comm_content" id="contentReply"></textarea></td>
					<td>작성시간</td>
					<td><input type="button" value="작성" id="btnReply"></td>
				</tr>
			</table>
		</form>
		<!-- 댓글 목록 -->
		<div id="listReply"></div>
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