var text = document.getElementsByClassName('text');
var shadow = '';
for (var i = 0; i < 20; i++){
    shadow +=(shadow? ',':'')+ -i*1+'px ' + i*1+'px 0 #d9d9d9' ;
}

for (var k = 0; k < 3; k ++){
	text[k].style.textShadow = shadow;
}

var icon = document.querySelectorAll('.menu-icon-inside')

for(let j = 0; j < icon.length; j++){
    icon[j].addEventListener('mouseover', function(){
        icon[j].setAttribute("style","background: linear-gradient(200deg, #ffffff, #dddddd);")
    })
    
    icon[j].addEventListener('mouseout', function(){
        icon[j].removeAttribute("style");
    })
}