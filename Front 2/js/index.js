document.addEventListener('DOMContentLoaded', function () {
   if (localStorage.length < 0){
       window.location.href = "/Front 2/Login.html"
   }
   if (localStorage.length > 0){
        var name = document.getElementById("user-name");
        var balance = document.getElementById("balance");
        var email = document.getElementById("email");
        var phone = document.getElementById("phone");
        var accountNumber = document.getElementById("account-number")

        var nameValue = localStorage.getItem("name");
        var emailValue = localStorage.getItem("email");
        var balanceValue = localStorage.getItem("balance");
        var accountNumberValue = localStorage.getItem("account_number");
        var phoneValue = localStorage.getItem("phone");

        name.innerHTML = nameValue
        balance.innerHTML = balanceValue
        email.innerHTML = emailValue
        phone.innerHTML = phoneValue
        accountNumber.innerHTML = accountNumberValue
   }
})