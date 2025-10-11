package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		if (!existe)
			m.altaCategoria(nombreCategoria);
		
		return !existe;
	}
	
	public List<String> categorias(){
		ManejadorCategoria m = ManejadorCategoria.getInstance();
		Map<String, Categoria> categorias = m.categorias();
		
		List<String> res = new ArrayList<>();
		
		for (String entry : categorias.keySet())
			res.add(entry);
		
		return res;
	}
}