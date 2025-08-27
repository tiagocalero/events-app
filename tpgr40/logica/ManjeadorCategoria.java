package logica;

import Categoria;

public class ManjeadorCategoria {
	private Map<String, Categoria> categorias;
    private static volatile ManejadorCategoria instance;

    private ManejadorCategoria() {}

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
    
    public altaCategoria(String nombreCategoria) {
    	Categria nuevaCat = new Categoria(nombreCategoria);
    	categorias.put(nombreCategoria, nuevaCat);
    }
    
    public bool existe(String nombreCategoria) {
    	bool res = categorias.containsKey(nombreCategoria);
    	return res;
    }
    
    public void asignar(String cat, Evento evento) {
    	if (!categorias.containsKey(nombreCategoria)) {
    		Categria nuevaCat = new Categoria(nombreCategoria);
        	categorias.put(nombreCategoria, nuevaCat);
        	nuevaCat.crearLink(evento);
        	evento.agregarCategoria(nuevaCat),
    	}
    	else {
    		Categoria categoria = get(cat);
    		categoria.crearLink(evento);
    		evento.agregarCategoria(cat),
    	}
    }
    
}
