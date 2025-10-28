package logica;

import java.time.LocalDate;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTEvento {
	private String nombre;
	private String sigla;
	private String descripcion;
	private LocalDate fechaAlta;
	private List<String> categorias;
	private List<String> ediciones;
	private String imagen;
	private int visitas;
	
	public DTEvento() {}
	
	public DTEvento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias, List<String> ediciones, String imagen, int visitas) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.categorias = categorias;
		this.ediciones = ediciones;
		this.imagen = imagen;
		this.visitas = visitas;
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
	public int getVisitas() {
		return visitas;
	}
}