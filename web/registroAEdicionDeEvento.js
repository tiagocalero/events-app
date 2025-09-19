function validarSeleccionRegistroEdicion() {
	const select = document.getElementById("tiposRegistro");
	if (select.selectedOptions.length != 1) {
		alert("Debes seleccionar un tipo de registro.");
		return false; // evita el submit
	}
	return true;
}