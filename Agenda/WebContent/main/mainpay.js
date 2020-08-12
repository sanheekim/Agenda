/* function pay01~04(){} 안에 있는 코드는
 * 부트페이 공식홈페이지에서 제공.
 * 
 * main.jsp에서 온클릭 버튼을 눌렀을 때 결제창이 띄워지고
 * 결제한 결과값(이하 '결제값')이 JSON 형태로 {key:value} 나타남.
 * 
 * .error(function (data) {})에는 결제값이 error가 날 경우
 * .cancel에는 결제를 취소할 경우 등등 처리할 코드를 써주면 되는데,
 * 우리는 결제가 끝났을 경우(done)만 사용하면 됨.
 * 
 * */

function pay01() {
	
BootPay.request({
	price: '1000',
	application_id: "5f17cf9f8f0751002136c6b9",
	name: '커피 한 잔을 후원하시겠습니까?',
	pg: '',
	method: 'card',
	show_agree_window: 0,
	items: [
		{
			item_name:'A Cup of Coffee',
			qty: 1,
			unique: '123',
			price: 1000,
		}
	],
	user_info: {
		username: '사용자 이름',
		email: '사용자 이메일',
		addr: '사용자 주소',
		phone: '010-1234-4567'
	},
	order_id: '고유order_id_1234',
	params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
	account_expire_at: '2018-05-25',
	extra: {
	    start_at: '2019-05-10',
		end_at: '2022-05-10',
        vbank_result: 1,
        quota: '0,2,3',
		theme: 'purple',
		custom_background: '#00a086',
		custom_font_color: '#ffffff'
	}
}).error(function (data) {
	console.log(data);
}).cancel(function (data) {
	console.log(data);
}).ready(function (data) {
	console.log(data);
	/* .confirm 코드는 기본제공.
	 * 결제값이 Bootpay 공식홈페이지의 결제내역으로 넘어가도록 함.*/
}).confirm(function (data) {
	console.log(data);
	var enable = true;
	if (enable) {
		BootPay.transactionConfirm(data);
	} else {
		BootPay.removePaymentWindow();
	}
}).close(function (data) {
    console.log(data);
    /* 결제가 완료(done) 되면 실행될 함수*/
}).done(function (data) {
		console.log(data);
		
		/* main.jsp 후원 부분의 c:when 하위 div에 id와 title이 있음.
		 * var(변수) member_id는 #donaClick01이라는 id를 가진 div에서 title 값을 attr(잘라서 가져오기)한 것 */	
		var member_id = $("#donaClick01").attr("title");
		console.log("컨트롤러 넘어가기전 아이디" + member_id)
		console.log("끝 : "+data);
		
		/* ajax를 통해서 url에 json형태의 결과값인 data를 string형태로 바꾼 obj와, 위에서 만든 변수인 member_id를 같이 보냄
		 * (여기까지 읽고 dnController의 donation으로 가세요)
		 * dnController의 "donation"으로 간 json 형태의 값은 다시 이곳으로 돌아와서
		 * success니까 member_id와 같이 dnController의 "dnlist"로 이동함.
		 * */
		$.ajax({
			url: "./dnController?command=donation",
			method: "post",
			data: {"obj" : JSON.stringify(data), member_id : member_id},
			success: function(msg){
				location.href="./dnController?command=dnlist&member_id="+member_id;
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}



function pay02(){

	BootPay.request({
		price: '5000',
		application_id: "5f17cf9f8f0751002136c6b9",
		name: '케이크 한 조각을 후원하시겠습니까?',
		pg: '',
		method: 'card',
		show_agree_window: 0,
		items: [
			{
				item_name: 'A Piece of Cake',
				qty: 1,
				unique: '123',
				price: 5000,
			}
		],
		user_info: {
			username: '사용자 이름',
			email: '사용자 이메일',
			addr: '사용자 주소',
			phone: '010-1234-4567'
		},
		order_id: '고유order_id_1234',
		params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
		account_expire_at: '2018-05-25',
		extra: {
		    start_at: '2019-05-10',
			end_at: '2022-05-10',
	        vbank_result: 1,
	        quota: '0,2,3',
			theme: 'purple',
			custom_background: '#00a086',
			custom_font_color: '#ffffff'
		}
	}).error(function (data) {
		console.log("에러 : " + data);
	}).cancel(function (data) {
		console.log("취소 : " + data);
	}).ready(function (data) {
		console.log("준비 : " + data);
	}).confirm(function (data) {
		console.log("확인 : " + data);
		var enable = true;
		if (enable) {
			BootPay.transactionConfirm(data);
		} else {
			BootPay.removePaymentWindow();
		}
	}).close(function (data) {
	    console.log(data);
	}).done(function (data) {
		console.log(data);
		
		var member_id = $("#donaClick02").attr("title");
		console.log("컨트롤러 넘어가기전 아이디" + member_id)
		console.log("끝 : "+data);
		
		$.ajax({
			url: "./dnController?command=donation",
			method: "post",
			data: {"obj" : JSON.stringify(data), member_id : member_id},
			success: function(msg){
				location.href="./dnController?command=dnlist&member_id="+member_id;
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}




function pay03(){

	BootPay.request({
		price: '10000',
		application_id: "5f17cf9f8f0751002136c6b9",
		name: '커피와 조각케이크를 후원하시겠습니까?',
		pg: '',
		method: 'card',
		show_agree_window: 0,
		items: [
			{
				item_name: 'Coffee With a Piece of Cake',
				qty: 1,
				unique: '123',
				price: 10000,
			}
		],
		user_info: {
			username: '사용자 이름',
			email: '사용자 이메일',
			addr: '사용자 주소',
			phone: '010-1234-4567'
		},
		order_id: '고유order_id_1234',
		params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
		account_expire_at: '2018-05-25',
		extra: {
		    start_at: '2019-05-10',
			end_at: '2022-05-10',
	        vbank_result: 1,
	        quota: '0,2,3',
			theme: 'purple',
			custom_background: '#00a086',
			custom_font_color: '#ffffff'
		}
	}).error(function (data) {
		console.log(data);
	}).cancel(function (data) {
		console.log(data);
	}).ready(function (data) {
		console.log(data);
	}).confirm(function (data) {
		console.log(data);
		var enable = true;
		if (enable) {
			BootPay.transactionConfirm(data);
		} else {
			BootPay.removePaymentWindow();
		}
	}).close(function (data) {
	    console.log(data);
	}).done(function (data) {
		console.log(data);
		
		var member_id = $("#donaClick03").attr("title");
		console.log("컨트롤러 넘어가기전 아이디" + member_id)
		console.log("끝 : "+data);
		
		console.log(data);
		$.ajax({
			url: "./dnController?command=donation",
			method: "post",
			data: {"obj" : JSON.stringify(data), member_id : member_id},
			success: function(msg){
				location.href="./dnController?command=dnlist&member_id="+member_id;
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
	}



function pay04(){

	BootPay.request({
		price: '50000',
		application_id: "5f17cf9f8f0751002136c6b9",
		name: '케이크 세트를 후원하시겠습니까?',
		pg: '',
		method: 'card',
		show_agree_window: 0,
		items: [
			{
				item_name: 'Whole Cake Set',
				qty: 1,
				unique: '123',
				price: 50000,
			}
		],
		user_info: {
			username: '사용자 이름',
			email: '사용자 이메일',
			addr: '사용자 주소',
			phone: '010-1234-4567'
		},
		order_id: '고유order_id_1234',
		params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
		account_expire_at: '2018-05-25',
		extra: {
		    start_at: '2019-05-10',
			end_at: '2022-05-10',
	        vbank_result: 1,
	        quota: '0,2,3',
			theme: 'purple',
			custom_background: '#00a086',
			custom_font_color: '#ffffff'
		}
	}).error(function (data) {
		console.log(data);
	}).cancel(function (data) {
		console.log(data);
	}).ready(function (data) {
		console.log(data);
	}).confirm(function (data) {
		console.log(data);
		var enable = true;
		if (enable) {
			BootPay.transactionConfirm(data);
		} else {
			BootPay.removePaymentWindow();
		}
	}).close(function (data) {
	    console.log(data);
	}).done(function (data) {
		console.log(data);
		
		var member_id = $("#donaClick04").attr("title");
		console.log("컨트롤러 넘어가기전 아이디" + member_id)
		console.log("끝 : "+data);
		
		$.ajax({
			url: "./dnController?command=donation",
			method: "post",
			data: {"obj" : JSON.stringify(data), member_id : member_id},
			success: function(msg){
				location.href="./dnController?command=dnlist&member_id="+member_id;
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}