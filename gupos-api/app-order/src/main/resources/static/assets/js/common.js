// JSON 응답 메시지 공통
function commonErrorMessage(result) {
    var obj = result.responseJSON;
    if(typeof obj == "undefined" || obj == null || obj == "") {
        // alert("시스템 오류가 발생했습니다. 관리자에게 문의해주세요.");
        Swal.fire("시스템 오류가 발생했습니다.\n관리자에게 문의해주세요.")
            .then((result) => {
                if(result.isConfirmed) {
                    return false;
                }
            })
    } else {
        // alert(obj.message);
        Swal.fire({
            title: obj.message,
            text: obj.detail,
            icon: "error"
        }).then((result) => {
                if(result.isConfirmed) {
                    return false;
                }
            })
    }
}

// JSON 응답 메시지 공통
function commonPopErrorMessage(result) {
    return new Promise((resolve) => {
        var obj = result.responseJSON;
        if(typeof obj == "undefined" || obj == null || obj == "") {
            // alert("시스템 오류가 발생했습니다. 관리자에게 문의해주세요.");
            Swal.fire("시스템 오류가 발생했습니다.\n관리자에게 문의해주세요.")
                .then((result) => {
                    if(result.isConfirmed) {
                        resolve({ isConfirmed: true });
                    }
                })
        } else {
            // alert(obj.message);
            Swal.fire({
                title: obj.message,
                text: obj.detail != undefined && obj != null && obj != "" ? obj.detail : "",
                icon: "error"
            }).then((result) => {
                if(result.isConfirmed) {
                    resolve({ isConfirmed: true });
                }
            })
        }
    });
}

function commonValidation(result) {
    var obj = result.responseJSON;
    if(typeof obj == "undefined" || obj == null || obj == "") {
        Swal.fire("오류가 발생했습니다.\n관리자에게 문의해주세요.")
            .then((result) => {
                if(result.isConfirmed) {
                    return false;
                }
            })
    } else {
        Swal.fire(obj.message)
            .then((result) => {
                if(result.isConfirmed) {
                    return false;
                }
            })

        $(".is-invalid").removeClass("is-invalid");

        if(obj.validationErrors) {
            var errors = obj.validationErrors;
            $(".error-message").remove();
            for(var i = 0; i < errors.length; i++) {
                var feedbackObj = $(".invalid-feedback."+errors[i].field);
                $(feedbackObj).text(errors[i].message)
                $("[name='"+errors[i].field+"']").addClass("is-invalid")
            }
        }
    }
}

function emptyValidation(form) {
    $(form).find("input").each(function(index, el) {
        debugger;
        var value = $(el).val();
        if(value === "") {
            var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
            var name = $(feedbackObj).attr("data-nm");

            $(feedbackObj).text(name + "을 입력해주세요.")
            $(el).addClass("is-invalid")
        }
    })
}

function inputLength(param, size) {
    var val = $(param).val();
    if (val.length > size) {
        $(param).val(val.slice(0, size));
    }
}

//숫자만 입력받도록 처리
function numOnlyCheck(obj){
    obj.value = obj.value.replace(/[^0-9]/gi, '').replace(/(\..*)\./g, '$1');
    return false;
}

//아이디 영문 숫자 처리
function idInputCheck(obj){
    obj.value = obj.value.replace(/[^a-zA-Z0-9]/g, '');
    return false;
}

//아이디 조합 체크
function chkId(id){
    if(id.length < 4){
        Swal.fire("아이디는 최소 4자리 이상 사용해야 합니다.");
        return false;
    }else if(!/^[A-Za-z0-9][A-Za-z0-9]*$/.test(id)){
        Swal.fire("아이디는 숫자와 영문자만 사용해야 합니다.");
        return false;
    }
    return true;
}

//패스워드 조합 체크
function chkPW(pw){
    var checkNumber = pw.search(/[0-9]/g);
    var checkEnglish = pw.search(/[a-z]/ig);

    if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(pw)){
        Swal.fire("비밀번호는 숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.");
        return false;
    }else if(checkNumber <0 || checkEnglish <0){
        Swal.fire("비밀번호는 숫자와 영문자를 혼용하여야 합니다.");
        return false;
    }else if(/(\w)\1\1\1/.test(pw)){
        Swal.fire("비밀번호는 같은 문자를 4번 이상 사용하실 수 없습니다.");
        return false;
    }
    return true;
}

// 사업자번호 유효성 체크
function checkCorporateRegiNumber(number){
    var numberMap = number.replace(/-/gi, '').split('').map(function (d){
        return parseInt(d, 10);
    });

    if(numberMap.length == 10){
        var keyArr = [1, 3, 7, 1, 3, 7, 1, 3, 5];
        var chk = 0;

        keyArr.forEach(function(d, i){
            chk += d * numberMap[i];
        });

        chk += parseInt((keyArr[8] * numberMap[8])/ 10, 10);
        console.log(chk);
        return Math.floor(numberMap[9]) === ( (10 - (chk % 10) ) % 10);
    }

    return false;
}

// 검색기간 설정.
function setCurrentDate(month){
    var dateObj = getDateRange(month);

    $("#startDate").val(dateObj.startDate);
    $("#endDate").val(dateObj.endDate);

    return false;
}

// 문자열 숫자만 반환
function stringToprice(str) {
    return result = str.replace(/[^0-9]/g, "");	// 원래 문자열에서 숫자가 아닌 모든 문자열을 빈 문자로 변경
}
