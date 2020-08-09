// 마우스커서
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
		cursor.style.zIndex = "0";
	});
}


// 메인텍스트
var TxtRotate = function (el, toRotate, period) {
	  this.toRotate = toRotate;
	  this.el = el;
	  this.loopNum = 0;
	  this.period = parseInt(period, 10) || 2000;
	  this.txt = "";
	  this.tick();
	  this.isDeleting = false;
	};

	TxtRotate.prototype.tick = function () {
	  var i = this.loopNum % this.toRotate.length;
	  var fullTxt = this.toRotate[i];

	  if (this.isDeleting) {
	    this.txt = fullTxt.substring(0, this.txt.length - 1);
	  } else {
	    this.txt = fullTxt.substring(0, this.txt.length + 1);
	  }

	  this.el.innerHTML = '<span class="wrap">' + this.txt + "</span>";

	  var that = this;
	  var delta = 300 - Math.random() * 100;

	  if (this.isDeleting) {
	    delta /= 2;
	  }

	  if (!this.isDeleting && this.txt === fullTxt) {
	    delta = this.period;
	    this.isDeleting = true;
	  } else if (this.isDeleting && this.txt === "") {
	    this.isDeleting = false;
	    this.loopNum++;
	    delta = 500;
	  }

	  setTimeout(function () {
	    that.tick();
	  }, delta);
	};

	window.onload = function () {
	  var elements = document.getElementsByClassName("txt-rotate");
	  for (var i = 0; i < elements.length; i++) {
	    var toRotate = elements[i].getAttribute("data-rotate");
	    var period = elements[i].getAttribute("data-period");
	    if (toRotate) {
	      new TxtRotate(elements[i], JSON.parse(toRotate), period);
	    }
	  }
	  // INJECT CSS
	  var css = document.createElement("style");
	  css.type = "text/css";
	  css.innerHTML = ".txt-rotate > .wrap { border-right: 0.08em solid #666 }";
	  document.body.appendChild(css);
	};
	

//스플릿스크롤
function splitScroll() {
	const controller = new ScrollMagic.Controller();

	new ScrollMagic.Scene({
		duration : "200%",
		triggerElement : ".fourth-title",
		triggerHook : 0,
	}).setPin(".fourth-title").addTo(controller);
}

splitScroll();


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


// 탑스크롤
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


/* 내려올때 header 컬러 변경 */
const header = document.querySelector("#header");
const headerHeight = header.getBoundingClientRect().height;

document.addEventListener("scroll", () => {

/*  console.log(window.scrollY);
  console.log(`headerHeight: ${headerHeight}`);*/

  if (window.scrollY > headerHeight) {
    header.classList.add("header--dark");
  } else {
    header.classList.remove("header--dark");
  }
});


//----------------------------------------------------------------

////메뉴 아이콘 효과
//var icon = document.querySelectorAll('.menu-icon-inside')
//
//for(let j = 0; j < icon.length; j++){
//    icon[j].addEventListener('mouseover', function(){
//        icon[j].setAttribute("style","background: linear-gradient(200deg, #ffffff, #dddddd);")
//    })
//    
//    icon[j].addEventListener('mouseout', function(){
//        icon[j].removeAttribute("style");
//    })
//}
//
////3D텍스트
//var text = document.getElementsByClassName('text');
//var shadow = '';
//for (var i = 0; i < 20; i++){
//    shadow +=(shadow? ',':'')+ -i*1+'px ' + i*1+'px 0 #d9d9d9' ;
//}
//for (var k = 0; k < 3; k ++){
//	text[k].style.textShadow = shadow;
//}
//
