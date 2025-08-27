package logica;

import logica.Evento;
import java.util.Map;

public class Categoria {
	private String nombre;
	private Map<String, Evento> eventos;
	
	public Categoria(String nombreCategoria) {
		this.nombre = nombreCategoria;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void crearLink(Evento evento) {
		eventos.put(evento.getNombre(), evento);
	}
}