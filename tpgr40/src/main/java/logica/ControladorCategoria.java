package main.java.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorCategoria {
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
	
	public List<String> categorias() {
		ManejadorCategoria manejador = ManejadorCategoria.getInstance();
		Map<String, Categoria> categorias = manejador.categorias();
		
		List<String> res = new ArrayList<>();
		
		for (String entry : categorias.keySet())
			res.add(entry);
		
		return res;
	}
}