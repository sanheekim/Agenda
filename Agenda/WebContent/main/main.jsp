<%@page import="com.agenda.login.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="https://cdn.bootpay.co.kr/js/bootpay-3.2.6.min.js" type="application/javascript"></script>
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/main/main.css">
</head>
<script>
$(window).scroll(function(){
	if ($(this).scrollTop() > 300){
		$('#scrollTop').show();
	} else{
		$('#scrollTop').hide();
	}
});

$('#scrollTop').click(function(){
	$('html, body').animate({scrollTop:0},400);
	return false;
});
</script>
<body>

	<!-- 헤더 -->
	<header id = "header">
		<c:choose>
			<c:when test="${empty logindto }">
				<jsp:include page="../header/header.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="../header/loginMain.jsp" />
			</c:otherwise>
		</c:choose>
	</header>

	<!-- 메인 -->
	<section class="section first">
		<div class="text-wrap">
			<p class="text" data-text="아프지마요.">아프지마요.</p>
			<p class="text" data-text="괜찮아요?">괜찮아요?</p>
			<p class="text" data-text="다친데는없어요?">다친데는없어요?</p>
		</div>
		<div class="content-wrapper">
			<div class="content"></div>
			<div class="content"></div>
			<div class="content"></div>
		</div>
	</section>


	<section class="section second">
		<p>M E N U</p>
		<div class="menu-wrap">
			<div class="menu">
				<div class="menu-icon">
					<div class="menu-icon-inside"
						onClick="location.href ='${pageContext.request.contextPath}/MainController?command=mediSearch'">
						<svg width="50" height="50" viewBox="0 0 197 197" fill="none"
							xmlns="http://www.w3.org/2000/svg">
                                <path d="M75.8689 163.115L75.8748 163.109L115.152 123.832L115.859 123.125L115.152 122.418L74.582 81.8482L73.8749 81.1411L73.1678 81.8482L33.8909 121.125L33.885 121.131C28.3951 126.715 25.3333 134.241 25.3664 142.071C25.3995 149.901 28.5247 157.401 34.0616 162.938C39.5985 168.475 47.0986 171.6 54.9289 171.633C62.7591 171.667 70.2854 168.605 75.8689 163.115ZM122.418 115.152L123.125 115.859L123.832 115.152L162.552 76.4315C162.552 76.4314 162.553 76.4312 162.553 76.431C166.723 72.291 169.568 67.0045 170.725 61.2436C171.883 55.4824 171.302 49.5071 169.055 44.0773C166.808 38.6475 162.997 34.0086 158.106 30.7505C153.217 27.4929 147.469 25.7627 141.594 25.7797C137.692 25.765 133.825 26.5229 130.218 28.0098C126.609 29.4971 123.33 31.6843 120.571 34.4453L81.8481 73.1679L81.141 73.875L81.8481 74.5821L122.418 115.152ZM16.1346 141.732C15.9136 131.429 19.677 121.439 26.64 113.844L113.351 27.1943C120.947 20.2312 130.937 16.4677 141.239 16.6887C151.549 16.9098 161.374 21.1038 168.666 28.3954C175.958 35.687 180.152 45.5128 180.373 55.8223C180.594 66.1246 176.83 76.1145 169.867 83.71L83.1563 170.36C75.5606 177.323 65.5707 181.086 55.2682 180.865C44.9587 180.644 35.1329 176.45 27.8413 169.159C20.5497 161.867 16.3557 152.041 16.1346 141.732Z"
								fill="black" stroke="black" stroke-width="2" />
                                <path d="M77.5389 144.086C76.5731 143.127 75.2671 142.589 73.9059 142.589C72.5441 142.589 71.2376 143.128 70.2716 144.087C70.2713 144.088 70.271 144.088 70.2708 144.088L64.1172 150.242L77.5389 144.086ZM77.5389 144.086C78.498 145.052 79.0363 146.358 79.0363 147.719C79.0363 149.081 78.4975 150.388 77.5376 151.354C77.5374 151.354 77.5371 151.354 77.5369 151.354L71.3836 157.508C67.1508 161.73 61.416 164.102 55.4372 164.102C49.4718 164.102 43.7494 161.741 39.5195 157.536C38.6908 156.554 38.2584 155.297 38.308 154.012C38.3582 152.715 38.8961 151.484 39.8144 150.565C40.7327 149.647 41.9637 149.109 43.2614 149.059C44.547 149.009 45.8042 149.442 46.7866 150.271C47.9197 151.398 49.2622 152.293 50.7388 152.906C52.2282 153.523 53.8248 153.841 55.4372 153.841C57.0495 153.841 58.6461 153.523 60.1355 152.906C61.6247 152.288 62.9775 151.383 64.1166 150.242L77.5389 144.086Z"
								fill="black" stroke="black" stroke-width="2" />
                        </svg>
					</div>
				</div>
				<div class="description">
					<p>의약품 찾기</p>
				</div>
			</div>
			<div class="menu">
				<div class="menu-icon">
					<div class="menu-icon-inside"
						onClick="location.href ='${pageContext.request.contextPath}/MainController?command=mediLocker'">
						<svg width="50" height="50" viewBox="0 0 203 203" fill="none"
							xmlns="http://www.w3.org/2000/svg">
                                <path d="M58.7083 133.242V133.742H59.2083H123.064H123.564V133.242V122.599V122.099H123.064H59.2083H58.7083V122.599V133.242Z"
								fill="black" stroke="black" />
                                <path d="M58.7083 90.6715V91.1715H59.2083H144.349H144.849V90.6715V80.0288V79.5288H144.349H59.2083H58.7083V80.0288V90.6715Z"
								fill="black" stroke="black" />
                                <path d="M58.7083 111.957V112.457H59.2083H144.349H144.849V111.957V101.314V100.814H144.349H59.2083H58.7083V101.314V111.957Z"
								fill="black" stroke="black" />
                                <path d="M136.263 15.8195L136.117 15.6731H135.91H37.9231H37.4231V16.1731V186.455V186.955H37.9231H165.635H166.135V186.455V45.8873V45.6801L165.988 45.5337L136.263 15.8195ZM134.196 47.601V30.2203L151.582 47.601H134.196ZM123.064 59.2383H154.492V175.313H49.0657V27.3051H122.564V58.7383V59.2383H123.064Z"
								fill="black" stroke="black" />
                       </svg>
					</div>
				</div>
				<div class="description">
					<p>처방전 보관함</p>
				</div>
			</div>
			<div class="menu">
				<div class="menu-icon">
					<div class="menu-icon-inside"
						onClick="location.href ='${pageContext.request.contextPath}/MainController?command=findPharm'">
						<svg width="50" height="50" viewBox="0 0 210 210" fill="none"
							xmlns="http://www.w3.org/2000/svg">
                                <path d="M106.555 8.77109C88.7377 8.77109 71.6502 15.849 59.0516 28.4476C46.4529 41.0463 39.375 58.1338 39.375 75.9511C39.375 110.549 94.8657 185.723 101.181 194.188C101.806 195.022 102.618 195.699 103.551 196.166C104.483 196.632 105.512 196.875 106.555 196.875C107.598 196.875 108.627 196.632 109.559 196.166C110.492 195.699 111.304 195.022 111.929 194.188C118.244 185.723 173.735 110.549 173.735 75.9511C173.735 67.1289 171.997 58.393 168.621 50.2424C165.245 42.0917 160.297 34.6859 154.058 28.4476C147.82 22.2094 140.414 17.261 132.264 13.8849C124.113 10.5087 115.377 8.77109 106.555 8.77109V8.77109ZM106.555 178.736C87.7446 152.469 52.811 98.9938 52.811 75.9511C52.811 61.6973 58.4733 48.0273 68.5522 37.9483C78.6312 27.8694 92.3012 22.2071 106.555 22.2071C120.809 22.2071 134.479 27.8694 144.558 37.9483C154.637 48.0273 160.299 61.6973 160.299 75.9511C160.299 98.9938 125.365 152.469 106.555 178.736Z"
								fill="black" />
                                <path d="M106.555 42.3611C99.9115 42.3611 93.4172 44.3311 87.8934 48.022C82.3696 51.7129 78.0642 56.9589 75.5219 63.0967C72.9796 69.2345 72.3144 75.9883 73.6104 82.5041C74.9065 89.0199 78.1056 95.0051 82.8033 99.7027C87.5009 104.4 93.4861 107.6 100.002 108.896C106.518 110.192 113.272 109.526 119.409 106.984C125.547 104.442 130.793 100.136 134.484 94.6126C138.175 89.0888 140.145 82.5945 140.145 75.951C140.145 67.0424 136.606 58.4987 130.307 52.1993C124.007 45.9 115.464 42.3611 106.555 42.3611V42.3611ZM106.555 96.105C102.569 96.105 98.6723 94.923 95.358 92.7085C92.0437 90.4939 89.4605 87.3463 87.9351 83.6636C86.4097 79.981 86.0106 75.9287 86.7883 72.0192C87.5659 68.1097 89.4854 64.5186 92.304 61.7C95.1226 58.8814 98.7137 56.9619 102.623 56.1843C106.533 55.4067 110.585 55.8058 114.268 57.3312C117.95 58.8566 121.098 61.4398 123.312 64.7541C125.527 68.0684 126.709 71.965 126.709 75.951C126.709 81.2962 124.586 86.4225 120.806 90.2021C117.026 93.9817 111.9 96.105 106.555 96.105Z"
								fill="black" />
                        </svg>
					</div>
				</div>
				<div class="description">
					<p>약국 찾기</p>
				</div>
			</div>
			<div class="menu">
				<div class="menu-icon">
					<div class="menu-icon-inside"
						onClick="location.href ='${pageContext.request.contextPath}/MainController?command=notice'">
						<svg width="50" height="50" viewBox="0 0 211 211" fill="none"
							xmlns="http://www.w3.org/2000/svg">
                                <path d="M158.25 72.5312H138.469V32.9688C138.469 27.7224 136.385 22.691 132.675 18.9813C128.965 15.2716 123.934 13.1875 118.688 13.1875H116.05C111.632 13.1909 107.343 14.6731 103.865 17.3977C100.388 20.1223 97.9224 23.9326 96.8622 28.2213L83.6747 64.4869C82.8486 66.7973 81.3413 68.8029 79.3519 70.2391C77.3624 71.6753 74.9843 72.4745 72.5312 72.5312H19.7812V191.219H158.25C166.994 191.219 175.38 187.745 181.562 181.562C187.745 175.38 191.219 166.994 191.219 158.25V105.5C191.219 96.7561 187.745 88.3704 181.562 82.1876C175.38 76.0047 166.994 72.5313 158.25 72.5312V72.5312ZM59.3438 178.031H32.9688V85.7188H59.3438V178.031ZM178.031 158.25C178.031 163.496 175.947 168.528 172.237 172.237C168.528 175.947 163.496 178.031 158.25 178.031H72.5312V85.7188C77.7499 85.7191 82.8405 84.1026 87.103 81.0917C91.3655 78.0808 94.5906 73.8233 96.3347 68.9047L109.522 32.3094V31.5181C109.857 30.0307 110.697 28.7053 111.899 27.7676C113.102 26.8299 114.592 26.3377 116.116 26.375H118.688C120.436 26.375 122.113 27.0697 123.35 28.3063C124.587 29.5428 125.281 31.22 125.281 32.9688V85.7188H158.25C163.496 85.7188 168.528 87.8028 172.237 91.5125C175.947 95.2222 178.031 100.254 178.031 105.5V158.25Z"
								fill="black" />
					</div>
				</div>
				<div class="description">
					<p>공지사항</p>
				</div>
			</div>
			<div class="menu">
				<div class="menu-icon">
					<div class="menu-icon-inside"
						onClick="location.href ='${pageContext.request.contextPath}/MainController?command=qna'">
						<svg width="50" height="50" viewBox="0 0 95 150" fill="none"
							xmlns="http://www.w3.org/2000/svg">
                                <path d="M35.8283 108.203V99.3169C35.8283 90.636 38.1386 86.09 43.5413 79.7969L60.7899 59.8477C69.7873 49.4439 72.8725 44.4566 72.8725 37.0814C72.8725 24.725 62.5836 16.9206 47.9109 16.9206C33.5036 16.9206 24.2408 24.2897 21.1555 37.5166C20.8973 38.8164 19.8712 39.4692 18.3214 39.2516L2.11326 36.8638C0.563472 36.6461 -0.204247 35.7817 0.0468753 34.4759C3.91417 13.6622 21.6721 0 48.4347 0C76.216 0 95 15.3911 95 36.8577C95 47.2676 90.6376 54.8543 81.8842 65.0466L64.6428 84.9958C59.4984 90.8476 57.9486 93.8823 57.9486 101.251V108.185C57.9486 109.485 56.9226 110.349 55.38 110.349H38.3969C36.8543 110.374 35.8283 109.503 35.8283 108.203ZM34.544 129.023C34.544 127.723 35.57 126.859 37.1198 126.859H56.6786C58.2213 126.859 59.2473 127.729 59.2473 129.023V147.667C59.2473 148.972 58.2141 149.831 56.6786 149.831H37.1198C35.5628 149.831 34.544 148.966 34.544 147.667V129.023Z"
								fill="black" />
                        </svg>
					</div>
				</div>
				<div class="description">
					<p>Q & A</p>
				</div>
			</div>
		</div>
	</section>

	<section class="section third">
		<p>D O N A T I O N</p>
		<div class="donation-wrapper">
			<div class="donation-content" onclick="pay01()">
				<div class="donation-content-01">
					<p>1,000</p>
				</div>
			</div>
			<div class="donation-content" onclick="pay02()">
				<div class="donation-content-01">
					<p>5,000</p>
				</div>
			</div>
			<div class="donation-content" onclick="pay03()">
				<div class="donation-content-01">
					<p>10,000</p>
				</div>
			</div>

			<div class="donation-content" onclick="pay04()">
				<div class="donation-content-01">
					<p>50,000</p>
				</div>
			</div>
		</div>
	</section>

	
	<section class="section fourth">
		<div class="fourth-title">
				<h2>How to Scan Prescription</h2>
			</div>
		<div class="fourth-pages">
			<div>
				<h2>01. how to scan my Prescription?</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mae
					cenas ex justo, efficitur a iaculis ac, pellentesque a odio.
					Phasellus non eros eu nulla hendrerit s celerisque sed at justo.
					Sed vulputate.</p>
			</div>
			<div>
				<h2>02.Save your page in cabinet</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mae
					cenas ex justo, efficitur a iaculis ac, pellentesque a odio.
					Phasellus non eros eu nulla hendrerit s celerisque sed at justo.
					Sed vulputate.</p>
			</div>
			<div>
				<h2>03. You're Done!</h2>
			</div>
		</div>
	</section>

	<section class="section contact">
		<div class="contact-title">Do you need help?</div>
		<div class="contact-sub">There are many variations of passages
			of Lorem Ipsum availd chunks as necessary, making this the first true
			generator on the Internet. It uses a dictionary of over 200 Latin
			words, com</div>
			<!-- 탑 이동 -->
		<div id ="scrollTop"><span>TOP</span></div>
	</section>

	<!-- 풋터 -->
	<footer id =footer>
		<jsp:include page="../footer/mainFooter.jsp" />
	</footer>

	<!-- 커서 -->
	<div id ="cursor"></div>
	<div id ="cursor2"></div>

<script type="text/javascript" src="${pageContext.request.contextPath}/main/mainpay1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/mainpay2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/mainpay3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/mainpay4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ScrollMagic/2.0.7/ScrollMagic.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ScrollMagic/2.0.7/plugins/debug.addIndicators.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/main/main.js"></script>
</body>
</html>