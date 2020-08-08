window.onload = function() {
	
BootPay.request({
	price: '1000',
	application_id: "5f17cf9f8f0751002136c6b9",
	name: '1,000원을 후원하시겠습니까?',
	pg: '',
	method: 'card',
	show_agree_window: 0,
	items: [
		{
			item_name: '1,000원',
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
		
		$.ajax({
			url: "./dnController?command=donation",
			method: "post",
			data: {"obj" : JSON.stringify(data), member_id},
			success: function(msg){
				location.href="./dnController?command=dnlist";
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}