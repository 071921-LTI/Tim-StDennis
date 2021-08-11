document.getElementById('logIn').addEventListener("click", login);

let apiURL = 'http://localhost:8080/Project1/authorize';

function login(){

    let username = document.getElementById("userName").value;
    let password = document.getElementById("passWord").value;

    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", apiURL);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let authToken = xhr.getResponseHeader("Authorization");
            
            sessionStorage.setItem("token", authToken);

            window.location.href="ERiS_EmployeePage.html";
            console.log("Log in sucessful");
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}