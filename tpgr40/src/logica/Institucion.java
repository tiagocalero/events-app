package logica;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean existePatrocinio(Edicion edicion) {
		for (Patrocinio patrocinio: patrocinios) {
			if (patrocinio.buscarEdicion(edicion))
				return true;
		}
		
		return false;	
	}
	
	public Patrocinio obtenerPatrocinio(Edicion edicion) {
		for (Patrocinio patrocinio: patrocinios) {
			if (patrocinio.buscarEdicion(edicion))
				return patrocinio;
		}
		
		return null;
	}
	
    public void agregarPatrocinio(Patrocinio patrocinio) {
    	patrocinios.add(patrocinio);
    }
	
	public String getNombre() {
		return nombre;
	}

	public String getDesc() {
		return desc;
	}

	public String getUrl() {
		return url;
	}
	
	public String getImagen() {
		return imagen;
	}
}