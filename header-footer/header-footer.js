
$(document).ready(function () {
    $.ajax({
        url: "/LunchBoxDonation/header-footer/header-footer.html",
        dataType: "html",
        success: function (result) {
            let htmlContent = $(result);
            let header = htmlContent.filter("#header").html();
            let footer = htmlContent.filter("#footer").html();

            $("#header").html(header);
            $("#footer").html(footer);
        }
    });
})
