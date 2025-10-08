package logica;

import java.time.LocalDate;
import java.util.List;

public class DTEvento {
	private String nombre;
	private String sigla;
	private String descripcion;
	private LocalDate fechaAlta;
	private List<String> categorias;
	private List<String> ediciones;
	private String imagen;
	
	public DTEvento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias, List<String> ediciones, String imagen) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.categorias = categorias;
		this.ediciones = ediciones;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public List<String> getCategorias() {
		return categorias;
	}
	
	public List<String> getEdiciones() {
		return ediciones;
	}
	
	public String getImagen() {
		return imagen;
	}
}