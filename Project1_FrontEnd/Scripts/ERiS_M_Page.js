document.getElementById('btn_AcctInfo').addEventListener("click", GoToAccountInfo);
document.getElementById('btn_ViewAllEmployees').addEventListener("click", ViewAllEmployees);
document.getElementById('btn_LogOut').addEventListener("click", LogOut);

let token = sessionStorage.getItem("token");
let RoleCheck = token.split(":")[1];
if(!token){
    window.location.href="Login.html";
} else
{
    GetAllReimbursements()
}

function GetAllReimbursements()
{
  let userName = sessionStorage.getItem("token");
  userName = userName.split(":");
  let apiURL = 'http://localhost:8080/Project1/Reimbursement/All-Details';
  let xhr = new XMLHttpRequest();
  xhr.open("GET", apiURL);
  xhr.setRequestHeader("ifo_ShortReimList", "application/x-www-form-urlencoded");
  xhr.setRequestHeader("Authorization",token);
  xhr.send();
  xhr.onreadystatechange = function(){
      if(xhr.readyState === 4 && xhr.status === 200){
        let info = xhr.getResponseHeader("ifo_ShortReimList").split("|");
        console.log(info);
        for (let i = 0; i < info.length; i++) {
            if(info[i] === "")
                break;
            let shortDetails = info[i].split(",");
            console.log(shortDetails);
            let status = shortDetails[3].split(":")[1].trim();
            AddToList(status,shortDetails);
        }
        $(".se-pre-con").fadeOut("slow");   
      } else if (xhr.readyState === 4){
          console.log('Something went wrong...');
      }
  }
}

function AddToList(status, details)
{
    let Reimb = document.createElement("li"); 
    let Reimb_btn = document.createElement("button");
    let btn_Text = document.createTextNode("More Details");
    Reimb_btn.id = details[0].split(":")[1].trim();
    Reimb_btn.addEventListener("click", DisplayReimbDetails);
    Reimb_btn.appendChild(btn_Text);
    let Reimb_Name = document.createTextNode(details[1]);
    let Reimb_Type = document.createTextNode(details[2]);
    let Reimb_ID = document.createTextNode(details[0]);
    let Reimb_Author = document.createTextNode(details[4]);
    let Reimb_Status = document.createTextNode("Status: " + status);
    ///There has to be a better way to do this...
    let br = document.createElement("br");
    let br2 = document.createElement("br");
    let br3 = document.createElement("br");
    let br4 = document.createElement("br");
    let br5 = document.createElement("br");
    Reimb.appendChild(Reimb_ID);
    Reimb.appendChild(br);
    Reimb.appendChild(Reimb_Name);
    Reimb.appendChild(br2);
    Reimb.appendChild(Reimb_Type);
    Reimb.appendChild(br3);
    Reimb.appendChild(Reimb_Author);
    Reimb.appendChild(br4);
    Reimb.appendChild(Reimb_Status);
    Reimb.appendChild(br5);
    Reimb.appendChild(Reimb_btn);
    if(status === "PENDING") {
        console.log("Pending Reimbursement");
        let ListToAdd = document.getElementById('ul_PendingReimbursements');
        ListToAdd.appendChild(Reimb);
    }else if(status === "ACCEPTED" || status === "REJECTED") {
        console.log("Resolved Reimbursement");
        let ListToAdd = document.getElementById('ul_ResolvedReimbursements');
        ListToAdd.appendChild(Reimb);
    }
    
}

function DisplayReimbDetails()
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