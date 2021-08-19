document.getElementById('btn_Return').addEventListener("click", ReturnToMainMenu);
document.getElementById('btn_ViewAllEmployees').addEventListener("click", ViewAllEmployees);
document.getElementById('btn_AccountInfo').addEventListener("click", GoToAccountInfo);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);
document.getElementById('btn_AcceptReimbursement').addEventListener("click",AcceptReimbursement);
document.getElementById('btn_RejectReimbursement').addEventListener("click",RejectReimbursement);

let token = sessionStorage.getItem("token");
let RoleCheck = token.split(":")[1];
if(!token){
    window.location.href="Login.html";
} else
{
    GetDetailedReimbursement();
}

function GetDetailedReimbursement(){
    let lookup = localStorage.getItem("Reimb_Id_LU");
    localStorage.removeItem("Reimb_Id_LU");
    let apiURL = 'http://localhost:8080/Project1/Reimbursement/Details/' + lookup;
    let xhr = new XMLHttpRequest();
    xhr.open("GET", apiURL);
    xhr.setRequestHeader("ifo_DetailedReimList", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let info = xhr.getResponseHeader("ifo_DetailedReimList").split("|");
            let displayTable = document.getElementById("tbl_ReimbDetails");
            console.log(info);
            for (let i = 0; i < 10; i++) {
                let KeyVal = info[i].split(":");
                console.log(KeyVal);
                let tableRow = document.createElement("tr");
                let tableColKey = document.createElement("td");
                let tableColValue = document.createElement("td");
                tableColValue.id = "lbl_Row" + i;
                if(KeyVal.length > 2)
                {
                    //One of the time stamps...Need to parse it together.
                    if(KeyVal[2])
                        KeyVal[1] += ":" + KeyVal[2];
                    if(KeyVal[3])
                        KeyVal[1] += ":" + KeyVal[3];
                }
                let infoKey = document.createTextNode(KeyVal[0].trim());
                let infoValue = document.createTextNode(KeyVal[1].trim());
                tableColKey.appendChild(infoKey);
                tableColValue.appendChild(infoValue);
                tableRow.appendChild(tableColKey);
                tableRow.appendChild(tableColValue);
                displayTable.appendChild(tableRow);
            }
            $(".se-pre-con").fadeOut("slow");
            CheckRole();  
      } else if (xhr.readyState === 4){
          console.log('Something went wrong...');
      }
  }
}

function AcceptReimbursement() {
    ModifyReimbursement("ACCEPTED");
}

function RejectReimbursement() 
{
    ModifyReimbursement("REJECTED");
}

function ModifyReimbursement(NewStatus)
{
    console.log(NewStatus);
    let apiURL = 'http://localhost:8080/Project1/Reimbursement/Update';
    let xhr = new XMLHttpRequest();
    xhr.open("POST", apiURL);
    let Reim_ID = document.getElementById("lbl_Row0").innerText;
    let Reim_Status = NewStatus;
    let Reim_Resolver = token.split(":")[0];

    xhr.onreadystatechange = function() {
      if(xhr.readyState === 4 && xhr.status === 200)
      {
        window.alert("Reimbursement Successfully" + NewStatus);
        window.location.href="ERiS_ManagerPage.html";
      }
    }
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `ReimID=${Reim_ID}&ReimStatus=${Reim_Status}&ReimResolver=${Reim_Resolver}`;
    xhr.send(requestBody);
}

function CheckRole()
{
    if(RoleCheck==2)
    {
        document.getElementById("btn_ViewAllEmployees").style.display = "inline";
        if(document.getElementById("lbl_Row3").innerText == "PENDING")
        {
            //This is a Manager!
            document.getElementById("btn_AcceptReimbursement").style.display = "inline";
            document.getElementById("btn_RejectReimbursement").style.display = "inline";
        }
    }
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

function SubmitNewRequest(){
    window.location.href="ERiS_NewReimbursement.html";
}

function ViewAllEmployees(){
    window.location.href="ERiS_ViewAllEmployees.html";
}