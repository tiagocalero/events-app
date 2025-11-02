package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Evento {
	private String nombre;
	private String sigla;
	private LocalDate fechaAlta;
	private List<Categoria> categorias;
	private Map<String, Edicion> ediciones;
	private String descripcion;
	private String imagen;
	private Boolean finalizado;
	private int visitas;
	
	public Evento(String nombre, String sigla, String descripcion, LocalDate fechaAlta, List<String> categorias, String imagen, Boolean finalizado) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.categorias = new ArrayList<>();
		this.imagen = imagen;
		this.finalizado= finalizado;
		ediciones = new HashMap<>();
		
		ManejadorCategoria manejador = ManejadorCategoria.getInstance();
		for (String cat: categorias)
			manejador.asignar(cat, this);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DTEvento getDatos() {
		List<String> nomCategorias = 
			categorias.stream()
				 	  .map(c -> c.getNombre()) // Convierte cada categoría en su nombre
					  .collect(Collectors.toList()); // Junta los resultados en una lista

		List<String> nomEdiciones = new ArrayList<>(ediciones.keySet());
		
		return new DTEvento(nombre, sigla, descripcion, fechaAlta, nomCategorias, nomEdiciones, imagen,finalizado, visitas);
	}
	
	public DTEvento getDatosAceptadas() {
		List<String> nomCategorias = 
			categorias.stream()
				 	  .map(c -> c.getNombre()) // Convierte cada categoría en su nombre
					  .collect(Collectors.toList()); // Junta los resultados en una lista

		List<String> nomEdiciones = new ArrayList<>();
		
		for (Map.Entry<String, Edicion> entry : ediciones.entrySet()) {
		    if (entry.getValue().getEstado() == TipoEstado.Aceptada) {
		        nomEdiciones.add(entry.getKey());
		    }
		}
		return new DTEvento(nombre, sigla, descripcion, fechaAlta, nomCategorias, nomEdiciones, imagen, finalizado, visitas);
	}
	
	public boolean existeEdicion(String nombre) {
		return ediciones.containsKey(nombre);
	}
	
	public Edicion getEdicion(String nombre) {
		return ediciones.get(nombre);
	}

	public void agregarEdicion(Edicion edicion) {
		ediciones.put(edicion.getNombre(), edicion);
	}
	
	public Map<String, Edicion> getEdiciones() {
		return Map.copyOf(ediciones);
	}
	
	public void agregarCategoria(Categoria categoria) {
		categorias.add(categoria);
	}
	
	public boolean getFinalizado() {
		return finalizado;
	}
	
	public void finalizar() {
		this.finalizado = true;
	}
	/*
	public int incrementarVisitar() {
		this.visitas++;
	}*/
}