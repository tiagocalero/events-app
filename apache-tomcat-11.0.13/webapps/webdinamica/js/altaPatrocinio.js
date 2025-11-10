function validarSeleccionAltaPatrocinio() {
	const codigoInput = document.getElementById('codigo');
	const montoInput = document.getElementById('monto');
	const cantRegistrosInput = document.getElementById('cantRegistros');

	const mensajeErrorCodigoVacio = document.getElementById("error_codigo_vacio");
	const mensajeErrorMontoVacio = document.getElementById("error_monto_vacio");
	const mensajeErrorCantRegistrosVacio = document.getElementById("error_cant_registros_vacio");

	mensajeErrorCodigoVacio.style.display = "none";
	mensajeErrorMontoVacio.style.display = "none";
	mensajeErrorCantRegistrosVacio.style.display = "none";

	if (!codigoInput.value.trim()) {
		mensajeErrorCodigoVacio.style.display = "block";
		return false; // evita el submit
	}
	if (montoInput.value === "") {
		mensajeErrorMontoVacio.style.display = "block";
		return false; // evita el submit
	}
	if (cantRegistrosInput.value === "") {
		mensajeErrorCantRegistrosVacio.style.display = "block";
		return false; // evita el submit
	}

	return true;
}