document.addEventListener('DOMContentLoaded', function (){
    var btnConfirm = document.getElementById("btn-confirm");

    var txtUserReceive = document.forms['transfer-form']['account-number']
    var txtAmount = document.forms['transfer-form']['amount']
    var txtMessage = document.forms['transfer-form']['message']

    btnConfirm.onclick = function (){
        var receiver = txtUserReceive.value;
        var amount = txtAmount.value;
        var messgage = txtMessage.value;


        var dataTransfer = {
            "receiverAccountNumber": receiver,
            "amount": amount,
            "message": messgage,
        }

        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function () {
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 201){
                var data = JSON.parse(xmlHttpRequest.responseText)
                console.log(data)
                alert("Transfer success!!!");
                var localBalance = localStorage.getItem("balance") - amount;
                localStorage.setItem("balance",localBalance);
                window.location.href = "/Front 2/transferhistory.html"
            }
        }

        xmlHttpRequest.open('post', "http://localhost:8080/api/v1/transactions/transfer?sender_id=" + localStorage.getItem("id"), false)
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.setRequestHeader('Accept', 'application/json')
        xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem("access_token"))
        xmlHttpRequest.send(JSON.stringify(dataTransfer))
    }
})