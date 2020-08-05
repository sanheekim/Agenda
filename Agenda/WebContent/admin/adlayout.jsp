<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<style type="text/css">

	body{
 		margin: 0 0;
 		padding: 0 0;
	}

	#admin {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100vw;
		height: 100vh;
	}
	
	#ad_top {
		display: flex;
		justify-content: center;
		width: 100vw;
		height: 150px;
	}
	
	#ad_title {
		display: flex;
		justify-content: center;
		align-items: center;
		font-family: Montserrat;
		font-style: normal;
		font-weight: normal;
		font-size: 44px;
		line-height: 72px;
		color: #000000;		
	}
	
	#ad_bottom {
		width: 100vw;
		top: 100vh;
		display: flex;
		align-items: center;
		justify-content: space-around;
	}
	
	.ad_box {
		width: 200px;
		height: 400px;
		top: 373px;
		bottom: 40px;
		background: #FAFAFA;
		cursor: pointer;
		text-align: center;
	}
	
	#ad_box span {
		display: teble-cell;
		verical-align: middle;
	}
	
	.icon {
		width: 100px;
		height: 100px;
		display: block;
		position: relative;
		top: 150px;
		margin: 0px auto;
	}
	
	.btn {
		position: absolute;
		background-color: #CED8F6;
		top: 200px;
	}
	
	#ad_footer {
		position: relative;
		width: 100vw;
		height: 300px;
		bottom: 0;
		background: #E5E5E5;
	}
	
	
</style>

</head>
<body>

<div id="admin">

	<div id="ad_top">
		<div id="ad_title">관리자 페이지</div>
	</div>
	
	<div id="ad_bottom">
			<div class="ad_box" onclick="location.href='../admin/admyinfo.jsp'">
			<span>내 정보 수정</span>
			<img class="icon" src="image/info.png" />
			</div>
			<div class="ad_box" onclick="location.href='../adController?command=allMember'">
			<span>회원 정보 관리</span>
			<img class="icon" src="image/search.png" />
			</div>
			<div class="ad_box" onclick="">
			<span>전체 채팅기록</span>
			<img class="icon" src="image/chat.png" />
			</div>
			<div class="ad_box" onclick="location.href='../dnController?command=alldnlist'">
			<span>전체 후원기록</span>
			<img class="icon" src="image/qr.png" />
			</div>
	</div>	

	<jsp:include page="../footer/mainFooter.jsp" />

<script type="text/javascript" src="adtransfer.js"></script>

</body>
</html>