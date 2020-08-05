// 첫페이지 텍스트
var text = document.getElementsByClassName('text');
var shadow = '';
for (var i = 0; i < 20; i++){
    shadow +=(shadow? ',':'')+ -i*1+'px ' + i*1+'px 0 #d9d9d9' ;
}

for (var k = 0; k < 3; k ++){
	text[k].style.textShadow = shadow;
}

// 커서
let cousor = document.getElementById("cursor");
let cousorArea1 = document.getElementsByClassName("donation");
let cousorArea2 = document.getElementsByClassName("fourth");
let cousorArea3 = document.getElementsByClassName("contact");


cousorArea2[0].addEventListener("mousemove", function(e) {
	cursor.style.left = e.pageX + "px";
	cursor.style.top = e.pageY + "px";
});

cousorArea3[0].addEventListener("mousemove", function(e) {
	cursor.style.left = e.pageX + "px";
	cursor.style.top = e.pageY + "px";
});


// 스크롤
function splitScroll() {
	const controller = new ScrollMagic.Controller();

	new ScrollMagic.Scene({
		duration : "200%",
		triggerElement : ".fourth-title",
		triggerHook : 0,
	}).setPin(".fourth-title").addTo(controller);
}

splitScroll();
	

// 도네이션 페이지 효과
var icon = document.querySelectorAll('.menu-icon-inside')

for(let j = 0; j < icon.length; j++){
    icon[j].addEventListener('mouseover', function(){
        icon[j].setAttribute("style","background: linear-gradient(200deg, #ffffff, #dddddd);")
    })
    
    icon[j].addEventListener('mouseout', function(){
        icon[j].removeAttribute("style");
    })
}