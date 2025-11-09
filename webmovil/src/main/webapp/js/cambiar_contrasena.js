function togglePassword() {
    const input = document.getElementById("pass");
    const icon = document.querySelector("#passbtn i");

    const isHidden = input.type === "password";

    input.type = isHidden ? "text" : "password";

    icon.classList.toggle("nf-cod-eye");
    icon.classList.toggle("nf-cod-eye_closed");
}

function toggleNewPassword() {
    const input = document.getElementById("newPass");
    const input2 = document.getElementById("confNewPass");
    const icon = document.querySelector("#passbtn2 i");

    const isHidden = input.type === "password";

    input.type = isHidden ? "text" : "password";
    input2.type = isHidden ? "text" : "password";

    icon.classList.toggle("nf-cod-eye");
    icon.classList.toggle("nf-cod-eye_closed");
}