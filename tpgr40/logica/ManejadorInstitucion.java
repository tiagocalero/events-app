package logica;

import logica.Institucion;
import java.util.Map;

public class ManejadorInstitucion {
	private Map<String, Institucion> instituciones;
    private static volatile ManejadorInstitucion instance;

    private ManejadorInstitucion() {}

    public static ManejadorInstitucion getInstance() {
        if (instance == null) {
            synchronized (ManejadorInstitucion.class) {
                if (instance == null) {
                    instance = new ManejadorInstitucion();
                }
            }
        }
        return instance;
    }
    
    public void altaInstitucion(String nombre, String desc, String url) {
    	Institucion nuevaInst = new Institucion(nombre, desc, url);
    	instituciones.put(nombre, nuevaInst);
    }
    
    public boolean existe(String nombre) {
    	return instituciones.containsKey(nombre);
    }
}
