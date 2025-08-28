package logica;

import logica.Categoria;

public class ControladorCategoria{
	private static volatile ControladorCategoria instance;
	private ControladorCategoria(){};
	
	public static ControladorCategoria getInstance() {
		if (instance == null) {
			synchronized (ControladorCategoria.class) {
				if (instance == null) {
					instance = new ControladorCategoria();
				}
			}
		}
		return instance;
	}
	
	public boolean altaCategoria(String nombreCategoria) {
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		boolean existe = m.existe(nombreCategoria);
		if (!existe) {
			m.altaCategoria(nombreCategoria);
		}
		return existe;
	}
}