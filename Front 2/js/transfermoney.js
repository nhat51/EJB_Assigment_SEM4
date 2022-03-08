document.addEventListener('DOMContentLoaded', function (){
    var btnConfirm = document.getElementById("btn-confirm");

    var txtAccountName = document.forms['transfer-form']['account-name']
    var txtBalance = document.forms['transfer-form']['balance']
    var txtAccountNumberReceiver = document.forms['transfer-form']['account-number-receiver']
    var txtAccountNameReceiver = document.forms['transfer-form']['account-name-receiver']
    var txtAmount= document.forms['transfer-form']['amount']
    var txtContent = document.forms['transfer-form']['content']

    btnConfirm.onclick = function (){
        var accountName = txtAccountName.value;
        var balance = txtBalance.value;
        var accountNumberReceiver = txtAccountNumberReceiver.value;
        var accountNameReceiver = txtAccountNameReceiver.value;
        var amount = txtAmount.value;
        var content = txtContent.value;

        var dataTransfer = {
            "accountName": accountName,
            "balance": balance,
            "accountNumberReceiver": accountNumberReceiver,
            "accountNameReceiver": accountNameReceiver,
            "amount": amount,
            "content": content
        }

        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function () {
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 201){
                alert("Transfer success!!!");
                window.location.href ="/Front%202/transferhistory.html";
            }
        }

        xmlHttpRequest.open('post', "http://localhost:8080/api/v1/accounts/transfer-money", false)
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.send(JSON.stringify(dataTransfer))
    }
})