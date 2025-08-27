package logica;

import Institucion;

public class ManejadorInstitucion {
	private Map<String, Insitucion> instituciones;
    private static volatile ManejadorCategoria instance;

    private ManejadorInstitucion() {}

    public static ManejadorInstitucion() getInstance() {
        if (instance == null) {
            synchronized (ManejadorInstitucion().class) {
                if (instance == null) {
                    instance = new ManejadorInstitucion()();
                }
            }
        }
        return instance;
    }
    
    public altaInstitucion(String nombre, String desc, String url) {
    	Institucion nuevaInst = new Institucion(nombre, desc, url);
    	instituciones.put(nombre, nuevaInst);
    }
    
    public bool existe(String nombre) {
    	bool res = instituciones.containsKey(nombre);
    	return res;
    }
}
