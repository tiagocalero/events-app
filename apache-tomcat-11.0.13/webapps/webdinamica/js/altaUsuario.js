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
        inputAs.style.display = 'inline-block';
        inputOrg.style.display = 'none';
    } else {
        btn.textContent = 'Organizador'
        inputAs.style.display = 'none';
        inputOrg.style.display = 'inline-block';
    }
}

function validarSeleccionAltaUsuario() {
	const input = document.getElementById("pass");
	const input2 = document.getElementById("passconf");
	if (input.value != input2.value) {
		alert("Las contraseñas no coinciden.");
		return false; // evita el submit
	}
	
	const nac = document.getElementById("nacimiento");
	const tipo = document.getElementById('tipoUsuario');
	if (tipo.innerText == "Asistente" && nac.value == "") {
		alert("Debes seleccionar una fecha de nacimiento");
		return false;
	}
	
	return true;
}

function updateHiddenField() {
	toggleTipoUsuario();
    var tipoUsuarioValue = document.getElementById('tipoUsuario').innerText;
    document.getElementById('hiddenTipoUsuario').value = tipoUsuarioValue;
}