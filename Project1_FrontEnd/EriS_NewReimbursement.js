let token = sessionStorage.getItem("token");
if(!token){
  window.location.href="Login.html";
}

document.getElementById('txt_ar_ReimbursementDscr').onkeyup = function () {
    document.getElementById('lbl_charRemain').innerHTML = "Characters left: " + (250 - this.value.length);
  };

  document.getElementById('btn_SubmitReim').addEventListener("click", SubmitNewReim);
  document.getElementById('btn_Return').addEventListener("click", ReturnToMainMenu);
  document.getElementById('btn_AccountInfo').addEventListener("click", GoToAccountInfo);
  document.getElementById('btn_LogOut').addEventListener("click", LogOut);

let numReim = document.getElementById('ifo_NumReimbursements');
  let userName = sessionStorage.getItem("token");
  userName = userName.split(":");
  let apiURL = 'http://localhost:8080/Project1/Reimbursement/All';
  let xhr = new XMLHttpRequest();
  xhr.open("GET", apiURL);
  xhr.setRequestHeader("ifo_ReimRecords", "application/x-www-form-urlencoded");
  xhr.send();
  console.log("sent");
  xhr.onreadystatechange = function(){
      if(xhr.readyState === 4 && xhr.status === 200){
          
            let info = xhr.getResponseHeader("ifo_ReimRecords").split(":");
            console.log(info);
            numReim.innerText = info[0]; 
      } else if (xhr.readyState === 4){
          console.log('Something went wrong...');
      }
  }

  function SubmitNewReim(){
    let userName = sessionStorage.getItem("token");
    userName = userName.split(":");
    let apiURL = 'http://localhost:8080/Project1/Reimbursement';
    let xhr = new XMLHttpRequest();
    let Reim_ID = document.getElementById('ifo_NumReimbursements').innerText;
    let Reim_Name = document.getElementById('txt_ReceiptName').value;
    let Reim_Type = document.getElementById('sel_Type').value;
    let Reim_Price = document.getElementById('num_Amount').value;
    let Reim_Descr = document.getElementById('txt_ar_ReimbursementDscr').value;
    xhr.open("POST",apiURL);
    xhr.onreadystatechange = function() {
      if(xhr.readyState === 4 && xhr.status === 200)
      {
        window.alert("Reimbursement Successfully Requested");
        window.location.href="ERiS_EmployeePage.html";
      }
    }
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `ReimID=${Reim_ID}&ReimName=${Reim_Name}&ReimType=${Reim_Type}&ReimPrice=${Reim_Price}
            &ReimDescr=${Reim_Descr}&ReimSubmitter=${userName[0]}`;
    xhr.send(requestBody);
  }

  function ReturnToMainMenu() {
    window.location.href="ERiS_EmployeePage.html";
  }

  function LogOut(){
    sessionStorage.removeItem("token");
    window.location.href="Login.html";
}

function GoToAccountInfo(){
  window.location.href="ERiS_EmployeeInfo.html";
}
