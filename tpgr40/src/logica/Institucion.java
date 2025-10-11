package logica;

import logica.Patrocinio;
import java.util.List;
import java.util.ArrayList;

public class Institucion {
	private String nombre;
	private String desc;
	private String url;
	private List<Patrocinio> patrocinios;
	private String imagen;
	
	public Institucion(String nombre, String desc, String url, String imagen) {
		this.nombre = nombre;
		this.desc = desc;
		this.url = url;
		this.imagen = imagen;
		patrocinios = new ArrayList<>();
	}
	
	public boolean existePatrocinio(Edicion e) {
		for (Patrocinio p: patrocinios) {
			if (p.buscarEdicion(e))
				return true;
		}
		
		return false;	
	}
	
	public Patrocinio obtenerPatrocinio(Edicion e) {
		for (Patrocinio p: patrocinios) {
			if (p.buscarEdicion(e))
				return p;
		}
		
		return null;
	}
	
    public void agregarPatrocinio(Patrocinio p) {
    	patrocinios.add(p);
    }
	
	public String getNombre() {
		return nombre;
	}
	
	public String getImagen() {
		return imagen;
	}
}