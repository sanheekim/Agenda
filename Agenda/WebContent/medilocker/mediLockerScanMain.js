const key ='?' + encodeURIComponent('ServiceKey') + '='+'BfXgu8Nrg94kP5UMKtnT32kMX6AUp1kzvIOupvhUowIXqupdnwrP0XSpWIeXNo1zQ%2BYvNT7NAEWM%2BL5P5E3Shw%3D%3D';
$(function(){
})
function upLoad(){
    $("#popUp").fadeIn();
}

function closeBtn(){
	$(".selectFile").val('');
    $("#popUp").hide();
}

function detailApi(name){
	$("td").eq(0).text(name);
    $("#detailInfo").fadeIn();
    $.ajaxSettings.traditional = true; //js에서 java로 배열을 보낼 때 해줘야하는 설정
    let item_name = '&' + encodeURIComponent('item_name') + '='+ encodeURIComponent(name);
    $.ajax({
        type: "GET",
        url: "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList" + key + item_name,
        dataType: "xml",
        success: function (xml) {
            let obj = conv2json(xml);
            let item = obj.response.body.items;    
            let item_image = item.item.ITEM_IMAGE;
            console.log(item);

            $("#ITEM_IMAGE").attr("src",item_image);

            //else조건 해결해야함
            
        },
        error: function(){
            console.log("데이터없음");
        }
    });
    
    $.ajax({
        type: "GET",
        url: "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem" + key + item_name,
        dataType: "xml",
        success: function (xml) {
            let obj = conv2json(xml);
            console.log(obj);
          	let item1 = obj.response.body.items.item.EE_DOC_DATA.DOC.SECTION.ARTICLE.PARAGRAPH;
          	let item3 = obj.response.body.items.item.UD_DOC_DATA.DOC.SECTION.ARTICLE.PARAGRAPH;
            let effect = item1['#cdata'];
            let capacity = item3['#cdata'];
          	$("td").eq(2).text(effect);
          	$("td").eq(3).text(capacity);
        },
        error : function(){
        	console.log("상세정보없음");
        }
    });
}

function closeInfo(){

    $("#ITEM_IMAGE").attr("src","");
    $("td").eq(3).text("");
    $("td").eq(4).text("");
	$("#detailInfo").hide();
}
function test11(){
var ducks = ["첫째 오리", "둘째 오리", "셋째 오리", "넷째 오리", "다섯째 오리"]
var delaySec = 2000;
for (i in ducks) {
    (function(ii) {
        setTimeout(() => {
            console.log(ducks[ii])
        }, ii * delaySec)
    })(i);
}
}

function fileUpload(){
    let form = $('form')[0];
    let data = new FormData(form);
    let strArr;
    let scanStr;
    //var url = ${pageContext.request.contextPath}+"/MediLockerScanController";
        if($(".scanBtnC").val()){
            alert("스캔이 시작되었습니다");
            console.log("구글비전 스캔 시작 준비");
               $.ajax({
                type: "POST",
                url: "./MediLockerScanController",
                contentType : false,
                processData : false,
                data: data,
                success: function (scanString) {
                scanStr = scanString;
                strArr = scanString.split(',');
                console.log(typeof(strArr));
                //for(var i = 0; i < strArr.length; i ++){
                //    strArr[i] = strArr[i].replace('젠점','젠정');
                //    strArr[i] = strArr[i].replace('점안역','점안액');
                //    strArr[i] = strArr[i].replace('장안액','점안액');
                //    console.log(strArr[i]);
                //}
                if(strArr == null){
                    alert("추출된 문자열이 없습니다");
                    location.reload();
                    }
                    else{
                    
                        for(let i in strArr){
                            (function(ii) {
                                setTimeout(() => {
                                    $.ajaxSettings.traditional = true; //js에서 java로 배열을 보낼 때 해줘야하는 설정
                                    let item_name = '&' + encodeURIComponent('item_name') + '='+ encodeURIComponent(strArr[i]);
                                    let item_str = strArr[i];
                                    let jsonData;
                                    console.log(strArr);
                                    console.log("item_name : " + item_name);
                                    console.log(key);
                                    //let : 지역변수 , const : 상수
        
                                    $.ajax({
                                        type: "GET",
                                        url: "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem" + key + item_name,
                                        dataType: "xml",
                                        success: function (data) {
                                            console.log(data);
                                            var obj = conv2json(data);
                                             console.log(obj);
                                            // console.log(typeof(obj));
                                            var strObj = JSON.stringify(obj);
                                            // console.log(strObj);
                                            // console.log(typeof(strObj));
                                            jsonData = strObj;
        
                                            $.ajax({
                                                type: "POST",
                                                url: "MediLockerRegistController",
                                                data : {strObj : jsonData,
                                                        command : "scanRegist",
                                                        strArr : scanStr,
                                                        str : item_str},
                                                success: function () {
                                                    // alert("통신성공!");
                                                    
                                                },
                                                error : function(){
                                                    console.log("통신실패 2");
                                                }
                                            });
                                        },
                                        error : function(){
                                            console.log("통신 실패 1");
                                        }
                                    });     
                                }, ii * 1000);
                            })(i);
                           ;
                        }
                    }
                },
                error : function(){
                    alert("통신실패");
                },
            })
            .done(function(){
                setTimeout(() => {
                	$("#popUp2").hide();
                    alert("스캔이 완료되었습니다!");
                    location.reload();
                }, 5000);
            });


    }else{
    	alert("파일을 선택해 주세요");
        console.log("파일없음");
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


function deletePres(pres_no){
    var del = confirm("삭제하시겠습니까?");
    if(del){
    $.ajax({
        type: "POST",
        url: "./MediLockerRegistController",
        data: {command : "delete", pres_no : pres_no},
        success: function (int) {
            console.log(int);
            var res = int;
        },
    })
    .done(function(res){
        if(res > 0){
            alert("삭제 성공!");
            location.reload();    
        }else{
            alert("삭제 실패");
            location.reload();
        }
    })
    }
}


