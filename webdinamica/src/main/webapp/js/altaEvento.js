function validarSeleccionAltaEvento() {
	const categoriasSelect = document.getElementById("categorias");
	const nombreInput = document.getElementById('nombre');
	
	const mensajeErrorCategorias = document.getElementById("error_categorias");
	const mensajeErrorNombreRepetido = document.getElementById("error_nombre_repetido");
	const mensajeErrorNombreVacio = document.getElementById("error_nombre_vacio");

	mensajeErrorCategorias.style.display = "none";
	mensajeErrorNombreRepetido.style.display = "none";
	mensajeErrorNombreVacio.style.display = "none";

	if (!nombreInput.value.trim()) {
		mensajeErrorNombreVacio.style.display = "block";
		return false; // evita el submit
	}
	
	// Si no hay categorías seleccionadas, mostrar error
	if (categoriasSelect.selectedOptions.length === 0) {
		mensajeErrorCategorias.style.display = "block";
		return false; // evita el submit
	}

	return true;
}