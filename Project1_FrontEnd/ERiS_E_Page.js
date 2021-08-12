
document.getElementById('btn_AcctInfo').addEventListener("click", GoToAccountInfo);
//document.getElementById('btn_NewRequest').addEventListener("click", login);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);

let token = sessionStorage.getItem("token");
if(!token){
    window.location.href="Login.html";
}

function LogOut(){
    sessionStorage.removeItem("token");
    window.location.href="Login.html";
}

function GoToAccountInfo(){
    window.location.href="ERiS_EmployeeInfo.html";
}