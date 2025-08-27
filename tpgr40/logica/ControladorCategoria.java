package logica;

import logica.Categoria;

public class ControladorCategoria{
	//private Categoria categoria;
		
	private static volatile ControladorCatgeoria instance;
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
	
	public bool altaCategoria(String nombreCategoria) {
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		bool existe = m.existe(nombreCategoria);
		if (!bool) {
			m.altaCategoria(nombreCategoria);
		}
		return bool;
	}
}