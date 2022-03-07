document.addEventListener('DOMContentLoaded', function () {
    var btnLogin = document.getElementById("btn-login");
    var  error = document.getElementById("error")

    var txtEmail = document.forms['login-form']['email']
    var txtPassword = document.forms['login-form']['password']

    btnLogin.onclick = function (){
        var email = txtEmail.value;
        var password = txtPassword.value;

        var dataLogin = {
            "name": email,
            "password": password
        }
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function (){
        }
        xmlHttpRequest.open('get',"http://localhost:8080/api/v1/accounts/login",true)
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.send(JSON.stringify(dataLogin))
    }
})