package logica;

import java.util.Map;
import java.util.HashMap;

public class Categoria {
	private String nombre;
	private Map<String, Evento> eventos;
	
	public Categoria(String nombreCategoria) {
		this.nombre = nombreCategoria;
		eventos = new HashMap<>();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void crearLink(Evento evento) {
		eventos.put(evento.getNombre(), evento);
	}

	public Map<String, Evento> eventosCategoria(){
		return Map.copyOf(eventos);
	}
}