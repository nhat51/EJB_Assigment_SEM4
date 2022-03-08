document.addEventListener('DOMContentLoaded', function () {
    var tableBody = document.getElementById('my-table-data');

    var xmlHttpRequest = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange = function (){
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            var newContent = ''; //
            for (let i = 0; i < data.length; i++) {
                newContent += `<tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>1</td>
                    <td>1</td>
                    <td>a</td>
                    <td>a</td>
                </tr>`;
            }
            tableBody.innerHTML = newContent;
        }
    }
    xmlHttpRequest.open('get', 'http://localhost:8080/api/v1/loans/list?user_id=' + localStorage.getItem("id"), false);
    xmlHttpRequest.send();
})