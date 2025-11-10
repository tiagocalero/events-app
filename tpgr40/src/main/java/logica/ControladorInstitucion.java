package main.java.logica;

import java.util.List;
import java.util.stream.Collectors;

public class ControladorInstitucion{
	private static volatile ControladorInstitucion instance;
	private ControladorInstitucion() {}
	
	public static ControladorInstitucion getInstance() {
		if (instance == null) {
			synchronized (ControladorInstitucion.class) {
				if (instance == null) {
					instance = new ControladorInstitucion();
				}
			}
		}
		return instance;
	}

	// Crea institución sin imagen
	public boolean altaInstitucion(String nombre, String desc, String url) {
		return altaInstitucion(nombre, desc, url, null);
	}

	// Crea institución con imagen
	public boolean altaInstitucion(String nombre, String desc, String url, String imagen) {
		ManejadorInstitucion manejador = ManejadorInstitucion.getInstance();
		boolean res = manejador.existe(nombre);
		if (!res)
			manejador.altaInstitucion(nombre, desc, url, imagen);

		return !res;
	}
	
	public List<String> listarInstituciones() {
		ManejadorInstitucion manejador = ManejadorInstitucion.getInstance();
		return manejador.instituciones()
						.values()
						.stream()
						.map(inst -> inst.getNombre())
						.collect(Collectors.toList());
	}
}