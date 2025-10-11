package logica;

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
		ManejadorCategoria manejador = ManejadorCategoria.getInstance();
		boolean existe = manejador.existe(nombreCategoria);
		if (!existe)
			manejador.altaCategoria(nombreCategoria);
		
		return !existe;
	}
}