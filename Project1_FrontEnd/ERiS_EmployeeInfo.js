let token = sessionStorage.getItem("token");
let _FirstName = document.getElementById("lbl_FirstName").value;
let _LastName = document.getElementById("lbl_LastName").value;
let _UserName = document.getElementById("lbl_UserName").value;
let _PassWord = document.getElementById("lbl_Password").value;
let _Email = document.getElementById("lbl_Email").value;


if(!token){
    window.location.href="Login.html";
} else
{
    let userName = sessionStorage.getItem("token");
    userName = userName.split(":");
    let apiURL = 'http://localhost:8080/Project1/User/' + userName[0];
    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", apiURL);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let FirstName = xhr.getResponseHeader("ifo_FirstName");
            let LastName = xhr.getResponseHeader("ifo_LastName");
            let UsersName = xhr.getResponseHeader("ifo_UserName");
            let PassWord = xhr.getResponseHeader("ifo_PassWord");
            let Email = xhr.getResponseHeader("ifo_Email");
            _FirstName = FirstName;
            _LastName = LastName;
            _UserName = UsersName;
            _PassWord = PassWord;
            _Email = Email;

            console.log(xhr.getAllResponseHeaders());
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    } 

    
}