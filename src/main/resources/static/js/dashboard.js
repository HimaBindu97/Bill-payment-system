function logout(){
    sessionStorage.clear();
    window.location="index.html";
}

function init() {
    if (sessionStorage.getItem("auth") != "yes") {
    	 alert("please login first.");
                window.location = 'index.html';
    }
    
}