document.getElementById('btn_Return').addEventListener("click", ReturnToMainMenu);
document.getElementById('btn_AccountInfo').addEventListener("click", GoToAccountInfo);
document.getElementById('btn_ViewAll').addEventListener("click", ViewAllEmployees);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);

let token = sessionStorage.getItem("token");
let RoleCheck = token.split(":")[1];
if(!token){
    window.location.href="Login.html";
} else
{
    GetAllReimbursementsForUser()
}

function GetAllReimbursementsForUser()
{
    let lookup = localStorage.getItem("Reimb_Id_LU");
    localStorage.removeItem("Reimb_Id_LU");
    let apiURL = 'http://localhost:8080/Project1/Reimbursement/All-Details';
    let xhr = new XMLHttpRequest();
    xhr.open("GET", apiURL);
    xhr.setRequestHeader("ifo_ShortReimList", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("Authorization",lookup+":1");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let info = xhr.getResponseHeader("ifo_ShortReimList").split("|");
            console.log(info);
            let displayTable = document.getElementById("tbl_ReimbShortList");
            for (let i = 0; i < info.length; i++) {
                let tbl_r = document.createElement("tr");
                tbl_r.id = "row" + i;
                if(info[i]==="")
                    break;
                let shortDetails = info[i].split(",");
                let tbl_c_ID = document.createElement("td");
                let tbl_c_Name = document.createElement("td");
                let tbl_c_Status = document.createElement("td");
                let idValue = document.createTextNode(shortDetails[0].split(":")[1].trim());
                let nameValue = document.createTextNode(shortDetails[1].split(":")[1].trim());
                let statusValue = document.createTextNode(shortDetails[2].split(":")[1].trim());
                tbl_c_ID.appendChild(idValue);
                tbl_c_Name.appendChild(nameValue);
                tbl_c_Status.appendChild(statusValue);
                tbl_r.appendChild(tbl_c_ID);
                tbl_r.appendChild(tbl_c_Name);
                tbl_r.appendChild(tbl_c_Status);
                let tbl_c_Button = document.createElement("td");
                let btn = document.createElement("button");
                let btn_Text = document.createTextNode("More Details");
                btn.addEventListener("click", ViewReimbursementDetails);
                btn.id = idValue.textContent;
                btn.appendChild(btn_Text);
                tbl_c_Button.appendChild(btn);
                tbl_r.appendChild(tbl_c_Button);
                displayTable.appendChild(tbl_r);
                
            }
            $(".se-pre-con").fadeOut("slow");   
        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    }
}

function ViewReimbursementDetails()
{
    ///Temporarily store the ID in local storage. Remove it once the data has been loaded
    ///in the proper page.
    localStorage.setItem("Reimb_Id_LU", this.id);
    window.location.href="ERiS_ViewReimbursement.html";
}

function ReturnToMainMenu() {
    if(RoleCheck == 1)
    {
        window.location.href="ERiS_EmployeePage.html";
    } else if(RoleCheck == 2) {
        window.location.href="ERiS_ManagerPage.html";
    }
}

function LogOut() {
    sessionStorage.removeItem("token");
    window.location.href="Login.html";
}

function GoToAccountInfo(){
    window.location.href="ERiS_EmployeeInfo.html";
}

function ViewAllEmployees(){
    window.location.href="ERiS_ViewAllEmployees.html";
}