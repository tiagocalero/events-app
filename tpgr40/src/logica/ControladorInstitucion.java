package logica;

import logica.Institucion;
import java.util.stream.*;
import java.util.List;

public class ControladorInstitucion{
	//private Institucion institucion;
		
	private static volatile ControladorInstitucion instance;
	private ControladorInstitucion(){};
	
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

	// Crea insitución sin imagen
	public boolean altaInstitucion(String nombre, String desc, String url) {
		return altaInstitucion(nombre, desc, url, null);
	}

	// Crea insitución con imagen
	public boolean altaInstitucion(String nombre, String desc, String url, String imagen) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		boolean res = m.existe(nombre);
		if (!res) {
			m.altaInstitucion(nombre, desc, url, imagen);
		}
		return !res;
	}
	
	public List<String> listarInstituciones() {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		return m.instituciones()
				.values()
				.stream()
				.map(inst -> inst.getNombre())
				.collect(Collectors.toList());
	}
	
	public Institucion obtenerInstitucion(String nombre) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		return m.obtener(nombre);
	}
	
	public boolean verificarCodigoCupos(Edicion edicion, String nombreInstitucion, String codigo) {
		Institucion institucion = this.obtenerInstitucion(nombreInstitucion);
		Patrocinio patrocinio = institucion.obtenerPatrocinio(edicion);
		if (patrocinio.getCodigo().equals(codigo) && (patrocinio.getCantRegistros() > patrocinio.getCantRegistrosUsados())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void aumentarUsados(Edicion edicion, String nombreInstitucion) {
		Institucion institucion = this.obtenerInstitucion(nombreInstitucion);
		Patrocinio patrocinio = institucion.obtenerPatrocinio(edicion);
		patrocinio.aumentarCantRegistrosUsados();
	}
}