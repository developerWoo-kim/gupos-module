(function() {
    'use strict'

    const forms = document.querySelectorAll('.needs-validation')

    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            var result = true;
            $(form).find("input").each(function(index, el) {

                var value = $(el).val();
                if(value === "") {
                    var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
                    var name = $(feedbackObj).attr("data-nm");

                    $(feedbackObj).text(name + "을 입력해 주세요.")
                    $(el).addClass("is-invalid")
                    result = false;
                } else {
                    $(el).removeClass("is-invalid")
                }
            })

            if(!result) {
                event.preventDefault()
                event.stopPropagation()
            }

        }, false)
    })

})();

function initializeForm(form) {
    $(form).find("input").each(function(index, el) {
        $(el).removeClass("is-invalid")
    })
    $(form).find("select").each(function(index, el) {
        $(el).removeClass("is-invalid")
    })
}

function basicValidation(form) {
    var result= true;
    $(form).find("input.need-valid").each(function(index, el) {
        var value = $(el).val();
        if(value === "") {
            var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
            var name = $(feedbackObj).attr("data-nm");

            $(feedbackObj).text(name + "을 입력해 주세요.")
            $(el).addClass("is-invalid")
            result = false;
        } else {
            $(el).removeClass("is-invalid")
        }
    })

    $(form).find("select.need-valid").each(function(index, el) {

        var value = $(el).val();
        if($(el).hasClass('choices__input')) {
            if(value === "") {
                var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
                var name = $(feedbackObj).attr("data-nm");

                $(feedbackObj).text(name + "을 선택해 주세요.")
                $(el).parent().parent().addClass("is-invalid")
                result = false;
            } else {
                $(el).parent().parent().removeClass("is-invalid")
            }
        } else {
            if(value === "") {
                var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
                var name = $(feedbackObj).attr("data-nm");

                $(feedbackObj).text(name + "을 선택해 주세요.")
                $(el).addClass("is-invalid")
                result = false;
            } else {
                $(el).removeClass("is-invalid")
            }
        }
    })

    $(form).find("textarea.need-valid").each(function(index, el) {

        var value = $(el).val();
        console.log(value);
        if(value === "") {
            var feedbackObj = $(".invalid-feedback."+$(el).attr("name"));
            var name = $(feedbackObj).attr("data-nm");

            $(feedbackObj).text(name + "을 입력해 주세요.")
            $(el).addClass("is-invalid")
            result = false;
        } else {
            $(el).removeClass("is-invalid")
        }
    })

    return result;
}

function idValidation(input) {
    const value = $(input).val();
    const message = "4~20자의 숫자와 영문자만 사용해 주세요.";

    if(checkSpace(value)){
        writeMessage(input, message);
        return false;
    }

    if(value.length < 4) {
        writeMessage(input, message);
        return false;
    }

    const isId = /^[a-zA-Z0-9]{4,20}$/

    if(!isId.test(value)) {
        writeMessage(input, message);
        return false;
    }

    return true;
}

/**
 * 비밀번호
 * @param input
 * @returns {boolean}
 */
function passwordValidation(input) {
    var value = $(input).val();
    var message = "10~20자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.";

    if(checkSpace(value)){
        writeMessage(input, message);
        return false;
    }

    if(value.length < 10) {
        writeMessage(input, message);
        return false;
    }

    let isPW = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()])[a-zA-Z\d!@#$%^&*()]{10,20}$/;

    if(!isPW.test(value)) {
        writeMessage(input, message);
        return false;
    }

    return true;
}

/**
 * 사업자번호
 * @param input
 * @returns {boolean}
 */
function corporateRegiNumberValidation(input){
    var value = $(input).val();
    var message = "유효한 사업자번호 인지 확인해 주세요.";

    var numberMap = value.replace(/-/gi, '').split('').map(function (d){
        return parseInt(d, 10);
    });

    if(numberMap.length === 10){
        var keyArr = [1, 3, 7, 1, 3, 7, 1, 3, 5];
        var chk = 0;

        keyArr.forEach(function(d, i){
            chk += d * numberMap[i];
        });

        chk += parseInt((keyArr[8] * numberMap[8])/ 10, 10);

        if(Math.floor(numberMap[9]) !== ( (10 - (chk % 10) ) % 10)) {
            writeMessage(input, message);
            return false;
        }
    } else {
        writeMessage(input, message);
        return false;
    }

    return true;
}

function validEmpty(input) {
    var value = $(input).val();
    if(value === "") {
        writeEmptyMessage(input);

        return false;
    }

    return true;
}

function writeMessage(input, message) {
    var name = $(input).attr("name");
    var feedbackObj = $(".invalid-feedback."+name);
    $(feedbackObj).text(message);
    $(input).addClass("is-invalid");
}

function writeEmptyMessage(input) {
    var name = $(input).attr("name");
    var feedbackObj = $(".invalid-feedback."+name);
    var dataName = $(feedbackObj).attr("data-nm");

    $(feedbackObj).text(dataName + "을 입력해 주세요.")
    $(input).addClass("is-invalid");
}

function checkSpace(str) {
    if (str.search(/\s/) !== -1) {
        return true;
    } else {
        return false;
    }
}