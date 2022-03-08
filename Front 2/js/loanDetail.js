document.addEventListener('DOMContentLoaded', function () {
    var tableBody = document.getElementById('my-table-data');

    var xmlHttpRequest = new XMLHttpRequest();
    console.log(localStorage.getItem("id"));
    xmlHttpRequest.onreadystatechange = function (){
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            var newContent = ''; //
            console.log(data)
            for (let i = 0; i < data.data.length; i++) {
                newContent += `<tr>
                    <th scope="row">${data.data[i].loan_id}</th>
                    <td>${localStorage.getItem("name")}</td>
                    <td>${localStorage.getItem("account_number")}</td>
                    <td>${data.data[i].amount}</td>
                    <td>${data.data[i].status}</td>
                    <td>${data.data[i].approvedDate}</td>
                </tr>`;
            }
            tableBody.innerHTML = newContent;
        }
    }
    xmlHttpRequest.open('get', 'http://localhost:8080/api/v1/loans/list?user_id=' + localStorage.getItem("id"), false);
    xmlHttpRequest.setRequestHeader('Accept', 'application/json')
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem("access_token"))
    xmlHttpRequest.send();

    var btn = document.getElementById("submit-loan-btn");

    var txtAmount = document.forms['loan-form']['amount']
    var txtTenure = document.forms['loan-form']['tenure']

    btn.onclick = function (){

        var amount = txtAmount.value;
        var tenure = txtTenure.value;

        var dataSend = {
            "amount": amount,
            "tenure": tenure
        }

        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function (){
            if (xmlHttpRequest.readyState == 4){
                var data = JSON.parse(xmlHttpRequest.responseText);
                alert(data.message)
                window.location.reload()
            }
        }
        xmlHttpRequest.open('post', 'http://localhost:8080/api/v1/loans/create?user_id=' + localStorage.getItem("id"), false);
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.setRequestHeader('Accept', 'application/json')
        xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem("access_token"))
        xmlHttpRequest.send(JSON.stringify(dataSend));
    }
})