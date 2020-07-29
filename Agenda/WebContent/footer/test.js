/** @format */

let mouseCousor = document.getElementById("cursor");

document.addEventListener("mousemove", function (e) {
  cursor.style.left = e.pageX + "px";
  cursor.style.top = e.pageY + "px";
});

const text = document.querySelector(".main__text");
const strText = text.textContent;
console.log(strText);
const splitText = strText.split("");
text.textContent = "";
// console.log(splitText);

for (let i = 0; i < splitText.length; i++) {
  text.innerHTML += "<span>" + splitText[i] + "</span>";
}

let char = 0;
let timer = setInterval(onTick, 50);

function onTick() {
  const span = text.querySelectorAll("span")[char];
  span.classList.add("fade");
  char++;
  if (char === splitText.length) {
    complete();
    return;
  }
}

function complete() {
  clearInterval(timer);
  timer = null;
}

/** @format */

function splitScroll() {
  const controller = new ScrollMagic.Controller();

  new ScrollMagic.Scene({
    duration: "200%",
    triggerElement: ".main__about__title",
    triggerHook: 0,
  })
    .setPin(".main__about__title")
    .addIndicators()
    .addTo(controller);
}

splitScroll();
