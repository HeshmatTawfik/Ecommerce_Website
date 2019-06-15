var newPassword = document.getElementById("new_password");
var confirmPassword = document.getElementById("confirm_password");
var current=document.getElementById("current_password")

var size = 6;

function validatePassword() {
    if (newPassword.value.length <= 0) {
        document.getElementById("new_password").setCustomValidity("please fill this field");

    }
    if (newPassword.value.length >= size) {
        document.getElementById("new_password").setCustomValidity('');

    }

    else {
        document.getElementById("new_password").setCustomValidity("Passwords password must be at least 6 digits");

    }
    if (newPassword.value != confirmPassword.value) {
        document.getElementById("confirm_password").setCustomValidity("Passwords do not match!");

    }

    else {
        //empty string means no validation error
        document.getElementById("confirm_password").setCustomValidity('');


    }

}

function show() {
    var x = document.getElementById("current_password");
    var y=document.getElementById('new_password');
    var z=document.getElementById('confirm_password')
    if (x.type === "password"&&y.type==="password" &&y.type==="password") {
        x.type = "text";
        y.type="text";
        z.type="text";
    } else {
        x.type = "password";
        y.type = "password";
        z.type = "password";

    }

}



newPassword.addEventListener("change", validatePassword);
confirmPassword.addEventListener("change", validatePassword);
newPassword.addEventListener("change", validatePassword);
