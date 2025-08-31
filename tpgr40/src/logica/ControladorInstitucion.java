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
	
	public boolean altaInstitucion(String nombre, String desc, String url) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		boolean res = m.existe(nombre);
		if (!res) {
			m.altaInstitucion(nombre, desc, url);
		}
		return !res;
	}
	
	public List<String> listarInstituciones() {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		return m.instituciones().values().stream().map(inst->inst.getNombre()).collect(Collectors.toList());
	}
	
	public Institucion obtenerInstitucion(String nombre) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		return m.obtener(nombre);
	}
}