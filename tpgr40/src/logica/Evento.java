package logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import logica.Categoria;
import logica.DTEvento;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Evento {
	private String nombre;
	private String sigla;
	private String descripcion;
	private LocalDate fechaAlta;
	private List<Categoria> categorias;
	private Map<String, Edicion> ediciones;
	
	public Evento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.categorias = new ArrayList<>();
		ediciones = new HashMap<>();
		
		ManejadorCategoria mc = ManejadorCategoria.getInstance();
		for (String cat: categorias)
			mc.asignar(cat, this);
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
	
	public DTEvento getDatos() {
		List<String> nomCategorias = 
			categorias.stream()
				 	  .map(c -> c.getNombre()) // Convierte cada categoría en su nombre
					  .collect(Collectors.toList()); // Junta los resultados en una lista

		List<String> nomEdiciones = new ArrayList<>(ediciones.keySet());
		
		return new DTEvento(nombre, sigla, descripcion, fechaAlta, nomCategorias, nomEdiciones);
	}
	
	public boolean existeEdicion(String nombre) {
		return ediciones.containsKey(nombre);
	}
	
	public Edicion getEdicion(String nombre) {
		return ediciones.get(nombre);
	}

	public void agregarEdicion(Edicion e) {
		ediciones.put(e.getNombre(), e);
	}
	
	public Map<String, Edicion> getEdiciones() {
		return Map.copyOf(ediciones);
	}
	
	public void agregarCategoria(Categoria c) {
		categorias.add(c);
	}
}