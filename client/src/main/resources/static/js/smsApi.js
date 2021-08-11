var count = 0; /* 문자 중복을 막기 위한 인증번호 */

$(document).ready(function() {

    $("#send").click(function() {

        var number = Math.floor(Math.random() * 100) + 1000;
        if(number>10000){
            number = number - 1000;
        }

        $("#text").val(number);      /* 난수로 생성된 인증번호를 hidden name : text 에 숨긴다 */

        var to = $("#to").val();

        if(to == "" || to == null){
            alert("빈칸이나 공백을 채워주세요");
        }

        else {
            var con_test = confirm("해당번호로 인증문자를 발송하시겠습니까?");   /* 문자를 보낼껀지 물어본다 */

            if(con_test == true){

                if(count < 3){      /* 추후 데이터베이스에 컬럼 값을 확인하여 count 값을 비교 할 예정 */

                    $.ajax({
                        url:"/user/sendSms",
                        type:"post",
                        data:{
                            to: $("#to").val(),
                            text: $("#text").val()
                        },
                        success:function(){
                            alert("해당 휴대폰으로 인증번호를 발송했습니다");
                            count++;
                        },
                        error(){

                        }

                    });
                } else {
                    alert("휴대폰 인증 그만하세요")
                }

            }
            else if(con_test == false){

            }
        }
    });

    $("#register").click(function (){
        var userNum = $("#userNum").val();
        console.log(userNum)
        if(userNum == null || userNum == ""){
            alert("빈칸 및 SMS인증을 해주세요.");
        }
    })

    $("#enterBtn").click(function() {   /* 내가 작성한 번호와 인증번호를 비교한다 */

        var userNum = $("#userNum").val();

        var sysNum = $("#text").val();

        if(userNum == null || userNum == ""){
            alert("휴대폰으로 발송된 인증번호를 입력해주세요");
            if(to == "" || to == null){
                alert("빈칸이나 공백을 채워주세요");
            }
        }
        else{
            if(userNum.trim() == sysNum.trim()){
                alert("성공");
            }

            else {
                alert("실패");
            }
        }
    });
});