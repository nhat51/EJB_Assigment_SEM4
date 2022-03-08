document.addEventListener('DOMContentLoaded', function () {
    var tableBody = document.getElementById('transfer-history');

    var xmlHttpRequest = new XMLHttpRequest();
    console.log(localStorage.getItem("id"))
    xmlHttpRequest.onreadystatechange = function (){
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            var newContent = ''; //
            console.log(data)
            for (let i = 0; i < data.length; i++) {
                newContent += `<tr>
                    <th scope="row">${data[i].transactionId}</th>
                    <td>${data[i].senderName}</td>
                    <td>${data[i].receiverName}</td>
                    <td>${data[i].amount}</td>
                    <td>${data[i].message}</td>
                </tr>`;
            }
            tableBody.innerHTML = newContent;
        }
    }
    xmlHttpRequest.open('get', 'http://localhost:8080/api/v1/transactions/search?user_id=' + localStorage.getItem("id"), false);
    xmlHttpRequest.setRequestHeader('Accept', 'application/json')
    xmlHttpRequest.setRequestHeader('Authorization', localStorage.getItem("access_token"))
    xmlHttpRequest.send();
})