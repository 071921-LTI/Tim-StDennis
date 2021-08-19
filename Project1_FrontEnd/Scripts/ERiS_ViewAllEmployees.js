document.getElementById('btn_AcctInfo').addEventListener("click", GoToAccountInfo);
document.getElementById('btn_ReturnToMainMenu').addEventListener("click", ReturnToMainMenu);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);

let token = sessionStorage.getItem("token");
if(!token){
    window.location.href="Login.html";
} else
{
    ViewAllEmployees();
}

function ViewAllEmployees()
{
    let userName = sessionStorage.getItem("token");
    userName = userName.split(":");
    let apiURL = 'http://localhost:8080/Project1/User/All';
    let xhr = new XMLHttpRequest();
    xhr.open("GET", apiURL);
    xhr.setRequestHeader("ifo_EmployeeList", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let info = xhr.getResponseHeader("ifo_EmployeeList").split("|");
            console.log(info);
            for (let i = 0; i < info.length; i++) {
                if(info[i] === "")
                    break;
                let shortDetails = info[i].split(",");
                AddToList(shortDetails);
            }
            $(".se-pre-con").fadeOut("slow");   
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
}

function AddToList(details)
{
    let user_ID = details[0].split(":");
    let User = document.createElement("li"); 
    let user_btn = document.createElement("button");
    let btn_Text = document.createTextNode("View Reimbursements");
    user_btn.id = user_ID[1].trim();
    user_btn.addEventListener("click", DisplayUserReimb);
    user_btn.appendChild(btn_Text);
    let user_ID_node = document.createTextNode(user_ID[0] + user_ID[1])
    let user_Name = document.createTextNode(details[1]);
    let user_ActualName = document.createTextNode(details[2]);
    let br = document.createElement("br");
    let br2 = document.createElement("br");
    let br3 = document.createElement("br");
    User.appendChild(user_ID_node);
    User.appendChild(br);
    User.appendChild(user_ActualName);
    User.appendChild(br2);
    User.appendChild(user_Name);
    User.appendChild(br3);
    User.appendChild(user_btn);
    let ListToAdd = document.getElementById('ul_AllEmployees');
    ListToAdd.appendChild(User);
}

function DisplayUserReimb()
{
      ///Temporarily store the ID in local storage. Remove it once the data has been loaded
    ///in the proper page.
    localStorage.setItem("Reimb_Id_LU", this.id);
    window.location.href="ERiS_ViewReimbursementsByUser.html";
}

function ReturnToMainMenu() {
    window.location.href="ERiS_ManagerPage.html";
}

function LogOut() {
    sessionStorage.removeItem("token");
    window.location.href="Login.html";
}

function GoToAccountInfo(){
    window.location.href="ERiS_EmployeeInfo.html";
}