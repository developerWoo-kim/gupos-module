
function commonAlertConfirm(title, subTitle, callback){
    Swal.fire({
        title: title ?? '',
        text: subTitle ?? '',
        html: "",
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '예',
        cancelButtonText: '아니오'
    }).then((result) => {
        if (result.isConfirmed) {
            callback();
        }
    })
}

function commonAlert({title, subTitle, icon, callback}){
    Swal.fire({
        title: title ?? "",
        text: subTitle ?? "",
        icon: icon ?? "",
    }).then((result) => {
        if (result.isConfirmed && typeof callback === "function") {
            callback();
        }
    })
}

function commonConfirm({title, subTitle, icon, callback, cancelCallback}){
    Swal.fire({
        title: title ?? "",
        text: subTitle ?? "",
        icon: icon ?? "",
        showCancelButton: true,
        confirmButtonText: '예',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed && typeof callback === "function") {
            callback();
        } else {
            if(typeof cancelCallback === "function") {
                cancelCallback();
            }
        }
    })
}