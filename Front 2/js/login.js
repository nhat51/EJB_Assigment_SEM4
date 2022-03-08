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
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                var data = JSON.parse(xmlHttpRequest.responseText);
                console.log(data.access_token)
                localStorage.setItem("id",data.id)
                localStorage.setItem("access_token",data.access_token)
                localStorage.setItem("name",data.name)
                localStorage.setItem("email",data.email)
                localStorage.setItem("balance",data.balance)
                localStorage.setItem("account_number",data.account_number)
                localStorage.setItem("phone",data.phone)


                window.location.href = "/Front 2/index.html"
            }
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 401){
                error.innerHTML = `<span style="color: red" class="form-message" id="loginemail_error">Wrong email or password!!</span>`
            }

        }
        xmlHttpRequest.open('POST',"http://localhost:8080/api/v1/accounts/login",false)
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.send(JSON.stringify(dataLogin))
    }
})