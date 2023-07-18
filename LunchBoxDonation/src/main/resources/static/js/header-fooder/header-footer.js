
$(document).ready(function () {
    var urltmp = window.location.pathname;
    var url = urltmp.split("/");
    if(url[1] == "LunchBoxDonation"){
        url =  "/LunchBoxDonation/header-footer/header-footer.html"
    }else{
        url =  "/header-footer/header-footer.html"
    }

    $.ajax({
        url: url,
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
