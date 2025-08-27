package logica

import Institucion

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
	
	public bool altaInstitucion(String nombre, String desc, String url) {
		ManejadorInstitucion m = ManejadorInstitucion.getInstance();
		bool res = m.existe(nombre);
		if (!res) {
			m.altaInstitucion(nombre, desc, url);
		}
		return !res;
	}
	
	
}