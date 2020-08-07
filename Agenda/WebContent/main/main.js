// 3D텍스트
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
let cousorArea = document.getElementsByClassName("area");

for (var l = 0; l < 4; l++) {
	cousorArea[l].addEventListener("mousemove", function(e) {
		cursor.style.visibility = "hidden";
	});
}

for (var p = 4; p < cousorArea.length; p++){
	cousorArea[p].addEventListener("mousemove", function(e) {
		cursor.style.visibility = "visible";
		cursor.style.left = e.pageX + "px";
		cursor.style.top = e.pageY + "px";
	});
}

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
	

// 메뉴 아이콘 효과
var icon = document.querySelectorAll('.menu-icon-inside')

for(let j = 0; j < icon.length; j++){
    icon[j].addEventListener('mouseover', function(){
        icon[j].setAttribute("style","background: linear-gradient(200deg, #ffffff, #dddddd);")
    })
    
    icon[j].addEventListener('mouseout', function(){
        icon[j].removeAttribute("style");
    })
}


//도네이션 효과
var donation = document.querySelectorAll('.donation-content-01');
for(let a = 0; a < donation.length; a++){
	
	donation[a].addEventListener('mouseover', function(){
		 donation[a].setAttribute("style","background-color: rgba(255, 255, 255, 0.7);")
	});
	
	donation[a].addEventListener('mouseout', function(){
		 donation[a].removeAttribute("style");
	});
}


// 스크롤
const main = document.querySelector('.first');
const mainHeight = main.getBoundingClientRect().height;


// Show "arrow up" button when scrolling down
const arrowUp = document.querySelector(".arrow-up");

document.addEventListener('scroll', () => {
  if (window.scrollY > mainHeight/ 2) {
    arrowUp.classList.add("visible");
    // arrowUp의 클래스리스트에 "visible을 붙여줌"
  } else {
    arrowUp.classList.remove("visible");
    // classList에 visible을 지워준다
  }
});

// Handle click on the "arrow up" button
arrowUp.addEventListener("click", () => {
  scrollIntoView(".first");
});

function scrollIntoView(selector){
	const scrollTo = document.querySelector(selector);
	scrollTo.scrollIntoView({behavior:'smooth'});
}