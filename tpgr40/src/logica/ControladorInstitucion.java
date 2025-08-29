package logica;

import logica.Institucion;

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
	
	
}