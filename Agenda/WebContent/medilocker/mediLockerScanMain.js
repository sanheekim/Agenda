$(function(){

})
function upLoad(){
   $("#popUp").show();
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
        if($(".selectFile").val()){
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
                    }
                    else{
                        var key = '?' + encodeURIComponent('ServiceKey') + '='+'BfXgu8Nrg94kP5UMKtnT32kMX6AUp1kzvIOupvhUowIXqupdnwrP0XSpWIeXNo1zQ%2BYvNT7NAEWM%2BL5P5E3Shw%3D%3D';
                        for(let i in strArr){
                            (function(ii) {
                                setTimeout(() => {
                                    $.ajaxSettings.traditional = true; //js에서 java로 배열을 보낼 때 해줘야하는 설정
                                    let item_name = '&' + encodeURIComponent('item_name') + '='+ encodeURIComponent(strArr[i]);
                                    let item_str = strArr[i];
                                    let jsonData;
                                    console.log(strArr[i]);
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
                        }
                    }
                },
                error : function(){
                    alert("통신실패");
                },
            })
            .done(function(){

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

function loop(x){
    setTimeout(function(){
        console.log(x);
    }, 1000*(x+1));
}


