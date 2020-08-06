

function upload(){
    //window.open("Upload.jsp","","width=300px, height=150px");
    var items = ["종근당아스피린정","록스펜정","그리밍캡슐"];
    console.log(items);
    console.log(typeof(items));
    console.log(items[0]);
    var ListDiv = document.getElementById("LockerListDiv");
    
    $.ajaxSettings.traditional = true; //js에서 java로 배열을 보낼 때 해줘야하는 설정
    $.ajax({
        type: "POST",
        url: "../MediLockerSearchController",
        data : {'item' : items},
        dataType: "text",
        success: function (predInfo) {
        var data = predInfo;
        //$.trim(data);
        data = data.replace(/\n/gi,"");
		data = data.replace(/\r/gi,"");
        var data1 = data.substring(0, data.length-2);
        console.log(data);
        var sub = '{' + data1 + '}';
        console.log(sub);
        var parsed = JSON.parse(sub);
        console.log(typeof(parsed));
        $.each(parsed, function (key,val) { 
            if(key != null){
                for(var i in val){
                    $("#LockerDetail").append("<div>"+val[i]+"</div>");
                }
            }
        });
        
        },
        error : function(){
            alert("통신실패");
        }
    });
    // for(var i = 0; i < item.length; i++){
    // var div = document.createElement("div");
    // div.textContent = item[i];
	// ListDiv.appendChild(div);
    // }
}
function docScan(){
    var items = ["종근당아스피린정","록스펜정","그리밍캡슐"];
 
    var key = '?' + encodeURIComponent('ServiceKey') + '='+'BfXgu8Nrg94kP5UMKtnT32kMX6AUp1kzvIOupvhUowIXqupdnwrP0XSpWIeXNo1zQ%2BYvNT7NAEWM%2BL5P5E3Shw%3D%3D';
    $.ajaxSettings.traditional = true; //js에서 java로 배열을 보낼 때 해줘야하는 설정
	for(var i in items){
    var item_name = '&' + encodeURIComponent('item_name') + '='+ encodeURIComponent(items[i]);
    $.ajax({
        type: "GET",
        url: "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem"+key+item_name,
        dataType: "xml",
        success: function (data) {
			var obj = conv2json(data);
            console.log(obj);
            console.log(typeof(obj));
            var strObj = JSON.stringify(obj);
            console.log(strObj);
            console.log(typeof(strObj));
            console.log(items[i]);
            $.ajax({
                type: "POST",
                url: "MediLockerRegistController",
                data : {strObj : strObj,
                		command : "scanRegist",
                        items : items,
                        str : items[i]},
                success: function () {
                    alert("통신성공!");
                },
                error : function(){
                    alert("통신실패");
                }
            });
        },
        error : function(){
            alert("통신 실패");
        }
    });
    }
}

function conv2json(dom){
   // var dom = parseXml(_xml);
    console.log(dom);
    console.log("xml to domXml");
    var jsonStr = mediLockerXml2json(dom);
    var jsonObj = eval( "(" + jsonStr + ")" ); //eval 문자열을 코드로 인식하게 하는 함수
    return jsonObj;
}

