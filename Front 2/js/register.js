document.addEventListener('DOMContentLoaded', function () {
    var btnRegister = document.getElementById("btn-register");

    var txtEmail = document.forms['register-form']['email']
    var txtName = document.forms['register-form']['name']
    var txtAccountNumber = document.forms['register-form']['accountNumber']
    var txtPhoneNumber = document.forms['register-form']['phoneNumber']
    var txtPassword = document.forms['register-form']['password']
    var txtRePassword = document.forms['register-form']['repassword']

    var error = document.getElementById("accountNumber-error");
    var nameError = document.getElementById("username_error");
    var phoneError = document.getElementById("phone-error");
    var passErr = document.getElementById("repassword_error");

    btnRegister.onclick = function () {
        var email = txtEmail.value;
        var name = txtName.value;
        var password = txtPassword.value;
        var rePass = txtRePassword.value;
        var phone = txtPhoneNumber.value;
        var accountNumber = txtAccountNumber.value;

        var register = {
            "name": name,
            "password": password,
            "email": email,
            "phone": phone,
            "accountNumber": accountNumber
        }
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function () {

            var data = JSON.parse(xmlHttpRequest.responseText);
            if (data.status === "BAD_REQUEST" && data.data == null) {
                error.innerHTML = `<p style="color: red">${data.message}</p>`
            } else if (data.status == "") {
                error.innerHTML = `<p style="color: red">Password must not empty</p>`
            }
            if (name == "") {
                nameError.innerHTML = `<p style="color: red">Name must not empty</p>`
            }
            if (phone == "") {
                phoneError.innerHTML = `<p style="color: red">Phone must not empty</p>`
            }
            if (password != rePass) {
                passErr.innerHTML = `<p style="color: red">Password not match</p>`
            }
            else {
                alert("Successful please login")
            }
        }
        xmlHttpRequest.open('POST', "http://localhost:8080/api/v1/accounts/register", false)
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
        xmlHttpRequest.send(JSON.stringify(register))
    }
})