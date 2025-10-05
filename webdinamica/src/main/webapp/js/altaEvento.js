function validarSeleccionAltaEvento() {
	const select = document.getElementById("categorias");
	const mensaje_error = document.getElementById("error_categorias");
	
	// Si no hay categorías seleccionadas, mostrar error
	if (select.selectedOptions.length === 0) {
		mensaje_error.style.display = "block";
		return false; // evita el submit
	}
	mensaje_error.style.display = "none";
	return true;
}