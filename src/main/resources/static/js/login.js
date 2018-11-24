function getJson() {
    var $items = $('#adminId,#adminPassword,#adminStatus');
    var obj = {};
    $items.each(function () {
        obj[this.id] = $(this).val();
    });
    var json = JSON.stringify(obj);
    return json;
}


function adminLogin() {
    var loginData = getJson();
    var adminContollerUrl = "http://localhost:3100/admins/login";
    $.ajax({
        type: "POST",
        url: adminContollerUrl,
        data: loginData,
        contentType: "application/json",
        dataType: "text",
        success: function (response) { 
            
            if(response=="Authenticated"){
                sessionStorage.setItem("auth","yes");
                window.location = "/dashboard.html";
            }

            if(response!="Authenticated"){
                
                $(".message").html(response);
            }
            
            console.log(response);


        },
            
        error: function (response) { 
            
           console.log(response);
    }

    });
}



function init() {
    if (sessionStorage.getItem("auth") === "yes") {
                window.location = 'dashboard.html';
    }
}