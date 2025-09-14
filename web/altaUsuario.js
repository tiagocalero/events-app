function togglePassword() {
    const input = document.getElementById("pass");
    const input2 = document.getElementById("passconf");
    const icon = document.querySelector("#passbtn i");

    const isHidden = input.type === "password";

    input.type = isHidden ? "text" : "password";
    input2.type = isHidden ? "text" : "password";

    icon.classList.toggle("nf-cod-eye");
    icon.classList.toggle("nf-cod-eye_closed");
}

function toggleTipoUsuario() {
    const btn = document.getElementById('tipoUsuario');
    const inputAs = document.getElementById('datosAs');
    const inputOrg = document.getElementById('datosOrg');

    if (btn.textContent == 'Organizador') {
        btn.textContent = 'Asistente'
        datosAs.style.display = 'inline-block';
        datosOrg.style.display = 'none';
    } else {
        btn.textContent = 'Organizador'
        datosAs.style.display = 'none';
        datosOrg.style.display = 'inline-block';
    }
}
