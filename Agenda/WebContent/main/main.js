//gsap

const firstBg = document.querySelector('#first-bg');
const firstBgAddition = document.querySelector('#first-bg-addition');
const firstWrapper = document.querySelector('.first-wrapper');
const headerMain = document.querySelector('#header');

const firstTL = gsap.timeline()
firstTL.from([firstBg, firstBgAddition], {
	duration : 1,
	width : 0,
	skewX : 4,
	ease : 'power3.inOut',
	stagger : {
		amount : .2
	}
}).from(firstWrapper, {
	opacity : 0,
	y: 80,
	duration : .6, 
	ease : 'power3.inOut',
	stagger : {
		amount : 0.2
	}
})

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


// 마우스커서
let cousor = document.getElementById("cursor");
let cousorArea = document.getElementsByClassName("area");

for (var l = 0; l < 4; l++) {
	cousorArea[l].addEventListener("mousemove", function(e) {
		cursor.style.visibility = "hidden";
	});
} 

cousorArea[4].addEventListener("mousemove", function(e) {
	showCursor(e);
	cursor.style.zIndex="0";
});

for (var p = 5; p < cousorArea.length; p++){
	cousorArea[p].addEventListener("mousemove", function(e) {
		showCursor(e);
		cursor.style.zIndex="9999";
	});
}

function showCursor(e){
	cursor.style.visibility = "visible";
	cursor.style.left = e.pageX + "px";
	cursor.style.top = e.pageY + "px";
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
	
//메인 텍스트2
	const AgendaText = document.querySelector(".agendaText");
	const agendaText = AgendaText.textContent;
	const splitText = agendaText.split("");
	
	AgendaText.textContent ="";
	for(let b=0; b<splitText.length; b++){
		AgendaText.innerHTML += "<span>"+splitText[b]+"</span>";
	}
	
	let char = 0;
	let timer = setInterval(onTick,50);
	
	function onTick(){
		const span = AgendaText.querySelectorAll("span")[char];
		span.classList.add("fade");
		char++
		if(char == splitText.length){
			complete();
			return;
		}
	}
	
	function complete(){
		clearInterval(timer);
		timer = null;
	}
	
	
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


//메뉴 효과
var menuWrapper = document.querySelectorAll('.second-menu');
for(let p = 0; p < menuWrapper.length; p++){
	menuWrapper[p].addEventListener('mouseover',function(){
		menuWrapper[p].setAttribute("style","background-color: rgba(255, 255, 255, 0.7);")
	});
	menuWrapper[p].addEventListener('mouseout', function(){
		menuWrapper[p].removeAttribute("style");
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