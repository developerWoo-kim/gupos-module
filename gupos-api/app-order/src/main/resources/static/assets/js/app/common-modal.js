function addContent(el, data) {
    $(el).children(".modal-dialog").empty();
    $(el).children(".modal-dialog").append(data);
}

$(document).on("click", ".close-modal", function () {
    $(this).parent().parent().parent().empty();
})