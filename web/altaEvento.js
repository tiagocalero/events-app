function validarSeleccion() {
	const select = document.getElementById("categorias");
	if (select.selectedOptions.length === 0) {
		alert("Debes seleccionar al menos una categoría.");
		return false; // evita el submit
	}
	return true;
}