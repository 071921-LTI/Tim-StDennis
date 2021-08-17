let token = sessionStorage.getItem("token");
let _FirstName = document.getElementById("lbl_FirstName");
let _LastName = document.getElementById("lbl_LastName");
let _UserName = document.getElementById("lbl_UserName");
let _PassWord = document.getElementById("lbl_Password");
let _Email = document.getElementById("lbl_Email");
let _upd_FirstName = document.getElementById("upd_FirstName");
let _upd_LastName = document.getElementById("upd_LastName");
let _upd_UserName = document.getElementById("upd_UserName");
let _upd_PassWord = document.getElementById("upd_Password");
let _upd_Email = document.getElementById("upd_Email");
document.getElementById('btn_Return').addEventListener("click", ReturnToMainMenu);
document.getElementById('btn_UpdateInformation').addEventListener("click", StartUpdating);
document.getElementById('btn_SendUpdate').addEventListener("click",SendUpdate);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);

if(!token){
    window.location.href="Login.html";
} else
{
    GetUserInfo();
}

function GetUserInfo()
{
    let userName = sessionStorage.getItem("token");
    userName = userName.split(":");
    let apiURL = 'http://localhost:8080/Project1/User/' + userName[0];
    let xhr = new XMLHttpRequest();
    console.log(userName[1]);
    xhr.open("GET", apiURL);
    xhr.setRequestHeader("ifo_UserInfo", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let UserInfo = xhr.getResponseHeader("ifo_UserInfo");
            let parsed = UserInfo.split(',');
            _FirstName.value = parsed[0];
            _LastName.value = parsed[1];
            _UserName.value = parsed[2];
            _PassWord.value = parsed[3];
            _Email.value = parsed[4];
            console.log(UserInfo);
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
}

function ReturnToMainMenu(){
    window.location.href="ERiS_EmployeePage.html";
}

function StartUpdating(){
    var x = document.getElementById("UpdatePanel");
    var btn = document.getElementById('btn_UpdateInformation');
    btn.setAttribute("disabled", "true");
    x.style.display = "block";
    console.log("Clicked!");
}

function SendUpdate(){
    let userName = sessionStorage.getItem("token");
    userName = userName.split(":");
    let apiURL = 'http://localhost:8080/Project1/User/' + userName[0];
    let xhr = new XMLHttpRequest();
    let UpdateBody = "First Name:" + _upd_FirstName.value + "\nLast Name:"+ _upd_LastName.value + "\nUser Name:" 
                        + _upd_UserName.value + "\nPassword:" + _upd_PassWord.value + "\nEmail:" + 
                        _upd_Email.value + "\nRoleID:" + userName[1];
    console.log(UpdateBody);
    xhr.open("PUT", apiURL);
    xhr.setRequestHeader("ifo_UserInfo", "application/x-www-form-urlencoded");
    xhr.send(UpdateBody);
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
           GetUserInfo();
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
    var btn = document.getElementById('btn_UpdateInformation');
    btn.removeAttribute("disabled");
    var x = document.getElementById("UpdatePanel");
    x.style.display = "none";
    clearUpdValues();
}

function clearUpdValues(){
    _upd_FirstName.value = " ";
    _upd_Email.value = " ";
    _upd_LastName.value = " ";
    _upd_PassWord.value = " ";
    _upd_UserName.value = " ";
}

function LogOut(){
    sessionStorage.removeItem("token");
    window.location.href="Login.html";
}