import java.time.LocalDate;
import java.util.stream.Stream;
import categoria;
import dtevento;

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
		
		ManejadorCategoria mc = ManejadorCategoria.getInstance();
		for (String cat: categorias) {
			mc.asignar(cat, this);
		}
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
				 	  .map(c -> c.getNombre())
					  .collect(Collectors.toList());
		
		return new DTEvento(nombre, sigla, descripcion, fechaAlta, nomCategorias);
	}
	
	public Edicion obtenerEdicion(String nombre) {
		return ediciones.get(nombre);
	}
	
	public Stream<Map.Entry<String, Edicion>> obtenerEdiciones() {
		return ediciones.entrySet().stream();
	}
	
	public void agregarCategoria(Categoria c) {
		categorias.add(c);
	}
}