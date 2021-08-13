document.getElementById('txt_ar_ReimbursementDscr').onkeyup = function () {
    document.getElementById('lbl_charRemain').innerHTML = "Characters left: " + (250 - this.value.length);
  };
let numReim = document.getElementById('ifo_NumReimbursements');
  /*let userName = sessionStorage.getItem("token");
  userName = userName.split(":");*/
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