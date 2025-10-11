package logica;

import logica.Categoria;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManejadorCategoria {
	private Map<String, Categoria> categorias;
    private static volatile ManejadorCategoria instance;

    private ManejadorCategoria() {
    	categorias = new HashMap<>();
    }

    public static ManejadorCategoria getInstance() {
        if (instance == null) {
            synchronized (ManejadorCategoria.class) {
                if (instance == null) {
                    instance = new ManejadorCategoria();
                }
            }
        }
        return instance;
    }
    
    public static void borrarInstance() {
    	instance = null;
    }
    
    public void altaCategoria(String nombreCategoria) {
    	Categoria nuevaCat = new Categoria(nombreCategoria);
    	categorias.put(nombreCategoria, nuevaCat);
    }
    
    public boolean existe(String nombreCategoria) {
    	return categorias.containsKey(nombreCategoria);
    }
    
    public void asignar(String cat, Evento evento) {
    	if (!categorias.containsKey(cat)) {
    		Categoria nuevaCat = new Categoria(cat);
        	categorias.put(cat, nuevaCat);
        	nuevaCat.crearLink(evento);
        	evento.agregarCategoria(nuevaCat);
    	} else {
    		Categoria categoria = categorias.get(cat);
    		categoria.crearLink(evento);
    		evento.agregarCategoria(categoria);
    	}
    }

	// Devuelve una copia del map de categorías sobre el que se puede iterar
	public Map<String, Categoria> categorias() {
		return Map.copyOf(categorias);
		
    }
	
	public Map<String, Evento> eventosCategoria(String categoria){
		Categoria cat = categorias.get(categoria);
		return cat.eventosCategoria();
	}
}
